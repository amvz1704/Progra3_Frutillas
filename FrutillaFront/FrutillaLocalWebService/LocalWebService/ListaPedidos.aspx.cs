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
        private int idEmpleado;

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
                EmpleadoWS.empleadoDTO empleado = empleadoWSClient.obtenerEmpleadoPorId(idUsuario);

                if (empleado != null)
                {
                    ushort tipo = empleado.tipo; 
                    rol = (char) tipo;
                    idLocal = empleado.idLocal;
                    idEmpleado = empleado.idUsuario;
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
                btnPedidos_Empleado.Visible = (rol == 'R');
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
            try
            {
                gvPedidos.DataSource = pedidoWSClient.obtenerPedidosPorEmpleado(idEmpleado);
                gvPedidos.DataBind();
            } catch (Exception ex)
            {
                lblError.Text = "Error al cargar pedido de empleado " + idEmpleado + ": " + ex;
            }
        }

        protected void gvPedidos_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            gvPedidos.PageIndex = e.NewPageIndex;
            CargarPedidos();
        }

        protected void gvPedidos_RowCommand(object sender, GridViewCommandEventArgs e)
        {
            if(e.CommandName == "VerDetalle")
            {
                int pedidoId;
                if(int.TryParse(e.CommandArgument.ToString(), out pedidoId))
                {
                    Response.Redirect("DetallePedido.aspx?id=" + pedidoId);
                }
            }
        }
    }
}