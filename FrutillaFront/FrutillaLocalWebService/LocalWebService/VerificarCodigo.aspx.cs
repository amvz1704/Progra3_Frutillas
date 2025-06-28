using LocalWebService.ClienteWS;
using LocalWebService.UsuarioWS;
using System;
using System.Web.UI;

namespace LocalWebService
{
    public partial class VerificarCodigo : System.Web.UI.Page
    {
        protected UsuarioWSClient usuarioService;
        protected void Page_Init(object sender, EventArgs e)
        {
            
            usuarioService = new UsuarioWSClient();
        }
        protected void Page_Load(object sender, EventArgs e)
        {
        }

        protected void btnVerificar_Click(object sender, EventArgs e)
        {
            string codigoIngresado = txtCodigo.Text.Trim();

            if (string.IsNullOrEmpty(codigoIngresado))
            {
                lblMensaje.Text = "Por favor, ingrese el código de verificación.";
                return;
            }
            
            int idUsuarioRecuperacion = usuarioService.validarCodigoRecuperacion(codigoIngresado);

            if(idUsuarioRecuperacion > 0)
            {
                Session["codigoRecuperacion"] = codigoIngresado;
                Session["idUsuarioRecuperacion"] = idUsuarioRecuperacion;
                Response.Redirect("RestablecerContrasena.aspx");
            }
            else
            {
                lblMensaje.Text = "El código ingresado no es válido.";
            }
        }

        protected void btnVolver_Click(object sender, EventArgs e)
        {
            Response.Redirect("RecuperarCuenta.aspx");
        }
    }
}
