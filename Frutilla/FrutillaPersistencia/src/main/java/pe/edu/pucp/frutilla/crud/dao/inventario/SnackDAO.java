/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.frutilla.crud.dao.inventario;

import pe.edu.pucp.frutilla.models.inventario.Snack;
import java.util.ArrayList;
import java.sql.SQLException;

public interface SnackDAO {
     void insertarSnack(Snack snack) throws SQLException;
     void actualizarSnack(Snack snack) throws SQLException;
     void eliminarSnack (int idProducto,int idLocal) throws SQLException;
     Snack obtenerSnackPorId(int idProducto) throws SQLException;
     ArrayList<Snack> obtenerTodos(int idLocal) throws SQLException;
}
