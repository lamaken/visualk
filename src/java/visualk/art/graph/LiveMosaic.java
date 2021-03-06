package visualk.art.graph;

///control shift f
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.UUID;
import org.w3c.dom.Node;

import javax.imageio.*;
import javax.imageio.metadata.*;

import javax.imageio.stream.ImageOutputStream;
import visualk.art.Mixed;
import static visualk.art.Mixed.CANVASX_SIZE;
import static visualk.art.Mixed.CANVASY_SIZE;
import static visualk.art.Mixed.cellw;


import visualk.html5.UniqueName;

/**
 * Creates an animated GIF from GIF frames. A thin wrapper to code written by
 * other people, as documented on the thread on the Sun forums 'Create animated
 * GIF using imageio' http://forums.sun.com/thread.jspa?threadID=5395006 See the
 * printUsage() method for details on paramaters required.
 *
 * @author lamaken
 */
public class LiveMosaic {

    
    /**
     * See http://forums.sun.com/thread.jspa?messageID=10755673#10755673
     *
     * @author Maxideon
     * @param delayTime String Frame delay for this frame.
     */
    public static void configure(IIOMetadata meta,
            String delayTime,
            int imageIndex) {

        String metaFormat = meta.getNativeMetadataFormatName();

        if (!"javax_imageio_gif_image_1.0".equals(metaFormat)) {
            throw new IllegalArgumentException(
                    "Unfamiliar gif metadata format: " + metaFormat);
        }

        Node root = meta.getAsTree(metaFormat);

        //find the GraphicControlExtension node
        Node child = root.getFirstChild();
        while (child != null) {
            if ("GraphicControlExtension".equals(child.getNodeName())) {
                break;
            }
            child = child.getNextSibling();
        }

        IIOMetadataNode gce = (IIOMetadataNode) child;
        gce.setAttribute("userDelay", "FALSE");
        gce.setAttribute("delayTime", delayTime);

        //only the first node needs the ApplicationExtensions node
        if (imageIndex == 0) {
            IIOMetadataNode aes
                    = new IIOMetadataNode("ApplicationExtensions");
            IIOMetadataNode ae
                    = new IIOMetadataNode("ApplicationExtension");
            ae.setAttribute("applicationID", "NETSCAPE");
            ae.setAttribute("authenticationCode", "2.0");
            byte[] uo = new byte[]{
                //last two bytes is an unsigned short (little endian) that
                //indicates the the number of times to loop.
                //0 means loop forever.
                0x1, 0x0, 0x0
            };
            ae.setUserObject(uo);
            aes.appendChild(ae);
            root.appendChild(aes);
        }

        try {
            meta.setFromTree(metaFormat, root);
        } catch (IIOInvalidTreeException e) {
            //shouldn't happen
            throw new Error(e);
        }
    }

    /**
     * See http://forums.sun.com/thread.jspa?messageID=9988198
     *
     * @author GeoffTitmus
     * @param file File A File in which to store the animation.
     * @param frames BufferedImage[] Array of BufferedImages, the frames of the
     * animation.
     * @param delayTimes String[] Array of Strings, representing the frame delay
     * times.
     */
    public static void saveAnimate(
            File file,
            BufferedImage[] frames,
            String[] delayTimes) throws Exception {

        ImageWriter iw = ImageIO.getImageWritersByFormatName("gif").next();

        ImageOutputStream ios = ImageIO.createImageOutputStream(file);
        iw.setOutput(ios);
        iw.prepareWriteSequence(null);

        for (int i = 0; i < frames.length; i++) {
            BufferedImage src = frames[i];

            ImageWriteParam iwp = iw.getDefaultWriteParam();

            IIOMetadata metadata = iw.getDefaultImageMetadata(
                    new ImageTypeSpecifier(src), iwp);

            configure(metadata, delayTimes[i], i);

            IIOImage ii = new IIOImage(src, null, metadata);

            iw.writeToSequence(ii, null);

        }

        iw.endWriteSequence();

        ios.close();

    }

    // ...
    /**
     * Dump the usage to the System.err stream.
     */
    public static void printUsage() {
        StringBuffer sb = new StringBuffer();
        String eol = System.getProperty("line.separator");
        sb.append("Usage: 2 forms each using 3 arguments");
        sb.append(eol);
        sb.append("1) output (animated GIF) file name");
        sb.append(eol);
        sb.append("2) input files (animation frames), separated by ','");
        sb.append(eol);
        sb.append("3) single frame rate, or comma separared list of frame rates");
        sb.append(eol);
        sb.append("java WriteAnimatedGif animate.gif frm1.gif,frm2.gif,..,frmN.gif 100");
        sb.append(eol);
        sb.append("java WriteAnimatedGif animate.gif frm1.gif,frm2.gif,..,frmN.gif 100,40,..,N");
        sb.append(eol);
        sb.append("The 2nd form must have exactly as many integers as there are frames.");
        sb.append(eol);
        sb.append("Frame rates are specified in increments of 1/100th second, NOT milliseconds.");
        sb.append(eol);

        System.err.print(sb);
    }

    /**
     * Checks that a String intended as a delayTime is an integer>0. If not,
     * dumps a warning message and the usage, then exits. If successful, returns
     * the String unaltered.
     */
    public static String checkDelay(String delay) {
        try {
            int val = Integer.parseInt(delay);
            if (val < 1) {
                System.err.println(
                        "Animation frame delay '"
                        + val
                        + "' is < 1!");
                printUsage();
                System.exit(1);
            }
        } catch (NumberFormatException nfe) {
            System.err.println(
                    "Could not parse '"
                    + delay
                    + "' as an integer.");
            printUsage();
            System.exit(1);
        }
        return delay;
    }

    public static BufferedImage getFace(float seed) {



        //seed=seed*new Float(Math.random()).intValue();
        BufferedImage buf = new BufferedImage(CANVASX_SIZE, CANVASY_SIZE, 2);
        Graphics2D g2 = buf.createGraphics();
        g2.clipRect(0, 0, CANVASX_SIZE + cellw, CANVASY_SIZE + cellw);

// g2.setColor(Color.CYAN);
//g2.fillRect(100, 100, CANVASX_SIZE-200, CANVASY_SIZE-200);
        for (int n = 0; n < new Float(CANVASX_SIZE / 2).intValue(); n += cellw) {
            for (int m = 0; m < new Float(CANVASY_SIZE / 2).intValue(); m += cellw) {
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(Color.getHSBColor((m*m + n*n) / 2 + seed, (m*m + n*n) / 2 + seed, (m*m + n*n) / 2 + seed));

                g2.fillRect(n - cellw, m - cellw, cellw * 2, cellw * 2);
                g2.fillRect(CANVASX_SIZE - n - cellw, m - cellw, cellw * 2, cellw * 2);
                g2.fillRect(n - cellw, Mixed.CANVASY_SIZE - m - cellw, cellw * 2, cellw * 2);
                g2.fillRect(CANVASX_SIZE - n - cellw, Mixed.CANVASY_SIZE - m - cellw, cellw * 2, cellw * 2);

            }
        }

        return (buf);
    }

    /**
     * Parse the arguments and if successful, attempt to write the animated GIF.
     */
    public static void main(String[] args) throws Exception {

        if (args.length != 3) {
            printUsage();
            System.exit(1);
        }

        // deal with the output file name
        File f = new File(args[0] + "-"+UUID.randomUUID()+"-" + new UniqueName(3).getName() + ".gif");

        // deal with the input file names
        String[] names = args[1].split(",");
        if (names.length < 2) {
            System.err.println("An animation requires 2 or more frames!");
            printUsage();
            System.exit(1);
        }
        BufferedImage[] frames = new BufferedImage[names.length];
        for (int ii = 0; ii < names.length; ii++) {
            Mixed.counter++;
            frames[ii] = getFace(Mixed.counter);
        }

        // deal with the frame rates
        String[] delays = args[2].split(",");
        // note: length of names, not delays
        String[] delayTimes = new String[names.length];
        if (delays.length != names.length) {
            System.err.println(delays.length
                    + " delays specified for "
                    + names.length
                    + " frames!");
            printUsage();
            System.exit(1);
        } else if (delays.length == 1) {
            for (int ii = 0; ii < delayTimes.length; ii++) {
                // fill all values with the single delayTime
                delayTimes[ii] = checkDelay(delays[0]);
            }
        } else {
            for (int ii = 0; ii < delayTimes.length; ii++) {
                delayTimes[ii] = checkDelay(delays[ii]);
            }
        }

        // save an animated GIF
        // saveAnimate(f, frames, delayTimes);
        File f2 = new File("/home/hrzmkr/facegen/mosaic.gif");
        //File f2 = new File("/Users/lamaken/NetBeansProjects/facegen/build/web/mosaic.gif");

        saveAnimate(f2, frames, delayTimes);
    }
}
