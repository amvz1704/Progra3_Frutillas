package pe.edu.pucp.frutilla.crud.dao.rrhh;

import java.sql.SQLException;
import java.util.ArrayList;
import pe.edu.pucp.frutilla.models.rrhh.Cliente; //Cliente

public interface ClienteDAO {
    void insertarCliente(Cliente cliente) throws SQLException; 
    Cliente obtenerClientePorId(int idCliente) throws SQLException;
    void actualizarCliente(Cliente cliente) throws SQLException;
    void eliminarCliente(int idCliente) throws SQLException;
    ArrayList<Cliente> obtenerTodos()throws SQLException;
}
