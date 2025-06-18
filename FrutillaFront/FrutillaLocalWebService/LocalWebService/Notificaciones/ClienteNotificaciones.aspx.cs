using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Permissions;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;
using LocalWebService.ClienteWS;
using LocalWebService.EmpleadoWS;
using LocalWebService.NotificionesWS;

namespace LocalWebService.Notificaciones
{
    public partial class ClienteNotificaciones : System.Web.UI.Page
    {
        private NotificacionWSClient notificacionWSClient;
        private ClienteWSClient clienteWSClient;
        private int idCliente;

        public ClienteNotificaciones()
        {
            notificacionWSClient = new NotificacionWSClient();
            clienteWSClient = new ClienteWSClient();
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
                cliente cliente = clienteWSClient.obtenerClientePorId(idUsuario);

                if (cliente != null)
                {
                    idCliente = cliente.idUsuario;
                }
                else
                {
                    Response.Redirect("Login.aspx");
                }

                // Aca pueden hacer uso del obtener por id
            }
            else
            {
                Response.Redirect("Login.aspx");
            }

            if (!IsPostBack)
            {
                CargarNotificaciones();
            }
        }

        private void CargarNotificaciones()
        {
            try
            {
                gvNotificaciones.DataSource = notificacionWSClient.listarPorCliente(idCliente); // Cambiar por id de Session
                gvNotificaciones.DataBind();

            } catch (Exception e)
            {
                lblError.Text = "Error al cargar las notificaciones: " + e.Message;
                lblError.Visible = true;
                gvNotificaciones.Visible = false;
                return;
            }
        }

        protected void gvNotificaciones_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            gvNotificaciones.PageIndex = e.NewPageIndex;
            CargarNotificaciones();
        }
    }
}