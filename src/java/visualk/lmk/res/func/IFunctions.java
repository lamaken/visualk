/*
 * Interface per les crides a funcions per a cada finestra
 */

package visualk.lmk.res.func;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

/**
 *
 * @author alex
 */
public interface IFunctions {
	
	 
	public void cmd(String cmd,String token,String parameter);
    public void init();
    public void run();
    public void paint(Graphics g, int x,int y,int mx,int my); 
    public int mousePressed(MouseEvent e);
    public int mouseMoved(MouseEvent e);
    
    public int closeWin();
}
