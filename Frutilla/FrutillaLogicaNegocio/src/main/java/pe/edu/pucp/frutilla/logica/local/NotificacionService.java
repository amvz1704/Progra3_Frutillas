package pe.edu.pucp.frutilla.logica.local;

import java.util.ArrayList;
import java.util.List;

import pe.edu.pucp.frutilla.crud.mysql.local.NotificacionMySQL;
import pe.edu.pucp.frutilla.models.inventario.Producto;
import pe.edu.pucp.frutilla.models.local.Notificacion;

public class NotificacionService {
    private NotificacionMySQL notiSQL;
    
    public NotificacionService (){
        notiSQL=new NotificacionMySQL();
    }
    
    public void agregar(Notificacion noti) throws Exception{
        if (noti == null)
            throw new Exception("La notificación ha ingresado nula");

        if (noti.getTitulo() == null || noti.getTitulo().trim().isEmpty())
            throw new Exception("El título no puede ser vacío");

        if (noti.getDescripcion() == null || noti.getDescripcion().trim().isEmpty())
            throw new Exception("La descripción no puede ser vacía");

        if (noti.getFecha() == null)
            throw new Exception("La fecha no puede ser nula");

        if (noti.getHora() == null)
            throw new Exception("La hora no puede ser nula");

        if (noti.getTipoReceptor() != 'C' && noti.getTipoReceptor() != 'S')
            throw new Exception("El tipo de receptor debe ser 'C' para Cliente o 'S' para Supervisor");

        if (noti.getTipoReceptor() == 'C' && noti.getIdCliente() <= 0)
            throw new Exception("El ID del cliente debe ser mayor a 0");

        if (noti.getTipoReceptor() == 'S' && noti.getIdSupervisor() <= 0)
            throw new Exception("El ID del supervisor debe ser mayor a 0");

        // Si pasa todas las validaciones, se procede a actualizar
        notiSQL.agregar(noti);
    }
    
    public void actualizar(Notificacion noti) throws Exception{
        if (noti == null)
            throw new Exception("La notificación ha ingresado nula");

        if (noti.getTitulo() == null || noti.getTitulo().trim().isEmpty())
            throw new Exception("El título no puede ser vacío");

        if (noti.getDescripcion() == null || noti.getDescripcion().trim().isEmpty())
            throw new Exception("La descripción no puede ser vacía");

        if (noti.getFecha() == null)
            throw new Exception("La fecha no puede ser nula");

        if (noti.getHora() == null)
            throw new Exception("La hora no puede ser nula");

        if (noti.getTipoReceptor() != 'C' && noti.getTipoReceptor() != 'S')
            throw new Exception("El tipo de receptor debe ser 'C' para Cliente o 'S' para Supervisor");

        if (noti.getTipoReceptor() == 'C' && noti.getIdCliente() <= 0)
            throw new Exception("El ID del cliente debe ser mayor a 0");

        if (noti.getTipoReceptor() == 'S' && noti.getIdSupervisor() <= 0)
            throw new Exception("El ID del supervisor debe ser mayor a 0");

        // Si pasa todas las validaciones, se procede a actualizar
        notiSQL.actualizar(noti);
    }
    
    public Notificacion obtenerPorId(int idNotificacion) throws Exception{
        if(idNotificacion<=0)
            throw new Exception("El id de la notificacion no es válido");
        Notificacion noti=notiSQL.obtener(idNotificacion);
        return noti;
    }
    
}
