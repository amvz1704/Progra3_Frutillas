/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.frutilla.logica.venta;

import java.sql.SQLDataException;
import pe.edu.pucp.frutilla.models.venta.OrdenVenta;
import pe.edu.pucp.frutilla.models.venta.LineaOrdenDeVenta;


import pe.edu.pucp.frutilla.crud.mysql.venta.OrdenVentaMySQL;
import pe.edu.pucp.frutilla.crud.mysql.venta.LineaOrdenDeVentaMySQL;
import java.sql.SQLException;
import java.util.List;
import pe.edu.pucp.frutilla.logica.inventario.InventarioService;
import pe.edu.pucp.frutilla.models.inventario.Producto;

public class OrdenVentaService {

    private OrdenVentaMySQL ordenVentaMySQL = new OrdenVentaMySQL();
    private LineaOrdenDeVentaMySQL lineaOrdenDeVentaMySQL = new LineaOrdenDeVentaMySQL();
    private InventarioService inventarioService = new InventarioService();

    // Registrar orden de venta con sus líneas
    public void registrarOrdenConLineas(OrdenVenta orden, List<LineaOrdenDeVenta> lineas) throws SQLException, Exception {
        //Validaciones
       
        
        if (lineas == null || lineas.isEmpty()) {
            throw new IllegalArgumentException("La orden debe tener al menos una línea.");
        }
        
        // Validación de stock antes de registrar la orden
        for (LineaOrdenDeVenta linea : lineas) {
            Producto producto = linea.getProducto();
            int stockDisponible = linea.getProducto().getStock();
            if (linea.getCantidad() > stockDisponible) {
                throw new IllegalArgumentException("No hay stock disponible para el producto: " +
                        producto.getNombre());
            }
        }

        ordenVentaMySQL.agregar(orden);

        for (LineaOrdenDeVenta linea : lineas) {
            linea.setIdOrdenVenta(orden.getIdOrdenVenta());
            lineaOrdenDeVentaMySQL.agregar(linea);

            // Actualizar stock
            Producto producto = linea.getProducto();
            int stockActual = inventarioService.obtenerStockPorId(producto.getIdProducto(), orden.getIdLocal());
            producto.setStock(stockActual - linea.getCantidad());
            inventarioService.actualizar(producto, orden.getIdLocal());
        }
    }
    
    //insertar la orden
    public void agregarOrden(OrdenVenta ordenVenta){
        if (ordenVenta == null) 
            throw new IllegalArgumentException("La orden no puede ser nula.");
        if(ordenVenta.getFecha()==null)
            throw new IllegalArgumentException("La fecha no puede ser nula");
        if(ordenVenta.getHoraFinEntrega()==null)
            throw new IllegalArgumentException("La hora no puede ser nula");
        if(ordenVenta.getDescripcion()==null||ordenVenta.getDescripcion().trim().isEmpty())
            throw new IllegalArgumentException("La descripcion no puede ser vacia");
        if(ordenVenta.getMontoTotal()<=0)
            throw new IllegalArgumentException("El monto no puede ser negativa");
        if(ordenVenta.getEstado()==null)
            throw new IllegalArgumentException("El estado no puede estar vacio");
        if(ordenVenta.getIdLocal()<=0)
            throw new IllegalArgumentException("El id de local no puede ser negativa");
        if(ordenVenta.getIdCliente()<=0)
            throw new IllegalArgumentException("El id de local no puede ser negativa");
        ordenVentaMySQL.agregar(ordenVenta);
    }

    // Actualizar orden y sus líneas
    public void actualizarOrdenConLineas(OrdenVenta orden, List<LineaOrdenDeVenta> nuevasLineas) throws SQLException {
        ordenVentaMySQL.actualizar(orden);

        for (LineaOrdenDeVenta linea : nuevasLineas) {
            if (linea.getIdLineaVenta() == 0) {
                linea.setIdOrdenVenta(orden.getIdOrdenVenta());
                lineaOrdenDeVentaMySQL.agregar(linea);
            } else {
                lineaOrdenDeVentaMySQL.actualizar(linea);
            }
        }
    }
    
     public void actualizarOrden (OrdenVenta ordenVenta) throws SQLException{
         if (ordenVenta == null) 
            throw new IllegalArgumentException("La orden no puede ser nula.");
        if(ordenVenta.getFecha()==null)
            throw new IllegalArgumentException("La fecha no puede ser nula");
        if(ordenVenta.getHoraFinEntrega()==null)
            throw new IllegalArgumentException("La hora no puede ser nula");
        if(ordenVenta.getDescripcion()==null||ordenVenta.getDescripcion().trim().isEmpty())
            throw new IllegalArgumentException("La descripcion no puede ser vacia");
        if(ordenVenta.getMontoTotal()<=0)
            throw new IllegalArgumentException("El monto no puede ser negativa");
        if(ordenVenta.getEstado()==null)
            throw new IllegalArgumentException("El estado no puede estar vacio");
        if(ordenVenta.getIdLocal()<=0)
            throw new IllegalArgumentException("El id de local no puede ser negativa");
        if(ordenVenta.getIdCliente()<=0)
            throw new IllegalArgumentException("El id de local no puede ser negativa");
        ordenVentaMySQL.actualizar(ordenVenta);
    }
     
    // Eliminar orden y sus líneas
    public void eliminarOrden(int idOrdenVenta) throws SQLException {
        lineaOrdenDeVentaMySQL.eliminarPorId(idOrdenVenta);
        ordenVentaMySQL.eliminar(idOrdenVenta);
    }

    // Listar todas las órdenes
    public List<OrdenVenta> listarTodasLasOrdenes() throws SQLException {
        return ordenVentaMySQL.listarTodos();
    }

    // Listar órdenes por cliente
    public List<OrdenVenta> listarOrdenesPorCliente(int idCliente) throws SQLException {
        if (idCliente<=0)
            throw new SQLDataException("el id de cliente no puede ser 0 o negativo");
        return ordenVentaMySQL.listarPorCliente(idCliente);
    }

    // Listar órdenes por empleado
    public List<OrdenVenta> listarOrdenesPorEmpleado(int idEmpleado) throws SQLException {
        if (idEmpleado<=0)
            throw new SQLDataException("el id de empleado no puede ser 0 o negativo");
        return ordenVentaMySQL.listarPorEmpleado(idEmpleado);
    }

    // Listar órdenes por local
    public List<OrdenVenta> listarOrdenesPorLocal(int idLocal) throws SQLException {
        if (idLocal<=0)
            throw new SQLDataException("el id de local no puede ser 0 o negativo");
        return ordenVentaMySQL.listarPorLocal(idLocal);
    }
    public OrdenVenta obtenerPedido (int id) throws SQLException {
        if (id<=0)
            throw new SQLDataException("el id de la orden no puede ser 0 o negativo");
        return ordenVentaMySQL.obtenerPorId(id);
    }
    
    public List<OrdenVenta> listarOrdenesPorLocalCliente(int idLocal,int idCliente)
            throws SQLException{
        if (idLocal<=0)
            throw new SQLDataException("el id de local no puede ser 0 o negativo");
         if (idCliente<=0)
            throw new SQLDataException("el id de cliente no puede ser 0 o negativo");
         return ordenVentaMySQL.listarPorClienteLocal(idCliente, idLocal);
    }
}
