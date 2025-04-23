/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.frutilla.crud.dao.inventario;

/**
 * 
 * @author Regina 
 */


import com.frutilla.models.inventario.Bebida;
import java.sql.SQLException;
import java.util.ArrayList;

public interface BebidaDAO {
    int insertarBebida(Bebida bebida) throws SQLException;
    int actualizarBebida(Bebida bebida) throws SQLException;
    Bebida obtenerBebidaPorId (int idProducto) throws SQLException;
    void eliminarBebida (int idProducto,int idLocal) throws SQLException;
    ArrayList<Bebida> obtenerTodos(int idLocal) throws SQLException;
}
