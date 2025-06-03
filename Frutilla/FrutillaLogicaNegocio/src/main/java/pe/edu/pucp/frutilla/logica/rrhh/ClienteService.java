package pe.edu.pucp.frutilla.logica.rrhh;

import java.util.List;

import pe.edu.pucp.frutilla.crud.mysql.rrhh.ClienteMySQL;
import pe.edu.pucp.frutilla.models.rrhh.Cliente;

public class ClienteService {

    private final ClienteMySQL clienteMySQL;
    
    public ClienteService() {
        clienteMySQL = new ClienteMySQL();
    }

    public void agregar(Cliente cliente) throws Exception {
        // Validaciones
        if (cliente == null) {
            throw new Exception("El cliente no puede ser nulo");
        }

        if (cliente.getNombre() == null || cliente.getNombre().trim().isEmpty()) {
            throw new Exception("El nombre del cliente no puede ser vacío");
        }

        if (cliente.getApellidoPaterno() == null || cliente.getApellidoPaterno().trim().isEmpty()) {
            throw new Exception("El apellido paterno del cliente no puede ser vacío");
        }

        if (cliente.getApellidoMaterno() == null || cliente.getApellidoMaterno().trim().isEmpty()) {
            throw new Exception("El apellido materno del cliente no puede ser vacío");
        }

        if (cliente.getTelefono() == null || cliente.getTelefono().trim().isEmpty()) {
            throw new Exception("El teléfono del cliente no puede ser vacío");
        }

        if (cliente.getCorreoElectronico() == null || cliente.getCorreoElectronico().trim().isEmpty()) {
            throw new Exception("El correo electrónico del cliente no puede ser vacío");
        }

        if (cliente.getUsuarioSistema() == null || cliente.getUsuarioSistema().trim().isEmpty()) {
            throw new Exception("El usuario del cliente no puede ser vacío");
        }

        if (cliente.getContraSistema() == null || cliente.getContraSistema().trim().isEmpty()) {
            throw new Exception("La contraseña del cliente no puede ser vacía");
        }
        

        clienteMySQL.agregar(cliente);
    }

    public void actualizar(Cliente cliente) throws Exception {
        // Validaciones
        if (cliente == null) {
            throw new Exception("El cliente no puede ser nulo");
        }

        if (cliente.getIdUsuario()<= 0) {
            throw new Exception("El id del cliente no puede ser menor o igual a 0");
        }

        if (cliente.getNombre() == null || cliente.getNombre().trim().isEmpty()) {
            throw new Exception("El nombre del cliente no puede ser vacío");
        }

        if (cliente.getApellidoPaterno() == null || cliente.getApellidoPaterno().trim().isEmpty()) {
            throw new Exception("El apellido paterno del cliente no puede ser vacío");
        }

        if (cliente.getApellidoMaterno() == null || cliente.getApellidoMaterno().trim().isEmpty()) {
            throw new Exception("El apellido materno del cliente no puede ser vacío");
        }

        if (cliente.getTelefono() == null || cliente.getTelefono().trim().isEmpty()) {
            throw new Exception("El teléfono del cliente no puede ser vacío");
        }

        if (cliente.getCorreoElectronico() == null || cliente.getCorreoElectronico().trim().isEmpty()) {
            throw new Exception("El correo electrónico del cliente no puede ser vacío");
        }

        clienteMySQL.actualizar(cliente);
    }

    public void eliminar(int idCliente) throws Exception {
        if (idCliente <= 0) {
            throw new Exception("El id del cliente no es válido");
        }
        clienteMySQL.eliminar(idCliente);
    }

    public Cliente obtener(int idCliente) throws Exception {
        if (idCliente <= 0) {
            throw new Exception("El id del cliente no es válido");
        }
        return clienteMySQL.obtener(idCliente);
    }

    public List<Cliente> listar() throws Exception{
        return clienteMySQL.listarTodos();
    }

}
