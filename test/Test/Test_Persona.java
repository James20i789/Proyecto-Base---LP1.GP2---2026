/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Test;

import Dao.PersonaDaoImpl;
import Dao.UsuarioDaoImpl;
import Interface.IPersona;
import Interface.IUsuario;
import Model.Persona;
import Model.Rol;
import Model.Usuario;

/**
 *
 * @author James Rios
 */
public class Test_Persona {

    IPersona dao = new PersonaDaoImpl();
    IUsuario Udao = new UsuarioDaoImpl();

    public static void main(String[] args) {
        Test_Persona tp = new Test_Persona();
        //tp.insert();
        tp.validarUsuario();
    }

    public void insert() {
        Persona p = new Persona();
        p.setNombre("James");
        p.setEmail("james.rios@gmail.com");
        p.setTelefono("+51 973 563 240");
        p.setDirección("FONAVI");

        Usuario u = new Usuario();
        u.setClave("61126141");
        u.setRol(Rol.CLIENTE);
        int result = dao.insert(p, u);
        if (result > 0) {
            System.out.println("PERSONA + USUARIO  ||   CREADOS");
            System.out.println("Usuario: " + p.getEmail());
            System.out.println("Rol asignado: " + u.getRol());
        } else {
            System.out.println("|ERROR| No se logró registrar");
        }
    }

    public void validarUsuario() {
        Usuario u = Udao.validate("james.rios@gmail.com", "61126141");
        if (u != null && u.getPersona() != null) {
            System.out.println("BIENVENIDO: " + u.getPersona().getNombre());
            System.out.println("ROL: " + u.getRol());
            System.out.println("USUARIO: " + u.getUsuario());
            System.out.println("UserID: " + u.getId_usuario());
            System.out.println("PersonaID: " + u.getPersona().getId_persona());
        } else {
            System.out.println("CREDENCIALES INCORRECTAS");
        }
    }

}
