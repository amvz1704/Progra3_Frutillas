package pe.edu.pucp.frutilla.crud.mysql.rrhh;

import pe.edu.pucp.frutilla.crud.dao.rrhh.EmpleadoDAO; //incluimos el dao correspondiente 
import pe.edu.pucp.frutilla.crud.mysql.BaseDAOImpl;
import pe.edu.pucp.frutilla.models.rrhh.Empleado; //Empleado
import pe.edu.pucp.frutilla.models.rrhh.Supervisor; //Supervisor pe causa
import pe.edu.pucp.frutilla.models.rrhh.Repartidor; // Repartidor pe causa

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

import pe.edu.pucp.frutilla.config.DBManager;

public class EmpleadoMySQL extends BaseDAOImpl<Empleado> implements EmpleadoDAO{

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO Empleado (idUsuario, nombres, apellidoPaterno, apellidoMaterno, telefono, correoElectronico, fechaContrato, salario, turnoTrabajo, tipo, idLocal) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE Empleado SET idUsuario = ?, nombres = ?, apellidoPaterno = ?, apellidoMaterno = ?, telefono = ?, correoElectronico = ?, fechaContrato = ?, salario = ?, turnoTrabajo = ?, tipo = ?, idLocal = ? WHERE idUsuario = ?";
    }

    @Override
    protected String getDeleteQuery() {
        return "UPDATE Usuario SET activo = false WHERE idUsuario = ?";
    }

    @Override
    protected String getSelectByIdQuery() {
    return "SELECT u.idUsuario, u.usuarioSistema, u.contrasSistema, u.activo, " +
           "e.nombres, e.apellidoPaterno, e.apellidoMaterno, e.telefono, " +
           "e.correoElectronico, e.fechaContrato, e.salario, e.turnoTrabajo, " +
           "e.tipo, e.idLocal " +
           "FROM Usuario u " +
           "INNER JOIN Empleado e ON u.idUsuario = e.idUsuario " +
           "WHERE e.idUsuario = ? AND u.activo = true";
    }


    @Override
    protected String getSelectAllQuery() {
        return "SELECT u.idUsuario, u.usuarioSistema, u.contrasSistema, u.activo, e.nombres, e.apellidoPaterno, e.apellidoMaterno, e.telefono, e.correoElectronico, e.fechaContrato, e.salario, e.turnoTrabajo, e.tipo, e.idLocal FROM Usuario u, Empleado e WHERE u.activo = true";
    }

    @Override
    protected void setInsertParameters(PreparedStatement ps, Empleado entity) throws SQLException {

        ps.setInt(1, entity.getIdEmpleado());
        ps.setString(2, entity.getNombre());
        ps.setString(3, entity.getApellidoPaterno());
        ps.setString(4, entity.getApellidoMaterno());
        ps.setString(5, entity.getTelefono());
        ps.setString(6, entity.getCorreoElectronico());
        ps.setDate(7, Date.valueOf(entity.getFechaContrato()));
        ps.setDouble(8, entity.getSalario());
        ps.setBoolean(9, entity.getTurnoTrabajo());
        ps.setString(10, String.valueOf(entity.getTipo()));
        ps.setInt(11, entity.getIdLocal());
    }

    @Override
    protected void setUpdateParameters(PreparedStatement ps, Empleado entity) throws SQLException {

        ps.setInt(1, entity.getIdEmpleado());
        ps.setString(2, entity.getNombre());
        ps.setString(3, entity.getApellidoPaterno());
        ps.setString(4, entity.getApellidoMaterno());
        ps.setString(5, entity.getTelefono());
        ps.setString(6, entity.getCorreoElectronico());
        ps.setDate(7, Date.valueOf(entity.getFechaContrato()));
        ps.setDouble(8, entity.getSalario());
        ps.setBoolean(9, entity.getTurnoTrabajo());
        ps.setString(10, String.valueOf(entity.getTipo()));
        ps.setInt(11, entity.getIdLocal());
        ps.setInt(12, entity.getIdEmpleado());
    }

    @Override
    protected Empleado createFromResultSet(ResultSet rs) throws SQLException {
        char tipo = rs.getString("tipo").charAt(0);
        Empleado empleado = null;
        if(tipo == 'S'){
            empleado = new Supervisor();
        }
        else{
            empleado = new Repartidor();
        }
        
        empleado.setIdEmpleado(rs.getInt("idUsuario"));
        empleado.setNombre(rs.getString("nombres"));
        empleado.setApellidoPaterno(rs.getString("apellidoPaterno"));
        empleado.setApellidoMaterno(rs.getString("apellidoMaterno"));
        empleado.setCorreoElectronico(rs.getString("correoElectronico"));
        empleado.setTelefono(rs.getString("telefono"));
        empleado.setUsuarioSistema(rs.getString("usuarioSistema"));
        empleado.setContraSistema(rs.getString("contrasSistema"));
        empleado.setActivo(rs.getBoolean("activo"));
        empleado.setFechaContrato(rs.getDate("fechaContrato").toLocalDate());
        empleado.setSalario(rs.getDouble("salario"));
        empleado.setTurnoTrabajo(rs.getBoolean("turnoTrabajo"));
        empleado.setTipo(tipo);
        empleado.setIdLocal(rs.getInt("idLocal"));
        
        return empleado;
    }

    @Override
    protected void setId(Empleado entity, Integer id) {
        entity.setIdEmpleado(id);
    }

    @Override
    public void agregar(Empleado entity){
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
    public void actualizar(Empleado entity){
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
            throw new RuntimeException("Error al agregar entidad", e);
        }
    }

    public ArrayList<Empleado> listarTodosPorLocal(int idLocal){
        ArrayList<Empleado> empleados = new ArrayList<>();
        String query = "SELECT u.idUsuario, u.usuarioSistema, u.contrasSistema, u.activo, e.nombres, e.apellidoPaterno, e.apellidoMaterno, e.telefono, e.correoElectronico, e.fechaContrato, e.salario, e.turnoTrabajo, e.tipo, e.idLocal FROM Usuario u, Empleado e WHERE u.activo = true AND e.idLocal = ?";
        try (Connection conn = DBManager.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, idLocal);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    empleados.add(createFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar empleados por local", e);
        }
        return empleados;
    }
}
