using LocalWebService.ClienteWS;
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

            string datos = FormsAuthentication.Decrypt(Request.Cookies[FormsAuthentication.FormsCookieName].Value).UserData;
            string[] partes = datos.Split('|');
            string tipoUsuario = partes[0];
            int idUsuario = int.Parse(partes[1]);
            if (tipoUsuario != "E")
            {
                Response.Redirect("Login.aspx");
                return;
            }

            if (!IsPostBack)
            {
                empleado empleado = empleadoService.obtenerEmpleadoPorId(idUsuario);
                if (empleado == null)
                {
                    lblEstado.Text = "No se encontró la información del usuario.";
                    lblEstado.CssClass = "text-danger";
                    btnEditar.Enabled = false;
                    btnVistaCliente.Visible = false;
                    return;
                }

                Session["empleado"] = empleado;
                Session["tipo"] = empleado.tipo.ToString();
            }

            MostrarDatos((empleado)Session["empleado"]);
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
            empleado empleado = (empleado)Session["empleado"];

            txtNombre.Text = empleado.nombre;
            txtApellidoPaterno.Text = empleado.apellidoPaterno;
            txtApellidoMaterno.Text = empleado.apellidoMaterno;
            txtCorreo.Text = empleado.correoElectronico;
            txtTelefono.Text = empleado.telefono;

            ToggleModoEdicion(true);
        }

        protected void btnCancelar_Click(object sender, EventArgs e)
        {
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

            empleado empleado = (empleado)Session["empleado"];

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
            else if (!correo.Equals(empleado.correoElectronico, StringComparison.OrdinalIgnoreCase) && usuarioService.correoExiste(correo))
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
                empleado.nombre = nombre;
                empleado.apellidoPaterno = apPaterno;
                empleado.apellidoMaterno = apMaterno;
                empleado.correoElectronico = correo;
                empleado.telefono = telefono;

                string date = empleado.fechatContratoSTRING;
                empleado.fechatContratoSTRING = date;

                bool actualizado = empleadoService.actualizarEmpleado(empleado);

                if (actualizado)
                {
                    Session["empleado"] = empleado;

                    MostrarDatos(empleado);

                    lblMensaje.CssClass = "text-success";
                    lblMensaje.Text = "Datos actualizados correctamente.";
                }
                else
                {
                    lblMensaje.CssClass = "text-danger";
                    lblMensaje.Text = "No se pudo actualizar los datos correctamente.";
                }
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

            btnEditar.Visible = btnCerrarSesion.Visible = !editar;
            btnGuardar.Visible = btnCancelar.Visible = editar;
        }

        protected void btnCerrarSesion_Click(object sender, EventArgs e)
        {
            FormsAuthentication.SignOut();

            if (Request.Cookies[FormsAuthentication.FormsCookieName] != null)
            {
                HttpCookie cookie = new HttpCookie(FormsAuthentication.FormsCookieName, "");
                cookie.Expires = DateTime.Now.AddDays(-1);
                Response.Cookies.Add(cookie);
            }

            Session.Clear();
            Session.Abandon();

            Response.Redirect("Login.aspx");
         }
        protected void btnVistaCliente_Click(object sender, EventArgs e)
        {
            string tipo = Session["tipo"].ToString();
            if (tipo.Equals("83")) 
            {
                Response.Redirect("ClienteHome.aspx");
            }
            else
            {
                btnVistaCliente.CssClass = "btn btn-secondary disabled";
            }
        }
    }
}
