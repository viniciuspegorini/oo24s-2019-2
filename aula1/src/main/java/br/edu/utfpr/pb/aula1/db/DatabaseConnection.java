/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.pb.aula1.db;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Vinicius
 */
public class DatabaseConnection {
    private static DatabaseConnection dbConnection;
    private Connection conn;

    private DatabaseConnection(){
        try {
            String url = "jdbc:postgresql://127.0.0.1:5432/oo24s-aula1";
            String urlMysql = "jdbc:mysql://127.0.0.1:3306/aula1-oo";
            String user = "postgres";
            String password = "root";
            conn = DriverManager.getConnection(url,
                    user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static DatabaseConnection getInstance(){
        if (dbConnection == null){
            dbConnection = new DatabaseConnection();
        }
        return dbConnection;
    }
    
    public Connection getConnection(){
        return conn;
    }
}
