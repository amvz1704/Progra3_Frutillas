package pe.edu.pucp.frutilla.crud.mysql.venta;

import pe.edu.pucp.frutilla.crud.dao.venta.ComprobantePagoDAO; 
import pe.edu.pucp.frutilla.models.venta.ComprobantePago;
import pe.edu.pucp.frutilla.models.venta.FormaDePago;
import pe.edu.pucp.frutilla.config.DBManager;  // Importando DBManager
import java.sql.*;
import java.util.ArrayList;

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
  
}
