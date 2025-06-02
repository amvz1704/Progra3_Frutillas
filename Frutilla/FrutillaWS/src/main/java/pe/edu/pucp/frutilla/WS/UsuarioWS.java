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
    public int validarUsuario(String usuario, String password){
        try{
            Persona persona = usuarioService.validarUsuario(usuario, password);
            if(persona instanceof Cliente){
                return ((Cliente) persona).getIdCliente();
            }
            else{
                return ((Empleado) persona).getIdEmpleado();
            }
        } catch (Exception ex){
            System.out.println(ex.getMessage()); 
        }
        return -1;
    }
}
