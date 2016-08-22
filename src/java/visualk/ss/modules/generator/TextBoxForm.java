/**
 * 
 */
package visualk.ss.modules.generator;


import visualk.html5.LinkHtml;

/**
 * @author alex
 *
 */
public class TextBoxForm extends ObjectForm{
	
	private static final String LNK_EDIT_FORM = "edit_form";

	int size=5;			//numero de caracters
	
	

	public TextBoxForm(int left, int top, String caption, String name, int size) {
		super(caption,name,"1");
		setLeftTop(left, top);
		this.size=size;
	}
	protected void setColor(String color){
		this.color=color;
	}
	private String genCaixa(){
		return(this.caption+"<input type=\"text\" name=\""+this.name+"\" value=\"\"/>");
	}
	private void update(){
		this.edit=new LinkHtml(LNK_EDIT_FORM,"edit","#","editObject","'editObject','"+getEscape()+"','"+this.name+"'","Editar la caixa de texte").toHtml();
		this.html = genCaixa();
		
	}
	protected String toHtml(){
		update();
		return(super.toHtml());
	}
	

}
