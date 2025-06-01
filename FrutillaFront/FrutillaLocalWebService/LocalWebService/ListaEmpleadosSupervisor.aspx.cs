using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using LocalWebService.EmpleadoWS;


namespace LocalWebService
{

    public partial class ListaEmpleadosSupervisor : System.Web.UI.Page
    {
        //protected Empleado empleadoService;
        const int LOCAL_ID = 1; //esto dependera del id del empleado que abre la pagina
        private EmpleadoWSClient daoEmpleado;

        protected void Page_Load(object sender, EventArgs e)
        {
            daoEmpleado = new EmpleadoWSClient();
            CargarDatos();
        }

        protected void btnAgregarEmpleado_Click(object sender, EventArgs e) { 
            //codigo para abir la cosa es 
        }

        protected void DdlEmpleado_SelectedIndexChanged(object sender, EventArgs e) {

            //el empleado guarda tipo en la BD? 

            //int id_empleado = int.Parse(DdlAreas.SelectedValue);
            //if (id_area_select == 0)
            //{
            //    GvEmpleados.DataSource = empleadoService.ListarTodos();
            //    GvEmpleados.DataBind();
            //}
            //else
            //{
            //    GvEmpleados.DataSource = empleadoService.ListarTodos().Where(p => p.Area.IdArea == id_area_select).ToList();
            //    GvEmpleados.DataBind();
            //}

        }

        protected void GvEmpleados_PageIndexChanging(object sender, System.Web.UI.WebControls.GridViewPageEventArgs e) {
            GvEmpleados.PageIndex = e.NewPageIndex;
            GvEmpleados.DataBind(); 

        }

        protected void CargarDatos()
        {
            GvEmpleados.DataSource = daoEmpleado.obtenerEmpleados(LOCAL_ID);
            GvEmpleados.DataBind();
        }
    }
}