/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.frutilla.logica.venta;

import java.sql.SQLException;
import java.util.List;
import pe.edu.pucp.frutilla.crud.mysql.inventario.InventarioMySQL;
import pe.edu.pucp.frutilla.crud.mysql.venta.LineaOrdenDeVentaMySQL;
import pe.edu.pucp.frutilla.models.inventario.Producto;
import pe.edu.pucp.frutilla.models.venta.LineaOrdenDeVenta;


public class LineaOrdenDeVentaService {
    private LineaOrdenDeVentaMySQL lineaOrdenVentaMySQL;
    private InventarioMySQL inventarioMySQL;

    public LineaOrdenDeVentaService() {
        lineaOrdenVentaMySQL = new LineaOrdenDeVentaMySQL();
        inventarioMySQL = new  InventarioMySQL();
    }
    
    // Registrar una línea
    public void registrarLinea(LineaOrdenDeVenta linea,int idLocal) throws SQLException {
        int stock = inventarioMySQL.obtenerInventarioPorId(linea.getProducto().getIdProducto(), idLocal);
        if (linea.getCantidad() > stock) {
            throw new IllegalArgumentException("Stock insuficiente para el producto: " +
                                               linea.getProducto().getNombre());
        }

        linea.actualizarSubtotal(); // calcula cantidad * precio
        lineaOrdenVentaMySQL.agregar(linea);
    }
    
    // Obtener línea por ID
    public LineaOrdenDeVenta obtener(int idOrden) {
        return lineaOrdenVentaMySQL.obtener(idOrden);
    }
    
    // Listar todas las líneas
    public List<LineaOrdenDeVenta> listarTodas() {
        return lineaOrdenVentaMySQL.listarTodos();
    }

    // Actualizar línea
    public void actualizar(LineaOrdenDeVenta linea) {
        if (linea.getCantidad() > linea.getProducto().getStock()) {
            throw new IllegalArgumentException("Stock insuficiente para el producto: " +
                                               linea.getProducto().getNombre());
        }

        linea.actualizarSubtotal();
        lineaOrdenVentaMySQL.actualizar(linea);
    }

    // Eliminar línea por ID
    public void eliminar(int idLinea) {
        lineaOrdenVentaMySQL.eliminar(idLinea);
    }
    
    //linea por orden
    public List<LineaOrdenDeVenta> listarPorOrden(int idOrden) throws SQLException {
        return lineaOrdenVentaMySQL.listarPorOrden(idOrden);
    }
}
