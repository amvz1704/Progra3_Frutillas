package pe.edu.pucp.frutilla.crud.mysql.venta;

import pe.edu.pucp.frutilla.crud.dao.venta.ComprobantePagoDAO;
import pe.edu.pucp.frutilla.models.local.Notificacion;
import pe.edu.pucp.frutilla.models.venta.ComprobantePago;
import pe.edu.pucp.frutilla.models.venta.FormaDePago;
import pe.edu.pucp.frutilla.config.DBManager;  // Importando DBManager

import java.util.ArrayList;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import pe.edu.pucp.frutilla.crud.mysql.BaseDAOImpl;

public class ComprobantePagoMySQL extends BaseDAOImpl<ComprobantePago> implements ComprobantePagoDAO {
    
    @Override
    protected String getInsertQuery() {
        String cadena = "INSERT INTO ComprobantePago(numeroArticulos, subtotal, montoIGV, total, fecha, formaDePago) VALUES(?, ?, ?, ?, ?, ?)";
        return cadena;
    }

    @Override
    protected String getUpdateQuery() {
        String cadena = "UPDATE ComprobantePago SET numeroArticulos = ?, subtotal = ?, montoIGV = ?, total = ?, fecha = ?, formaDePago = ? WHERE idComprobante = ?";
        return cadena;
    }

    @Override
    protected String getDeleteQuery() {
        String cadena = "DELETE FROM ComprobantePago WHERE idComprobante=?";
        return cadena;
    }

    @Override
    protected String getSelectByIdQuery() {
        String cadena =  "SELECT idComprobante, numeroArticulos, subtotal, montoIGV, total, fecha, formaDePago FROM ComprobantePago WHERE idComprobante = ?";
        return cadena;
    }

    @Override
    protected String getSelectAllQuery() {
        String cadena =  "SELECT idComprobante, numeroArticulos, subtotal, montoIGV, total, fecha, formaDePago FROM ComprobantePago";
        return cadena;
    }

    @Override
    protected void setInsertParameters(PreparedStatement ps, ComprobantePago comprobantePago) throws SQLException {
        ps.setInt(1, comprobantePago.getNumeroArticulos());
        ps.setDouble(2, comprobantePago.getSubtotal());
        ps.setDouble(3, comprobantePago.getMontoIGV());
        ps.setDouble(4, comprobantePago.getTotal());
        ps.setDate(5, Date.valueOf(comprobantePago.getFecha()));
        ps.setString(6, comprobantePago.getFormaPago().name());
    }

    @Override
    protected void setUpdateParameters(PreparedStatement ps, ComprobantePago comprobantePago) throws SQLException {
        ps.setInt(1, comprobantePago.getNumeroArticulos());
        ps.setDouble(2, comprobantePago.getSubtotal());
        ps.setDouble(3, comprobantePago.getMontoIGV());
        ps.setDouble(4, comprobantePago.getTotal());
        ps.setDate(5, Date.valueOf(comprobantePago.getFecha()));
        ps.setString(6, comprobantePago.getFormaPago().name());
    }

    @Override
    protected ComprobantePago createFromResultSet(ResultSet rs) throws SQLException {
        ComprobantePago comprobante = new ComprobantePago();
        comprobante.setIdComprobante(rs.getInt("idComprobante"));
        comprobante.setNumeroArticulos(rs.getInt("numeroArticulos"));
        comprobante.setSubtotal(rs.getDouble("subtotal"));
        comprobante.setMontoIGV(rs.getDouble("montoIGV"));
        comprobante.setTotal(rs.getDouble("total"));
        comprobante.setFecha(rs.getDate("fecha").toLocalDate());
        comprobante.setFormaPago(FormaDePago.valueOf(rs.getString("formaDePago")));
        return comprobante;
    }

    @Override
    protected void setId(ComprobantePago comprobantePago, Integer id) {
        comprobantePago.setIdComprobante(id);
    }
  
    public Notificacion crearNotificacionCompra(ComprobantePago comprobante) {
        Notificacion notificacion = null;
        try (Connection conn = DBManager.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT \n" +
                                        "    ov.fecha,\n" +
                                        "    ov.horaFinEntrega,\n" +
                                        "    ov.montoTotal,\n" +
                                        "    ov.idCliente,\n" +
                                        "    ov.idEmpleado\n" +
                                        "FROM \n" +
                                        "    frutilla.OrdenVenta ov\n" +
                                        "WHERE \n" +
                                        "    ov.idComprobante = ?;");
            ) {
            //fecha, LocalTime hora, char tipoReceptor, int idCliente, int idSupervisor
            ps.setInt(1, comprobante.getIdComprobante());
            try(ResultSet rs = ps.executeQuery();){
                while (rs.next()) {
                    notificacion = new Notificacion('C', rs.getInt("ov.ifCliente"), rs.getInt("ov.idEmpleado"));
                    notificacion.textoCompra(rs.getDouble("ov.montoTotal"), rs.getDate("ov.fecha").toLocalDate(), rs.getTime("ov.horaFinEntrega").toLocalTime());
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar noticaciones", e);
        }
        return notificacion;
    }
}
