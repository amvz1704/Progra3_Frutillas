/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.frutilla.crud.mysql.inventario;

import pe.edu.pucp.frutilla.config.DBManager;
import pe.edu.pucp.frutilla.crud.dao.inventario.FrutaDAO;
import pe.edu.pucp.frutilla.models.inventario.Fruta;
import pe.edu.pucp.frutilla.models.inventario.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class FrutaMySQL implements FrutaDAO{
    @Override
    public void insertarFruta(Fruta fruta) throws SQLException{
        int id = 0;
        ProductoMySQL padre=new ProductoMySQL();
        id=padre.insertarProductoDevolverID(fruta);
        fruta.setIdProducto(id);
        String query="INSERT INTO Fruta (idProducto,requiereLimpieza,"
                + "estaLimpio,requiereEnvasado,estaEnvasado,envase)"
                + "VALUES (?,?,?,?,?,?)";
        try(Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps=con.prepareStatement(query,
                     PreparedStatement.RETURN_GENERATED_KEYS)){
            ps.setInt(1,fruta.getIdProducto());
            ps.setBoolean(2,fruta.isRequiereLimpieza());
            ps.setBoolean(3,fruta.isEstaLimpio());
            ps.setBoolean(4,fruta.isRequiereEnvase());
            ps.setBoolean(5,fruta.isEstaEnvasado());
            ps.setString(6,fruta.getEnvase());
            ps.executeUpdate();
        }
    }
    @Override
    public void actualizarFruta (Fruta fruta)throws SQLException{
        ProductoMySQL padre=new ProductoMySQL();
        padre.actualizarProducto(fruta);
        String query = """
                UPDATE Fruta JOIN Producto ON 
                Fruta.idProducto=Producto.idProducto 
                SET Fruta.requiereLimpieza=?,
                Fruta.requiereEnvasado=?,Fruta.estaLimpio=?, 
                Fruta.estaEnvasado=?,Fruta.envase=? WHERE 
                Producto.idProducto=? """;
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps=con.prepareStatement(query)){
            ps.setBoolean(1, fruta.isRequiereLimpieza());
            ps.setBoolean(2, fruta.isRequiereEnvase());
            ps.setBoolean(3, fruta.isEstaLimpio());
            ps.setBoolean(4, fruta.isEstaEnvasado());
            ps.setString(5, fruta.getEnvase());
            ps.setInt(6, fruta.getIdProducto());
            ps.executeUpdate();
        }
    }
    @Override
    public void eliminarFruta (int idProducto,int idLocal) throws SQLException{
        ProductoMySQL pro=new ProductoMySQL();
        pro.eliminarProducto(idProducto, idLocal);
    }
    @Override
    public Fruta obtenerFrutaPorId(int idProducto)throws 
            SQLException{
        ProductoMySQL pro=new ProductoMySQL();
        Producto temp=pro.obtenerProductoPorId(idProducto);
        Fruta fru=new Fruta(temp);
        String query="SELECT requiereLimpieza, estaLimpio,requiereEnvasado, "
                + " estaEnvasado, envase FROM Fruta,Producto WHERE "
                + " Producto.idProducto=Fruta.idProducto "
                + " AND Fruta.idProducto=? ";
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps=con.prepareStatement(query)){
            ps.setInt(1, idProducto);
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
        String query="SELECT Inventario.idProducto FROM Fruta,Inventario "
                + "WHERE Fruta.idProducto = Inventario.idProducto "
                + "AND Inventario.idLocal = ?";
        try(Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps=con.prepareStatement(query)){
            ps.setInt(1, idLocal);
            try (ResultSet rs = ps.executeQuery()) {
                while(rs.next()){
                    Fruta fru=obtenerFrutaPorId(rs.getInt("idProducto"));
                    frutas.add(fru);
                }
            }
        }
        return frutas;
    }
}
