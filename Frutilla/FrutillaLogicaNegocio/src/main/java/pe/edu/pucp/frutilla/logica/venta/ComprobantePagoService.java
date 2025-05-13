package pe.edu.pucp.frutilla.logica.venta;

import pe.edu.pucp.frutilla.models.local.Notificacion;
import pe.edu.pucp.frutilla.models.venta.ComprobantePago;
import pe.edu.pucp.frutilla.crud.dao.venta.ComprobantePagoDAO;
import pe.edu.pucp.frutilla.crud.mysql.local.NotificacionMySQL;
import pe.edu.pucp.frutilla.crud.mysql.venta.ComprobantePagoMySQL;

public class ComprobantePagoService {
    private ComprobantePagoMySQL comprobanteSQL;
    
    public ComprobantePagoService (){
        comprobanteSQL=new ComprobantePagoMySQL();
    }
    
    public void agregar(ComprobantePago comp) throws Exception{
        if(comp==null)
            throw new Exception("EL comprobante ha ingresado nulo");
        
        if(comp.getNumeroArticulos()<=0)
            throw new Exception("El numero de articulos no puede ser vacio o menor a 0");
        if(comp.getSubtotal()<=0)
            throw new Exception("El subtotal del comprobante no puede menor a 0");
        if(comp.getMontoIGV()<=0)
            throw new Exception("El monto IGV del comprobante no puede menor a 0");
        if(comp.getTotal()<=0)
            throw new Exception("El total del copmrobante no puede menor a 0");
        if(comp.getFecha()==null)
            throw new Exception("El total del copmrobante no puede menor a 0");
        if(comp.getFormaPago().name()==null||comp.getFormaPago().name().trim().isEmpty())
            throw new Exception("El nombre de la Forma de Pago no puede ser vacio");   
        comprobanteSQL.agregar(comp);
        Notificacion noti = comprobanteSQL.crearNotificacionCompra(comp);
        NotificacionMySQL notiSQL = new NotificacionMySQL();
        notiSQL.agregar(noti);
    }
    
    public void actualizar(ComprobantePago comp) throws Exception{
        if(comp==null)
            throw new Exception("EL comprobante ha ingresado nulo");
        if(comp.getNumeroArticulos()<=0)
            throw new Exception("El numero de articulos no puede ser vacio o menor a 0");
        if(comp.getSubtotal()<=0)
            throw new Exception("El subtotal del comprobante no puede menor a 0");
        if(comp.getMontoIGV()<=0)
            throw new Exception("El monto IGV del comprobante no puede menor a 0");
        if(comp.getTotal()<=0)
            throw new Exception("El total del copmrobante no puede menor a 0");
        if(comp.getFecha()==null)
            throw new Exception("El total del copmrobante no puede menor a 0");
        if(comp.getFormaPago().name()==null||comp.getFormaPago().name().trim().isEmpty())
            throw new Exception("El nombre de la Forma de Pago no puede ser vacio");     
        comprobanteSQL.agregar(comp);
    }
    
    public void eliminar(int idComprobante) throws Exception{
        if(idComprobante<=0)
            throw new Exception("El id del comprobante de Pago no es válido");
        comprobanteSQL.eliminar(idComprobante);
    }
    
    public ComprobantePago obtenerPorId(int idComprobante) throws Exception{
        if(idComprobante<=0)
            throw new Exception("El id del comprobante de pago no es válido");
        ComprobantePago comp=comprobanteSQL.obtener(idComprobante);
        return comp;
    }
    
}
