package com.frutilla.crud.mysql.venta;

import com.frutilla.crud.dao.venta.ComprobantePagoDAO;
import com.frutilla.models.venta.ComprobantePago;
import com.frutilla.config.DBManager;  // Importando DBManager
import java.sql.*;
import java.util.ArrayList;

public class ComprobantePagoMySQL implements ComprobantePagoDAO {

    // Método para insertar un nuevo comprobante de pago
    @Override
    public void insertarComprobante(ComprobantePago comprobantePago) throws SQLException {
        String query = "INSERT INTO ComprobantePago(numeroArticulos, subtotal, montoIGV, total, fecha, formaDePago) VALUES(?, ?, ?, ?, ?, ?)";

        
        try (Connection con = DBManager.getInstance().getConnection();  // Se conecta a la base de datos
             PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) { 

            // Insertar datos en la base de datos
            ps.setInt(1, comprobantePago.getNumeroArticulos());
            ps.setDouble(2, comprobantePago.getSubtotal());
            ps.setDouble(3, comprobantePago.getMontoIGV());
            ps.setDouble(4, comprobantePago.getTotal());
            ps.setDate(5, Date.valueOf(comprobantePago.getFecha()));
            ps.setString(6, comprobantePago.getFormaDePago());

            // Ejecuta la inserción en la base de datos
            ps.executeUpdate();

            // Traer el último idComprobante generado (autoincremental)
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    comprobantePago.setIdComprobante(rs.getInt(1)); 
                }
            }
        }
        return result;
    }

    // Método para modificar un comprobante de pago
    @Override
    public int modificar(ComprobantePago comprobantePago) throws SQLException {
        int result = 0;
        String query = "UPDATE ComprobantePago SET numeroArticulos = ?, subtotal = ?, montoIGV = ?, total = ?, " +
                       "fecha = ?, formaDePago = ? WHERE idComprobante = ?";

   
        try (Connection con = DBManager.getConnection(); 
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, comprobantePago.getNumeroArticulos());
            ps.setDouble(2, comprobantePago.getSubtotal());
            ps.setDouble(3, comprobantePago.getMontoIGV());
            ps.setDouble(4, comprobantePago.getTotal());
            ps.setDate(5, Date.valueOf(comprobantePago.getFecha()));
            ps.setString(6, comprobantePago.getFormaDePago());
            ps.setInt(7, comprobantePago.getIdComprobante());

            result = ps.executeUpdate();  // Ejecuta la actualización
        }
        return result;
    }

    // Método para obtener un comprobante de pago por su ID
    @Override
    public ComprobantePago obtenerPorId(int idComprobante) throws SQLException {
        ComprobantePago comprobante = new ComprobantePago();
        String query = "SELECT idComprobante, numeroArticulos, subtotal, montoIGV, total, fecha, formaDePago " +
                       "FROM ComprobantePago WHERE idComprobante = ?";

        // Usando try-with-resources para asegurar el cierre de la conexión
        try (Connection con = DBManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, idComprobante);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    comprobante.setIdComprobante(rs.getInt("idComprobante"));
                    comprobante.setNumeroArticulos(rs.getInt("numeroArticulos"));
                    comprobante.setSubtotal(rs.getDouble("subtotal"));
                    comprobante.setMontoIGV(rs.getDouble("montoIGV"));
                    comprobante.setTotal(rs.getDouble("total"));
                    comprobante.setFecha(rs.getDate("fecha").toLocalDate());
                    comprobante.setFormaDePago(rs.getString("formaDePago"));
                }
            }
        }
        return comprobante;
    }

    // Método para eliminar un comprobante de pago
    @Override
    public int eliminar(int idComprobante) throws SQLException {
        int result = 0;
        String query = "DELETE FROM ComprobantePago WHERE idComprobante = ?";

        try (Connection con = DBManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, idComprobante);
            result = ps.executeUpdate();  // Ejecuta la eliminación
        }
        return result;
    }

    // Método para obtener todos los comprobantes de pago
    @Override
    public ArrayList<ComprobantePago> obtenerTodos() throws SQLException {
        ArrayList<ComprobantePago> comprobantes = new ArrayList<>();
        String query = "SELECT idComprobante, numeroArticulos, subtotal, montoIGV, total, fecha, formaDePago " +
                       "FROM ComprobantePago";


        try (Connection con = DBManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ComprobantePago comprobante = new ComprobantePago();
                comprobante.setIdComprobante(rs.getInt("idComprobante"));
                comprobante.setNumeroArticulos(rs.getInt("numeroArticulos"));
                comprobante.setSubtotal(rs.getDouble("subtotal"));
                comprobante.setMontoIGV(rs.getDouble("montoIGV"));
                comprobante.setTotal(rs.getDouble("total"));
                comprobante.setFecha(rs.getDate("fecha").toLocalDate());
                comprobante.setFormaPago(rs.getString("formaDePago"));
                comprobantes.add(comprobante);
            }
        }
        return comprobantes;
    }

    private void setComprobanteParameters(PreparedStatement ps, ComprobantePago comprobantePago) throws SQLException {
        ps.setInt(1, comprobantePago.getNumeroArticulos());
        ps.setDouble(2, comprobantePago.getSubtotal());
        ps.setDouble(3, comprobantePago.getMontoIGV());
        ps.setDouble(4, comprobantePago.getTotal());
        ps.setDate(5, Date.valueOf(comprobantePago.getFecha()));
        ps.setString(6, comprobantePago.getFormaPago().toString());
    }
}
