/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.frutilla.crud.mysql.inventario;

import pe.edu.pucp.frutilla.config.DBManager;
import pe.edu.pucp.frutilla.crud.dao.inventario.SnackDAO;
import pe.edu.pucp.frutilla.models.inventario.Snack;
import pe.edu.pucp.frutilla.models.inventario.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class SnackMySQL implements SnackDAO{
    @Override
    public void insertarSnack(Snack snack) throws SQLException{
        int id = 0;
        ProductoMySQL padre=new ProductoMySQL();
        id = padre.insertarProductoDevolverID(snack);
        snack.setIdProducto(id);
        String query="INSERT INTO Snack (idProducto,tipo,requiereEnvase,"
                + "estaEnvasado,envase)VALUES (?,?,?,?,?)";
        try(Connection con= DBManager.getInstance().getConnection();
             PreparedStatement ps=con.prepareStatement(query)){
            ps.setInt(1, snack.getIdProducto());
            ps.setString(2,snack.getTipo());
            ps.setBoolean(3,snack.isRequiereEnvase());
            ps.setBoolean(4,snack.isEstaEnvasado());
            ps.setString(5,snack.getEnvase());
            ps.executeUpdate();
        }
    }
    @Override
    public void actualizarSnack(Snack snack) throws SQLException{
        ProductoMySQL pro=new ProductoMySQL();
        pro.actualizarProducto(snack);
        String query = """
                UPDATE Snack JOIN Producto ON 
                Snack.idProducto=Producto.idProducto
                SET Snack.tipo=?,Snack.requiereEnvase=?,
                Snack.estaEnvasado=?,Snack.envase=? WHERE 
                Producto.idProducto=?""";
        try (Connection con=DBManager.getInstance().getConnection();
             PreparedStatement ps=con.prepareStatement(query)){
            ps.setString(1, snack.getTipo());
            ps.setBoolean(2, snack.isRequiereEnvase());
            ps.setBoolean(3, snack.isEstaEnvasado());
            ps.setString(4, snack.getEnvase());
            ps.setInt(5, snack.getIdProducto());
            ps.executeUpdate();
        }
    }
    @Override
    public void eliminarSnack(int idProducto,int idLocal) throws SQLException{
        ProductoMySQL pro=new ProductoMySQL();
        pro.eliminarProducto(idProducto, idLocal);
    }
    @Override
    public Snack obtenerSnackPorId(int idProducto) throws SQLException{
        ProductoMySQL pro=new ProductoMySQL();
        Producto temp=pro.obtenerProductoPorId(idProducto);
        Snack sna=new Snack(temp);
        String query="SELECT tipo,requiereEnvase, estaEnvasado, envase FROM "
                + " Snack,Producto WHERE Producto.idProducto=Snack.idProducto "
                + " AND Snack.idProducto=?";
        try (Connection con=DBManager.getInstance().getConnection();
             PreparedStatement ps=con.prepareStatement(query)){
            ps.setInt(1, idProducto);
            try (ResultSet rs = ps.executeQuery()) {
                while(rs.next()){
                    sna.setTipo(rs.getString("tipo"));
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
        String query="SELECT Inventario.idProducto FROM Snack,Inventario WHERE "
                + "Snack.idProducto = Inventario.idProducto AND "
                + "Inventario.idLocal = ?";
        try(Connection con=DBManager.getInstance().getConnection();
             PreparedStatement ps=con.prepareStatement(query)){
            ps.setInt(1, idLocal);
            try (ResultSet rs = ps.executeQuery()) {
                while(rs.next()){
                    Snack sna=obtenerSnackPorId(rs.getInt("idProducto"));
                    snacks.add(sna);
                }
            }
        }
        return snacks;
    }
}
