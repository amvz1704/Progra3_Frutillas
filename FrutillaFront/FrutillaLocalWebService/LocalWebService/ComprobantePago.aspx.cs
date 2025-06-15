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
        const int pedidoOrden = 2; // 

        protected void Page_Load(object sender, EventArgs e)
        {
            daoCliente = new ClienteWSClient();
            daoComprobante = new ComprobanteWSClient();
            daoPedidoOrden = new PedidoWSClient();

            if (User.Identity.IsAuthenticated)
            {
                string datos = FormsAuthentication.Decrypt(
                    Request.Cookies[FormsAuthentication.FormsCookieName].Value
                ).UserData;

                string[] partes = datos.Split('|');
                string tipoUsuario = partes[0];
                int idUsuario = int.Parse(partes[1]);

                if (tipoUsuario == "C")
                {
                    cliente cliente = daoCliente.obtenerClientePorId(idUsuario);
                    if (cliente == null)
                    {
                        Response.Redirect("Login.aspx"); // A 
                    }
                    
                }
                else {
                    Response.Redirect("Login.aspx"); //ademas colcoar un mensaje de vista solo acceso por cliente

                }

                

                // Aca pueden hacer uso del obtener por id
            }
            else
            {
                Response.Redirect("Login.aspx");
            }

            
            if (!IsPostBack)
                BindGrid();
        }

        private void BindGrid()
        {
            //funciona pero existe un problema que quiza se demore / no se actualice la informacion y siempre jale 
            //la informacion del producto actual -- casos descontinuados por ejemplo (pro ahora no hago cambios hasta conversarlo con el grupo) 
            //lo calculas del comprobante actual 

            ordenVenta ordenVenta = daoPedidoOrden.obtenerPedidoPorId(pedidoOrden);

            int comprobanteId = ordenVenta.idComprobante;

            comprobantePago comprobante = daoComprobante.obtenerComprobante(comprobanteId);
            lblId.Text = comprobanteId.ToString(); 
            txtFecha.Text = comprobante.fecha.ToString();
            txtNumeroArticulos.Text = comprobante.numeroArticulos.ToString();
            txtMetodoPago.Text = comprobante.formaPago.ToString();
            txtLocal.Text = ordenVenta.idLocal.ToString();
            txtDescripcion.Text = ordenVenta.descripcion.ToString();

            txtSubtotal.Text = comprobante.subtotal.ToString();
            txtIGV.Text = comprobante.montoIGV.ToString();
            txtTotal.Text = comprobante.total.ToString (); 
            gvDetalles.DataSource = daoComprobante.obtenerLineasPorIdComprobante(comprobanteId);
            gvDetalles.DataBind();
        }

        protected void gvComprobante_RowCommand(object sender, GridViewCommandEventArgs e) { 
        
        }

        protected void gvDetalles_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            gvDetalles.PageIndex = e.NewPageIndex;
            BindGrid();
        }

        //crear uno mas para que regrese a listaEmpleados 
    }
}