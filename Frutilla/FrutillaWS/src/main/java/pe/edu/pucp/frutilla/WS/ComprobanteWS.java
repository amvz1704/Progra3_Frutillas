
package pe.edu.pucp.frutilla.WS;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.xml.ws.WebServiceException;
import java.util.List;
import pe.edu.pucp.frutilla.logica.venta.ComprobantePagoService;
import pe.edu.pucp.frutilla.models.venta.ComprobantePago;

@WebService(serviceName = "ComprobanteWS")
public class ComprobanteWS {

    private final ComprobantePagoService comprobanteService;
    
    public ComprobanteWS(){
        comprobanteService = new ComprobantePagoService();
    }
    
    
    
    @WebMethod(operationName = "agregarComprobante")
    public void agregarComprobante(@WebParam(name = "Comprobante") ComprobantePago comp) {
        try {
            comprobanteService.agregar(comp);
        } catch (Exception ex) {
            throw new WebServiceException("Error al agregar el comprobante: " + ex.getMessage());
        }
    }
    
    @WebMethod(operationName = "actualizarComprobante")
    public void actualizarComprobante(@WebParam(name = "Comprobante") ComprobantePago comp) {
        try {
            comprobanteService.actualizar(comp);
        } catch (Exception ex) {
            throw new WebServiceException("Error al actualizar el comprobante: " + ex.getMessage());
        }
    }
    
    @WebMethod(operationName = "eliminarComprobante")
    public void eliminarComprobante(int idComprobante) {
        try {
            comprobanteService.eliminar(idComprobante);
        } catch (Exception ex) {              
            throw new WebServiceException("Error al eliminar comprobante: " + ex.getMessage());
        }
    }
    
    @WebMethod(operationName = "obtenerComprobante") 
    public ComprobantePago obtenerComprobante(@WebParam(name = "idComprobante") int idComprobante) {
        try {
            return comprobanteService.obtenerPorId(idComprobante);
        } catch (Exception ex) {
            throw new WebServiceException("Error al obtener Comprobante: " + ex.getMessage());
        }
    }
    @WebMethod(operationName = "listarComprobantes")
    public List<ComprobantePago> listarComprobantes() {
        try {
            return comprobanteService.listarComprobantes();
        } catch (Exception ex) {
            throw new WebServiceException("Error al listar comprobantes: " + ex.getMessage());
        }
    }
}
