package pe.edu.pucp.frutilla.crud.mysql.local;


import pe.edu.pucp.frutilla.crud.dao.local.LocalDAO; //incluimos la interfaz del local

import pe.edu.pucp.frutilla.crud.dao.rrhh.EmpleadoDAO; //Incluye EmpleadoDAO 
import pe.edu.pucp.frutilla.crud.mysql.rrhh.EmpleadoMySQL; //incluimos EmpleadoMySQL 

import pe.edu.pucp.frutilla.crud.dao.inventario.ProductoDAO; 
import pe.edu.pucp.frutilla.crud.mysql.inventario.ProductoMySQL; 


import pe.edu.pucp.frutilla.crud.dao.inventario.InventarioDAO;
import pe.edu.pucp.frutilla.crud.mysql.inventario.InventarioMySQL; 

import pe.edu.pucp.frutilla.crud.dao.inventario.SnackDAO;
import pe.edu.pucp.frutilla.crud.mysql.inventario.SnackMySQL; 

import pe.edu.pucp.frutilla.crud.dao.inventario.BebidaDAO;
import pe.edu.pucp.frutilla.crud.mysql.inventario.BebidaMySQL; 


import pe.edu.pucp.frutilla.crud.dao.inventario.FrutaDAO;
import pe.edu.pucp.frutilla.crud.mysql.inventario.FrutaMySQL; 

import pe.edu.pucp.frutilla.crud.dao.venta.OrdenVentaDAO;
import pe.edu.pucp.frutilla.crud.mysql.venta.OrdenVentaMySQL; 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.frutilla.config.DBManager;

//importado de frutilla.models
import pe.edu.pucp.frutilla.models.local.Local; //incluimos LOCAL 
import pe.edu.pucp.frutilla.models.rrhh.Empleado; //incluimos Empleado 
import pe.edu.pucp.frutilla.models.inventario.Producto; //incluimos Producto 
import pe.edu.pucp.frutilla.models.inventario.Bebida; //incluimos Producto 
import pe.edu.pucp.frutilla.models.inventario.Fruta; //incluimos Producto 
import pe.edu.pucp.frutilla.models.inventario.Snack; //incluimos Producto 

import pe.edu.pucp.frutilla.models.venta.OrdenVenta;

import pe.edu.pucp.frutilla.crud.mysql.BaseDAOImpl; 
import pe.edu.pucp.frutilla.models.inventario.TipoEstado;


	

public class LocalMySQL extends BaseDAOImpl<Local> implements LocalDAO{
     //primero los tipo inter
    @Override
    protected String getInsertQuery() {
        // Ajusta los nombres de columna a tu tabla real
        return "INSERT INTO Local (nombre, descripcion, direccion, telefono) VALUES (?, ?, ?, ?)";
    }
    
    @Override
    protected String getUpdateQuery() {
        return "UPDATE Local SET nombre = ?, descripcion= ?, direccion = ?, activo = ?, telefono = ? WHERE idLocal = ?";
    }
    
    @Override
    protected String getDeleteQuery() {
        return "UPDATE Local SET activo = false WHERE idLocal = ?";
    }
  
    @Override
    protected String getSelectByIdQuery() {
        return "SELECT idLocal, nombre, descripcion, direccion, telefono, activo FROM Local WHERE idLocal = ?";
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
    
    
    
    //actualizar requiere una copia de todos 
    //los empleados, ordenes de venta y productos? NO,
    //porque nuestra tabla de local no almacena essos datos.
    //eso lo tiene que ver negocio y WS
    
    
    //tampoco es necesario, pues la tabla de datos no obtiene l
    
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
    public List<OrdenVenta> encontrarVentas(int idLocal) throws SQLException{
        OrdenVentaMySQL interfazVenta = new OrdenVentaMySQL();  //falta actualizar DAO para que lo implemente
        return interfazVenta.listarPorLocal(idLocal); //por ahora mientras que no este implementado OrdenVenta
       // return interfazVenta.obtenerTodosPorLocal(idLocal);
    }
}