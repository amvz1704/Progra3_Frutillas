/**
 *
 * @author  Gabriel Medrano
 * Consultas: a20200948@pucp.edu.pe
 * Telf: 989299092
 */

 package com.frutilla.crud.mysql.venta;

 import com.frutilla.models.inventario.Producto;
 import com.frutilla.models.venta.LineaOrdenDeVenta;
 import com.frutilla.crud.mysql.inventario.ProductoMySQL;
 import com.frutilla.config.DBManager;
 import java.sql.Connection;
 import java.sql.SQLException;
 import java.sql.PreparedStatement;
 import java.sql.ResultSet;
 import java.util.ArrayList;

public class LineaOrdenDeVentaMySQL{
    //Metodo que permite insertar datos a la tabla LineaOrdenVenta
    public int insertarLineaVenta(LineaOrdenDeVenta lVenta,int idOrdenVenta,int idProducto) throws SQLException{
        int result=0;
        String query= "INSERT INTO LineaOrdenVenta(idOrdenVenta,cantidad,subtotal,IdProducto)"
        + "values(?,?,?,?)";
        
        try(Connection con = DBManager.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(query);){
                ps.setInt(1,idOrdenVenta);
                ps.setInt(2,lVenta.getCantidad());
                ps.setDouble(3,lVenta.getSubtotal());
                ps.setInt(4,idProducto);
                
                result=ps.executeUpdate();//ejecuta la insercion

                try(ResultSet rs=ps.getGeneratedKeys()){
                    if(rs.next()){
                        lVenta.setIdLineaVenta(rs.getInt(1));
                    }
                }
        }
            
        return result;
    }

    //metodo para actualizar dentro de la tabla LineaOrdenVenta
<<<<<<< HEAD
    public void actualizarLineaOrdenVenta(LineaOrdenDeVenta lVenta)throws SQLException{

        String query="UPDATE LineaOrdenVenta SET cantidad = ?, subtotal = ?, IdProducto = ? WHERE idLineaOrdenVenta = ?";
=======
    public int actualizarLineaOrdenVenta(LineaOrdenDeVenta lVenta)throws SQLException{
        int result=0;
        String query="UPDATE LineaOrdenVenta SET cantidad = ? WHERE idLineaOrdenVenta = ?";
>>>>>>> origin/main

        try (Connection con = DBManager.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1,lVenta.getCantidad());
<<<<<<< HEAD
            lVenta.actualizarSubtotal();
            ps.setInt(2, lVenta.getSubtotal());
            ps.setInt(3, lVenta.getProducto().getIdProducto());
            ps.setInt(4,lVenta.getIdLineaVenta());
            
            ps.executeUpdate();
        }
=======
            ps.setInt(2,lVenta.getIdLineaVenta());

            result = ps.executeUpdate();
        }
        return result;
>>>>>>> origin/main
    }

    public ArrayList<LineaOrdenDeVenta> obtenerLineasPorOrden(int idOrdenVenta)throws SQLException{
        ArrayList<LineaOrdenDeVenta> lineasDeVentas = new ArrayList<>();

<<<<<<< HEAD
        String query="SELECT * FROM LineaOrdenVenta WHERE idOrdenVenta=?";
=======
        String query="SELECT *FROM LineaOrdenVenta WHERE idOrdenVenta=?";
>>>>>>> origin/main

        try(Connection con = DBManager.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(query)){
            
            ps.setInt(1,idOrdenVenta);
            ResultSet rs = ps.executeQuery();
            ProductoMySQL productoMySQL = new ProductoMySQL();
            while(rs.next()){
                LineaOrdenDeVenta lineaOrdenVenta = new LineaOrdenDeVenta();
                lineaOrdenVenta.setIdLineaVenta(rs.getInt("idLineaOrdenVenta"));
<<<<<<< HEAD
                lineaOrdenVenta.setCantidad(rs.getInt("cantidad"));
=======
                lineaOrdenVenta.setCantidad(rs.getInt("cantidad"))
>>>>>>> origin/main
                lineaOrdenVenta.setSubtotal(rs.getDouble("subTotal"));
                Producto producto = productoMySQL.obtenerProductoPorId(rs.getInt("idProducto"));
                lineaOrdenVenta.setProducto(producto);
                lineasDeVentas.add(lineaOrdenVenta);
            }
        }
        return lineasDeVentas;
    }
}

