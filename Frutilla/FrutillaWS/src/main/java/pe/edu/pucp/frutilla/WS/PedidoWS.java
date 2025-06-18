/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.frutilla.WS;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
    
    @WebMethod(operationName = "agregarOrden")
    public void agregarOrden(@WebParam(name = "orden")OrdenVenta orden){
        try{
            daoOrdenVenta.agregarOrden(orden);
        } catch (Exception e){
             System.err.println("Error al crear pedido"  + e.getMessage());
            throw new RuntimeException("No se pudo crear pedido");
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
    public List<OrdenVenta> listarPedidoPorLocal (@WebParam(name = "idLocal")int idLocal){
        try{
            return daoOrdenVenta.listarOrdenesPorLocal(idLocal);
        } catch(Exception e) {
            System.err.println("Error al listar el pedido con local " + idLocal 
                    + ": " + e.getMessage());
        }
        return null;
    }
    
    @WebMethod (operationName = "listarPedidoPorLocalCliente")
    public List<OrdenVenta> listarPedidoPorLocalCliente(@WebParam(name = "idLocal")
    int idLocal,@WebParam(name = "idCliente")int idCliente){
        try{
            return daoOrdenVenta.listarOrdenesPorLocalCliente(idLocal, idCliente);
        } catch (Exception ex){
            System.err.println("Error al listar el pedido con local y cliente " + idLocal 
                    + ": " + ex.getMessage());
        }
        return null;
    }
    
    @WebMethod(operationName = "generarOrdenConLineas")
    public int generarOrdenConLineas(
            @WebParam(name = "orden") OrdenVenta orden,
            @WebParam(name = "lineas") List<LineaOrdenDeVenta> lineas) {
        try {
            // 1. Agregar la orden
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false); // para evitar fechas inválidas tipo 2024-02-30
            // Convierte el String fecha a Date
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate fechaDate =  LocalDate.parse(orden.getFechaStr(), formatter);
            orden.setFecha(fechaDate);
            SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm:ss");
            sdf.setLenient(false);
            DateTimeFormatter formatterHora=DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalTime horaFinal = LocalTime.parse(orden.getHoraStr(),formatterHora);
            orden.setHoraFinEntrega(horaFinal);
            daoOrdenVenta.agregarOrden(orden); // actualiza el ID
            int idOrden = orden.getIdOrdenVenta();
            // 2. Agregar cada línea asociada a la orden
            for (LineaOrdenDeVenta linea : lineas) {
                linea.setIdOrdenVenta(idOrden);
                daoLineaOrdenDeVenta.registrarLinea(linea,orden.getIdLocal());
            }

            return idOrden;
        } catch (Exception e) {
            System.err.println("Error al generar la orden con líneas: " + e.getMessage());
            return -1;
        }
    }
}
