using LocalWebService.ComprobanteWS;
using LocalWebService.InventarioWS;
using LocalWebService.PedidoWS;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace LocalWebService
{
    public partial class ClienteCarrito : System.Web.UI.Page
    {
        private PedidoWSClient pedidoWS;

        private List<ComprobanteWS.lineaOrdenDeVenta> CarritoSesion
            => Session["Carrito"] as List<ComprobanteWS.lineaOrdenDeVenta>;

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                BindCarrito();
            }
        }

        private void BindCarrito()
        {
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

<<<<<<< Updated upstream
            lblSubtotal.Text = subtotal.ToString("C");
            lblIGV.Text = igv.ToString("C");
            lblTotal.Text = total.ToString("C");
            btnPagar.Enabled = true;
        }
           
        protected void btnPagar_Click(object sender, EventArgs e)
        {
            int servicioOrdenId =  1;

            //subimos a la BD las lineas ordenes de venta del carrito


            //crea la orden de servicio "EN_PROCESO"


            // Aquí puedes hacer lo que necesites con el idProducto,
            // por ejemplo, redirigir a una página con detalles:
            //Pasamos el idOrden

            Response.Redirect($"ClientePago.aspx?id={servicioOrdenId}");
=======
            // Extraer valores de sesión (asegúrate de tenerlos correctamente configurados en login)
            int idEmpleado = Session["idEmpleado"] != null ? (int)Session["idEmpleado"] : 0;
            int idLocal = Session["idLocal"] != null ? (int)Session["idLocal"] : 0;
            int idCliente = Session["idCliente"] != null ? (int)Session["idCliente"] : 0;

            string fechaStr = DateTime.Now.ToString("yyyy-MM-dd");
            string horaStr = DateTime.Now.ToString("HH:mm:ss");

            var nuevaOrden = new PedidoWS.ordenVenta
            {
                idEmpleado = idEmpleado,
                idLocal = idLocal,
                idCliente = idCliente,
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

                if (idGenerado == -1)
                {
                    Response.Write("Error al generar la orden. Inténtalo más tarde.");

                    Response.Redirect("ClientePago.aspx?id=-1");
                    return;
                }

                Session["Carrito"] = null;

                // Redireccionar a la página de confirmación
                Response.Redirect("ClientePago.aspx?id=" + idGenerado);
            }
            catch (Exception ex)
            {
                Response.Write("Error al guardar el pedido: " + ex.Message);

                Response.Redirect("ClientePago.aspx?id=-1");
            }
>>>>>>> Stashed changes
        }
    }
}
