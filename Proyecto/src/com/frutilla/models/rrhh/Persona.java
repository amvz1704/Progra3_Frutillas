
public abstract class Persona {
    private int idPersona;
    private static int correlativo=1;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String correoElectronico;
    private String telefono;
    private boolean activo;
    //son el usuario y contraseña;
    private String usuarioSistema;
    private String contraSistema;

    public Persona() {
    }

    public Persona(int idPersona, String nombre, String apellidoPaterno, String apellidoMaterno, String correoElectronico, String telefono, boolean activo, String usuarioSistema, String contraSistema) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
        this.activo = activo;
        this.usuarioSistema = usuarioSistema;
        this.contraSistema = contraSistema;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public static int getCorrelativo() {
        return correlativo;
    }

    public static void setCorrelativo(int correlativo) {
        Persona.correlativo = correlativo;
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
    
	public void crearUsuario(String usuario, String contrasena) {
		this.usuarioSistema = usuario;
		this.contraSistema = contrasena;
		System.out.println("Usuario creado exitosamente");
	}	
    
	public void desactivarUsuario() {
		this.activo = false;
		System.out.println("El usuario " + usuarioSistema + " ha sido desactivado.");
	}
	
	public boolean ingresoSistema(String usuario, String contra) {
		if (this.activo && this.usuarioSistema.equals(usuario) && this.contraSistema.equals(contra)) {
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
	
}
