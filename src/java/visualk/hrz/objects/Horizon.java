package visualk.hrz.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import java.io.Serializable;

import java.net.URL;

import java.util.Random;
import javax.imageio.ImageIO;
import visualk.Main;

import visualk.hrz.db.DbHorizons;
import visualk.html5.UniqueName;

public class Horizon implements Serializable {

    private static final String URL_IMG = Main.HOST_NAME + Main.HOST_VISUALK + "/hrz/img/";
    //private static final String URL_IMG = "http://hrzmkr.com/img/";

    /**
     *
     */
    private static final long serialVersionUID = 24L;
    private static final String HRZMKR_VERSION = "alk@soft.hrzmkr v0.4";//SIZE 20

    private final static int MAX_WIDTH = 151;
    private final static int MAX_HEIGTH = 303;

    private int max_width = MAX_WIDTH;
    private int max_height = MAX_HEIGTH;

    private final static int MIN_WIDTH = 310;
    private final static int MIN_HEIGTH = 310;

    private Image bmpSuperNova;
    private Image bmpTextura;
    private Image bmpCel;

    private String version = HRZMKR_VERSION;
    private String nameHrz_codiUnicIdentificador = "void";
    private int topHrz = 250;
    private Color topHrzColor = Color.red;
    private Color bottomHrzColor = Color.blue;
    private int canvasWidth = 150;
    private int canvasHeigth = 50;
    private String authorHrz_texteFooter = "auto-author Hrz_texteFooter";
    private int xPal, yPal = 100;
    private int hPalx, hPaly = 200;
    private int alcada = 200;
    private Color colPal = Color.red;
    private boolean horizontal = true;
    private boolean aureaProp = false;

    private int superX = 150;
    private int superY = 50;
    private boolean textura = true;

    private DbHorizons db = new DbHorizons();

    public void save2Db(String name) {
        name = name.replace("'", "`");
        name = name.replace("\"", "`");
        setNameHrz(new UniqueName(8).getName());
        setAuthorHrz(name);
        db.addHorizon(this, name);
    }

    public boolean isAurea() {
        return (aureaProp);
    }

    public void setAureaProp(boolean sino) {
        this.aureaProp = sino;
    }

    public void setHorizontal() {
        horizontal = true;
    }

    public void setvertical() {
        horizontal = false;
    }

    public Horizon(String name) {
        this(name,MAX_WIDTH,MAX_HEIGTH);
        
    }

    public Horizon(String name, int mx, int my) {
        
        System.out.println("Creating a new Horizon, name:"+name);
        
        this.nameHrz_codiUnicIdentificador = name;
        max_width = mx;
        max_height = my;

        this.version = HRZMKR_VERSION;

        loadSuperNova();// carreguem imatge de la llum

        loadTextura();// carreguem textura del terra

        loadCel();// carreguem textura del cel
        
        makeRandom(mx,my);//si es penja com a minim que sigui bonics
    }

    private int getAureo(int mida) {
        return ((int) (mida * 0.618033988272397));
    }

    public void carrega(String nom) {

        Horizon tmp=null;
        try{
            tmp = db.getHrznBD(nom);
        }catch (Exception e){
            tmp = new Horizon(new UniqueName(8).getName(),350,250);            
            tmp.authorHrz_texteFooter="eps! "+e.getMessage();
        }

        this.nameHrz_codiUnicIdentificador = tmp.nameHrz_codiUnicIdentificador;
        this.topHrz = tmp.topHrz;
        this.topHrzColor = tmp.topHrzColor;
        this.bottomHrzColor = tmp.bottomHrzColor;
        this.canvasWidth = tmp.canvasWidth;
        this.canvasHeigth = tmp.canvasHeigth;
        this.authorHrz_texteFooter = tmp.authorHrz_texteFooter;
        this.xPal = tmp.xPal;
        this.yPal = tmp.yPal;
        this.hPalx = tmp.hPalx;
        this.hPaly = tmp.hPaly;
        this.alcada = tmp.alcada;
        this.colPal = tmp.colPal;
        this.horizontal = tmp.horizontal;
        this.aureaProp = tmp.aureaProp;
        this.version = tmp.version;
        this.superX = tmp.superX;
        this.superY = tmp.superY;
        this.textura = tmp.textura;

    }

    public void saveToFile(String name) {
        save2Db(name);

    }

    public void makeRandomCanvas(int mx, int my) {
        // Random r = new Random();

        this.canvasHeigth = my;//;r.nextInt(max_height);
        this.canvasWidth = mx;//r.nextInt(max_width);

        System.out.println("rw:" + this.canvasWidth + " rh:"
                + this.canvasHeigth);
    }

    public void makeRandomCanvas(int maxx, int maxy, int minx, int miny) {
        Random r = new Random();
        this.canvasWidth = maxx;
        this.canvasHeigth = maxy;
    }

    public void makeRandomSuperNova() {
        Random r = new Random();
        try {
            this.superX = r.nextInt(this.canvasWidth);
            this.superY = r.nextInt(this.topHrz);
        } catch (Exception e) {
            this.superX = this.canvasWidth / 2;
            this.superY = this.topHrz / 2;
        }
        makeRandomHombra();

    }

    public void makeRandomTextura() {
        //Random r = new Random();
        //this.textura = (r.nextInt(2) == 1);
        this.textura = true;
    }

    public void makeRandomAureo() {
        Random r = new Random();
        // nuemero aureo
        //this.aureaProp = (r.nextInt(2) == 1);
        this.aureaProp = true;
        System.out.println("makeRandomAureo");

    }

    public void makeRandomHorizontal() {
        Random r = new Random();

        this.horizontal = (r.nextInt(2) == 1);
        System.out.println("makeRandomHorizontal");
    }

    public void makeRandomAlçadaHoritzo() {
        Random r = new Random();
        if(isAureaProp()) this.topHrz = getAureo(r.nextInt(this.canvasHeigth));
        else this.topHrz = r.nextInt(this.canvasHeigth);
        System.out.println("makeRandomAlçadaHoritzo");
    }

    public void makeRandomPal() {
        Random r = new Random();

        this.xPal = getAureo(r.nextInt(this.canvasWidth));
        this.yPal = this.topHrz + getAureo(r.nextInt(this.canvasHeigth - this.topHrz));
        this.alcada = getAureo(r.nextInt(this.canvasHeigth - this.yPal));

        makeRandomHombra();
    }

    public void makeRandomHombra() {
        if (superX > xPal) {
            hPalx = xPal - (superX - xPal);
        }
        if (superX <= xPal) {
            hPalx = xPal + (xPal - superX);
        }
        this.hPaly = yPal + 15;//this.canvasHeigth - 50;
        
        System.out.println("makeRandomHombra");
        }
        
    public void makeRandomColors() {
        Random r = new Random();

        // colors
        this.colPal = new Color(r.nextInt(255 * 255 * 255));
        this.topHrzColor = new Color(r.nextInt(255 * 255 * 255));
        this.bottomHrzColor = new Color(r.nextInt(255 * 255 * 255));
    }

    public void makeRandom(int mx, int my) {
        makeRandomTextura();
        makeRandomHorizontal();
        makeRandomCanvas(mx, my);
        makeRandomAlçadaHoritzo();
        makeRandomPal();
        makeRandomSuperNova();
        makeRandomColors();

        if (isAurea()) {
            setAuthorHrz("* " + this.version);
        } else {
            setAuthorHrz("" + this.version);
        }
        System.out.println("makeRandom");
    }

    public BufferedImage getHrzSmallImage(int w, int h) {
        BufferedImage buf = this.getHrzImage();
        float mx = 0, my = 0;
        int mmy = 0, mmx = 0;

        mx = this.getCanvasWidth();
        my = this.getCanvasHeigth();
        if (mx > my) {
            mmx = w;
            mmy = Math.round((my / mx) * w);
        }
        if (mx <= my) {
            mmx = Math.round((mx / my) * w);
            mmy = h;
        }
        ;

        // TODO:DEBUG
        System.out.println("mx:" + mx + " my:" + my + " mmx:" + mmx + " mmy:"
                + mmy);

        Image img = buf
                .getScaledInstance(mmx, mmy, BufferedImage.SCALE_DEFAULT);

        /*if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }*/
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img
                .getHeight(null), 1);
        Graphics g = bimage.createGraphics();
        g.drawImage(img, 0, 0, null);
        g.dispose();

        return (bimage);
    }

    private void loadSuperNova() {
        System.out.print("Loading supernova...");
        
        try {
            URL url = new URL(URL_IMG + "llum2.png");
            
            if (bmpSuperNova == null) {
                bmpSuperNova = ImageIO.read(url.openStream());
                System.out.println("... idle");
            } else {
                System.out.println("... ok");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.print(".. ERROR url.");
            e.printStackTrace();
        }

    }

    private void loadTextura() {
        System.out.print("Loading textura");

      
        try {
            URL url = new URL(URL_IMG + "textura.png");
            

            if (bmpTextura == null) {
                bmpTextura = ImageIO.read(url.openStream());
                System.out.println("... idle.");
            } else {
                System.out.println("... ok");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.print(".. ERROR url.");
        }
    }

    private void loadCel() {
        System.out.print("Loading cel");

        
        try {
            URL url = new URL(URL_IMG + "celgran.png");
            

            if (bmpCel == null) {
                bmpCel = ImageIO.read(url.openStream());
                System.out.println("... idle");
            } else {
                System.out.println("... ok");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.print(".. ERROR url.");
            e.printStackTrace();
        }
    }

    public BufferedImage getHrzImage() {

        int mx = this.getCanvasWidth();
        int my = this.getCanvasHeigth();

        int ratiox = mx / 200;
        int ratioy = my / 200;
        int nx, ny = 0;

        BufferedImage buf = new BufferedImage(this.getCanvasWidth(), this.getCanvasHeigth() + 21, 1);
        Graphics2D g2 = buf.createGraphics();

        g2.clipRect(0, 0, this.getCanvasWidth(), this.getCanvasHeigth() + 21);

        //cel
        g2.setColor(this.getTopHrzColor());
        g2.fillRect(0, 0, this.getCanvasWidth(), this.getTopHrz());

        if (bmpCel == null) {
            System.out.println("\nreloading images");
            loadCel();
            loadSuperNova();
            loadTextura();
        }

        g2.drawImage(bmpCel, 0, 0, mx, my, null);

        // posem la llum
        g2.drawImage(bmpSuperNova, this.superX - 100, this.superY - 100, null);

        // posem el terra
        g2.setColor(this.getBottomHrzColor());
        g2.fillRect(0, this.getTopHrz(), this.getCanvasWidth(), this
                .getCanvasHeigth()
                - this.getTopHrz());
        // posem la textura per tot el terra

        for (nx = 0; nx <= ratiox; nx++) {
            for (ny = 0; ny <= ratioy; ny++) {
                if (this.isTextura()) {
                    g2.drawImage(bmpTextura, nx * 200, this.getTopHrz()
                            + (ny * 200), null);
                }
            }
        }

        //cenit
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(getBottomHrzColor());
        g2.drawLine(0, this.topHrz, this.getCanvasWidth(), this.topHrz);

        //ombra antiga
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.darkGray);
        g2.drawLine(this.getxPal(), this.getyPal(), this
                .gethPalx(), this.gethPaly());
        g2.drawLine(this.getxPal() + 1, this.getyPal(), this
                .gethPalx() + 1, this.gethPaly());

        // pal
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);

        Color c1 = Color.white,
                c2 = Color.darkGray;

        if (superX >= xPal) {
            c1 = Color.darkGray;
            c2 = Color.white;
        }

        g2.setColor(c1);//

        g2.drawLine(this.getxPal(), this.getyPal(), this
                .getxPal(), this.getyPal()
                - this.getAlçada());
        g2.setColor(c2);//

        g2.drawLine(this.getxPal() + 1, this.getyPal(), this
                .getxPal() + 1, this.getyPal()
                - this.getAlçada());

// firma
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        g2.setColor(Color.white);
        g2.fillRect(0, this.getCanvasHeigth() + 1, this.getCanvasWidth(), 20);
        g2.setColor(Color.gray);
        g2.drawString(this.authorHrz_texteFooter, 2, this.getCanvasHeigth() + 15);

        g2.drawRect(0, 0, this.getCanvasWidth() - 1, this.getCanvasHeigth() + 20);
        g2.dispose();

        return (buf);
    }

    public String getNameHrz() {
        return nameHrz_codiUnicIdentificador;
    }

    public void setNameHrz(String nameHrz) {
        this.nameHrz_codiUnicIdentificador = nameHrz;
    }

    public int getTopHrz() {
        return topHrz;
    }

    public void setTopHrz(int topHrz) {
        this.topHrz = topHrz;
    }

    public Color getTopHrzColor() {
        return topHrzColor;
    }

    public void setTopHrzColor(Color topHrzColor) {
        this.topHrzColor = topHrzColor;
    }

    public Color getBottomHrzColor() {
        return bottomHrzColor;
    }

    public void setBottomHrzColor(Color bottomHrzColor) {
        this.bottomHrzColor = bottomHrzColor;
    }

    public int getCanvasWidth() {
        return canvasWidth;
    }

    public void setCanvasWidth(int canvasWidth) {
        this.canvasWidth = canvasWidth;
    }

    public int getCanvasHeigth() {
        return canvasHeigth;
    }

    public void setCanvasHeigth(int canvasHeigth) {
        this.canvasHeigth = canvasHeigth;
    }

    public String getAuthorHrz() {
        return authorHrz_texteFooter;
    }

    public void setAuthorHrz(String authorHrz) {
        this.authorHrz_texteFooter = authorHrz;
    }

    public int getxPal() {
        return xPal;
    }

    public void setxPal(int xPal) {
        this.xPal = xPal;
    }

    public int getyPal() {
        return yPal;
    }

    public void setyPal(int yPal) {
        this.yPal = yPal;
    }

    public int getAlçada() {
        return alcada;
    }

    public void setAlcada(int alçada) {
        this.alcada = alçada;
    }

    public Color getColPal() {
        return colPal;
    }

    public void setColPal(Color colPal) {
        this.colPal = colPal;
    }

    public int gethPalx() {
        return hPalx;
    }

    public void sethPalx(int hPalx) {
        this.hPalx = hPalx;
    }

    public int gethPaly() {
        return hPaly;
    }

    public void sethPaly(int hPaly) {
        this.hPaly = hPaly;
    }

    @Deprecated
    public String getSVG() {

        String palcolor = "rgb(" + this.getColPal().getRed() + ","
                + this.getColPal().getGreen() + ","
                + this.getColPal().getBlue() + ")";

        String topcolor = "rgb(" + this.getTopHrzColor().getRed() + ","
                + this.getTopHrzColor().getGreen() + ","
                + this.getTopHrzColor().getBlue() + ")";

        String bottomcolor = "rgb(" + this.getBottomHrzColor().getRed() + ","
                + this.getBottomHrzColor().getGreen() + ","
                + this.getBottomHrzColor().getBlue() + ")";

        String xml = "<svg style=\"text-align:center\" width=\"100%\" height=\"100%\" version=\"1.1\""
                + "     xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\">"
                + " <desc>hrzmkr.com</desc>"
                + "<rect x=\"0\" y=\"0\" width=\""
                + this.canvasWidth
                + "\" height=\""
                + this.getTopHrz()
                + "\""
                + "      fill=\""
                + topcolor
                + "\" stroke=\"white\"  stroke-width=\".13\"/>"
                + "<rect x=\"0\" y=\""
                + this.getTopHrz()
                + "\" width=\""
                + this.canvasWidth
                + "\" height=\""
                + (this.canvasHeigth - this.getTopHrz())
                + "\" fill=\""
                + bottomcolor
                + "\" stroke=\"white\"  stroke-width=\".13\"/>"
                + "<line x1=\""
                + this.getxPal()
                + "\" y1=\""
                + (this.getyPal() + this.getTopHrz())
                + "\" x2=\""
                + this.gethPalx()
                + "\" y2=\""
                + (this.getTopHrz() + this.gethPaly())
                + "\""
                + " style=\"stroke:rgb(0,0,0);stroke-width:2\"/>"
                + "<line x1=\""
                + this.getxPal()
                + "\" y1=\""
                + (this.getyPal() + this.getTopHrz())
                + "\" x2=\""
                + this.getxPal()
                + "\" y2=\""
                + (this.getTopHrz() + this.getyPal() - this.getAlçada())
                + "\""
                + " style=\"stroke:" + palcolor + ";stroke-width:2\"/>"
                + "</svg>";

        /*
		 * //pal g2.setColor(this.getColPal()); g2.drawLine(this.getxPal(),
		 * this.getyPal()+this.getTopHrz(),
		 * this.getxPal(),this.getTopHrz()+this.getyPal()-this.getAlçada());
		 * g2.drawLine(this.getxPal()+1, this.getyPal()+this.getTopHrz(),
		 * this.getxPal()+1,this.getTopHrz()+this.getyPal()-this.getAlçada());
		 * 
		 * 
		 * 
		 * <line x1="0" y1="0" x2="300" y2="300"
		 * style="stroke:rgb(99,99,99);stroke-width:2"/>
         */
        return xml;
    }

    // iexplorer
    @Deprecated
    public String getVML() {
        String xml = "<html xmlns:v>"
                + "<body>"
                + "<v:oval style=\"left:0;top:0;width:100;height:50\" fillcolor=\"blue\" stroked=\"f\" />"
                + "</body>" + "</html>";
        return xml;
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getSuperX() {
        return superX;
    }

    public void setSuperX(int superX) {
        this.superX = superX;
    }

    public int getSuperY() {
        return superY;
    }

    public void setSuperY(int superY) {
        this.superY = superY;
    }

    public boolean isTextura() {
        return textura;
    }

    public void setTextura(boolean textura) {
        this.textura = textura;
    }

    public boolean isAureaProp() {
        return aureaProp;
    }

}
