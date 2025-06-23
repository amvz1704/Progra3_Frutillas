
using LocalWebService.ClienteWS;
using LocalWebService.EmpleadoWS;
using LocalWebService.LocalWS;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;


namespace LocalWebService
{
    public partial class LocalSupervisor : System.Web.UI.Page
    {
        private int LOCAL_ID; //esto dependera del id del empleado --> cambiar con LOGIN
        private local localActual;
        private LocalWSClient localWSClient;

        public LocalSupervisor()
        {
            localWSClient = new LocalWSClient();
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
                EmpleadoWSClient empleadoWSClient = new EmpleadoWSClient(); 
                EmpleadoWS.empleado empleado = empleadoWSClient.obtenerEmpleadoPorId(idUsuario);
                if (empleado != null)
                {
                    LOCAL_ID = empleado.idLocal;
                }
                else
                {
                    Response.Redirect("Login.aspx"); //regresa a login si no existe el empleado
                }

            }
            else
            {
                Response.Redirect("Login.aspx"); //regresa a login si no se autentica
            }

            if (!IsPostBack)
            {
                localActual = localWSClient.obtenerLocal(LOCAL_ID); // el LOCAL_ID dependerá del usuario que ingrese!
                // Asigna valores a cada control
                lblNombre.Text = localActual.nombre;
                lblDireccion.Text = localActual.direccion;
                lblDescripcion.Text = localActual.descripcion;
                lblTelefono.Text = localActual.telefono;
                //falta agregar la imagen !!


                btnEditar.CommandArgument = LOCAL_ID.ToString(); //para editar la informacion del local actual 
                                
            }
        }

        private void CargarDatosLocal()
        {
            localActual = localWSClient.obtenerLocal(LOCAL_ID); // cambiar a idLocal

            // Y aquí asignas el CommandArgument con ese ID
            

            if (localActual != null)
            {
                // Asigna valores a cada control
                lblNombre.Text = localActual.nombre; ;
                lblDireccion.Text = localActual.direccion;
                lblDescripcion.Text = localActual.descripcion;
                lblTelefono.Text = localActual.telefono;


            }

            
        }

        protected void btnEditarLocal_Click(object sender, EventArgs e)
        {
            var btn = (LinkButton)sender;
            int idLocal = int.Parse(btn.CommandArgument);
            hfLocalId.Value = idLocal.ToString();

            local localNuevo = localWSClient.obtenerLocal(idLocal);
            if (localNuevo != null)
                txtNombreLocal.Text = localNuevo.nombre;
                txtTelefonoLocal.Text = localNuevo.telefono;
                txtDireccionLocal.Text = localNuevo.direccion;
                txtDescripcionLocal.Text = localNuevo.descripcion;
                ddlEstadoLocal.SelectedValue = "true";

            // 2) Desde el servidor le decimos al cliente que abra el modal
            string showModal = $@"
          var modal = new bootstrap.Modal(
            document.getElementById('{pnlModalLocal.ClientID}'));
          modal.show();";

            ScriptManager.RegisterStartupScript(
              this,
              this.GetType(),
              "ShowEditModal",
              showModal,
              true);
        }

        protected void btnGuardarLocalModal_Click(object sender, EventArgs e)
        {
            if (!int.TryParse(hfLocalId.Value, out var idLocal) || idLocal == 0)
            {
                lblError.Text = "ID inválido.";
                return;
            }

            localWSClient.actualizarLocalVarios(idLocal, txtNombreLocal.Text.Trim(), txtTelefonoLocal.Text.Trim(), txtDescripcionLocal.Text.Trim(),
                txtDireccionLocal.Text.Trim());

            CargarDatosLocal();

            // Cerrar el modal con JavaScript
            string scriptHide = @"
                    var modal = bootstrap.Modal.getInstance(
                        document.getElementById('" + pnlModalLocal.ClientID + @"'));
                    if(modal) modal.hide();";
            ScriptManager.RegisterStartupScript(this, this.GetType(), "HideModal", scriptHide, true);

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