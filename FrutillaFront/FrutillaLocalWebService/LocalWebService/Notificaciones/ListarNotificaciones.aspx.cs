using LocalWebService.EmpleadoWS;
using LocalWebService.LocalWS;
using LocalWebService.NotificionesWS;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace LocalWebService.Notificaciones
{
    public partial class ListarNotificaciones : System.Web.UI.Page
    {
        private NotificacionWSClient notificacionWSClient;
        private EmpleadoWSClient empleadoWSClient;
        private int idSupervisor;

        public ListarNotificaciones()
        {
            notificacionWSClient = new NotificacionWSClient();
            empleadoWSClient = new EmpleadoWSClient();
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
                    idSupervisor = empleado.idUsuario;
                    gvNotificaciones.Visible = (empleado.tipo == 'S'); // Verifica si es supervisor
                    lblError.Visible = gvNotificaciones.Visible;
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
                gvNotificaciones.DataSource = notificacionWSClient.listarPorSupervisor(idSupervisor);// Cambiar por id de Session
                gvNotificaciones.DataBind();

            } catch(System.Exception e)
            {
                lblError.Text = "Error al cargar las notificaciones: " + e.Message;
                lblError.Visible = true;
                gvNotificaciones.Visible = false;
                return;
            }
        }

        protected void btnSubmitDate_Click(object sender, EventArgs e)
        {
            if (string.IsNullOrEmpty(datePicker.Value))
            {
                CargarNotificaciones();
            }
            else
            {
                DateTime selectedDate = DateTime.Parse(datePicker.Value);
                string fechaFormateada = selectedDate.ToString("yyyy-MM-dd");
                gvNotificaciones.DataSource = notificacionWSClient.listarPorFecha(fechaFormateada, idSupervisor); //cambiar por id de Session
                gvNotificaciones.DataBind();
            }
        }

        protected void gvNotificaciones_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            gvNotificaciones.PageIndex = e.NewPageIndex;
            CargarNotificaciones();
        }

    }
}