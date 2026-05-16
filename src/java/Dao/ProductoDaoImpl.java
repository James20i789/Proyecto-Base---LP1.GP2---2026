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
    // LISTAR PRODUCTOS
    public List<Productos> lista() {
        List<Productos> Lista = null;
        Productos pr;
        PreparedStatement st;
        ResultSet rs;
        String query = null;

        try {
            query = " SELECT id_producto,nombre,descripcion,"
                    + " precio,stock FROM productos ";

            Lista = new ArrayList<>();

            cn = ConexionSingleton.getConnection();
            st = cn.prepareStatement(query);
            rs = st.executeQuery();
            while (rs.next()) {
                pr = new Productos();
                pr.setId_producto(rs.getInt("id_producto"));
                pr.setNombre(rs.getString("nombre"));
                pr.setDescripción(rs.getString("descripcion"));
                pr.setPrecio(rs.getDouble("precio"));
                pr.setStock(rs.getInt("stock"));
                Lista.add(pr);
            }

        } catch (Exception e) {
            System.out.println(" ERROR EN EL LISTADO:" + e.getMessage());
            try {
                cn.rollback();
            } catch (Exception ex) {
            }
            System.out.println("No se pudo listar el producto");
        } finally {
            if (cn != null) {
                try {
                } catch (Exception ex) {
                }

            }
        }
        return Lista;
    }

    @Override
    // INSERTAR PRODUCTOS
    public boolean insert(Productos p) {
        boolean agrg = true;
        PreparedStatement st;
        String query = null;
        
        try {
            query = "INSERT INTO productos(nombre,descripcion,precio, stock)"
                    + " VALUES(?,?,?,?)";
            cn = ConexionSingleton.getConnection();
            st = cn.prepareStatement(query);
            st.setString(1, p.getNombre());
            st.setString(2, p.getDescripción());
            st.setDouble(3, p.getPrecio());
            st.setInt(4, p.getStock());
            
            st.executeUpdate();
            
            
        } catch (Exception e) {
            System.out.println(" |ERROR| Al agregar el producto"+e.getMessage());
            try {
                cn.rollback();
            } catch (Exception ex) {
            }
            System.out.println(" |ERROR| No sé logró agregar al registro de productos"+e.getMessage());
        } finally {
            if (cn != null) {
                try {
                    
                    cn.close();
                } catch (Exception e) {
                    System.out.println(" |ERROR AL CERRAR LA SESIÓN| ");
                }
            }
        }
        return agrg;
    }
    // UPDATE PRODUCTOS
    @Override
    public boolean update(Productos p) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    // BUSCADOR POR ID
    @Override
    public Productos SearchByID(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    // ELIMINAR
    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    // UPDATE STOCK
    @Override
    public boolean updateStock(int id, int stock) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
