package pe.edu.pucp.frutilla.crud.dao.rrhh;

import java.sql.Connection;
import java.sql.SQLException;

import pe.edu.pucp.frutilla.models.rrhh.Persona;

public interface UsuarioDAO {
    void insertarUsuario(Persona persona, Connection con) throws SQLException;
    void actualizarUsuario(Persona persona, Connection con) throws SQLException;
}