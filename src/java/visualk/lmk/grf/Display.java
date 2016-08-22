/*
 * Created on 23-feb-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package visualk.lmk.grf;

/**
 * @author alex
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
import java.awt.*;

public class Display{
      

        public static Color crTitleWin  =   new Color(10,  10, 200);
        public static Color crFondoWin  =   new Color(10,  10, 100);
        public static Color crCloseWin  =   new Color(10,  10, 150);
        public static Color crSizeWin  =    new Color(10,  210, 10);
        public static Color crMarcoWin  =   new Color(210,  210, 210);
   
	//2D//
       
	public void grfWindow(Graphics g,int x,int y, int mx, int my, int cr, int cg, int cb)	{
                Color c=new Color(cr, cg, cb);
		g.setColor(Color.GRAY);
                g.drawRect(x,y,mx,my);  //marc
                g.setColor(Color.GREEN);  //{ cantonada del resize }
		g.drawRect(x+mx-5,y+my-5,4,4); 	
                g.setColor(c); //fons
                g.fillRect(x+2,y+2,mx-3,my-3); 	
	};	
	private void Line(float x,float y,float x2,float y2,float color){};//dibuixa linea
	private void Cercle(float x,float y,float radi,float color){};//dibuixa cercle	
	private void defcol(int col, float red, float green,float blue){};//define color       
	private void ShowBuffer(){
		};//swap buffer 	
	public void Bar(Graphics g, int x,int y, int mx, int my, int cr, int cg, int cb){
            Color c=new Color (cr,cg, cb);
            g.setColor(c);
            g.fillRect(x,y,mx,my); 	
        };
        public void Bar(Graphics g, int x,int y, int mx, int my, Color c){
            g.setColor(c);
            g.fillRect(x,y,mx,my); 	
        };
        public void Rect(Graphics g, int x, int y, int mx, int my,int cr, int cg, int cb){
            Color c=new Color (cr,cg,cb);
            g.setColor(c);
            g.drawRect(x,y,mx,my);
        };	
        public void Rect(Graphics g, int x, int y, int mx, int my,Color c){
            g.setColor(c);
            g.drawRect(x,y,mx,my);
        };
        public void writeText(Graphics g, Color c,String txt, int x, int y){
            g.setColor(c);
            g.drawString(txt,x,y);
        };    
}
