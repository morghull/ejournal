/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajaxServlets;

import java.io.IOException;
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
        String id = request.getParameter("id");

//        String fileName = "";
//        String fileType = "";
//        // Find this file id in database to get file name, and file type
//
//        // You must tell the browser the file type you are going to send
//        // for example application/pdf, text/plain, text/html, image/jpg
//        response.setContentType(fileType);
//
//        // Make sure to show the download dialog
//        response.setHeader("Content-disposition", "attachment; filename=yourcustomfilename.pdf");
//
//        // Assume file name is retrieved from database
//        // For example D:\\file\\test.pdf
//        File my_file = new File(fileName);
//
//        // This should send the file to browser
//        OutputStream out = response.getOutputStream();
//        FileInputStream in = new FileInputStream(my_file);
//        byte[] buffer = new byte[4096];
//        int length;
//        while ((length = in.read(buffer)) > 0) {
//            out.write(buffer, 0, length);
//        }
//        in.close();
//        out.flush();
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
