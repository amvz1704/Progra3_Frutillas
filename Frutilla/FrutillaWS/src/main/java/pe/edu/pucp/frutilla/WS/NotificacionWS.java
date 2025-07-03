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
import pe.edu.pucp.frutilla.dto.NotificacionDTO;
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
    public List<NotificacionDTO> listarTodos() {
        try {
            List<Notificacion> lista = notificacionServ.listarTodos();
            List<NotificacionDTO> listaDTO = new ArrayList<> ();
            for (int i=0;i<lista.size();i++){
                NotificacionDTO temp = new NotificacionDTO(lista.get(i));
                listaDTO.add(temp);
            }
            return listaDTO;
        }catch (Exception e){
            throw new WebServiceException("Error al listar notificaciones");
        }
    }
    
    @WebMethod(operationName = "listarPorFecha")
    public ArrayList<NotificacionDTO> listarPorFecha(String fecha,int idSupervisor) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false); // para evitar fechas inválidas tipo 2024-02-30
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate fechaDate =  LocalDate.parse(fecha, formatter);
            ArrayList<Notificacion> lista = notificacionServ.listarPorFecha(fechaDate,idSupervisor);
            ArrayList<NotificacionDTO> listaDTO = new ArrayList<>();
            for(int i=0;i<lista.size();i++){
                NotificacionDTO temp = new NotificacionDTO(lista.get(i));
                temp.setTipoReceptor('S');
                listaDTO.add(temp);
            }
            return listaDTO;
        }catch (Exception e){
            throw new WebServiceException("Error al listar notificaciones por fechas");
        }
    }
    
    @WebMethod(operationName = "listarPorSupervisor")
    public ArrayList<NotificacionDTO> listarPorSupervisor(int idSupervisor) {
        try {
            ArrayList<Notificacion> lista = notificacionServ.listarPorSupervisor(idSupervisor);
            ArrayList<NotificacionDTO> listaDTO = new ArrayList<> ();
            for (int i=0;i<lista.size();i++){
                NotificacionDTO temp = new NotificacionDTO(lista.get(i));
                temp.setTipoReceptor('S');
                listaDTO.add(temp);
            }
            return listaDTO;
        }catch (Exception e){
            throw new WebServiceException("Error al listar notificaciones por fechas");
        }
    }
    
    @WebMethod(operationName = "listarPorCliente")
    public ArrayList<NotificacionDTO> listarPorCliente(int idCliente){
        try {
            ArrayList<Notificacion> lista = notificacionServ.listarPorCliente(idCliente);
            ArrayList<NotificacionDTO> listaDTO = new ArrayList<> ();
            for (int i=0;i<lista.size();i++){
                NotificacionDTO temp = new NotificacionDTO(lista.get(i));
                temp.setTipoReceptor('C');
                listaDTO.add(temp);
            }
            return listaDTO;
        }catch (Exception e){
            throw new WebServiceException("Error al listar notificaciones por fechas");
        }
    }
}
