package com.frutilla.models.Cliente;

import com.frutilla.config.DBManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Statement;
import java.sql.ResultSet;


public class ClienteDAO {
    public void insertarCliente(Cliente cliente) {
        // Consulta SQL para insertar un nuevo cliente
        String queryCliente = "INSERT INTO Clientes (nombre, apellidoPaterno, apellidoMaterno, telefono, correoElectronico, activo) VALUES (?, ?, ?, ?, ?, ?)";
        try(Connection con = DBManager.getConnection(); PreparedStatement ps = con.prepareStatement(queryCliente)){// Obtiene la conexion y prepara la consulta
            setClienteParameters(ps,cliente);// Establece los parámetros del cliente
            ps.executeUpdate();// Ejecuta la consulta

            try(Statement st = con.createStatement(); ResultSet rs = st.executeQuery("SELECT @@last_insert_id")) {
                // Obtiene el ID del cliente recién insertado
                if (rs.next()) {
                    cliente.setIdCliente(rs.getInt(1)); // Establece el ID del cliente en el objeto cliente
                }
            }
        }
        catch(SQLException e){
            System.out.println("Error al insertar el cliente: " + e.getMessage());
        }
    }

    // Ejemplo de método para obtener un cliente por su ID
    public Cliente obtenerClientePorId(int idCliente) {
        String query = "SELECT * FROM Clientes WHERE idCliente = ?";
        try(Connection con = DBManager.getConnection(); PreparedStatement ps = con.prepareStatement(query)){
            ps.setInt(1,idCliente);// Establece el ID del cliente en la consulta
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    return mapCliente(rs);
                }
            }
        }
        catch(SQLException e){
            System.out.println("Error al obtener el cliente: " + e.getMessage());
        }
        return null;
    }

    public void actualizarCliente(Cliente cliente) {
        String query = "UPDATE clientes SET nombre = ?, apellidoPaterno = ?, apellidoMaterno = ?, telefono = ?, correoElectronico = ?, activo = ? WHERE idCliente = ?";
        try(Connection con = DBManager.getConnection(); PreparedStatement ps = con.prepareStatement(query)){
            setClienteParameters(ps, cliente);
            ps.setInt(7, cliente.getIdCliente());// Establece el ID del cliente en la consulta
            ps.executeUpdate();// Ejecuta la consulta
        }
        catch(SQLException e){
            System.out.println("Error al actualizar el cliente: " + e.getMessage());
        }
    }
    private Cliente mapCliente(ResultSet rs) throws SQLException{
        int idCliente = rs.getInt("idCliente");
        String nombre = rs.getString("nombre");
        String apellidoPaterno = rs.getString("apellidoPaterno");
        String apellidoMaterno = rs.getString("apellidoMaterno");
        String telefono = rs.getString("telefono");
        String correoElectronico = rs.getString("correoElectronico");
        boolean activo = rs.getBoolean("activo");
        Cliente cliente = new Cliente(nombre, apellidoPaterno, apellidoMaterno, telefono, correoElectronico);
        cliente.setIdCliente(idCliente);// Establece el ID del cliente en el objeto cliente
        return cliente;
    }
    private void setClienteParameters(PreparedStatement ps,Cliente cliente) throws SQLException {
        // Establece los parámetros del cliente en la consulta SQL
        ps.setString(1, cliente.getNombre());
        ps.setString(2, cliente.getApellidoPaterno());
        ps.setString(3, cliente.getApellidoMaterno());
        ps.setString(4, cliente.getTelefono());
        ps.setString(5, cliente.getCorreoElectronico());
        ps.setBoolean(6, cliente.getActivo());
    }

}




