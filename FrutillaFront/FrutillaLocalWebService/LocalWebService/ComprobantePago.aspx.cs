using LocalWebService.ClienteWS;
using LocalWebService.ComprobanteWS; 
using LocalWebService.EmpleadoWS;
using LocalWebService.LocalWS;
using LocalWebService.PedidoWS;
using LocalWebService.UsuarioWS;
using System;
using System.Collections.Generic;
using System.EnterpriseServices;
using System.Linq;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace LocalWebService
{
    public partial class ComprobantePago : System.Web.UI.Page
    {
        //sacar las lineas de venta relacionadas con un comprobante
        private ComprobanteWSClient daoComprobante;
        private ClienteWSClient daoCliente;
        private PedidoWSClient daoPedidoOrden;
        private LocalWSClient daoLocal;
        private int idComprobanteServicio
        {
            get => ViewState["idComprobanteServicio"] as int? ?? 0;
            set => ViewState["idComprobanteServicio"] = value;
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            daoCliente = new ClienteWSClient();
            daoComprobante = new ComprobanteWSClient();
            daoPedidoOrden = new PedidoWSClient();
            daoLocal = new LocalWSClient();

            // 1) Obtener el valor de la URL: ClientePago.aspx?id=123
            string sId = Request.QueryString["id"];
            if (!int.TryParse(sId, out int id))
            {
                // Parámetro inválido; podrías redirigir o mostrar error
                Response.Redirect("ClienteCarrito.aspx"); //no se hgenero un comprobante 
                return;
            }

            // 2) Guardarlo si luego lo vas a reutilizar
            idComprobanteServicio = id;


            if (!IsPostBack)
                BindGrid();
        }

        private void BindGrid()
        {
            //funciona pero existe un problema que quiza se demore / no se actualice la informacion y siempre jale 
            //la informacion del producto actual -- casos descontinuados por ejemplo (pro ahora no hago cambios hasta conversarlo con el grupo) 
            //lo calculas del comprobante actual 

            ordenVentaDTO ordenVenta = daoPedidoOrden.obtenerPedidoPorId(idComprobanteServicio);

            int comprobanteId = ordenVenta.idComprobante;

            comprobantePago comprobante = daoComprobante.obtenerComprobante(comprobanteId);
            lblId.Text = comprobanteId.ToString();


            txtFecha.Text = "2222-11-11"; //editar luego 
            
            txtNumeroArticulos.Text = comprobante.numeroArticulos.ToString();
            txtMetodoPago.Text = comprobante.formaPago.ToString();

            local copia = daoLocal.obtenerLocal(ordenVenta.idLocal);

            txtLocal.Text = copia.nombre; //editar y
            txtDescripcion.Text = ordenVenta.descripcion.ToString();

            txtSubtotal.Text = comprobante.subtotal.ToString();
            txtIGV.Text = comprobante.montoIGV.ToString();
            txtTotal.Text = comprobante.total.ToString (); 
            gvDetalles.DataSource = daoComprobante.obtenerLineasPorIdComprobante(comprobanteId);
            gvDetalles.DataBind();
        }

        protected void btnRegresar_Click(object sender, EventArgs e) {
            // Si quieres volver a la página de carrito:
            Response.Redirect("ClientePedidos.aspx");
        }

        protected void gvDetalles_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            gvDetalles.PageIndex = e.NewPageIndex;
            BindGrid();
        }

        //crear uno mas para que regrese a listaEmpleados 
    }
}