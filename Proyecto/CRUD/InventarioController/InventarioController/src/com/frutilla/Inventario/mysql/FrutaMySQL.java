/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.frutilla.Inventario.mysql;

import com.frutilla.config.DBManager;
import com.frutilla.Inventario.dao.FrutaDAO;
import com.frutilla.models.Inventario.Fruta;
import com.frutilla.models.Inventario.Producto;
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
        padre.insertar(fruta,idLocal);
        String query="INSERT INTO Bebida (idProducto,requiereLimpieza,"
                + "estaLimpio,requiereEnvase,estaEnvasado,envase)"
                + "VALUES (?,?,?,?,?,?)";
        try(Connection con=DBManager.getConnection();
             PreparedStatement ps=con.prepareStatement(query)){
            //falta implementar estos gets/sets
//            ps.setInt(1,fruta.getIdProducto());
//            ps.setBoolean(2,fruta.getRequiereLimpieza());
//            ps.setBoolean(3,fruta.isLimpio());
//            ps.setBoolean(4,fruta.getRequiereEnvase());
//            ps.setBoolean(5,fruta.isEnvasado());
//            ps.setString(6,fruta.getEnvase());
            result=ps.executeUpdate();
        }
        return result;
    }
    @Override
    public int actualizarProcesado (int idProducto,boolean limpieza,
            boolean envasado,String envase,int idLocal)throws SQLException{
        int result=0;
        String query = """
                UPDATE Fruta JOIN Producto ON 
                Fruta.idProducto=Producto.idProducto 
                SET Fruta.estaLimpio=?, Fruta.estaEnvasado=?,
                Fruta.envase=? WHERE Producto.idLocal=?""";
        try (Connection con=DBManager.getConnection();
             PreparedStatement ps=con.prepareStatement(query)){
            ps.setBoolean(1, limpieza);
            ps.setBoolean(2, envasado);
            ps.setString(3, envase);
            ps.setInt(4, idLocal);
            result=ps.executeUpdate();
        }
        return result;
    }
    @Override
    public Fruta obtenerDatosFruta(int idProducto,int idLocal)throws 
            SQLException{
        Producto temp=new Producto();
        ProductoMySQL pro=new ProductoMySQL();
        temp=pro.obtenerProducto(idProducto, idLocal);
        //necesitamos implementar un constructor por parametros
        Fruta fru=new Fruta(/*temp.getIdProducto(),temp.getNombre(),
        temp.getDescripcion(),temp.getCodigoProd(),temp.getStock(),
        temp.getStockMinimo()*/);
        String query="SELECT requiereLimpieza, estaLimpio,"
                + "requiereEnvasado, estaEnvasado, envase FROM "
                + " Fruta,Producto WHERE Producto.idProducto=Fruta.idProducto "
                + " AND Fruta.idProducto=? AND Producto.idLocal = ?";
        try (Connection con=DBManager.getConnection();
             PreparedStatement ps=con.prepareStatement(query)){
            ps.setInt(1, idProducto);
            ps.setInt(2, idLocal);
            ResultSet rs=ps.executeQuery();
            fru.setIdProducto(idProducto);
            while(rs.next()){
//              fru.setRequiereLimpieza(rs.getBoolean("requiereLimpieza"));
//              fru.setLimpio(rs.getBoolean("estaLimpio"));
//              fru.setRequiereEnvase(rs.getBoolean("requiereEnvasado"));
//              fru.setEnvasado(rs.getBoolean("estaEnvasado"));
//              fru.setEnvase(rs.getString("envase"));
            }
            rs.close();
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
                Fruta fru=new Fruta(/*pro.obtenerProducto
                (rs.getInt("idProducto"), idLocal);*/);
//                fru.setIdProducto(rs.getInt("idProducto"));
//                fru.setRequiereLimpieza(rs.getBoolean("requiereLimpieza"));
//                fru.setLimpio(rs.getBoolean("estaLimpio"));
//                fru.setRequiereEnvasado(rs.getBoolean("requiereEnvasado"));
//                fru.setEnvasado(rs.getBoolean("estaEnvasado"));
//                fru.setEnvase(rs.getString("envase"));
                frutas.add(fru);
            }
            rs.close(); 
        }
        return frutas;
    }
}
