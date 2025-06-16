using LocalWebService.EmpleadoWS;
using LocalWebService.UsuarioWS;
using System;
using System.Linq;
using System.Text.RegularExpressions;
using System.Web;
using System.Web.Security;

namespace LocalWebService
{
    public partial class EmpleadoPerfil : System.Web.UI.Page
    {
        protected EmpleadoWSClient empleadoService;
        protected UsuarioWSClient usuarioService;
        private empleado empleadoActual;

        protected void Page_Init(object sender, EventArgs e)
        {
            empleadoService = new EmpleadoWSClient();
            usuarioService = new UsuarioWSClient();
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!User.Identity.IsAuthenticated)
            {
                Response.Redirect("Login.aspx");
                return;
            }

            if (Session["empleado"] == null)
            {
                string datos = FormsAuthentication.Decrypt(Request.Cookies[FormsAuthentication.FormsCookieName].Value).UserData;
                string[] partes = datos.Split('|');
                string tipoUsuario = partes[0];
                int idUsuario = int.Parse(partes[1]);

                if (tipoUsuario != "E")
                {
                    Response.Redirect("Login.aspx");
                    return;
                }

                empleadoActual = empleadoService.obtenerEmpleadoPorId(idUsuario);
                if (empleadoActual == null)
                {
                    lblEstado.Text = "No se encontró la información del usuario.";
                    lblEstado.CssClass = "text-danger";
                    btnEditar.Enabled = false;
                    return;
                }

                Session["empleado"] = empleadoActual;
            }
            else
            {
                empleadoActual = (empleado)Session["empleado"];
            }

            if (!IsPostBack)
            {
                MostrarDatos(empleadoActual);
            }
        }

        private void MostrarDatos(empleado e)
        {
            lblNombre.Text = e.nombre;
            lblApellidoPaterno.Text = e.apellidoPaterno;
            lblApellidoMaterno.Text = e.apellidoMaterno;
            lblCorreo.Text = e.correoElectronico;
            lblTelefono.Text = e.telefono;
            lblUsuarioSistema.Text = e.usuarioSistema;
        }

        protected void btnEditar_Click(object sender, EventArgs e)
        {
            empleadoActual = (empleado)Session["empleado"];

            txtNombre.Text = empleadoActual.nombre;
            txtApellidoPaterno.Text = empleadoActual.apellidoPaterno;
            txtApellidoMaterno.Text = empleadoActual.apellidoMaterno;
            txtCorreo.Text = empleadoActual.correoElectronico;
            txtTelefono.Text = empleadoActual.telefono;

            ToggleModoEdicion(true);
        }

        protected void btnCancelar_Click(object sender, EventArgs e)
        {
            empleadoActual = (empleado)Session["empleado"];
            MostrarDatos(empleadoActual);
            ToggleModoEdicion(false);
            lblMensaje.Text = "";
        }

        protected void btnGuardar_Click(object sender, EventArgs e)
        {
            lblNombreError.Text = "";
            lblApPaternoError.Text = "";
            lblApMaternoError.Text = "";
            lblCorreoError.Text = "";
            lblTelefonoError.Text = "";

            string nombre = txtNombre.Text.Trim();
            string apPaterno = txtApellidoPaterno.Text.Trim();
            string apMaterno = txtApellidoMaterno.Text.Trim();
            string correo = txtCorreo.Text.Trim();
            string telefono = txtTelefono.Text.Trim();


            bool datosvalidos = true;
            if (string.IsNullOrEmpty(nombre))
            {
                datosvalidos = false;
                lblNombreError.Text = "Ingrese su nombre.";
            }
            if (string.IsNullOrEmpty(apPaterno))
            {
                datosvalidos = false;
                lblApPaternoError.Text = "Ingrese su apellido paterno.";
            }
            if (string.IsNullOrEmpty(apMaterno))
            {
                datosvalidos = false;
                lblApMaternoError.Text = "Ingrese su apellido materno.";
            }
            if (!Regex.IsMatch(correo, @"^[^@\s]+@[^@\s]+\.[^@\s]+$"))
            {
                datosvalidos = false;
                lblCorreoError.Text = "Correo electrónico no válido.";
            }
            else if (!correo.Equals(empleadoActual.correoElectronico, StringComparison.OrdinalIgnoreCase) && usuarioService.correoExiste(correo))
            {
                datosvalidos = false;
                lblCorreoError.Text = "El correo ya está en uso por otra cuenta.";
            }
            if (!telefono.All(char.IsDigit) || telefono.Length != 9)
            {
                datosvalidos = false;
                lblTelefonoError.Text = "Teléfono inválido. Debe contener 9 dígitos numéricos.";
            }

            if (!datosvalidos)
            {
                return;
            }

            try
            {
                empleadoActual.nombre = nombre;
                empleadoActual.apellidoPaterno = apPaterno;
                empleadoActual.apellidoMaterno = apMaterno;
                empleadoActual.correoElectronico = correo;
                empleadoActual.telefono = telefono;

                empleadoService.actualizarEmpleado(empleadoActual);
                Session["empleado"] = empleadoActual;

                MostrarDatos(empleadoActual);
                lblMensaje.CssClass = "text-success";
                lblMensaje.Text = "Datos actualizados correctamente.";
                ToggleModoEdicion(false);
            }
            catch (Exception ex)
            {
                lblMensaje.CssClass = "text-danger";
                lblMensaje.Text = "Error: " + ex.Message;
            }
        }

        private void ToggleModoEdicion(bool editar)
        {
            lblNombre.Visible = lblApellidoPaterno.Visible = lblApellidoMaterno.Visible =
            lblCorreo.Visible = lblTelefono.Visible = !editar;

            txtNombre.Visible = txtApellidoPaterno.Visible = txtApellidoMaterno.Visible =
            txtCorreo.Visible = txtTelefono.Visible = editar;

            btnEditar.Visible = !editar;
            btnGuardar.Visible = btnCancelar.Visible = editar;
        }
    }
}
