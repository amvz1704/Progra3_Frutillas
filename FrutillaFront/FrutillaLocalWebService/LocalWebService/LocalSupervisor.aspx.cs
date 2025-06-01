
using LocalWebService.EmpleadoWS;
using LocalWebService.LocalWS;
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
        private LocalWSClient daoLocal;

        protected void Page_Load(object sender, EventArgs e)
        {
            daoLocal = new LocalWSClient();
            if (!IsPostBack)
            {
                CargarDatosLocal();
            }
        }

        private void CargarDatosLocal()
        {
            
            // Obtiene el objeto Local
            local local = daoLocal.obtenerIdLocal(LOCAL_ID);

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
            //Response.Redirect($"EditarLocal.aspx?id={LOCAL_ID}");
        }

        protected void btnGuardarModal_Click(object sender, EventArgs e) {
            // 1) Leer el ID del HiddenField:
            int id = int.Parse(hfIdEmpleado.Value);

            // 2) Crear o actualizar según el id:
            if (id == 0)
            {
                // Significa “Nuevo local”: obtén los datos de los campos del modal y agrega
                var nuevo = new local
                {
                    //Id = EmpleadoRepository.ObtenerTodos().Count > 0
                    //       ? EmpleadoRepository.ObtenerTodos().Max(x => x.Id) + 1
                    //       : 1,
                    //NombreCompleto = txtNombreModal.Text.Trim(),
                    //TurnoTrabajo = txtTurnoModal.Text.Trim(),
                    //Tipo = ddlTipoModal.SelectedValue,
                    //Correo = txtCorreoModal.Text.Trim(),
                };
                //daoLocal.ObtenerTodos().Add(nuevo);
            }
            else
            {
                //// Edición de uno existente:
                //var emp = EmpleadoRepository.ObtenerTodos().Find(x => x.Id == id);
                //if (emp != null)
                //{
                //    emp.NombreCompleto = txtNombreModal.Text.Trim();
                //    emp.TurnoTrabajo = txtTurnoModal.Text.Trim();
                //    emp.Tipo = ddlTipoModal.SelectedValue;
                //    emp.Correo = txtCorreoModal.Text.Trim();
                //}
            }

            //// 3) Recargar la grilla principal para reflejar cambios:
            //CargarGrilla();

            //// 4) Cerrar el modal con JavaScript
            //string script = "var myModal = bootstrap.Modal.getInstance(document.getElementById('"
            //                + pnlModalEditar.ClientID + "'));"
            //              + "myModal.hide();";
            //ScriptManager.RegisterStartupScript(this, this.GetType(), "HideModalEditar", script, true);
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