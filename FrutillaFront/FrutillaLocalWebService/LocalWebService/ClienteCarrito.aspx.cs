using LocalWebService.ClienteWS;
using LocalWebService.InventarioWS;
using LocalWebService.LocalWS;
using LocalWebService.PedidoWS;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace LocalWebService
{
    public partial class ClienteCarrito : System.Web.UI.Page
    {
        private PedidoWSClient pedidoWS;
        private LocalWSClient localWS;
        private ClienteWSClient clienteWS;
        int idLocalSeleccionado = 0;
        int idClienteGlobal = 0;

        public ClienteCarrito()
        {
            pedidoWS = new PedidoWSClient();
            localWS = new LocalWSClient();
            clienteWS = new ClienteWSClient();
        }

        private Dictionary<int, List<PedidoWS.lineaOrdenDeVenta>> CarritoPorLocal
        {
            get
            {
                if (Session["Carritos"] == null)
                    Session["Carritos"] = new Dictionary<int, List<PedidoWS.lineaOrdenDeVenta>>();
                return (Dictionary<int, List<PedidoWS.lineaOrdenDeVenta>>)Session["Carritos"];
            }
        }

        private List<PedidoWS.lineaOrdenDeVenta> CarritoDelLocal
        {
            get
            {
                int idLocal = Session["idLocal"] != null ? (int)Session["idLocal"] : 0;
                if (CarritoPorLocal.ContainsKey(idLocal))
                    return CarritoPorLocal[idLocal];
                else
                    return new List<PedidoWS.lineaOrdenDeVenta>();
            }
        }

        protected void Page_Load(object sender, EventArgs e)
        {

            if (User.Identity.IsAuthenticated)
            {
                string datos = FormsAuthentication.Decrypt(
                    Request.Cookies[FormsAuthentication.FormsCookieName].Value
                ).UserData;

                string[] partes = datos.Split('|');
                string tipoUsuario = partes[0];

                int idUsuario = 10; //el id del clientePrueba 

                if (tipoUsuario == "C")
                {
                    idUsuario = int.Parse(partes[1]);

                }

                cliente cliente = clienteWS.obtenerClientePorId(idUsuario);

                if (cliente != null)
                {
                    idClienteGlobal = cliente.idUsuario;
                }
                else
                {
                    Response.Redirect("Login.aspx");
                }
            }
            else
            {
                Response.Redirect("Login.aspx");
            }
            if (!IsPostBack)
            {
                

                BindCarrito();
            }
        }

        //private void llenarDropdownLocales()
        //{
        //    ddlLocales.DataSource = localWS.listarLocales();
        //    ddlLocales.DataTextField = "nombre";
        //    ddlLocales.DataValueField = "idLocal";
        //    ddlLocales.DataBind();
        //    ddlLocales.Items.Insert(0, new ListItem("Seleccione un local", "0"));
        //}

        private void BindCarrito()
        {


            // 1) Recuperar idLocal
                if (Session["idLocal"] == null)
                Response.Redirect("ClienteHome.aspx");

            int idLocal = (int)Session["idLocal"];

            // 2) Obtener el objeto Local completo
            var cliente = new LocalWSClient();
            local local = cliente.obtenerLocal(idLocal);
            cliente.Close();

            // Guárdalo en un label o en una propiedad si lo necesitas luego
            lblNombreLocal.Text = local.nombre;
            lblDireccion.Text = local.direccion;

            var carrito = CarritoDelLocal ?? new List<PedidoWS.lineaOrdenDeVenta>();

            foreach (var item in carrito)
            {
                if (item.subtotal <= 0 && item.producto != null)
                {
                    item.subtotal = item.cantidad * item.producto.precioUnitario;
                }
            }

            gvCarrito.DataSource = carrito;
            gvCarrito.DataBind();

            if (carrito.Count == 0)
            {
                lblSubtotal.Text = "S/ 0.00";
                lblIGV.Text = "S/ 0.00";
                lblTotal.Text = "S/ 0.00";
                btnPagar.Enabled = false;
                return;
            }

            decimal subtotal = carrito.Sum(x => Convert.ToDecimal(x.subtotal));
            decimal igv = subtotal * 0.18m;
            decimal total = subtotal + igv;

            lblSubtotal.Text = subtotal.ToString("C");
            lblIGV.Text = igv.ToString("C");
            lblTotal.Text = total.ToString("C");
            btnPagar.Enabled = true;
        }

        protected void gvCarrito_RowCommand(object sender, GridViewCommandEventArgs e)
        {
            var carrito = CarritoDelLocal;
            if (carrito == null) return;

            int index = Convert.ToInt32(e.CommandArgument);
            if (index < 0 || index >= carrito.Count) return;

            switch (e.CommandName)
            {
                case "Aumentar":
                    carrito[index].cantidad++;
                    carrito[index].subtotal = carrito[index].cantidad * carrito[index].producto.precioUnitario;
                    break;

                case "Disminuir":
                    if (carrito[index].cantidad > 1)
                    {
                        carrito[index].cantidad--;
                        carrito[index].subtotal = carrito[index].cantidad * carrito[index].producto.precioUnitario;
                    }
                    break;

                case "Eliminar":
                    carrito.RemoveAt(index);
                    break;
            }

            Session["Carrito"] = carrito;
            BindCarrito();
        }

        protected void btnPagar_Click(object sender, EventArgs e)
        {

            var carrito = CarritoDelLocal;

            if (carrito == null || carrito.Count == 0)
            {
                Response.Write("El carrito está vacío.");
                return;
            }

            
            // Extraer valores de sesión (asegúrate de tenerlos correctamente configurados en login)
            int idLocal = Session["idLocal"] != null ? (int)Session["idLocal"] : 0;

            string fechaStr = DateTime.Now.ToString("yyyy-MM-dd");
            string horaStr = DateTime.Now.ToString("HH:mm:ss");

            var nuevaOrden = new PedidoWS.ordenVentaDTO
            {
                idEmpleado = 0,
                idLocal = idLocal,
                idCliente = idClienteGlobal,
                fechaStr = fechaStr,
                horaStr = horaStr,
                estado = PedidoWS.estadoVenta.PROCESO,
                entregado = false,
                montoTotal = carrito.Sum(c => c.subtotal),
                descripcion = "Pedido generado desde ClienteCarrito.aspx"
            };

            try
            {
                var lineas = carrito.Select(c => new PedidoWS.lineaOrdenDeVenta
                {
                    cantidad = c.cantidad,
                    subtotal = c.subtotal,
                    producto = new PedidoWS.producto
                    {
                        idProducto = c.producto.idProducto,
                        nombre = c.producto.nombre,
                        precioUnitario = c.producto.precioUnitario
                    }
                }).ToArray();

                int idGenerado = pedidoWS.generarOrdenConLineas(nuevaOrden, lineas);

                // el false indica: no abortes el hilo inmediatamente
                Response.Redirect($"ClientePago.aspx?id={idGenerado}", false);

                // indica a ASP.NET que complete limpiamente la petición
                Context.ApplicationInstance.CompleteRequest();

                // sales del método para que no siga procesando nada más
                return;
            }
            catch (Exception ex)
            {
                Response.Write("Error al guardar el pedido: " + ex.Message);

                Response.Redirect("ClientePago.aspx?id=-1");
            }
        }

        //protected void ddlLocales_SelectedIndexChanged(object sender, EventArgs e)
        //{
        //    idLocalSeleccionado = int.Parse(ddlLocales.SelectedValue);
        //    Session["idLocal"] = idLocalSeleccionado;
        //}
    }
}
