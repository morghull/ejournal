/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajaxServlets;

import DAOs.ejrdokController;
import dataObjects.ejrdok;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author u27brvz04
 */
@WebServlet(name = "ejrdokCrudAjaxServlet")
@MultipartConfig
public class ejrdokCrudAjaxServlet extends HttpServlet {

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
            out.println("<title>Servlet ejrdokCrudAjaxServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ejrdokCrudAjaxServlet at " + request.getContextPath() + "</h1>");
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String tableName = null;
        String mode = null;
        int newId;

        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        ejrdok object = new ejrdok();
        try {
            tableName = request.getParameter("q_table_name");
            mode = request.getParameter("q_mode");

            object.setRdk(request.getParameter("rdk"));
            object.setRdn(request.getParameter("rdn"));
            object.setRdd(format.parse(request.getParameter("rdd")));
            object.setNazz(request.getParameter("nazz"));
            object.setRdsh(request.getParameter("rdsh"));
            object.setOrdk(request.getParameter("ordk"));
            object.setOrdn(request.getParameter("ordn"));
            object.setOrdd(format.parse(request.getParameter("ordd")));
            object.setDvd(format.parse(request.getParameter("dvd")));
            object.setNzak(request.getParameter("nzak"));
            object.setPrim(request.getParameter("prim"));

            ejrdokController controller = new ejrdokController();
            newId = controller.create(object);
            controller.returnConnectionInPool();

        } catch (Throwable e) {
            Map<String, String> stringModes = new HashMap<String, String>();
            stringModes.putAll(new HashMap<String, String>() {
                {
                    put("edit", "зміні");
                    put("add", "доданні");
                    put("delete", "видаленні");
                }
            });
            response.setContentType("application/json; charset=utf-8");
            response.addHeader("error", URLEncoder.encode("Помилка при роботі з sql-сервером</br>Помилка при "
                    + stringModes.get(mode).trim() + " запису у таблиці " + tableName, "UTF-8")
            );
            response.addHeader("error_details", URLEncoder.encode("<div class=\"nested-error\">"+e.getMessage()+"</div>", "UTF-8"));

            //response.setStatus(500);
            throw new ServletException();
        }
        response.addHeader("new_id",Integer.toString(newId));
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
