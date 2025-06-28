using LocalWebService.ClienteWS;
using LocalWebService.UsuarioWS;
using System;
using System.Linq;
using System.Text.RegularExpressions;
using System.Web;
using System.Web.Security;

namespace LocalWebService
{
    public partial class ClientePerfil : System.Web.UI.Page
    {
        protected ClienteWSClient clienteService;
        protected UsuarioWSClient usuarioService;
        protected void Page_Init(object sender, EventArgs e)
        {
            clienteService = new ClienteWSClient();
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

            if (tipoUsuario != "C")
            {
                Response.Redirect("Login.aspx");
                return;
            }

            if (!IsPostBack)
            {
                cliente cliente = clienteService.obtenerClientePorId(idUsuario);
                if (cliente == null)
                {
                    lblEstado.Text = "No se encontró la información del usuario.";
                    lblEstado.CssClass = "text-danger";
                    btnEditar.Enabled = false;
                    return;
                }

                Session["cliente"] = cliente;
                
            }

            MostrarDatos((cliente)Session["cliente"]);
        }

        private void MostrarDatos(cliente c)
        {
            lblNombre.Text = c.nombre;
            lblApellidoPaterno.Text = c.apellidoPaterno;
            lblApellidoMaterno.Text = c.apellidoMaterno;
            lblCorreo.Text = c.correoElectronico;
            lblTelefono.Text = c.telefono;
            lblUsuarioSistema.Text = c.usuarioSistema;
        }

        protected void btnEditar_Click(object sender, EventArgs e)
        {
            cliente cliente = (cliente)Session["cliente"];

            txtNombre.Text = cliente.nombre;
            txtApellidoPaterno.Text = cliente.apellidoPaterno;
            txtApellidoMaterno.Text = cliente.apellidoMaterno;
            txtCorreo.Text = cliente.correoElectronico;
            txtTelefono.Text = cliente.telefono;

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

            cliente cliente = (cliente)Session["cliente"];

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
            else if(!correo.Equals(cliente.correoElectronico, StringComparison.OrdinalIgnoreCase) && usuarioService.correoExiste(correo))
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
                lblMensaje.CssClass = "text-danger";
                lblMensaje.Text = "Por favor corrija los errores indicados.";
                return;
            }

            try
            {
                cliente.nombre = nombre;
                cliente.apellidoPaterno = apPaterno;
                cliente.apellidoMaterno = apMaterno;
                cliente.correoElectronico = correo;
                cliente.telefono = telefono;

                bool actualizado = clienteService.actualizarCliente(cliente);

                if (actualizado)
                {
                    Session["cliente"] = cliente;

                    MostrarDatos(cliente);

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

    }
}