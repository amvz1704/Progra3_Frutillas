package pe.edu.pucp.frutilla.logica.rrhh;

import java.util.List;

import pe.edu.pucp.frutilla.crud.mysql.rrhh.UsuarioMySQL;
import pe.edu.pucp.frutilla.models.rrhh.Cliente;
import pe.edu.pucp.frutilla.models.rrhh.Empleado;
import pe.edu.pucp.frutilla.models.rrhh.Persona;

public class UsuarioService {
    
    private final UsuarioMySQL usuarioMySQL;

    public UsuarioService() {
        usuarioMySQL = new UsuarioMySQL();
    }

    public void agregar(Persona persona) throws Exception {
        // Validaciones

        if (persona == null) {
            throw new Exception("La persona no puede ser nula");
        }
        if (persona.getUsuarioSistema() == null || persona.getUsuarioSistema().trim().isEmpty()) {
            throw new Exception("El usuario no puede ser vacío");
        }

        if (persona.getContraSistema() == null || persona.getContraSistema().trim().isEmpty()) {
            throw new Exception("La contraseña no puede ser vacía");
        }

    }

    public void actualizar(Persona persona) throws Exception {
        // Validaciones
        if (persona == null) {
            throw new Exception("La persona no puede ser nula");
        }

        if(persona instanceof Empleado){
            if (((Empleado) persona).getIdEmpleado() <= 0) {
                throw new Exception("El id del empleado no puede ser menor o igual a 0");
            }
        } else if (persona instanceof Cliente) {
            if (((Cliente) persona).getIdCliente() <= 0) {
                throw new Exception("El id del cliente no puede ser menor o igual a 0");
            }
        }   

        if (persona.getUsuarioSistema() == null || persona.getUsuarioSistema().trim().isEmpty()) {
            throw new Exception("El usuario no puede ser vacío");
        }

        if (persona.getContraSistema() == null || persona.getContraSistema().trim().isEmpty()) {
            throw new Exception("La contraseña no puede ser vacía");
        }

    }

    public void eliminar(int idUsuario) throws Exception {
        if (idUsuario <= 0) {
            throw new Exception("El id de usuario no es válido");
        }
        usuarioMySQL.eliminar(idUsuario);
    }

    public Persona obtener(int idUsuario) throws Exception {
        if (idUsuario <= 0) {
            throw new Exception("El id de usuario no es válido");
        }
        return usuarioMySQL.obtener(idUsuario);
    }

    public List<Persona> listar() throws Exception{
        return usuarioMySQL.listarTodos();
    }
    
}
