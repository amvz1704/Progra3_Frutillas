/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.frutilla.WS;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.xml.ws.WebServiceException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.text.DateFormatter;
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
    public ArrayList<Notificacion> listarPorFecha(String fecha,int idSupervisor) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false); // para evitar fechas inválidas tipo 2024-02-30
            // Convierte el String fecha a Date
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate fechaDate =  LocalDate.parse(fecha, formatter);
            return notificacionServ.listarPorFecha(fechaDate,idSupervisor);
        }catch (Exception e){
            throw new WebServiceException("Error al listar notificaciones por fechas");
        }
    }
    
    @WebMethod(operationName = "listarPorSupervisor")
    public ArrayList<Notificacion> listarPorSupervisor(int idSupervisor) {
        try {
            return notificacionServ.listarPorSupervisor(idSupervisor);
        }catch (Exception e){
            throw new WebServiceException("Error al listar notificaciones por fechas");
        }
    }
}
