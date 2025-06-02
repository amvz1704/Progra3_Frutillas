/**
 *
 * @author  Gabriel Medrano
 * Consultas: a20200948@pucp.edu.pe
 * Telf: 989299092
 */

package pe.edu.pucp.frutilla.crud.mysql.venta;

import pe.edu.pucp.frutilla.models.venta.OrdenVenta;
import pe.edu.pucp.frutilla.models.venta.EstadoVenta;
import pe.edu.pucp.frutilla.models.venta.LineaOrdenDeVenta;
import pe.edu.pucp.frutilla.config.DBManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDateTime;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.ArrayList;
import java.time.*; 
import java.util.List;
import pe.edu.pucp.frutilla.crud.mysql.BaseDAOImpl;


public class OrdenVentaMySQL extends BaseDAOImpl<OrdenVenta> {
    
    //Uso los metodos sobrecargados de Query
    @Override
    protected String getInsertQuery() {
        return "CALL INSERTAR_ORDEN_VENTA(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "CALL ACTUALIZAR_ORDEN_VENTA(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected String getDeleteQuery() {
        return "CALL ELIMINAR_ORDEN_VENTA(?)";
    }

    @Override
    protected String getSelectByIdQuery() {
        return "CALL ORDEN_VENTA_LISTAR_X_ID(?)";
    }

    @Override
    protected String getSelectAllQuery() {
        return "CALL ORDEN_VENTA_LISTAR()";
    }
    //----------------------------------------------------------------------
    //----------------------------------------------------------------------
    
    
    //Insertar
    @Override
    protected void setInsertParameters(PreparedStatement ps, OrdenVenta entity) throws SQLException {
        ps.setDate(1, Date.valueOf(entity.getFecha()));
        ps.setTime(2, Time.valueOf(entity.getHoraFinEntrega()));
        ps.setString(3, entity.getDescripcion());
        ps.setDouble(4, entity.getMontoTotal());
        ps.setBoolean(5, entity.getEntregado());
        ps.setString(6, entity.getEstado().name());
        ps.setInt(7, entity.getIdLocal());
        ps.setInt(8, entity.getIdCliente());
        ps.setInt(9, entity.getIdEmpleado());
    }

    @Override
    protected void setUpdateParameters(PreparedStatement ps, OrdenVenta entity) throws SQLException {
        ps.setInt(1, entity.getIdOrdenVenta());
        ps.setDate(2, Date.valueOf(entity.getFecha()));
        ps.setTime(3, Time.valueOf(entity.getHoraFinEntrega()));
        ps.setString(4, entity.getDescripcion());
        ps.setDouble(5, entity.getMontoTotal());
        ps.setBoolean(6, entity.getEntregado());
        ps.setString(7, entity.getEstado().name());
        ps.setInt(8, entity.getIdLocal());
        ps.setInt(9, entity.getComprobantePago().getIdComprobante());
        ps.setInt(10, entity.getIdCliente());
        ps.setInt(11, entity.getIdEmpleado());
    }
    
    
    //Eliminar por ID
    public void eliminarPorId(int idOrdenVenta) throws SQLException {
        String query = getDeleteQuery();
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
                ps.setInt(1, idOrdenVenta);
                ps.executeUpdate();
            }
    }
    
    //Asignar el id generado
    @Override
    protected void setId(OrdenVenta entity, Integer id) {
        entity.setIdOrdenVenta(id);
    }
    
    //Resultado
    @Override
    protected OrdenVenta createFromResultSet(ResultSet rs) throws SQLException {
        OrdenVenta ordenVenta = new OrdenVenta();
        ordenVenta.setIdOrdenVenta(rs.getInt("idOrdenVenta"));
        ordenVenta.setDescripcion(rs.getString("descripcion"));
        ordenVenta.setMontoTotal(rs.getDouble("montoTotal"));
        ordenVenta.setEntregado(rs.getBoolean("entregado"));
        ordenVenta.setEstado(EstadoVenta.valueOf(rs.getString("estadoVenta")));
        return ordenVenta;
    }

    // Métodos adicionales para listar por cliente, empleado, local, id , todos
    
    public List<OrdenVenta> listarPorCliente(int idCliente) {
        List<OrdenVenta> ordenes = new ArrayList<>();
        String query = "{CALL ORDEN_VENTA_LISTAR_X_CLIENTE(?)}";

        try (Connection con = DBManager.getInstance().getConnection();
             CallableStatement cs = con.prepareCall(query)) {

            cs.setInt(1, idCliente);
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    OrdenVenta orden = createFromResultSet(rs);
                    ordenes.add(orden);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al listar órdenes de venta por cliente", e);
        }

        return ordenes;
    }

    public List<OrdenVenta> listarPorLocal(int idLocal) {
        List<OrdenVenta> ordenes = new ArrayList<>();
        String query = "{CALL ORDEN_VENTA_LISTAR_X_LOCAL(?)}";

        try (Connection con = DBManager.getInstance().getConnection();
             CallableStatement cs = con.prepareCall(query)) {

            cs.setInt(1, idLocal);
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    OrdenVenta orden = createFromResultSet(rs);
                    ordenes.add(orden);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al listar órdenes de venta por local", e);
        }

        return ordenes;
    }

    public List<OrdenVenta> listarPorEmpleado(int idEmpleado) throws SQLException {
        List<OrdenVenta> ordenes = new ArrayList<>();
        String query = "CALL ORDEN_VENTA_LISTAR_X_EMPLEADO(?)";
        try (Connection con = DBManager.getInstance().getConnection();
             CallableStatement cs = con.prepareCall(query)) {

            cs.setInt(1, idEmpleado);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                OrdenVenta orden = createFromResultSet(rs);
                ordenes.add(orden);
            }
        }
        return ordenes;
    }
    
    public OrdenVenta obtenerPorId(int idOrdenVenta) throws SQLException {
        OrdenVenta ordenVenta = null;
        String query = "CALL ORDEN_VENTA_LISTAR_X_ID(?)";
            try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, idOrdenVenta);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ordenVenta = createFromResultSet(rs);
                    ordenVenta.setFecha(rs.getDate("fecha").toLocalDate());
                    ordenVenta.setHoraFinEntrega(rs.getTime("horaFinEntrega").toLocalTime());
                    ordenVenta.setIdLocal(rs.getInt("idLocal"));
                    ordenVenta.setIdComprobante(rs.getInt("idComprobante"));
                    ordenVenta.setIdCliente(rs.getInt("idCliente"));
                    ordenVenta.setIdEmpleado(rs.getInt("idEmpleado"));
                }
            }
        }
        return ordenVenta;
    }   
    
    public List<OrdenVenta> listarTodas() throws SQLException {
        List<OrdenVenta> ordenes = new ArrayList<>();
        String query = "CALL ORDEN_VENTA_LISTAR()";
        try (Connection con = DBManager.getInstance().getConnection();
         PreparedStatement ps = con.prepareStatement(query);
         ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                OrdenVenta orden = createFromResultSet(rs);
                orden.setFecha(rs.getDate("fecha").toLocalDate());
                orden.setHoraFinEntrega(rs.getTime("horaFinEntrega").toLocalTime());
                orden.setIdLocal(rs.getInt("idLocal"));
                orden.setIdComprobante(rs.getInt("idComprobante"));
                orden.setIdCliente(rs.getInt("idCliente"));
                orden.setIdEmpleado(rs.getInt("idEmpleado"));
                ordenes.add(orden);
            }
        }
        return ordenes;
    }
    
    

}
