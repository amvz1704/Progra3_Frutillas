
package pe.edu.pucp.frutilla.crud.dao.venta;

import pe.edu.pucp.frutilla.models.venta.ComprobantePago;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ComprobantePagoDAO extends BaseDAO<ComprobantePago> {

    // Métodos extras
    ArrayList<ComprobantePago> obtenerTodosPorLocal(int idLocal);
}
