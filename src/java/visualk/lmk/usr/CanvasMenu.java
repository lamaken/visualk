/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package visualk.lmk.usr;


import java.awt.Color;
import java.awt.Graphics;

import java.awt.event.MouseEvent;






import visualk.lmk.Main;
import visualk.lmk.grf.Finestra;
import visualk.lmk.grf.InputWinDlgMsg;
import visualk.lmk.grf.WinDlgMsg;
import visualk.lmk.res.func.IFunctions;
import visualk.lmk.res.obj.Menu;
import visualk.lmk.tools.UniqueName;

/**
 *
 * @author alex
 */
public class CanvasMenu implements IFunctions{

   
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Menu menu = null;
	
	WinDlgMsg wd=null;
	
	//mirar possibilitat d'utilitzar el javax...menu
	public CanvasMenu(){
		menu = new Menu(new UniqueName(8).getName());
		menu.addItem("Nou...","icon","1");
		menu.addItem("Guardar...","icon","2");
		menu.addItem("Carregar...","icon","3");
    }

     @Override
    public void init() {
      
    }

    @Override
    public void paint(Graphics g, int x, int y, int mx, int my) {
        g.setColor(Color.RED);
        g.drawLine(x, y, x+mx, y+my);
        g.drawLine(x, y+my, x+mx, y);
        menu.putinscreen(g,x,y,mx,my);
        
    }

    @Override
    public int mousePressed(MouseEvent e) {
        
        
        if(menu.getItemMenu("2").clic(e)==1){
        	IFunctions cdApp1 = new InputWinDlgMsg("");
        	Finestra fin4=new Finestra(cdApp1,260, 200, 300, 200,"Dades del XML",true,false,true,true);
            Main.myDesktop.AddWin(fin4);
            Main.myDesktop.WinUp(fin4.id);
            
           
         /*   
            Serializer serializer = (Serializer) new Persister();
            File source = new File("example.xml");

            Example example = serializer.read(Example.class, source);
           */ 
            
      	 }
        
        return(menu.clic(e));
    }

    @Override
    public int mouseMoved(MouseEvent e) {
        return(menu.mouseOver(e));
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
