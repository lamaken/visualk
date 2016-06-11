package visualk.html;

import java.util.LinkedList;

public class CssStyles {
	
	
	private static String topHeader= "<style type=\"text/css\">";
	private static String bottomHeader = "</style>";
	
	
	
	private String html="";
	
	
	private LinkedList<ClassCSS> classCSS;
	
	
	public CssStyles(){
		this.html="";
		classCSS= new LinkedList<ClassCSS>();;
	}
	
	public void addFileCSS(String outFile) {
		this.html+="<link type=\"text/css\"  rel=\"stylesheet\" href=\""+outFile+"\"/>";
	}
	
	public void addStyle(ClassCSS classStyle){
		classCSS.add(classStyle);
	}
	public String toHtml(){
		String html = this.html+topHeader;
		int n;
		for (n=0;n<classCSS.size();n++){
			html+=classCSS.get(n).toHtml();
		}
		html+=bottomHeader;
		return(html);
	}
}


/*
#optionsBar{
position: absolute; 
right:50px;
top:10px;
z-index: 100;
text-align:center;
padding:10px;
background:rgb(90,80,90);
}
*/