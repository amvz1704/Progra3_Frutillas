package pe.edu.pucp.frutilla.WS;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NAMS_
 */

import pe.edu.pucp.frutilla.models.local.Local;
import pe.edu.pucp.frutilla.logica.local.LocalService;

@WebService(serviceName = "LocalWS")
public class LocalWS {

    private final LocalService daoLocal; 
    
    public LocalWS(){
        daoLocal = new LocalService();
    }
    
    @WebMethod(operationName = "obtenerLocal")
    public Local obtenerLocal(@WebParam (name ="idLocal") int idLocal) {
        try{
            LocalService nuevo = new LocalService(); 
            return nuevo.obtenerPorId(idLocal);
        }catch(Exception ex){ 
            System.out.println(ex.getMessage()); 
        }
        return null;
    }
    
    @WebMethod(operationName = "actualizarLocalVarios")
    public boolean actualizarLocalVarios(
            @WebParam (name ="id") int id, 
            @WebParam (name ="nombre") String nombre,
            @WebParam (name ="telefono") String telefono,
            @WebParam (name ="descripcion") String descripcion,
            @WebParam (name ="direccion") String direccion) {
        
        try {
            Local local = new Local(daoLocal.obtenerPorId(id));
            local.setIdLocal(id);
            local.setDescripcion(descripcion);
            local.setDireccion(direccion);
            local.setTelefono(telefono);
            local.setNombre(nombre);
            
            System.out.println("Llega al servicio con Local: " + local);
            try{
                daoLocal.actualizar(local);
                return true;
            }catch(Exception ex){ 
                System.out.println(ex.getMessage()); 
                return false; 
            }
        } catch (Exception ex) {
            Logger.getLogger(LocalWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false; 
        
    }
    
    @WebMethod(operationName = "listarLocales")
    public List<Local> listarLocales(){
        try{
            return daoLocal.listarActivos();
        } catch (Exception ex){
            System.out.println(ex.getMessage()); 
        }
        return null;
    }
}
