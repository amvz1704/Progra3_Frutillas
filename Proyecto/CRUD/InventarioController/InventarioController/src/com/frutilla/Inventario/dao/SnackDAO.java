/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.frutilla.Inventario.dao;

/**
 *
 * @author Desktop
 */
import com.frutilla.models.Inventario.Snack;
import java.util.ArrayList;
import java.sql.SQLException;


public interface SnackDAO {
     int insertar(Snack snack,int idLocal) throws SQLException;
     int actualizar(Snack snack,int idLocal) throws SQLException;
     void eliminar (int idProducto,int idLocal) throws SQLException;
     Snack obtenerDatosSnack(int idProducto,int idLocal) throws SQLException;
     ArrayList<Snack> obtenerTodos(int idLocal) throws SQLException;
}
