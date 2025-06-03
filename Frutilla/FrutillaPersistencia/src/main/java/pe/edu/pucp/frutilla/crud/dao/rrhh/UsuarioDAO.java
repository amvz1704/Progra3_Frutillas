package pe.edu.pucp.frutilla.crud.dao.rrhh;

import pe.edu.pucp.frutilla.crud.dao.BaseDAO;
import pe.edu.pucp.frutilla.models.rrhh.Persona;

public interface UsuarioDAO extends BaseDAO<Persona>{
    public Persona validarUsuario(String usuarioSistema, String constrasistema) throws Exception;
    public int obtenerIDPorNombreUsuario(String nombreUsuario) throws Exception;
}