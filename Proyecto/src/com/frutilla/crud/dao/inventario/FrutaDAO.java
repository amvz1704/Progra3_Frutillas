/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.frutilla.crud.dao.inventario;

import com.frutilla.models.inventario.Fruta;
import java.sql.SQLException;
import java.util.ArrayList;


public interface FrutaDAO {
    int insertarFruta(Fruta fruta) throws SQLException;
    int actualizarFruta(Fruta fruta)throws SQLException;
    void eliminarFruta(int idProducto,int idLocal) throws SQLException;
    Fruta obtenerFrutaPorId(int idProducto)throws SQLException;
    ArrayList<Fruta> obtenerTodos(int idLocal) throws SQLException;
}
