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
    int insertarProducto(Producto producto) throws SQLException;
    //hay 2 actualiza uno que llama al procedure actualizar_stock para inventario 
    //y el otro ingresa un producto completo para actualizar todo los parametros
    int actualizarStock (int cant,int idProducto,int idLocal) 
            throws SQLException;
    int actualizarProducto(Producto producto) throws SQLException;
    //recordar que este es una eliminación logica
    void eliminarProducto(int idProducto,int idLocal)throws SQLException;
    Producto obtenerProductoPorId(int idProducto)throws SQLException;
    ArrayList<Producto> obtenerTodos(int idLocal)throws SQLException; 
}
