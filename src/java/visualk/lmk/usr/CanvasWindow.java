/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package visualk.lmk.usr;

import java.awt.Color;
import java.awt.event.MouseEvent;


import java.awt.Graphics;
import visualk.lmk.res.func.IFunctions;


/**
 *
 * @author alex
 */
public class CanvasWindow implements IFunctions {

	
	private static final int penya = 900050;
	
	
	
	
    int px, py, pmx, pmy;
    int cx[] = new int[penya];
    int cy[] = new int[penya];
    int dirx[] = new int[penya];
    int diry[] = new int[penya];
    int rad[]  = new int[penya];
    int populate=0;
    double r=0;
    boolean run1=true;

    public CanvasWindow() {
    
    
    
    }


    public void stop(){
    	run1 = !run1;
    	
    }
    
    
    
    @Override
    public void paint(Graphics g, int x, int y, int mx, int my) {
        g.clipRect(x, y, mx, my);
       
        
        Color colors[]={Color.CYAN,
                        Color.GRAY,
                        Color.MAGENTA,
                        Color.RED,
                        Color.BLUE,
                        Color.YELLOW,
                        Color.GREEN,
                        Color.ORANGE};
        
        populate=0;
        for (int n = 1; n < penya; n++) {
                    if(
                    (cx[n]>=x-rad[n])&&
                    (cy[n]>=y-rad[n])&&
                    (cx[n]<=mx+x)&&
                    (cy[n]<=my+y))
            {
                        populate++;
            g.setColor(colors[n%8]);
            g.fillArc(cx[n], cy[n], rad[n], rad[n], 0, 360);
            }
        }
        this.px = x;
        this.py = y;
        this.pmx = mx + x;
        this.pmy = my + y;
        g.setClip(null);
        g.setColor(Color.WHITE);
        g.drawString(populate+"", 10,10);
    }

    @Override
    public void init() {
        
            
        for (int n = penya-1; n > 0; n--) {
            
        	
            cx[n] = n*55;
            cy[n] = n/2;
            rad[n] = 20+n/12;
            dirx[n]=1;
            diry[n]=0;
        }
        run();
    }

    @Override
    public int mousePressed(MouseEvent e) {
        return (0);
    }

    @Override
    public int mouseMoved(MouseEvent e) {
        return (0);
    }

    @Override
   public void run() {
        
    	
    	if(run1){
    	
    	for (int n = 0; n < penya; n++) {
       		
        	
            if (dirx[n] == 0) {
                cx[n] += rad[n]/12;
            } else {
                cx[n] -=rad[n]/12;
            }
            if (diry[n] == 0) {
                cy[n] += rad[n]/12;
            } else {
                cy[n]  -= rad[n]/12;
           }
            
            if (cy[n] > pmy-rad[n] ) {
                diry[n] = 1;
            }
            if (cy[n] < py) {
                diry[n] = 0;
            }
            if (cx[n] + rad[n] > pmx) {
                dirx[n] = 1;
            }
            if (cx[n] < px) {
                dirx[n] = 0;
            }
      
    	}
    	}
    }







	@Override
	public int closeWin() {
		// TODO Auto-generated method stub
		return -1;//CLOSE_WIN=-1
	}


	@Override
	public void cmd(String cmd, String token, String parameter) {
		// TODO Auto-generated method stub
		
	}
}
