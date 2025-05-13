package pe.edu.pucp.frutilla.logica.rrhh;

import java.util.ArrayList;
import java.util.List;

import pe.edu.pucp.frutilla.crud.mysql.rrhh.EmpleadoMySQL;
import pe.edu.pucp.frutilla.models.rrhh.Empleado;

public class EmpleadoService {
    private final EmpleadoMySQL empleadoMySQL;
    
    public EmpleadoService() {
        empleadoMySQL = new EmpleadoMySQL();
    }

    public void agregar(Empleado empleado) throws Exception {
        // Validaciones
        if (empleado == null) {
            throw new Exception("El empleado no puede ser nulo");
        }

        if (empleado.getNombre() == null || empleado.getNombre().trim().isEmpty()) {
            throw new Exception("El nombre del empleado no puede ser vacío");
        }

        if (empleado.getApellidoPaterno() == null || empleado.getApellidoPaterno().trim().isEmpty()) {
            throw new Exception("El apellido paterno del empleado no puede ser vacío");
        }

        if (empleado.getApellidoMaterno() == null || empleado.getApellidoMaterno().trim().isEmpty()) {
            throw new Exception("El apellido materno del empleado no puede ser vacío");
        }

        if (empleado.getTelefono() == null || empleado.getTelefono().trim().isEmpty()) {
            throw new Exception("El teléfono del empleado no puede ser vacío");
        }

        if (empleado.getCorreoElectronico() == null || empleado.getCorreoElectronico().trim().isEmpty()) {
            throw new Exception("El correo electrónico del empleado no puede ser vacío");
        }

        if (empleado.getUsuarioSistema() == null || empleado.getUsuarioSistema().trim().isEmpty()) {
            throw new Exception("El usuario del empleado no puede ser vacío");
        }

        if (empleado.getContraSistema() == null || empleado.getContraSistema().trim().isEmpty()) {
            throw new Exception("La contraseña del empleado no puede ser vacía");
        }
        
        if (empleado.getFechaContrato() == null) {
            throw new Exception("La fecha de contrato del empleado no puede ser nula");
        }
        
        if (empleado.getSalario() <= 0) {
            throw new Exception("El salario del empleado no puede ser menor o igual a 0");
        }
        
        if (empleado.getIdLocal() <= 0) {
            throw new Exception("El id del local del empleado no puede ser menor o igual a 0");
        }

        empleadoMySQL.agregar(empleado);
    }

    public void actualizar(Empleado empleado) throws Exception {
        // Validaciones
        if (empleado == null) {
            throw new Exception("El empleado no puede ser nulo");
        }

        if (empleado.getIdEmpleado() <= 0) {
            throw new Exception("El id del empleado no puede ser menor o igual a 0");
        }

        if (empleado.getNombre() == null || empleado.getNombre().trim().isEmpty()) {
            throw new Exception("El nombre del empleado no puede ser vacío");
        }

        if (empleado.getApellidoPaterno() == null || empleado.getApellidoPaterno().trim().isEmpty()) {
            throw new Exception("El apellido paterno del empleado no puede ser vacío");
        }

        if (empleado.getApellidoMaterno() == null || empleado.getApellidoMaterno().trim().isEmpty()) {
            throw new Exception("El apellido materno del empleado no puede ser vacío");
        }

        if (empleado.getTelefono() == null || empleado.getTelefono().trim().isEmpty()) {
            throw new Exception("El teléfono del empleado no puede ser vacío");
        }

        if (empleado.getCorreoElectronico() == null || empleado.getCorreoElectronico().trim().isEmpty()) {
            throw new Exception("El correo electrónico del empleado no puede ser vacío");
        }
        
        if (empleado.getFechaContrato() == null) {
            throw new Exception("La fecha de contrato del empleado no puede ser nula");
        }
        
        if (empleado.getSalario() <= 0) {
            throw new Exception("El salario del empleado no puede ser menor o igual a 0");
        }
        
        if (empleado.getIdLocal() <= 0) {
            throw new Exception("El id del local del empleado no puede ser menor o igual a 0");
        }

        empleadoMySQL.actualizar(empleado);
    }

    public void eliminar(int idEmpleado) throws Exception {
        if (idEmpleado <= 0) {
            throw new Exception("El id del empleado no puede ser menor o igual a 0");
        }
        empleadoMySQL.eliminar(idEmpleado);
    }

    public Empleado obtener(int idEmpleado) throws Exception {
        if (idEmpleado <= 0) {
            throw new Exception("El id del empleado no puede ser menor o igual a 0");
        }
        return empleadoMySQL.obtener(idEmpleado);
    }

    public List<Empleado> listar() throws Exception{
        return empleadoMySQL.listarTodos();
    }

    public ArrayList<Empleado> listarTodosPorLocal(int idLocal) throws Exception {
        if (idLocal <= 0) {
            throw new Exception("El id del local no puede ser menor o igual a 0");
        }
        return empleadoMySQL.listarTodosPorLocal(idLocal);
    }

}
