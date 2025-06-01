/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.frutilla.WS;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.List;

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
    
    @WebMethod(operationName = "listarTodosLocales")
    public List<Local> listarTodosLocales() {
        List<Local> local = null; 
        try{
            return daoLocal.listarActivos();
        }catch(Exception ex){ 
            System.out.println(ex.getMessage()); 
        }
        return local;
    }
    
    @WebMethod(operationName = "obtenerIdLocal")
    public Local obtenerIdLocal(@WebParam (name ="idLocal") int idLocal) {
        try{
            return daoLocal.obtenerPorId(idLocal);
        }catch(Exception ex){ 
            System.out.println(ex.getMessage()); 
        }
        return null;
    }
}
