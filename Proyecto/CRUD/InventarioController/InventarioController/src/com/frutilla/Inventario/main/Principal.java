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
        //Prueba de SNACK
//        Snack sna1=new Snack(0, "galleta 1", "galleta de salvado", "GAL", 3.5, 
//                10, 5, "galleta", "empaquetado propio", false, false);
//        Snack sna2=new Snack(1, "keke 1", "keke de platano", "KEK", 2.5, 
//                11, 3, "keke", "empaquetado propio", false, false);
//        sna1.setTipoEstado(TipoEstado.DISPONIBLE);
//        sna2.setTipoEstado(TipoEstado.DISPONIBLE);
//        SnackMySQL snaSQL=new SnackMySQL();
//        snaSQL.insertarSnack(sna1);
//        snaSQL.insertarSnack(sna2);
//        Snack sna3=snaSQL.obtenerSnackPorId(1);
//        sna3.setEstaEnvasado(true);
//        snaSQL.actualizarSnack(sna3);
//        Snack prueba=snaSQL.obtenerSnackPorId(2);
//        System.out.println(prueba);
         //FINAL PRUEBA SNACK
         
         //Prueba de BEBIDA
//        ArrayList<FrutasBebida> ejmeok=new ArrayList<>();
//        Bebida beb1=new Bebida(4, "jugo", "jugo de frutas", "JUG", 7.5, 4, 1, 7,
//                "jugo", "stevia", TipoLeche.ENTERA, ejmeok);
//        beb1.setTipoEstado(TipoEstado.DISPONIBLE);
//        BebidaMySQL beb=new BebidaMySQL();
//        beb.insertarBebida(beb1);
//        Bebida beb2=beb.obtenerBebidaPorId(3);
//        beb2.setEndulzante("azucar_rubia");
//        beb.actualizarBebida(beb2);
          //FINAL PRUEBA BEBIDA
          
          //Prueba de FRUTA
//        Fruta fru1=new Fruta(6, "platano", "platano de la isla", "FRU", 2, 
//                3, 1, true, true, true, true, "no tiene envase");
//        fru1.setTipoEstado(TipoEstado.DISPONIBLE);
//        FrutaMySQL fru=new FrutaMySQL();
//        fru.insertarFruta(fru1);
//        Fruta fru2=fru.obtenerFrutaPorId(4);
//        fru2.setEstaLimpio(false);
//        fru.actualizarFruta(fru2);
        //FINAL PRUEBA FRUTA
        
        //Prueba de INVENTARIO
//        Snack sna1=new Snack(0, "galleta 1", "galleta de salvado", "GAL", 3.5, 
//                10, 5, "galleta", "empaquetado propio", false, false);
//        sna1.setTipoEstado(TipoEstado.DISPONIBLE);
//        ArrayList<FrutasBebida> ejmeok=new ArrayList<>();
//        Bebida beb1=new Bebida(4, "jugo", "jugo de frutas", "JUG", 7.5, 4, 1, 7,
//                "jugo", "stevia", TipoLeche.ENTERA, ejmeok);
//        beb1.setTipoEstado(TipoEstado.DISPONIBLE);
//        Fruta fru1=new Fruta(6, "platano", "platano de la isla", "FRU", 2, 
//                3, 1, true, true, true, true, "no tiene envase");
//        fru1.setTipoEstado(TipoEstado.DISPONIBLE);
        InventarioMySQL invSQL=new InventarioMySQL();
//        invSQL.insertarInventario(fru1, 1);
//        invSQL.insertarInventario(beb1, 1);
        //Prueba de actualizacion
//        SnackMySQL snaSQL=new SnackMySQL();
//        Snack sna=snaSQL.obtenerSnackPorId(1);
//        sna.setStock(invSQL.obtenerInventarioPorId(1, 1)-3);
//        if(sna.getStock()>sna.getStockMinimo())
//            sna.setTipoEstado(TipoEstado.DISPONIBLE);
//        else
//            sna.setTipoEstado(TipoEstado.AGOTADO);
//        invSQL.actualizarInventario(sna, 1);
        //Prueba recuperacion de datos totales
        ArrayList<Producto> productos=invSQL.obtenerTodos(1);
        for(Producto p:productos){
            System.out.println(p);
        }
       //FINAL PRUEBA INVENTARIO
    }
    
    
    
    
    
}

