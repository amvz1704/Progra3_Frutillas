/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.frutilla.Inventario.mysql;

import com.frutilla.Inventario.dao.ProductoDAO;
import com.frutilla.models.Inventario.Producto;
import com.frutilla.config.DBManager;
import com.frutilla.models.Inventario.TipoEstado;
import java.sql.ResultSet;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductoMySQL implements ProductoDAO{
    @Override
    public int insertar(Producto producto,int idLocal) throws SQLException{
        int result=0;
        String query="INSERT INTO Producto (nombre,descripcion,codProd,"
                + "precioUnitario,stock,stockMinimo,estado,idLocal) VALUES "
                + "(?,?,?,?,?,?,?,?)";
        try(Connection con=DBManager.getConnection();
             PreparedStatement ps=con.prepareStatement(query,
                     PreparedStatement.RETURN_GENERATED_KEYS)){
            ps.setString(1,producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setString(3, producto.getCodigoProd());
            ps.setDouble(4, producto.getPrecioUnitario());
            ps.setInt(5, producto.getStock());
            ps.setInt(6, producto.getStockMinimo());
            ps.setString(7, producto.getTipoEstado().name());
            ps.setInt(8, idLocal); 
            result=ps.executeUpdate();
            try(ResultSet rs=ps.getGeneratedKeys()){
                if(rs.next()){
                    producto.setIdProducto(rs.getInt(1));
                }
            }
        }
        return result;
    }
    @Override
    public int actualizarStock (int cant,int idProducto,
            int idLocal) throws SQLException{
        int result=0;
        String query="{CALL actualizar_stock(?,?,?,?,?)}";
        try(Connection con=DBManager.getConnection();
            CallableStatement cs=con.prepareCall(query);){
            cs.setInt(1, cant);
            if (cant<0)
                cs.setString(2,String.valueOf('S'));
            else
                cs.setString(2, String.valueOf('E'));
            cs.setInt(3, idProducto);
            cs.setInt(4, idLocal);
            cs.registerOutParameter(5,java.sql.Types.INTEGER);
            cs.execute();
            result=cs.getInt(5);
        }
        return result;
    }
    @Override
    public void eliminar(int idProd,int idLocal) throws SQLException{
        TipoEstado desactivado=TipoEstado.AGOTADO;
        String query="UPDATE Producto SET estado = ? WHERE idProducto = ? AND "
                + "idLocal = ?";
        try(Connection con=DBManager.getConnection();
             PreparedStatement ps=con.prepareStatement(query)){
            ps.setString(1, desactivado.name());
            ps.setInt(2, idProd);
            ps.setInt(3, idLocal);
            int result=ps.executeUpdate();
            if (result==1) 
                System.out.println("Se realizo eliminado correctamente");
        }
    }
    @Override
    public Producto obtenerProducto(int idProducto,int idLocal) 
            throws SQLException{
        Producto prod=new Producto();
        String query="SELECT nombre,descripcion,codProd,precioUnitario,"
                + "stock,estado FROM Producto WHERE idProducto = ? "
                + "AND idLocal = ?";
        try(Connection con=DBManager.getConnection();
             PreparedStatement ps=con.prepareStatement(query)){
            ps.setInt(1,idProducto);
            ps.setInt(2, idLocal);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                prod.setIdProducto(idProducto);
                prod.setNombre(rs.getString("nombre"));
                prod.setDescripcion(rs.getString("descripcion"));
                prod.setCodigoProd(rs.getString("codProd"));
                prod.setStock(rs.getInt("stock"));
                prod.setTipoEstado(TipoEstado.valueOf(rs.getString("estado")));
            }
            rs.close();  
        }
        return prod;//colocar bien la revision en caso de pedir un producto
    }
    @Override
    public ArrayList<Producto> obtenerTodos(int idLocal) throws SQLException{
        ArrayList<Producto> productos=new ArrayList<Producto>();
        String query="SELECT idProducto,nombre,descripcion,codProd,precioUnitario,"
                + "stock,estado FROM Producto WHERE idLocal = ?";
        try(Connection con=DBManager.getConnection();
             PreparedStatement ps=con.prepareStatement(query)){
            ps.setInt(1, idLocal);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Producto prod = new Producto();
                prod.setIdProducto(rs.getInt("idProducto"));
                prod.setNombre(rs.getString("nombre"));
                prod.setDescripcion(rs.getString("descripcion"));
                prod.setCodigoProd(rs.getString("codProd"));
                prod.setStock(rs.getInt("stock"));
                prod.setTipoEstado(TipoEstado.valueOf(rs.getString("estado")));
                productos.add(prod);
            }
            rs.close();  
        }
        return productos;
    }
}
