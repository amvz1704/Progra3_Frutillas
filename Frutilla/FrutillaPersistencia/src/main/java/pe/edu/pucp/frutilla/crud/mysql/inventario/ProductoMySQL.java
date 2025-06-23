/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.frutilla.crud.mysql.inventario;

import java.sql.Connection;
import pe.edu.pucp.frutilla.crud.dao.inventario.ProductoDAO;
import pe.edu.pucp.frutilla.models.inventario.Producto;
import pe.edu.pucp.frutilla.config.DBManager;
import pe.edu.pucp.frutilla.models.inventario.TipoEstado;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import pe.edu.pucp.frutilla.crud.mysql.BaseDAOImpl;



public class ProductoMySQL extends BaseDAOImpl<Producto> implements ProductoDAO{

    @Override
    protected String getInsertQuery() {
        String cadena = "INSERT INTO Producto (nombre,descripcion,codProd,"
                + "precioUnitario,stockMinimo) VALUES (?,?,?,?,?)";
        return cadena;
    }

    @Override
    protected String getUpdateQuery() {
        String cadena = "UPDATE Producto SET nombre=?,descripcion=?,"
                + "codProd=?,precioUnitario=?,stockMinimo=? WHERE "
                + "idProducto=?";
        return cadena;
    }

    @Override
    protected String getDeleteQuery() {
        String cadena = "DELETE FROM Producto WHERE idProducto=?";
        return cadena;
    }

    @Override
    protected String getSelectByIdQuery() {
        String cadena = "SELECT idProducto,nombre,descripcion,"
                + "codProd,precioUnitario,stockMinimo FROM Producto "
                + "WHERE idProducto=?";
        return cadena;
    }

    @Override
    protected String getSelectAllQuery() {
        String cadena = "SELECT idProducto,nombre,descripcion,"
                + "codProd,precioUnitario,stockMinimo FROM Producto";
        return cadena;
    }
    
    @Override
    protected void setInsertParameters(PreparedStatement ps, Producto entity) throws SQLException {
        ps.setString(1, entity.getNombre());
        ps.setString(2, entity.getDescripcion());
        ps.setString(3, entity.getCodigoProd());
        ps.setDouble(4, entity.getPrecioUnitario());
        ps.setInt(5, entity.getStockMinimo());
    }

    @Override
    protected void setUpdateParameters(PreparedStatement ps, Producto entity) throws SQLException {
        setInsertParameters(ps, entity);
        ps.setInt(6, entity.getIdProducto());
    }

    @Override
    protected Producto createFromResultSet(ResultSet rs) throws SQLException {
        Producto producto = new Producto();
        
                
        producto.setIdProducto(rs.getInt("idProducto"));
        producto.setNombre(rs.getString("nombre"));
        producto.setDescripcion(rs.getString("descripcion"));
        producto.setCodigoProd(rs.getString("codProd"));
        producto.setPrecioUnitario(rs.getDouble("precioUnitario"));
        producto.setStockMinimo(rs.getInt("stockMinimo"));
        return producto;
    }

    @Override
    protected void setId(Producto entity, Integer id) {
        entity.setIdProducto(id);
    }
    
    

    @Override
    public ArrayList<Producto> obtenerPorNombre(String nombre) {
        ArrayList<Producto> entities = new ArrayList<>();
        String query = "SELECT idProducto,nombre,descripcion,"
                + "codProd,precioUnitario,stockMinimo FROM Producto "
                + "WHERE nombre LIKE ? ";
        try (Connection conn = DBManager.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);) {
            ps.setString(1, "%" + nombre + "%");
            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()) {
                    entities.add(createFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar entidades", e);
        }
        return entities;
    }

    @Override
    public ArrayList<Producto> obtenerTodosPorLocal(int idLocal) {
        ArrayList<Producto> entities = new ArrayList<>();
        String query="SELECT p.idProducto,nombre,descripcion,"
                + "codProd,precioUnitario,stockMinimo,i.stock,"
                + "i.estado FROM Producto p,Inventario i WHERE "
                + "p.idProducto=i.idProducto AND i.idLocal=?";
        try (Connection conn = DBManager.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);) {
            ps.setInt(1, idLocal);
            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()) {
                    Producto temp=createFromResultSet(rs);
                    temp.setStock(rs.getInt("stock"));
                    temp.setTipoEstado(TipoEstado.valueOf(rs.getString("estado")));
                    System.out.println(temp.toString());
                    entities.add(temp);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar entidades", e);
        }
        return entities;
    }

    @Override
    public ArrayList<Producto> obtenerSoloProductosSinCategoriaTodosPorLocal(int idLocal) {
        ArrayList<Producto> entities = new ArrayList<>();
        String query="SELECT p.idProducto,nombre,descripcion,"
                + "codProd,precioUnitario,stockMinimo,i.stock,"
                + "i.estado FROM Producto p,Inventario i WHERE "
                + "p.idProducto=i.idProducto AND i.idLocal=? AND i.tipo="
                + "P";
        try (Connection conn = DBManager.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);) {
            ps.setInt(1, idLocal);
            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()) {
                    Producto temp=createFromResultSet(rs);
                    temp.setStock(rs.getInt("stock"));
                    temp.setTipoEstado(TipoEstado.valueOf(rs.getString("estado")));
                    System.out.println(temp.toString());
                    entities.add(temp);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar entidades", e);
        }
        return entities;
    }
}
