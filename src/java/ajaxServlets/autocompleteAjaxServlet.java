/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajaxServlets;

import DAOs.autocompleteController;
import dataControllerCore.backendError;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author u27brvz04
 */
@WebServlet(name = "autocompleteAjaxServlet")
public class autocompleteAjaxServlet extends HttpServlet {

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
            out.println("<title>Servlet autocompleteAjaxServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet autocompleteAjaxServlet at " + request.getContextPath() + "</h1>");
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

        String tableName = null;
        String fieldName = null;
        String stringPattern = null;
        List<String> list = null;

        try {
            tableName = request.getParameter("q_table_name");
            fieldName = request.getParameter("q_field_name");
            stringPattern = request.getParameter("q_string_pattern");

            autocompleteController controller = new autocompleteController(tableName, fieldName);
            try {
                list = controller.getAutocompleteByPart(stringPattern);
            } catch (Throwable e) {
                throw e;
            } finally {
                controller.returnConnectionInPool();
            }

            PrintWriter out = response.getWriter();
            String jsonList = "";
            if (list != null && !list.isEmpty()) {
                for (String str : list) {
                    jsonList += ((jsonList.equals("")) ? "" : ",") + "\"" + str + "\"";
                }
            }
            out.print("{\"autocomplete\":[" + jsonList + "]}");
            out.flush();
        } catch (Throwable e) {
            backendError err = new backendError();
            err.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            err.setText("Помилка при роботі з web-сервісом</br>Помилка при "
                    + " спробі отримати інформацію для функції автозоповнення "
                    + " з таблиці " + tableName + " поля " + fieldName);
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
