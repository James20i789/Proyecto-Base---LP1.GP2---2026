/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Interface.IUsuario;
import Model.Persona;
import Model.Usuario;
import java.sql.*;

/**
 *
 * @author James Rios
 */
public class UsuarioDaoImpl implements IUsuario {

    private Connection cn;

    @Override
    public Usuario validate(String usuario, String clave) {
        Usuario u = null;
        Persona p = null;

        PreparedStatement st;
        ResultSet rs;
        String query = null;

        try {
            u = new Usuario();
            p = new Persona();
            String hashedPassword = u.HashClave(clave);
            query = " select u.id_usuario, u.usuario, p.id_persona,"
                    + " p.nombre "
                    + " FROM persona p, usuarios u "
                    + " where p.id_persona = u.id_persona "
                    + " AND u.usuario = ? "
                    + " AND u.password = ?";

        } catch (Exception e) {
        } finally {
        }
        return u;
    }

}


