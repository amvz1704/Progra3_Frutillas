/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.frutilla.Inventario.dao;

import com.frutilla.models.Inventario.Fruta;
import java.sql.SQLException;
import java.util.ArrayList;

public interface FrutaDAO {
    int insertar(Fruta fruta,int idLocal) throws SQLException;
    int actualizar(Fruta fruta,int idLocal)throws SQLException;
    void eliminar (int idProducto,int idLocal) throws SQLException;
    Fruta obtenerDatosFruta(int idProducto,int idLocal)throws SQLException;
    ArrayList<Fruta> obtenerTodos(int idLocal) throws SQLException;
}
