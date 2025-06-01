package pe.edu.pucp.ws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.frutilla.logica.rrhh.EmpleadoService;
import pe.edu.pucp.frutilla.models.rrhh.Empleado;

/**
 *
 * @author NAMS_
 */
@WebService(serviceName = "EmpleadoWS")
public class EmpleadoWS {

    /**
     * This is a sample web service operation
     */
    EmpleadoService dao;
    
    public EmpleadoWS(){
        dao = new EmpleadoService(); 
    
    }
    @WebMethod(operationName = "listarTodos")
    public ArrayList<Empleado> listarEmpleados(@WebParam(name = "idLocal") int idLocal) throws Exception{
        return dao.listarTodosPorLocal(idLocal);
    }
    
    //para ver la informacion de un empleado
    @WebMethod(operationName = "obtenerEmpleado")
    public Empleado obtenerEmpleadoPorId(@WebParam(name = "idEmpleado") int idEmpleado) throws Exception{
        return dao.obtener(idEmpleado); 
    }
    
    //para agregar un empleado
    @WebMethod(operationName = "agregarEmpleado")
    public void insertar(@WebParam(name = "idEmpleado") Empleado empleado) throws Exception{
        dao.agregar(empleado);
        
    }
    
    //para actualizar un empleado
    @WebMethod(operationName = "actualizarEmpleado")
    public void modificar(@WebParam(name = "idEmpleado") Empleado empleado) throws Exception{
        dao.actualizar(empleado);
        
    }
    
    //eliminar un empleado
    
    @WebMethod(operationName = "eliminarEmpleado")
    public void eliminar(@WebParam(name = "idEmpleado") int idEmpleado) throws Exception{
        dao.eliminar(idEmpleado);
        
    }
}
