using LocalWebService.EmpleadoWS;
using LocalWebService.LocalWS;
using LocalWebService.NotificionesWS;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Net.Sockets;
using System.Reflection;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;


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
            if (!IsPostBack)
            {
                CargarEmpleados();
            }

        }

        protected void btnAgregarEmpleado_Click(object sender, EventArgs e)
        {
            //codigo para abir el modal que agrega empleado * Por hacer 
            // Como es "Agregar", ponemos el hidden en 0:
            hfIdEmpleado.Value = "0";
            hfModo.Value = "Create";

            // Limpiamos todos los campos del modal:
            LimpiarCamposModal();

        }

        private void LimpiarCamposModal()
        {
            txtNombre.Text = "";
            txtApellidoPa.Text = "";
            txtApellidoMa.Text = "";
            txtTelefono.Text = "";
            txtSalario.Text = "";
            txtFechaContrato.Text = "";
            txtCorreo.Text = "";
            // HfIdEmpleado ya está en "0".

            string script = @"
                        var modal = new bootstrap.Modal(
                          document.getElementById('miModalEditarEmpleado'), {});
                        modal.show();";
            ScriptManager.RegisterStartupScript(this, this.GetType(), "ShowEditarModal", script, true);

        }


        protected void GvEmpleado_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            gvEmpleados.PageIndex = e.NewPageIndex;
            CargarEmpleados();

        }

        private void CargarEmpleados()
        {
            try
            {
                gvEmpleados.DataSource = daoEmpleado.obtenerEmpleados(LOCAL_ID);
                gvEmpleados.DataBind();
            }
            catch (Exception ex)
            {
                lblError.Text = "Error al cargar los empleados: " + ex.Message;
            }
        }

        protected void GvEmpleados_RowCommand(object sender, GridViewCommandEventArgs e)
        {
            if (e.CommandArgument == null) return;

            int idEmp = Convert.ToInt32(e.CommandArgument);
            switch (e.CommandName)
            {
                case "VerDetalles":
                    VerDetallesEmpleado(idEmp);
                    break;

                case "Editar":
                    CargarModalEditarEmpleado(idEmp);
                    break;

                case "Eliminar":
                    EliminarEmpleado(idEmp);
                    break;
            }
        }


        protected void BtnEliminar_Click(object sender, EventArgs e)
        {
            int idEmpleado = int.Parse((sender as LinkButton).CommandArgument);
            daoEmpleado.eliminarEmpleado(idEmpleado);
            CargarEmpleados();
        }

        private void VerDetallesEmpleado(int idEmp)
        {

            try
            {
                var client = new EmpleadoWSClient();
                var emp = client.obtenerEmpleadoPorId(idEmp);
                client.Close();
                


                if (emp != null)
                {
                    // Asignamos valores a los labels del modal
                    lblVerID.Text = emp.idUsuario.ToString();
                    lblVerNombre.Text = emp.nombre;
                    lblVerApellidoPa.Text = emp.apellidoPaterno;
                    lblVerApellidoMa.Text = emp.apellidoMaterno;
                    lblVerSalario.Text = emp.salario.ToString("N2");
                    lblVerTelefono.Text = emp.telefono;

                    // Define el formato de entrada esperado.
                    
                    lblVerFechaContrato.Text = emp.fechatContratoSTRING;
                    lblVerCorreo.Text = emp.correoElectronico;
                    
                    if (emp.turnoTrabajo)
                    {
                        lblTurno.Text = "Mañana";
                    }
                    else {
                        lblTurno.Text = "Tarde";
                    }

                        // Ejecutamos JS para mostrar el modal
                        string script = @"
                        var modal = new bootstrap.Modal(
                          document.getElementById('miModalVerDetalles'), {});
                        modal.show();";
                    ScriptManager.RegisterStartupScript(this, this.GetType(), "ShowVerDetallesModal", script, true);
                }
                else
                {
                    lblError.Text = "\"warning\", $\"No se encontró empleado con ID = {idEmp}\"";

                }
            }
            catch (Exception ex)
            {
                lblError.Text = "Error al obtener detalles los empleados: " + ex.Message;
            }
        }


        private void CargarModalEditarEmpleado(int idEmp)
        {
            hfModo.Value = "Editar";

            try
            {
                var client = new EmpleadoWSClient();
                var emp = client.obtenerEmpleadoPorId(idEmp);
                client.Close();
                // var fecha = emp.fechaContrato;


                if (emp != null)
                {
                    // Guardamos el ID para saber si es edición
                    hfIdEmpleado.Value = emp.idUsuario.ToString();
                    txtNombre.Text = emp.nombre;
                    txtApellidoPa.Text = emp.apellidoPaterno;
                    txtApellidoMa.Text = emp.apellidoMaterno;
                    txtSalario.Text = emp.salario.ToString("N2");
                    txtTelefono.Text = emp.telefono;
                    txtCorreo.Text = emp.correoElectronico;
                    txtFechaContrato.Text = emp.fechatContratoSTRING;
                    ddlEstado.SelectedValue = emp.turnoTrabajo.ToString().ToLower(); 


                    // Mostrar modal
                    string script = @"
                        var modal = new bootstrap.Modal(
                          document.getElementById('miModalEditarEmpleado'), {});
                        modal.show();";
                    ScriptManager.RegisterStartupScript(this, this.GetType(), "ShowEditarModal", script, true);
                }
                else
                {
                    lblError.Text = "\"warning\", $\"No se encontró empleado con ID = {idEmp}\"";
                }
            }
            catch (Exception ex)
            {
                lblError.Text = "Error al obtener detalles los empleados: " + ex.Message;

            }
        }

        private void EliminarEmpleado(int idEmp)
        {
            try
            {
                var client = new EmpleadoWSClient();
                bool ok = client.eliminarEmpleado(idEmp);
                client.Close();

                if (ok)
                {
                    lblError.Text = "Correcto al obtener detalles los empleados: ";
                }
                else
                {
                    lblError.Text = "Waring al eliminar el cliente";
                }
            }
            catch (Exception ex)
            {
                lblError.Text = "DANGER!! " + ex.Message;
            }
        }


        protected void btnGuardarModal_Click(object sender, EventArgs e)
        {
            int idEmp;
            if (!int.TryParse(hfIdEmpleado.Value, out idEmp)) idEmp = 0;

            DateTime fechaContrato;
            string fechaFormateada = "";
            if (DateTime.TryParse(txtFechaContrato.Text, out fechaContrato))
            {
                fechaFormateada = fechaContrato.ToString("yyyy-MM-dd");
                // Ahora tienes la fecha en formato string tipo yyyy-MM-dd
            }

            string modo = hfModo.Value;
            


            // Construimos el DTO según tu 
            var empDto = new EmpleadoWS.empleado
            {
                idUsuario = idEmp,
                idLocal = LOCAL_ID,
                nombre = txtNombre.Text.Trim(),
                apellidoPaterno = txtApellidoPa.Text.Trim(),
                apellidoMaterno = txtApellidoMa.Text.Trim(),
                salario = double.TryParse(txtSalario.Text.Trim(), out double s) ? s : 0,
                telefono = txtTelefono.Text.Trim(),
                correoElectronico = txtCorreo.Text.Trim(),
                turnoTrabajo = ddlEstado.SelectedValue == "true",
                fechatContratoSTRING = fechaFormateada
            };


            try
            {
                var client = new EmpleadoWSClient();
                bool ok;
                if (modo == "Create")
                { 
                    ok = client.agregarEmpleado(empDto);      // Método WSDL para asignar un empleado* pero este debe crear su cuenta primero pues
                }
                else
                {
                    

                    ok = client.actualizarEmpleado(empDto); // Método WSDL para actualizar
                }
                client.Close();

                if (!ok)
                {
                    lblError.Text = "INCORRECTO al agregar detalles de los empleados: "; return;
                }

                // Recarga la grilla
                CargarEmpleados();

                // Cerrar modal por JavaScript
                string scriptHide = @"
                  var modal = bootstrap.Modal.getInstance(
                    document.getElementById('miModalEditarEmpleado'));
                  if(modal) modal.hide();";
                ScriptManager.RegisterStartupScript(this, this.GetType(), "HideModal", scriptHide, true);
            }
            catch (Exception ex)
            {
                lblError.Text = "ERRROR s: " + ex.Message;
            }
        }

    }
}