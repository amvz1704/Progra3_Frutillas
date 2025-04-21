package com.frutilla.crud.mysql.rrhh;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.frutilla.models.rrhh.Empleado;

public class UsuarioMySQL {
    public void insertarUsuario(Empleado empleado,Connection con){
        String query = "INSERT INTO Usuario (usuarioSistema, contrasSistema, activo) VALUES (?, ?, ?)";
        try(PreparedStatement ps = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)){
            setUsuarioParameters(ps, empleado);// Establece los parámetros del usuario
            ps.executeUpdate();// Ejecuta la consulta
            try(ResultSet rs = ps.getGeneratedKeys()){
                if(rs.next()){
                    empleado.setIdEmpleado(rs.getInt(1));
                }
            }
        }
        catch (SQLException e) {
            System.out.println("Error al insertar el usuario: " + e.getMessage());
        }
    }

    public void actualizarUsuario(Empleado empleado, Connection con) throws SQLException {
        String query = "UPDATE Usuario SET usuarioSistema = ?, contrasSistema = ?, activo = ? WHERE idUsuario = ?";
        try(PreparedStatement ps = con.prepareStatement(query)){
            setUsuarioParameters(ps, empleado);// Establece los parámetros del usuario
            ps.setInt(4, empleado.getIdEmpleado());// Establece el ID del usuario
            ps.executeUpdate();// Ejecuta la consulta
        }
    }

    private void setUsuarioParameters(PreparedStatement ps, Empleado empleado) throws SQLException {
        ps.setString(1, empleado.getUsuarioSistema());
        ps.setString(2, empleado.getContraSistema());
        ps.setBoolean(3, empleado.getActivo());
    }
}
