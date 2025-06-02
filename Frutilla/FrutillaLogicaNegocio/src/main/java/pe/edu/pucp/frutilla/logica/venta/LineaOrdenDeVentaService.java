/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.frutilla.logica.venta;

import java.sql.SQLException;
import java.util.List;
import pe.edu.pucp.frutilla.crud.mysql.venta.LineaOrdenDeVentaMySQL;
import pe.edu.pucp.frutilla.models.venta.LineaOrdenDeVenta;


public class LineaOrdenDeVentaService {
    private LineaOrdenDeVentaMySQL lineaOrdenVentaMySQL;

    public LineaOrdenDeVentaService() {
        lineaOrdenVentaMySQL = new LineaOrdenDeVentaMySQL();
    }
    
    // Registrar una línea
    public void registrarLinea(LineaOrdenDeVenta linea) throws SQLException {
        if (linea.getCantidad() > linea.getProducto().getStock()) {
            throw new IllegalArgumentException("Stock insuficiente para el producto: " +
                                               linea.getProducto().getNombre());
        }

        linea.actualizarSubtotal(); // calcula cantidad * precio
        lineaOrdenVentaMySQL.agregar(linea);
    }
    
    // Obtener línea por ID
    public LineaOrdenDeVenta obtener(int idLinea) {
        return lineaOrdenVentaMySQL.obtener(idLinea);
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
}

