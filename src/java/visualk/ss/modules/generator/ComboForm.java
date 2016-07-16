
/**
 * 
 */
package visualk.ss.modules.generator;

import java.util.LinkedList;

import visualk.html.LinkHtml;

/**
 * @author alex
 *
 */


public class ComboForm extends ObjectForm{

	private LinkedList<String> options;
	
	
	private static final String LNK_COMBO_FORM = "combo_form";
	private static final String LNK_COMBO_FORM_ADD= "combo_add_form";
	
	
	public ComboForm(int left, int top, String caption, String name) {
		super(caption,name,"2");
		options= new LinkedList<String>();
		
	}
	public void addOption(String caption,String name){
		options.add(caption);

		//System.out.println("addOption:"+caption);
	}
	private String genOptions(){
		String temp="";
		String opt="";
		temp=this.caption+"<select name=\""+this.name+"\">";
		opt+="<option value=\"0\">---</option>";
		for(int k=0;k<options.size();k++){
			opt+="<option value=\""+(k+1)+"\">"+options.get(k)+"</option>";
		}
		temp+=opt;
		temp+="</select>";
		return(temp);
	}
	
	public LinkedList<String> getOptions(){
		return(options);
	}
	private void update(){
		this.edit=new LinkHtml(LNK_COMBO_FORM,"edit","#","editObject","'editObject','"+getEscape()+"','"+this.name+"'","Editar el texte de la selecció").toHtml();
		this.add=new LinkHtml(LNK_COMBO_FORM_ADD,"add","#","addComboOption","'addComboOption','"+getEscape()+"','"+this.name+"'","Afegeix una opció").toHtml();
		
		this.html = genOptions();
	}
	protected String toHtml(){
		update();
		return(super.toHtml());
	}
	
}
