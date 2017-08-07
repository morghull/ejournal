/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajaxDataController;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pgsqlDataController.PgsqlData;

/**
 *
 * @author u27brvz04
 */
@WebServlet(name = "AjaxDataHttpServlet", urlPatterns = {"/AjaxDataHttpServlet"})
public class AjaxDataHttpServlet extends HttpServlet {

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
            out.println("<title>Servlet AjaxDataHttpServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AjaxDataHttpServlet at " + request.getContextPath() + "</h1>");
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
        try {
            Boolean autocompleteFlag = request.getParameter("atc").equals("1");
            String tableName = request.getParameter("tbl_nm");
            if (!autocompleteFlag) {
                String filterString = request.getParameter("filter");
                List<String> filterPairList = new ArrayList<String>(Arrays.asList(filterString.replaceAll("^\\{\"|\"\\}$|\\}$", "").split("\",\"")));
                Map<String, String> filterHashMap = new HashMap<String, String>();
                if (!filterString.equals("")) {
                    for (String filterPair : filterPairList) {
                        filterHashMap.put(filterPair.split("\":\"")[0].toString(), filterPair.split("\":\"")[1].toString().replace("\\\"", "\""));
                    }
                }
                PgsqlData pgsqlData = new PgsqlData();
                ResultSet ajaxRows = pgsqlData.getRows(tableName, filterHashMap);
                response.setContentType("application/json; charset=utf-8");

                ArrayList<String> lst = new ArrayList<String>();
                PrintWriter out = response.getWriter();
                while (ajaxRows.next()) {
                    if (tableName.equals("software")) {
                        lst.add("{\"softname\":\"" + ajaxRows.getString("softname").replace("\"", "\\\"")
                                + "\",\"sysname\":\"" + ((ajaxRows.getString("sysname") == null) ? "" : ajaxRows.getString("sysname"))
                                + "\",\"schemaname\":\"" + ((ajaxRows.getString("schemaname") == null) ? "" : ajaxRows.getString("schemaname")) + "\"}");
                    }
                }
                out.print(lst);
            }
//            String part = request.getParameter("part");
//            String fieldName = request.getParameter("fld_nm");
//            PgsqlData ajaxData = new PgsqlData();
//
//            ResultSet ajaxRows = ajaxData.getRows(part);
//
//            response.setContentType("text/plain; charset=utf-8");
//
//            ArrayList<String> lst = new ArrayList<String>();
//            PrintWriter out = response.getWriter();
//            while (ajaxRows.next()) {
//                lst.add("\"" + ajaxRows.getString("atc_fld") + "\"");
//            };
//            out.print(lst);

        } catch (Exception e) {
            e.printStackTrace();
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
        Map<String, String> rowDataHashMap = new HashMap<String, String>();
        String tableName = request.getParameter("tbl_nm");
        String mode = request.getParameter("mode");
        try {
            String retrievedId = "";

            if (request.getParameter("row_data") != null) {
                String jsonRowDataString = request.getParameter("row_data");
                List<String> rowDataPairList = new ArrayList<String>(Arrays.asList(jsonRowDataString.replaceAll("^\\{\"|\"\\}$|\\}$", "").split("\",\"")));
                if (!jsonRowDataString.equals("")) {
                    for (String rowDataPair : rowDataPairList) {
                        rowDataHashMap.put(
                                rowDataPair.split("\":\"")[0].toString(),
                                (rowDataPair.split("\":\"").length == 2)
                                ? rowDataPair.split("\":\"")[1].toString().replace("\\\"", "\"")
                                : "");
                    }
                }
            }

            PgsqlData pgsqlData = new PgsqlData();

            if (mode.equals("add")) {
                retrievedId = pgsqlData.insertRow(tableName, rowDataHashMap);
            } else if (mode.equals("edit")) {
                retrievedId = pgsqlData.updateRow(tableName, rowDataHashMap);
            } else if (mode.equals("delete")) {
                pgsqlData.deleteRow(tableName, rowDataHashMap);
            }

            response.setContentType("application/json; charset=utf-8");
            PrintWriter out = response.getWriter();
            out.print("{\"id\":\"" + ((retrievedId == null) ? "" : retrievedId.trim()) + "\"}");
        } catch (SQLException e) {
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
            response.addHeader("error_details", URLEncoder.encode(e.getMessage(), "UTF-8"));
            throw new ServletException();
        } catch (Exception e) {
            response.setContentType("application/json; charset=utf-8");
            response.addHeader("error", URLEncoder.encode("Помилка при роботі web-сервісу", "UTF-8"));
            response.addHeader("error_details", URLEncoder.encode(e.toString(), "UTF-8"));
        }
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
