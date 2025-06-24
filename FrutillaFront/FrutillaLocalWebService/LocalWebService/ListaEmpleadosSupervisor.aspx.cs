using LocalWebService.EmpleadoWS;
using LocalWebService.LocalWS;
using LocalWebService.NotificionesWS;
using LocalWebService.UsuarioWS;
using System;
using System.Collections;
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
        
        private EmpleadoWSClient daoEmpleado;
        protected int LocalId { get; private set; }
        //para obtener el local 
        private int localActualId
        {
            get => ViewState["LOCAL_ID"] as int? ?? 0;
            set => ViewState["LOCAL_ID"] = value;
        }



        protected void Page_Load(object sender, EventArgs e)
        {

            daoEmpleado = new EmpleadoWSClient();
            if (!IsPostBack)
            {
                string sId = Request.QueryString["id"];
                if (!int.TryParse(sId, out int id))
                {
                    // Parámetro inválido; podrías redirigir o mostrar error
                    Response.Redirect("LocalSupervisor.aspx");
                    return;
                }
                // 2) Guardarlo si luego lo vas a reutilizar
                localActualId = id;
                CargarEmpleados();
            }

        }


        protected void btnBuscarEmpleado_Click(object sender, EventArgs e) {

            string nombre = txtBuscar.Text.Trim();
            CargarEmpleados(nombre);
        } 
        protected void btnAgregarEmpleado_Click(object sender, EventArgs e)
        {
            //codigo para abir el modal que agrega empleado * Por hacer 
            // Como es "Agregar", ponemos el hidden en 0:
            hfIdEmpleado.Value = "0";
            hfModo.Value = "Create";

            // Limpiamos todos los campos del modal:
            crearUsuarioModal(); //creamos otro modal para la creacion de usuario

        }

        private void crearUsuarioModal()
        {
            txtNombre.Text = "";
            txtApellidoPa.Text = "";
            txtApellidoMa.Text = "";
            txtTelefono.Text = "";
            txtSalario.Text = "";
            txtFechaContrato.Text = "";
            txtCorreo.Text = "";

            //usuario generado random con contraRandom

            txtUsuario.Text = "";
            txtContrasena.Text = ""; 
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

        private void CargarEmpleados(string filtroNombre = null)
        {
            

            try
            {
                // 1) Obtén la lista completa (desde tu DAO o servicio)
                var listaOriginal = daoEmpleado.obtenerEmpleadosxLocal(localActualId);

                if (!string.IsNullOrWhiteSpace(filtroNombre))
                {
                    var filtrados = listaOriginal
                    .Where(emp => !string.IsNullOrEmpty(emp.nombre)
                                  && (emp.nombre.IndexOf(filtroNombre,StringComparison.CurrentCultureIgnoreCase) >= 0
                                  ||
                                  emp.apellidoPaterno.IndexOf(filtroNombre,StringComparison.CurrentCultureIgnoreCase) >= 0
                                  ||
                                  emp.apellidoMaterno.IndexOf(filtroNombre, StringComparison.CurrentCultureIgnoreCase) >= 0)
                                  
                                  )
                    .ToList();

                    gvEmpleados.DataSource = filtrados;
                    gvEmpleados.DataBind();
                }
                else {
                    gvEmpleados.DataSource = listaOriginal;
                    gvEmpleados.DataBind();

                }
                
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
                        lblTurno.Text = "Noche";
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
                    txtUsuario.Text = emp.usuarioSistema.ToString();
                    txtContrasena.Text = emp.contraSistema.ToString();


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
                    CargarEmpleados(); 
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

            //validar el ingreso de usuario --> hacer una sesion sabes 
            UsuarioWSClient usuarioService = new UsuarioWSClient(); 

            string nombre = txtNombre.Text.Trim();
            string apPaterno = txtApellidoPa.Text.Trim();
            string apMaterno = txtApellidoMa.Text.Trim();
            string usuario = txtUsuario.Text.Trim();
            string password = txtContrasena.Text;
            string correo = txtCorreo.Text.Trim();
            string telefono = txtTelefono.Text.Trim();
            double salario = double.TryParse(txtSalario.Text.Trim(), out double s) ? s : 0;
            bool turnoTrabajo = ddlEstado.SelectedValue == "true";

            bool esValido = true;
            //mostrar un modal
            if (string.IsNullOrEmpty(nombre)) { txtNombre.Text = "Ingrese su nombre"; esValido = false; }
            if (string.IsNullOrEmpty(apPaterno)) { txtApellidoPa.Text = "Ingrese su apellido paterno"; esValido = false; }
            if (string.IsNullOrEmpty(apMaterno)) { txtApellidoMa.Text = "Ingrese su apellido materno"; esValido = false; }
            if (string.IsNullOrEmpty(usuario))
            {
                txtUsuario.Text = "Ingrese un nombre de usuario";
                esValido = false;
            }
            else if (!System.Text.RegularExpressions.Regex.IsMatch(usuario, @"^[a-zA-Z0-9_]+$"))
            {
                txtUsuario.Text = "Solo se permite una palabra sin espacios, letras, números o guion bajo.";
                esValido = false;
            }

            if (string.IsNullOrEmpty(password))
            {
                txtContrasena.Text = "Ingrese una contraseña";
                esValido = false;
            }
            else if (password.Length < 6)
            {
                txtContrasena.Text = "La contraseña debe tener al menos 6 caracteres.";
                esValido = false;
            }
            else if (password.Contains(" "))
            {
                txtContrasena.Text = "La contraseña no debe contener espacios.";
                esValido = false;
            }
            else if (!System.Text.RegularExpressions.Regex.IsMatch(password, @"^[a-zA-Z0-9]+$"))
            {
                txtContrasena.Text = "Solo se permiten letras y números.";
                esValido = false;
            }


            if (string.IsNullOrEmpty(correo))
            {
                txtCorreo.Text = "Ingrese un correo electrónico";
                esValido = false;
            }
            else if (!System.Text.RegularExpressions.Regex.IsMatch(correo, @"^[^@\s]+@[^@\s]+\.[^@\s]+$"))
            {
                txtCorreo.Text = "Correo electrónico no válido";
                esValido = false;
            }
            if (usuarioService.correoExiste(correo))
            {
                txtCorreo.Text = "Este correo ya está asociado a una cuenta.";
                esValido = false;
            }

            if (string.IsNullOrEmpty(telefono))
            {
                txtTelefono.Text = "Ingrese un teléfono";
                esValido = false;
            }
            else if (!telefono.All(char.IsDigit) || telefono.Length != 9)
            {
                txtTelefono.Text = "Teléfono inválido. Debe contener 9 dígitos numéricos.";
                esValido = false;
            }

            if (!esValido) return;

            int id = usuarioService.obtenerIDPorNombreUsuario(usuario);
            if (id > 0)
            {
                txtUsuario.Text = "Este nombre de usuario ya está en uso.";
                return;
            }

            //una vez actualizado la base de datos lo confirma la persona y se crea el usuario 
            var empDto = new EmpleadoWS.empleadoDTO
            {
                idUsuario = idEmp,
                idLocal = localActualId,
                nombre = nombre,
                apellidoPaterno = apPaterno,
                apellidoMaterno = apMaterno,
                salario = salario,
                telefono = telefono,
                correoElectronico = correo,
                turnoTrabajo = turnoTrabajo,
                fechatContratoSTRING = fechaFormateada,
                usuarioSistema = usuario,
                contraSistema = password
            };


            try
            {
                var client = new EmpleadoWSClient();
                bool ok;
                if (modo == "Create")
                { 
                    ok = client.agregarEmpleado(empDto);
                    // Método WSDL para asignar un empleado* pero este debe crear su cuenta primero pues
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