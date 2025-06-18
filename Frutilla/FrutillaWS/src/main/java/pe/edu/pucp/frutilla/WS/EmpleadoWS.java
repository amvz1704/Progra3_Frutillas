/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.frutilla.WS;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public List<Empleado> obtenerEmpleados(@WebParam (name ="idLocal") int idLocal) {
        try{
            return daoEmpleado.listarTodosPorLocal(idLocal);
        }catch(Exception ex){ 
            System.out.println(ex.getMessage()); 
        }
        return null;
    }
    

    @WebMethod(operationName = "actualizarEmpleado")
    public boolean actualizarEmpleado(@WebParam (name ="empleado") Empleado empleado) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            
            
        try { 
            sdf.setLenient(false); // para evitar fechas inválidas tipo 2024-02-30
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            empleado.setFechaContrato(LocalDate.parse(empleado.getFechatContratoSTRING(), formatter));
            //Date fechaTemp = sdf.parse(empleado.getFechatContratoSTRING());
            //empleado.setFechaContrato(sdf.parse(empleado.getFechatContratoSTRING()));
        }catch (Exception ex) {
            Logger.getLogger(EmpleadoWS.class.getName()).log(Level.SEVERE, null, ex);
        }    
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try { 
            sdf.setLenient(false); // para evitar fechas inválidas tipo 2024-02-30
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            empleado.setFechaContrato(LocalDate.parse(empleado.getFechatContratoSTRING(), formatter));
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoWS.class.getName()).log(Level.SEVERE, null, ex);
        }   
        
        try{
            daoEmpleado.agregar(empleado);
            
            return true;
        }catch(Exception ex){ 
            System.out.println(ex.getMessage()); 
            return false; 
        }
    }
}
