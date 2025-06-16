package pe.edu.pucp.frutilla.WS;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TokenManager {

    private static class TokenInfo {
        int idUsuario;
        LocalDateTime expiracion;

        TokenInfo(int idUsuario, LocalDateTime expiracion) {
            this.idUsuario = idUsuario;
            this.expiracion = expiracion;
        }
    }
    
    private static final Map<String, TokenInfo> tokens = new HashMap<>();

    private static final int duracion = 60;

    public static String generarToken(int idUsuario) {
        String token = UUID.randomUUID().toString();
        LocalDateTime expiracion = LocalDateTime.now().plusMinutes(duracion);
        tokens.put(token, new TokenInfo(idUsuario, expiracion));
        return token;
    }

    public static int validarToken(String token) {
        TokenInfo info = tokens.get(token);
        if (info == null) return -1;
        if (LocalDateTime.now().isAfter(info.expiracion)) {
            invalidarToken(token);
            return -1;
        }
        return info.idUsuario;
    }

    public static void invalidarToken(String token) {
        tokens.remove(token);
    }
}