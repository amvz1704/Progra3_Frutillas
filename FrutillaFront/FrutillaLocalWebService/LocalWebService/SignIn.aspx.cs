using LocalWebService.ClienteWS;
using LocalWebService.UsuarioWS;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace LocalWebService
{
    public partial class SignIn : System.Web.UI.Page
    {
        protected UsuarioWSClient usuarioService;
        protected ClienteWSClient clienteService;
        protected void Page_Init(object sender, EventArgs e)
        {
            usuarioService = new UsuarioWSClient();
            clienteService = new ClienteWSClient();
        }
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void btnSignIn_Click(object sender, EventArgs e)
        {

            lblNombre.Text = "";
            lblApPaterno.Text = "";
            lblApMaterno.Text = "";
            lblUsuario.Text = "";
            lblPassword.Text = "";
            lblCorreo.Text = "";
            lblTelefono.Text = "";

            string nombre = txtNombre.Text.Trim();
            string apPaterno = txtApPaterno.Text.Trim();
            string apMaterno = txtApMaterno.Text.Trim();
            string usuario = txtUsuario.Text.Trim();
            string password = txtPassword.Text;
            string correo = txtCorreo.Text.Trim();
            string telefono = txtTelefono.Text.Trim();

            bool esValido = true;

            if (string.IsNullOrEmpty(nombre)) { lblNombre.Text = "Ingrese su nombre"; esValido = false; }
            else if (nombre.Length > 70)
            {
                lblCorreo.Text = "El nombre no debe tener más de 70 caracteres.";
                esValido = false;
            }

            if (string.IsNullOrEmpty(apPaterno)) { lblApPaterno.Text = "Ingrese su apellido paterno"; esValido = false; }
            else if (apPaterno.Length > 45)
            {
                lblCorreo.Text = "El apellido paterno no debe tener más de 45 caracteres.";
                esValido = false;
            }

            if (string.IsNullOrEmpty(apMaterno)) { lblApMaterno.Text = "Ingrese su apellido materno"; esValido = false; }
            else if (apMaterno.Length > 45)
            {
                lblCorreo.Text = "El apellido materno no debe tener más de 45 caracteres.";
                esValido = false;
            }

            if (string.IsNullOrEmpty(usuario))
            {
                lblUsuario.Text = "Ingrese un nombre de usuario";
                esValido = false;
            }
            else if (!System.Text.RegularExpressions.Regex.IsMatch(usuario, @"^[a-zA-Z0-9_]+$"))
            {
                lblUsuario.Text = "Solo se permite una palabra sin espacios, letras, números o guion bajo.";
                esValido = false;
            }
            else if (usuario.Length > 45)
            {
                lblUsuario.Text = "El nombre de usuario no debe tener más de 45 caracteres.";
                esValido = false;
            }

            if (string.IsNullOrEmpty(password)) 
            { 
                lblPassword.Text = "Ingrese una contraseña"; 
                esValido = false; 
            }
            else if (password.Length < 6)
            {
                lblPassword.Text = "La contraseña debe tener al menos 6 caracteres.";
                esValido = false;
            }
            else if (password.Length > 15)
            {
                lblPassword.Text = "La contraseña no debe tener más de 15 caracteres.";
                esValido = false;
            }
            else if (password.Contains(" "))
            {
                lblPassword.Text = "La contraseña no debe contener espacios.";
                esValido = false;
            }
            else if (!System.Text.RegularExpressions.Regex.IsMatch(password, @"^[a-zA-Z0-9]+$"))
            {
                lblPassword.Text = "Solo se permiten letras y números.";
                esValido = false;
            }


            if (string.IsNullOrEmpty(correo))
            {
                lblCorreo.Text = "Ingrese un correo electrónico";
                esValido = false;
            }
            if (correo.Length > 80)
            {
                lblCorreo.Text = "El correo no debe tener más de 80 caracteres.";
                esValido = false;
            }
            else if (!System.Text.RegularExpressions.Regex.IsMatch(correo, @"^[^@\s]+@[^@\s]+\.[^@\s]+$"))
            {
                lblCorreo.Text = "Correo electrónico no válido";
                esValido = false;
            }
            if (usuarioService.correoExiste(correo))
            {
                lblCorreo.Text = "Este correo ya está asociado a una cuenta.";
                esValido = false;
            }

            if (string.IsNullOrEmpty(telefono))
            {
                lblTelefono.Text = "Ingrese un teléfono";
                esValido = false;
            }
            else if (!telefono.All(char.IsDigit) || telefono.Length != 9)
            {
                lblTelefono.Text = "Teléfono inválido. Debe contener 9 dígitos numéricos.";
                esValido = false;
            }

            if (!esValido) return;

            int id = usuarioService.obtenerIDPorNombreUsuario(usuario);
            if (id > 0)
            {
                lblUsuario.Text = "Este nombre de usuario ya está en uso.";
                return;
            }

            cliente cliente = new cliente();
            cliente.nombre = nombre;
            cliente.apellidoPaterno = apPaterno;
            cliente.apellidoMaterno = apMaterno;
            cliente.correoElectronico = correo;
            cliente.telefono = telefono;
            cliente.usuarioSistema = usuario;
            cliente.contraSistema = password;

            clienteService.agregarCliente(cliente);

            int idCliente = usuarioService.obtenerIDPorNombreUsuario(usuario);
            string tipo = cliente.tipoUsuario;

            if(idCliente > 0)
            {
                FormsAuthenticationTicket tkt;
                string cookiestr;
                HttpCookie ck;
                tkt = new FormsAuthenticationTicket(1, usuario, DateTime.Now, DateTime.Now.AddMinutes(30), true, tipo + "|" + idCliente);
                cookiestr = FormsAuthentication.Encrypt(tkt);
                ck = new HttpCookie(FormsAuthentication.FormsCookieName, cookiestr);
                ck.Expires = tkt.Expiration;
                ck.Path = FormsAuthentication.FormsCookiePath;
                Response.Cookies.Add(ck);

                Response.Redirect("ClienteHome.aspx", true);
            }
            else
            {
                lblRegistro.Text = "Registro Fallido.";
            }
        }
    }
}