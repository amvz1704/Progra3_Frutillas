package com.frutilla.crud.dao.rrhh;

import java.sql.Connection;
import java.sql.SQLException;

import com.frutilla.models.rrhh.Persona;

public interface UsuarioDAO {
    void insertarUsuario(Persona persona, Connection con) throws SQLException;
    void actualizarUsuario(Persona persona, Connection con) throws SQLException;
}
