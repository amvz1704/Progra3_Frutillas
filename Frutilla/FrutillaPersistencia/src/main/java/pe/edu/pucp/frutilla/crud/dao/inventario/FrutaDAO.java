/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.frutilla.crud.dao.inventario;

import java.util.ArrayList;
import pe.edu.pucp.frutilla.models.inventario.Fruta;
import pe.edu.pucp.frutilla.crud.dao.BaseDAO;


public interface FrutaDAO extends BaseDAO<Fruta>{
     //Agregar metodos unicos en caso haya
    ArrayList<Fruta> obtenerTodosPorLocal(int idLocal);
}
