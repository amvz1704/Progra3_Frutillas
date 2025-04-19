package com.frutilla.config;

import java.sql.DriverManager;
import java.sql.Connection;

public class DBManager {
    
    private Connection con;
    private static DBManager dbManager;
    private final String url = "jdbc:mysql://<host>:3306/<db_name>";
    private final String username = "admin";
    private final String password = "labs1inf3020241";
    
    @SuppressWarnings("UseSpecificCatch")
    public Connection getConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);     
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return con;
    }
    
    public static DBManager getInstance(){
        if(dbManager == null){
            createInstance();
        }
        return dbManager;
    }
    
    private synchronized static void createInstance(){
        if(dbManager == null){
            dbManager = new DBManager();
        }
    }
}
