package pe.edu.pucp.frutilla.models.rrhh;
import java.time.LocalDate;
import java.util.Date; 

public class Supervisor extends Empleado{

    public Supervisor(){
        super();
    }

    public Supervisor(String nombre, String apellidoPaterno, String apellidoMaterno, String correoElectronico, String telefono, LocalDate fechaContrato, double salario, String usuarioSistema, String contraSistema, int idLocal, int idUsuario){
        super(nombre, apellidoPaterno, apellidoMaterno, correoElectronico, telefono, fechaContrato, salario, usuarioSistema, contraSistema, 'S', idLocal, idUsuario);
    }

    public Supervisor(Supervisor supervisor){
        super(supervisor);
    }
    
    @Override
    public String toString(){
        return super.toString();
    }

//    public void generarReporte(int idLocal, LocalDate fecha){
//        LocalMySQL localMySQL = new LocalMySQL();
//        Local local;
//        try{
//            local = localMySQL.obtenerLocalPorId(idLocal);
//        }
//        catch(Exception e){
//            System.out.println("Error al obtener el local: " + e.getMessage());
//            return;
//        }
//        local.generarReporteVentas(fecha);
//    }

}
