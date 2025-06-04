/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.frutilla.WS;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.xml.ws.WebServiceException;
import java.util.List;
import pe.edu.pucp.frutilla.logica.inventario.InventarioService;
import pe.edu.pucp.frutilla.models.inventario.Producto;

/**
 *
 * @author User
 */
@WebService(serviceName = "InventarioWS")
public class InventarioWS {

    private InventarioService inventarioService;
    
    public InventarioWS(){
        inventarioService = new InventarioService();
    }
    
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
    
    @WebMethod(operationName = "listarTodos")
    public List<Producto> listarTodos(@WebParam(name = "idLocal")int idLocal){
        try{
            return inventarioService.listar(idLocal);
        }
        catch (Exception e){
            throw new WebServiceException("Error al listar productos");
        }
    }
    
    @WebMethod(operationName = "insertarProducto")
    public void insertarProducto(Producto producto, int idLocal){
        try{
            inventarioService.insertar(producto, idLocal);
        }
        catch (Exception e){
            throw new WebServiceException("Error al agregar producto");
        }
    }
    
    @WebMethod(operationName = "actualizarProducto")
    public void actualizarProducto(Producto producto, int idLocal){
        try{
            inventarioService.actualizar(producto, idLocal);
        }
        catch (Exception e){
            throw new WebServiceException("Error al actualizar producto");
        }
    }
    
    @WebMethod(operationName = "eliminarProducto")
    public void eliminarProducto(int idProducto, int idLocal){
        try{
            inventarioService.eliminar(idProducto, idLocal);
        }
        catch (Exception e){
            throw new WebServiceException("Error al actualizar producto");
        }
    }
}
