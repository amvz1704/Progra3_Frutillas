using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace LocalWebService
{
    public partial class ClientePago : System.Web.UI.Page
    {
        // Helper para leer el carrito de Session
        private List<ComprobanteWS.lineaOrdenDeVenta> Carrito
            => Session["Carrito"] as List<ComprobanteWS.lineaOrdenDeVenta>;

        private int ServicioOrdenId
        {
            get => ViewState["ServicioOrdenId"] as int? ?? 0;
            set => ViewState["ServicioOrdenId"] = value;
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                // 1) Obtener el valor de la URL: ClientePago.aspx?id=123
                string sId = Request.QueryString["id"];
                if (!int.TryParse(sId, out int id))
                {
                    // Parámetro inválido; podrías redirigir o mostrar error
                    Response.Redirect("ClienteCarrito.aspx");
                    return;
                }

                // 2) Guardarlo si luego lo vas a reutilizar
                ServicioOrdenId = id;

                // Si no hay carrito o está vacío, volver al carrito
                if (Carrito == null || !Carrito.Any())
                {
                    Response.Redirect("ClienteCarrito.aspx");
                    return;
                }
                CargarTotales();
            }
        }

        private void CargarTotales()
        {
            // Asumimos que cada línea ya tiene Subtotal = Precio * Cantidad
            decimal subtotal = Carrito.Sum(l => (decimal)l.subtotal);
            decimal igv = subtotal * 0.18m;
            decimal total = subtotal + igv;

            //RECIEN SE INSERTA EN LA BD, se crea un pedido y asi --> luego de eso finalmente se confirma con pagar
            txtPedido.Text = "Por edtiar"; //Recien aqui se crea el pedido y se sube a la BD porque PAGAR en carrito sirve como "confirmar orden"


            txtSubtotal.Text = subtotal.ToString("C2");
            txtIGV.Text = igv.ToString("C2");
            txtTotal.Text = total.ToString("C2");
        }

        protected void SeleccionarMetodo_Click(object sender, EventArgs e)
        {
            var btn = (System.Web.UI.WebControls.Button)sender;
            string metodo = btn.CommandArgument;

            // Marcar visualmente el botón seleccionado
            btnTarjeta.CssClass = metodo == "Tarjeta" ? "btn btn-success" : "btn btn-outline-secondary";
            btnTransferencia.CssClass = metodo == "Transferencia" ? "btn btn-success" : "btn btn-outline-secondary";

            // Mostrar u ocultar sección de bancos si es necesario
            pnlBancos.Visible = metodo == "Transferencia";
        }

        protected void btnCancelar_Click(object sender, EventArgs e)
        {
            // Limpiar carrito y volver a la página de carrito
            Session["Carrito"] = null;
            Response.Redirect("ClienteCarrito.aspx");
        }

        protected void btnPagar_Click(object sender, EventArgs e)
        {
            // Aquí iría tu lógica de integración con pasarela de pago

            // Por ahora simulamos confirmación y vaciamos el carrito:

            //actualizar servicio orden id con ServicioOrdenId --> UPDATE ordenServicio "PAGADO"


            Session["Carrito"] = null;
            //En realidad debe de abrir un modal! tal que... 
        }

        protected void btnVerComprobante_Click(object sender, EventArgs e)
        {
            //var idOrden = Session["UltimaOrdenId"]?.ToString() ?? Request.QueryString["pedidoId"];


            //creas el comprobante y lo subes a la BD 

            //idBD

            int idComprobanteServicio = 2;

            //luego vas a ver el comprobante cread
            Response.Redirect($"ComprobantePago.aspx?id={idComprobanteServicio}");
        }
    }
}