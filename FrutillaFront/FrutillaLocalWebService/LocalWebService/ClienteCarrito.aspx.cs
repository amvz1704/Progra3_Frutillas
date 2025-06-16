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
            gvCarrito.RowCommand += gvCarrito_RowCommand;

            if (!IsPostBack)
            {
                BindCarrito();
            }
        }

        protected void gvCarrito_RowCommand(object sender, GridViewCommandEventArgs e)
        {
            if (e.CommandName == "Eliminar")
            {
                int index = Convert.ToInt32(e.CommandArgument);
                var carrito = CarritoSesion;

                if (carrito != null && index >= 0 && index < carrito.Count)
                {
                    carrito.RemoveAt(index);
                    Session["Carrito"] = carrito;
                    BindCarrito();
                }
            }
        }

        private void BindCarrito()
        {
            var carrito = CarritoSesion ?? new List<ComprobanteWS.lineaOrdenDeVenta>();

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
            // Aquí rediriges a la pasarela de pago u otra página
            Response.Redirect("ClientePago.aspx");
        }
    }
}
