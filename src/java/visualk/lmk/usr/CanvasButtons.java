/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package visualk.lmk.usr;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import visualk.lmk.res.func.IFunctions;
import visualk.lmk.res.obj.Button;
import visualk.lmk.res.obj.CheckBox;
import visualk.lmk.usr.CanvasGlobal;
/**
 *
 * @author alex
 */
public class CanvasButtons  implements IFunctions{
	CheckBox chkBox;
	CheckBox chkBox2;
	Button button;
	boolean run=true;
	
    public CanvasButtons(){
    	 chkBox= new CheckBox(true,"Hello");
    	 chkBox2= new CheckBox(true,"Hello");
    	 button = new Button("Stop");
    }
      @Override
    public void init() {
      
    }



    @Override
    public void paint(Graphics g, int x, int y, int mx, int my) {
    	g.clipRect(x, y, mx, my);
    	/*g.setColor(Color.GREEN);
        g.drawLine(x, y, x+mx, y+my);
        g.drawLine(x, y+my, x+mx, y);
        */
        chkBox.putinscreen(g, x+30, y+20);
        chkBox2.putinscreen(g, x+30, y+40);
        button.putinscreen(g, x+30, y+70);
        g.setClip(null);
    }

    @Override
    public int mousePressed(MouseEvent e) {
    	 chkBox.clic(e);
    	 chkBox2.clic(e);
    	 if(button.clic(e)==1){
      		CanvasGlobal.cw.stop();
      		run=!run;
      		if(!run)button.setLabel("Run");
      		else button.setLabel("Stop");
      		
      	 }
        return(0);
    }

    @Override
    public int mouseMoved(MouseEvent e) {
    	chkBox.mouseOver(e);
    	chkBox2.mouseOver(e);
    	button.mouseOver(e);
    	return(0);
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
