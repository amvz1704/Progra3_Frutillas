
using LocalWebService.LocalWS;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;


namespace LocalWebService
{
    public partial class LocalSupervisor : System.Web.UI.Page
    {
        const int LOCAL_ID = 2; //esto dependera del id del empleado --> cambiar con LOGIN
        private LocalWSClient daoLocal;
        private String localNombre;
        private String localDescripcion;
        private String localTelefono;
        private String localDireccion;

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                CargarDatosLocal();
            }
        }

        private void CargarDatosLocal()
        {
            var client = new LocalWSClient();
            local local = client.obtenerLocal(LOCAL_ID);

            if (local != null)
            {
                // Asigna valores a cada control
                localNombre = local.nombre;
                localDireccion = local.direccion;
                localDescripcion = local.descripcion;
                localTelefono = local.telefono;

            }

            lblNombre.Text = localNombre;
            lblDireccion.Text = localDireccion;
            lblDescripcion.Text = localDescripcion;
            lblTelefono.Text = localTelefono;
        }

        protected void btnEditarLocal_Click(object sender, EventArgs e)
        {
            // 1. Cargar datos del local en los controles del modal:

            var client = new LocalWSClient();
            local local = client.obtenerLocal(LOCAL_ID);

            txtNombreLocal.Text = local.nombre;
            txtDireccionLocal.Text = local.direccion;
            txtTelefonoLocal.Text = local.telefono;
            txtDescripcionLocal.Text = local.descripcion;
            ddlEstadoLocal.SelectedValue = "true";


            // 2. Inyectar JavaScript para abrir el modal por su ClientID
            string script = "var myModal = new bootstrap.Modal(document.getElementById('"
                            + pnlModalLocal.ClientID
                            + "'), { keyboard: false });"
                            + "myModal.show();";
            ScriptManager.RegisterStartupScript(this, this.GetType(), "ShowModalLocal", script, true);
        }

        protected void btnGuardarLocalModal_Click(object sender, EventArgs e)
        {
            string nombre = txtNombreLocal.Text.Trim();
            string direccion = txtDireccionLocal.Text.Trim();
            string telefono = txtTelefonoLocal.Text.Trim();
            string descripcion = txtDescripcionLocal.Text.Trim();
            bool activo = ddlEstadoLocal.SelectedValue == "true";

            local localNuevo = new LocalWS.local
            {
                idLocal = LOCAL_ID,
                nombre = nombre,
                direccion = direccion,
                telefono = telefono,
                descripcion = descripcion,
                activo = activo

            };

            try
            {
                // Llamar al servicio y obtener la respuesta boolean
                var client = new LocalWSClient();
                bool ok = client.actualizarLoc(localNuevo); //FALSE xd
                client.Close();

                if (!ok)
                {
                    // El booleano vino como “false”: hubo un fallo interno en el servicio
                    lblError.Text = "El servicio NO pudo actualizar el Local. Revise los datos.";
                    lblError.CssClass = "text-danger";
                    return;
                }

                // Éxito: recargar la UI
                CargarDatosLocal();  // o el método que refresque la lista/tarjeta de locales

                // Cerrar el modal con JavaScript
                string scriptHide = @"
                    var modal = bootstrap.Modal.getInstance(
                        document.getElementById('" + pnlModalLocal.ClientID + @"'));
                    if(modal) modal.hide();";
                ScriptManager.RegisterStartupScript(this, this.GetType(), "HideModal", scriptHide, true);
            }
            catch (Exception ex)
            {
                // Aquí atrapamos CommunicationException o FaultException si algo falla
                lblError.Text = "Error al comunicarse con el servicio: " + ex.Message;
                lblError.CssClass = "text-danger";
            }
        }

        protected void BtnAdminEmpleados(object sender, EventArgs e)
        {

            Response.Redirect($"ListaEmpleadosSupervisor.aspx?id={LOCAL_ID}");
        }

        protected void BtnAdminProductos(object sender, EventArgs e)
        {

            Response.Redirect($"ListaProductosSupervisor.aspx?id={LOCAL_ID}");
        }

        protected void BtnAdminVentas(object sender, EventArgs e)
        {

            Response.Redirect($"ListaPedidos.aspx");
        }
    }
}