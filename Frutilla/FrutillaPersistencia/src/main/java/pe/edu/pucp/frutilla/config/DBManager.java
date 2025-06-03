package pe.edu.pucp.frutilla.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.io.IOException;

import java.sql.DriverManager;
import java.sql.Connection;
import java.util.Properties;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;


public class DBManager {
// 1) Única instancia
    private static volatile DBManager INSTANCE;
    
      // 2) Pool de conexiones
    private HikariDataSource dataSource;
     // 3) Parámetros de conexión
    private final Properties props = new Properties();
    
    private String dbType;  // p. ej. "mysql"
    
    
    // Constructor privado para evitar instanciación externa
     // 4) Constructor privado
    private DBManager() {
        loadProperties();
        configureDataSource();
    }
    
     // 5) Punto de acceso al singleton (double-checked locking)
    public static DBManager getInstance() {
        if (INSTANCE == null) {
            synchronized (DBManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DBManager();
                }
            }
        }
          return INSTANCE;
    }
    
 // 6) Carga de db.properties
    private void loadProperties() {
        String file = "db.properties";
        try ( InputStream in = Thread.currentThread()
                                      .getContextClassLoader()
                                      .getResourceAsStream(file) ) {
            if (in == null) {
                throw new RuntimeException("No se encuentra el archivo: " + file);
            }
            props.load(in);
            dbType = props.getProperty("db.type");
             if (dbType == null || dbType.isBlank()) {
                throw new RuntimeException("Falta la clave 'db.type' en db.properties");
            }
              } catch (IOException e) {
                  throw new RuntimeException("Error leyendo " + file, e);
        }
         }
    
// 7) Configuración de HikariCP
    private void configureDataSource() {
        
         try {
            // 2.1) Registrar el driver (por si acaso)
            String driverKey = props.getProperty("db.driver");
            if (driverKey == null) {
                throw new RuntimeException("db.driver no está definido en db.properties");
            }
            Class.forName(driverKey);

            // 2.2) Ahora construyo el HikariConfig con las claves correctas:
            HikariConfig cfg = new HikariConfig();

            // NOTA: si dbType="mysql", las claves en el properties son:
            //    mysql.jdbcUrl, mysql.username, mysql.password
            String prefix = dbType + ".";  // -> "mysql."

            String jdbcUrl   = props.getProperty(prefix + "jdbcUrl");
            String username  = props.getProperty(prefix + "username");
            String password  = props.getProperty(prefix + "password");

            if (jdbcUrl == null || username == null || password == null) {
                throw new RuntimeException(
                    "Faltan claves obligatorias para el pool: "
                    + prefix + "jdbcUrl, " 
                    + prefix + "username, o " 
                    + prefix + "password"
                );
            }
            cfg.setDriverClassName(driverKey); //agrego
            cfg.setJdbcUrl(jdbcUrl);
            cfg.setUsername(username);
            cfg.setPassword(password);

            // 2.3) Ajustes opcionales de pool
            cfg.setMaximumPoolSize(10);
            cfg.setMinimumIdle(5);
            cfg.setIdleTimeout(120_000);
            cfg.setConnectionTimeout(20_000);

            // 2.4) Propiedades extra para MySQL
            if ("mysql".equalsIgnoreCase(dbType)) {
                cfg.addDataSourceProperty("cachePrepStmts",     "true");
                cfg.addDataSourceProperty("prepStmtCacheSize",   "250");
                cfg.addDataSourceProperty("prepStmtCacheSqlLimit","2048");
            }

            dataSource = new HikariDataSource(cfg);
        }
        catch (Exception ex) {
            Logger.getLogger(DBManager.class.getName())
                  .log(Level.SEVERE, "Error configurando DataSource", ex);
            throw new RuntimeException(ex);
        }
    }
    
  // 8) Método público para obtener conexión
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
    
    public void cerrarPool() {
         if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
        }

    }
    
    public void shutdown() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
        }
    }


    }