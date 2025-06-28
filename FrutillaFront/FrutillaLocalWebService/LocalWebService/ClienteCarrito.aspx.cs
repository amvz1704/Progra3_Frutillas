using LocalWebService.ClienteWS;
using LocalWebService.ComprobanteWS;
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
            localWS = new LocalWSClient();
            clienteWS = new ClienteWSClient();
        }

        private List<ComprobanteWS.lineaOrdenDeVenta> CarritoSesion
            => Session["Carrito"] as List<ComprobanteWS.lineaOrdenDeVenta>;

        protected void Page_Load(object sender, EventArgs e)
        {

            if (User.Identity.IsAuthenticated)
            {
                string datos = FormsAuthentication.Decrypt(
                    Request.Cookies[FormsAuthentication.FormsCookieName].Value
                ).UserData;

                string[] partes = datos.Split('|');
                string tipoUsuario = partes[0];

                int idUsuario = int.Parse(partes[1]);
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

            var carrito = CarritoSesion ?? new List<ComprobanteWS.lineaOrdenDeVenta>();

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
            var carrito = CarritoSesion;
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
            var carrito = CarritoSesion;

            if (carrito == null || carrito.Count == 0)
            {
                Response.Write("El carrito está vacío.");
                return;
            }

            pedidoWS = new PedidoWSClient();

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

                int idGenerado = pedidoWS.generarOrdenConLineas(nuevaOrden, lineas);  //EDITAR, funciona en el back pero no en el front

                if (idGenerado == -1)
                {
                    Response.Write("Error al generar la orden. Inténtalo más tarde.");

                    Response.Redirect("ClientePago.aspx?id=-1");
                    return;
                }

                // Redireccionar a la página de confirmación
                // false: NO aborta el hilo, simplemente añade la cabecera de redirección
                Response.Redirect("ClientePago.aspx?id=" + idGenerado, false);

                // Indica a ASP.NET que termine la petición sin abortar el hilo
                Context.ApplicationInstance.CompleteRequest();
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
