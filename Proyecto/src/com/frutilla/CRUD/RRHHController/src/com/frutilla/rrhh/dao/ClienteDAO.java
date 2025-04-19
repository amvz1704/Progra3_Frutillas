package com.frutilla.models.rrhh;

import java.sql.SQLException;

public interface ClienteDAO {
    void insertarCliente(Cliente cliente) throws SQLException; 
}
