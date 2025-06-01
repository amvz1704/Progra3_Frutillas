package pe.edu.pucp.ws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import pe.edu.pucp.frutilla.logica.local.LocalService;
import pe.edu.pucp.frutilla.models.local.Local;

/**
 *
 * @author NAMS_
 */
@WebService(serviceName = "LocalWS")
public class LocalWS {

    /**
     * This is a sample web service operation
     */
   
    @WebMethod(operationName = "obtenerId")
    public Local obtenerPorId(@WebParam(name = "codigo") int codigo) throws Exception {
        LocalService dao = new LocalService(); 
        Local obtener = dao.obtenerPorId(codigo); 
        
        return obtener; 
    }
    
    
}
