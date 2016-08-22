package visualk.lmk.res.obj;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.LinkedList;



public class ItemMenu extends Obj{
	
	
	private final static Color col=Color.gray;
	private final static Color colHi=Color.white;
	
	
	
	
	
	
	String icon;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	String id;
	
	boolean cursorover=false;
	boolean cursorclick=false;
	boolean visible=false;
	
	private LinkedList<ItemMenu> brothers;
	private LinkedList<ItemMenu> sons;
	

	public void addSon(String caption,String icon,String id){
		this.sons.add(new ItemMenu(caption,icon, id));
	}
	public void addBrother(String caption,String icon,String id){
		this.brothers.add(new ItemMenu(caption,icon,id));
	}
	
	public ItemMenu(String caption, String icon,String id){
		brothers= new LinkedList<ItemMenu>();
		sons= new LinkedList<ItemMenu>();
		this.caption = caption;
		this.icon=icon;
		this.id = id;
	}
	 public int clic(MouseEvent e){
	        if(this.siDins(e.getX(),e.getY())){
	        	this.cursorclick=true;	
	        return(1);
	        }else{ this.cursorclick=false;return(0);}
	    }
	 public int mouseOver(MouseEvent e){
	        if(this.siDins(e.getX(),e.getY())){
	        	this.cursorover=true;
	            return(0);}
	        else{
	        	this.cursorover=false;
	            return(-1);
	        }
	    }
	    
	    public void putinscreen(Graphics g, int x, int y, int mx) {
	    	if(this.cursorover)g.setColor(colHi);
			else g.setColor(col);
			this.bx=x;
			this.by=y;
			this.bmx=mx;
			g.drawString(caption, x+14, y+10);
	       
		}
}
