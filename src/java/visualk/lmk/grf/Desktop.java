/*
 * Created on 23-feb-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package visualk.lmk.grf;

import java.awt.*;
import java.net.URL;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import visualk.lmk.debug.Debug;

public class Desktop extends JPanel implements MouseMotionListener, MouseListener, Runnable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5903586521379873675L;
	//propietats
    public static Debug myDebug;
    public static BufferedImage vpage;
    public static Graphics miCG;
    public static Image bmp_fondo;
    private int SCREENX,  SCREENY;


    private static LinkedList<Finestra> finestres;
    
    private static Thread thread;
    private static int ratx,  raty;
    
    private static boolean onDragMove = false;
    private static boolean onDragResize = false;
    private static boolean onDragClick = false;
    private static boolean onDragClose = false;
    private static boolean onDragMin = false;
    private static boolean onDragMax = false;
    
    
    private static int CLOSE_WIN = -1;
    
    private static int idSelected = 0;
    
    
    
    private FinestresBar finestresBar;
    
            
    //m�todes
    public void outmsg(String msg) {
        myDebug.outText(0, msg);
    }

    public void outmsgErr(String msg) {
        myDebug.outText(-1, msg);
    }

    public void showBuffer() {
        miCG.drawImage(vpage, 0, 0, null);
    }

    public Desktop(int mmx, int mmy) {
        myDebug = new Debug(false);
        finestres = new LinkedList<Finestra>();
        finestresBar = new FinestresBar();
        addMouseListener(this);
        addMouseMotionListener(this);
        Inicialitza(mmx, mmy);
        vpage = new BufferedImage(mmx, mmy, 1);
       
        miCG = vpage.createGraphics();
        if (miCG == null) {
            outmsg("error get context");
            System.exit(0);
        }
      
    }

    public void start() {
        thread = new Thread(this);
        thread.setPriority(Thread.MIN_PRIORITY);
        thread.start();
    }

    public synchronized void stop() {
        thread = null;
    }

    @Override
    public void run() {
        Thread me = Thread.currentThread();
        while (thread == me) {
            repaint();
            
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                break;
            }
        }
        thread = null;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        outmsg("pressed");
        
        
        if ((!onDragMove) && (!onDragResize)) {
            
            
            

            //for (int n = finestres.size()-1;n>-1; n--) {
           for (int n = 0;n<finestres.size(); n++) {
                //provocamos click
                Finestra pwi=finestres.get(n);
                //pwi.si_click(e);
                //
                if(pwi.enable){
                if (pwi.siDinsFin(e.getX(), e.getY(), "finestra")) {
                    ratx = e.getX();
                    raty = e.getY();
                    WinUp(pwi.id);
                    idSelected = pwi.id;
                    
                    
                    if (pwi.siDinsFin(e.getX(), e.getY(), "titol")) {
                        outmsg("move");
                        onDragMove = true;
                        this.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
                    }else

                    if (pwi.siDinsFin(e.getX(), e.getY(), "resize") && (pwi.potresize)) {
                        outmsg("resize");
                        onDragResize = true;
                        this.setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
                    }else
                    if (pwi.siDinsFin(e.getX(), e.getY(), "close")) {
                        outmsg("close");
                        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        onDragClose = true;
                        
                    }else
                        if (pwi.siDinsFin(e.getX(), e.getY(), "max")) {
                        outmsg("max");
                        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        onDragMax = true;
                        
                    }else
                        if (pwi.siDinsFin(e.getX(), e.getY(), "min")) {
                        outmsg("min");
                        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        onDragMin = true;
                        
                    }
                    else onDragClick=true;
                n=finestres.size()+1;//sortim del bucle
                }
            }
        }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
       
    	this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR)); 
        
        this.setCursor(Cursor.getPredefinedCursor(finestresBar.mouseMoved(e)));//move in the task bar
        
      
        for (int n = finestres.size()-1;n>-1; n--) {
        
           Finestra pf=finestres.get(n);
           if(pf.enable){
          
           if (pf.siDinsFin(e.getX(), e.getY(), "finestra")) {

               
               this.setCursor(Cursor.getPredefinedCursor(pf.si_over(e)));

                if (pf.siDinsFin(e.getX(), e.getY(), "titol")) {
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
                } else {
                    if (pf.siDinsFin(e.getX(), e.getY(), "resize") && (pf.potresize)) {
                        this.setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
                    } else {
                        if (pf.siDinsFin(e.getX(), e.getY(), "close")) {
                            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        }else
                         if ((pf.siDinsFin(e.getX(), e.getY(), "max")&& (pf.potresize))) {
                            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        }else
                         if ((pf.siDinsFin(e.getX(), e.getY(), "min")&& (pf.potresize))) {
                            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        }
                    }
                }


            }

        }
        
        }
    }


    @Override
    public void mouseEntered(MouseEvent e) {
        //hide mouse and change icon
        //this.setCursor(Cursor.getDefaultCursor());

        outmsg("mouse enter.");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //show mouse and change icon

        outmsg("mouse exit.");
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        ratx = e.getX();
        raty = e.getY();
        outmsg("clic.");

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        outmsg("release.");
        
        
        int idf = finestresBar.mousePressed(e);//press
        if(idf!=-1){
            WinUp(idf);
        }
        Finestra pw = finestres.get(idSelected);
        
        
        
        
        if (onDragMove) {
            pw.mou_fin(pw.x + e.getX() - ratx, pw.y + e.getY() - raty);
            onDragMove = false;
        }
        if (onDragResize) {
            pw.resize(pw.mx + e.getX() - ratx, pw.my + e.getY() - raty);
            onDragResize = false;
        }
        if (onDragClick) {
           /* if( ((WinDlgMsg) pw.getFuncWin()).modal){ 
            		if(pw.si_clickUp(e)==1)this.EliminaWin(pw.id);}
            		else{pw.si_clickUp(e);}
            onDragClick=false;
            */
        	if(pw.si_clickUp(e)==CLOSE_WIN)this.EliminaWin(pw.id);
        }
        if (onDragClose) {
            if (pw.siDinsFin(e.getX(), e.getY(), "close")) {
                EliminaWin(pw.id);
            }
            
            onDragClose=false;
        }
         if (onDragMin) {
        	 if (pw.siDinsFin(e.getX(), e.getY(), "min")) {
                 pw.minimize();
             }
            onDragMin=false;
            
        }
         if (onDragMax) {
             if (pw.siDinsFin(e.getX(), e.getY(), "max")) {
                pw.maximize(SCREENX,SCREENY-26);
            }
            
            onDragMax=false;
        }
        
        
         if (pw.siDinsFin(e.getX(), e.getY(), "finestra")) {
           pw.releaseMouse(e);
           
         }
        
    }

    
    @Override
    public void mouseDragged(MouseEvent e) {
        if (onDragMove) {
            if ((ratx != e.getX()) || (raty != getY())) {
                Finestra pw = finestres.get(idSelected);
                pw.mou_fin(e.getX() - (ratx - pw.x), e.getY() - (raty - pw.y));
                ratx = e.getX();
                raty = e.getY();
            }
        }
        if (onDragResize) {
            if ((ratx != e.getX()) || (raty != getY())) {
                Finestra pw = finestres.get(idSelected);
                pw.resize(e.getX() - (ratx - pw.mx), e.getY() - (raty - pw.my));
                ratx = e.getX();
                raty = e.getY();
            }
        }

    }

    public void Controla() {
        outmsg("desktop.Controla() : controling..");
    }
    
    private void Inicialitza(int mmx, int mmy) {
        outmsg("Inicialitzant l`entorn ... ");
        SCREENX = mmx;
        SCREENY = mmy;
        creaFondo();
    }
    
    private void creaFondo() {
        URL url = this.getClass().getResource("../res/img/fondo.png");
        bmp_fondo = Toolkit.getDefaultToolkit().getImage(url);
        if (bmp_fondo == null) {
            outmsg("... error");
            System.exit(0);
        } else {
            outmsg("... ok");
        }
    }

    private void posaFondo(Graphics g) {
       // outmsg("posa fondo....");
        int xx, yy = 0;
        for (xx = 0; xx < ((SCREENX+122) / 112); xx++) {
            for (yy = 0; yy < ((SCREENY+122) / 112); yy++) {
                g.drawImage(bmp_fondo, xx * 112, yy * 112, this);
            }
        }
    }

    private void draw(Graphics g) {
       // outmsg("drawing desktop...");
        posaFondo(g);
        ShowAllWins(g);
        
        
        g.setColor(Color.MAGENTA);
        g.drawString("(rata)> x:"+ratx+" y:"+raty, 15, 70);
    }

    
    
    @Override
    public void paint(Graphics g) {
        draw(miCG);//finestres i coese
        g.drawImage(vpage, 0, 0, this);
        finestresBar.dibBar(g,SCREENX,SCREENY,finestres);
        
        //outmsg("paint();");		
    }

    public void AddWin(Finestra finestra) {
        //Finestra fin=new Finestra();
        Finestra fin = new Finestra();
        fin = finestra;
        finestres.add(fin);
        //no cal, renombra, fin.id=finestres.size();    //ordre de les finestres
        
        
        renombra();
        
        int max=0;
        for(int n=0;n<finestres.size();n++)if(finestres.get(n).id>max)max= finestres.get(n).id;
        fin.seed=max+1; //aquest no varia, li posem el maxim+1
        
       
        
    }

    private void Marxar() {
        AlliberaMem();
        outmsg("\nMarxar() : bye..");
    }
    
    
    private void AlliberaMem() {
        int n, k;
        outmsg("\nAlliberaMem() : Elimino totes les finestres.");
        for (n = 1; n < finestres.size(); n++) {//per totes les finestres
            this.EliminaWin(finestres.get(n).id);
        }
    }

    private void EliminaWin(int num) {
        outmsg("\nElimino finestra: Elimino ." + num);
        this.WinUp(num);
        finestres.remove(num);
        this.renombra();
        
    }

    private void renombra() {
        for (int n = 0;n<finestres.size();n++)
            {
                finestres.get(n).id=n;
                outmsg("rename: get(n).id="+n);
            }
    }

    public void WinUp(int id) {
        Finestra swap = finestres.remove(id);
        finestres.addFirst(swap);
        renombra();
    }  

    private void ShowAllWins(Graphics g) {
        Finestra pwi;
        for (int n = finestres.size()-1;n>-1; n--) {
            pwi = finestres.get(n);
            if(pwi.enable){
            	pwi.dibuixa_fin(g); //dibuixa finestra
            
            }
            pwi.runApp();
        }
    }
    
}
