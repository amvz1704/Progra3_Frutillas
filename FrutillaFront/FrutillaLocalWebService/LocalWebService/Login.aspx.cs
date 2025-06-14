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
    public partial class Login : System.Web.UI.Page
    {
        protected UsuarioWSClient usuarioService;
        protected void Page_Init(object sender, EventArgs e)
        {
            usuarioService = new UsuarioWSClient();
        }
        protected void Page_Load(object sender, EventArgs e)
        {

        }
        protected void btnLogin_Click(object sender, EventArgs e)
        {
            string usuario = txtUsuario.Text.Trim();
            string password = txtPassword.Text.Trim();

            persona persona = usuarioService.validarUsuario(usuario, password);
            if (persona != null)
            {

                string tipo = persona.tipoUsuario;
                int id = persona.idUsuario;

                FormsAuthenticationTicket tkt;
                string cookiestr;
                HttpCookie ck;
                tkt = new FormsAuthenticationTicket(1, usuario, DateTime.Now,
                DateTime.Now.AddMinutes(30), true, tipo + "|" + id);
                cookiestr = FormsAuthentication.Encrypt(tkt);
                ck = new HttpCookie(FormsAuthentication.FormsCookieName, cookiestr);
                ck.Expires = tkt.Expiration;
                ck.Path = FormsAuthentication.FormsCookiePath;
                Response.Cookies.Add(ck);

                string redirectUrl = Request["ReturnUrl"];

                if (redirectUrl == null)
                {
                    if (tipo.Equals("E")) redirectUrl = "LocalSupervisor.aspx";
                    else redirectUrl = "ClienteHome.aspx";
                }

                Response.Redirect(redirectUrl, true);

            }
            else
            {
                lblMensaje.Text = "Usuario o contraseña incorrectos.";
            }
        }

        protected void btnRegister_Click(object sender, EventArgs e)
        {

        }

        protected void btnIrRegistro_Click(object sender, EventArgs e)
        {

        }

        protected void btnIrRegistro_Click1(object sender, EventArgs e)
        {

        }
    }
}