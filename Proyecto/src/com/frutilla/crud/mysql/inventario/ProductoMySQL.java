/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.frutilla.crud.mysql.inventario;

import com.frutilla.crud.dao.inventario.ProductoDAO;
import com.frutilla.models.inventario.Producto;
import com.frutilla.config.DBManager;
import com.frutilla.models.inventario.TipoEstado;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;



public class ProductoMySQL implements ProductoDAO{
    @Override
    //inserta el producto, devuelve verificacion de insercion y
    //asigna el id del producto de regreso para tener coincidencia
    public void insertarProducto(Producto producto) throws SQLException{
        String query="INSERT INTO Producto (nombre,descripcion,codProd,"
                + "precioUnitario,stockMinimo) VALUES "
                + "(?,?,?,?,?)";
        try(Connection con=DBManager.getInstance().getConnection();
             PreparedStatement ps=con.prepareStatement(query,
                     PreparedStatement.RETURN_GENERATED_KEYS)){
            ps.setString(1,producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setString(3, producto.getCodigoProd());
            ps.setDouble(4, producto.getPrecioUnitario());
            ps.setInt(5, producto.getStockMinimo()); 
            ps.executeUpdate();
            try(ResultSet rs=ps.getGeneratedKeys()){
                if(rs.next()){
                   //result = rs.getInt(1);
                   producto.setIdProducto(rs.getInt(1));
                }
            }
        }
    }

    public int insertarProductoDevolverID(Producto producto) throws SQLException{
        int result=0;
        String query="INSERT INTO Producto (nombre,descripcion,codProd,"
                + "precioUnitario,stockMinimo) VALUES "
                + "(?,?,?,?,?)";
        try(Connection con=DBManager.getInstance().getConnection();
             PreparedStatement ps=con.prepareStatement(query,
                     PreparedStatement.RETURN_GENERATED_KEYS)){
            ps.setString(1,producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setString(3, producto.getCodigoProd());
            ps.setDouble(4, producto.getPrecioUnitario());
            ps.setInt(5, producto.getStockMinimo()); 
            ps.executeUpdate();
            try(ResultSet rs=ps.getGeneratedKeys()){
                if(rs.next()){
                   result = rs.getInt(1);
                   producto.setIdProducto(rs.getInt(1));
                }
            }
        }
        return result;
    }

    //ingresa todos los datos del producto y los cambia en caso haya algun cambio
    @Override
    public void actualizarProducto(Producto producto) 
            throws SQLException{
        String query="""
                    UPDATE Producto SET nombre=?,descripcion=?,codProd=?,
                    precioUnitario=?, stockMinimo=?
                    WHERE idProducto=? """;
        try (Connection con=DBManager.getInstance().getConnection();
             PreparedStatement ps=con.prepareStatement(query)){
            ps.setString(1,producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setString(3, producto.getCodigoProd());
            ps.setDouble(4, producto.getPrecioUnitario());
            ps.setInt(5, producto.getStockMinimo());
            ps.setInt(6, producto.getIdProducto());
            ps.executeUpdate();
        }
    }
    //eliminado lógico
    @Override
    public void eliminarProducto(int idProd,int idLocal) throws SQLException{
        TipoEstado desactivado=TipoEstado.AGOTADO;
        String query="UPDATE Inventario SET estado = ? WHERE idProducto = ? AND "
                + "idLocal = ?";
        try(Connection con=DBManager.getInstance().getConnection();
             PreparedStatement ps=con.prepareStatement(query)){
            ps.setString(1, desactivado.toString());
            ps.setInt(2, idProd);
            ps.setInt(3, idLocal);
            int result=ps.executeUpdate();
            if (result==1) 
                System.out.println("Se realizo eliminado correctamente");
        }
    }
    //solo se obtiene un solo producto en base a su id y local
    @Override
    public Producto obtenerProductoPorId(int idProducto) 
            throws SQLException{
        Producto prod=new Producto();
        String query="SELECT nombre,descripcion,codProd,precioUnitario,"
                + "stockMinimo FROM Producto WHERE idProducto = ? ";
        try(Connection con=DBManager.getInstance().getConnection();
             PreparedStatement ps=con.prepareStatement(query)){
            ps.setInt(1,idProducto);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                prod.setIdProducto(idProducto);
                prod.setNombre(rs.getString("nombre"));
                prod.setDescripcion(rs.getString("descripcion"));
                prod.setCodigoProd(rs.getString("codProd"));
                prod.setPrecioUnitario(rs.getDouble("precioUnitario"));
                prod.setStock(rs.getInt("stockMinimo"));
            }
            rs.close();  
        }
        return prod;//colocar bien la revision en caso de pedir un producto
    }
    @Override
    public ArrayList<Producto> obtenerTodos(int idLocal) throws SQLException{
        ArrayList<Producto> productos=new ArrayList<Producto>();
        String query="SELECT idProducto FROM Inventario WHERE idLocal = ?";
        try(Connection con=DBManager.getInstance().getConnection();
             PreparedStatement ps=con.prepareStatement(query)){
            ps.setInt(1, idLocal);
            try (ResultSet rs = ps.executeQuery()) {
                while(rs.next()){
                    Producto prod = obtenerProductoPorId(rs.getInt("idProducto"));
                    productos.add(prod);
                }
            }
        }
        return productos;
    }
}
