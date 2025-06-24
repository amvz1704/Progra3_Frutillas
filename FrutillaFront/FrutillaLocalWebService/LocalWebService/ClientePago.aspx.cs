using LocalWebService.ComprobanteWS;
using LocalWebService.LocalWS;
using LocalWebService.PedidoWS;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Xml.Linq;

namespace LocalWebService
{
    public partial class ClientePago : System.Web.UI.Page
    {
    
        private ComprobanteWSClient ComprobanteWS;


        private List<ComprobanteWS.lineaOrdenDeVenta> Carrito
            => Session["Carrito"] as List<ComprobanteWS.lineaOrdenDeVenta>;

        private int servicioOrdenId
        {
            get => ViewState["servicioOrdenId"] as int? ?? 0;
            set => ViewState["servicioOrdenId"] = value;
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            ComprobanteWS = new ComprobanteWSClient();
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
                if (id == -1) {
                    Response.Redirect("ClienteCarrito.aspx");
                    return; 
                }

                servicioOrdenId = id;


                // Si no hay carrito o está vacío, volver al carrito    
                if (Carrito == null || !Carrito.Any())
                {
                    Response.Redirect("ClienteCarrito.aspx");
                    return;
                }

                // Seleccionar método de pago por defecto: Tarjeta
                SeleccionarMetodo("Tarjeta");

                CargarTotales();
            }
        }

        private void SeleccionarMetodo(string metodo)
        {
            // Marcar visualmente el botón seleccionado
            btnTarjeta.CssClass = metodo == "Tarjeta" ? "btn btn-success" : "btn btn-outline-secondary";
            btnTransferencia.CssClass = metodo == "Transferencia" ? "btn btn-success" : "btn btn-outline-secondary";
            btnPlin.CssClass = metodo == "Plin" ? "btn btn-success" : "btn btn-outline-secondary";
            btnYape.CssClass = metodo == "Yape" ? "btn btn-success" : "btn btn-outline-secondary";
            // Mostrar u ocultar sección de bancos
            pnlBancos.Visible = metodo == "Transferencia";
            qrPlin.Visible = metodo == "Plin";
            qrYape.Visible = metodo == "Yape";
        }



        private void CargarTotales()
        {
            // Asumimos que cada línea ya tiene Subtotal = Precio * Cantidad
            decimal subtotal = Carrito.Sum(l => (decimal)l.subtotal);
            decimal igv = subtotal * 0.18m;
            decimal total = subtotal + igv;

            //RECIEN SE INSERTA EN LA BD, se crea un pedido y asi --> luego de eso finalmente se confirma con pagar
            txtPedido.Text = "Por editar"; //Recien aqui se crea el pedido y se sube a la BD porque PAGAR en carrito sirve como "confirmar orden"

           

            txtSubtotal.Text = subtotal.ToString();
            txtIGV.Text = igv.ToString();
            txtTotal.Text = total.ToString();
        }

        protected void SeleccionarMetodo_Click(object sender, EventArgs e)
        {
            var btn = (Button)sender;
            string metodo = btn.CommandArgument;
            SeleccionarMetodo(metodo);
        }

        protected void btnCancelar_Click(object sender, EventArgs e)
        {
            // Limpiar carrito y volver a la página de carrito
            Session["Carrito"] = null;
            Response.Redirect("ClienteCarrito.aspx");
        }

        protected void btnVerComprobante_Click(object sender, EventArgs e)
        {
            //          servicioOrdenId
            //int idComprobante = Convert.ToInt32(hfidComprobante.Value); 


            //luego vas a ver el comprobante cread
            Response.Redirect($"ComprobantePago.aspx?id={servicioOrdenId}");
        }

        protected void btnPagar_Click1(object sender, EventArgs e)
        {
            // Aquí iría tu lógica de integración con pasarela de pago
            ComprobanteWS = new ComprobanteWSClient();
            string metodoSeleccionado = btnTarjeta.CssClass.Contains("btn-success") ? "TARJETA_CREDITO" :
                                btnTransferencia.CssClass.Contains("btn-success") ? "Transferencia" :
                                btnPlin.CssClass.Contains("btn-success") ? "PLIN" : "YAPE";

            // Convertir el string al enum formaDePago
            Enum.TryParse(metodoSeleccionado, out ComprobanteWS.formaDePago formadePago);

            comprobanteDTO comprobante = new comprobanteDTO();
            comprobante.formaPago = formadePago;
            comprobante.formaPagoSpecified = true;
            comprobante.montoIGV = (double)(Carrito.Sum(l => (decimal)l.subtotal) * 0.18m); // Asumiendo IGV incluido
                comprobante.numeroArticulos = Carrito.Count;
            comprobante.fechaStr = DateTime.Now.ToString("yyyy-MM-dd");
            comprobante.subtotal = (double)(Carrito.Sum(l => (decimal)l.subtotal));
            comprobante.total = double.Parse(txtTotal.Text); // Asumiendo IGV incluido
            

            // Por ahora simulamos confirmación y vaciamos el carrito:
            
            //actualizar servicio orden id con ServicioOrdenId --> UPDATE ordenServicio "POR_ENTREGAR"

            //vecorrectamente 
            int idComprobanteCreado = ComprobanteWS.agregarComprobante(comprobante); //debo obtener el id de alguna forma! 
            hfidComprobante.Value = idComprobanteCreado.ToString(); 

            PedidoWSClient pedidoWS = new PedidoWSClient(); 
            ordenVentaDTO orden = pedidoWS.obtenerPedidoPorId(servicioOrdenId);

            // 3) Comprueba que no sea null antes de usarlo
            if (orden == null)
            {

                return;
            }

            orden.idComprobante = idComprobanteCreado;
            orden.estado = estadoVenta.POR_ENTREGAR; 

            pedidoWS.actualizarOrden(orden);


            Session["Carrito"] = null;
            //En realidad debe de abrir un modal! tal que... 

            // Si tu modal tiene runat="server"
            ScriptManager.RegisterStartupScript(
                this,
                this.GetType(),
                "ShowModal",
                "var m = new bootstrap.Modal(document.getElementById('successModal')); m.show();",
                true
            );

        }
    }
}