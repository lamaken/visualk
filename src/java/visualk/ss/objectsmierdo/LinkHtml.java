/**
 * 
 */
package visualk.ss.objectsmierdo;

/**
 * @author àlex
 *
 */

public class LinkHtml {
	private LabelHtml label;
	private String id;
	private String onclick="";
	private String link="";
	private String params="";
	private String status="";
	
	
	public String getId(){
		return(id);
	}
	public LinkHtml(String id,String label,String link,String onclick, String params,String status) {
		this.label	= new LabelHtml(label);
		this.onclick	= onclick;
		this.link = link;
		this.status=status;
		this.params = params;
		this.id=id;
	}
	public LinkHtml(String id,String label,String link,String status) {
		this.label	= new LabelHtml(label);
		this.link = link;
		this.id=id;
		this.status=status;
	}
	
	public String toHtml(){
		String tagStatus=""; 
		String tagOnClick="";
		tagStatus= "onmouseout=\"statusBar('idle')\" onmouseover=\"statusBar('"+this.status+"')\"";
		if(!this.onclick.equals(""))tagOnClick="onclick=\""+this.onclick+"("+this.params+")\"";
		return("<a "+tagStatus+" "+tagOnClick+" href=\""+this.link+"\" >"+this.label.toHtml()+"</a>");
	}
}
