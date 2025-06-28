/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.frutilla.WS;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.frutilla.dto.ComprobanteDTO;
import pe.edu.pucp.frutilla.dto.OrdenVentaDTO;
import pe.edu.pucp.frutilla.logica.rrhh.EmpleadoService;
import pe.edu.pucp.frutilla.logica.venta.ComprobantePagoService;
import pe.edu.pucp.frutilla.logica.venta.LineaOrdenDeVentaService;
import pe.edu.pucp.frutilla.logica.venta.OrdenVentaService;
import pe.edu.pucp.frutilla.models.inventario.Producto;
import pe.edu.pucp.frutilla.models.rrhh.Empleado;
import pe.edu.pucp.frutilla.models.venta.ComprobantePago;
import pe.edu.pucp.frutilla.models.venta.EstadoVenta;
import pe.edu.pucp.frutilla.models.venta.LineaOrdenDeVenta;
import pe.edu.pucp.frutilla.models.venta.OrdenVenta;

/**
 *
 * @author Asus
 */

@WebService(serviceName = "PedidoWS")
public class PedidoWS {
    
    private final OrdenVentaService daoOrdenVenta; 
    private  final LineaOrdenDeVentaService daoLineaOrdenDeVenta; 
    
    public PedidoWS(){
        daoOrdenVenta=new OrdenVentaService();
        daoLineaOrdenDeVenta=new LineaOrdenDeVentaService();
    }
    
    @WebMethod(operationName = "obtenerPedidosPorEmpleado")
    public List<OrdenVentaDTO> obtenerPedidosPorEmpleado(@WebParam (name ="idEmpleado") int idEmpleado) {
        try{
            List<OrdenVenta> lista = daoOrdenVenta.listarOrdenesPorEmpleado(idEmpleado);
            List<OrdenVentaDTO> listaDTO = new ArrayList<>();
            for(int i=0;i<lista.size();i++){
                OrdenVentaDTO temp = new OrdenVentaDTO(lista.get(i));
                listaDTO.add(temp);
            }
            return listaDTO;
        }catch(Exception ex){ 
            System.out.println(ex.getMessage()); 
        }
        return null;
    }
    
    @WebMethod(operationName = "obtenerPedidosPorCliente")
    public List<OrdenVentaDTO> obtenerPedidosPorCliente(@WebParam (name ="idCliente") int idCliente){
        try{
            List<OrdenVenta> lista = daoOrdenVenta.listarOrdenesPorCliente(idCliente);
            List<OrdenVentaDTO> listaDTO = new ArrayList<>();
            for(int i=0;i<lista.size();i++){
                OrdenVentaDTO temp = new OrdenVentaDTO(lista.get(i));
                listaDTO.add(temp);
            }
            return listaDTO;
        }catch(Exception ex){ 
            System.out.println(ex.getMessage()); 
        }
        return null;
    }
    @WebMethod(operationName = "obtenerDetallePedido")
    public List<LineaOrdenDeVenta> obtenerDetallePedido(@WebParam(name = "idOrdenVenta") int idOrden) {
        try {
            return daoLineaOrdenDeVenta.listarPorOrden(idOrden);
        } catch (Exception ex) {
            System.err.println("Error al obtener el detalle del pedido con ID " + idOrden + ": " + ex.getMessage());
            throw new RuntimeException("No se pudo obtener el detalle del pedido.");
        }
    }
    
    @WebMethod(operationName = "agregarOrden")
    public void agregarOrden(@WebParam(name = "orden")OrdenVentaDTO orden){
        try{
            OrdenVenta ordenFinal = orden.convertirAOrdenVenta();
            daoOrdenVenta.agregarOrden(ordenFinal);
        } catch (Exception e){
             System.err.println("Error al crear pedido"  + e.getMessage());
            throw new RuntimeException("No se pudo crear pedido");
        }
    }
    
    @WebMethod(operationName = "actualizarOrden")
    public void actualizarOrden(@WebParam(name = "orden") OrdenVentaDTO orden) {
        try {
            OrdenVenta ordenFinal = orden.convertirAOrdenVenta();
            daoOrdenVenta.actualizarOrden(ordenFinal);
        } catch (Exception ex) {
           System.err.println("Error al actualizar pedido"  + ex.getMessage());
            throw new RuntimeException("No se pudo obtener el detalle del pedido.");
        }
    }
    
    @WebMethod(operationName = "insertarOrden")
    public int insertarOrden(
            @WebParam(name = "idOrden") int idOrden, 
            @WebParam(name = "comprobante") ComprobanteDTO comprobante) {
        try {
            
            if(!validarOrden(idOrden)){
                //eliminar orden de Venta 
                daoOrdenVenta.eliminarOrden(idOrden);
                return -1; //se elimina la ordenPedido por falta de stock 
            }
            
            //creamos el comprobante y lo subimos a la BD 
            ComprobantePago comprobanteFinal = comprobante.convertirAComprobante(); 
            
            ComprobantePagoService daoComprobante = new ComprobantePagoService(); 
            daoComprobante.agregar(comprobanteFinal); 
            //actualizamos con el id
            OrdenVenta ordenFinal = daoOrdenVenta.obtenerPedido(idOrden); 
            
            ordenFinal.setIdComprobante(comprobanteFinal.getIdComprobante());
            ordenFinal.setEstado(EstadoVenta.POR_ENTREGAR);
            
            daoOrdenVenta.actualizarOrden(ordenFinal); //actualizas
            
            return 1; 
            
            
        } catch (Exception ex) {
           System.err.println("Error al actualizar pedido"  + ex.getMessage());
            throw new RuntimeException("No se pudo obtener el detalle del pedido.");
            
        }
       
    }
    
    private boolean validarOrden(int idOrden) throws SQLException{
        List<LineaOrdenDeVenta> resultado = daoLineaOrdenDeVenta.listarPorOrden(idOrden);
        
        for(LineaOrdenDeVenta lineaProducto: resultado){
            if(!validarStock(lineaProducto.getProducto(), lineaProducto.getCantidad())){
                return false; 
            }
        }
        
        return true; 
    }
    
    private boolean validarStock(Producto producto, int pedidoCantidad){
        return pedidoCantidad <= producto.getStock(); //regresar verdad si esta en el rango 
    }
    
    
    @WebMethod(operationName = "obtenerPedidoPorId")
    public OrdenVentaDTO obtenerPedidoPorId(@WebParam(name = "idOrdenVenta") int idOrden) {
        try {
            OrdenVenta pedido = daoOrdenVenta.obtenerPedido(idOrden);
            OrdenVentaDTO pedidoFinal = new OrdenVentaDTO(pedido);
            return pedidoFinal;
        } catch (Exception ex) {
            System.err.println("Error al obtener el pedido con ID " + idOrden + ": " + ex.getMessage());
        }
        return null;
        
    }
    
    @WebMethod(operationName = "obtenerDetallePedidoList")
    public List<LineaOrdenDeVenta> obtenerDetallePedidoList(@WebParam(name = "idOrdenVenta") int idOrden) {
        try {
            List<LineaOrdenDeVenta> resultado = daoLineaOrdenDeVenta.listarPorOrden(idOrden);
            return (resultado != null) ? resultado : new ArrayList<>();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new ArrayList<>(); // ⚠️ ← aquí también
        }
    }
    
    @WebMethod(operationName = "listarPedidoPorLocal")
    public List<OrdenVentaDTO> listarPedidoPorLocal (@WebParam(name = "idLocal")int idLocal){
        try{
            List<OrdenVenta> lista = daoOrdenVenta.listarOrdenesPorLocal(idLocal);
            List<OrdenVentaDTO> listaDTO = new ArrayList<>();
            for(int i=0;i<lista.size();i++){
                OrdenVentaDTO temp = new OrdenVentaDTO(lista.get(i));
                listaDTO.add(temp);
            }
            return listaDTO;
        } catch(Exception e) {
            System.err.println("Error al listar el pedido con local " + idLocal 
                    + ": " + e.getMessage());
        }
        return null;
    }
    
    @WebMethod (operationName = "listarPedidoPorLocalCliente")
    public List<OrdenVentaDTO> listarPedidoPorLocalCliente(@WebParam(name = "idLocal")
    int idLocal,@WebParam(name = "idCliente")int idCliente){
        try{
            List<OrdenVenta> lista = daoOrdenVenta.listarOrdenesPorLocalCliente(idLocal, idCliente);
            List<OrdenVentaDTO> listaDTO = new ArrayList<>();
            for(int i=0;i<lista.size();i++){
                OrdenVentaDTO temp = new OrdenVentaDTO(lista.get(i));
                listaDTO.add(temp);
            }
            return listaDTO;
        } catch (Exception ex){
            System.err.println("Error al listar el pedido con local y cliente " + idLocal 
                    + ": " + ex.getMessage());
        }
        return null;
    }
    
    @WebMethod(operationName = "generarOrdenConLineas")
    public int generarOrdenConLineas(
            @WebParam(name = "orden") OrdenVentaDTO orden,
            @WebParam(name = "lineas") List<LineaOrdenDeVenta> lineas) {
        try {
            OrdenVenta ordenFinal = orden.convertirAOrdenVenta();
            daoOrdenVenta.agregarOrden(ordenFinal); // actualiza el ID
            int idOrden = ordenFinal.getIdOrdenVenta();
            // 2. Agregar cada línea asociada a la orden
            for (LineaOrdenDeVenta linea : lineas) {
                linea.setIdOrdenVenta(idOrden);
                daoLineaOrdenDeVenta.registrarLinea(linea,ordenFinal.getIdLocal());
            }
            return idOrden;
        } catch (Exception e) {
            System.err.println("Error al generar la orden con líneas: " + e.getMessage());
            return -1;
        }
    }
}
