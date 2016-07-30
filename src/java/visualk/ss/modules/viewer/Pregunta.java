package visualk.ss.modules.viewer;

import java.util.LinkedList;




public class Pregunta {
	

	private String open_form_xml = "<form method=\"post\" name=\"fPagina\" action=\"/visualk/ss/SurveyServer\">";;
	private String form_data_xml = "<input type=\"hidden\" value=\"view\" name=\"where\"/><input type=\"hidden\" name=\"actions\" value=\"\"/>";
	
	private String close_form_xml="</form>";
	private int top=10;
	
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
	public  void setRespostes(LinkedList<String> respuestas){
		int n;
		for(n=0;n<objectesFormulari.size();n++){
			objectesFormulari.get(n).setResposta(respuestas.get(n));
		}
	}
	protected Pregunta(String id) {
		objectesFormulari= new LinkedList<ObjectForm>();
	
	}
	
	protected void addLabel(String caption,String name,String id_tp){
		
		LabelForm label = new LabelForm(10,top,caption,name,id_tp);
		objectesFormulari.add(label);
		label.setOrder(objectesFormulari.size());
		top+=47;
	}
	protected void addCombo(String caption,String name,String id_tp){
		ComboForm combo = new ComboForm(caption,name,id_tp);
		objectesFormulari.add(combo);
		combo.setOrder(objectesFormulari.size());
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
	
	protected void addTextBox(String caption,String name,String id_tp){
		TextBoxForm textBox= new TextBoxForm(10,top,caption,name,5,id_tp);
		objectesFormulari.add(textBox);
		textBox.setOrder(objectesFormulari.size());
		top+=47;
	}
	
	protected String toHtml(){
		int n=0;
		String ret = this.open_form_xml;
		ret+=this.form_data_xml;
		ret+="<input type=hidden name=\"count\" value=\""+objectesFormulari.size()+"\"/>";
		for(n=0;n<objectesFormulari.size();n++)ret += objectesFormulari.get(n).toHtml();
		ret+=this.close_form_xml;
		return (ret);
	}
	
}
