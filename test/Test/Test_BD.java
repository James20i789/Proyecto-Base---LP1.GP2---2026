package Test;

import Util.ConexionSingleton;
import java.sql.*;


public class Test_BD {

    
    public static void main(String[] args) {
      Test_BD t = new Test_BD();
      t.testConexion();
    }
    
    public void testConexion(){
        ConexionSingleton conn = new ConexionSingleton();
        try {
            Connection connection = conn.getConnection();
            if (connection != null && !connection.isClosed()) {
                System.out.println("Conexion Satisfactoria!!!!");
            }else{
                System.out.println(" |ERROR| - CONEXIÓN FALLIDA");
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
            e.printStackTrace();
            
        }
    }
    
}