package pe.edu.pucp.frutilla.models.venta;

import pe.edu.pucp.frutilla.models.inventario.Producto;

public class LineaOrdenDeVenta {
    
    //ATRIBUTOS
    private int idLineaVenta;
    private int cantidad;
    private double subtotal;
    private Producto producto;

    // CONSTRUCTORES
    
    
    public LineaOrdenDeVenta(int cantidad, Producto producto) {
        this.cantidad = cantidad;
        this.producto = new Producto(producto);//crea un nuevo producto no el mismo! 
        actualizarSubtotal(); 
    } 
    
    public LineaOrdenDeVenta(){
        this.idLineaVenta = 0;
        this.cantidad = 0;
        this.subtotal = 0;
        this.producto = new Producto();
    }

    //agregue constructor copia 
	public LineaOrdenDeVenta(LineaOrdenDeVenta original) {
        this.idLineaVenta = original.idLineaVenta;
        this.cantidad = original.cantidad;
        this.subtotal = original.subtotal;
        this.producto = new Producto(original.producto);
    } 
    
    //GETTERS AND SETTERS
    
    public int getIdLineaVenta() {
        return idLineaVenta;
    }    
    public void setIdLineaVenta(int idLineaVenta) {
        this.idLineaVenta = idLineaVenta;
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        actualizarSubtotal();
    }
    public double getSubtotal() {
        return subtotal;
    }
    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
    // METODOS
    
    public void actualizarSubtotal() {
        this.subtotal = this.cantidad * producto.getPrecioUnitario();
    }

    public Producto getProducto() {
        return producto;
    }
    
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {
        return "LineaDeOrdenVenta{" +
                "idLineaVenta=" + idLineaVenta +
                ", cantidad=" + cantidad +
                ", subtotal=" + subtotal +
                '}';
    }
}

