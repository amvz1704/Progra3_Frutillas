package com.frutilla.crud.mysql.rrhh;

import com.frutilla.crud.dao.rrhh.EmpleadoDAO; //incluimos el dao correspondiente 
import com.frutilla.models.rrhh.Empleado; //Empleado
import com.frutilla.models.rrhh.Supervisor; //Supervisor pe causa
import com.frutilla.models.rrhh.Repartidor; // Repartidor pe causa

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.frutilla.config.DBManager;

public class EmpleadoMySQL implements EmpleadoDAO{
	
	
	public EmpleadoMySQL(){
		
	}
	
	
    public void insertarEmpleado(Empleado empleado, int idLocal) throws SQLException{
        String query = "INSERT INTO Empleado (idUsuario, nombres, apellidoPaterno, apellidoMaterno, telefono, correoElectronico, fechaContrato, salario, turnoTrabajo, tipo, idLocal) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try(Connection con = DBManager.getInstance().getConnection(); PreparedStatement ps = con.prepareStatement(query)){// Obtiene la conexion y prepara la consulta
            UsuarioMySQL usuarioMySQL = new UsuarioMySQL();// Crea una instancia de UsuarioMySQL
            usuarioMySQL.insertarUsuario(empleado, con);// Inserta el usuario en la base de datos
            setEmpleadoParameters(ps, empleado, idLocal);// Establece los parámetros del empleado    
            ps.executeUpdate();// Ejecuta la consulta
        }
    }
    
    public Empleado obtenerEmpleadoPorId(int idEmpleado) throws SQLException{
        String query = "SELECT u.idUsuario, u.usuarioSistema, u.contrasSistema, u.activo, e.nombres, e.apellidoPaterno, e.apellidoMaterno, e.telefono, e.correoElectronico, e.fechaContrato, e.salario, e.turnoTrabajo, e.tipo, e.idLocal FROM Usuario u JOIN Empleado e ON e.idUsuario = u.idUsuario WHERE e.idUsuario = ? AND u.activo = true";
        try(Connection con = DBManager.getInstance().getConnection(); PreparedStatement ps = con.prepareStatement(query)){
            ps.setInt(1,idEmpleado);// Establece el ID del empleado en la consulta
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    return mapEmpleado(rs);
                }
            }
        }
        return null;
    }

    public void actualizarEmpleado(Empleado empleado, int idLocal) throws SQLException{
        String query = "UPDATE Empleado SET idUsuario = ?, nombres = ?, apellidoPaterno = ?, apellidoMaterno = ?, telefono = ?, correoElectronico = ?, fechaContrato = ?, salario = ?, turnoTrabajo = ?, tipo = ?, idLocal = ? WHERE idUsuario = ?";
        try(Connection con = DBManager.getInstance().getConnection(); PreparedStatement ps = con.prepareStatement(query)){
            UsuarioMySQL usuarioMySQL = new UsuarioMySQL();// Crea una instancia de UsuarioMySQL
            usuarioMySQL.actualizarUsuario(empleado, con);
            setEmpleadoParameters(ps, empleado, idLocal);// Establece los parámetros del empleado
            ps.setInt(12, empleado.getIdEmpleado());
            ps.executeUpdate();
        }
    }
    public void eliminarEmpleado(int idEmpleado) throws SQLException{
        String query = "UPDATE Usuario SET activo = false WHERE idUsuario = ?";
        try(Connection con = DBManager.getInstance().getConnection(); PreparedStatement ps = con.prepareStatement(query)){
            ps.setInt(1, idEmpleado);// Establece el ID del empleado en la consulta
            ps.executeUpdate();
        }
    }
    
    public ArrayList<Empleado> obtenerTodos(int idLocal)throws SQLException{
        ArrayList<Empleado> empleados = new ArrayList<Empleado>();// Crea una lista para almacenar los empleados
        String query = "SELECT u.idUsuario, u.usuarioSistema, u.contrasSistema, u.activo, e.nombres, e.apellidoPaterno, e.apellidoMaterno, e.telefono, e.correoElectronico, e.fechaContrato, e.salario, e.turnoTrabajo, e.tipo, e.idLocal FROM Usuario u JOIN Empleado e ON e.idUsuario = u.idUsuario WHERE e.idLocal = ? AND Usuario.activo = true";
        try(Connection con = DBManager.getInstance().getConnection(); PreparedStatement ps = con.prepareStatement(query)){
            ps.setInt(1, idLocal);// Establece el ID del local en la consulta
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    empleados.add(mapEmpleado(rs));// Mapea los datos de cada empleado y los agrega a la lista
                }
            }
        }
        return empleados;
    }


    private Empleado mapEmpleado(ResultSet rs) throws SQLException{
        char tipo = rs.getString("tipo").charAt(0);
        Empleado empleado = null;
        if(tipo == 'S'){
            empleado = new Supervisor();
        }
        else{
            empleado = new Repartidor();
        }
        
        empleado.setIdEmpleado(rs.getInt("idEmpleado"));
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

        return empleado;
    }
    private void setEmpleadoParameters(PreparedStatement ps,Empleado empleado, int idLocal) throws SQLException {
        // Establece los parámetros del empleado en la consulta SQL
        ps.setInt(1, empleado.getIdEmpleado());
        ps.setString(2, empleado.getNombre());
        ps.setString(3, empleado.getApellidoPaterno());
        ps.setString(4, empleado.getApellidoMaterno());
        ps.setString(5, empleado.getTelefono());
        ps.setString(6, empleado.getCorreoElectronico());
        ps.setDate(7, java.sql.Date.valueOf(empleado.getFechaContrato()));
        ps.setDouble(8, empleado.getSalario());
        ps.setBoolean(9, empleado.getTurnoTrabajo());
        ps.setString(10, String.valueOf(empleado.getTipo()));
        ps.setInt(11, idLocal);
        
    }
}
