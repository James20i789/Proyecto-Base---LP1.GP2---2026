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
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 *
 * @author James Rios
 */
@MultipartConfig
@WebServlet(name = "ProductoController", urlPatterns = {"/ProductoController"})
public class ProductoController extends HttpServlet {

    private final IProducto pDao = new ProductoDaoImpl();
    private final Gson gson = new Gson();
    private static  final String UPLOAD_DIR ="assets/img/productos";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        // ACCIONES DE LISTADOS - DIRECTAMENTE CON POSTMAN
        if (action == null) {
            action = "listar";
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
                break;
        }

    }
    
    
    // LISTAR - PRODUCTOS
    private void listarProductos(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        List<Productos> productos = pDao.lista();
        response.getWriter().print(gson.toJson(productos));

    }

    // GUARDAR - PRODUCTOS
    private void guardarProductos(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try {
            Productos p = new Productos();
            p.setNombre(request.getParameter("nombre"));
            p.setDescripción(request.getParameter("descripcion"));
            p.setPrecio(Double.parseDouble(request.getParameter("precio")));
            p.setStock(Integer.parseInt(request.getParameter("stock")));

            Part part = request.getPart("imagen");

            if (part != null && part.getSize() > 0) {
                String fileName = part.getSubmittedFileName();
                //obtener ls ruta donde guardar la imgh
                String pathBuild = getServletContext().getRealPath("/")
                        + "assets/img/productos" + File.separator;
                System.out.println("Ruta Build: " + pathBuild);
                String pathSource = pathBuild.replace("build" + File.separator + "web", "web");

                if (pathSource.equals(pathBuild)) {
                    System.out.println("colocar ruta fija");
                }
                System.out.println("Ruta Source: " + pathSource);
                try {
                    new File(pathSource).mkdirs();
                    new File(pathBuild).mkdirs();

                    File fileSource = new File(pathSource + fileName);
                    try (InputStream input = part.getInputStream()) {
                        java.nio.file.Files.copy(input, fileSource.toPath(),
                                java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                    }
                    System.out.println("Guardado en Source OK");
                    
                    part.write(pathBuild + fileName);
                    System.out.println("Guardado en build OK");

                } catch (Exception e) {
                    System.err.println("Error critico"+e.getMessage());
                    e.printStackTrace();
                }
                p.setImagen("assets/img/productos/"+fileName);

            }
            boolean res = pDao.insert(p);
            response.getWriter().print(gson.toJson(res));

        } catch (Exception e) {
             response.getWriter().print(gson.toJson(false));
        }

    }
    
    // EDITAR PRODUCTOS
    private void editarProductos(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
           try {
               
             Productos p = new Productos();
            p.setNombre(request.getParameter("nombre"));
            p.setDescripción(request.getParameter("descripcion"));
            p.setPrecio(Double.parseDouble(request.getParameter("precio")));
            p.setStock(Integer.parseInt(request.getParameter("stock")));
            p.setId_producto(Integer.parseInt(request.getParameter("id_producto")));
            
            Part part = request.getPart("imagen");
            
               if (part !=null && part.getSize() >0) {
                   String fileName = part.getSubmittedFileName();
                   String uploadPath = getServletContext().getRealPath("")
                           +File.separator + UPLOAD_DIR;
                   part.write(uploadPath + File.separator + fileName);
                   p.setImagen(UPLOAD_DIR+"/"+fileName);
               } else {
                   p.setImagen(request.getParameter("imagen_actual"));
               }
               boolean res = pDao.update(p);
               response.getWriter().print(gson.toJson(res));
            
            
        } catch (Exception e) {
            response.getWriter().print(gson.toJson(false));
        }
          

    }

    // ELIMINAR - PRODUCTOS
    private void eliminarProductos(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
       int id = Integer.parseInt(request.getParameter("id"));
        boolean res = pDao.delete(id);
        response.getWriter().print(gson.toJson(res));

    }

    // BUSCAR - PRODUCTOS
    private void buscarProductos(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
         int id = Integer.parseInt(request.getParameter("id"));
        Productos p = pDao.SearchByID(id);
        response.getWriter().print(gson.toJson(p));

    }

    // PROCESOS - DoGET - RECEPCIÓN DE DATOS POSTMAN
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    // PROCESOS - DoPost - ENVÍO DE DATOS POSTMAN
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
