/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.frutilla.Inventario.dao;

/**
 *
 * @author Regina 
 */

import com.frutilla.models.Inventario.Bebida;
import com.frutilla.models.Inventario.FrutasBebida;
import java.sql.SQLException;
import java.util.ArrayList;

public interface BebidaDAO {
    int insertar(Bebida bebida,int idLocal) throws SQLException;
    int actualizar(FrutasBebida f) throws SQLException; //lo pongo pero no estoy segura que se tenga
    //que hacer
    Bebida obtenerDatosBebida (int idProducto,int idLocal) throws SQLException;
    //no se coloca eliminar porque el eliminado logico se hace a nivel de 
    //producto
    ArrayList<Bebida> obtenerTodos(int idLocal) throws SQLException;
}
