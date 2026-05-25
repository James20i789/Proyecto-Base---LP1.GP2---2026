/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import Dao.PersonaDaoImpl;
import Dao.UsuarioDaoImpl;
import Interface.IPersona;
import Interface.IUsuario;
import Model.Usuario;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import oracle.jdbc.driver.json.binary.JsonpOsonObject;

/**
 *
 * @author James Rios
 */
@WebServlet(name = "AuthController", urlPatterns = {"/AuthController"})
public class AuthController extends HttpServlet {

    private final IUsuario uDao = new UsuarioDaoImpl();
    private final IPersona PDao = new PersonaDaoImpl();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AuthController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AuthController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    // DoPost ENVÍO
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        // FORMA DE RECOGER DATOS DE LA VISTA EN LA ARQUITECTURA MVC
        String action = request.getParameter("ACTION");
        JsonObject jsonResponse = new JsonObject(); // Importe de Librería gson-2.10 jar
        
        
        Gson gson =  new Gson();
        try (PrintWriter out = response.getWriter()){
            if (action.equals("VALIDAR")) {
                String user = request.getParameter("USUARIO");
                String pasw = request.getParameter("PASSWORD");
                // VARIABLE LOCAL DE Usuario.MODEL
                Usuario us = uDao.validate(user, pasw);
                
                if (us!=null && us.getUsuario() !=null) {
                    // ABRIENDO LA SESIÓN
                    HttpSession sesion = request.getSession(true);
                    sesion.setAttribute("USUARIO",us);
                    
                    jsonResponse.addProperty("Success", true);
                    jsonResponse.addProperty("Message", "Incio de sesión exitoso");
                    jsonResponse.add("userData", gson.toJsonTree(us));
                    
                }else{
                    jsonResponse.addProperty("Success", false);
                    jsonResponse.addProperty("Message", "SESIÓN INCORRECTA");
                }
                out.println(jsonResponse.toString());
            }
        } catch (Exception e) {
            // EN UN SERVIDOR EL ERRO DE ESTADO 500 ES DE FALLA EN LA LÓGICA
            response.setStatus(500);
            jsonResponse.addProperty("Success", false);
            jsonResponse.addProperty("Message", "ERRR" + e.getMessage());
            response.getWriter().print(jsonResponse.toString());
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    // DoGET Envío
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
