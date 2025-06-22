/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.frutilla.logica.local;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import pe.edu.pucp.frutilla.crud.dao.local.NotificacionDAO;
import pe.edu.pucp.frutilla.crud.mysql.local.NotificacionMySQL;
import pe.edu.pucp.frutilla.models.local.Notificacion;

/**
 *
 * @author Desktop
 */
public class NotificacionService {
    private final NotificacionDAO notificacionDAO;
    
    public NotificacionService (){
        notificacionDAO = new NotificacionMySQL();
    }
    
    public List<Notificacion> listarTodos(){
        return notificacionDAO.listarTodos();
    }
    
    public ArrayList<Notificacion> listarPorSupervisor(int idSupervisor) 
            throws Exception{
        if(idSupervisor <= 0 ){
            throw new Exception("El idSupervisor no puede ser negativo o 0");
        }
        return notificacionDAO.listarPorSupervisor(idSupervisor);
    }
    
    public ArrayList<Notificacion> listarPorFecha(LocalDate fecha,int idSupervisor) throws Exception{
        if (fecha == null || fecha.isAfter(LocalDate.now())) {
            throw new Exception("Las fechas no pueden ser nulas");
        }
        if(idSupervisor <= 0){
            throw new Exception("El idSupervisor no puede ser negativo o 0");
        }
        return notificacionDAO.listarPorFecha(fecha,idSupervisor);
    }
    public ArrayList<Notificacion> listarPorCliente(int idCliente)throws Exception{
        if(idCliente<=0)
            throw new Exception("El idCliente no puede ser negativo o 0");
        return notificacionDAO.listarPorCliente(idCliente);
    }
}
