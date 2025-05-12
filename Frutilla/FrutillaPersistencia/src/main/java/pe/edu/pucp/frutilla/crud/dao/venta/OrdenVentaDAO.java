package pe.edu.pucp.frutilla.crud.dao.venta;

import pe.edu.pucp.frutilla.models.venta.OrdenVenta;
import java.sql.SQLException;
import java.util.List;
import pe.edu.pucp.frutilla.crud.dao.BaseDAO;

public interface OrdenVentaDAO extends BaseDAO<OrdenVenta> {
    // Metodos para listas
    List<OrdenVenta> listarPorCliente(int idCliente) throws SQLException;
    List<OrdenVenta> listarPorLocal(int idLocal) throws SQLException;
    List<OrdenVenta> listarPorEmpleado(int idEmpleado) throws SQLException;
}