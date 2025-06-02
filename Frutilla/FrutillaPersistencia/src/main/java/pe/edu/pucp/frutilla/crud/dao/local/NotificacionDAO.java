/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.frutilla.crud.dao.local;


import java.time.LocalDate;
import pe.edu.pucp.frutilla.crud.dao.BaseDAO;
import java.util.ArrayList;
import pe.edu.pucp.frutilla.models.local.Notificacion; 
/**
 *
 * @author User
 */
public interface NotificacionDAO extends BaseDAO<Notificacion>{
    ArrayList<Notificacion> listarPorFecha(LocalDate fecha, int idSupervisor);
    ArrayList<Notificacion> listarPorSupervisor(int idSupervisor);
}
