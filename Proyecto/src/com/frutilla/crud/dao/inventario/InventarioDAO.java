/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.frutilla.crud.dao.inventario;

import com.frutilla.models.inventario.Producto;
import java.sql.SQLException;
import java.util.ArrayList; 

public interface InventarioDAO {
    //inserta un producto en el inventario de un local particular
    int insertarInventario(Producto producto,int idLocal) throws SQLException;
    //actualiza datos de Inventario
    int actualizarInventario(Producto producto,int idLocal) throws SQLException;
    //Devuelve el stock del producto particular en un local particular
    int obtenerInventarioPorId(int idProducto,int idLocal) throws SQLException;
    //Este es para poder jalar todos los productos asociados a un local
    //particular
    ArrayList<Producto> obtenerTodos(int idLocal) throws SQLException;
}

