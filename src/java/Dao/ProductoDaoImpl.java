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
