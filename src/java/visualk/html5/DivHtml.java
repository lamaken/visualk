/**
 * 
 */
package visualk.html5;

/**
 * @author alex
 *
 */
public class DivHtml {

	private String div_id="";
	private String div_class="";
	private String div_style="";	
	private String div_params="";
	
	public void setDiv_class(String div_class) {
		this.div_class = div_class;
	}
	public void setDiv_style(String div_style) {
		this.div_style = div_style;
	}
	public void addParams(String params){
		this.div_params=params;
	}
		
	public DivHtml(String div_id) {
		this.div_id 	= div_id;
	}
	
	public String toHtml(String html){
		String ret="<div ";
		
		ret += " id=\""+this.div_id+"\" ";
		if(!div_class.equals(""))ret += " class=\""+this.div_class+"\" ";
		if(!div_style.equals(""))ret += " style=\""+this.div_style+"\" ";
		if(!div_params.equals(""))ret += div_params;
		
		ret +=	">";
		ret +=	html;
		ret+=	"</div>";
		
		return(ret);
	}

}
