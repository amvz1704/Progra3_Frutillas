package pe.edu.pucp.frutilla.crud.mysql.rrhh;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pe.edu.pucp.frutilla.config.DBManager;

import pe.edu.pucp.frutilla.models.rrhh.Empleado;
import pe.edu.pucp.frutilla.models.rrhh.Persona;
import pe.edu.pucp.frutilla.crud.dao.rrhh.UsuarioDAO;
import pe.edu.pucp.frutilla.crud.mysql.BaseDAOImpl;
import pe.edu.pucp.frutilla.models.rrhh.Cliente;

public class UsuarioMySQL extends BaseDAOImpl<Persona> implements UsuarioDAO{

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO Usuario (usuarioSistema, contrasSistema, activo, tipo) VALUES (?, ?, ?, ?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE Usuario SET usuarioSistema = ?, contrasSistema = ?, activo = ?, tipo = ? WHERE idUsuario = ?";
    }

    @Override
    protected String getDeleteQuery() {
        return "UPDATE Usuario SET activo = false WHERE idUsuario = ?";
    }

    @Override
    protected String getSelectByIdQuery() {
        return "SELECT idUsuario, usuarioSistema, contrasSistema, activo, tipo FROM Usuario WHERE idUsuario = ?";
    }

    @Override
    protected String getSelectAllQuery() {
        return "SELECT idUsuario, usuarioSistema, contrasSistema, activo, tipo FROM Usuario";
    }
    
    @Override
    protected void setInsertParameters(PreparedStatement ps, Persona entity) throws SQLException {
        ps.setString(1, entity.getUsuarioSistema());
        ps.setString(2, entity.getContraSistema());
        ps.setBoolean(3, entity.getActivo());
        if(entity instanceof Empleado) {
            ps.setString(4,"E");
        } else {
            ps.setString(4,"C");
        }
    }

    @Override
    protected void setUpdateParameters(PreparedStatement ps, Persona entity) throws SQLException{
        ps.setString(1, entity.getUsuarioSistema());
        ps.setString(2, entity.getContraSistema());
        ps.setBoolean(3, entity.getActivo());
    }

    @Override
    protected Persona createFromResultSet(ResultSet rs) throws SQLException {
        Persona persona = null;
        if(rs.getString("tipo").equals("E")) {
            persona = new Empleado();
            ((Empleado) persona).setIdEmpleado(rs.getInt("idUsuario"));
        } else {
            persona = new Cliente();
            ((Cliente) persona).setIdCliente(rs.getInt("idUsuario"));
        }
        persona.setUsuarioSistema(rs.getString("usuarioSistema"));
        persona.setContraSistema(rs.getString("contrasSistema"));
        persona.setActivo(rs.getBoolean("activo"));
        return persona;
    }

    @Override
    protected void setId(Persona entity, Integer id) {
        if(entity instanceof Empleado) {
            ((Empleado) entity).setIdEmpleado(id);
        } else {
            ((Cliente) entity).setIdCliente(id);
        }
    }
    
    @Override
    public Persona validarUsuario(String usuarioSistema, String contrasSistema) throws Exception {
        String query = "SELECT idUsuario, usuarioSistema, contrasSistema, activo, tipo FROM Usuario WHERE usuarioSistema = ? AND contrasSistema = ? AND activo = true";

        try (Connection conn = DBManager.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);) 
        {
            ps.setString(1, usuarioSistema);
            ps.setString(2, contrasSistema);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return createFromResultSet(rs);
                }
            }
        } catch (Exception ex) {
            throw new Exception("Error al validar usuario", ex);
        }

        return null;
    }
    
}
