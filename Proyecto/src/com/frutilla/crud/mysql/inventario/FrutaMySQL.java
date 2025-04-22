/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
 
package com.frutilla.crud.mysql.inventario;

import com.frutilla.config.DBManager;

import com.frutilla.crud.dao.inventario.FrutaDAO; //interfaz de Fruta

import com.frutilla.models.inventario.Fruta;
import com.frutilla.models.inventario.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class FrutaMySQL implements FrutaDAO{
    @Override
    public int insertar(Fruta fruta,int idLocal) throws SQLException{
        int result=0;
        ProductoMySQL padre=new ProductoMySQL();
        result=padre.insertar(fruta,idLocal);
        fruta.setIdProducto(result);
        String query="INSERT INTO Fruta (idProducto,requiereLimpieza,"
                + "estaLimpio,requiereEnvasado,estaEnvasado,envase)"
                + "VALUES (?,?,?,?,?,?)";
        try(Connection con=DBManager.getConnection();
             PreparedStatement ps=con.prepareStatement(query,
                     PreparedStatement.RETURN_GENERATED_KEYS)){
            ps.setInt(1,fruta.getIdProducto());
            ps.setBoolean(2,fruta.isRequiereLimpieza());
            ps.setBoolean(3,fruta.isEstaLimpio());
            ps.setBoolean(4,fruta.isRequiereEnvase());
            ps.setBoolean(5,fruta.isEstaEnvasado());
            ps.setString(6,fruta.getEnvase());
            result=ps.executeUpdate();
        }
        return result;
    }
    @Override
    public int actualizar (Fruta fruta,int idLocal)throws SQLException{
        int result=0;
        ProductoMySQL padre=new ProductoMySQL();
        result=padre.actualizarProducto(fruta, idLocal);
        fruta.setIdProducto(result);
        String query = """
                UPDATE Fruta JOIN Producto ON 
                Fruta.idProducto=Producto.idProducto 
                SET Fruta.requiereLimpieza=?,
                Fruta.requiereEnvasado=?,Fruta.estaLimpio=?, 
                Fruta.estaEnvasado=?,Fruta.envase=? WHERE 
                Producto.idProducto=? AND Producto.idLocal=?""";
        try (Connection con=DBManager.getConnection();
             PreparedStatement ps=con.prepareStatement(query)){
            ps.setBoolean(1, fruta.isRequiereLimpieza());
            ps.setBoolean(2, fruta.isRequiereEnvase());
            ps.setBoolean(3, fruta.isEstaLimpio());
            ps.setBoolean(4, fruta.isEstaEnvasado());
            ps.setString(5, fruta.getEnvase());
            ps.setInt(6, fruta.getIdProducto());
            ps.setInt(7, idLocal);
            result=ps.executeUpdate();
        }
        return result;
    }
    @Override
    public void eliminar (int idProducto,int idLocal) throws SQLException{
        ProductoMySQL pro=new ProductoMySQL();
        pro.eliminar(idProducto, idLocal);
    }
    @Override
    public Fruta obtenerDatosFruta(int idProducto,int idLocal)throws 
            SQLException{
        ProductoMySQL pro=new ProductoMySQL();
        Producto temp=pro.obtenerProducto(idProducto, idLocal);
        //necesitamos implementar un constructor por parametros
        Fruta fru=new Fruta(temp);
        String query="SELECT requiereLimpieza, estaLimpio,requiereEnvasado, "
                + " estaEnvasado, envase FROM Fruta,Producto WHERE "
                + " Producto.idProducto=Fruta.idProducto "
                + " AND Fruta.idProducto=? AND Producto.idLocal = ?";
        try (Connection con=DBManager.getConnection();
             PreparedStatement ps=con.prepareStatement(query)){
            ps.setInt(1, idProducto);
            ps.setInt(2, idLocal);
            try (ResultSet rs = ps.executeQuery()) {
                while(rs.next()){
                    fru.setRequiereLimpieza(rs.getBoolean("requiereLimpieza"));
                    fru.setEstaLimpio(rs.getBoolean("estaLimpio"));
                    fru.setRequiereEnvase(rs.getBoolean("requiereEnvasado"));
                    fru.setEstaEnvasado(rs.getBoolean("estaEnvasado"));
                    fru.setEnvase(rs.getString("envase"));
                }
            }
        }
        return fru;
    }
    @Override
    public ArrayList<Fruta> obtenerTodos(int idLocal) throws SQLException{
        ArrayList<Fruta> frutas= new ArrayList<Fruta> ();
        ProductoMySQL pro=new ProductoMySQL();
        String query="SELECT idProducto,requiereLimpieza,"
                + "estaLimio,requiereEnvasado,estaEnvasado,envase"
                + "FROM Fruta,Producto WHERE Fruta.idProducto = "
                + "Producto.idProducto AND Producto.idLocal = ?";
        try(Connection con=DBManager.getConnection();
             PreparedStatement ps=con.prepareStatement(query)){
            ps.setInt(1, idLocal);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Fruta fru=new Fruta(pro.obtenerProducto
                (rs.getInt("idProducto"), idLocal));
                fru.setRequiereLimpieza(rs.getBoolean("requiereLimpieza"));
                fru.setEstaLimpio(rs.getBoolean("estaLimpio"));
                fru.setRequiereEnvase(rs.getBoolean("requiereEnvasado"));
                fru.setEstaEnvasado(rs.getBoolean("estaEnvasado"));
                fru.setEnvase(rs.getString("envase"));
                frutas.add(fru);
            }
            rs.close(); 
        }
        return frutas;
    }
}
