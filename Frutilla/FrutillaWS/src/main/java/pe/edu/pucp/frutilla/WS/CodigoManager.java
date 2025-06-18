package pe.edu.pucp.frutilla.WS;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CodigoManager {

    private static class CodigoInfo {
        int idUsuario;
        LocalDateTime expiracion;

        CodigoInfo(int idUsuario, LocalDateTime expiracion) {
            this.idUsuario = idUsuario;
            this.expiracion = expiracion;
        }
    }
    
    private static final Map<String, CodigoInfo> codigos = new HashMap<>();

    private static final int duracion = 60;

    public static String generarCodigo(int idUsuario) {
        String codigo = String.format("%06d",(int)(Math.random()*1000000));
        LocalDateTime expiracion = LocalDateTime.now().plusMinutes(duracion);
        codigos.put(codigo, new CodigoInfo(idUsuario, expiracion));
        return codigo;
    }

    public static int validarCodigo(String codigo) {
        CodigoInfo info = codigos.get(codigo);
        if (info == null) return -1;
        if (LocalDateTime.now().isAfter(info.expiracion)) {
            invalidarCodigo(codigo);
            return -1;
        }
        return info.idUsuario;
    }

    public static void invalidarCodigo(String codigo) {
        codigos.remove(codigo);
    }
}