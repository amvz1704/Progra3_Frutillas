import java.util.Date;

public class Empleado{
    private int idEmpleado;
    private static int correlativo = 1;
    private String nombre;
    private String apePaterno;
    private String apeMaterno;
    private String email;
    private int telefono;
    private Date fecha_contrato;
    private double salario; 
    private String usuario_sistema;
    private String contra_sistem;
    // Este podriamos considerarlo un enum (manana o tarde)
    private String turnoTrabajo;


}