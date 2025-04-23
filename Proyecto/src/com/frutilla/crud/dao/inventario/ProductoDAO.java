/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.frutilla.crud.dao.inventario;

import com.frutilla.models.inventario.Producto;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ProductoDAO {
    void insertarProducto(Producto producto) throws SQLException;
    void actualizarProducto(Producto producto) throws SQLException;
    //recordar que este es una eliminación logica
    void eliminarProducto(int idProducto,int idLocal)throws SQLException;
    Producto obtenerProductoPorId(int idProducto)throws SQLException;
    //este obtener todos se puede usar para sacar una version general
    //de todo el inventario de un local
    ArrayList<Producto> obtenerTodos(int idLocal)throws SQLException; 
    //Si necesitas sacar información especifica utilizar el obtener todos de 
    //InventarioMySQL
}
