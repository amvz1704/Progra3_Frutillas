/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.frutilla.Inventario.dao;

/**
 *
 * @author Regina Sanchez
 */
import com.frutilla.models.Inventario.Producto;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ProductoDAO {
    int insertar(Producto producto,int idLocal) throws SQLException;
    int actualizarStock (int cant,int idProducto,int idLocal) 
            throws SQLException;
    void eliminar(int idProducto,int idLocal)throws SQLException;
    Producto obtenerProducto(int idProducto,int idLocal)throws SQLException;
    ArrayList<Producto> obtenerTodos(int idLocal)throws SQLException; 
}
