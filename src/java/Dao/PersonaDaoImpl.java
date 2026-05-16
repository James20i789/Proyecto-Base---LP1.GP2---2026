package Dao;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import Interface.IPersona;
import Model.Persona;
import Model.Rol;
import Model.Usuario;
import java.util.List;
import java.sql.*;
import Util.ConexionSingleton;

/**
 *
 * @author James Rios
 */
public class PersonaDaoImpl implements IPersona {

    private Connection cn;

    // LISTAR DATOS - PERSONA
    @Override
    public List<Persona> lista() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // INSERTAR NUEVOS DATOS
    @Override
    public int insert(Persona p, Usuario u) {
        PreparedStatement st;
        String query = null;
        ResultSet rs;
        int id_persona = 0;
        int r = 0;
        try {
            query = "INSERT INTO persona(nombre,email,direccion,telefono)"
                    + " VALUES (?, ?, ?, ?)";
            cn = ConexionSingleton.getConnection();
            st = cn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, p.getNombre());
            st.setString(2, p.getEmail());
            st.setString(3, p.getDirección());
            st.setString(4, p.getTelefono());
            r = st.executeUpdate();

            if (r != 0) {
                rs = st.getGeneratedKeys();
                if (rs.next()) {
                    //linea que devuelve el id de la persona creada
                    id_persona = rs.getInt(1);
                    System.out.println("id_recuperado: " + id_persona);
                }
                if (id_persona > 0) {
                    u.setRol(Rol.CLIENTE);
                    String hashedPassword = u.HashClave(u.getClave());
                    query = "INSERT INTO usuarios(usuario,password,rol,id_persona)"
                            + " VALUES (?,?,?,?)";
                    st = cn.prepareStatement(query);
                    st.setString(1, p.getEmail());
                    st.setString(2, hashedPassword);
                    st.setString(3, u.getRol().name());
                    st.setInt(4, id_persona);
                    r = st.executeUpdate();
                } else {
                    System.out.println(" |ERROR| No se logró agregar a la persona");
                }
            }

        } catch (Exception e) {
            System.out.println(" ERROR AL AGREGAR" + e.getMessage());
            try {
                cn.rollback();
            } catch (Exception ex) {
                System.out.println(" ERROR DEL ROLLBACK" + e.getMessage());

            }

        } finally {
            if (cn != null) {
                try {
                } catch (Exception ex) {
                }

            }
        }
        return r;

    }
    // UPDATE LOS DATOS - PERSONA
    @Override
    public boolean update(Persona p) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    // BUSCADOR POR ID
    @Override
    public Persona SearchByID(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    // ELIMINAR DATOS
    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
