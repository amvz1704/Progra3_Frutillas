package pe.edu.pucp.frutilla.crud.dao.venta;

import pe.edu.pucp.frutilla.models.venta.LineaOrdenDeVenta;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.frutilla.crud.dao.BaseDAO;

public interface LineaOrdenVentaDAO extends BaseDAO<LineaOrdenDeVenta> {
    
    List<LineaOrdenDeVenta> listarPorIdOrden(int idOrdenVenta) throws SQLException;
}