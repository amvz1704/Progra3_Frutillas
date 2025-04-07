public class LineaComprobanteDePago{
    private int idLineaComprobante;
    private int cantidad;
    private double subtotal;
    private Producto producto;

    //Falta Constructores con Parametros;


    public LineaComprobanteDePago(){
        this.idLineaComprobante = idLineaComprobante;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.producto = new Producto();
        //Agregar un set Producto?
    }

    public LineaComprobanteDePago(int idLineaVenta, int cantidad, double subtotal, Producto producto) {
        this.idLineaComprobante = idLineaComprobante;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.producto = new Producto(producto);
    } 

    public LineaComprobanteDePago(LineaComprobanteDePago original){
        this.idLineaComprobante = original.idLineaComprobante;
        this.cantidad = original.cantidad;
        this.subtotal = original.subtotal;
        this.producto = original.producto;
    }

    public void calcularSubtotal(){
        //Wait
    }

    public int getIdLineaComprobante() {
        return idLineaComprobante;
    }

    public void setIdLineaComprobante(int idLineaComprobante) {
        this.idLineaComprobante = idLineaComprobante;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }
}