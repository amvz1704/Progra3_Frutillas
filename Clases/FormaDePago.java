public class FormaDePago {
    private static int correlativo = 1;
    private int idFormaPago;
    private String nombre;
    private String descripcion;

    public FormaDePago(String nombre, String descripcion) {
        this.idFormaPago = correlativo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        correlativo++;
    }

    public int getIdFormaPago() {
        return idFormaPago;
    }

    public String getNombre(){
        return this.nombre;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getDescripcion(){
        return descripcion;
    }

    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }