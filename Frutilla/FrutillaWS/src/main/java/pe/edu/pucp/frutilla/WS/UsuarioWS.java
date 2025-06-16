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
    
    @WebMethod(operationName = "generarTokenRecuperacion")
    public String generarTokenRecuperacion(@WebParam(name = "idUsuario") int idUsuario) {
        return TokenManager.generarToken(idUsuario);
    }
    
    @WebMethod(operationName = "validarTokenRecuperacion")
    public int validarTokenRecuperacion(@WebParam(name = "token") String token){
        int idUsuario = TokenManager.validarToken(token);
        return idUsuario;
    }
    
    @WebMethod(operationName = "actualizarContrasena")
    public boolean actualizarContrasena(@WebParam(name = "token") String token, @WebParam(name = "nuevaContrasena") String nuevaContrasena){
        int idUsuario = TokenManager.validarToken(token);
        if(idUsuario == -1) return false;
        
        try{
            Persona usuario = usuarioService.obtener(idUsuario);
            usuario.setContraSistema(nuevaContrasena);
            usuarioService.actualizar(usuario);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    @WebMethod(operationName = "obtenerPorCorreo")
    public Persona obtenerPorCorreo(String correo){
        try{
            return usuarioService.obtenerPorCorreo(correo);
        } catch (Exception ex){
            System.out.println(ex.getMessage()); 
        }
        return null;
    }
    
    @WebMethod(operationName = "correoExiste")
    public boolean correoExiste(@WebParam(name = "correo") String correo){
        try {
            return usuarioService.correoExiste(correo);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
