package visualk.ss.modules.generator;

import java.util.LinkedList;
import ss.modules.generator.ComboForm;




public class Pregunta {
	
	private String id;
	private String open_form_xml;
	private String close_form_xml;
	private int top=10;
	
	//private llista de editboxes.

	private LinkedList<ObjectForm> objectesFormulari;
	
	public LinkedList<ObjectForm> getObjectesFormulari(){
		return(objectesFormulari);
	}
	protected void setObjectCaption(String name,String caption){
		int n;
		for(n=0;n<objectesFormulari.size();n++){
			if(objectesFormulari.get(n).getName().equals(name))objectesFormulari.get(n).setCaption(caption);
		}
	}

	protected void deleteObject(String name){
		int n;
		for(n=0;n<objectesFormulari.size();n++){
			if(objectesFormulari.get(n).getName().equals(name))objectesFormulari.remove(n);
		}
	}

	protected void upObject(String name){
		int n;
		ObjectForm obj;
		for(n=0;n<objectesFormulari.size();n++){
			if(objectesFormulari.get(n).getName().equals(name)){
					if(n>0){
						obj = objectesFormulari.remove(n);
						objectesFormulari.add(n-1, obj);
						n=objectesFormulari.size();
					}
			}
		}
	}

	protected void downObject(String name){
		int n;
		ObjectForm obj;
		for(n=0;n<objectesFormulari.size();n++){
			if(objectesFormulari.get(n).getName().equals(name)){
					if(n<objectesFormulari.size()-1){
						obj = objectesFormulari.remove(n);
						objectesFormulari.add(n+1, obj);
						n=objectesFormulari.size();
					}
			}
		}
	}

	
	protected Pregunta(String id) {
		objectesFormulari= new LinkedList<ObjectForm>();
		
		this.id=id;
		
		this.open_form_xml="<form name=\""+this.id+"\" action=\"\">";
		this.close_form_xml="</form>";
	}
	
	protected void addLabel(String caption,String name){
		
		LabelForm label = new LabelForm(10,top,caption,name);
		objectesFormulari.add(label);
		top+=47;
	}
	protected void addCombo(String caption,String name){
		
		ComboForm combo = new ComboForm(10,top,caption,name);
		objectesFormulari.add(combo);
		top+=47;
	}
	protected void addComboOption(String name,String caption){
		
		int n;
		for(n=0;n<objectesFormulari.size();n++){
			if(objectesFormulari.get(n).getName().equals(name)){
				((ComboForm)objectesFormulari.get(n)).addOption(caption, name);
			}
		}
	}
	
	protected void addTextBox(String caption,String name){
		
		
		TextBoxForm textBox= new TextBoxForm(10,top,caption,name,5);
		objectesFormulari.add(textBox);
		top+=47;
	}
	
	protected String toHtml(){
		int n=0;
		String ret = this.open_form_xml;
		
		for(n=0;n<objectesFormulari.size();n++)ret += objectesFormulari.get(n).toHtml();
		
		//aqui van els edit,,, etc..
		ret+=this.close_form_xml;
		
		
	
		return (ret);
	}
	
}
