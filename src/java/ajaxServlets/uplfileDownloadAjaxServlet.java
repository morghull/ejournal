/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajaxServlets;

import DAOs.uplfileController;
import dataControllerCore.backendError;
import dataControllerCore.fileUtils;
import dataObjects.uplfile;
import java.net.URLEncoder;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author u27brvz04
 */
@WebServlet(name = "uplfileDownloadAjaxServlet")
public class uplfileDownloadAjaxServlet extends HttpServlet {

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
            out.println("<title>Servlet uplfileDownloadAjaxServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet uplfileDownloadAjaxServlet at " + request.getContextPath() + "</h1>");
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

        String id = null;
        uplfile entity = null;

        try {
            id = request.getParameter("q_id");

            uplfileController controller = new uplfileController();
            try {
                entity = controller.getEntityById(Integer.parseInt(id));
            } catch (Throwable e) {
                throw e;
            } finally {
                controller.returnConnectionInPool();
            }

            OutputStream out = response.getOutputStream();
            if (entity != null) {
                String fileName = entity.getUfname();

                String contentType = getServletContext().getMimeType(fileName);

                response.setContentType(contentType);
                if (fileUtils.canBeShowInBrowser(fileName)) {
                    response.setHeader("content-disposition", "inline; filename=file" + entity.getUfid() + "." + fileUtils.getExtension(fileName));
                } else {
                    response.setHeader("content-disposition", "attachment; filename=file" + entity.getUfid() + "." + fileUtils.getExtension(fileName));
                }
                InputStream in = entity.getUfcontent();
                try {
                    byte[] buffer = new byte[4096];
                    int length;
                    while ((length = in.read(buffer)) > 0) {
                        out.write(buffer, 0, length);
                    }
                } finally {
                    in.close();
                }
            }
            out.flush();
        } catch (Throwable e) {
            backendError err = new backendError();
            err.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            err.setText("Помилка при роботі з web-сервісом</br>Помилка при "
                    + " спробі отримати ынформацыю щодо збереженого файлу");
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
