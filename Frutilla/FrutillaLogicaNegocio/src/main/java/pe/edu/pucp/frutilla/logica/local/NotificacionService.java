/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.frutilla.logica.local;

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
    private NotificacionDAO notificacionDAO;
    
    public NotificacionService (){
        notificacionDAO = new NotificacionMySQL();
    }
    
    public List<Notificacion> listarTodos(){
        return notificacionDAO.listarTodos();
    }
    
    public ArrayList<Notificacion> listarPorFecha(Date fecha){
        return notificacionDAO.listarPorFecha(fecha);
    }
}
