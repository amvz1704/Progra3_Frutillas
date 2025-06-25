package pe.edu.pucp.frutilla.WS;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.xml.ws.WebServiceException;

import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import pe.edu.pucp.frutilla.config.DBManager;

@WebService(serviceName = "ReporteWS", targetNamespace = "pe.edu.pucp.frutilla")
public class ReporteWS {

    private String getFileResource(String fileName) {
        // Accede al archivo desde src/main/resources
        String filePath = getClass().getClassLoader().getResource(fileName).getPath();
        return filePath.replace("%20", " ");
    }

    @WebMethod(operationName = "VentasXLocal")
    public byte[] reporteVentasXLocal(@WebParam(name = "idLocal") int idLocal) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("idLocal", idLocal);
            // Si tienes logo o subreporte, también puedes usar:
            // params.put("logo", getFileResource("frutilla_logo.png"));
            
            return generarBuffer(getFileResource("VentasXLocal.jrxml"), params);
        } catch (Exception ex) {
            Logger.getLogger(ReporteWS.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebServiceException("Error al generar el reporte: " + ex.getMessage());
        }
    }
    @WebMethod(operationName = "VentasXEmpleado")
    public byte[] reporteVentasXEmpleado(@WebParam(name = "idEmpleado") int idEmpleado) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("idEmpleado", idEmpleado);
            // Si usas imágenes o subreportes, también puedes agregar:
            // params.put("logo", getFileResource("frutilla_logo.png"));

            return generarBuffer(getFileResource("VentasXEmpleado.jrxml"), params);
        } catch (Exception ex) {
            Logger.getLogger(ReporteWS.class.getName()).log(Level.SEVERE, null, ex);
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

    public byte[] generarBuffer(String jrxmlPath, Map<String, Object> params) throws Exception {
        String jasperPath = compileJrxmlToJasper(jrxmlPath);
        JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(jasperPath);
        Connection conn = DBManager.getInstance().getConnection();
        JasperPrint print = JasperFillManager.fillReport(reporte, params, conn);
        conn.close();
        return JasperExportManager.exportReportToPdf(print);
    }
}