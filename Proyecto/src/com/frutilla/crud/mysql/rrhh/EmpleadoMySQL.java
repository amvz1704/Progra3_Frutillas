package com.frutilla.crud.mysql.rrhh;

import com.frutilla.crud.dao.rrhh.EmpleadoDAO; //incluimos el dao correspondiente 
import com.frutilla.models.rrhh.Empleado //Empleado

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.frutilla.config.DBManager;

public class EmpleadoMySQL implements EmpleadoDAO{
    public void insertarEmpleado(Empleado empleado, int idLocal) throws SQLException{
        String query = "INSERT INTO Empleado (nombre, apellidoPaterno, apellidoMaterno, telefono, correoElectronico, usuarioSistema, contrasSistema, activo, fechaContrato, salario, turnoTrabajo, tipo, idLocal) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try(Connection con = DBManager.getConnection(); PreparedStatement ps = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS )){// Obtiene la conexion y prepara la consulta
            setEmpleadoParameters(ps, empleado, idLocal);// Establece los parámetros del empleado
            ps.executeUpdate();// Ejecuta la consulta
            try(ResultSet rs = ps.getGeneratedKeys()){// Obtiene las claves generadas por la consulta anterior
                if (rs.next()) {
                    empleado.setIdEmpleado(rs.getInt(1)); // Establece el ID en el objeto empleado
                }
            }     
        }
    }
    
    public Empleado obtenerEmpleadoPorId(int idCliente) throws SQLException{
        String query = "SELECT * FROM Empleado WHERE idEmpleado = ?";
        try(Connection con = DBManager.getConnection(); PreparedStatement ps = con.prepareStatement(query)){
            ps.setInt(1,idCliente);// Establece el ID del empleado en la consulta
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    return mapEmpleado(rs);
                }
            }
        }
        return null;
    }

    public void actualizarEmpleado(Empleado empleado, int idLocal) throws SQLException{
        String query = "UPDATE Empleado SET nombre = ?, apellidoPaterno = ?, apellidoMaterno = ?, telefono = ?, correoElectronico = ?, usuarioSistema = ?, contrasSistema = ?, activo = ?, fechaContrato = ?, salario = ?, turnoTrabajo = ?, tipo = ?, idLocal = ? WHERE idEmpleado = ?";
        try(Connection con = DBManager.getConnection(); PreparedStatement ps = con.prepareStatement(query)){
            setEmpleadoParameters(ps, empleado, idLocal);// Establece los parámetros del empleado
            ps.setInt(12, empleado.getIdEmpleado());
            ps.executeUpdate();
        }
    }
    public void eliminarEmpleado(int idEmpleado) throws SQLException{
        String query = "UPDATE Empleado SET activo = false WHERE idEmpleado = ?";
        try(Connection con = DBManager.getConnection(); PreparedStatement ps = con.prepareStatement(query)){
            ps.setInt(1, idEmpleado);// Establece el ID del empleado en la consulta
            ps.executeUpdate();
        }
    }
    
    public ArrayList<Empleado> obtenerEmpleados(int idLocal)throws SQLException{
        ArrayList<Empleado> empleados = new ArrayList<Empleado>();// Crea una lista para almacenar los empleados
        String query = "SELECT * FROM Empleado WHERE idLocal = ? AND activo = true";
        try(Connection con = DBManager.getConnection(); PreparedStatement ps = con.prepareStatement(query)){
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
        empleado.setNombre(rs.getString("nombre"));
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
        ps.setString(1, empleado.getNombre());
        ps.setString(2, empleado.getApellidoPaterno());
        ps.setString(3, empleado.getApellidoMaterno());
        ps.setString(4, empleado.getTelefono());
        ps.setString(5, empleado.getCorreoElectronico());
        ps.setString(6, empleado.getUsuarioSistema());
        ps.setString(7, empleado.getContraSistema());
        ps.setBoolean(8, empleado.getActivo());
        ps.setDate(9, java.sql.Date.valueOf(empleado.getFechaContrato()));
        ps.setDouble(10, empleado.getSalario());
        ps.setBoolean(11, empleado.getTurnoTrabajo());
        ps.setString(12, String.valueOf(empleado.getTipo()));
        ps.setInt(13, idLocal);
        
    }
}
