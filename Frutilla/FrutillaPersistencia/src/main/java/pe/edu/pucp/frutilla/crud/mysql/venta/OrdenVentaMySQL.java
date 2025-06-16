/**
 *
 * @author  Gabriel Medrano
 * Consultas: a20200948@pucp.edu.pe
 * Telf: 989299092
 */

package pe.edu.pucp.frutilla.crud.mysql.venta;

import java.sql.CallableStatement;
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
import java.sql.Types;
import java.util.ArrayList;
import java.time.*; 
import java.util.List;
import pe.edu.pucp.frutilla.crud.dao.venta.OrdenVentaDAO;
import pe.edu.pucp.frutilla.crud.mysql.BaseDAOImpl;


public class OrdenVentaMySQL extends BaseDAOImpl<OrdenVenta> implements OrdenVentaDAO{
    
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
    
    
    @Override
    public void agregar(OrdenVenta entity) {
        try (Connection conn = DBManager.getInstance().getConnection();
             CallableStatement cs = conn.prepareCall("{CALL INSERTAR_ORDEN_VENTA(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}")) {

            // OUT parameter
            cs.registerOutParameter(1, Types.INTEGER);

            // IN parameters
            cs.setDate(2, Date.valueOf(entity.getFecha()));
            cs.setTime(3, Time.valueOf(entity.getHoraFinEntrega()));
            cs.setString(4, entity.getDescripcion());
            cs.setDouble(5, entity.getMontoTotal());
            cs.setBoolean(6, entity.getEntregado());
            cs.setString(7, entity.getEstado().name());
            cs.setInt(8, entity.getIdLocal());
            cs.setInt(9, entity.getIdCliente());

            // idEmpleado puede ser null
            if (entity.getIdEmpleado() > 0) {
                cs.setInt(10, entity.getIdEmpleado());
            } else {
                cs.setNull(10, Types.INTEGER);
            }

            cs.execute();

            // Recuperar id generado
            int idGenerado = cs.getInt(1);
            entity.setIdOrdenVenta(idGenerado); // o setId(entity, idGenerado);

        } catch (SQLException e) {
            throw new RuntimeException("Error al agregar OrdenVenta", e);
        }
    }
    
    
    @Override
    public void actualizar(OrdenVenta entity){
        try (Connection conn = DBManager.getInstance().getConnection();
             CallableStatement cs = conn.prepareCall("{CALL ACTUALIZAR_ORDEN_VENTA(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}")) {

            cs.setInt(1, entity.getIdOrdenVenta()); // ID a actualizar
            cs.setDate(2, Date.valueOf(entity.getFecha()));
            cs.setTime(3, Time.valueOf(entity.getHoraFinEntrega()));
            cs.setString(4, entity.getDescripcion());
            cs.setDouble(5, entity.getMontoTotal());
            cs.setBoolean(6, entity.getEntregado());
            cs.setString(7, entity.getEstado().name());
            cs.setInt(8, entity.getIdLocal());

            // idComprobante (nuevo parámetro)
            cs.setInt(9, entity.getIdComprobante()); 

            cs.setInt(10, entity.getIdCliente());

            if (entity.getIdEmpleado() > 0) {
                cs.setInt(11, entity.getIdEmpleado());
            } else {
                cs.setNull(11, Types.INTEGER);
            }

            cs.execute();

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar OrdenVenta", e);
        }
    }
    
    @Override
    public List<OrdenVenta> listarTodos() {
        List<OrdenVenta> lista = new ArrayList<>();

        try (Connection conn = DBManager.getInstance().getConnection();
             CallableStatement cs = conn.prepareCall("{CALL ORDEN_VENTA_LISTAR()}");
             ResultSet rs = cs.executeQuery()) {

            while (rs.next()) {
                OrdenVenta orden = new OrdenVenta();

                orden.setIdOrdenVenta(rs.getInt("idOrdenVenta"));
                orden.setFecha(rs.getDate("fecha").toLocalDate());
                orden.setHoraFinEntrega(rs.getTime("horaFinEntrega").toLocalTime());
                orden.setDescripcion(rs.getString("descripcion"));
                orden.setMontoTotal(rs.getDouble("montoTotal"));
                orden.setEstado(EstadoVenta.valueOf(rs.getString("estadoVenta")));
                orden.setIdLocal(rs.getInt("idLocal"));
                int idComprobante = rs.getInt("idComprobante");
                if (!rs.wasNull()){
                    orden.setIdComprobante(idComprobante);
                }
                orden.setIdCliente(rs.getInt("idCliente"));
                int idEmpleado = rs.getInt("idEmpleado");
                if (!rs.wasNull()) {
                    orden.setIdEmpleado(idEmpleado);
                }

                lista.add(orden);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al listar órdenes de venta", e);
        }

        return lista;
    }
    
    
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
        ps.setInt(9, entity.getIdComprobante());
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
        OrdenVenta orden = new OrdenVenta();
        orden.setIdOrdenVenta(rs.getInt("idOrdenVenta"));
        orden.setFecha(rs.getDate("fecha").toLocalDate());
        orden.setFechaStr(orden.getFecha().toString());
        orden.setHoraFinEntrega(rs.getTime("horaFinEntrega").toLocalTime());
        orden.setHoraStr(orden.getHoraFinEntrega().toString());
        orden.setDescripcion(rs.getString("descripcion"));
        orden.setMontoTotal(rs.getDouble("montoTotal"));
        orden.setEstado(EstadoVenta.valueOf(rs.getString("estadoVenta")));
        orden.setIdLocal(rs.getInt("idLocal"));
        int idCliente = rs.getInt("idCliente");
        if (!rs.wasNull()){
            orden.setIdCliente(idCliente);
        }
        int idComprobante = rs.getInt("idComprobante");
        if (!rs.wasNull()){
            orden.setIdComprobante(idComprobante);
        }
        else{
            orden.setIdComprobante(0);
        }
        int idEmpleado = rs.getInt("idEmpleado");
        if (!rs.wasNull()) {
            orden.setIdEmpleado(idEmpleado);
        }
        else{
            orden.setIdEmpleado(0);
        }
        return orden;
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
        String query = "SELECT * FROM OrdenVenta WHERE idEmpleado = ?";

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, idEmpleado);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrdenVenta ordenVenta = createFromResultSet(rs);
//                ordenVenta.setFecha(rs.getDate("fecha").toLocalDate());
//                ordenVenta.setHoraFinEntrega(rs.getTime("horaFinEntrega").toLocalTime());
                ordenVenta.setIdLocal(rs.getInt("idLocal"));
//                ordenVenta.setIdComprobante(rs.getInt("idComprobante"));
                ordenVenta.setIdCliente(rs.getInt("idCliente"));
                ordenVenta.setIdEmpleado(rs.getInt("idEmpleado"));
                ordenes.add(ordenVenta);
            }

        }
        return ordenes;
    }

    
    public OrdenVenta obtenerPorId(int idOrdenVenta) throws SQLException {
        OrdenVenta ordenVenta = null;
        String query = "{CALL ORDEN_VENTA_LISTAR_X_ID(?)}";
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
        String query = "{CALL ORDEN_VENTA_LISTAR()}";
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
    
    public List<OrdenVenta> listarPorClienteLocal
        (int idCliente,int idLocal) throws SQLException {
        List<OrdenVenta> ordenes = new ArrayList<>();
        String query = "{CALL ORDEN_V_LISTAR_X_LOCALCLIENTE(?,?)}";

        try (Connection con = DBManager.getInstance().getConnection();
             CallableStatement ps = con.prepareCall(query)) {
            ps.setInt(1, idLocal);
            ps.setInt(2, idCliente);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrdenVenta ordenVenta = createFromResultSet(rs);
                ordenes.add(ordenVenta);
            }
        }
        return ordenes;
    }
    

}
