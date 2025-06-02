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
import pe.edu.pucp.frutilla.crud.mysql.BaseDAOImpl;
import pe.edu.pucp.frutilla.models.inventario.TipoEstado;



public class FrutaMySQL extends BaseDAOImpl<Fruta> implements FrutaDAO{
    @Override
    protected String getInsertQuery() {
        String cadena = "INSERT INTO Fruta (idProducto,requiereLimpieza,"
                + "estaLimpio,requiereEnvasado,estaEnvasado,envase) VALUES "
                + "(?,?,?,?,?,?)";
        return cadena;
    }

    @Override
    protected String getUpdateQuery() {
        String cadena = "UPDATE Fruta SET requiereLimpieza=?,estaLimpio=?,"
                + "requiereEnvasado=?,estaEnvasado=?,envase=? WHERE "
                + "idProducto=?";
        return cadena;
    }

    @Override
    protected String getDeleteQuery() {
        String cadena = "DELETE FROM Fruta WHERE idProducto=?";
        return cadena;
    }

    @Override
    protected String getSelectByIdQuery() {
        String cadena = "SELECT p.idProducto,p.nombre,p.descripcion,"
                + "p.codProd,p.precioUnitario,p.stockMinimo,f.requiereLimpieza,"
                + "f.estaLimpio,f.requiereEnvasado,f.estaEnvasado,"
                + "f.envase FROM Fruta f,Producto p WHERE "
                + "f.idProducto=p.idProducto AND p.idProducto=?";
        return cadena;
    }

    @Override
    protected String getSelectAllQuery() {
        String cadena = "SELECT p.idProducto,p.nombre,p.descripcion,"
                + "p.codProd,p.precioUnitario,p.stockMinimo,f.requiereLimpieza,"
                + "f.estaLimpio,f.requiereEnvasado,f.estaEnvasado,"
                + "f.envase FROM Fruta f,Producto p WHERE "
                + "f.idProducto=p.idProducto";
        return cadena;
    }

    @Override
    protected void setInsertParameters(PreparedStatement ps, Fruta entity) throws SQLException {
        ps.setInt(1,entity.getIdProducto());
        ps.setBoolean(2, entity.isRequiereLimpieza());
        ps.setBoolean(3, entity.isEstaLimpio());
        ps.setBoolean(4, entity.isRequiereEnvase());
        ps.setBoolean(5, entity.isEstaEnvasado());
        ps.setString(6, entity.getEnvase());
    }

    @Override
    protected void setUpdateParameters(PreparedStatement ps, Fruta entity) throws SQLException {
        ps.setBoolean(1, entity.isRequiereLimpieza());
        ps.setBoolean(2, entity.isEstaLimpio());
        ps.setBoolean(3, entity.isRequiereEnvase());
        ps.setBoolean(4, entity.isEstaEnvasado());
        ps.setString(5, entity.getEnvase());
        ps.setInt(6,entity.getIdProducto());
    }

    @Override
    protected Fruta createFromResultSet(ResultSet rs) throws SQLException {
        ProductoMySQL prodSQL=new ProductoMySQL();
        Fruta fru = new Fruta(prodSQL.createFromResultSet(rs));
        fru.setRequiereLimpieza(rs.getBoolean("requiereLimpieza"));
        fru.setEstaLimpio(rs.getBoolean("estaLimpio"));
        fru.setRequiereEnvase(rs.getBoolean("requiereEnvasado"));
        fru.setEstaEnvasado(rs.getBoolean("estaEnvasado"));
        fru.setEnvase(rs.getString("envase"));
        return fru;
    }

    @Override
    protected void setId(Fruta entity, Integer id) {
        entity.setIdProducto(id);
    }
    
    @Override
    public void agregar(Fruta entity){
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
    public void actualizar(Fruta entity) {
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
    public ArrayList<Fruta> obtenerTodosPorLocal(int idLocal) {
        ArrayList<Fruta> entities = new ArrayList<>();
        String query="SELECT p.idProducto,p.nombre,p.descripcion,"
                + "p.codProd,p.precioUnitario,p.stockMinimo,f.requiereLimpieza,"
                + "f.estaLimpio,f.requiereEnvasado,f.estaEnvasado,"
                + "f.envase,i.stock,i.estado FROM Fruta f,Producto p,"
                + "Inventario i WHERE p.idProducto=i.idProducto AND "
                + "i.idLocal=? and i.tipo='F'";
        try (Connection conn = DBManager.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);) {
            ps.setInt(1, idLocal);
            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()) {
                    Fruta temp=createFromResultSet(rs);
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
