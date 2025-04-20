package com.frutilla.models.rrhh;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

public interface PersonaDAO {
    void setPersonaParameters(PreparedStatement ps,Persona cliente) throws SQLException;
    Persona mapPersona(ResultSet rs, Persona persona) throws SQLException;
    int insertarPersona(Persona persona, Connection con) throws SQLException;
    void actualizarPersona(Persona persona, Connection con) throws SQLException;
    void eliminarPersona(int idPersona, Connection con) throws SQLException;
}
