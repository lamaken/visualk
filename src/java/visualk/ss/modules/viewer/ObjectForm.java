/**
 * 
 */
package visualk.ss.modules.viewer;



import visualk.html.DivHtml;



/**
 * @author alex
 *
 */
public class ObjectForm {

	

	protected DivHtml div;
	protected String caption;		//texte 
	protected String name;		//nom intern
	protected String tipus;
	protected String html;
	protected String resposta="";
	
	
	
	private int order=0;
	
	
	public String getResposta() {
		return resposta;
	}
	
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public String getId_tp() {
		return id_tp;
	}
	public void setId_tp(String id_tp) {
		this.id_tp = id_tp;
	}
	public String id_tp;
	
	protected String getName(){return this.name;}
	public void setResposta(String resp){
		resposta=resp;
	}
	protected void setCaption(String caption){
		this.caption=caption;
	}
	
	protected String getEscape(){
		return(this.caption);
	}
	public String getTipus(){
		return(this.tipus);
	}
	public String getCaption(){
		return(this.caption);
	}
	
	public ObjectForm(String caption, String name, String tipus, String id_tp) {
		this.tipus=tipus;
		this.caption=caption;
		this.name=name;
		this.id_tp=id_tp;
		div = new DivHtml(this.name);
		div.setDiv_class("objectForm");
	}
	protected String toHtml(){
		return(div.toHtml(this.html));
	}
}


