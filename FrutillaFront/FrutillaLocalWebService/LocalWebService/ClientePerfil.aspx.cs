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
        private cliente clienteActual;
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

            if (Session["cliente"] == null)
            {
                string datos = FormsAuthentication.Decrypt(Request.Cookies[FormsAuthentication.FormsCookieName].Value).UserData;
                string[] partes = datos.Split('|');
                string tipoUsuario = partes[0];
                int idUsuario = int.Parse(partes[1]);

                if (tipoUsuario != "C")
                {
                    Response.Redirect("Login.aspx");
                    return;
                }

                clienteActual = clienteService.obtenerClientePorId(idUsuario);
                if (clienteActual == null)
                {
                    lblEstado.Text = "No se encontró la información del usuario.";
                    lblEstado.CssClass = "text-danger";
                    btnEditar.Enabled = false;
                    return;
                }

                Session["cliente"] = clienteActual;
            }
            else
            {
                clienteActual = (cliente)Session["cliente"];
            }

            if (!IsPostBack)
            {
                MostrarDatos(clienteActual);
            }
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
            clienteActual = (cliente)Session["cliente"];

            txtNombre.Text = clienteActual.nombre;
            txtApellidoPaterno.Text = clienteActual.apellidoPaterno;
            txtApellidoMaterno.Text = clienteActual.apellidoMaterno;
            txtCorreo.Text = clienteActual.correoElectronico;
            txtTelefono.Text = clienteActual.telefono;

            ToggleModoEdicion(true);
        }

        protected void btnCancelar_Click(object sender, EventArgs e)
        {
            clienteActual = (cliente)Session["cliente"];
            MostrarDatos(clienteActual);
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
            else if(!correo.Equals(clienteActual.correoElectronico, StringComparison.OrdinalIgnoreCase) && usuarioService.correoExiste(correo))
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
                lblMensaje.Text = "Datos actualizados correctamente.";
                return;
            }

            try
            {
                clienteActual.nombre = nombre;
                clienteActual.apellidoPaterno = apPaterno;
                clienteActual.apellidoMaterno = apMaterno;
                clienteActual.correoElectronico = correo;
                clienteActual.telefono = telefono;

                clienteService.actualizarCliente(clienteActual);
                Session["cliente"] = clienteActual;
                MostrarDatos(clienteActual);

                lblMensaje.CssClass = "text-success";
                lblMensaje.Text = "Datos actualizados correctamente.";
                ToggleModoEdicion(false);
            }
            catch (Exception ex)
            {
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