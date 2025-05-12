/**
 *
 * @author  Gabriel Medrano
 * Consultas: a20200948@pucp.edu.pe
 * Telf: 989299092
 */

package pe.edu.pucp.frutilla.crud.mysql.venta;

import pe.edu.pucp.frutilla.models.inventario.Producto;
import pe.edu.pucp.frutilla.models.venta.LineaOrdenDeVenta;
import pe.edu.pucp.frutilla.crud.mysql.inventario.ProductoMySQL;
import pe.edu.pucp.frutilla.config.DBManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.frutilla.crud.mysql.BaseDAOImpl;

public class LineaOrdenDeVentaMySQL extends BaseDAOImpl<LineaOrdenDeVenta> {

    @Override
    protected String getInsertQuery() {
        return "CALL INSERTAR_LINEA_ORDEN_VENTA(?, ?, ?, ?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "CALL ACTUALIZAR_LINEA_ORDEN_VENTA(?, ?, ?, ?)";
    }

    @Override
    protected String getDeleteQuery() {
        return "CALL ELIMINAR_LINEA_ORDEN_VENTA(?)";
    }

    //
    @Override 
    protected String getSelectByIdQuery() {
        // No se usa individualmente por ID
        return null;
    }

    @Override
    protected String getSelectAllQuery() {
        // No se usa obtener todos
        return null;
    }
    

    @Override
    protected void setInsertParameters(PreparedStatement ps, LineaOrdenDeVenta entity) throws SQLException {
        ps.setInt(1, entity.getIdOrdenVenta());
        ps.setInt(2, entity.getCantidad());
        ps.setDouble(3, entity.getSubtotal());
        ps.setInt(4, entity.getProducto().getIdProducto());
    }

    @Override
    protected void setUpdateParameters(PreparedStatement ps, LineaOrdenDeVenta entity) throws SQLException {
        ps.setInt(1, entity.getIdLineaVenta());
        ps.setInt(2, entity.getCantidad());
        ps.setDouble(3, entity.getSubtotal());
        ps.setInt(4, entity.getProducto().getIdProducto());
    }
    
    public void eliminarPorId(int idLineaVenta) throws SQLException {
        String query = getDeleteQuery();
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, idLineaVenta);
            ps.executeUpdate();
        }
    }

    @Override
    protected void setId(LineaOrdenDeVenta entity, Integer id) {
        entity.setIdLineaVenta(id);
    }

    @Override
    protected LineaOrdenDeVenta createFromResultSet(ResultSet rs) throws SQLException {
        LineaOrdenDeVenta linea = new LineaOrdenDeVenta();
        linea.setIdLineaVenta(rs.getInt("idLineaOrdenVenta"));
        linea.setCantidad(rs.getInt("cantidad"));
        linea.setSubtotal(rs.getDouble("subTotal"));

        ProductoMySQL productoDAO = new ProductoMySQL();
        Producto producto = productoDAO.obtener(rs.getInt("idProducto"));
        linea.setProducto(producto);

        return linea;
    }

    // Método personalizado para listar por ID de orden
    public List<LineaOrdenDeVenta> listarPorOrden(int idOrdenVenta) throws SQLException {
        List<LineaOrdenDeVenta> lineas = new ArrayList<>();
        String query = "CALL LISTAR_LINEAS_X_ORDEN(?)";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, idOrdenVenta);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lineas.add(createFromResultSet(rs));
                }
            }
        }
        return lineas;
    }
}

