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
                // Si no hay carrito, redirigimos
                if (CarritoSesion == null || CarritoSesion.Count == 0)
                {
                    Response.Redirect("ClienteListaProducto.aspx");
                }

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
            gvCarrito.DataSource = CarritoSesion;
            gvCarrito.DataBind();

            // Cálculos de totales
            decimal subtotal = CarritoSesion.Sum(x => (decimal)x.subtotal);
            decimal igv = subtotal * 0.18m;
            decimal total = subtotal + igv;

            lblSubtotal.Text = subtotal.ToString("C");
            lblIGV.Text = igv.ToString("C");
            lblTotal.Text = total.ToString("C");
        }

        protected void btnPagar_Click(object sender, EventArgs e)
        {
            // Aquí rediriges a la pasarela de pago u otra página


            //CREA UN ORDENVENTA EN "PROCESO" --> terminar y hacer llamado a la BD

            Response.Redirect("ClientePago.aspx"); //falta crear pero redirige a pago! --> despues recien se sube a la BD idOrdenVneta
        }
    }
}