
package pe.edu.pucp.frutilla.WS;
    
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.xml.ws.WebServiceException;
import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import pe.edu.pucp.frutilla.config.DBManager;
import pe.edu.pucp.frutilla.dto.EmpleadoDTO;
import pe.edu.pucp.frutilla.logica.rrhh.EmpleadoService;
import pe.edu.pucp.frutilla.models.rrhh.Empleado;

/**
 *
 * @author NAMS_
 */
@WebService(serviceName = "EmpleadoWS")
public class EmpleadoWS {
    
    private final EmpleadoService daoEmpleado; 
    /**
     * This is a sample web service operation
     */
    
    public EmpleadoWS(){
        daoEmpleado = new EmpleadoService(); 
    }
    
   
    
    @WebMethod(operationName = "obtenerEmpleadosxLocal")
    public List<EmpleadoDTO> obtenerEmpleadosxLocal(@WebParam (name ="idLocal") int idLocal) {
        try{
            List<Empleado> lista = daoEmpleado.listarTodosPorLocal(idLocal);
            List<EmpleadoDTO> listaDTO = new ArrayList<>();
            for(int i=0;i<lista.size();i++){
                EmpleadoDTO temp = new EmpleadoDTO(lista.get(i));
                listaDTO.add(temp);
            }
            
            return listaDTO; 
        }catch(Exception ex){ 
            System.out.println(ex.getMessage()); 
        }
        return null;
    }
    

    @WebMethod(operationName = "actualizarEmpleado")
    public boolean actualizarEmpleado(@WebParam (name ="empleado") EmpleadoDTO empleado) {
        
        try {
            Empleado hiddenValues = empleado.convertirAEmpleado();
            try{
                daoEmpleado.actualizar(hiddenValues);
                return true; 
            }catch(Exception ex){ 
                System.out.println(ex.getMessage()); 
                return false; 
            }
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoWS.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        return false; 
    }

    
    @WebMethod(operationName = "eliminarEmpleado")
    public boolean eliminarEmpleado(@WebParam (name ="idEmpleado") int idEmpleado) {
        try{
            daoEmpleado.eliminar(idEmpleado);
            return true; 
        }catch(Exception ex){ 
            System.out.println(ex.getMessage()); 
            return false; 
        }
    }
    
    @WebMethod(operationName = "obtenerEmpleadoPorId")
    public EmpleadoDTO obtenerEmpleadoPorId(@WebParam (name ="idEmpleado") int idEmpleado) {
        
        try{
            Empleado temp = daoEmpleado.obtener(idEmpleado);
            EmpleadoDTO empleadoFinal = new  EmpleadoDTO(temp);
            return empleadoFinal; 
        }catch(Exception ex){ 
            System.out.println(ex.getMessage()); 
        }
        return null; 
    }
    
    @WebMethod(operationName = "agregarEmpleado")
    public boolean agregarEmpleado(@WebParam (name ="empleado") EmpleadoDTO empleado) {
       
            Empleado temp = empleado.convertirAEmpleado();
            
        
        try{
            daoEmpleado.agregar(temp);
            
            return true;
        }catch(Exception ex){ 
            System.out.println(ex.getMessage()); 
            return false; 
        }
    }
    
    private InputStream getFileResourceAsStream(String fileName) {
    InputStream stream = getClass().getClassLoader().getResourceAsStream(fileName);
    if (stream == null) {
        throw new IllegalArgumentException("No se encontró el recurso: " + fileName);
    }
    return stream;
    }

    @WebMethod(operationName = "VentasXLocal")
    public byte[] reporteVentasXLocal(@WebParam(name = "idLocal") int idLocal) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("LocalBuscar", idLocal);
            // Si tienes logo o subreporte, también puedes usar:
            // params.put("logo", getFileResource("frutilla_logo.png"));
            
            return generarBuffer("VentasXLocal.jasper", params);
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoWS.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebServiceException("Error al generar el reporte: " + ex.getMessage());
        }
    }
    @WebMethod(operationName = "VentasXEmpleado")
    public byte[] reporteVentasXEmpleado(@WebParam(name = "idEmpleado") int idEmpleado) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("NumEmpleado", idEmpleado);
            // Si usas imágenes o subreportes, también puedes agregar:
            // params.put("logo", getFileResource("frutilla_logo.png"));

            return generarBuffer("VentasXEmpleado.jasper", params);
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoWS.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebServiceException("Error al generar el reporte de ventas por empleado: " + ex.getMessage());
        }
    }
    private String compileJrxmlToJasper(String jrxmlPath) throws Exception {
        String jasperPath = jrxmlPath.replace(".jrxml", ".jasper");
        File jasperFile = new File(jasperPath);

        if (!jasperFile.exists()) {
            JasperCompileManager.compileReportToFile(jrxmlPath, jasperPath);
        }
        return jasperPath;
    }

    public byte[] generarBuffer(String jasperFileName, Map<String, Object> params) throws Exception {
        JasperReport reporte;
        try (InputStream jasperStream = getFileResourceAsStream(jasperFileName)) {
            reporte = (JasperReport) JRLoader.loadObject(jasperStream);
        }

        try (Connection conn = DBManager.getInstance().getConnection()) {
            JasperPrint print = JasperFillManager.fillReport(reporte, params, conn);
            return JasperExportManager.exportReportToPdf(print);
        }
    }
}
