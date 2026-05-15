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
        //tP.listar();
        tP.agregar();
    }

    public static void listar() {
        List<Productos> Lista = dao.lista();

        if (Lista != null && !Lista.isEmpty()) {
            System.out.println("ID\tNombre\tPrecio\tStock");
            for (Productos ps : Lista) {
                System.out.println(ps.getId_producto()
                        + "\t" + ps.getNombre() + "\t$"
                        + ps.getPrecio() + "\t" + ps.getStock());

            }
        } else {
            System.out.println(" NO HAY PRODUCTOS ");
        }

    }

    public static void agregar() {
        Productos p = new Productos();
        p.setNombre("Vino Unión");
        p.setDescripción("Bebida");
        p.setPrecio(8.00);
        p.setStock(80);

        boolean result = dao.insert(p);
        if (result) {
            System.out.println(" REGISTRO SATISFACTORIO");
        } else {
            System.out.println(" |ERROR| No sé logró registrar");

        }
    }
}
