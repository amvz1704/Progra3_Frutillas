using LocalWebService.UsuarioWS;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Mail;
using System.Text.RegularExpressions;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace LocalWebService
{
    public partial class RecuperarCuenta : System.Web.UI.Page
    {
        protected UsuarioWSClient usuarioService;

        protected void Page_Init(object sender, EventArgs e)
        {
            usuarioService = new UsuarioWSClient();
        }

        protected void btnEnviar_Click(object sender, EventArgs e)
        {
            string correo = txtCorreo.Text.Trim();
            lblMensaje.Text = "";
            lblMensaje.CssClass = "text-danger mb-3 d-block text-center";

            if (string.IsNullOrEmpty(correo))
            {
                lblMensaje.Text = "Ingrese un correo electrónico.";
                return;
            }
            if (!Regex.IsMatch(correo, @"^[^@\s]+@[^@\s]+\.[^@\s]+$"))
            {
                lblMensaje.Text = "Correo electrónico no válido.";
                return;
            }

            try
            {
                persona persona = usuarioService.obtenerPorCorreo(correo);
                if (persona == null || persona.idUsuario == 0)
                {
                    lblMensaje.Text = "El correo no está asociado a ninguna cuenta.";
                    return;
                }

                int idUsuario = persona.idUsuario;
                string codigo = usuarioService.generarCodigoRecuperacion(idUsuario);

                string asunto = "Recuperación de contraseña - Frutilla";
                string mensaje = $@"<p>Hola, hemos recibido una solicitud para recuperar tu contraseña.</p>
                                <p>Tu código de verificación es:</p>
                                <h2 style='color:#070426'>{codigo}</h2>
                                <p>Ingresa este código en la página para continuar con la recuperación de tu cuenta.</p>";

                EnviarCorreoMailtrap(correo, asunto, mensaje);

                Response.Redirect("VerificarCodigo.aspx");
            }
            catch (Exception ex)
            {
                lblMensaje.CssClass = "text-danger mb-3 d-block text-center";
                lblMensaje.Text = "Error: " + ex.Message;
            }
        }

        private void EnviarCorreoMailtrap(string destinatario, string asunto, string contenidoHtml)
        {
            var client = new SmtpClient("sandbox.smtp.mailtrap.io", 2525)
            {
                Credentials = new NetworkCredential("33079d9358c54f", "6bc4f5f08baad6"),
                EnableSsl = true
            };

            MailMessage mail = new MailMessage("no-reply@frutilla.com", destinatario, asunto, contenidoHtml);
            mail.IsBodyHtml = true;

            client.Send(mail);
        }
        protected void btnVolverLogin_Click(object sender, EventArgs e)
        {
            Response.Redirect("Login.aspx");
        }

    }
}