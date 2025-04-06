import java.util.ArrayList;

public class Proveedor {
    
    //ATRIBUTOS
    
    private int idProveedor;
    private String RUC;
    private String razonSocial;
    private int telefono;
    private String correoElectronico;
    private String nombreContacto;
    private ArrayList<String> cuentaBancaria;

    // CONSTRUCTORES
    
    public Proveedor(int idProveedor, String RUC, String razonSocial, int telefono, String correoElectronico, String nombreContacto, ArrayList<String> cuentaBancaria) {
        this.idProveedor = idProveedor;
        this.RUC = RUC;
        this.razonSocial = razonSocial;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.nombreContacto = nombreContacto;
        this.cuentaBancaria = cuentaBancaria;
    }
    
    public Proveedor(){
        this.idProveedor = 0;
        this.telefono = 0;
        this.cuentaBancaria = new ArrayList<String>();
    }
    
    //GETTERS AND SETTERS
    
    public int getIdProveedor() {
        return idProveedor;
    }

    
    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

   
    public String getRUC() {
        return RUC;
    }

  
    public void setRUC(String RUC) {
        this.RUC = RUC;
    }

    
    public String getRazonSocial() {
        return razonSocial;
    }

    
    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    
    public int getTelefono() {
        return telefono;
    }

    
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    
    public String getNombreContacto() {
        return nombreContacto;
    }

    
    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }
    
    
    //METODOS
    
    public void crearProveedor(){
        
    }
    public void cambiarInformacion(String nuevaRazonSocial, 
            String nuevoCorreoElectronico, int nuevoTelefono){
        this.razonSocial = nuevaRazonSocial;
        this.correoElectronico = nuevoCorreoElectronico;
        this.telefono = nuevoTelefono;
        //System.out.println("Información del proveedor actualizada.");
        
    }
    
    @Override
    public String toString() {
        return "Proveedor{" +
                "idProveedor=" + idProveedor +
                ", RUC='" + RUC + '\'' +
                ", razonSocial='" + razonSocial + '\'' +
                ", telefono=" + telefono +
                ", correoElectronico='" + correoElectronico + '\'' +
                ", nombreContacto='" + nombreContacto + '\'' +
                ", cuentaBancaria=" + cuentaBancaria +
                '}';
    }
}
