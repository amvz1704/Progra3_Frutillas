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
		try(Connection con = DBManager.getInstance().getConnection(); PreparedStatement ps = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS )){// Obtiene la conexion y prepara la consulta
			setLocalParameters(ps, local);// Establece los parámetros del local
			ps.executeUpdate();// Ejecuta la consulta
			try(ResultSet rs = ps.getGeneratedKeys()){// Obtiene las claves generadas por la consulta anterior
				if (rs.next()) {
					local.setIdLocal(rs.getInt(1)); // Establece el ID en el objeto local
				}
			}     
		}
	}
	
	
	public ArrayList<Local> obtenerTodosLocales() throws SQLException{
		ArrayList<Local> locales=new ArrayList<Local>();
		
        String query="SELECT * FROM Local WHERE activo = true"; //Consulta SQL para obtener todos los locales activos
        try(Connection con=DBManager.getInstance().getConnection();
            PreparedStatement ps=con.prepareStatement(query);
			ResultSet rs=ps.executeQuery()){
            
				while(rs.next()){
					locales.add(mapLocal(rs));
				}  
        }
		
        return locales;
		
		
	}
	
	
	
	public Local obtenerLocalPorId(int idLocal) throws SQLException{
        String query = "SELECT * FROM Local WHERE idLocal = ?";
        try(Connection con = DBManager.getInstance().getConnection(); PreparedStatement ps = con.prepareStatement(query)){
            ps.setInt(1,idLocal);// Establece el ID del local en la consulta
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    return mapLocal(rs);
                }
            }
        }
        return null;
    }
	
	
	private Local mapLocal(ResultSet rs) throws SQLException{
        
        Local local = new Local(rs.getString("nombre"), rs.getString("descripcion"), rs.getString("direccion"), rs.getString("telefono"));
        
        local.setIdLocal(rs.getInt("idLocal"));
		local.setActivo(rs.getBoolean("activo"));
        

        return local;
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