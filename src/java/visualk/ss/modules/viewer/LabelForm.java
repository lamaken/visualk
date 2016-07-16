/**
 * 
 */
package ss.modules.viewer;


/**
 * @author alex
 *
 */
public class LabelForm extends ObjectForm{
	public LabelForm(int left, int top, String caption, String name,String id_tp) {
		super(caption,name,"3",id_tp);
	}
	private void update(){
		this.html = "<input name=\"object_"+this.getOrder()+"\" type=\"hidden\"/>"+this.caption;
	}
	protected String toHtml(){
		update();
		return(super.toHtml());
	}
}
