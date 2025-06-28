package pe.edu.pucp.frutilla.crud.mysql.rrhh;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pe.edu.pucp.frutilla.config.DBManager;
import org.mindrot.jbcrypt.BCrypt;

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
        return "UPDATE Usuario SET usuarioSistema = ?, contrasSistema = ?, activo = ? WHERE idUsuario = ?";
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
        String hashedContrasena = BCrypt.hashpw(entity.getContraSistema(), BCrypt.gensalt());
        ps.setString(1, entity.getUsuarioSistema());
        ps.setString(2, hashedContrasena);
        ps.setBoolean(3, true);
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
        ps.setInt(4, entity.getIdUsuario());
    }

    @Override
    protected Persona createFromResultSet(ResultSet rs) throws SQLException {
        Persona persona = null;
        if(rs.getString("tipo").equals("E")) {
            persona = new Empleado();
        } else {
            persona = new Cliente();
        }
        persona.setIdUsuario(rs.getInt("idUsuario"));
        persona.setUsuarioSistema(rs.getString("usuarioSistema"));
        persona.setContraSistema(rs.getString("contrasSistema"));
        persona.setActivo(rs.getBoolean("activo"));
        return persona;
    }

    @Override
    protected void setId(Persona entity, Integer id) {
        entity.setIdUsuario(id);
    }
    
    @Override
    public Persona validarUsuario(String usuarioSistema, String contrasSistema) throws Exception {
        String query = "SELECT idUsuario, usuarioSistema, contrasSistema, activo, tipo FROM Usuario WHERE usuarioSistema = ? AND activo = true";

        try (Connection conn = DBManager.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);) 
        {
            ps.setString(1, usuarioSistema);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String hashedContrasena = rs.getString("contrasSistema");
                    if(BCrypt.checkpw(contrasSistema, hashedContrasena)){
                        return createFromResultSet(rs);
                    }
                }
            }
        } catch (Exception ex) {
            throw new Exception("Error al validar usuario", ex);
        }

        return null;
    }
    
    @Override
    public int obtenerIDPorNombreUsuario(String nombreUsuario) throws Exception{

        String query = "SELECT idUsuario FROM Usuario WHERE usuarioSistema = ?";
        
        try (Connection conn = DBManager.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);) 
        {
            ps.setString(1, nombreUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("idUsuario");
                }
            }
        } catch (Exception ex) {
            throw new Exception("Error al validar nombre de usuario", ex);
        }

        return -1;
    }
    
    public Persona obtenerPorCorreo(String correo)throws Exception{
        String query = "SELECT u.idUsuario, u.usuarioSistema, u.contrasSistema, u.activo, u.tipo FROM Usuario u " +
                "JOIN Cliente c ON u.idUsuario = c.idUsuario " +
                "WHERE c.correoElectronico = ? " +
                "UNION " +
                "SELECT u.idUsuario, u.usuarioSistema, u.contrasSistema, u.activo, u.tipo " +
                "FROM Usuario u " +
                "JOIN Empleado e ON u.idUsuario = e.idUsuario " +
                "WHERE e.correoElectronico = ? ";

        try (Connection conn = DBManager.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);) 
        {
            ps.setString(1, correo);
            ps.setString(2, correo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return createFromResultSet(rs);
                }
            }
        } catch (Exception ex) {
            throw new Exception("Error al recuperar cuenta", ex);
        }

        return null;
    }
    
    public boolean correoExiste(String correo) throws SQLException{
        String sql ="SELECT 1 FROM Cliente WHERE correoElectronico = ? " +
            "UNION " +
            "SELECT 1 FROM Empleado WHERE correoElectronico = ? " +
            "LIMIT 1";

        try (Connection conn = DBManager.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, correo);
            ps.setString(2, correo);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }
}
