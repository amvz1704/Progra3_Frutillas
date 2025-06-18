/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.frutilla.crud.mysql.inventario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pe.edu.pucp.frutilla.config.DBManager;
import pe.edu.pucp.frutilla.crud.dao.inventario.ProductoImagenDAO;
import pe.edu.pucp.frutilla.crud.mysql.BaseDAOImpl;
import pe.edu.pucp.frutilla.models.inventario.ProductoImagen;

/**
 *
 * @author Desktop
 */
public class ProductoImagenMySQL extends BaseDAOImpl<ProductoImagen>
implements ProductoImagenDAO{

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO ProductoImagen (idProductoImagen,tipoProducto,"
                + "urlImagen) VALUES (?,?,?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE ProductoImagen SET urlImagen=?,tipoProducto=? "
                + "WHERE tipoProducto=?";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM ProductoImagen WHERE tipoProducto=?";
    }

    @Override
    protected String getSelectByIdQuery() {
        return "SELECT * FROM ProductoImagen WHERE tipoProducto=?";
    }

    @Override
    protected String getSelectAllQuery() {
        return "SELECT * FROM ProductoImagen";
    }

    @Override
    protected void setInsertParameters(PreparedStatement ps, ProductoImagen entity) throws SQLException {
        ps.setInt(1, entity.getIdProductoImagen());
        ps.setString(2, String.valueOf(entity.getTipoProducto()));
        ps.setString(3, entity.getUtlImagen());
    }

    @Override
    protected void setUpdateParameters(PreparedStatement ps, ProductoImagen entity) throws SQLException {
        ps.setString(1, String.valueOf(entity.getTipoProducto()));
        ps.setString(2, entity.getUtlImagen());
        ps.setInt(3, entity.getIdProductoImagen());
    }

    @Override
    protected ProductoImagen createFromResultSet(ResultSet rs) throws SQLException {
        ProductoImagen entity=new ProductoImagen();
        entity.setIdProductoImagen(rs.getInt("idProductoImagen"));
        entity.setTipoProducto(rs.getString("tipoProducto").charAt(0));
        entity.setUtlImagen(rs.getString("urlImagen"));
        return entity;
    }

    @Override
    protected void setId(ProductoImagen entity, Integer id) {
        entity.setIdProductoImagen(id);
    }
    
    public String obtenerImagenPorTipo(char tipoProducto){
        String url=null;
        String query = "SELECT urlImagen FROM ProductoImagen WHERE tipoProducto=?";
        try (Connection conn = DBManager.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, String.valueOf(tipoProducto));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    url=rs.getString("urlImagen");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener entidad", e);
        }
        return url;
    }
}
