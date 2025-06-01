package pe.edu.pucp.frutilla.crud.dao.local;


/**
 *
 * @author Nayane 20210489_
 */
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.frutilla.crud.dao.BaseDAO;
import pe.edu.pucp.frutilla.models.inventario.Producto;
import pe.edu.pucp.frutilla.models.local.Local;
import pe.edu.pucp.frutilla.models.rrhh.Empleado;
import pe.edu.pucp.frutilla.models.venta.OrdenVenta; 

public interface LocalDAO extends BaseDAO<Local>
{   
    //Metodos extra para obtener los empleados
    ArrayList<Empleado> encontrarEmpleados(int idLocal) throws SQLException; 
    ArrayList<Producto> encontrarProductos(int idLocal) throws SQLException;
    List<OrdenVenta> encontrarVentas(int idLocal) throws SQLException; //ver si necesitamos agregar fecha

}
