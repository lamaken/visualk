/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualk.art;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Rounded extends Mosaic {
    
    public static float counter = 0;
    public static boolean show_number = false;
    
    public static Integer CANVASX_SIZE = 100;
    public static Integer CANVASY_SIZE = 100;
    public static Integer cellw = 10;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("image/PNG");

        String mx = request.getParameter("mx");
        String my = request.getParameter("my");
        String cell = request.getParameter("cellw");
        String d = request.getParameter("d");

        Rounded.show_number=false;
        if (d != null) {
            Rounded.show_number = true;
        }

        if (mx != null) {
            try {
                Rounded.CANVASX_SIZE = Integer.parseInt(mx);
            } catch (Exception e) {

                Rounded.CANVASX_SIZE = 150;
            }
        }
        if (my != null) {
            try {
                Rounded.CANVASY_SIZE = Integer.parseInt(my);
            } catch (Exception e) {

                Rounded.CANVASY_SIZE = 150;
            }
        }
        if (cell
                != null) {
            try {
                Rounded.cellw = Integer.parseInt(cell);
            } catch (Exception e) {

                Rounded.cellw = 23;
            }

        }

        //coud be infinite of course
        if (Rounded.counter
                > 0.3) {
            Rounded.counter = 0;
        }
        Rounded.counter += 0.001;
        BufferedImage rounded = generateRounded(Rounded.counter);
        rounded = textura(negativo(rounded));//TODO:ASACO
        ImageIO.write(rounded, "png", response.getOutputStream());

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

   
    public BufferedImage generateRounded(float seed) {

        BufferedImage buf = new BufferedImage(CANVASX_SIZE, CANVASY_SIZE, 2);
        Graphics2D g2 = buf.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(Color.red);

        g2.fillRect(0, 0, CANVASX_SIZE + cellw, CANVASY_SIZE + cellw);

        for (int n = 0; n < new Float(Rounded.CANVASX_SIZE / 2).intValue() + 2; n += cellw) {
            for (int m = 0; m < new Float(Rounded.CANVASY_SIZE / 2).intValue() + 2; m += cellw) {
                g2.setColor(Color.getHSBColor((m + n) / seed, (m + n) / seed, (m + n) / seed));
                g2.fillOval(n - cellw, m - cellw, cellw * 2, cellw * 2);
                g2.fillOval(Rounded.CANVASX_SIZE - n - cellw, m - cellw, cellw * 2, cellw * 2);
                g2.fillOval(n - cellw, Rounded.CANVASY_SIZE - m - cellw, cellw * 2, cellw * 2);
                g2.fillOval(Rounded.CANVASX_SIZE - n - cellw, Rounded.CANVASY_SIZE - m - cellw, cellw * 2, cellw * 2);
            }
        }

        //buf = negativo(buf);
        //buf = desaturate(buf);
        g2.setColor(Color.BLACK);
        if (Rounded.show_number) {
            g2.drawString(Math.round(Rounded.counter*1000)+"", 0, Rounded.CANVASY_SIZE - 4);
        }

        g2.dispose();
        return (buf);
    }

    

    

}
