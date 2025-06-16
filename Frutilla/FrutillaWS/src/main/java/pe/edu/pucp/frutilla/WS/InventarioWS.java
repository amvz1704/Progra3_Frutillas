/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.frutilla.WS;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.ws.WebServiceException;
import java.util.List;
import pe.edu.pucp.frutilla.logica.inventario.BebidaService;
import pe.edu.pucp.frutilla.logica.inventario.FrutaService;
import pe.edu.pucp.frutilla.logica.inventario.InventarioService;
import pe.edu.pucp.frutilla.logica.inventario.ProductoService;
import pe.edu.pucp.frutilla.logica.inventario.SnackService;
import pe.edu.pucp.frutilla.models.inventario.Bebida;
import pe.edu.pucp.frutilla.models.inventario.Fruta;
import pe.edu.pucp.frutilla.models.inventario.Producto;
import pe.edu.pucp.frutilla.models.inventario.Snack;
import pe.edu.pucp.frutilla.models.inventario.TipoEstado;

/**
 *
 * @author User
 */
@WebService(serviceName = "InventarioWS")
@XmlSeeAlso({Fruta.class, Bebida.class, Snack.class})
public class InventarioWS {

    private InventarioService inventarioService;
    private ProductoService productoService;
    private SnackService snackService;
    private BebidaService bebidaService;
    private FrutaService frutaService;
    
    public InventarioWS(){
        inventarioService = new InventarioService();
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
    
    @WebMethod(operationName = "insertarFruta")
    public void insertarFruta(Fruta producto, int idLocal){
        try{
            frutaService = new FrutaService();
            boolean requiere = producto.isRequiereEnvase();
            frutaService.agregar((Fruta)producto);
            producto.setTipoEstado(TipoEstado.DISPONIBLE);
                
            inventarioService.insertar(producto, idLocal);
        }
        catch (Exception e){
            throw new WebServiceException("Error al agregar fruta");
        }
    }
    
    @WebMethod(operationName = "insertarBebida")
    public void insertarBebida(Bebida producto, int idLocal){
        try{
            bebidaService = new BebidaService();
            bebidaService.agregar((Bebida)producto);
            producto.setTipoEstado(TipoEstado.DISPONIBLE);
                
            inventarioService.insertar(producto, idLocal);
        }
        catch (Exception e){
            throw new WebServiceException("Error al agregar fruta");
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
            throw new WebServiceException("Error al eliminar producto");
        }
    }
    
    @WebMethod(operationName = "obtenerProductoPorId")
    public Producto obtenerProductoPorId(@WebParam(name = "idProducto") int idProducto) {
        productoService = new ProductoService();
        try {
            // Usamos el servicio de Producto para obtener el Producto completo
            Producto pedido = productoService.obtenerPorId(idProducto); // Asegúrate de tener el método en ProductoService
            return pedido;
        } catch (Exception ex) {
            System.err.println("Error al obtener el producto con ID " + idProducto + ": " + ex.getMessage());
        }
        return null;
    }
    
//    Falta agregar filtrar en persistencia
//    @WebMethod(operationName = "filtrarPorTipo")
//    public List<Producto> filtrarPorTipo(int idLocal, char tipo){
//        try{
//            return inventarioService.filtrarPorTipo(idLocal, tipo);
//        }
//        catch (Exception e){
//            throw new WebServiceException("Error al filtrar por tipo");
//        }
//    }
    
}