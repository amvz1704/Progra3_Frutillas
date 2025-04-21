package com.frutilla.crud.dao.rrhh;

import java.sql.SQLException;
import java.util.ArrayList;
import com.frutilla.models.rrhh.Empleado; //Empleado

public interface EmpleadoDAO {
    void insertarEmpleado(Empleado empleado, int idLocal) throws SQLException;
    Empleado obtenerEmpleadoPorId(int idCliente) throws SQLException;
    void actualizarEmpleado(Empleado empleado, int idLocal) throws SQLException;
    void eliminarEmpleado(int idEmpleado) throws SQLException;
    ArrayList<Empleado> obtenerEmpleados(int idLocal)throws SQLException;
}
