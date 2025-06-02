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
            string usuario = txtUsuario.Text;
            string password = txtPassword.Text;

            UsuarioWS.persona persona = usuarioService.validarUsuario(usuario, password);
            if (persona != null)
            {

                FormsAuthenticationTicket tkt;
                string cookiestr;
                HttpCookie ck;
                tkt = new FormsAuthenticationTicket(1, usuario, DateTime.Now,
                DateTime.Now.AddMinutes(30), true, "datos adicionales del usuario");
                cookiestr = FormsAuthentication.Encrypt(tkt);
                ck = new HttpCookie(FormsAuthentication.FormsCookieName, cookiestr);
                ck.Expires = tkt.Expiration;
                ck.Path = FormsAuthentication.FormsCookiePath;
                Response.Cookies.Add(ck);

            }
            else
            {
                lblMensaje.Text = "Usuario o contraseña incorrectos.";
                Response.Redirect("Login.aspx", true);
            }
        }
    }
}