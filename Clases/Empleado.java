import java.time.*;

public class Empleado{
    private int idEmpleado;
    private static int correlativo = 1;
    private String nombre;
    private String apePaterno;
    private String apeMaterno;
    private String email;
    private String telefono;
    private LocalDate fecha_contrato;
    private double salario; 
    private String usuario_sistema;
    private String contra_sistem;
    // Este podriamos considerarlo un enum (manana o tarde)
    private String turnoTrabajo;

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public static int getCorrelativo() {
        return correlativo;
    }

    public static void setCorrelativo(int aCorrelativo) {
        correlativo = aCorrelativo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApePaterno() {
        return apePaterno;
    }

    public void setApePaterno(String apePaterno) {
        this.apePaterno = apePaterno;
    }

    public String getApeMaterno() {
        return apeMaterno;
    }

    public void setApeMaterno(String apeMaterno) {
        this.apeMaterno = apeMaterno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFecha_contrato() {
        return fecha_contrato;
    }

    public void setFecha_contrato(LocalDate fecha_contrato) {
        this.fecha_contrato = fecha_contrato;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getUsuario_sistema() {
        return usuario_sistema;
    }

    public void setUsuario_sistema(String usuario_sistema) {
        this.usuario_sistema = usuario_sistema;
    }

    public String getContra_sistem() {
        return contra_sistem;
    }

    public void setContra_sistem(String contra_sistem) {
        this.contra_sistem = contra_sistem;
    }

    public String getTurnoTrabajo() {
        return turnoTrabajo;
    }

    public void setTurnoTrabajo(String turnoTrabajo) {
        this.turnoTrabajo = turnoTrabajo;
    }
}