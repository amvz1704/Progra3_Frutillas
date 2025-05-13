/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.frutilla.crud.dao.local;


import pe.edu.pucp.frutilla.crud.dao.BaseDAO;
import java.time.LocalDate;
import java.util.ArrayList;


import pe.edu.pucp.frutilla.models.local.Notificacion; 
/**
 *
 * @author User
 */
public interface NotificacionDAO extends BaseDAO<Notificacion>{
    public ArrayList <Notificacion> listarPorCliente(int idCliente);
    public ArrayList <Notificacion> listarFecha(LocalDate fecha);
    public ArrayList <Notificacion> listarRangoFecha(LocalDate fechaIni, LocalDate fechaFin);
}
