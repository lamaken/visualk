/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualk.nine;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import visualk.nine.model.NineGen;

/**
 *
 * @author alex
 */
@WebServlet(name = "Nine", urlPatterns = {"/Nine"})
public class Nine extends HttpServlet {

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
            String number = request.getParameter("number");
            if(number==null)number="10";
            if(Integer.parseInt(number)<9)number="10";
            NineGen nine = new NineGen(number);
            

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Main</title>");
            out.println("</head>");
            out.println("<body style=\"text-align:center;font-family: monospace \">");
            out.println("<form action='/visualk/nine/Nine' method='GET'>");
            out.println("<h1>Magic Nine</h1>");
            
            out.println("<input name='number' type='number' value='"+number+"'/>");
            out.println("<input type='Submit' value='Submit'/>");
            out.println("</form>");
            if(nine.getIterations()>1)out.println("<h1>It has "+nine.getIterations()+" iterations</h1>");
            else out.println("<h1>It has only "+nine.getIterations()+" iteration</h1>");
            //out.println("<h3>Iterations:" + nine.getIterations() + "</h3>");
            //out.println("<h3>Diff:" + nine.getDiff() + "</h3>");
            //out.println("<h3>OldDiff:" + nine.getOldiff() + "</h3>");
            
            
            out.println(nine.toString());
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
