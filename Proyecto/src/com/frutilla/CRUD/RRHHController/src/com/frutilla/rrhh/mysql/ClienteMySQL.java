package com.frutilla.models.rrhh;

import com.frutilla.config.DBManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Statement;
import java.sql.ResultSet;


public class ClienteMySQL extends PersonaMySQL implements ClienteDAO{
    public void insertarCliente(Cliente cliente) throws SQLException {
        // Consulta SQL para insertar un nuevo cliente
        String queryPersona = "INSERT INTO Personas (nombre, apellidoPaterno, apellidoMaterno, correoElectronico, telefono, usuarioSistema, contrasSistema) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String queryCliente = "INSERT INTO Clientes (activo, idPersona) VALUES (?, ?)";
        try(Connection con = DBManager.getConnection(); PreparedStatement psP = con.prepareStatement(queryPersona, Statement.RETURN_GENERATED_KEYS); PreparedStatement psC = con.prepareStatement(queryCliente)){// Obtiene la conexion y prepara la consulta
            setPersonaParameters(psP, cliente);// Establece los parámetros de la persona
            psP.executeUpdate();// Ejecuta la consulta
            try(ResultSet rs = psP.getGeneratedKeys()){// Obtiene las claves generadas por la consulta anterior)
                if (rs.next()) {
                    cliente.setIdPersona(rs.getInt(1)); // Establece el ID de persona en el objeto cliente
                }
            }
            setClienteParameters(psC,cliente);// Establece los parámetros del cliente
            psC.executeUpdate();// Ejecuta la consulta            
        }
    }

    // Ejemplo de método para obtener un cliente por su ID
    public Cliente obtenerClientePorId(int idCliente) throws SQLException {
        String query = "SELECT p.idPersona, p.nombre, p.apellidoPaterno, p.apellidoMaterno, p.correoElectronico, p.telefono, p.usuarioSistema, p.contrasSistema, c.idCliente, c.activo FROM Clientes c JOIN Personas p ON c.idPersona = p.idPersona WHERE idCliente = ?";
        try(Connection con = DBManager.getConnection(); PreparedStatement ps = con.prepareStatement(query)){
            ps.setInt(1,idCliente);// Establece el ID del cliente en la consulta
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    return mapCliente(rs);
                }
            }
        }
        return null;
    }

    public void actualizarCliente(Cliente cliente) throws SQLException {
        String query = "UPDATE clientes SET nombre = ?, apellidoPaterno = ?, apellidoMaterno = ?, telefono = ?, correoElectronico = ?, activo = ? WHERE idCliente = ?";
        try(Connection con = DBManager.getConnection(); PreparedStatement ps = con.prepareStatement(query)){
            setClienteParameters(ps, cliente);
            ps.setInt(7, cliente.getIdCliente());// Establece el ID del cliente en la consulta
            ps.executeUpdate();// Ejecuta la consulta
        }
    }
    private Cliente mapCliente(ResultSet rs) throws SQLException{
        Cliente cliente = new Cliente();

        mapPersona(rs, cliente);// Mapea los datos de la persona al objeto cliente

        //Cliente
        cliente.setIdCliente(rs.getInt("idCliente"));// Establece el ID del cliente en el objeto cliente
        cliente.setActivo(rs.getBoolean("activo"));// Establece el estado activo del cliente en el objeto cliente

        return cliente;
    }
    private void setClienteParameters(PreparedStatement ps,Cliente cliente) throws SQLException {
        // Establece los parámetros del cliente en la consulta SQL "INSERT INTO Clientes (activo, idPersona) VALUES (?, ?)"
        ps.setBoolean(1, cliente.getActivo());
        ps.setInt(2, cliente.getIdPersona());// Establece el ID de persona en la consulta
    }

}




