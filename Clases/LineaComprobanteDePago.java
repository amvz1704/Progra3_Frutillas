public class LineaComprobanteDePago{
    private int idLineaComprobante;
    private int cantidad;
    private double subtotal;

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