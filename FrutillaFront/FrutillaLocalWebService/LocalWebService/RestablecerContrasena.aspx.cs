using LocalWebService.UsuarioWS;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace LocalWebService
{
    public partial class RestablecerContrasena : System.Web.UI.Page
    {
        protected UsuarioWSClient usuarioService;
        private string codigo;
        private int idUsuario;

        protected void Page_Init(object sender, EventArgs e)
        {
            usuarioService = new UsuarioWSClient();
            codigo = Session["codigoRecuperacion"] as string;
            idUsuario = (Session["idUsuarioRecuperacion"] != null) ? (int)Session["idUsuarioRecuperacion"] : -1;
        }

        protected void Page_Load(object sender, EventArgs e)
        {
                lblEstado.Text = "";
                

                if (string.IsNullOrEmpty(codigo) || idUsuario <= 0)
                {
                    Response.Redirect("Login.aspx");
                    btnRestablecer.Enabled = false;
                    pnlFormulario.Visible = false;
                    return;
                }

                int validacion = usuarioService.validarCodigoRecuperacion(codigo);
                if (validacion != idUsuario)
                {
                    lblEstado.Text = "El código ha expirado o no es válido. Por facor, inicie el proceso de recuperación nuevamente.";
                    lblEstado.CssClass = "text-danger d-block text-center";
                    btnRestablecer.Enabled = false;
                    pnlFormulario.Visible = false;
                    return;
                }

                lblEstado.Text = "Código válido, puede cambiar su contraseña.";
                lblEstado.CssClass = "text-success d-block text-center";
                pnlFormulario.Visible = true;
                btnRestablecer.Enabled = true;
        }


        protected void btnRestablecer_Click(object sender, EventArgs e)
        {
            string nueva = txtNuevaContrasena.Text.Trim();
            string confirmar = txtConfirmarContrasena.Text.Trim();

            lblEstado.Text = "";
            lblEstado.CssClass = "text-danger d-block text-center";

            if (string.IsNullOrEmpty(nueva))
            {
                lblEstado.Text = "Ingrese una contraseña.";
                return;
            }
            if (nueva.Length < 6)
            {
                lblEstado.Text = "La contraseña debe tener al menos 6 caracteres.";
                return;
            }
            if (nueva.Contains(" "))
            {
                lblEstado.Text = "La contraseña no debe contener espacios.";
                return;
            }
            if (!System.Text.RegularExpressions.Regex.IsMatch(nueva, @"^[a-zA-Z0-9]+$"))
            {
                lblEstado.Text = "La contraseña solo puede contener letras y números.";
                return;
            }
            if (!nueva.Equals(confirmar))
            {
                lblEstado.Text = "Las contraseñas no coinciden.";
                return;
            }

            int validacion = usuarioService.validarCodigoRecuperacion(codigo);

            if (validacion != idUsuario)
            {
                lblEstado.Text = "Sesión inválida. Por favor, vuelva a solicitar el codigo.";
                return;
            }

            bool ok = usuarioService.actualizarContrasena(codigo, nueva);

            if (ok)
            {
                lblEstado.CssClass = "text-success d-block text-center";
                lblEstado.Text = "Contraseña actualizada correctamente. Serás redirigido al inicio de sesión.";
                pnlFormulario.Visible = false;
                btnRestablecer.Enabled = false;
                ClientScript.RegisterStartupScript(this.GetType(), "redirect", "setTimeout(function(){ window.location.href = 'Login.aspx'; }, 5000);", true);
            }
            else
            {
                lblEstado.Text = "No se pudo actualizar la contraseña.";
            }
        }

        protected void btnVolver_Click(object sender, EventArgs e)
        {
            Response.Redirect("Login.aspx");
        }
    }
}