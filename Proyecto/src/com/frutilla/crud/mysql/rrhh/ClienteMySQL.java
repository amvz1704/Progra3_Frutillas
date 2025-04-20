package com.frutilla.crud.mysql.rrhh;


import com.frutilla.crud.dao.rrhh.ClienteDAO; //incluimos el dao correspondiente 
import com.frutilla.models.rrhh.Cliente //Cliente

import com.frutilla.config.DBManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;


public class ClienteMySQL implements ClienteDAO{
    public void insertarCliente(Cliente cliente) throws SQLException {
        String query = "INSERT INTO Cliente (nombre, apellidoPaterno, apellidoMaterno, correoElectronico, telefono, usuarioSistema, contrasSistema, activo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try(Connection con = DBManager.getConnection(); PreparedStatement ps = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS )){// Obtiene la conexion y prepara la consulta
            setClienteParameters(ps,cliente);// Establece los parámetros del cliente
            ps.executeUpdate();// Ejecuta la consulta       
            try(ResultSet rs = ps.getGeneratedKeys()){// Obtiene las claves generadas por la consulta anterior
                if (rs.next()) {
                    cliente.setIdCliente(rs.getInt(1)); // Establece el ID en el objeto cliente
                }
            }     
        }
    }

    public ArrayList<Cliente> obtenerClientes()throws SQLException{
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();// Crea una lista para almacenar los clientes
        String query = "SELECT * FROM Cliente WHERE activo = true";// Consulta SQL para obtener todos los clientes activos
        try(Connection con = DBManager.getConnection(); PreparedStatement ps = con.prepareStatement(query); ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                clientes.add(mapCliente(rs));// Mapea los datos de cada cliente y los agrega a la lista
            }
        }
        return clientes;
    }

    public Cliente obtenerClientePorId(int idCliente) throws SQLException {
        String query = "SELECT * FROM Cliente WHERE idCliente = ?";
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
        String query = "UPDATE Cliente SET nombre = ?, apellidoPaterno = ?, apellidoMaterno = ?, correoElectronico = ?, telefono = ?, usuarioSistema = ?, contrasSistema = ?, activo = ? WHERE idCliente = ?";
        try(Connection con = DBManager.getConnection(); PreparedStatement ps = con.prepareStatement(query)){
            setClienteParameters(ps, cliente);// Establece los parámetros del cliente
            ps.setInt(9, cliente.getIdCliente());
            ps.executeUpdate();
        }
    }

    public void eliminarCliente(int idCliente) throws SQLException {
        String query = "UPDATE Cliente SET activo = false WHERE idCliente = ?";
        try(Connection con = DBManager.getConnection(); PreparedStatement ps = con.prepareStatement(query)){
            ps.setInt(1, idCliente);// Establece el ID del cliente en la consulta
            ps.executeUpdate();// Ejecuta la consulta
        }
    }

    

    private Cliente mapCliente(ResultSet rs) throws SQLException{
        Cliente cliente = new Cliente();

        //Persona
        cliente.setNombre(rs.getString("nombre"));
        cliente.setApellidoPaterno(rs.getString("apellidoPaterno"));
        cliente.setApellidoMaterno(rs.getString("apellidoMaterno"));
        cliente.setCorreoElectronico(rs.getString("correoElectronico"));
        cliente.setTelefono(rs.getString("telefono"));
        cliente.setUsuarioSistema(rs.getString("usuarioSistema"));
        cliente.setContraSistema(rs.getString("contrasSistema"));


        //Cliente
        cliente.setIdCliente(rs.getInt("idCliente"));// Establece el ID del cliente en el objeto cliente
        cliente.setActivo(rs.getBoolean("activo"));// Establece el estado activo del cliente en el objeto cliente

        return cliente;
    }

    private void setClienteParameters(PreparedStatement ps,Cliente cliente) throws SQLException {
        // Establece los parámetros del cliente en la consulta SQL
        ps.setString(1, cliente.getNombre());
        ps.setString(2, cliente.getApellidoPaterno());
        ps.setString(3, cliente.getApellidoMaterno());
        ps.setString(4, cliente.getCorreoElectronico());
        ps.setString(5, cliente.getTelefono());
        ps.setString(6, cliente.getUsuarioSistema());
        ps.setString(7, cliente.getContraSistema());
        ps.setBoolean(8, cliente.getActivo());
    }

}




