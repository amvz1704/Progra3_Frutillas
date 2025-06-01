using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using LocalWebService.NotificionesWS;

namespace LocalWebService.Notificaciones
{
    public partial class ListarNotificaciones : System.Web.UI.Page
    {
        private NotificacionWSClient notificacionWSClient;

        public ListarNotificaciones()
        {
            notificacionWSClient = new NotificacionWSClient();
        }
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                CargarNotificaciones();
            }
        }

        private void CargarNotificaciones()
        {
            try
            {
                gvNotificaciones.DataSource = notificacionWSClient.listarTodos().ToList();
                gvNotificaciones.DataBind();
            } catch (Exception ex)
            {
                lblError.Text = "Error al cargar notificaciones";
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
                gvNotificaciones.DataSource = notificacionWSClient.listarPorFecha(selectedDate.ToShortDateString(),1);
                gvNotificaciones.DataBind();
            }
        }

        protected void gvNotificaciones_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {

        }
    }
}