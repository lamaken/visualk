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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lamaken
 */
@WebServlet(name = "Arab", urlPatterns = {"/Arab"})
public class Arab extends HttpServlet {

    public static Integer CANVASX_SIZE = 100;
    public static Integer CANVASY_SIZE = 100;
    public static Integer cellw = 10;
    public static float counter = 0;
    public static boolean show_number = false;

    static BufferedImage getMosaic(float seed) {
        BufferedImage buf = new BufferedImage(CANVASX_SIZE, CANVASY_SIZE, 2);
        Graphics2D g2 = buf.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(Color.black);

        g2.fillRect(0, 0, CANVASX_SIZE + cellw, CANVASY_SIZE + cellw);

        g2.setStroke(new BasicStroke(1));
        double angle = 0;
        float ratio = (float) 0.001;

        //generar nomes el quadradet dadalt a lesquerra 
        //i despres clonarlo per ocupar tot lespai
        for (int n = 0; n < new Float(Arab.CANVASX_SIZE).intValue(); n += cellw) {

            for (int m = 0; m < new Float(Arab.CANVASY_SIZE).intValue(); m += cellw) {

                int joe = (int) (seed * Math.cos(angle));
                angle += ratio;

                g2.setColor(Color.getHSBColor((m + n) / seed, (m + n) / seed, (m + n) / seed));
                //g2.setColor(Color.white);//getHSBColor((m + n) / seed, (m + n) / seed, (m + n) / seed));
                
                g2.setColor(Color.getHSBColor((m + n) / seed, (m + n) / seed, (m + n) / seed));
               
                g2.fillOval(n, m, cellw, cellw);
                int x = n + joe;
                int y = m + joe;

                g2.fillArc((int) (x+seed), y, cellw, cellw, 0, m);
                g2.fillArc((int)(x+seed), y, cellw, cellw, n, 0);

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

        Arab.show_number = false;
        if (d != null) {
            Arab.show_number = true;
        }

        if (mx != null) {
            try {
                Arab.CANVASX_SIZE = Integer.parseInt(mx);
            } catch (Exception e) {

                Arab.CANVASX_SIZE = 150;
            }
        }
        if (my != null) {
            try {
                Arab.CANVASY_SIZE = Integer.parseInt(my);
            } catch (Exception e) {

                Arab.CANVASY_SIZE = 150;
            }
        }
        if (cell
                != null) {
            try {
                Arab.cellw = Integer.parseInt(cell);
            } catch (Exception e) {

                Arab.cellw = 23;
            }

        }

        //coud be infinite of course
        if (Arab.counter
                > 0.3) {
            Arab.counter = 0;
        }
        Arab.counter += 0.001;

        // BufferedImage squared = generateSquared(Arab.counter);
        BufferedImage mixed = getMosaic(Arab.counter);
        mixed = spill(mixed);
        mixed = negativo(mixed);

        ImageIO.write(mixed, "png", response.getOutputStream());
    }

    public static BufferedImage negativo(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int rgb = image.getRGB(i, j);               //a cor inversa é dado por 255 menos o valor da cor                 
                int r = (int) ((rgb & 0xFF));
                int g = (int) ((rgb >> 8 & 0xFF));
                int b = (int) ((rgb >> 16 & 0xFF));
                Color color = new Color(r, g, b);
                image.setRGB(i, j, color.getRGB());
            }
        }
        return image;
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

    public static BufferedImage spill(BufferedImage image) {

        int width = image.getWidth() * 2;
        int height = image.getHeight() * 2;

        BufferedImage buf = new BufferedImage(width, height, 2);
        Graphics2D g2 = buf.createGraphics();

        g2.setColor(Color.BLACK);

        g2.fillRect(0, 0, width, height);

        for (int i = 0; i < width / 2; i++) {
            for (int j = 0; j < height / 2; j++) {
                int rgb = image.getRGB(i, j);

                Color color = new Color(rgb);
                g2.setColor(color);
                g2.fillRect(i, j, 1, 1);
                g2.fillRect(width - i - 1, j, 1, 1);
                g2.fillRect(width - i - 1, height - j - 1, 1, 1);
                g2.fillRect(i, height - j - 1, 1, 1);

            }
        }
        g2.setColor(Color.BLACK);
        if (Arab.show_number) {
            g2.drawString(Math.round(Arab.counter * 1000) + "", 0, Arab.CANVASY_SIZE * 2 - 4);
        }

        g2.dispose();
        return (buf);
    }
}
