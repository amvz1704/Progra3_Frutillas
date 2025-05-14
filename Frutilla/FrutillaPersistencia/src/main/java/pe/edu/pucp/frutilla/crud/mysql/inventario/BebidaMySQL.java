/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.frutilla.crud.mysql.inventario;

import pe.edu.pucp.frutilla.config.DBManager;
import pe.edu.pucp.frutilla.models.inventario.Bebida;
import pe.edu.pucp.frutilla.models.inventario.Producto;
import pe.edu.pucp.frutilla.models.inventario.TipoLeche;
import pe.edu.pucp.frutilla.crud.dao.inventario.BebidaDAO;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pe.edu.pucp.frutilla.crud.mysql.BaseDAOImpl;
import pe.edu.pucp.frutilla.models.inventario.TipoEstado;



public class BebidaMySQL extends BaseDAOImpl<Bebida> implements BebidaDAO{

    @Override
    protected String getInsertQuery() {
        String cadena = "INSERT INTO Bebida (idProducto,tamanioOnz,tipo,"
                + "endulzante,tipoLeche) VALUES (?,?,?,?,?)";
        return cadena;
    }

    @Override
    protected String getUpdateQuery() {
        String cadena = "UPDATE Bebida SET tamanioOnz=?,tipo=?,"
                + "endulzante=?,tipoLeche=? WHERE idProducto=?";
        return cadena;
    }

    @Override
    protected String getDeleteQuery() {
        String cadena = "DELETE FROM Bebida WHERE idProducto=?";
        return cadena;
    }

    @Override
    protected String getSelectByIdQuery() {
        String cadena = "SELECT p.idProducto,p.nombre,p.descripcion,"
                + "p.codProd,p.precioUnitario,p.stockMinimo,b.tamanioOnz,"
                + "b.tipo,b.endulzante,b.tipoLeche FROM Bebida b,"
                + "Producto p WHERE b.idProducto=p.idProducto AND "
                + "p.idProducto=?";
        return cadena;
    }

    @Override
    protected String getSelectAllQuery() {
        String cadena = "SELECT p.idProducto,p.nombre,p.descripcion,"
                + "p.codProd,p.precioUnitario,p.stockMinimo,b.tamanioOnz,"
                + "b.tipo,b.endulzante,b.tipoLeche FROM Bebida b,"
                + "Producto p WHERE b.idProducto=p.idProducto";
        return cadena;
    }

    @Override
    protected void setInsertParameters(PreparedStatement ps, Bebida entity) throws SQLException {
        ps.setInt(1,entity.getIdProducto());
        ps.setInt(2,entity.getTamanioOz());
        ps.setString(3, entity.getTipo());
        ps.setString(4, entity.getEndulzante());
        ps.setString(5,entity.getTieneLeche().name());
    }

    @Override
    protected void setUpdateParameters(PreparedStatement ps, Bebida entity) throws SQLException {
        ps.setInt(1,entity.getTamanioOz());
        ps.setString(2, entity.getTipo());
        ps.setString(3, entity.getEndulzante());
        ps.setString(4,entity.getTieneLeche().name());
        ps.setInt(5,entity.getIdProducto());
    }

    @Override
    protected Bebida createFromResultSet(ResultSet rs) throws SQLException {
        ProductoMySQL prodSQL=new ProductoMySQL();
        Bebida beb = new Bebida(prodSQL.createFromResultSet(rs));
        beb.setTamanioOz(rs.getInt("tamanioOnz"));
        beb.setTipo(rs.getString("tipo"));
        beb.setEndulzante(rs.getString("endulzante"));
        String strTipoLeche=rs.getString("tipoLeche");
        if(strTipoLeche!=null)
            beb.setTieneLeche(TipoLeche.valueOf(rs.getString("tipoLeche")));
        return beb;
    }

    @Override
    protected void setId(Bebida entity, Integer id) {
        entity.setIdProducto(id);
    }
    
    @Override
    public void agregar(Bebida entity){
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
    public void actualizar(Bebida entity) {
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
    public ArrayList<Bebida> obtenerTodosPorLocal(int idLocal) {
        ArrayList<Bebida> entities = new ArrayList<>();
        String query = "SELECT p.idProducto, p.nombre, p.descripcion, "
            + "p.codProd, p.precioUnitario, p.stockMinimo, b.tamanioOnz, "
            + "b.tipo, b.endulzante, b.tipoLeche, i.stock, i.estado FROM "
            + "Bebida b, Producto p, Inventario i WHERE "
            + "p.idProducto = i.idProducto AND b.idProducto = p.idProducto "
            + "AND i.idLocal = ? AND i.tipo = 'B'";
        try (Connection conn = DBManager.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query);) {
            ps.setInt(1, idLocal);
            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()) {
                    Bebida temp=createFromResultSet(rs);
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
