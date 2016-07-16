/**
 * 
 */
package visualk.ss.modules.generator;


import ss.objects.LinkHtml;

/**
 * @author alex
 *
 */
public class LabelForm extends ObjectForm{
	
	private static final String LNK_LABEL_FORM = "label_form";

		
	
	public LabelForm(int left, int top, String caption, String name) {
		super(caption,name,"3");
		
		
		setLeftTop(left, top);
	}
	private void update(){
		this.edit=new LinkHtml(LNK_LABEL_FORM,"edit","#","editObject","'editObject','"+getEscape()+"','"+this.name+"'","Editar el label").toHtml();
		this.html = this.caption;
		
	}
	protected String toHtml(){
		update();
		
		return(super.toHtml());
	}
}
