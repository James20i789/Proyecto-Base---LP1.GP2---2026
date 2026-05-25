/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Test;

import Dao.PedidoDaoImpl;
import Interface.IPedido;
import Model.Carrito;
import Model.EstadoPedido;
import Model.Pedidos;
import Model.Persona;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author James Rios
 */
public class Test_Pedido {
    IPedido dao = new PedidoDaoImpl();

    public static void main(String[] args) {
       Test_Pedido t = new Test_Pedido();
       t.TestPedido();
    }
    // FUNCIONALIDAD - TestPedido
    public void TestPedido(){
        Persona p = new Persona();
        p.setId_persona(1);
        
        List<Carrito> listaCarrito = new ArrayList<>();
        
        // CARRITO - Insert ITEM2
        Carrito item1 = new Carrito();
        item1.setIdProducto(1); // TABLA PRODUCTOS - DATOS 01
        item1.setPrecioCompra(50);
        item1.setCanitdad(2);
        item1.setSubTotal(100);
        listaCarrito.add(item1);
        
        // CARRITO - Insert ITEM1
        Carrito item2 = new Carrito();
        item2.setIdProducto(2); // TABLA PRODUCTOS - DATOS 02
        item2.setPrecioCompra(40);
        item2.setCanitdad(3);
        item2.setSubTotal(120);
        listaCarrito.add(item2);
        
        double total = 100+120;
        
        Pedidos nuevoPedido = new Pedidos();
        nuevoPedido.setPersona(p);
        nuevoPedido.setTotal(total);
        nuevoPedido.setEstadoPedido(EstadoPedido.ENTREGADO);
        nuevoPedido.setDetalleCarritos(listaCarrito);
        
        // ENVIO DE DATOS EN RELACIÓN A LOS PEDIDOS DEL CARRITO - EJECUCIÓN
        System.out.println(" ENVIANDO .... ");
        int result = dao.generarPedido(nuevoPedido);
        if (result>0) {
            System.out.println(" FELICIDADES. PEDIDO REGISTRADO");
            System.out.println("TOTAL"+total);
        } else {
            System.out.println(" |ERROR| AL GENERAR EL PEDIDO ");
        }
    }

}
