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

        protected void Page_Init(object sender, EventArgs e)
        {
            usuarioService = new UsuarioWSClient();
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            try
            {
                if (!IsPostBack)
                {
                    string token = Request.QueryString["token"];

                    if (string.IsNullOrEmpty(token))
                    {
                        lblEstado.Text = "Enlace inválido o expirado.";
                        btnRestablecer.Enabled = false;
                        pnlFormulario.Visible = false;
                        return;
                    }

                    int idUsuario = usuarioService.validarTokenRecuperacion(token);

                    if (idUsuario <= 0)
                    {
                        lblEstado.Text = "Enlace inválido o expirado.";
                        btnRestablecer.Enabled = false;
                        pnlFormulario.Visible = false;
                        return;
                    }

                    lblEstado.Text = "Token válido, puede cambiar su contraseña.";
                    lblEstado.CssClass = "text-success d-block text-center";
                    btnRestablecer.Enabled = true;
                    pnlFormulario.Visible = true;

                    ViewState["token"] = token;
                    ViewState["idUsuario"] = idUsuario;
                }
            }
            catch (Exception ex)
            {
                lblEstado.Text = "Error: " + ex.Message;
                lblEstado.CssClass = "text-danger d-block text-center";
                btnRestablecer.Enabled = false;
                pnlFormulario.Visible = false;
            }
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

            string token = ViewState["token"] as string;

            if (string.IsNullOrEmpty(token))
            {
                lblEstado.Text = "Token inválido. Por favor, vuelva a solicitar la recuperación de contraseña.";
                return;
            }

            bool ok = usuarioService.actualizarContrasena(token, nueva);

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
    }
}