package com.frutilla.crud.mysql.local;


import com.frutilla.crud.dao.local.LocalDAO; //incluimos la interfaz del local

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.frutilla.models.local.Local; //incluimos LOCAL 


import com.frutilla.config.DBManager; //

public class LocalMySQL implements LocalDAO{
	
	//creacion de un local en base a un objeto "Local inicializado"
	public void crearLocal(Local local) throws SQLException{
		String query = "INSERT INTO Local (nombre, descripcion, direccion, activo, telefono) VALUES (?, ?, ?, ?, ?)";
		try(Connection con = DBManager.getConnection(); PreparedStatement ps = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS )){// Obtiene la conexion y prepara la consulta
			setLocalParameters(ps, local);// Establece los parámetros del local
			ps.executeUpdate();// Ejecuta la consulta
			try(ResultSet rs = ps.getGeneratedKeys()){// Obtiene las claves generadas por la consulta anterior
				if (rs.next()) {
					local.setIdLocal(rs.getInt(1)); // Establece el ID en el objeto local
				}
			}     
		}
	}
	
	
	private void setLocalParameters(PreparedStatement ps,Local local) throws SQLException {
        // Establece los parámetros del empleado en la consulta SQL
        ps.setString(1, local.getNombre());
        ps.setString(2, local.getDescripcion());
        ps.setString(3, local.getDireccion());
        ps.setBoolean(4, local.getActivo());
        ps.setString(5, local.getTelefono());
    }
	
}