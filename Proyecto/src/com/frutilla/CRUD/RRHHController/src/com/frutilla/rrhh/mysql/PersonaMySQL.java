package com.frutilla.models.rrhh;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class PersonaMySQL implements PersonaDAO{

    public Persona mapPersona(ResultSet rs, Persona persona) throws SQLException {
        persona.setIdPersona(rs.getInt("idPersona"));// Establece el ID de persona en el objeto cliente
        persona.setNombre(rs.getString("nombre"));// Establece el nombre de la persona en el objeto cliente
        persona.setApellidoPaterno(rs.getString("apellidoPaterno"));// Establece el apellido paterno de la persona en el objeto cliente 
        persona.setApellidoMaterno(rs.getString("apellidoMaterno"));// Establece el apellido materno de la persona en el objeto cliente
        persona.setCorreoElectronico(rs.getString("correoElectronico"));// Establece el correo electrónico de la persona en el objeto cliente
        persona.setTelefono(rs.getString("telefono"));// Establece el teléfono de la persona en el objeto cliente
        persona.setUsuarioSistema(rs.getString("usuarioSistema"));// Establece el usuario del sistema de la persona en el objeto cliente
        persona.setContraSistema(rs.getString("contrasSistema"));// Establece la contraseña del sistema de la persona en el objeto cliente
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