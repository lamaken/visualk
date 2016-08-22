package visualk.lmk.res.obj;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class Menu {

	private int bmx, bmy;// tamany finestra
	private int posx, posy;// posicio menu

	private LinkedList<ItemMenu> Items;

	public Menu(String caption) {
		Items = new LinkedList<ItemMenu>();
	}

	public void addItem(String caption, String icon, String id) {
		ItemMenu item = new ItemMenu(caption, icon, id);
		Items.add(item);
	}
	public ItemMenu getItemMenu(String id){
		for (int n = 0; n < Items.size(); n++) {
			if(Items.get(n).getId().equals(id)){
				return Items.get(n);
			}
		}
		return(null);
	}
	

	public int clic(MouseEvent e) {
		if (this.siDins(e.getX(), e.getY())) {
			return (1);
		} else {
			return (0);
		}
	}

	public int mouseOver(MouseEvent e) {
		if (this.siDins(e.getX(), e.getY())) {
			for (int n = 0; n < Items.size(); n++) {
				Items.get(n).mouseOver(e);
			}
			return (Cursor.HAND_CURSOR);
		} else {
			return (-1);
		}
	}

	public boolean siDins(int x, int y) {
		if ((x >= this.posx) && (x <= this.posx + bmx) && (y >= this.posy)
				&& (y <= this.posy + this.bmy)) {
			return (true);
		} else
			return (false);
	}

	public void putinscreen(Graphics g, int x, int y, int mx, int my) {
		this.posx = x;
		this.posy = y;
		this.bmx = mx;
		this.bmy = my;

		int numItems = Items.size();
		for (int n = 0; n < numItems; n++)
			Items.get(n).putinscreen(g, x, y + 20 + 20 * n, mx);
	}

}
