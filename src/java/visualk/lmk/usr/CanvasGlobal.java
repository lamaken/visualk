package visualk.lmk.usr;

public class CanvasGlobal {
	
	protected static CanvasWindow cw;
	protected static CanvasMenu cm;
	protected static CanvasButtons cb;
	
	public CanvasGlobal(CanvasWindow cw, CanvasMenu cm, CanvasButtons cb){
		CanvasGlobal.cw = cw;
		CanvasGlobal.cm = cm;
		CanvasGlobal.cb = cb;
	}
	
	
}
