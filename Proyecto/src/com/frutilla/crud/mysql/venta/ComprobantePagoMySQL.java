package com.frutilla.crud.mysql.venta;

import com.frutilla.crud.dao.venta.ComprobantePagoDAO; 
import com.frutilla.models.venta.ComprobantePago;
import com.frutilla.models.venta.FormaDePago;
import com.frutilla.config.DBManager;  // Importando DBManager
import java.sql.*;
import java.util.ArrayList;

public class ComprobantePagoMySQL implements ComprobantePagoDAO {

    // Método para insertar un nuevo comprobante de pago
    public void insertarComprobante(ComprobantePago comprobantePago) throws SQLException {
        String query = "INSERT INTO ComprobantePago(numeroArticulos, subtotal, montoIGV, total, fecha, formaDePago) VALUES(?, ?, ?, ?, ?, ?)";

        
        try (Connection con = DBManager.getInstance().getConnection();  // Se conecta a la base de datos
        PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) { 

            setComprobanteParameters(ps, comprobantePago);

            // Ejecuta la inserción en la base de datos
            ps.executeUpdate();

            // Traer el último idComprobante generado (autoincremental)
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    comprobantePago.setIdComprobante(rs.getInt(1)); 
                }
            }
        }
    }

    // Método para modificar un comprobante de pago
    public void actualizarComprobante(ComprobantePago comprobantePago) throws SQLException {
        String query = "UPDATE ComprobantePago SET numeroArticulos = ?, subtotal = ?, montoIGV = ?, total = ?, fecha = ?, formaDePago = ? WHERE idComprobante = ?";


        try (Connection con = DBManager.getInstance().getConnection(); PreparedStatement ps = con.prepareStatement(query)) {

            setComprobanteParameters(ps, comprobantePago);
            ps.setInt(7, comprobantePago.getIdComprobante());

            ps.executeUpdate();  // Ejecuta la actualización
        }
    }

    public ComprobantePago obtenerComprobantePorId(int idComprobante) throws SQLException {
        String query = "SELECT idComprobante, numeroArticulos, subtotal, montoIGV, total, fecha, formaDePago FROM ComprobantePago WHERE idComprobante = ?";

        // Usando try-with-resources para asegurar el cierre de la conexión
        try (Connection con = DBManager.getInstance().getConnection(); PreparedStatement ps = con.prepareStatement(query)){

            ps.setInt(1, idComprobante);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapComprobante(rs);  // Mapea el resultado a un objeto ComprobantePago
                }
            }
        }
        return null;
    }

    public ArrayList<ComprobantePago> obtenerTodos() throws SQLException {
        ArrayList<ComprobantePago> comprobantes = new ArrayList<ComprobantePago>();
        String query = "SELECT idComprobante, numeroArticulos, subtotal, montoIGV, total, fecha, formaDePago FROM ComprobantePago";


        try (Connection con = DBManager.getInstance().getConnection(); PreparedStatement ps = con.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                comprobantes.add(mapComprobante(rs));
            }
        }
        return comprobantes;
    }

    private ComprobantePago mapComprobante(ResultSet rs) throws SQLException {
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

    private void setComprobanteParameters(PreparedStatement ps, ComprobantePago comprobantePago) throws SQLException {
        ps.setInt(1, comprobantePago.getNumeroArticulos());
        ps.setDouble(2, comprobantePago.getSubtotal());
        ps.setDouble(3, comprobantePago.getMontoIGV());
        ps.setDouble(4, comprobantePago.getTotal());
        ps.setDate(5, Date.valueOf(comprobantePago.getFecha()));
        ps.setString(6, comprobantePago.getFormaPago().name());
    }
}
