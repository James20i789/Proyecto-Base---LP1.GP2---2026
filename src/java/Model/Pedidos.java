/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.List;
import oracle.sql.TIMESTAMP;

/**
 *
 * @author James Rios
 */
public class Pedidos {

    private int id_pedidos;
    private Persona persona;
    private double total;
    private EstadoPedido estadoPedido;
    private TIMESTAMP fecha;
    private List<Carrito> detalleCarritos;

    public Pedidos() {
    }

    public Pedidos(int id_pedidos, Persona persona, double total, EstadoPedido estadoPedido, TIMESTAMP fecha, List<Carrito> detalleCarritos) {
        this.id_pedidos = id_pedidos;
        this.persona = persona;
        this.total = total;
        this.estadoPedido = estadoPedido;
        this.fecha = fecha;
        this.detalleCarritos = detalleCarritos;
    }

    public int getId_pedidos() {
        return id_pedidos;
    }

    public void setId_pedidos(int id_pedidos) {
        this.id_pedidos = id_pedidos;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public EstadoPedido getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(EstadoPedido estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public TIMESTAMP getFecha() {
        return fecha;
    }

    public void setFecha(TIMESTAMP fecha) {
        this.fecha = fecha;
    }

    public List<Carrito> getDetalleCarritos() {
        return detalleCarritos;
    }

    public void setDetalleCarritos(List<Carrito> detalleCarritos) {
        this.detalleCarritos = detalleCarritos;
    }
    
    

}