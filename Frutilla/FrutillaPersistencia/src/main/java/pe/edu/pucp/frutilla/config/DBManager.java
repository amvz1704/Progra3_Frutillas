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

    public synchronized static DBManager getInstance(){
        if(instance == null){
            instance = new DBManager();
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
