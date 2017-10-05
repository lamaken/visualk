/*
 * Copyright (C) 2017 lamaken
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package visualk.art;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.http.HttpServlet;

class Mosaic extends HttpServlet {

    static Double step(Double counter) {
        counter += 0.01;
        if (counter > 10.3) {
            counter = Double.parseDouble("0L");
        }
        return counter;
    }

    public BufferedImage negativo(BufferedImage image) {
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

    public BufferedImage textura(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int rgb = image.getRGB(i, j);               //a cor inversa é dado por 255 menos o valor da cor                 
                //Color mycolor = new Color((i*j)+rgb);
                Color mycolor = new Color(rgb);
                image.setRGB(i, j, mycolor.getRGB());
            }
        }
        return image;
    }

    public BufferedImage spill(BufferedImage image) {

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

        g2.dispose();
        return (buf);
    }
    
    public void copy(final InputStream in, final OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int count;

        while ((count = in.read(buffer)) != -1) {
            out.write(buffer, 0, count);
        }

        // Flush out stream, to write any remaining buffered data
        out.flush();
    }
    
    public static BufferedImage desaturate(BufferedImage source) {
        ColorConvertOp colorConvert = new ColorConvertOp(ColorSpace
                .getInstance(ColorSpace.CS_GRAY + 1), null);
        colorConvert.filter(source, source);

        return source;
    }

}
