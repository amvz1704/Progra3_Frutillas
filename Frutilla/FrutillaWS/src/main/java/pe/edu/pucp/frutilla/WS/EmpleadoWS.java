/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.frutilla.WS;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.List;
import pe.edu.pucp.frutilla.logica.rrhh.EmpleadoService;
import pe.edu.pucp.frutilla.models.rrhh.Empleado;

/**
 *
 * @author NAMS_
 */
@WebService(serviceName = "EmpleadoWS")
public class EmpleadoWS {
    
    private final EmpleadoService daoEmpleado; 
    /**
     * This is a sample web service operation
     */
    
    public EmpleadoWS(){
        daoEmpleado = new EmpleadoService(); 
    }
    
    @WebMethod(operationName = "obtenerEmpleados")
    public List<Empleado> obtenerLocal(@WebParam (name ="idLocal") int idLocal) {
        try{
            return daoEmpleado.listarTodosPorLocal(idLocal);
        }catch(Exception ex){ 
            System.out.println(ex.getMessage()); 
        }
        return null;
    }
    
    @WebMethod(operationName = "actualizarEmpleado")
    public boolean actualizarEmpleado(@WebParam (name ="empleado") Empleado empleado) {
        try{
            daoEmpleado.actualizar(empleado);
            return true; 
        }catch(Exception ex){ 
            System.out.println(ex.getMessage()); 
            return false; 
        }
    }
    
    @WebMethod(operationName = "eliminarEmpleado")
    public boolean eliminarEmpleado(@WebParam (name ="idEmpleado") int idEmpleado) {
        try{
            daoEmpleado.eliminar(idEmpleado);
            return true; 
        }catch(Exception ex){ 
            System.out.println(ex.getMessage()); 
            return false; 
        }
    }
    
    @WebMethod(operationName = "obtenerEmpleadoPorId")
    public Empleado obtenerEmpleadoPorId(@WebParam (name ="idEmpleado") int idEmpleado) {
        try{
            return daoEmpleado.obtener(idEmpleado);
        }catch(Exception ex){ 
            System.out.println(ex.getMessage()); 
        }
        return null; 
    }
    
    @WebMethod(operationName = "agregarEmpleado")
    public boolean agregarEmpleado(@WebParam (name ="empleado") Empleado empleado) {
        try{
            daoEmpleado.agregar(empleado);
            
            return true;
        }catch(Exception ex){ 
            System.out.println(ex.getMessage()); 
            return false; 
        }
    }
}
