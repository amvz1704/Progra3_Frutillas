/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.frutilla.WS;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import java.util.List;
import pe.edu.pucp.frutilla.logica.rrhh.EmpleadoService;
import pe.edu.pucp.frutilla.logica.venta.LineaOrdenDeVentaService;
import pe.edu.pucp.frutilla.logica.venta.OrdenVentaService;
import pe.edu.pucp.frutilla.models.rrhh.Empleado;
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
    public List<OrdenVenta> obtenerPedidosPorEmpleado(@WebParam (name ="idEmpleado") int idEmpleado) {
        try{
            return daoOrdenVenta.listarOrdenesPorEmpleado(idEmpleado);
        }catch(Exception ex){ 
            System.out.println(ex.getMessage()); 
        }
        return null;
    }
    
    @WebMethod(operationName = "obtenerPedidosPorCliente")
    public List<OrdenVenta> obtenerPedidosPorCliente(@WebParam (name ="idCliente") int idCliente){
        try{
            return daoOrdenVenta.listarOrdenesPorCliente(idCliente);
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
    
    @WebMethod(operationName = "actualizarOrden")
    public void actualizarOrden(@WebParam(name = "orden") OrdenVenta orden) {
        try {
            daoOrdenVenta.actualizarOrden(orden);
        } catch (Exception ex) {
           System.err.println("Error al actualizar pedido"  + ex.getMessage());
            throw new RuntimeException("No se pudo obtener el detalle del pedido.");
        }
    }
    
    @WebMethod(operationName = "obtenerPedidoPorId")
    public OrdenVenta obtenerPedidoPorId(@WebParam(name = "idOrdenVenta") int idOrden) {
        try {
            // Usamos el servicio de OrdenVenta para obtener el pedido completo
            OrdenVenta pedido = daoOrdenVenta.obtenerPedido(idOrden); // Asegúrate de tener el método en OrdenVentaService
            return pedido;
        } catch (Exception ex) {
            System.err.println("Error al obtener el pedido con ID " + idOrden + ": " + ex.getMessage());
            throw new RuntimeException("No se pudo obtener el pedido.");
        }
    }
}
