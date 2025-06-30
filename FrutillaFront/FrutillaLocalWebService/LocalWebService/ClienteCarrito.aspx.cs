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

        private List<PedidoWS.lineaOrdenDeVenta> CarritoSesion
            => Session["Carrito"] as List<PedidoWS.lineaOrdenDeVenta>;

        private Dictionary<int, List<lineaOrdenDeVenta>> Carritos
        {
            get
            {
                if (Session["Carritos"] == null)
                    Session["Carritos"] = new Dictionary<int, List<lineaOrdenDeVenta>>();
                return (Dictionary<int, List<lineaOrdenDeVenta>>)Session["Carritos"];
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
                

                llenarDropdownLocales();

                BindCarrito((int)Session["idLocal"]);
            }
        }



        private void llenarDropdownLocales()
        {
            LocalWSClient localWSClient = new LocalWSClient();
            ddlLocal.Items.Clear();
            foreach (var local in localWSClient.listarLocales()) // Cambiar por locales activos
            {
                ddlLocal.Items.Add(new ListItem(local.nombre, local.idLocal.ToString()));
            }

        }

        protected void ddlLocal_SelectedIndexChanged(object sender, EventArgs e)
        {
            int idLocal;

            // Guardas en Session
            if (int.TryParse(ddlLocal.SelectedValue, out idLocal))
            {
                // Llama a tu método para cargar productos filtrados por el local seleccionado
                Session["idLocal"] = idLocal;
                
                BindCarrito(idLocal);

            }

            else
            {
                // Local “0” o inválido: limpia el grid
                gvCarrito.DataSource = null;
                gvCarrito.DataBind();
            }

        }

        private List<PedidoWS.lineaOrdenDeVenta> GetCart(int idLocal)
        {
            // Si no existe, lo creamos y devolvemos la lista vacía
            if (!Carritos.TryGetValue(idLocal, out var lista))
            {
                lista = new List<PedidoWS.lineaOrdenDeVenta>();
                Carritos[idLocal] = lista;
            }
            return lista;
        }

        private void BindCarrito(int idLocal)
        {



            //var carrito = GetCart(idLocal);      // nunca null, ni excepción
            var carrito = GetCart(idLocal); 

            // 2) Obtener el objeto Local completo
            var cliente = new LocalWSClient();
            local local = cliente.obtenerLocal(idLocal);
            cliente.Close();

            // Guárdalo en un label o en una propiedad si lo necesitas luego


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
            Session["Carrito"] = carrito;
        }

        protected void gvCarrito_RowCommand(object sender, GridViewCommandEventArgs e)
        {
            int idLocal;

            // Guardas en Session
            if (int.TryParse(ddlLocal.SelectedValue, out idLocal))
            {
                // Llama a tu método para cargar productos filtrados por el local seleccionado

                Session["Carrito"] = GetCart(idLocal);
                var carrito = GetCart(idLocal);

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

                Carritos[idLocal] = carrito;
                Session["Carrito"] = carrito;

                BindCarrito(idLocal);
            }
        }

        protected void btnPagar_Click(object sender, EventArgs e)
        {
            int idLocal;

            // Guardas en Session
            if (int.TryParse(ddlLocal.SelectedValue, out idLocal))
            {
                Session["idLocal"] = idLocal; //guardas para pasar

                var carrito = GetCart(idLocal);

                if (carrito == null || carrito.Count == 0)
                {
                    Response.Write("El carrito está vacío.");
                    return;
                }


                //// Extraer valores de sesión (asegúrate de tenerlos correctamente configurados en login)
                //int idLocal = Session["idLocal"] != null ? (int)Session["idLocal"] : 0;

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
            else { 
                //por favor seleccione un local
            
            }
        }

        //protected void ddlLocales_SelectedIndexChanged(object sender, EventArgs e)
        //{
        //    idLocalSeleccionado = int.Parse(ddlLocales.SelectedValue);
        //    Session["idLocal"] = idLocalSeleccionado;
        //}
    }
}
