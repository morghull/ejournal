/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajaxServlets;

import DAOs.rdtController;
import dataControllerCore.backendError;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
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
        PrintWriter out = response.getWriter();
        try {
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
        } finally {
            out.close();
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

        String item = null;
        String value = null;

        try {
            boolean validation;
            String validationMessage = "";

            item = request.getParameter("q_item");
            value = request.getParameter("q_value");

            rdtController controller = new rdtController();
            try {
                validation = controller.valdiateRdtk(value);
            } catch (Throwable e) {
                throw e;
            } finally {
                controller.returnConnectionInPool();
            }

            if (!validation) {
                validationMessage = "Код типу розпорядчого документа не відповідає довіднику \\\"Розпорядчі документи.Типи\\\"";
            }

            PrintWriter out = response.getWriter();
            out.print("{\"valid\":" + validation
                    + ((validation) ? "" : ",\"message\":\"" + validationMessage + "\"")
                    + "}");
            out.flush();
        } catch (Throwable e) {
            backendError err = new backendError();
            err.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            err.setText("Помилка при роботі з web-сервісом</br>Помилка при "
                    + " спробі отримати інформацію запису таблиці rdt для перевірки");
            err.setDetails("<div class=\"nested-error\">" + e.getClass().getName()
                    + ": " + e.getMessage() + "</div>");
            response.setStatus(err.getStatus());
            PrintWriter outErr = response.getWriter();
            outErr.printf(err.toString());

            outErr.flush();
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
