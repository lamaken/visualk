package visualk.lmk.res.obj;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import java.net.URL;

public class CheckBox extends Obj{

	
	private Image bmpCheck;

	
	
	private boolean checked;
	private Color btTextColor=Color.GRAY;
	
	public CheckBox(boolean checked, String caption) {
		this.checked = checked;
		this.caption = caption;
		LoadBMP();
	}

	public int clic(MouseEvent e) {
		if (this.siDins(e.getX(), e.getY())) {
			this.checked = !this.checked;
			return (1);
		} else {
			return (0);
		}
	}

	

	 public int mouseOver(MouseEvent e){
	        offsetImage=0;
	        
	        if(this.siDins(e.getX(),e.getY())){
	            
	            btTextColor=Color.WHITE;
	            return(Cursor.HAND_CURSOR);}
	        else{
	            
	            btTextColor=Color.GRAY;
	            return(-1);
	        }
	    
	    }

	private void LoadBMP() {
		System.out.print("Loading bmp checkbox");
		URL url;
		url = this.getClass().getResource("../img/check2.png");
		bmpCheck = Toolkit.getDefaultToolkit().getImage(url);
		if (bmpCheck == null) {
			System.out.println("... error");
		} else {
			System.out.println("... ok");
		}
	}

	public void putinscreen(Graphics g, int x, int y) {

	
		this.bx = x;
		this.by = y;
		this.bmx = 10 * (this.caption.length() + 2);

		BufferedImage mybuffer = new BufferedImage(26, 13,	BufferedImage.TYPE_INT_ARGB);
		Graphics g2 = mybuffer.createGraphics();
		g2.drawImage(this.bmpCheck, 0, 0, null);
		g2.dispose();

		BufferedImage c1 = mybuffer.getSubimage(offsetImage, 0, 14, 13);
		BufferedImage c2 = mybuffer.getSubimage(offsetImage + 13, 0, 13, 13);

		if (this.checked)
			g.drawImage(c1, x, y, null);
		else
			g.drawImage(c2, x, y, null);
		
		g.setColor(btTextColor);
		g.drawString(caption, x+14, y+11);
        
	}

}
