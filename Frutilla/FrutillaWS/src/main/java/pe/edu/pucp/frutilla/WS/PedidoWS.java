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
    
    private  OrdenVentaService daoOrdenVenta; 
    private  LineaOrdenDeVentaService daoLineaOrdenDeVenta; 
    
    public PedidoWS(){
        daoOrdenVenta=new OrdenVentaService();
        daoLineaOrdenDeVenta=new LineaOrdenDeVentaService();
    }
    
    @WebMethod(operationName = "obtenerPedidos")
    public List<OrdenVenta> obtenerPedidos(@WebParam (name ="idEmpleado") int idEmpleado) {
        try{
            return daoOrdenVenta.listarOrdenesPorEmpleado(idEmpleado);
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
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
}
