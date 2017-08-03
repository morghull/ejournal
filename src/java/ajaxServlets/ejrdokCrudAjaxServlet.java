/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajaxServlets;

import DAOs.ejrdokController;
import DAOs.uplfileController;
import dataObjects.ejrdok;
import dataObjects.uplfile;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String tableName = null;
        String id = null;

        try {
            tableName = request.getParameter("q_table_name");
            id = request.getParameter("q_id");

            ejrdokController controller = new ejrdokController();
            ejrdok entity = controller.getEntityById(Integer.parseInt(id));
            controller.returnConnectionInPool();

            PrintWriter out = response.getWriter();
            out.print("{\"row\":" + entity.toString() + "}");
            out.flush();
            //response.getWriter().write(json);
        } catch (Throwable e) {
            response.setContentType("application/json; charset=utf-8");
            response.addHeader("error", URLEncoder.encode("Помилка при роботі з sql-сервером</br>Помилка при спробі"
                    + " отримати інформацію запису таблиці " + tableName + " для редагування", "UTF-8")
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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String tableName = null;
        String mode = null;
        int outId = -1;
        int outRowNumber = -1;

        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        try {
            tableName = request.getParameter("q_table_name");
            mode = request.getParameter("q_mode");
            ejrdokController controller = new ejrdokController();

            if (mode.equals("add") || mode.equals("edit")) {
                ejrdok entity = new ejrdok();
                entity.setRdk(request.getParameter("rdk"));
                entity.setRdn(request.getParameter("rdn"));
                entity.setRdd(format.parse(request.getParameter("rdd")));
                entity.setNazz(request.getParameter("nazz"));
                entity.setRdsh(request.getParameter("rdsh"));
                entity.setOrdk(request.getParameter("ordk"));
                entity.setOrdn(request.getParameter("ordn"));
                entity.setOrdd(format.parse(request.getParameter("ordd")));
                entity.setDvd(format.parse(request.getParameter("dvd")));
                entity.setNzak(request.getParameter("nzak"));
                entity.setPrim(request.getParameter("prim"));

                if (mode.equals("add")) {
                    outId = controller.create(entity);
                    outRowNumber = controller.getRowNumberInOrdering(outId);
                    controller.returnConnectionInPool();

                    Collection<Part> parts = request.getParts();
                    List<uplfile> files = new LinkedList<uplfile>();

                    uplfile temp = null;
                    for (Part part : parts) {
                        if (part.getContentType() != null && !part.getSubmittedFileName().isEmpty()) {
                            temp = new uplfile();
                            temp.setUfname(Paths.get(part.getSubmittedFileName()).getFileName().toString());
                            temp.setUfcontent(part.getInputStream());
                            temp.setIdd(outId);
                            files.add(temp);
                        }
                    }
                    if (!files.isEmpty()) {
                        uplfileController upfController = new uplfileController();
                        upfController.createFromList(files);
                        upfController.returnConnectionInPool();
                    }
                } else if (mode.equals("edit")) {
                    entity.setIdd(Integer.parseInt(request.getParameter("q_id")));
                    outId = entity.getIdd();
                    controller.update(entity);
                    outRowNumber = controller.getRowNumberInOrdering(outId);
                    controller.returnConnectionInPool();
                    
                    Collection<Part> parts = request.getParts();
                    List<uplfile> files = new LinkedList<uplfile>();

                    uplfile temp = null;
                    for (Part part : parts) {
                        if (part.getContentType() != null && !part.getSubmittedFileName().isEmpty()) {
                            temp = new uplfile();
                            temp.setUfname(Paths.get(part.getSubmittedFileName()).getFileName().toString());
                            temp.setUfcontent(part.getInputStream());
                            temp.setIdd(outId);
                            files.add(temp);
                        }
                    }
                    if (!files.isEmpty()) {
                        uplfileController upfController = new uplfileController();
                        upfController.updateFromList(files);
                        upfController.returnConnectionInPool();
                    }
                }
            } else if (mode.equals("delete")) {
                controller.delete(Integer.parseInt(request.getParameter("q_id")));
                controller.returnConnectionInPool();
            }
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
            response.addHeader("error_details", URLEncoder.encode("<div class=\"nested-error\">" + e.getClass().getName() + ": " + e.getMessage() + "</div>", "UTF-8"));

            //response.setStatus(500);
            throw new ServletException();
        }
        if (mode.equals("add") || mode.equals("edit")) {
            response.addHeader("ret_id", Integer.toString(outId));
            response.addHeader("ret_rn", Integer.toString(outRowNumber));
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
