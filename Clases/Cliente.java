public class Cliente {
    private static int correlativo = 1; // Correlativo para asignar ID a los clientes
    private int idCliente;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String telefono;
    private String correoElectronico;
    private boolean activo; // true: activo, false: inactivo

    public Cliente(String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, String correoElectronico) {
        this.idCliente = correlativo;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.activo = true; // Por defecto, el cliente es activo al crearse
        correlativo++;
    }
    
    public void desactivarCliente(){
        setActivo(false);
    }

    public void realizarCompra() {
        System.out.println("Realizando compra...");
    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getApellidoPaterno(){
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno){
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno(){
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno){
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getTelefono(){
        return telefono;
    }

    public void setTelefono(String telefono){
        this.telefono = telefono;
    }

    public String getCorreoElectronico(){
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico){
        this.correoElectronico = correoElectronico;
    }

    public boolean getActivo(){
        return activo;
    }

    public void setActivo(boolean activo){
        this.activo = activo;
    }
