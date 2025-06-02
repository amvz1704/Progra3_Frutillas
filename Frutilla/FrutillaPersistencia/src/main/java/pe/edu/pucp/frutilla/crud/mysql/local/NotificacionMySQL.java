
package pe.edu.pucp.frutilla.crud.mysql.local;

import java.sql.Connection;
import pe.edu.pucp.frutilla.models.local.Notificacion;
import java.sql.Date;
import java.sql.Time;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
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
        //no es usado
        return "INSERT INTO Notificacion(tipoReceptor, fecha, hora, titulo, descripcion, idCliente, idEmpleado) VALUES (?,?,?,?,?,?,?)";
    }

    @Override
    protected String getUpdateQuery() {
        //no es usado
        return "UPDATE Notificacion SET tipoReceptor = ?, fecha = ?, hora = ?, titulo = ?, descripcion = ?, idCliente = ?, idEmpleado = ? where idNotificacion = ?";
    }

    @Override
    protected String getDeleteQuery() {
        //no es usado
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
        //no va a ser utilizado
        switch (entity.getTipoReceptor()) {
            case 'C':
                ps.setString(1, "CLIENTE");
                break;
            case 'S':
                ps.setString(1, "SUPERVISOR");
        }
        ps.setDate(2, new java.sql.Date(entity.getFecha().getYear(),
                entity.getFecha().getMonthValue(),
                entity.getFecha().getDayOfMonth()));
        ps.setString(3, entity.getTitulo());
        ps.setString(4, entity.getDescripcion());
        ps.setInt(5, entity.getIdCliente());
        ps.setInt(6, entity.getIdSupervisor());
    }

    @Override
    protected void setUpdateParameters(PreparedStatement ps, Notificacion entity) throws SQLException {
        //no es utilizado
        setInsertParameters(ps, entity);
        ps.setInt(8, entity.getIdNotificacion());
    }

    @Override
    protected Notificacion createFromResultSet(ResultSet rs) throws SQLException {
        Notificacion notificacion = new Notificacion();
        notificacion.setIdNotificacion(rs.getInt("idNotificacion"));
        notificacion.setFecha(rs.getDate("fechaHora").toLocalDate());
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
    public ArrayList<Notificacion> listarPorFecha(LocalDate fecha,int idSupervisor) {
        ArrayList<Notificacion> entities = new ArrayList<>();
         try (Connection conn = DBManager.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(getSelectByFechaQuery())) {

            ps.setDate(1, new java.sql.Date(fecha.getYear(),fecha.getMonthValue(),fecha.getDayOfMonth()));
            ps.setInt(2, idSupervisor);
            try (ResultSet rs = ps.executeQuery()) {
                 while (rs.next()) {
                    entities.add(createFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener entidad", e);
        }
        return entities;
    }

    private String getSelectByFechaQuery() {
        return "SELECT * FROM Notificacion WHERE DATE(fechaHora) = ? and "
                + "idEmpleado = ?";
    }

    @Override
    public ArrayList<Notificacion> listarPorSupervisor(int idSupervisor) {
        ArrayList<Notificacion> entities = new ArrayList<>();
         try (Connection conn = DBManager.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(getSelectBySupervisorQuery())) {
            ps.setInt(1, idSupervisor);
            try (ResultSet rs = ps.executeQuery()) {
                 while (rs.next()) {
                    entities.add(createFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener entidad", e);
        }
        return entities;
    }

    private String getSelectBySupervisorQuery() {
        return "SELECT * FROM Notificacion WHERE idEmpleado = ?";
    }
    
}
