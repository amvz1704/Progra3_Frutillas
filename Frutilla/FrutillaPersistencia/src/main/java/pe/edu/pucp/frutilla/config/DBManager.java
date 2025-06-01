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
    private static DBManager instance;
    private HikariDataSource dataSource;
    
<<<<<<< HEAD
<<<<<<< Updated upstream
    // 3) Parámetros de conexión
    private final Properties props = new Properties();
    
    private String dbType;  // p. ej. "mysql"
=======
    private static String url;
    private static String user;
    private static String password;

    static{
        String pathFile = "db.properties"; //cambie esto para acceder
        try(InputStream input = DBManager.class.getClassLoader().getResourceAsStream(pathFile)){
            if(input == null){
                System.out.println("No se encuentra el archivo: " + pathFile);
            }
            Properties prop = new Properties();
            prop.load(input);
            url = prop.getProperty("db.url");
            user = prop.getProperty("db.user");
            password = prop.getProperty("db.password");
            if(url == null || user == null || password == null){
                System.out.println("Faltan propiedades en el archivo: " + pathFile);
            }
        }
        catch(Exception e){
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, e);
        }
    }
>>>>>>> parent of 8c2dd85 (cambio en masa XD)
    
    // Método para configurar el pool de conexiones
    private void configurar() {
        Properties properties = new Properties();
        String propertiesFile = "db.properties";

        try (InputStream input = getClass().getClassLoader().getResourceAsStream(propertiesFile)) {
            if (input == null) {
                throw new IOException("No se pudo encontrar el archivo de propiedades: " + propertiesFile);
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Error al cargar el archivo de propiedades", e);
        }

        HikariConfig config = new HikariConfig();
        String dbType = properties.getProperty("db.type").toLowerCase();
        config.setJdbcUrl(properties.getProperty(dbType + ".jdbcUrl"));
        config.setUsername(properties.getProperty(dbType + ".username"));
        config.setPassword(properties.getProperty(dbType + ".password"));

        // Configuración del pool
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(5);
        config.setIdleTimeout(100000); // 2 minutos
        config.setConnectionTimeout(20000); // 20 segundos

        // Configuraciones específicas según el tipo de base de datos
        if ("mysql".equals(dbType)) {
            config.addDataSourceProperty("cachePrepStmts", "true");
            config.addDataSourceProperty("prepStmtCacheSize", "250");
            config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        } else if ("postgresql".equals(dbType)) {
            // Configuraciones específicas para PostgreSQL (si es necesario)
        }
        dataSource = new HikariDataSource(config);
    }
    
    // Constructor privado para evitar instanciación externa
    private DBManager() {
        configurar();
    }
<<<<<<< HEAD
    
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
            // Registrar driver (por si acaso)
            Class.forName(props.getProperty("db.driver"));

            // Prepara HikariConfig
            HikariConfig cfg = new HikariConfig();
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
        } catch (Exception ex) {
            Logger.getLogger(DBManager.class.getName())
                  .log(Level.SEVERE, "Error configurando DataSource", ex);
            throw new RuntimeException(ex);
=======
    private static String url;
    private static String user;
    private static String password;

    
    // Constructor privado para evitar instanciación externa
    private DBManager() {
        String pathFile = "db.properties"; //cambie esto para acceder
        
        try(InputStream input = DBManager.class.getClassLoader().getResourceAsStream(pathFile)){
            if(input == null){
                System.out.println("No se encuentra el archivo: " + pathFile);
            }
            Properties prop = new Properties();
            prop.load(input);
            url = prop.getProperty("db.url");
            user = prop.getProperty("db.user");
            password = prop.getProperty("db.password");
            
        }
        catch(Exception e){
            
            url = "aaa";
            user = "aaa";
            password = "aaa";
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, e);
        }
    }
=======
>>>>>>> parent of 8c2dd85 (cambio en masa XD)

    public synchronized static DBManager getInstance(){
        if(instance == null){
            instance = new DBManager();
<<<<<<< HEAD
>>>>>>> Stashed changes
=======
>>>>>>> parent of 8c2dd85 (cambio en masa XD)
        }
        return instance;
    }

    //Metodo estatico para obtener la conexion a la base de datos
    public Connection getConnection() throws SQLException{
        return dataSource.getConnection();
    }
    
    public void cerrarPool() {
        if (dataSource != null) {
            dataSource.close();
        }
    }
}
