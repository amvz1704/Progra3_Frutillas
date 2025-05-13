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
import pe.edu.pucp.frutilla.crud.mysql.BaseDAOImpl;
import pe.edu.pucp.frutilla.models.inventario.TipoEstado;



public class SnackMySQL extends BaseDAOImpl<Snack> implements SnackDAO{

    @Override
    protected String getInsertQuery() {
        String cadena = "INSERT INTO snack (idProducto,tipo,requiereEnvase,"
                + "envase,estaEnvasado) VALUES (?,?,?,?,?)";
        return cadena;
    }

    @Override
    protected String getUpdateQuery() {
        String cadena = "UPDATE snack SET tipo=?,requiereEnvase=?,"
                + "envase=?,estaEnvasado=? WHERE idProducto=?";
        return cadena;
    }

    @Override
    protected String getDeleteQuery() {
        String cadena = "DELETE FROM snack WHERE idProducto=?";
        return cadena;
    }

    @Override
    protected String getSelectByIdQuery() {
        String cadena = "SELECT p.idProducto,p.nombre,p.descripcion,"
                + "p.codProd,p.precioUnitario,p.stockMinimo,s.tipo,"
                + "s.requiereEnvase,s.envase,s.estaEnvasado FROM snack s,"
                + "producto p WHERE s.idProducto=p.idProducto AND p.idProducto=?";
        return cadena;
    }

    @Override
    protected String getSelectAllQuery() {
        String cadena = "SELECT p.idProducto,p.nombre,p.descripcion,"
                + "p.codProd,p.precioUnitario,p.stockMinimo,s.tipo,"
                + "s.requiereEnvase,s.envase,s.estaEnvasado FROM snack s,"
                + "producto p WHERE s.idProducto=p.idProducto ";
        return cadena;
    }

    @Override
    protected void setInsertParameters(PreparedStatement ps, Snack entity) throws SQLException {
        ps.setInt(1, entity.getIdProducto());
        ps.setString(2, entity.getTipo());
        ps.setBoolean(3, entity.isRequiereEnvase());
        ps.setString(4, entity.getEnvase());
        ps.setBoolean(5, entity.isEstaEnvasado());
    }

    @Override
    protected void setUpdateParameters(PreparedStatement ps, Snack entity) throws SQLException {
        ps.setString(1, entity.getTipo());
        ps.setBoolean(2, entity.isRequiereEnvase());
        ps.setString(3, entity.getEnvase());
        ps.setBoolean(4, entity.isEstaEnvasado());
        ps.setInt(5, entity.getIdProducto());
    }

    @Override
    protected Snack createFromResultSet(ResultSet rs) throws SQLException {
        ProductoMySQL prodSQL = new ProductoMySQL();
        Snack snack=new Snack(prodSQL.createFromResultSet(rs));
        snack.setTipo(rs.getString("tipo"));
        snack.setRequiereEnvase(rs.getBoolean("requiereEnvase"));
        snack.setEnvase(rs.getString("envase"));
        snack.setEstaEnvasado(rs.getBoolean("estaEnvasado"));
        return snack;
    }

    @Override
    protected void setId(Snack entity, Integer id) {
        entity.setIdProducto(id);
    }
    
    @Override
    public void agregar(Snack entity){
        ProductoMySQL prodSQL = new ProductoMySQL();
        try (Connection conn = DBManager.getInstance().getConnection();){
            conn.setAutoCommit(false);
            try{
                prodSQL.agregar(entity);
                try(PreparedStatement ps = 
                        conn.prepareStatement(getInsertQuery())) {
                    setInsertParameters(ps, entity);
                    ps.executeUpdate();
                }
                conn.commit();
            } catch(SQLException e) {
                conn.rollback();
                throw e;
            } finally{
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al agregar entidad", e);
        }
    }
    
    @Override
    public void actualizar(Snack entity) {
        ProductoMySQL prodSQL = new ProductoMySQL();
        try (Connection conn = DBManager.getInstance().getConnection()) {
            conn.setAutoCommit(false);
            try {
                prodSQL.actualizar(entity);
                try (PreparedStatement ps = conn.prepareStatement(getUpdateQuery())) {
                    setUpdateParameters(ps, entity);
                    ps.executeUpdate();
                }
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar cliente", e);
        }
    }
    
    @Override
    public ArrayList<Snack> obtenerTodosPorLocal(int idLocal) {
        ArrayList<Snack> entities = new ArrayList<>();
        String query="SELECT p.idProducto,p.nombre,p.descripcion,"
                + "p.codProd,p.precioUnitario,p.stockMinimo,s.tipo,"
                + "s.requiereEnvase,s.envase,s.estaEnvasado,"
                + "i.stock,i.estado FROM snack s,producto p,inventario i "
                + "WHERE p.idProducto=i.idProducto AND i.idLocal=? AND "
                + "i.tipo='S'";
        try (Connection conn = DBManager.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query);) {
            ps.setInt(1, idLocal);
            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()) {
                    Snack temp=createFromResultSet(rs);
                    temp.setStock(rs.getInt("stock"));
                    temp.setTipoEstado(TipoEstado.valueOf(rs.getString("estado")));
                    entities.add(temp);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar entidades", e);
        }
        return entities;
    }
}
