/**
 * 
 */
package ss.modules.viewer;

import java.util.LinkedList;

/**
 * @author alex
 *
 */
public class ComboForm extends ObjectForm{
	private LinkedList<String> options;
	public ComboForm(String caption, String name,String id_tp) {
		super(caption,name,"2",id_tp);
		options= new LinkedList<String>();
	}
	public void addOption(String caption,String name){
		options.add(caption);
	}
	private String genOptions(){
		String temp="";
		String opt="";
		String sel="";
		String kk="";
		
		temp=this.caption+"<select name=\"object_"+this.getOrder()+"\">";
		opt+="<option value=\"0\">---</option>";
		for(int k=0;k<options.size();k++){
			kk=""+(k+1);
			if(this.resposta.equals(kk))sel="selected";else sel="";
			opt+="<option "+sel+" value=\""+(k+1)+"\">"+options.get(k)+"</option>";
		}
		temp+=opt;
		temp+="</select>";
		return(temp);
	}
	public LinkedList<String> getOptions(){
		return(options);
	}
	private void update(){
		this.html = genOptions();
	}
	protected String toHtml(){
		update();
		return(super.toHtml());
	}
}
