/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.frutilla.crud.mysql.inventario;

import com.frutilla.crud.dao.inventario.InventarioDAO;
import com.frutilla.config.DBManager;
import com.frutilla.models.inventario.Producto;
import com.frutilla.models.inventario.Fruta;
import com.frutilla.models.inventario.Snack;
import com.frutilla.models.inventario.Bebida;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class InventarioMySQL implements InventarioDAO{
    @Override
    @SuppressWarnings("null")
    public int insertarInventario(Producto producto,int idLocal) 
            throws SQLException{
        int result=0;
        char tipo;
        //revisamos el tipo de producto que esta entrando
        if (producto instanceof Snack)
            tipo='S';
        else if (producto instanceof Fruta)
            tipo='F';
        else if (producto instanceof Bebida)
            tipo='B';
        else
            tipo='P';
        String query="INSERT INTO Inventario (idProducto,idLocal,"
                + "stock,estado,tipo) VALUES (?,?,?,?,?)";
        try (Connection con=DBManager.getInstance().getConnection();
             PreparedStatement ps=con.prepareStatement(query);){
            ps.setInt(1, producto.getIdProducto());
            ps.setInt(2, idLocal);
            ps.setInt(3, producto.getStock());
            ps.setString (4, producto.getTipoEstado().name());
            ps.setString(5, String.valueOf(tipo));
            result=ps.executeUpdate();
        }
        return result;
    }
    
    @Override
    public int actualizarInventario(Producto producto,int idLocal) 
            throws SQLException{
        int result=0;
        //continuamos haciendo el query y actualización de la tabla
        String query = "UPDATE Inventario SET stock=?,estado=? WHERE "
                + "idProducto=? AND idLocal=?";
        try(Connection con=DBManager.getInstance().getConnection();
            PreparedStatement ps=con.prepareStatement(query)){
            ps.setInt(1, producto.getStock());
            ps.setString(2, producto.getTipoEstado().name());
            ps.setInt(3,producto.getIdProducto());
            ps.setInt(4,idLocal);
            result=ps.executeUpdate();
        }
        return result;
    }
    
    @Override
    public int obtenerInventarioPorId(int idProducto,int idLocal) 
            throws SQLException{
        int stock=0;
        String query = "SELECT stock FROM Inventario WHERE "
                + "idProducto = ? AND idLocal = ?";
        try (Connection con=DBManager.getInstance().getConnection();
             PreparedStatement ps=con.prepareStatement(query)){
            ps.setInt(1, idProducto);
            ps.setInt(2, idLocal);
            try(ResultSet rs=ps.executeQuery()){
                if(rs.next()){
                    stock=rs.getInt("stock");
                }
            }
        }
        return stock;
    }
    
    @Override
    public ArrayList<Producto> obtenerTodos(int idLocal) throws SQLException{
        ArrayList<Producto> productos= new ArrayList<>();
        //Recuperamos las frutas
        FrutaMySQL fruSQL=new FrutaMySQL();
        productos.addAll(fruSQL.obtenerTodos(idLocal));
        //Recuperamos las bebidas
        BebidaMySQL bebSQL=new BebidaMySQL();
        productos.addAll(bebSQL.obtenerTodos(idLocal));
        //Recuperamos los snacks
        SnackMySQL snaSQL=new SnackMySQL();
        productos.addAll(snaSQL.obtenerTodos(idLocal));
        //Buscamos los productos restantes
        String query = "SELECT idProducto FROM Inventario WHERE idLocal=? and "
                + "tipo=?";
        try (Connection con=DBManager.getInstance().getConnection();
             PreparedStatement ps=con.prepareStatement(query)){
            ps.setInt(1, idLocal);
            ps.setString(2,String.valueOf('P'));
            try(ResultSet rs=ps.executeQuery()){
                while(rs.next()){
                    ProductoMySQL pro=new ProductoMySQL();
                    Producto producto=
                            pro.obtenerProductoPorId(rs.getInt("idProducto"));
                    productos.add(producto);
                }
            }
        }
        return productos;
    }
}
