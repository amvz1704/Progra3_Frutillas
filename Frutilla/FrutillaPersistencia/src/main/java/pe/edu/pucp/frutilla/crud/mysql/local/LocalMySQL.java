package pe.edu.pucp.frutilla.crud.mysql.local;


import pe.edu.pucp.frutilla.crud.dao.local.LocalDAO; //incluimos la interfaz del local

import pe.edu.pucp.frutilla.crud.dao.rrhh.EmpleadoDAO; //Incluye EmpleadoDAO 
import pe.edu.pucp.frutilla.crud.mysql.rrhh.EmpleadoMySQL; //incluimos EmpleadoMySQL 

import pe.edu.pucp.frutilla.crud.dao.inventario.ProductoDAO; 
import pe.edu.pucp.frutilla.crud.mysql.inventario.ProductoMySQL; 


import pe.edu.pucp.frutilla.crud.dao.venta.OrdenVentaDAO;
import pe.edu.pucp.frutilla.crud.mysql.venta.OrdenVentaMySQL; 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import pe.edu.pucp.frutilla.config.DBManager;

//importado de frutilla.models
import pe.edu.pucp.frutilla.models.local.Local; //incluimos LOCAL 
import pe.edu.pucp.frutilla.models.rrhh.Empleado; //incluimos Empleado 
import pe.edu.pucp.frutilla.models.inventario.Producto; //incluimos Producto 
import pe.edu.pucp.frutilla.models.venta.OrdenVenta;

import pe.edu.pucp.frutilla.crud.mysql.BaseDAOImpl; 


	

public class LocalMySQL extends BaseDAOImpl<Local> implements LocalDAO{
     //primero los tipo inter
    @Override
    protected String getInsertQuery() {
        // Ajusta los nombres de columna a tu tabla real
        return "INSERT INTO Local (nombre, descripcion, direccion, telefono) VALUES (?, ?, ?, ?)";
    }
    
    @Override
    protected String getUpdateQuery() {
        return "UPDATE Local SET nombre = ?, descripcion= ?, direccion = ?, activo = ?, telefono = ? WHERE idlocal = ?";
    }
    
    @Override
    protected String getDeleteQuery() {
        return "UPDATE Local SET activo = false WHERE idLocal = ?";
    }
  
    @Override
    protected String getSelectByIdQuery() {
        return "SELECT * FROM Local WHERE idlocal = ?";
    }

    @Override
    protected String getSelectAllQuery() {
        return "SELECT idLocal, nombre, descripcion, direccion, telefono, activo FROM Local WHERE activo = true";
    }
    //creacion de un local en base a un objeto "Local inicializado"
    @Override
    protected void setInsertParameters(PreparedStatement ps, Local entity) throws SQLException{
        // Aquí van en orden los “?” de getInsertQuery()
        
        ps.setString(1, entity.getNombre());
        ps.setString(2, entity.getDescripcion());
        ps.setString(3, entity.getDireccion());
        ps.setString(4, entity.getTelefono());
    } 
    //creacion de un local en base a un objeto "Local inicializado"
    
    
    @Override
    protected Local createFromResultSet(ResultSet rs) throws SQLException{
        Local local = new Local(rs.getString("nombre"), rs.getString("descripcion"),
                rs.getString("direccion"), rs.getString("telefono"));
        local.setIdLocal(rs.getInt("idLocal")); //revisar si tiene este nombre
        local.setActivo(rs.getBoolean("activo"));
        
        return local;
    }
    
    
    
    //actualizar requiere una copia de todos los empleados
    
    @Override
    public void actualizar(Local entity) {
        super.actualizar(entity);
        
        //tambien deben actualizarse los empleados a la base de datos
        EmpleadoDAO interfazEmpleado = new EmpleadoMySQL(); 
        ProductoDAO interfazProducto = new ProductoMySQL(); 
        LocalDAO interfazLocal = new LocalMySQL(); 
        
        //por ahora se harán inactivos --> pero sí debo eliminar definitivamente porque no deja actualizarLocal 
        
        for(Empleado e: entity.getEmpleados()){
            
            Empleado copia = new Empleado(e); 
            interfazLocal.eliminarEmpleado(e.getIdEmpleado()); 
            interfazEmpleado.agregar(copia);
        }
        
        //los productos 
        for(Producto e: entity.getProductos()){
            Producto copia = new Producto(e); 
            interfazLocal.eliminarProducto(e.getIdProducto());
            interfazProducto.agregar(copia);
        }
        
    }
    
    public void eliminarEmpleado(int idEmpleado){
        
        try (Connection conn = DBManager.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Empleado WHERE idUsuario = ?")){
            
            ps.setInt(1, idEmpleado);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar empleado", e);
        }
        
        //Deberiamos tener un trigger que cuando se elimine un empleado se active a eliminar el usuario de dicho
    
        try (Connection conn = DBManager.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Usuario WHERE idUsuario = ?")){
            
            ps.setInt(1, idEmpleado);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar usuario", e);
        }
    
    }
    
    public void eliminarProducto(int idProducto){
    
        try (Connection conn = DBManager.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Producto WHERE idProducto = ?")){
            
            ps.setInt(1, idProducto);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar entidad", e);
        }
    
    }
    
    
    
    
    
    @Override 
    protected void setUpdateParameters(PreparedStatement ps, Local entity) throws SQLException{
            ps.setString(1, entity.getNombre());
            ps.setString(2, entity.getDescripcion());
            ps.setString(3, entity.getDireccion());
            ps.setBoolean(4, entity.getActivo());
            ps.setString(5, entity.getTelefono());
            ps.setInt(6, entity.getIdLocal());
    }
    //
    @Override
    protected void setId(Local entity, Integer id) {
        entity.setIdLocal(id);
    }
    
    //Devuelve una lista de empleados de un local por id --> llama a EmpleadoDAOSQL //CAMBIAR
    @Override
    public ArrayList<Empleado> encontrarEmpleados(int idLocal) throws SQLException{

        EmpleadoDAO interfazEmpleado = new EmpleadoMySQL(); 
        return interfazEmpleado.listarTodosPorLocal(idLocal);
    }

    //Devuelve una lista de producto de un local por id --> llama a ProductosDAOSQL 
    @Override
    public ArrayList<Producto> encontrarProductos(int idLocal) throws SQLException{
        ProductoDAO interfazProducto = new ProductoMySQL(); 
        return interfazProducto.obtenerTodosPorLocal(idLocal); 
    }
    
    @Override
    public ArrayList<OrdenVenta> encontrarVentas(int idLocal) throws SQLException{
        OrdenVentaMySQL interfazVenta = new OrdenVentaMySQL();  //falta actualizar DAO para que lo implemente
        return new ArrayList<>(interfazVenta.listarPorLocal(idLocal));
    }
}