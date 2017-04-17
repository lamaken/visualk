/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualk.art;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static visualk.art.Mixed.CANVASX_SIZE;
import static visualk.art.Mixed.CANVASY_SIZE;
import static visualk.art.Mixed.counter;

/**
 *
 * @author lamaken
 */
@WebServlet(name = "LiveMosaic", urlPatterns = {"/LiveMosaic"})
public class LiveMosaic extends Mosaic{
    
    public static float counter = 0;
    public static boolean show_number = false;
    public static Integer CANVASX_SIZE = 100;
    public static Integer CANVASY_SIZE = 100;
    public static Integer cellw = 10;
    

    static BufferedImage getMosaic(float seed) {
        BufferedImage buf = new BufferedImage(CANVASX_SIZE, CANVASY_SIZE, 2);
        Graphics2D g2 = buf.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(Color.black);

        g2.fillRect(0, 0, CANVASX_SIZE + cellw, CANVASY_SIZE + cellw);

        g2.setStroke(new BasicStroke(1));
    //    double angle = 0;
    //  float ratio = (float) (CANVASX_SIZE * CANVASY_SIZE) / 360;

        
  /*      
        if (counter % 13 == 0) {
            CANVASX_SIZE += 10;
            CANVASY_SIZE += 10;
        } else {
            CANVASX_SIZE -= 10;
            CANVASY_SIZE -= 10;
        }
*/
        //generar nomes el quadradet dadalt a lesquerra 
        //i despres clonarlo per ocupar tot lespai
        for (int n = -10; n < new Float(CANVASX_SIZE).intValue(); n += cellw) {

            for (int m = -10; m < new Float(CANVASY_SIZE).intValue(); m += cellw) {

                //int joe = (int) (seed * Math.cos(angle));
                //angle += ratio;

                g2.setColor(Color.getHSBColor((m + n) / seed, (m + n) / seed, (m + n) / seed));

                // int x =  n + joe -CANVASX_SIZE/cellw;
                //int y = m + joe -CANVASY_SIZE/cellw;
               // g2.fillArc(n, m, cellw * 3, cellw * 3, n, m);
                g2.fillArc(n, m,  cellw, cellw ,180+n,260-m);

            }
        }

        g2.dispose();
        return (buf);
    }

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
        response.setContentType("image/PNG");

        String mx = request.getParameter("mx");
        String my = request.getParameter("my");
        String cell = request.getParameter("cellw");
        String d = request.getParameter("d");

        show_number = false;
        if (d != null) {
            show_number = true;
        }

        if (mx != null) {
            try {
                CANVASX_SIZE = Integer.parseInt(mx);
            } catch (Exception e) {

                CANVASX_SIZE = 150;
            }
        }
        if (my != null) {
            try {
                CANVASY_SIZE = Integer.parseInt(my);
            } catch (Exception e) {

                CANVASY_SIZE = 150;
            }
        }
        if (cell
                != null) {
            try {
                cellw = Integer.parseInt(cell);
            } catch (Exception e) {

                cellw = 23;
            }

        }

        //coud be infinite of course
        if (counter > 0.3) {
            counter = 0;
        };
        counter += 0.001;

        // BufferedImage squared = generateSquared(counter);
        BufferedImage mixed = this.getMosaic(counter);
        mixed = spill(mixed);
        mixed = negativo(mixed);

        ImageIO.write(mixed, "png", response.getOutputStream());
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