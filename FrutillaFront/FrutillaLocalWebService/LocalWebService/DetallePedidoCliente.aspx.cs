using LocalWebService.PedidoWS;
using LocalWebService.LocalWS;
using System;
using System.Web.UI;

namespace LocalWebService
{
    public partial class DetallePedidoCliente : Page
    {
        PedidoWSClient pedidoWS = new PedidoWSClient();
        LocalWSClient localWS = new LocalWSClient();

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                if (Request.QueryString["idPedido"] != null)
                {
                    int idPedido = int.Parse(Request.QueryString["idPedido"]);
                    CargarDatosPedido(idPedido);
                }
                else
                {
                    lblMensaje.Text = "ID de pedido inválido.";
                }
            }
        }

        private void CargarDatosPedido(int idPedido)
        {
            try
            {
                var pedido = pedidoWS.obtenerPedidoPorId(idPedido);

                if (pedido == null)
                {
                    lblMensaje.Text = "Pedido no encontrado.";
                    return;
                }

                lblNumeroPedido.Text = pedido.idOrdenVenta.ToString();
                lblFecha.Text = pedido.fechaStr;
                lblDescripcion.Text = pedido.descripcion;
                lblEstado.Text = pedido.estado.ToString();

                if (pedido.idLocal > 0)
                {
                    var local = localWS.obtenerLocal(pedido.idLocal);
                    lblLocal.Text = (local != null) ? local.nombre : "No disponible";
                }

                var productos = pedidoWS.obtenerDetallePedidoList(idPedido);
                if (productos == null)
                {
                    lblMensaje.Text = "Pedido no encontrado.";
                }
                else
                {
                    gvDetalle.DataSource = productos;
                    gvDetalle.DataBind();

                    double subtotal = 0;
                    foreach (var p in productos)
                        subtotal += p.subtotal;

                    double igv = subtotal * 0.18;
                    double total = subtotal + igv;

                    lblSubtotal.Text = $"S/ {subtotal:F2}";
                    lblIGV.Text = $"S/ {igv:F2}";
                    lblTotal.Text = $"S/ {total:F2}";
                }
            }
            catch (Exception ex)
            {
                lblMensaje.Text = "Error: " + ex.Message;
            }
        }
        protected void btnVerPedidos_Click(object sender, EventArgs e)
        {
            Response.Redirect("ClientePedidos.aspx");
        }
    }
}
