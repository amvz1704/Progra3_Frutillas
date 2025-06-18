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
        if (persona == null) {
            throw new Exception("La persona no puede ser nula");
        }

        if (persona.getIdUsuario()<= 0) {
            throw new Exception("El id del usuario no puede ser menor o igual a 0");
        }

        if (persona.getUsuarioSistema() == null || persona.getUsuarioSistema().trim().isEmpty()) {
            throw new Exception("El usuario no puede ser vacío");
        }
        
        if (persona.getContraSistema() == null || persona.getContraSistema().trim().isEmpty()) {
            throw new Exception("La contraseña no puede ser vacía");
        }
        usuarioMySQL.actualizar(persona);
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
    
    public Persona validarUsuario(String usuario, String password) throws Exception{
        if (usuario == null || usuario.trim().isEmpty()) {
            throw new Exception("El usuario no puede ser vacío");
        }

        if (password == null || password.trim().isEmpty()) {
            throw new Exception("La contraseña no puede ser vacía");
        }

        return usuarioMySQL.validarUsuario(usuario, password);
    }
    
    public int obtenerIDPorNombreUsuario(String nombreUsuario) throws Exception{
        if (nombreUsuario == null || nombreUsuario.trim().isEmpty()) {
            throw new Exception("El nombre de usuario no puede ser vacío");
        }
        return usuarioMySQL.obtenerIDPorNombreUsuario(nombreUsuario.trim());
    }
    
    public Persona obtenerPorCorreo(String correo) throws Exception{
        if(correo == null || correo.trim().isEmpty()){
            throw new Exception("El correo del usuario no puede ser vacío");
        }
        return usuarioMySQL.obtenerPorCorreo(correo.trim());
    }
    
    public boolean correoExiste(String correo)throws Exception{
        if(correo == null || correo.trim().isEmpty()){
            throw new Exception("El correo no puede ser vacío");
        }
        return usuarioMySQL.correoExiste(correo);
    }
    
}
