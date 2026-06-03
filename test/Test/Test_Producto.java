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


    // TEST PRODUCTO-BD
public class Test_Producto {

    public static IProducto dao = new ProductoDaoImpl();

    public static void main(String[] args) {
        Test_Producto tP = new Test_Producto();
        //tP.listar();
        // tP.agregar();
        //tP.actualizar();
        //P.SearchByID();
        // tP.eliminar();
        //tP.updateStock();
    }

    // FUNCIONALIDAD - LISTAR.Productos
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

    // FUNCIONALIDAD - AGREGAR.Productos
    public static void agregar() {
        Productos p = new Productos();
        p.setNombre("Samsung S25 ULTRA-BLACK");
        p.setDescripción("Alto rendimiento + imagen 4K + RENDIMIENTO CON 512GB");
        p.setPrecio(3800.55);
        p.setStock(25);
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
        p.setId_producto(6);

        boolean result = dao.update(p);
        if (result) {
            System.out.println(" PRODUCTO ACTUALIZADO");
        } else {
            System.out.println(" |ERROR| No sé logró actualizar");

        }
    }

    // FUNCIONALIDAD - SearchByID.Productos
    public static void SearchByID() {
        Productos pr = dao.SearchByID(6);

        if (pr != null) {
            System.out.println(" PRODUCTOS ENCONTRADO");
            System.out.println("ID:" + pr.getId_producto());
            System.out.println("Nombre:" + pr.getNombre());
            System.out.println("Descripcion:" + pr.getDescripción());
            System.out.println("Precio:" + pr.getPrecio());
            System.out.println("Stock:" + pr.getStock());
            System.out.println("Ruta Img:" + pr.getImagen());
        } else {
            System.out.println("|ERROR| No hay registros");
        }

    }

    // FUNCIONALIDAD - ELIMINAR.Productos
    public static void eliminar() {
        boolean result = dao.delete(6);
        if (result) {
            System.out.println("ELIMINADO");
        } else {
            System.out.println(" |ERROR| No se logró eliminar");
        }
    }

    // FUNCIONALIDAD - UPDATE STOCK.Productos
    public static void updateStock() {
        boolean result = dao.updateStock(6, 160);
        if (result) {
            System.out.println(" STOCK ACTUALIZADO");
        } else {
            System.out.println(" |ERROR| No sé logró actualizar");
        }
    }
}