/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.frutilla.Inventario.mysql;

import com.frutilla.config.DBManager;
import com.frutilla.Inventario.dao.SnackDAO;
import com.frutilla.models.Inventario.Snack;
import com.frutilla.models.Inventario.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SnackMySQL implements SnackDAO{
    @Override
    public int insertar(Snack snack,int idLocal) throws SQLException{
         int result=0;
        ProductoMySQL padre=new ProductoMySQL();
        result=padre.insertar(snack,idLocal);
        snack.setIdProducto(result);
        String query="INSERT INTO Snack (idProducto,tipo,requiereEnvase,"
                + "estaEnvasado,envase)VALUES (?,?,?,?,?)";
        try(Connection con=DBManager.getConnection();
             PreparedStatement ps=con.prepareStatement(query)){
            ps.setInt(1, snack.getIdProducto());
            ps.setString(2,snack.getTipo());
            ps.setBoolean(3,snack.isRequiereEnvase());
            ps.setBoolean(4,snack.isEstaEnvasado());
            ps.setString(5,snack.getEnvase());
            result=ps.executeUpdate();
        }
        return result;
    }
    @Override
    public int actualizar(Snack snack,int idLocal) throws SQLException{
        int result=0;
        ProductoMySQL pro=new ProductoMySQL();
        pro.actualizarProducto(snack, idLocal);
        String query = """
                UPDATE Snack JOIN Producto ON 
                Snack.idProducto=Producto.idProducto
                SET Snack.tipo=?,Snack.requiereEnvase=?,
                Snack.estaEnvasado=?,Snack.envase=? WHERE Producto.idProducto=?
                AND Producto.idLocal=?""";
        try (Connection con=DBManager.getConnection();
             PreparedStatement ps=con.prepareStatement(query)){
            ps.setString(1, snack.getTipo());
            ps.setBoolean(2, snack.isRequiereEnvase());
            ps.setBoolean(3, snack.isEstaEnvasado());
            ps.setString(4, snack.getEnvase());
            ps.setInt(5, snack.getIdProducto());
            ps.setInt(6, idLocal);
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
    public Snack obtenerDatosSnack(int idProducto,int idLocal)
            throws SQLException{
        ProductoMySQL pro=new ProductoMySQL();
        Producto temp=pro.obtenerProducto(idProducto, idLocal);
        Snack sna=new Snack(temp);
        String query="SELECT requiereEnvase, estaEnvasado, envase FROM "
                + " Snack,Producto WHERE Producto.idProducto=Snack.idProducto "
                + " AND Snack.idProducto=? AND Producto.idLocal = ?";
        try (Connection con=DBManager.getConnection();
             PreparedStatement ps=con.prepareStatement(query)){
            ps.setInt(1, idProducto);
            ps.setInt(2, idLocal);
            try (ResultSet rs = ps.executeQuery()) {
                while(rs.next()){
                    sna.setRequiereEnvase(rs.getBoolean("requiereEnvase"));
                    sna.setEstaEnvasado(rs.getBoolean("estaEnvasado"));
                    sna.setEnvase(rs.getString("envase"));
                }
            }
        }
        return sna;
    }
    @Override
    public ArrayList<Snack> obtenerTodos(int idLocal) throws SQLException{
        ArrayList<Snack> snacks= new ArrayList<Snack> ();
        ProductoMySQL pro=new ProductoMySQL();
        String query="SELECT idProducto,requiereEnvasado,"
                + "estaEnvasado,envase FROM Fruta,Producto WHERE "
                + "Fruta.idProducto = Producto.idProducto AND "
                + "Producto.idLocal = ?";
        try(Connection con=DBManager.getConnection();
             PreparedStatement ps=con.prepareStatement(query)){
            ps.setInt(1, idLocal);
            try (ResultSet rs = ps.executeQuery()) {
                while(rs.next()){
                    Snack sna=new Snack(pro.obtenerProducto
                        (rs.getInt("idProducto"), idLocal));
                    sna.setRequiereEnvase(rs.getBoolean("requiereEnvasado"));
                    sna.setEstaEnvasado(rs.getBoolean("estaEnvasado"));
                    sna.setEnvase(rs.getString("envase"));
                    snacks.add(sna);
                }
            }
        }
        return snacks;
    }
}
