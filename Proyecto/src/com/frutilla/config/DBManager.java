package com.frutilla.config;

import java.sql.DriverManager;
import java.sql.Connection;
import java.util.Properties;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;

public class DBManager {
    private static String url;
    private static String user;
    private static String password;

    static{
        String pathFile = "/com/frutilla/config/config.properties";
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

            //Cargar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(Exception e){
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    //Metodo estatico para obtener la conexion a la base de datos
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url, user, password);
    }
}
