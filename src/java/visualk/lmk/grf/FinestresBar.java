/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package visualk.lmk.grf;


import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import visualk.lmk.res.func.IFunctions;


/**
 *
 * @author alex
 */
public class FinestresBar implements IFunctions{

        private boolean visible = true;
        private int size=25;
        
        private LinkedList<Finestra> finestres=null;
        
        
        public void dibBar(Graphics g,int mmx,int mmy,LinkedList<Finestra> finestres){
        	
        	
            this.finestres=finestres;
            
            
            if(visible){
            
              int max=0;
              for(int n=0;n<finestres.size();n++)if(finestres.get(n).seed>max)max= finestres.get(n).seed;
              //System.out.println("max:"+max);
              
               int x=0;
               for(int k=0;k<max+1;k++){
                   
                     for(int n=0;n<finestres.size();n++){
                       if(finestres.get(n).seed==k){
                            finestres.get(n).taskButton.putinscreenMX(g, (mmx/finestres.size())*x+4, mmy-size,(mmx/finestres.size())-11,finestres.get(n).id);
                            x++;
                        }
                     }
               }
            }
            
        }

    @Override
    public void init() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void paint(Graphics g, int x, int y, int mx, int my) {
        g.clipRect(x, y, mx, my);
    }

    @Override
    public int mousePressed(MouseEvent e) {
        for(int n=0;n<this.finestres.size();n++){
            if(finestres.get(n).taskButton.clic(e)==1){
            	if(finestres.get(n).isMinimized){
            		finestres.get(n).minimize();
            	}
                return(finestres.get(n).id);
            }
        }
        return(-1);
    }

    @Override
    public int mouseMoved(MouseEvent e) {
         int k;
        for(int n=0;n<this.finestres.size();n++){
           k= finestres.get(n).taskButton.mouseOver(e);
           if(k!=-1)return(k);
        }
        return(0);
        
    }

  

	@Override
	public int closeWin() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void cmd(String cmd, String token, String parameter) {
		// TODO Auto-generated method stub
		
	}
}
