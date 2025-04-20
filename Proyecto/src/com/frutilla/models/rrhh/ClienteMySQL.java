package com.frutilla.models.rrhh;

import com.frutilla.config.DBManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;


public class ClienteMySQL extends PersonaMySQL implements ClienteDAO{
    public void insertarCliente(Cliente cliente) throws SQLException {
        String query = "INSERT INTO Clientes (activo, idPersona) VALUES (?, ?)";
        try(Connection con = DBManager.getConnection(); PreparedStatement ps = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS )){// Obtiene la conexion y prepara la consulta
            //Se inserta primero la persona y luego el cliente, ya que el cliente depende de la persona
            cliente.setIdPersona(insertarPersona(cliente, con));
            setClienteParameters(ps,cliente);// Establece los parámetros del cliente
            ps.executeUpdate();// Ejecuta la consulta       
            try(ResultSet rs = ps.getGeneratedKeys()){// Obtiene las claves generadas por la consulta anterior)
                if (rs.next()) {
                    cliente.setIdCliente(rs.getInt(1)); // Establece el ID de persona en el objeto cliente
                }
            }     
        }
    }

    public ArrayList<Cliente> obtenerClientes()throws SQLException{
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();// Crea una lista para almacenar los clientes
        String query = "SELECT p.idPersona, p.nombre, p.apellidoPaterno, p.apellidoMaterno, p.correoElectronico, p.telefono, p.usuarioSistema, p.contrasSistema, c.idCliente, c.activo FROM Clientes c JOIN Personas p ON c.idPersona = p.idPersona";
        try(Connection con = DBManager.getConnection(); PreparedStatement ps = con.prepareStatement(query); ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                clientes.add(mapCliente(rs));// Mapea los datos de cada cliente y los agrega a la lista
            }
        }
        return clientes;
    }

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
        String query = "UPDATE clientes SET activo = ?, idPersona = ? WHERE idCliente = ?";
        try(Connection con = DBManager.getConnection(); PreparedStatement ps = con.prepareStatement(query)){
            actualizarPersona(cliente, con);// Actualiza la persona asociada al cliente
            setClienteParameters(ps, cliente);// Establece los parámetros del cliente
            ps.setInt(3, cliente.getIdCliente());
            ps.executeUpdate();
        }
    }

    public void eliminarCliente(int idCliente) throws SQLException {
        String queryS = "SELECT idPersona FROM Clientes WHERE idCliente = ?";
        String queryD = "DELETE FROM Clientes WHERE idCliente = ?";
        try(Connection con = DBManager.getConnection(); PreparedStatement psS = con.prepareStatement(queryS); PreparedStatement psD = con.prepareStatement(queryD)){
            psS.setInt(1, idCliente);// Establece el ID del cliente en la consulta
            try(ResultSet rs = psS.executeQuery()){
                if(rs.next()){
                    eliminarPersona(rs.getInt("idPersona"), con);// Obtiene el ID de persona asociado al cliente y lo elimina
                }
            }
            psD.setInt(1, idCliente);// Establece el ID del cliente en la consulta
            psD.executeUpdate();// Ejecuta la consulta
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




