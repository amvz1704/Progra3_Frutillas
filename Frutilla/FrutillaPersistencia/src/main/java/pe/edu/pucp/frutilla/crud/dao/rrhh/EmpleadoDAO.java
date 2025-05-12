package pe.edu.pucp.frutilla.crud.dao.rrhh;

import java.util.ArrayList;

import pe.edu.pucp.frutilla.crud.dao.BaseDAO;
import pe.edu.pucp.frutilla.models.rrhh.Empleado; //Empleado

public interface EmpleadoDAO extends BaseDAO<Empleado> {

    public ArrayList<Empleado> listarTodosPorLocal(int idLocal);

}
