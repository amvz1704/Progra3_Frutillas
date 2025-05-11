/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.frutilla.crud.dao.inventario;

/**
 *
 * @author Regina Sanchez
 */

import pe.edu.pucp.frutilla.models.inventario.Producto;
import java.util.ArrayList;
import pe.edu.pucp.frutilla.crud.dao.BaseDAO;

public interface ProductoDAO extends BaseDAO<Producto> {
    //Metodos extra
    ArrayList<Producto> obtenerPorNombre(String nombre);
    ArrayList<Producto> obtenerTodosPorLocal(int idLocal);
}
