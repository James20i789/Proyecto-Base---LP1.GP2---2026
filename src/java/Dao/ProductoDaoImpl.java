/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Interface.IProducto;
import Model.Productos;
import Util.ConexionSingleton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author James Rios
 */
public class ProductoDaoImpl implements IProducto {
    private Connection cn;
    
    
    @Override
    public List<Productos> lista() {
        List<Productos> Lista= null;
        Productos pr;
        PreparedStatement st;
        ResultSet rs;
        String query = null;
        
        try{
        } catch (Exception e) {
            System.out.println("ERROR AL VALIDAR LISTAR" +e.getMessage());
            try {
                query = "SELECT id_produdcto, nombre, descripción,"
                        + "precio, stock FROM productos";
                Lista = new ArrayList<>();
                if (cn==null || cn.isClosed()) {
                    System.out.println(" LA CONEXIÓN SE ENCUENTRA CERRADA");
                }
                cn = ConexionSingleton.getConnection();
                st = cn.prepareStatement(query);
                rs = st.executeQuery(); // EJECUTA LA CONSULTA
                while (rs.next()) {                    
                    pr = new Productos();
                    pr.setId_producto(rs.getInt("id_produdcto"));
                    pr.setNombre(rs.getString("nombre"));
                    pr.setDescripción(rs.getString("descripción"));
                    pr.setPrecio(rs.getDouble("precio"));
                    pr.setStock(rs.getInt("stock"));
                    Lista.add(pr);
                }
                
                
                
                cn.rollback();
            } catch (Exception ex) {
            }
            System.out.println("NO SE LOGRÓ VALIDAR EL PRODUCTO");
        } finally {
            if (cn!=null) {
                try {
                    
                } catch (Exception e) {
                }
            }
        }
        return lista();
    }

    @Override
    public boolean insert(Productos p) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(Productos p) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Productos SearchByID(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean updateStock(int id, int stock) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
