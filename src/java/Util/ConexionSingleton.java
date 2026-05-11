/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

/**
 *
 * @author James Rios
 */
import java.sql.*;
public class ConexionSingleton {
    //creado una variable estatica 
    public static Connection connection;

    //metodo getConnection
    public static Connection getConnection() {
        try {
            if (connection == null) {
                Runtime.getRuntime().addShutdownHook(new getClose());
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost/bd_ecommerce", "root", "james");
                System.out.println(" ENTRO A LA BASE DE DATOS ");
            }
            return connection;

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("CONEXIÓN FALLIDA: ", e);

        }
    }

    static class getClose extends Thread {

        @Override
        public void run() {
            try {
                Connection conn = ConexionSingleton.getConnection();
                conn.close();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

        }

    }
}