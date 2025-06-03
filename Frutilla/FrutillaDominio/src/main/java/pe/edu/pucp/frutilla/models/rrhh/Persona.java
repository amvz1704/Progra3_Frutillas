package pe.edu.pucp.frutilla.models.rrhh;

public class Persona {
    private int idUsuario;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String correoElectronico;
    private String telefono;
    //son el usuario y contraseña;
    private String usuarioSistema;
    private String contraSistema;
    private boolean activo; // true: activo, false: inactivo
    private String tipoUsuario;

    public Persona() {
    }

    public Persona(Persona persona) {
        this.nombre = persona.nombre;
        this.apellidoPaterno = persona.apellidoPaterno;
        this.apellidoMaterno = persona.apellidoMaterno;
        this.correoElectronico = persona.correoElectronico;
        this.telefono = persona.telefono;
        this.usuarioSistema = persona.usuarioSistema;
        this.contraSistema = persona.contraSistema;
        this.activo = persona.activo; // Copia el estado activo
        this.idUsuario = persona.idUsuario;
        this.tipoUsuario = persona.tipoUsuario;
    }

    public Persona(String nombre, String apellidoPaterno, String apellidoMaterno, String correoElectronico, String telefono, String usuarioSistema, String contraSistema, int idUsuario, String tipoUsuario) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
        this.usuarioSistema = usuarioSistema;
        this.contraSistema = contraSistema;
        this.activo = true; // Por defecto, la persona está activa al crearla
        this.idUsuario = idUsuario;
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public String toString() {
        return "Id: " + idUsuario + ", Nombre: " + nombre + " " + apellidoPaterno + " " + apellidoMaterno + ", Correo: " + correoElectronico + ", Telefono: " + telefono + ", Activo: " + activo;
    }
    
    public void crearUsuario(String usuario, String contrasena) {
	this.usuarioSistema = usuario;
	this.contraSistema = contrasena;
	System.out.println("Usuario creado exitosamente");
    }	
	
    public boolean ingresoSistema(String usuario, String contra) {
	if (this.usuarioSistema.equals(usuario) && this.contraSistema.equals(contra)) {
            System.out.println("Bienvenido al sistema, " + nombre + "!");
            return true;
	} else {
            System.out.println("Acceso denegado para " + usuario);
            return false;
        }
    }

    public void salirSistema() {
	System.out.println("El usuario " + usuarioSistema + " ha cerrado sesión.");
    }
    
    public String getTipoUsuario(){
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario){
        this.tipoUsuario = tipoUsuario;
    }
    
    public int getIdUsuario(){
        return idUsuario;
    }
    
    public void setIdUsuario(int idUsuario){
        this.idUsuario = idUsuario;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getUsuarioSistema() {
        return usuarioSistema;
    }

    public void setUsuarioSistema(String usuarioSistema) {
        this.usuarioSistema = usuarioSistema;
    }

    public String getContraSistema() {
        return contraSistema;
    }

    public void setContraSistema(String contraSistema) {
        this.contraSistema = contraSistema;
    }
    
    public boolean getActivo(){
        return activo;
    }

    public void setActivo(boolean activo){
        this.activo = activo;
    }
	
}
