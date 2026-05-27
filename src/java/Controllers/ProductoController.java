/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import Dao.ProductoDaoImpl;
import Interface.IProducto;
import Model.Productos;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author James Rios
 */
@WebServlet(name = "ProductoController", urlPatterns = {"/ProductoController"})
public class ProductoController extends HttpServlet {

    // LLAMADA GLOBAL
    private final IProducto pDAO = new ProductoDaoImpl();
    private final Gson gson = new Gson();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        if (action == null) {
            action = "Listar";
        }
        switch (action) {
            case "guardar":
                guardarProductos(request, response);
                break;
            case "editar":
                editarProductos(request, response);
                break;
            case "eliminar":
                eliminarProductos(request, response);
                break;
            case "buscar":
                buscarProductos(request, response);
                break;
            default:
                listarProductos(request, response);
        }
    }
    // LISTAR PRODUCTOS
    private void listarProductos(HttpServletRequest request, HttpServletResponse response)
            throws IOException{
        List<Productos> productos = pDAO.lista(); // ENVIO DE DATOS A POSTMAN
        response.getWriter().print(gson.toJson(productos));
    }
    // GUARDAR PRODUCTOS
    private void guardarProductos(HttpServletRequest request, HttpServletResponse response)
            throws IOException{
        
    }
    // EDITAR PRODUCTOS
    private void editarProductos(HttpServletRequest request, HttpServletResponse response)
            throws IOException{
        
    }
    // ELIMINAR PRODUCTOS
    private void eliminarProductos(HttpServletRequest request, HttpServletResponse response)
            throws IOException{
        
    }
    // BUSCAR PRODUCTOS
    private void buscarProductos(HttpServletRequest request, HttpServletResponse response)
            throws IOException{
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
