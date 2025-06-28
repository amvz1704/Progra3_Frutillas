
package pe.edu.pucp.frutilla.WS;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.xml.ws.WebServiceException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import pe.edu.pucp.frutilla.dto.ComprobanteDTO;
import pe.edu.pucp.frutilla.logica.inventario.ProductoService;
import pe.edu.pucp.frutilla.logica.venta.ComprobantePagoService;
import pe.edu.pucp.frutilla.logica.venta.LineaOrdenDeVentaService;
import pe.edu.pucp.frutilla.models.inventario.Producto;
import pe.edu.pucp.frutilla.models.venta.ComprobantePago;
import pe.edu.pucp.frutilla.models.venta.LineaOrdenDeVenta;

@WebService(serviceName = "ComprobanteWS")
public class ComprobanteWS {

    private final ComprobantePagoService comprobanteService;
    
    public ComprobanteWS(){
        comprobanteService = new ComprobantePagoService();
    }
    

    
    @WebMethod(operationName = "actualizarComprobante")
    public void actualizarComprobante(@WebParam(name = "Comprobante") ComprobanteDTO comp) {
        try {
            ComprobantePago enviado = comp.convertirAComprobante();
            comprobanteService.actualizar(enviado);
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
    public ComprobanteDTO obtenerComprobante(@WebParam(name = "idComprobante") int idComprobante) {
        try {
            ComprobantePago base=comprobanteService.obtenerPorId(idComprobante);
            ComprobanteDTO enviado=new ComprobanteDTO(base);
            return enviado;
        } catch (Exception ex) {
            throw new WebServiceException("Error al obtener Comprobante: " + ex.getMessage());
        }
    }
    
    //Lo necesito para obtener producto al agregar
    
    @WebMethod(operationName = "obtenerProductoPorId")
    public Producto obtenerProductoPorId(@WebParam(name = "idProducto") int idProducto) {
        ProductoService productoService = new ProductoService();
        try {
            // Usamos el servicio de Producto para obtener el Producto completo
            Producto pedido = productoService.obtenerPorId(idProducto); // Asegúrate de tener el método en ProductoService
            return pedido;
        } catch (Exception ex) {
            System.err.println("Error al obtener el producto con ID " + idProducto + ": " + ex.getMessage());
        }
        return null;
    }
    
    @WebMethod(operationName = "obtenerLineasPorIdComprobante") 
    public List<LineaOrdenDeVenta> obtenerLineasPorIdComprobante(@WebParam(name = "idComprobante") int idComprobante) {
        LineaOrdenDeVentaService daoLinea = new LineaOrdenDeVentaService(); 
        try {
            return daoLinea.listarPorOrden(idComprobante);
        } catch (Exception ex) {
            throw new WebServiceException("Error al obtener las lineas del comprobante: " + ex.getMessage());
        }
    }
//    @WebMethod(operationName = "listarComprobantes")
//    public List<ComprobantePago> listarComprobantes() {
//        try {
//            return comprobanteService.listarComprobantes();
//        } catch (Exception ex) {
//            throw new WebServiceException("Error al listar comprobantes: " + ex.getMessage());
//        }
//    }
}
