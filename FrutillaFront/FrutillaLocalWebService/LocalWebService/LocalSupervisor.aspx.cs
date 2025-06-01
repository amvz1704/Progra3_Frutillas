
using LocalWebService.FrutillaWS;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;


namespace LocalWebService
{
    public partial class LocalSupervisor : System.Web.UI.Page
    {
        const int LOCAL_ID = 1; //esto dependera del id del empleado --> cambiar con LOGIN
        private LocalWSClient daoLocal = new LocalWSClient();

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                CargarDatosLocal();
            }
        }

        private void CargarDatosLocal()
        {
            
            // Obtiene el objeto Local
            local local = daoLocal.obtenerId(LOCAL_ID);

            if (local != null)
            {
                // Asigna valores a cada control
                lblNombre.Text = local.nombre;
                lblDireccion.Text = local.direccion;
                lblDescripcion.Text = local.descripcion;
                lblTelefono.Text = local.telefono; 

                // Imagen: si tienes una URL o la obtienes como bytes: agregr al final local.ImagenUrl ??  
                imgLocal.ImageUrl =  "~/images/default-local.png";
            }
            else
            {
                // Si no existe, podrías ocultar el panel o mostrar un mensaje
                lblNombre.Text = "No encontrado";
                // opcional: un Label de error

            }
        }

        protected void btnEditar_Click(object sender, EventArgs e) {
            // Redirige a la página de edición, pasando el ID
            Response.Redirect($"EditarLocal.aspx?id={LOCAL_ID}");
        }

        protected void BtnAdminEmpleados(object sender, EventArgs e) {

            Response.Redirect($"ListaEmpleadosSupervisor.aspx?id={LOCAL_ID}");
        }

        protected void BtnAdminProductos(object sender, EventArgs e)
        {

            Response.Redirect($"ListaProductosSupervisor.aspx?id={LOCAL_ID}");
        }

        protected void BtnAdminVentas(object sender, EventArgs e)
        {

            Response.Redirect($"ListaVentasSupervisor.aspx?id={LOCAL_ID}");
        }
    }
}