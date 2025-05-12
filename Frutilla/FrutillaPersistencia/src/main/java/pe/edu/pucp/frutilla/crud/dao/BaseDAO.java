package pe.edu.pucp.frutilla.crud.dao;

import java.util.ArrayList;
import java.util.List;

public interface BaseDAO<T> {
    void agregar(T entidad);
    T obtener(Integer id);
    ArrayList<T> listarTodos();
    void actualizar(T entidad);
    void eliminar(Integer id);
}