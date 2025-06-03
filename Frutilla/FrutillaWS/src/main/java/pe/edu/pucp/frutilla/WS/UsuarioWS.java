/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.frutilla.WS;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import pe.edu.pucp.frutilla.logica.rrhh.UsuarioService;
import pe.edu.pucp.frutilla.models.rrhh.Persona;
import pe.edu.pucp.frutilla.models.rrhh.Cliente;
import pe.edu.pucp.frutilla.models.rrhh.Empleado;

/**
 *
 * @author User
 */
@WebService(serviceName = "UsuarioWS")
public class UsuarioWS {
    
    private UsuarioService usuarioService;
    
    public UsuarioWS(){
        usuarioService = new UsuarioService();
    }
    
    @WebMethod(operationName = "validarUsuario")
    public Persona validarUsuario(String usuario, String password){
        try{
            return usuarioService.validarUsuario(usuario, password);
        } catch (Exception ex){
            System.out.println(ex.getMessage()); 
        }
        return null;
    }
    
    @WebMethod(operationName = "obtenerIDPorNombreUsuario")
    public int obtenerIDPorNombreUsuario(String nombreUsuario){
        try{
            return usuarioService.obtenerIDPorNombreUsuario(nombreUsuario);
        }catch(Exception ex){
            System.out.println(ex.getMessage()); 
        }
        return -1;
    }
}
