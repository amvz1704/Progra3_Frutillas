/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.frutilla.crud.mysql.inventario;

import pe.edu.pucp.frutilla.crud.dao.inventario.InventarioDAO;
import pe.edu.pucp.frutilla.config.DBManager;
import pe.edu.pucp.frutilla.models.inventario.Producto;
import pe.edu.pucp.frutilla.models.inventario.Fruta;
import pe.edu.pucp.frutilla.models.inventario.Snack;
import pe.edu.pucp.frutilla.models.inventario.Bebida;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class InventarioMySQL implements InventarioDAO{
    @Override

    public void insertarInventario(Producto producto,int idLocal) throws SQLException{
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
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement ps=con.prepareStatement(query);){
            ps.setInt(1, producto.getIdProducto());
            ps.setInt(2, idLocal);
            ps.setInt(3, producto.getStock());
            ps.setString (4, producto.getTipoEstado().name());
            ps.setString(5, String.valueOf(tipo));
            ps.executeUpdate();
        }
    }
    
    @Override
    public void actualizarInventario(Producto producto,int idLocal) throws SQLException{
        String query = "UPDATE Inventario SET stock=?,estado=? WHERE "
                + "idProducto=? AND idLocal=?";
        try(Connection con=DBManager.getInstance().getConnection();
            PreparedStatement ps=con.prepareStatement(query)){
            ps.setInt(1, producto.getStock());
            ps.setString(2, producto.getTipoEstado().name());
            ps.setInt(3,producto.getIdProducto());
            ps.setInt(4,idLocal);
            ps.executeUpdate();
        }
    }
    
    @Override
    public int obtenerInventarioPorId(int idProducto,int idLocal) throws SQLException{
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
    public char obtenerTipoProducto(int idProducto,int idLocal) throws SQLException{
        String tipo = null;
        String query = "SELECT tipo FROM Inventario WHERE "
                + "idProducto = ? AND idLocal = ?";
        try (Connection con=DBManager.getInstance().getConnection();
             PreparedStatement ps=con.prepareStatement(query)){
            ps.setInt(1, idProducto);
            ps.setInt(2, idLocal);
            try(ResultSet rs=ps.executeQuery()){
                if(rs.next()){
                    tipo=rs.getString("tipo");
                }
            }
        }
        if (tipo != null && !tipo.isEmpty()) {
            return tipo.charAt(0);
        } else {
            throw new SQLException("No se encontró el tipo para el producto con ID " + idProducto + " en el local " + idLocal);
        }
    }
    
    @Override
    public ArrayList<Producto> obtenerTodos(int idLocal) throws SQLException{
        ArrayList<Producto> productos= new ArrayList<>();
        //Recuperamos las frutas
        FrutaMySQL fruSQL=new FrutaMySQL();
        productos.addAll(fruSQL.obtenerTodosPorLocal(idLocal));
        //Recuperamos las bebidas
        BebidaMySQL bebSQL=new BebidaMySQL();
        productos.addAll(bebSQL.obtenerTodosPorLocal(idLocal));
        //Recuperamos los snacks
        SnackMySQL snaSQL=new SnackMySQL();
        productos.addAll(snaSQL.obtenerTodosPorLocal(idLocal));
        //Buscamos los productos restantes
        ProductoMySQL proSQl = new ProductoMySQL();
        productos.addAll(proSQl.obtenerTodosPorLocal(idLocal));
        //retonamo el arraylist completo;
        return productos;
    }

    @Override
    public void eliminarInventario(int idProducto, int idLocal) throws SQLException {
        String query = "UPDATE Inventario SET estado='AGOTADO' WHERE "
                + "idProducto=? AND idLocal=?";
        try(Connection con=DBManager.getInstance().getConnection();
            PreparedStatement ps=con.prepareStatement(query)){
            ps.setInt(1,idProducto);
            ps.setInt(2,idLocal);
            ps.executeUpdate();
        }
    }

    @Override
    public void actualizarStock(Producto producto, int idLocal) throws SQLException {
        String query = "UPDATE Inventario SET stock=? WHERE "
                + "idProducto=? AND idLocal=?";
        try(Connection con=DBManager.getInstance().getConnection();
            PreparedStatement ps=con.prepareStatement(query)){
            ps.setInt(1, producto.getStock());
            ps.setInt(2,producto.getIdProducto());
            ps.setInt(3,idLocal);
            ps.executeUpdate();
        }
    }
    
    @Override
    public ArrayList<Producto> listarPorTipo(int idLocal, char tipo) throws SQLException{
        ArrayList<Producto> productos= new ArrayList<>();
        //Recuperamos las frutas
        switch(tipo){
            case 'F':
            FrutaMySQL fruSQL=new FrutaMySQL();
            productos.addAll(fruSQL.obtenerTodosPorLocal(idLocal));
        break;
            case 'B':
            //Recuperamos las bebidas
            BebidaMySQL bebSQL=new BebidaMySQL();
            productos.addAll(bebSQL.obtenerTodosPorLocal(idLocal));
        break;
            case 'S':
            //Recuperamos los snacks
            SnackMySQL snaSQL=new SnackMySQL();
            productos.addAll(snaSQL.obtenerTodosPorLocal(idLocal));
        default:
            //Buscamos los productos restantes
            ProductoMySQL proSQl = new ProductoMySQL();
            productos.addAll(proSQl.obtenerTodosPorLocal(idLocal));
        }
        //retonamo el arraylist completo;
        return productos;
    }
}
