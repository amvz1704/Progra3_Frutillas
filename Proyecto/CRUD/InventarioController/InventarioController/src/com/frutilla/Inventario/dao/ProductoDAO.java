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
    //hay 2 actualiza uno que llama al procedure actualizar_stock y el otro
    //ingresa un producto completo para actualizar todo los parametros
    //en caso de alguna actualización particular en ellos
    int actualizarStock (int cant,int idProducto,int idLocal) 
            throws SQLException;
    int actualizarProducto(Producto producto, int idLocal) throws SQLException;
    //recordar que este es una eliminación logica
    void eliminar(int idProducto,int idLocal)throws SQLException;
    Producto obtenerProducto(int idProducto,int idLocal)throws SQLException;
    ArrayList<Producto> obtenerTodos(int idLocal)throws SQLException; 
}
