/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Test;

import Dao.ProductoDaoImpl;
import Interface.IProducto;
import Model.Productos;
import java.util.List;

/**
 *
 * @author James Rios
 */
public class Test_Producto {

    public static IProducto dao = new ProductoDaoImpl();

    public static void main(String[] args) {
        Test_Producto tP = new Test_Producto();
        tP.listar();
        //tP.agregar();
    }
    // FUNCIONALIDAD - Listar.Productos
    public static void listar() {
        List<Productos> Lista = dao.lista();

        if (Lista != null && !Lista.isEmpty()) {
            System.out.println("ID\tNombre\tPrecio\tStock\tImagen");
            for (Productos ps : Lista) {
                System.out.println(ps.getId_producto()
                        + "\t" + ps.getNombre() + "\t$"
                        + ps.getPrecio() + "\t" + ps.getStock()+ ps.getImagen());

            }
        } else {
            System.out.println(" NO HAY PRODUCTOS ");
        }

    }
    // FUNCIONALIDAD - AGREGAR.Productos
    public static void agregar() {
        Productos p = new Productos();
        p.setNombre("Xiaomi 15T Pro-Black");
        p.setDescripción("Alto rendimiento + RENDIMIENTO CON 256GB");
        p.setPrecio(2500.20);
        p.setStock(15);
        p.setImagen("/resources/img/celular.jpg");

        boolean result = dao.insert(p);
        if (result) {
            System.out.println(" PRODUCTO INSERTADO");
        } else {
            System.out.println(" |ERROR| No sé logró registrar");

        }
    }
}
