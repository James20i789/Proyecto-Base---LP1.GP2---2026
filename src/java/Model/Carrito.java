/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author James Rios
 */
public class Carrito {

    private int item;
    private int idProducto;
    private String nombre;
    private String description;
    private double precioCompra;
    private int canitdad;
    private double subTotal; // CANTIDAD DEL DETALLE - PEDIDO.

    public Carrito() {
    }

    public Carrito(int item, int idProducto, String nombre, String description, double precioCompra, int canitdad, double subTotal) {
        this.item = item;
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.description = description;
        this.precioCompra = precioCompra;
        this.canitdad = canitdad;
        this.subTotal = subTotal;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public int getCanitdad() {
        return canitdad;
    }

    public void setCanitdad(int canitdad) {
        this.canitdad = canitdad;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }
    
    
    
    

}
