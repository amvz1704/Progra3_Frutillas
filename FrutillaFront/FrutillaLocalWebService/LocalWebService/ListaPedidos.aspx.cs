using LocalWebService.EmpleadoWS;
using LocalWebService.NotificionesWS;
using LocalWebService.PedidoWS;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace LocalWebService
{
    public partial class ListaPedidos : System.Web.UI.Page
    {
        private EmpleadoWSClient empleadoWSClient;
        private PedidoWSClient pedidoWSClient;
        private char rol;
        private int idLocal;

        public ListaPedidos()
        {
            empleadoWSClient = new EmpleadoWSClient();
            pedidoWSClient = new PedidoWSClient();
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
                int idUsuario = int.Parse(partes[1]);
                EmpleadoWS.empleado empleado = empleadoWSClient.obtenerEmpleadoPorId(idUsuario);

                if (empleado != null)
                {
                    ushort tipo = empleado.tipo; 
                    rol = (char) tipo;
                    idLocal = empleado.idLocal;
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
                CargarPedidos();
            }
        }

        private void CargarPedidos()
        {
            try
            {
                gvPedidos.DataSource = pedidoWSClient.listarPedidoPorLocal(idLocal);
                gvPedidos.DataBind();
                
            }
            catch (Exception ex)
            {
                lblError.Text = "Error al cargar pedidos" + ex;
            }
        }

        protected void btnPedidos_Empleado_Click(object sender, EventArgs e)
        {

        }

        protected void gvPedidos_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            gvPedidos.PageIndex = e.NewPageIndex;
            CargarPedidos();
        }

        protected void gvPedidos_RowCommand(object sender, GridViewCommandEventArgs e)
        {
            Response.Redirect($"DetallePedido.aspx");
        }
    }
}