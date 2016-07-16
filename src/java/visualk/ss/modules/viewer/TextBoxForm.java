/**
 * 
 */
package ss.modules.viewer;



/**
 * @author alex
 *
 */
public class TextBoxForm extends ObjectForm{
	public TextBoxForm(int left, int top, String caption, String name, int size,String id_tp) {
		super(caption,name,"1",id_tp);
	}
	private String genCaixa(){
		return(this.caption+"<input type=\"text\" name=\"object_"+this.getOrder()+"\" value=\""+this.resposta+"\"/>");
	}
	private void update(){
		this.html = genCaixa();
	}
	protected String toHtml(){
		update();
		return(super.toHtml());
	}
}
