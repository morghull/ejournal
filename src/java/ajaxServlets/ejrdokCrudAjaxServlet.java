/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajaxServlets;

import DAOs.ejrdokController;
import DAOs.uplfileController;
import dataControllerCore.backendError;
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
//@WebServlet(name = "ejrdokCrudAjaxServlet", urlPatterns = {"/servlets/ajax/ejrdokCrud"})
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
        PrintWriter out = response.getWriter();
        try {
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
        String id = null;
        ejrdok entity = null;
        ejrdokController controller = null;

        try {
            tableName = request.getParameter("q_table_name");
            id = request.getParameter("q_id");

            controller = new ejrdokController();
            try {
                entity = controller.getEntityById(Integer.parseInt(id));
            } catch (Throwable e) {
                throw e;
            } finally {
                controller.returnConnectionInPool();
            }

            PrintWriter out = response.getWriter();
            if (entity != null) {
                out.print("{\"row\":" + entity.toString() + "}");
            }
            out.flush();
        } catch (Throwable e) {
            backendError err = new backendError();
            err.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            err.setText("Помилка при роботі з web-сервісом</br>Помилка при спробі"
                    + " отримати інформацію запису таблиці " + tableName + " для редагування");
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
        response.setContentType("application/json; charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        String tableName = null;
        String mode = null;
        int outId = -1;
        int outRowNumber = -1;

        try {
            tableName = request.getParameter("q_table_name");
            mode = request.getParameter("q_mode");
            DateFormat format = new SimpleDateFormat("dd.MM.yyyy");

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
                    ejrdokController controller = new ejrdokController();
                    try {
                        outId = controller.create(entity);
                        outRowNumber = controller.getRowNumberInOrdering(outId);
                    } catch (Throwable e) {
                        throw e;
                    } finally {
                        controller.returnConnectionInPool();
                    }

                    Collection<Part> parts = request.getParts();
                    List<uplfile> files = new LinkedList<uplfile>();

                    uplfile temp = null;
                    for (Part part : parts) {
                        String fileName = getSubmittedFileName(part);
                        if (part.getContentType() != null && fileName != null && !fileName.isEmpty()) {
                            temp = new uplfile();
                            temp.setUfname(fileName);
                            temp.setUfcontent(part.getInputStream());
                            temp.setIdd(outId);
                            files.add(temp);
                        }
                    }
                    if (!files.isEmpty()) {
                        uplfileController upfController = new uplfileController();
                        try {
                            upfController.createFromList(files);
                        } catch (Throwable e) {
                            throw e;
                        } finally {
                            upfController.returnConnectionInPool();
                        }
                    }
                } else if (mode.equals("edit")) {
                    entity.setIdd(Integer.parseInt(request.getParameter("q_id")));
                    outId = entity.getIdd();

                    ejrdokController controller = new ejrdokController();
                    try {
                        controller.update(entity);
                        outRowNumber = controller.getRowNumberInOrdering(outId);
                    } catch (Throwable e) {
                        throw e;
                    } finally {
                        controller.returnConnectionInPool();
                    }

                    Collection<Part> parts = request.getParts();
                    List<uplfile> files = new LinkedList<uplfile>();

                    uplfile temp = null;
                    for (Part part : parts) {
                        String fileName = getSubmittedFileName(part);
                        if (part.getContentType() != null && fileName != null && !fileName.isEmpty()) {
                            temp = new uplfile();
                            temp.setUfname(fileName);
                            temp.setUfcontent(part.getInputStream());
                            temp.setIdd(outId);
                            files.add(temp);
                        }
                    }
                    if (files.size() > 0) {
                        uplfileController upfController = new uplfileController();
                        try {
                            upfController.updateFromList(files);
                        } catch (Throwable e) {
                            throw e;
                        } finally {
                            upfController.returnConnectionInPool();
                        }
                    }
                }
                PrintWriter out = response.getWriter();
                out.printf("{"
                        + "\"ret_id\":" + outId
                        + ",\"ret_rn\":" + outRowNumber
                        + "}");

            } else if (mode.equals("delete")) {
                ejrdokController controller = new ejrdokController();
                try {
                    controller.delete(Integer.parseInt(request.getParameter("q_id")));
                } catch (Throwable e) {
                    throw e;
                } finally {
                    controller.returnConnectionInPool();
                }
            }
        } catch (Throwable e) {
            Map<String, String> stringModes = new HashMap<String, String>();
            stringModes.putAll(new HashMap<String, String>() {
                {
                    put("edit", "зміні");
                    put("add", "додаванні");
                    put("delete", "видаленні");
                }
            });

            backendError err = new backendError();
            err.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            err.setText("Помилка при роботі з web-сервісом</br>Помилка при "
                    + ((stringModes.containsKey(mode)) ? stringModes.get(mode).trim() : "обробці")
                    + " запису у таблиці " + tableName);
            err.setDetails("<div class=\"nested-error\">" + e.getClass().getName()
                    + ": " + e.getMessage() + "</div>");
            response.setStatus(err.getStatus());
            PrintWriter outErr = response.getWriter();
            outErr.printf(err.toString());

            outErr.flush();
        }

    }

    private static String getSubmittedFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
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
