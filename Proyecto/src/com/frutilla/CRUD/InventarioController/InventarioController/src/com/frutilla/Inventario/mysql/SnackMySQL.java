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
        padre.insertar(snack,idLocal);
        String query="INSERT INTO Snack (idProducto,requiereEnvase,"
                + "estaEnvasado,envase)VALUES (?,?,?,?)";
        try(Connection con=DBManager.getConnection();
             PreparedStatement ps=con.prepareStatement(query)){
            //falta implementar estos gets/sets
//            ps.setInt(1,snack.getIdProducto());
//            ps.setBoolean(2,snack.isRequiereEnvase());
//            ps.setBoolean(3,snack.isEnvasado());
//            ps.setString(4,snack.getEnvase());
            result=ps.executeUpdate();
        }
        return result;
    }
    @Override
    public int actualizarEnvasado(int idProducto,boolean envasado,String envase,
             int idLocal) throws SQLException{
        int result=0;
        String query = """
                UPDATE Snack JOIN Producto ON 
                Snack.idProducto=Producto.idProducto
                SET Snack.estaEnvasado=?,Snack.envase=? WHERE 
                Producto.idLocal=?""";
        try (Connection con=DBManager.getConnection();
             PreparedStatement ps=con.prepareStatement(query)){
            ps.setBoolean(1, envasado);
            ps.setString(2, envase);
            ps.setInt(3, idLocal);
            result=ps.executeUpdate();
        }
        return result;
    }
    @Override
    public Snack obtenerDatosSnack(int idProducto,int idLocal)
            throws SQLException{
        Producto temp=new Producto();
        ProductoMySQL pro=new ProductoMySQL();
        temp=pro.obtenerProducto(idProducto, idLocal);
        //necesitamos implementar un constructor por parametros
        Snack sna=new Snack(/*temp.getIdProducto(),temp.getNombre(),
        temp.getDescripcion(),temp.getCodigoProd(),temp.getStock(),
        temp.getStockMinimo()*/);
        String query="SELECT requiereEnvasado, estaEnvasado, envase FROM "
                + " Snack,Producto WHERE Producto.idProducto=Snack.idProducto "
                + " AND Snack.idProducto=? AND Producto.idLocal = ?";
        try (Connection con=DBManager.getConnection();
             PreparedStatement ps=con.prepareStatement(query)){
            ps.setInt(1, idProducto);
            ps.setInt(2, idLocal);
            ResultSet rs=ps.executeQuery();
            sna.setIdProducto(idProducto);
            while(rs.next()){
//              sna.setRequiereEnvase(rs.getBoolean("requiereEnvasado"));
//              sna.setEnvasado(rs.getBoolean("estaEnvasado"));
//              sna.setEnvase(rs.getString("envase"));
            }
            rs.close();
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
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Snack sna=new Snack(/*pro.obtenerProducto
                (rs.getInt("idProducto"), idLocal);*/);
//                sna.setIdProducto(rs.getInt("idProducto"));
//                sna.setRequiereEnvasado(rs.getBoolean("requiereEnvasado"));
//                sna.setEnvasado(rs.getBoolean("estaEnvasado"));
//                sna.setEnvase(rs.getString("envase"));
                snacks.add(sna);
            }
            rs.close(); 
        }
        return snacks;
    }
}
