package visualk.lmk.res.obj;

import java.awt.Image;

public class Obj {
	public String caption;
	public Image bmpButton;
	public int bx, by;
	public int bmx,bmy;
	public int offsetImage = 0;

	public boolean siDins(int x, int y) {
		if ((x >= bx) && (y >= by) && (x <= bx + bmx) && (y <= by + 21)) {
			return (true);
		} else
			return (false);
	}

}
