using LocalWebService.EmpleadoWS; // Asegúrate que este namespace sea correcto
using LocalWebService.LocalWS;
using System;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace LocalWebService
{
    public partial class Monitoreo : Page
    {
        private EmpleadoWSClient empleadoWSClient;
        private int idLocal;

        public Monitoreo()
        {
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
                txtId.Text = idLocal.ToString();
                CargarEmpleados();
                ddlEmpleadoAsignado.Enabled = false; // Deshabilitar inicialmente
            }
        }
        protected void btnGenerar_Click(object sender, EventArgs e)
        {

            string tipo = ddlTipoReporte.SelectedValue;
            int id = int.Parse(txtId.Text);


            try
            {
                // Usa la clase generada por la referencia del servicio
                var cliente = new EmpleadoWSClient();

                byte[] pdfBytes;

                if (tipo == "local")
                {
                    pdfBytes = cliente.VentasXLocal(id);
                }
                else
                {
                    id = int.Parse(ddlEmpleadoAsignado.SelectedValue);
                    pdfBytes = cliente.VentasXEmpleado(id);
                }
                // Enviar PDF al navegador
                string nombreArchivo = $"Reporte_{tipo}_{id}.pdf";
                Response.Clear();
                Response.Buffer = true;
                Response.ContentType = "application/pdf";
                Response.AddHeader("Content-Disposition", $"attachment; filename=Reporte_{tipo}_{id}.pdf");
                Response.BinaryWrite(pdfBytes);
                Response.Flush();
                Response.End();
            }
            catch (System.Exception ex)
            {
                Response.Write($"<h3 style='color:red;'>Error al generar el reporte: {ex.Message}</h3>");
            }
        }

        protected void ddlTipoReporte_SelectedIndexChanged(object sender, EventArgs e)
        {
            string tipo = ddlTipoReporte.SelectedValue;
            if (tipo == "empleado")
            {
                ddlEmpleadoAsignado.Enabled = true;
            }
            else
            {
                ddlEmpleadoAsignado.Enabled = false;
            }

        }
        private void CargarEmpleados()
        {
            EmpleadoWSClient empleadoClient = new EmpleadoWSClient();

            try
            {
                int idLocal = int.Parse(txtId.Text);
                var empleados = empleadoClient.obtenerEmpleadosxLocal(idLocal);
                ddlEmpleadoAsignado.Items.Clear();
                ddlEmpleadoAsignado.Items.Add(new ListItem("Seleccione un empleado", ""));

                foreach (empleadoDTO emp in empleados)
                {
                    string nombreCompleto = $"{emp.nombre} {emp.apellidoPaterno} {emp.apellidoMaterno}";
                    ddlEmpleadoAsignado.Items.Add(new ListItem(nombreCompleto, emp.idUsuario.ToString()));
                }
            }
            catch (System.Exception ex)
            {
                lblMensaje.Text = "Error al cargar empleados: " + ex.Message;
            }
            finally
            {
                empleadoClient.Close();
            }
        }
    }
}