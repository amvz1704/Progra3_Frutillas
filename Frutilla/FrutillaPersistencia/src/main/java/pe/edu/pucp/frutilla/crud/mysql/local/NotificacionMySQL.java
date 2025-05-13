
package pe.edu.pucp.frutilla.crud.mysql.local;

import pe.edu.pucp.frutilla.models.local.Notificacion;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pe.edu.pucp.frutilla.config.DBManager;
import pe.edu.pucp.frutilla.crud.dao.local.NotificacionDAO;
import pe.edu.pucp.frutilla.crud.mysql.BaseDAOImpl;

/**
 *
 * @author User
 */
public class NotificacionMySQL extends BaseDAOImpl<Notificacion> implements NotificacionDAO{

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO Notificacion(tipoReceptor, fecha, hora, titulo, descripcion, idCliente, idEmpleado) VALUES (?,?,?,?,?,?,?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE Notificacion SET tipoReceptor = ?, fecha = ?, hora = ?, titulo = ?, descripcion = ?, idCliente = ?, idEmpleado = ? where idNotificacion = ?";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM Notificacion WHERE idNotificacion = ?";
    }

    @Override
    protected String getSelectByIdQuery() {
        return "SELECT * FROM Notificacion WHERE idNotificacion = ?";
    }

    @Override
    protected String getSelectAllQuery() {
        return "SELECT * FROM Notificacion";
    }

    @Override
    protected void setInsertParameters(PreparedStatement ps, Notificacion entity) throws SQLException {
        switch (entity.getTipoReceptor()) {
            case 'C':
                ps.setString(1, "CLIENTE");
                break;
            case 'S':
                ps.setString(1, "SUPERVISOR");
        }
        ps.setDate(2, Date.valueOf(entity.getFecha()));
        ps.setTime(3, Time.valueOf(entity.getHora()));
        ps.setString(4, entity.getTitulo());
        ps.setString(5, entity.getDescripcion());
        ps.setInt(6, entity.getIdCliente());
        ps.setInt(7, entity.getIdSupervisor());
    }

    @Override
    protected void setUpdateParameters(PreparedStatement ps, Notificacion entity) throws SQLException {
        setInsertParameters(ps, entity);
        ps.setInt(8, entity.getIdNotificacion());
    }

    @Override
    protected Notificacion createFromResultSet(ResultSet rs) throws SQLException {
        Notificacion notificacion = new Notificacion();
        notificacion.setIdNotificacion(rs.getInt("idNotificacion"));
        notificacion.setFecha(rs.getDate("fecha").toLocalDate());
        notificacion.setHora(rs.getTime("hora").toLocalTime());
        notificacion.setTitulo(rs.getString("titulo"));
        notificacion.setDescripcion(rs.getString("descripcion"));
        notificacion.setIdCliente(rs.getInt("idCliente"));
        notificacion.setIdSupervisor(rs.getInt("idEmpleado"));
        return notificacion;
    }

    @Override
    protected void setId(Notificacion entity, Integer id) {
        entity.setIdNotificacion(id);
    }

    @Override
    public ArrayList<Notificacion> listarPorCliente(int idCliente) {
        ArrayList <Notificacion> notificaciones = new ArrayList<>();
        try (Connection conn = DBManager.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Notificacion WHERE idCliente = ?");
            ResultSet rs = ps.executeQuery()) {

            ps.setInt(1, idCliente);
            while (rs.next()) {
                notificaciones.add(createFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar noticaciones", e);
        }
        return notificaciones;
    }

    @Override
    public ArrayList<Notificacion> listarFecha(LocalDate fecha) {
        ArrayList <Notificacion> notificaciones = new ArrayList<>();
        try (Connection conn = DBManager.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Notificacion WHERE fecha = ?");
            ResultSet rs = ps.executeQuery()) {

            ps.setDate(1, Date.valueOf(fecha));
            while (rs.next()) {
                notificaciones.add(createFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar noticaciones", e);
        }
        return notificaciones;
    }

    @Override
    public ArrayList<Notificacion> listarRangoFecha(LocalDate fechaIni, LocalDate fechaFin) {
        ArrayList <Notificacion> notificaciones = new ArrayList<>();
        try (Connection conn = DBManager.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Notificacion WHERE fecha BETWEEN ? AND ?");
            ResultSet rs = ps.executeQuery()) {

            ps.setDate(1, Date.valueOf(fechaIni));
            ps.setDate(2, Date.valueOf(fechaFin));
            while (rs.next()) {
                notificaciones.add(createFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar noticaciones", e);
        }
        return notificaciones;
    }
    
}
