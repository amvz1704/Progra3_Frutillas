package com.frutilla.crud.mysql.rrhh;


import com.frutilla.crud.dao.rrhh.ClienteDAO; //incluimos el dao correspondiente 
import com.frutilla.models.rrhh.Cliente; //Cliente

import com.frutilla.config.DBManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;


public class ClienteMySQL implements ClienteDAO{
	
	public ClienteMySQL(){
	}
	
	
    public void insertarCliente(Cliente cliente) throws SQLException {
        String query = "INSERT INTO Cliente (idUsuario, nombres, apellidoPaterno, apellidoMaterno, correoElectronico, telefono) VALUES (?, ?, ?, ?, ?, ?)";
        try(Connection con = DBManager.getInstance().getConnection(); PreparedStatement ps = con.prepareStatement(query)){// Obtiene la conexion y prepara la consulta
            UsuarioMySQL usuarioMySQL = new UsuarioMySQL();// Crea una instancia de UsuarioMySQL
            usuarioMySQL.insertarUsuario(cliente, con);
            setClienteParameters(ps,cliente);// Establece los parámetros del cliente
            ps.executeUpdate();// Ejecuta la consulta
        }
    }

    public ArrayList<Cliente> obtenerTodos()throws SQLException{
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();// Crea una lista para almacenar los clientes
        String query = "SELECT u.idUsuario, u.usuarioSistema, u.contrasSistema, u.activo, c.nombres, c.apellidoPaterno, c.apellidoMaterno, c.correoElectronico, c.telefono FROM Usuario u JOIN Cliente c ON c.idUsuario = u.idUsuario WHERE Usuario.activo = true";// Consulta SQL para obtener todos los clientes activos
        try(Connection con = DBManager.getInstance().getConnection(); PreparedStatement ps = con.prepareStatement(query); ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                clientes.add(mapCliente(rs));// Mapea los datos de cada cliente y los agrega a la lista
            }
        }
        return clientes;
    }

    public Cliente obtenerClientePorId(int idCliente) throws SQLException {
        String query = "SELECT u.idUsuario, u.usuarioSistema, u.contrasSistema, u.activo, c.nombres, c.apellidoPaterno, c.apellidoMaterno, c.correoElectronico, c.telefono FROM Usuario u JOIN Cliente c ON c.idUsuario = u.idUsuario WHERE idUsuario = ? AND u.activo = true";// Consulta SQL para obtener un cliente por su ID
        try(Connection con = DBManager.getInstance().getConnection(); PreparedStatement ps = con.prepareStatement(query)){
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
        String query = "UPDATE Cliente SET idUsuario = ?, nombres = ?, apellidoPaterno = ?, apellidoMaterno = ?, correoElectronico = ?, telefono = ?  WHERE idUsuario = ?";
        try(Connection con = DBManager.getInstance().getConnection(); PreparedStatement ps = con.prepareStatement(query)){
            UsuarioMySQL usuarioMySQL = new UsuarioMySQL();// Crea una instancia de UsuarioMySQL
            usuarioMySQL.actualizarUsuario(cliente, con);// Actualiza el usuario en la base de datos
            setClienteParameters(ps, cliente);// Establece los parámetros del cliente
            ps.setInt(9, cliente.getIdCliente());
            ps.executeUpdate();
        }
    }

    public void eliminarCliente(int idCliente) throws SQLException {
        String query = "UPDATE Cliente SET activo = false WHERE idCliente = ?";
        try(Connection con = DBManager.getInstance().getConnection(); PreparedStatement ps = con.prepareStatement(query)){
            ps.setInt(1, idCliente);// Establece el ID del cliente en la consulta
            ps.executeUpdate();// Ejecuta la consulta
        }
    }

    

    private Cliente mapCliente(ResultSet rs) throws SQLException{
        Cliente cliente = new Cliente();

        //Persona
        cliente.setNombre(rs.getString("nombres"));
        cliente.setApellidoPaterno(rs.getString("apellidoPaterno"));
        cliente.setApellidoMaterno(rs.getString("apellidoMaterno"));
        cliente.setCorreoElectronico(rs.getString("correoElectronico"));
        cliente.setTelefono(rs.getString("telefono"));
        cliente.setUsuarioSistema(rs.getString("usuarioSistema"));
        cliente.setContraSistema(rs.getString("contrasSistema"));
        cliente.setActivo(rs.getBoolean("activo"));// Establece el estado activo del cliente en el objeto persona


        //Cliente
        cliente.setIdCliente(rs.getInt("idCliente"));// Establece el ID del cliente en el objeto cliente
        

        return cliente;
    }

    private void setClienteParameters(PreparedStatement ps,Cliente cliente) throws SQLException {
        // Establece los parámetros del cliente en la consulta SQL
        ps.setInt(1, cliente.getIdCliente());
        ps.setString(2, cliente.getNombre());
        ps.setString(3, cliente.getApellidoPaterno());
        ps.setString(4, cliente.getApellidoMaterno());
        ps.setString(5, cliente.getCorreoElectronico());
        ps.setString(6, cliente.getTelefono());
    }

}




