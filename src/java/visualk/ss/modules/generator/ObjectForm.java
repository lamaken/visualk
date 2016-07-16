/**
 * 
 */
package visualk.ss.modules.generator;



import visualk.html.DivHtml;
import visualk.html.LinkHtml;


/**
 * @author alex
 *
 */
public class ObjectForm {

	
	private static final String LNK_DEL_OBJECT = "delete_object";
	private static final String LNK_UP_OBJECT = "up_object";
	private static final String LNK_DOWN_OBJECT = "down_object";
	
	

	
	protected DivHtml div;
	protected int left=0;			//posició
	protected int top=0;
	protected int right=10;
	
	
	protected String caption;		//texte 
	
	protected String name;		//nom intern
	protected String color;
	
	
	protected String edit;	//enllaç per editar
	protected String close;//enllaç per tancar
	protected String up;//ellanç per pujar
	protected String down;//enllaç per baixar
	protected String add;//button add only choice
	protected String tipus;
	
	protected String html;

	
	
	protected String getName(){return this.name;}
	
	protected void setCaption(String caption){
		String escape=caption.replace("`","'");
		escape=escape.replace("\"","&quot;");
		this.caption=escape;
	
	}
	
	protected String getEscape(){
		String escape=this.caption.replace("'", "`");
		escape.replace("&quot;", "*");
		return(escape);
	}
	protected void setColor(String color){
		this.color=color;
	}
	protected void setLeftTop(int left, int top){
		this.left=left;
		this.top=top;
		
	}
	public String getTipus(){
		return(this.tipus);
	}
	public String getCaption(){
		return(this.caption);
	}
	
	public ObjectForm(String caption, String name, String tipus) {
		this.tipus=tipus;
		this.caption=caption;
		this.name=name;
		div = new DivHtml(this.name);
		div.setDiv_class("objectForm");
		//div.addParams("onclick=\"alert('"+this.name+"')\"");
		this.close=new LinkHtml(LNK_DEL_OBJECT,"delete","#","deleteObject","'deleteObject','"+this.name+"'","Eliminar l`objecte").toHtml();
		this.up=new LinkHtml(LNK_UP_OBJECT,"up","#","upObject","'upObject','"+this.name+"'","Enviar amunt").toHtml();
		this.down=new LinkHtml(LNK_DOWN_OBJECT,"down","#","downObject","'downObject','"+this.name+"'","Enviar aball").toHtml();
		this.add = "";
		
		
	}
	protected String toHtml(){
		
		DivHtml divEdit= new DivHtml("e_"+this.name);
		divEdit.setDiv_class("class_edit");
		
		DivHtml divClose= new DivHtml("c_"+this.name);
		divClose.setDiv_class("class_close");

		DivHtml divUp= new DivHtml("u_"+this.name);
		divUp.setDiv_class("class_up");

		DivHtml divDown= new DivHtml("d_"+this.name);
		divDown.setDiv_class("class_down");

		DivHtml divAdd= new DivHtml("a_"+this.name);
		divAdd.setDiv_class("class_down");

		String xurro =	divEdit.toHtml(this.edit)+
						divClose.toHtml(this.close)+
						divAdd.toHtml(this.add)+
						divUp.toHtml(this.up)+
						divDown.toHtml(this.down)+
						this.html;
		
		return(div.toHtml(xurro));
	}
}


