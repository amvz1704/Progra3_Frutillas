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
            Date fechaTemp = sdf.parse(empleado.getFechatContratoSTRING());
            empleado.setFechaContrato(LocalDate.parse(empleado.getFechatContratoSTRING()));
            
            try {
                Empleado hiddenValues = daoEmpleado.obtener(empleado.getIdUsuario());
                hiddenValues.setNombre(empleado.getNombre());
                hiddenValues.setApellidoMaterno(empleado.getApellidoMaterno());
                hiddenValues.setApellidoPaterno(empleado.getApellidoPaterno());
                hiddenValues.setSalario(empleado.getSalario());
                hiddenValues.setCorreoElectronico(empleado.getCorreoElectronico());
                hiddenValues.setTurnoTrabajo(empleado.getTurnoTrabajo());
                hiddenValues.setTelefono(empleado.getTelefono());
                hiddenValues.setFechaContrato(LocalDate.parse(empleado.getFechatContratoSTRING()));
                hiddenValues.setFechatContratoSTRING(empleado.getFechatContratoSTRING());
                try{
                    daoEmpleado.actualizar(hiddenValues);
                    return true; 
                }catch(Exception ex){ 
                    System.out.println(ex.getMessage()); 
                    return false; 
                }
            } catch (Exception ex) {
                Logger.getLogger(EmpleadoWS.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (ParseException ex) {
            Logger.getLogger(EmpleadoWS.class.getName()).log(Level.SEVERE, null, ex);
            
        }    
        return false; 
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
            LocalDate fechaTemp = LocalDate.parse(empleado.getFechatContratoSTRING());
            
            empleado.setFechaContrato(fechaTemp);
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoWS.class.getName()).log(Level.SEVERE, null, ex);
            return false;
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
