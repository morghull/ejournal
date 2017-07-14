/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajaxServlets;

import DAOs.rdtController;
import dataObjects.rdt;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author u27brvz04
 */
@WebServlet(name = "rdtValidationAjaxServlet")
public class rdtValidationAjaxServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet rdtValidationAjaxServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet rdtValidationAjaxServlet at " + request.getContextPath() + "</h1>");
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
        response.setContentType("application/json; charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        String item = null, value = null;

        try {
            boolean validation;
            String validationMessage = "";
            
            item = request.getParameter("q_item");
            value = request.getParameter("q_value");

            rdtController controller = new rdtController();
            validation = controller.valdiateRdtk(value);
            controller.returnConnectionInPool();

            if (!validation) {
                validationMessage = "Код типу розпорядчого документа не відповідає довіднику \\\"Типи розпорядчих документів\\\"";
            }

            PrintWriter out = response.getWriter();
            out.print("{\"valid\":" + validation
                    + ((!validation) ? ",\"message\":\"" + validationMessage + "\"" : "")
                    + "}");
            out.flush();
            //response.getWriter().write(json);
        } catch (Throwable e) {
            response.setContentType("application/json; charset=utf-8");
            response.addHeader("error", URLEncoder.encode("Помилка при роботі з sql-сервером</br>Помилка при "
                    + " отримати інформацію запису таблиці rdt для редагування", "UTF-8")
            );
            response.addHeader("error_details", URLEncoder.encode("<div class=\"nested-error\">" + e.getClass().getName() + ": " + e.getMessage() + "</div>", "UTF-8"));

            //response.setStatus(500);
            throw new ServletException();
        }
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