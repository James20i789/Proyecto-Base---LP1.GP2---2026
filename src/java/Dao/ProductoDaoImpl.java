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
        PreparedStatement st; // ENVÍO DE PARÁMETRO - LISTAR PRODUCTOS
        ResultSet rs;
        String query = null;

        try {
            query = " SELECT id_producto,nombre,descripcion,"
                    + "precio,imagen stock FROM productos ";

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
                pr.setImagen(rs.getString("Image"));
                Lista.add(pr);
            }

        } catch (Exception e) {
            System.out.println(" ERROR EN EL LISTADO:" + e.getMessage());
            try {
                cn.rollback();
            } catch (Exception ex) {
            }
            System.out.println(" |ERROR| No sé logró listar el producto");
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
        boolean flag = false;
        PreparedStatement st; // ENVÍO DE PARÁMETRO - INSERTAR PRODUCTOS
        String query = null;

        try {
            query = "INSERT INTO productos(nombre,descripcion,precio, stock, imagen)"
                    + " VALUES(?,?,?,?,?)";
            cn = ConexionSingleton.getConnection();
            st = cn.prepareStatement(query);
            st.setString(1, p.getNombre());
            st.setString(2, p.getDescripción());
            st.setDouble(3, p.getPrecio());
            st.setInt(4, p.getStock());
            st.setString(5, p.getImagen());
            st.executeUpdate();
            flag = true;
            

        } catch (Exception e) {
            System.out.println(" |ERROR| Al insertar el producto" + e.getMessage());
            try {
                cn.rollback();
            } catch (Exception ex) {
            }
            flag = false;
            System.out.println(" |ERROR| No sé logró insertar al registro de productos" + e.getMessage());
        } finally {
            if (cn != null) {
                try {
                } catch (Exception ex) {
                }
            }
        }
        return flag;
    }

    @Override
    // UPDATE PRODUCTOS
    public boolean update(Productos p) {
        boolean flag = false;
        PreparedStatement st; // ENVÍO DE PARÁMETRO - UPDATE PRODUCTOS
        String query = null;

        try {
            query = " UPDATE productos SET nombre=?,"
                    + "descripcion=?, precio=?, stock=?,imagen=?"
                    + "WHERE id_producto=?";
            cn = ConexionSingleton.getConnection();
            st = cn.prepareStatement(query);
            st.setString(1, p.getNombre());
            st.setString(2, p.getDescripción());
            st.setDouble(3, p.getPrecio());
            st.setInt(4, p.getStock());
            st.setString(5, p.getImagen());
            st.setInt(6, p.getId_producto());
            st.executeUpdate();
            flag = true;
            

        } catch (Exception e) {
            System.out.println(" |ERROR| Al actualizar el producto" + e.getMessage());
            try {
                cn.rollback();
            } catch (Exception ex) {
            }
            flag = false;
            System.out.println(" |ERROR| No sé logró actualizar al registro de productos" + e.getMessage());
        } finally {
            if (cn != null) {
                try {

                } catch (Exception e) {
                }
            }
        }
        return flag;

    }

    // BUSCADOR POR ID
    @Override
    public Productos SearchByID(int id) {
        Productos pr = null;
        PreparedStatement st; // ENVÍO DE PARÁMETRO - BUSCAR POR ID PRODUCTO
        ResultSet rs;
        String query = null;

        try {
            query = "SELECT * FROM productos WHERE id_producto=?";
            cn = ConexionSingleton.getConnection();
            st = cn.prepareStatement(query);

            st.setInt(1, id);
            rs = st.executeQuery();
            while (rs.next()) {
                pr = new Productos();
                pr.setId_producto(rs.getInt("id_producto"));
                pr.setNombre(rs.getString("nombre"));
                pr.setDescripción(rs.getString("descripcion"));
                pr.setPrecio(rs.getDouble("precio"));
                pr.setStock(rs.getInt("stock"));
                pr.setImagen(rs.getString("imagen"));

            }

        } catch (Exception e) {

            System.out.println("|ERROR| Al buscar el producto por ID" + e.getMessage());

            try {
                cn.rollback();
            } catch (Exception ex) {
            }
            System.out.println(" |ERROR| No sé logró buscar por el ID");
        } finally {

            if (cn != null) {
                try {
                } catch (Exception ex) {
                }
            }
        }

        return pr;
    }

    // ELIMINAR
    @Override
    public boolean delete(int id) {
        boolean flag = false;
        PreparedStatement st; // ENVÍO DE PARÁMETRO - ELIMINAR PRODUCTOS
        String query = null;

        try {
            query = "DELETE FROM productos WHERE id_producto=?";
            cn = ConexionSingleton.getConnection();
            st = cn.prepareStatement(query);
            st.setInt(1, id);
            st.executeUpdate();
            flag = true;

        } catch (Exception e) {

            System.out.println(" |ERROR| al eliminar producto " + e.getMessage());

            try {
                cn.rollback();
            } catch (Exception ex) {
            }
            flag = false;
            System.out.println(" |ERROR| No sé logró eliminar el producto");
        } finally {

            if (cn != null) {
                try {
                } catch (Exception ex) {
                }
            }
        }

        return flag;
    }

    // UPDATE STOCK
    @Override
    public boolean updateStock(int id, int stock) {
        boolean flag = false;
        PreparedStatement st; // ENVÍO DE PARÁMETRO - UPDATE STOCK
        String query = null;

        try {
            query = " UPDATE productos SET stock=? WHERE id_producto=?";
            cn = ConexionSingleton.getConnection();
            st = cn.prepareStatement(query);
            st.setInt(1, stock);
            st.setInt(2, id);
            st.executeUpdate();
            flag = true;

        } catch (Exception e) {
            System.out.println(" |ERROR| Al actualizar al stock de productos" + e.getMessage());
            try {
                cn.rollback();
            } catch (Exception ex) {
            }
            flag = false;
            System.out.println(" |ERROR| No sé logró actualizar al stock de productos" + e.getMessage());
        } finally {
            if (cn != null) {
                try {
                } catch (Exception ex) {
                }
            }
        }
        return flag;

    }
}
