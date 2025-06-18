using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using LocalWebService.ComprobanteWS;
using LocalWebService.InventarioWS;

namespace LocalWebService
{
    public partial class ClienteCarrito : System.Web.UI.Page
    {
        private List<ComprobanteWS.lineaOrdenDeVenta> CarritoSesion
            => Session["Carrito"] as List<ComprobanteWS.lineaOrdenDeVenta>;

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                BindCarrito();
            }
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

        private void BindCarrito()
        {
            var carrito = CarritoSesion ?? new List<ComprobanteWS.lineaOrdenDeVenta>();

            // Si el subtotal no está calculado, lo calculamos
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
           
        protected void btnPagar_Click(object sender, EventArgs e)
        {
            int servicioOrdenId =  1; 

            //subimos a la BD las lineas ordenes de venta del carrito


            //crea la orden de servicio "EN_PROCESO"

            //Pasamos el idOrden

            Response.Redirect("ClientePago.aspx?id={servicioOrdenId}");
        }
    }
}
