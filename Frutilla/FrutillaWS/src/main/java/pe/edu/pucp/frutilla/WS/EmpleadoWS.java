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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.frutilla.dto.EmpleadoDTO;
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
    
   
    
    @WebMethod(operationName = "obtenerEmpleadosxLocal")
    public List<EmpleadoDTO> obtenerEmpleadosxLocal(@WebParam (name ="idLocal") int idLocal) {
        try{
            List<Empleado> lista = daoEmpleado.listarTodosPorLocal(idLocal);
            List<EmpleadoDTO> listaDTO = new ArrayList<>();
            for(int i=0;i<lista.size();i++){
                EmpleadoDTO temp = new EmpleadoDTO(lista.get(i));
                listaDTO.add(temp);
            }
            
            return listaDTO; 
        }catch(Exception ex){ 
            System.out.println(ex.getMessage()); 
        }
        return null;
    }
    

    @WebMethod(operationName = "actualizarEmpleado")
    public boolean actualizarEmpleado(@WebParam (name ="empleado") EmpleadoDTO empleado) {
        
        try {
            Empleado hiddenValues = empleado.convertirAEmpleado();
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
    public EmpleadoDTO obtenerEmpleadoPorId(@WebParam (name ="idEmpleado") int idEmpleado) {
        
        try{
            Empleado temp = daoEmpleado.obtener(idEmpleado);
            EmpleadoDTO empleadoFinal = new  EmpleadoDTO(temp);
            return empleadoFinal; 
        }catch(Exception ex){ 
            System.out.println(ex.getMessage()); 
        }
        return null; 
    }
    
    @WebMethod(operationName = "agregarEmpleado")
    public boolean agregarEmpleado(@WebParam (name ="empleado") EmpleadoDTO empleado) {
       
            Empleado temp = empleado.convertirAEmpleado();
            
        
        try{
            daoEmpleado.agregar(temp);
            
            return true;
        }catch(Exception ex){ 
            System.out.println(ex.getMessage()); 
            return false; 
        }
    }
}
