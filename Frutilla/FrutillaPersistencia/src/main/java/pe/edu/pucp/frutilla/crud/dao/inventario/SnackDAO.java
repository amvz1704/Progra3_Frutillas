/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.frutilla.crud.dao.inventario;

import java.util.ArrayList;
import pe.edu.pucp.frutilla.models.inventario.Snack;
import pe.edu.pucp.frutilla.crud.dao.BaseDAO;

public interface SnackDAO extends BaseDAO<Snack>{
    //metodos extra en caso sea necesario
    ArrayList<Snack> obtenerTodosPorLocal(int idLocal);
}
