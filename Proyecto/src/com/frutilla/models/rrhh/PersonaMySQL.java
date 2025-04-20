package com.frutilla.models.rrhh;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Connection;

public class PersonaMySQL implements PersonaDAO{

    public int insertarPersona(Persona persona, Connection con) throws SQLException{
        String query = "INSERT INTO persona (nombre, apellidoPaterno, apellidoMaterno, correoElectronico, telefono, usuarioSistema, contrasSistema) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {// Obtiene la conexion y prepara la consulta
            setPersonaParameters(ps, persona);// Establece los parámetros de la persona
            ps.executeUpdate();// Ejecuta la consulta
            try(ResultSet rs = ps.getGeneratedKeys()){// Obtiene las claves generadas por la consulta anterior
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return -1;
    }

    public void actualizarPersona(Persona persona, Connection con) throws SQLException{
        String query = "UPDATE persona SET nombre = ?, apellidoPaterno = ?, apellidoMaterno = ?, correoElectronico = ?, telefono = ?, usuarioSistema = ?, contrasSistema = ? WHERE idPersona = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {// Obtiene la conexion y prepara la consulta
            setPersonaParameters(ps, persona);// Establece los parámetros de la persona
            ps.setInt(8, persona.getIdPersona());// Establece el ID de persona en la consulta
            ps.executeUpdate();// Ejecuta la consulta
        }
    }

    public void eliminarPersona(int idPersona, Connection con) throws SQLException{
        String query = "DELETE FROM persona WHERE idPersona = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {// Obtiene la conexion y prepara la consulta
            ps.setInt(1, idPersona);// Establece el ID de persona en la consulta
            ps.executeUpdate();// Ejecuta la consulta
        }
    }

    public Persona mapPersona(ResultSet rs, Persona persona) throws SQLException {
        persona.setIdPersona(rs.getInt("idPersona"));
        persona.setNombre(rs.getString("nombre"));
        persona.setApellidoPaterno(rs.getString("apellidoPaterno"));
        persona.setApellidoMaterno(rs.getString("apellidoMaterno"));
        persona.setCorreoElectronico(rs.getString("correoElectronico"));
        persona.setTelefono(rs.getString("telefono"));
        persona.setUsuarioSistema(rs.getString("usuarioSistema"));
        persona.setContraSistema(rs.getString("contrasSistema"));
        return persona;
    }

    public void setPersonaParameters(PreparedStatement ps,Persona cliente) throws SQLException {
        // Establece los parámetros del cliente en la consulta SQL
        ps.setString(1, cliente.getNombre());
        ps.setString(2, cliente.getApellidoPaterno());
        ps.setString(3, cliente.getApellidoMaterno());
        ps.setString(4, cliente.getCorreoElectronico());
        ps.setString(5, cliente.getTelefono());
        ps.setString(6, cliente.getUsuarioSistema());
        ps.setString(7, cliente.getContraSistema());
    }
}