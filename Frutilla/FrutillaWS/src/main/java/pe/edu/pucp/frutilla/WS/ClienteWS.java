/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.frutilla.WS;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import pe.edu.pucp.frutilla.logica.rrhh.ClienteService;
import pe.edu.pucp.frutilla.models.rrhh.Cliente;

@WebService(serviceName = "ClienteWS")
public class ClienteWS {
    
    private ClienteService clienteService;
    
    public ClienteWS(){
        clienteService = new ClienteService();
    }

    @WebMethod(operationName = "agregarCliente")
    public void agregarCliente(Cliente cliente){
        try{
            clienteService.agregar(cliente);
        }catch (Exception ex){
            System.out.println(ex.getMessage()); 
        }
    }
    
    @WebMethod(operationName = "obtenerClientePorId")
    public Cliente obtenerClientePorId(@WebParam (name ="Idcliente") int Idcliente){
        try{
            return clienteService.obtener(Idcliente);
        }catch (Exception ex){
            System.out.println(ex.getMessage()); 
        }
        return null; 
    }
}
