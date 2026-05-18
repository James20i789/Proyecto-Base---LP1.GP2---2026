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
        tP.agregar();
        tP.actualizar();
        tP.eliminar();
    }

    // FUNCIONALIDAD - Listar.Productos
    public static void listar() {
        List<Productos> Lista = dao.lista();

        if (Lista != null && !Lista.isEmpty()) {
            System.out.println("ID\tNombre\tPrecio\tStock\tImagen");
            for (Productos ps : Lista) {
                System.out.println(ps.getId_producto()
                        + "\t" + ps.getNombre() + "\t$"
                        + ps.getPrecio() + "\t" + ps.getStock() + ps.getImagen());

            }
        } else {
            System.out.println(" NO HAY PRODUCTOS ");
        }

    }

    // FUNCIONALIDAD - AGREGAR.Productos
    public static void agregar() {
        Productos p = new Productos();
        p.setNombre("Oppo Reno Pro 11");
        p.setDescripción("Alto rendimiento +  calidad imagen + RENDIMIENTO CON 256GB");
        p.setPrecio(1760.20);
        p.setStock(12);
        p.setImagen("/resources/img/celular.jpg");

        boolean result = dao.insert(p);
        if (result) {
            System.out.println(" PRODUCTO INSERTADO");
        } else {
            System.out.println(" |ERROR| No sé logró registrar");

        }
    }

    // FUNCIONALIDAD - ACTUALIZAR.Productos
    public static void actualizar() {
        Productos p = new Productos();
        p.setNombre("Oppo Reno Pro 11");
        p.setDescripción("CALIDAD RENDIMIENTO 256 GB + CALIDAD IMAGEN");
        p.setPrecio(1760.20);
        p.setStock(18);
        p.setImagen("/resources/img/celular.jpg");

        boolean result = dao.update(p);
        if (result) {
            System.out.println(" PRODUCTO ACTUALIZADO");
        } else {
            System.out.println(" |ERROR| No sé logró actualizar");

        }
    }

    // FUNCIONALIDAD - ELIMINAR.Productos
    public static void eliminar() {
        boolean result = dao.delete(3);

        if (result) {
            System.out.println("ELIMINADO");
        } else {
            System.out.println(" |ERROR| No se logró eliminar");
        }
    }
    // FUNCIONALIDAD - UPDATE STOCK.Productos
    public static void updateStock() {

        if (result) {
            System.out.println("ELIMINADO");
        } else {
            System.out.println(" |ERROR| No se logró eliminar");
        }
    
}
