/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import Dao.PersonaDaoImpl;
import Dao.UsuarioDaoImpl;
import Interface.IPersona;
import Interface.IUsuario;
import Model.Persona;
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

/**
 *
 * @author James Rios
 */
@WebServlet(name = "AuthController", urlPatterns = {"/AuthController"})
public class AuthController extends HttpServlet {

    private final IUsuario uDao = new UsuarioDaoImpl();
    private final IPersona pDao = new PersonaDaoImpl();

    // PROCESS REQUEST - AUTENTICADOR DE DATOS
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

    @Override
    // PROCESOS - DoGET - RECEPCIÓN DE DATOS POSTMAN
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    // PROCESOS - DoPost - ENVÍO DE DATOS POSTMAN
    @Override
    // DoPost ENVÍO
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        // FORMA DE RECOGER DATOS DE LA VISTA EN LA ARQUITECTURA MVC
        String action = request.getParameter("action");
        JsonObject jsonResponse = new JsonObject();

        Gson gson = new Gson();

        try (PrintWriter out = response.getWriter()) {

            if (action.equals("validar")) {
                String user = request.getParameter("usuario");
                String pass = request.getParameter("password");
                // VARIABLE LOCAL DE Usuario.MODEL
                Usuario us = uDao.validate(user, pass);

                if (us != null && us.getUsuario() != null) {
                    // ABRIENDO LA SESIÓN
                    HttpSession session = request.getSession(true);

                    session.setAttribute("usuario", us);
                    jsonResponse.addProperty("success", true);
                    jsonResponse.addProperty("message", "Inicio de Sesion");

                    jsonResponse.add("userData", gson.toJsonTree(us));
                } else {
                    jsonResponse.addProperty("success", false);
                    jsonResponse.addProperty("message", "Usuario o contraseña invalida");
                }
                out.print(jsonResponse.toString());

                // SALIR ACTION
            } else if (action.equals("Salir")) {
                HttpSession session = request.getSession(false);
                if (session != null) {
                    session.invalidate();
                }
                jsonResponse.addProperty("success", true);
                jsonResponse.addProperty("message", "Sesion cerrada");
                out.print(jsonResponse.toString());

                 // REGISTER ACTION
            } else if (action.equals("register")) {

                Persona p = new Persona();
                Usuario u = new Usuario();

                p.setNombre(request.getParameter("nombre"));
                p.setEmail(request.getParameter("email"));
                p.setDirección(request.getParameter("direccion"));
                p.setTelefono(request.getParameter("telefono"));
                u.setClave(request.getParameter("password"));

                int resultado = pDao.insert(p, u);

                jsonResponse.addProperty("sucess", resultado != 0);
                jsonResponse.addProperty("message", resultado != 0 ? "Registro Sucess" : "Error de registro");
                out.print(jsonResponse.toString());

            }

        } catch (Exception e) {
            // EN UN SERVIDOR EL ERROR DE ESTADO 500 ES DE FALLA EN LA LÓGICA
            response.setStatus(500);
            jsonResponse.addProperty("sucess", false);
            jsonResponse.addProperty("message", "Error" + e.getMessage());
            response.getWriter().print(jsonResponse.toString());

        }

    }

    @Override
    // DoServletInfo Envío
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
