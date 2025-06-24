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


}
