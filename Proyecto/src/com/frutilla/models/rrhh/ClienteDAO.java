package com.frutilla.models.rrhh;

import java.sql.SQLException;

public interface ClienteDAO {
    void insertarCliente(Cliente cliente) throws SQLException; 
    Cliente obtenerClientePorId(int idCliente) throws SQLException;
    void actualizarCliente(Cliente cliente) throws SQLException;
    void eliminarCliente(int idCliente) throws SQLException;
}
