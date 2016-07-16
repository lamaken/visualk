package visualk.ss.objectsmierdo;

public class CssStyles {
	private String html="";
	public CssStyles(){this.html="";};
	
	public void addFileCSS(String outFile) {
		this.html+="<link rel=\"stylesheet\" href=\""+outFile+"\"/>";
	}
	public String toHtml(){
		return(this.html);
	}
}
