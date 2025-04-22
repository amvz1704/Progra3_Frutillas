/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.frutilla.Inventario.main;

import com.frutilla.models.Inventario.*;
import com.frutilla.Inventario.mysql.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class Principal {
    public static void main(String []args) throws SQLException{
        Snack sna1=new Snack(0, "galleta 1", "galleta de salvado", "GAL", 3.5, 
                10, 5, "galleta", "empaquetado propio", false, false);
        Snack sna2=new Snack(1, "keke 1", "keke de platano", "KEK", 2.5, 
                11, 3, "keke", "empaquetado propio", false, false);
        sna1.setTipoEstado(TipoEstado.DISPONIBLE);
        sna2.setTipoEstado(TipoEstado.DISPONIBLE);
//        SnackMySQL snaSQL=new SnackMySQL();
//        snaSQL.insertar(sna1, 1);
////        snaSQL.insertar(sna2, 1);
//        sna1.setEstaEnvasado(true);
//        snaSQL.actualizar(sna1, 1);
//        Snack prueba=snaSQL.obtenerDatosSnack(3, 1);
//        System.out.println(prueba);
        ArrayList<FrutasBebida> ejmeok=new ArrayList<>();
        Bebida beb1=new Bebida(4, "jugo", "jugo de frutas", "JUG", 7.5, 4, 1, 7,
                "jugo", "stevia", TipoLeche.ENTERA, ejmeok);
        beb1.setTipoEstado(TipoEstado.DISPONIBLE);
//        BebidaMySQL beb=new BebidaMySQL();
//        beb.insertar(beb1, 1);
//        beb1.setEndulzante("azucar_rubia");
//        beb.actualizar(beb1, 1);
//        beb.eliminar(11, 1);
        Fruta fru1=new Fruta(6, "platano", "platano de la isla", "FRU", 2, 
                3, 1, true, true, true, true, "no tiene envase");
        fru1.setTipoEstado(TipoEstado.DISPONIBLE);
        FrutaMySQL fru=new FrutaMySQL();
        fru.insertar(fru1, 1);
    }
}

