/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.frutilla.Inventario.main;

import com.frutilla.models.Inventario.*;
import com.frutilla.Inventario.mysql.*;
import java.sql.SQLException;

public class Principal {
    public static void main(String []args) throws SQLException{
        Snack sna1=new Snack(0, "galleta 1", "galleta de salvado", "GAL", 3.5, 
                10, 5, "galleta", "empaquetado propio", false, false);
        Snack sna2=new Snack(1, "keke 1", "keke de platano", "KEK", 2.5, 
                11, 3, "keke", "empaquetado propio", false, false);
        sna1.setTipoEstado(TipoEstado.DISPONIBLE);
        sna2.setTipoEstado(TipoEstado.DISPONIBLE);
        SnackMySQL snaSQL=new SnackMySQL();
        snaSQL.insertar(sna1, 1);
    }
}

