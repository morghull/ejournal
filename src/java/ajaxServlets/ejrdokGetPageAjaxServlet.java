/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajaxServlets;

import DAOs.ejrdokController;
import dataControllerCore.backendError;
import dataObjects.ejrdok;
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
@WebServlet(name = "ejrdokGetPageAjaxServlet")
public class ejrdokGetPageAjaxServlet extends HttpServlet {

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
            out.println("<title>Servlet ejrdokGetPageAjaxServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ejrdokGetPageAjaxServlet at " + request.getContextPath() + "</h1>");
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
        String pageSize = null;
        String pageNumber = null;
        List<ejrdok> list = null;

        try {
            tableName = request.getParameter("q_table_name");
            pageSize = request.getParameter("q_page_size");
            pageNumber = request.getParameter("q_page_number");

            ejrdokController controller = new ejrdokController();
            try {
                list = controller.getPage(Integer.parseInt(pageNumber), Integer.parseInt(pageSize));
            } catch (Throwable e) {
                throw e;
            } finally {
                controller.returnConnectionInPool();
            }

            PrintWriter out = response.getWriter();
            String jsonList = "";
            if (list != null && !list.isEmpty()) {
                for (ejrdok entity : list) {
                    jsonList += ((jsonList.equals("")) ? "" : ",") + entity.toString();
                }
                out.print("{\"page\":[" + jsonList + "],"
                        + "\"q_page_size\":" + pageSize
                        + ",\"q_page_number\":" + pageNumber + "}");
            }
            out.flush();
        } catch (Throwable e) {
            backendError err = new backendError();
            err.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            err.setText("Помилка при роботі з web-сервісом</br>Помилка при "
                    + " спробі отримати дінні з таблиці " + tableName);
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
