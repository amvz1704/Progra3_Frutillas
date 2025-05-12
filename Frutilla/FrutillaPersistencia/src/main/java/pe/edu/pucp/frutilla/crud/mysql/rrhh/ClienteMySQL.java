package pe.edu.pucp.frutilla.crud.mysql.rrhh;


import pe.edu.pucp.frutilla.crud.dao.rrhh.ClienteDAO; //incluimos el dao correspondiente 
import pe.edu.pucp.frutilla.crud.mysql.BaseDAOImpl;
import pe.edu.pucp.frutilla.models.rrhh.Cliente; //Cliente

import pe.edu.pucp.frutilla.config.DBManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


public class ClienteMySQL extends BaseDAOImpl<Cliente> implements ClienteDAO{
    @Override
    protected String getInsertQuery() {
        return "INSERT INTO Cliente (idUsuario, nombres, apellidoPaterno, apellidoMaterno, correoElectronico, telefono) VALUES (?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE Cliente SET idUsuario = ?, nombres = ?, apellidoPaterno = ?, apellidoMaterno = ?, correoElectronico = ?, telefono = ? WHERE idUsuario = ?";
    }

    @Override
    protected String getDeleteQuery() {
        return "UPDATE Usuario SET activo = false WHERE idUsuario = ?";
    }

    @Override
    protected String getSelectByIdQuery() {
        return "SELECT u.idUsuario, u.usuarioSistema, u.contrasSistema, u.activo, c.nombres, c.apellidoPaterno, c.apellidoMaterno, c.correoElectronico, c.telefono FROM Usuario u, Cliente c WHERE u.idUsuario = ? AND u.activo = true";
    }

    @Override
    protected String getSelectAllQuery() {
        return "SELECT u.idUsuario, u.usuarioSistema, u.contrasSistema, u.activo, c.nombres, c.apellidoPaterno, c.apellidoMaterno, c.correoElectronico, c.telefono FROM Usuario u, Cliente c WHERE u.activo = true";
    }

    @Override
    protected void setInsertParameters(PreparedStatement ps, Cliente entity) throws SQLException {
        ps.setInt(1, entity.getIdCliente());
        ps.setString(2, entity.getNombre());
        ps.setString(3, entity.getApellidoPaterno());
        ps.setString(4, entity.getApellidoMaterno());
        ps.setString(5, entity.getCorreoElectronico());
        ps.setString(6, entity.getTelefono());
    }

    @Override
    protected void setUpdateParameters(PreparedStatement ps, Cliente entity) throws SQLException {
        ps.setInt(1, entity.getIdCliente());
        ps.setString(2, entity.getNombre());
        ps.setString(3, entity.getApellidoPaterno());
        ps.setString(4, entity.getApellidoMaterno());
        ps.setString(5, entity.getCorreoElectronico());
        ps.setString(6, entity.getTelefono());
        ps.setInt(7, entity.getIdCliente());
    }

    @Override
    protected Cliente createFromResultSet(ResultSet rs) throws SQLException {
        
        Cliente cliente = new Cliente();
        
        cliente.setIdCliente(rs.getInt("idUsuario"));
        cliente.setNombre(rs.getString("nombres"));
        cliente.setApellidoPaterno(rs.getString("apellidoPaterno"));
        cliente.setApellidoMaterno(rs.getString("apellidoMaterno"));
        cliente.setCorreoElectronico(rs.getString("correoElectronico"));
        cliente.setTelefono(rs.getString("telefono"));
        cliente.setUsuarioSistema(rs.getString("usuarioSistema"));
        cliente.setContraSistema(rs.getString("contrasSistema"));
        cliente.setActivo(rs.getBoolean("activo"));
        
        return cliente;
    }

    @Override
    protected void setId(Cliente entity, Integer id){
        entity.setIdCliente(id);
    }

    @Override
    public void agregar(Cliente entity){
        UsuarioMySQL usuarioMySQL = new UsuarioMySQL();
        try (Connection conn = DBManager.getInstance().getConnection();){
            conn.setAutoCommit(false);
            try{
                usuarioMySQL.agregar(entity);
                try(PreparedStatement ps = conn.prepareStatement(getInsertQuery())) {
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
    public void actualizar(Cliente entity){
        UsuarioMySQL usuarioMySQL = new UsuarioMySQL();
        try (Connection conn = DBManager.getInstance().getConnection();){
            conn.setAutoCommit(false);
            try{
                usuarioMySQL.actualizar(entity);
                try(PreparedStatement ps = 
                        conn.prepareStatement(getUpdateQuery())) {
                    setUpdateParameters(ps, entity);
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
            throw new RuntimeException("Error al actualizar entidad", e);
        }
    }

}
