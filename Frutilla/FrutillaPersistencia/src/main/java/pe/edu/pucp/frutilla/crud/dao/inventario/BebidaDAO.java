/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.frutilla.crud.dao.inventario;

/**
 * 
 * @author Regina 
 */


import java.util.ArrayList;
import pe.edu.pucp.frutilla.models.inventario.Bebida;
import pe.edu.pucp.frutilla.crud.dao.*;


public interface BebidaDAO extends BaseDAO<Bebida>{
    //metodos extra
    ArrayList<Bebida> obtenerTodosPorLocal(int idLocal);
}
