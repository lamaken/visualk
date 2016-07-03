/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualk.art;

import java.awt.Color;
import java.awt.color.ColorSpace;

import java.awt.image.ColorConvertOp;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lamaken
 */
public class Mixed extends HttpServlet {

    public static float counter = 0;
    public static boolean show_number = false;

    public void copy(final InputStream in, final OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int count;

        while ((count = in.read(buffer)) != -1) {
            out.write(buffer, 0, count);
        }

        // Flush out stream, to write any remaining buffered data
        out.flush();
    }

    static public void step() {
        counter += 0.01;
        if (counter > 10.3) {
            counter = 0;
        }
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

        Mixed.show_number = false;
        if (d != null) {
            Mixed.show_number = true;
        }

        if (mx != null) {
            try {
                Mixed.CANVASX_SIZE = Integer.parseInt(mx);
            } catch (Exception e) {

                Mixed.CANVASX_SIZE = 150;
            }
        }
        if (my != null) {
            try {
                Mixed.CANVASY_SIZE = Integer.parseInt(my);
            } catch (Exception e) {

                Mixed.CANVASY_SIZE = 150;
            }
        }
        if (cell
                != null) {
            try {
                Mixed.cellw = Integer.parseInt(cell);
            } catch (Exception e) {

                Mixed.cellw = 23;
            }

        }

        //coud be infinite of course
        if (Mixed.counter
                > 0.3) {
            Mixed.counter = 0;
        }
        Mixed.counter += 0.001;

        // BufferedImage squared = generateSquared(Mixed.counter);
        BufferedImage mixed = generateMixed(Mixed.counter);

        //BufferedImage result = mix(rounded, squared);
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

    public static Integer CANVASX_SIZE = 100;
    public static Integer CANVASY_SIZE = 100;
    public static Integer cellw = 10;

    public BufferedImage generateMixed(float seed) {
        BufferedImage buf = new BufferedImage(CANVASX_SIZE, CANVASY_SIZE, 2);
        Graphics2D g2 = buf.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(Color.red);

        g2.fillRect(0, 0, CANVASX_SIZE + cellw, CANVASY_SIZE + cellw);
        int mod = 0;
        for (int n = 0; n < new Float(Mixed.CANVASX_SIZE / 2).intValue() + 2; n += cellw) {
            for (int m = 0; m < new Float(Mixed.CANVASY_SIZE / 2).intValue() + 2; m += cellw) {
                g2.setColor(Color.getHSBColor((m + n) / 2 + seed, (m + n) / 2 + seed, (m + n) / 2 + seed));
                if (mod++ % 2 != 0) {

                    g2.fillOval(n - cellw, m - cellw, cellw * 2, cellw * 2);
                    g2.fillOval(Mixed.CANVASX_SIZE - n - cellw, m - cellw, cellw * 2, cellw * 2);
                    g2.fillOval(n - cellw, Mixed.CANVASY_SIZE - m - cellw, cellw * 2, cellw * 2);
                    g2.fillOval(Mixed.CANVASX_SIZE - n - cellw, Mixed.CANVASY_SIZE - m - cellw, cellw * 2, cellw * 2);
                } else {
                    // g2.setColor(Color.getHSBColor((m + n) / 3 + seed, (m + n) / 3 + seed, (m + n) / 3 + seed));
                    g2.fillRect(n - cellw, m - cellw, cellw * 2, cellw * 2);
                    g2.fillRect(Mixed.CANVASX_SIZE - n - cellw, m - cellw, cellw * 2, cellw * 2);
                    g2.fillRect(n - cellw, Mixed.CANVASY_SIZE - m - cellw, cellw * 2, cellw * 2);
                    g2.fillRect(Mixed.CANVASX_SIZE - n - cellw, Mixed.CANVASY_SIZE - m - cellw, cellw * 2, cellw * 2);
                }
            }
        }
        g2.setColor(Color.BLACK);
        if (Mixed.show_number) {
            g2.drawString(Math.round(Mixed.counter*1000)+"", 0, Mixed.CANVASY_SIZE - 4);
        }
        g2.dispose();
        return (buf);
    }


    public static BufferedImage desaturate(BufferedImage source) {
        ColorConvertOp colorConvert = new ColorConvertOp(ColorSpace
                .getInstance(ColorSpace.CS_GRAY + 1), null);
        colorConvert.filter(source, source);

        return source;
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

}
