package com.frutilla.crud.mysql.rrhh;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.frutilla.models.rrhh.Empleado;
import com.frutilla.models.rrhh.Persona;
import com.frutilla.models.rrhh.Cliente;

public class UsuarioMySQL {
    public void insertarUsuario(Persona persona,Connection con){
        String query = "INSERT INTO Usuario (usuarioSistema, contrasSistema, activo) VALUES (?, ?, ?)";
        try(PreparedStatement ps = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)){
            setUsuarioParameters(ps, persona);// Establece los parámetros del usuario
            ps.executeUpdate();// Ejecuta la consulta
            try(ResultSet rs = ps.getGeneratedKeys()){
                if(rs.next()){
                    if(persona instanceof Empleado) {
                        ((Empleado) persona).setIdEmpleado(rs.getInt(1));// Establece el ID del empleado
                    } else {
                        ((Cliente) persona).setIdCliente(rs.getInt(1));// Establece el ID del cliente
                    }
                }
            }
        }
        catch (SQLException e) {
            System.out.println("Error al insertar el usuario: " + e.getMessage());
        }
    }

    public void actualizarUsuario(Persona persona, Connection con) throws SQLException {
        String query = "UPDATE Usuario SET usuarioSistema = ?, contrasSistema = ?, activo = ? WHERE idUsuario = ?";
        try(PreparedStatement ps = con.prepareStatement(query)){
            setUsuarioParameters(ps, persona);// Establece los parámetros del usuario
            if(persona instanceof Empleado) {
                ps.setInt(4, ((Empleado) persona).getIdEmpleado());// Establece el ID del empleado
            } else {
                ps.setInt(4, ((Cliente)persona).getIdCliente());// Establece el ID del cliente
            }
            ps.executeUpdate();// Ejecuta la consulta
        }
    }

    private void setUsuarioParameters(PreparedStatement ps, Persona persona) throws SQLException {
        ps.setString(1, persona.getUsuarioSistema());
        ps.setString(2, persona.getContraSistema());
        ps.setBoolean(3, persona.getActivo());
    }
}
