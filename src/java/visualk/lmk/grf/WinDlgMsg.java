/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package visualk.lmk.grf;

/**
 *
 * @author alex
 */


import java.awt.Color;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import visualk.lmk.res.func.IFunctions;
import visualk.lmk.res.obj.Button;



public class WinDlgMsg implements IFunctions{

    String msg;
    int x,y,mx,my;
    Button btClose;
    public boolean modal=false;
    
    
    
    
    public WinDlgMsg(String msg){
        this.msg=msg;
        this.modal=true;
        btClose=new Button("Close");
       
    }
    
    
    @Override
    public void init() {
        
        
    }

    @Override
    public void paint(Graphics g, int x, int y, int mx, int my) {
        g.setColor(Color.lightGray);
        g.fillRect(x, y, mx, my);
        g.setColor(Color.BLACK);
        g.drawString(msg, x+15, y+20);
        g.setColor(Color.red);
        
        
        btClose.putinscreen(g, x + mx/2 - btClose.bmx/2, y+my-(btClose.bmy));
        
        this.x=x;
        this.y=y;
        this.mx=mx;
        this.my=my;
    }

   

    @Override
    public int mousePressed(MouseEvent e) {
    	if(btClose.clic(e)==1)return(closeWin());
         return(0);
    }

    @Override
    public int mouseMoved(MouseEvent e) {
        
            
        return (btClose.mouseOver(e));
    }

    @Override
    public void run() {
        
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
