package com.frutilla.models.rrhh;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface PersonaDAO {
    void setPersonaParameters(PreparedStatement ps,Persona cliente) throws SQLException;
    public Persona mapPersona(ResultSet rs, Persona persona) throws SQLException;
}
