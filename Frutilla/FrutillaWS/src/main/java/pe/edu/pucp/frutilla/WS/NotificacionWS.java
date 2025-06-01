/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.frutilla.WS;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.xml.ws.WebServiceException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import pe.edu.pucp.frutilla.logica.local.NotificacionService;
import pe.edu.pucp.frutilla.models.local.Notificacion;

/**
 *
 * @author Desktop
 */
@WebService(serviceName = "NotificacionWS")
public class NotificacionWS {   
    private NotificacionService notificacionServ;
    
    public NotificacionWS(){
        notificacionServ = new NotificacionService();
    }
    
    @WebMethod(operationName = "listarTodos")
    public List<Notificacion> listarTodos() {
        try {
            return notificacionServ.listarTodos();
        }catch (Exception e){
            throw new WebServiceException("Error al listar notificaciones");
        }
    }
    
    @WebMethod(operationName = "listarPorFecha")
    public ArrayList<Notificacion> listarPorFecha(Date fecha) {
        try {
            return notificacionServ.listarPorFecha(fecha);
        }catch (Exception e){
            throw new WebServiceException("Error al listar notificaciones por fechas");
        }
    }
}
