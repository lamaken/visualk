/*
 * Created on  23-feb-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package visualk.lmk.grf;

import visualk.lmk.res.obj.Button;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.net.URL;
import visualk.lmk.res.func.IFunctions;

public class Finestra {

    static Display myDisplay = null;
    public Image bmpWindow;
    public Image bmpWindowButtons;
    public Image bmpWindowButtonsSel;
    
    public int pwin;			   //punter al bloc grafic de la finestra }
//	!!		pfuncioDib funcdib;      //punter a una rutina de dibuix d'aquesta finestra }
//	!!		void *pfuncioRata si_click;    //que fa si s'apreta el ratoli en x,y }
    public boolean enable;          //si s'ha de visualitzar }
    public boolean potresize;       //si pot canviar de mida }
    public boolean potmoure;       //si pot canviar de lloc }
    public boolean potClose;
    public String titol;            //titol }
    
    
    
    public int x,  y;             //posicio de la finestra }
    public int mx,  my;           //midax i miday }
 
    private int antx,  anty;             //posicio anterior de la finestra }
    private int antmx,  antmy;           //midax i miday anterior de la finestra}
    private boolean isMaximized=false;
    public boolean isMinimized=false;
    
    
    //public Finestra SegWin;       //punter a la seguent finestra }
    
    public int id;                 //identificador }
   

	public int seed;                //codi original quan es crea
    private IFunctions funcWin = null;
    
    private boolean overClose = false;
    private boolean clicClose = false;
    private boolean overMax = false;
    private boolean clicMax = false;
    private boolean overMin = false;
    private boolean clicMin = false;
    
    
    public IFunctions getFuncWin(){
    	return(funcWin);
    }
    
    public Button taskButton;//es el boto de la barra de tasques
    
    public void releaseMouse(MouseEvent e){
        clicMin=false;
        clicMax=false;
        overMin = false;
        overMax = false;
        //this.funcWin.mousePressed(e);
    }
    
    public void minimize(){
    	isMinimized=!isMinimized;
    	enable = !isMinimized;
    }
    public void maximize(int maxx,int maxy){
        if(isMaximized){
            x=antx;
            y=anty;
            mx=antmx;
            my=antmy;
        }else{
            antx=x;
            anty=y;
            antmx=mx;
            antmy=my;
            x=0;
            y=0;
            mx=maxx;
            my=maxy;
        }
        isMaximized=!isMaximized;
        
    }
    public void runApp() {
        this.funcWin.run();
    }

    public int si_over(MouseEvent event) {
        int c = this.funcWin.mouseMoved(event);
        if(c!=-1)return(c);
        return (0);
    }

    public int si_clickUp(MouseEvent event) {

        return (this.funcWin.mousePressed(event));
    }

    public int si_click(MouseEvent event) {
        
    	/* no deixem que la tanqui
    	if(siDinsFin(event.getX(), event.getY(), "close")){
            clicClose=true;
        }else clicClose=false;
        */
        
        if(siDinsFin(event.getX(), event.getY(), "max")){
            clicMax=true;
        }else clicMax=false;
        
        if(siDinsFin(event.getX(), event.getY(), "min")){
            clicMin=true;
        }else clicMin=false;
        
        return (this.funcWin.mousePressed(event));
    }
    //metode que pinta una regi� de la finestra
    public int paintWindowApp(Graphics g) {
        this.funcWin.paint(g, this.x + 7, this.y + 22, this.mx - 14, this.my - 29);
        return (0);
    }
    //metode inicializacio de l'aplicacio per a la finestra
    public int initApplication() {
        this.funcWin.init();
        return (0);
    }

    private void LoadBMPWindow() {
        System.out.print("Loading window bmp");
        URL url;
        if (this.potresize) {
            url = this.getClass().getResource("../res/img/ResizeWin.png");
        } else {
            url = this.getClass().getResource("../res/img/Win.png");
        }
        bmpWindow = Toolkit.getDefaultToolkit().getImage(url);
        if (bmpWindow == null) {
            System.out.println("... error");
        } else {
            System.out.println("... ok");
        }
        System.out.print("Loading window buttons");
        URL url2 = this.getClass().getResource("../res/img/MinClose.png");
        bmpWindowButtons = Toolkit.getDefaultToolkit().getImage(url2);
        if (bmpWindowButtons == null) {
            System.out.println("... error");
        } else {
            System.out.println("... ok");
        }
        
        System.out.print("Loading window Selected buttons");
        URL url3 = this.getClass().getResource("../res/img/SelMinClose.png");
        bmpWindowButtonsSel = Toolkit.getDefaultToolkit().getImage(url3);
        if (bmpWindowButtonsSel == null) {
            System.out.println("... error");
        } else {
            System.out.println("... ok");
        }
    }

    
    public Finestra() {
        System.out.print("Es crida al constructor de la finestra.\n");
    //this.inicialitza(10, 10, 10, 10, "no title");
    }

    public Finestra(IFunctions funcWin, int px, int py, int mmx, int mmy, String cad, boolean e, boolean r, boolean m,boolean c) {
        System.out.print("Es crea una finestra inicialitzada.\n");
       
       
        this.potClose=c;      
		this.funcWin = funcWin;
        this.enable = e;
        this.potresize = r;
        this.potmoure = m;
        this.taskButton = new Button(cad);

        this.inicialitza(px, py, mmx, mmy, cad);
    }
    //dibuixa la finestra en pwin }
    public void dibuixa_fin(Graphics g) {

        if (this.enable) {
            if (this.y > 600) {
                y = 600;
            }
            if (this.y < 2) {
                y = 2;            //fondo
            }
            
            g.clipRect(x, y, mx, my);
            
            myDisplay.Bar(g, this.x + 2, this.y + 2, this.mx - 3, this.my - 3, Color.BLACK);


            myDisplay.Rect(g, this.x + 2, this.y + 2, this.mx - 3, this.my - 3, Color.WHITE);
            myDisplay.Rect(g, this.x + 3, this.y + 3, this.mx - 4, 20, Color.WHITE);

            
            BufferedImage mybuffer = new BufferedImage(128, 128, 1);


          
            Graphics g2 = mybuffer.createGraphics();
            g2.drawImage(bmpWindow, 0, 0, null);
            g2.dispose();

            int pixX, pixY;
            pixX = 25;
            pixY = 25;


            BufferedImage c1 = mybuffer.getSubimage(0, 0, pixX, pixY);
            BufferedImage c2 = mybuffer.getSubimage(pixX + 5, 0, 1, pixY);
            BufferedImage c3 = mybuffer.getSubimage(128 - pixX, 0, pixX, pixY);
            BufferedImage c4 = mybuffer.getSubimage(128 - pixX, pixY + 5, pixX, 1);
            BufferedImage c5 = mybuffer.getSubimage(128 - pixX, 128 - pixY, pixX, pixY);
            BufferedImage c6 = mybuffer.getSubimage(pixX + 5, 128 - pixY, 1, pixY);
            BufferedImage c7 = mybuffer.getSubimage(0, 128 - pixY, pixX, pixY);
            BufferedImage c8 = mybuffer.getSubimage(0, pixY + 5, pixX, pixY);
            int xx, yy;


            for (xx = 1; xx < mx; xx++) {
                g.drawImage(c2, xx + x, y, 1, pixY, null);
            }
            for (yy = 1; yy < my; yy++) {
                g.drawImage(c4, mx + x - pixX, yy + y, pixY, 1, null);
            }
            for (xx = pixX; xx < mx; xx++) {
                g.drawImage(c6, xx + x - pixX, y + my - pixY, 1, pixY, null);
            }
            for (yy = pixY; yy < my; yy++) {
                g.drawImage(c8, x, yy + y - pixY, pixX, 1, null);
            }


            g.drawImage(c1, x, y, pixX, pixY, null);
            g.drawImage(c3, mx + x - pixX, y, pixX, pixY, null);
            g.drawImage(c5, mx + x - pixX, my + y - pixY, pixX, pixY, null);
            g.drawImage(c7, x, my + y - pixY, pixX, pixY, null);



            BufferedImage mybuffer2 = new BufferedImage(85, 15, 1);
            Graphics g3 = mybuffer2.createGraphics();
            g3.drawImage(bmpWindowButtons, 0, 0, null);
            g3.dispose();

            BufferedImage bMinNo = mybuffer2.getSubimage(0, 2, 13, 11);
            BufferedImage bMinYes = mybuffer2.getSubimage(14, 2, 13, 11);
            BufferedImage bMaxNo = mybuffer2.getSubimage(28, 2, 13, 11);
            BufferedImage bMaxYes = mybuffer2.getSubimage(42, 2, 13, 11);
            BufferedImage bCloseNo = mybuffer2.getSubimage(56, 2, 13, 11);
            BufferedImage bCloseYes = mybuffer2.getSubimage(70, 2, 13, 11);
            
            BufferedImage mybufferSel = new BufferedImage(85, 15, 1);
            Graphics g4 = mybufferSel.createGraphics();
            g4.drawImage(bmpWindowButtonsSel, 0, 0, null);
            g4.dispose();

            BufferedImage bMinNoS = mybufferSel.getSubimage(0, 2, 13, 11);
            BufferedImage bMaxNoS = mybufferSel.getSubimage(28, 2, 13, 11);
            BufferedImage bCloseNoS = mybufferSel.getSubimage(56, 2, 13, 11);
            
            
            
            BufferedImage bClose=null;
            BufferedImage bMin=null;
            BufferedImage bMax=null;
            
            
            if(potClose){
            if (overClose) bClose=bCloseNoS;
            else bClose=bCloseNo;
            if (clicClose) bClose=bCloseYes;
            }
            
            if (overMax) bMax=bMaxNoS;
            else bMax=bMaxNo;
            if (clicMax) bMax=bMaxYes;
           
            if (overMin) bMin=bMinNoS;
            else bMin=bMinNo;
            if (clicMin) bMin=bMinYes;
            
            
            int posx = 0;
            for (int kx = 0; kx < 6; kx++) {
                posx = mx + x - 90 + kx * 14;
                if ((kx == 3) && (this.potresize)) {
                    g.drawImage(bMin, posx, y + 7, null);
                }
                if ((kx == 4) && (this.potresize)) {
                    g.drawImage(bMax, posx, y + 7, null);
                }
                if (kx == 5) {
                   if(potClose) 
                	   g.drawImage(bClose, posx, y + 7, null);
                }
            
            }



            //title

            if (this.id == 0) {
                myDisplay.writeText(g, Color.WHITE, titol, x + 15, y + 16);
            } else {
                myDisplay.writeText(g, Color.DARK_GRAY, titol , x + 15, y + 16);
            }
        
        }
        g.setClip(null);
        paintWindowApp(g);//dibuixem lo tipic de l'aplicacio
        

    }
    ;
    //inicialitza l'estructura de la finestra.
    public void inicialitza(int px, int py, int mmx, int mmy, String cad) {
        if (myDisplay == null) {
            myDisplay = new Display();
        }
        
        LoadBMPWindow(); //carguem el dibuix de la finestra
        if (px < 0) {
            px = 0;
        }
        if (py < 0) {
            py = 0;
        }
        if (mmx > 1024) {
            mmx = 1024;
        }
        if (mmy > 768) {
            mmy = 768;
        }
        this.x = px;
        this.y = py;
        this.mx = mmx;
        this.my = mmy;
        this.antx = px;
        this.anty = py;
        this.antmx = mmx;
        this.antmy = mmy;
        
        this.titol = cad;
        //inciializa aplicacio
        initApplication();
    }
    public long posw;               //posicio finestra posw=1->devant }

    public void mou_fin(int newx, int newy) {
        if (this.potmoure) {
            x = newx;
            y = newy;
        }
    }      //{ mou la finestra a newx newy }

    public void resize(int newmx, int newmy) {
        if (this.potresize) {
            if (newmx > 150) {
                this.mx = newmx;
            } else {
                mx++;
            }
            if (newmy > 50) {
                this.my = newmy;
            } else {
                my++;
            }
        }
    }
    ; //{ canvia la mida }

    public boolean siDinsFin(int rx, int ry, String cad) {//{ retona true si x,y es troba dins de la finestra}

        
        overClose=false;
        overMax=false;
        overMin=false;
        
        if (cad.equals("finestra")) {
            if ((rx > x) && (rx < x + mx) && (ry > y) && (ry < y + my)) {
                return true;
            } else {
                return false;
            }
        }
        if (cad.equals("titol")) {
            if ((rx + 1 > x) && (rx + 1 < x + mx - 49) && (ry + 1 > y) && (ry - 1 < y + 16)) {
                return true;
            } else {
                return false;
            }
        }

        if (cad.equals("resize")) {
            if ((rx > x + mx - 10) && (rx < x + mx) && (ry > y + my - 10) && (ry < y + my)) {
                return true;
            } else {
                return false;
            }
        }

        if (cad.equals("close")&&(potClose)) {
            if ((rx + 1 > x + mx - 15) && (rx < x + mx-8) && (ry + 1 > y+5) && (ry - 1 < y + 16)) {
                overClose=true;
                return true;
            } else
                return false;
        }
         if (cad.equals("max")) {
            if ((rx + 1 > x + mx - 15-15) && (rx < x + mx-8-15) && (ry + 1 > y+5) && (ry - 1 < y + 16)) {
                overMax=true;
                return true;
            } else
                return false;
        }
        if (cad.equals("min")) {
            if ((rx + 1 > x + mx - 15-15-15) && (rx < x + mx-8-15-15) && (ry + 1 > y+5) && (ry - 1 < y + 16)) {
                overMin=true;
                return true;
            } else
                return false;
        }


        return false;
    }
}
