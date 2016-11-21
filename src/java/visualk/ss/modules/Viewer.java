/**
 * 
 */
package visualk.ss.modules;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import visualk.ss.db.ViewDB;
import visualk.ss.modules.viewer.FluxeSurvey;
import visualk.ss.modules.viewer.UserData;
import visualk.html5.*;
/**
 * @author alex
 *
 */
public class Viewer  extends Xhtml5{

	private static final String CSS_VIEWER_FILE_NAME="css/viewer.css";
	private static final String JS_VIEWER_FILE_NAME="js/viewer.js";
	
	private static final String LNK_MARXAR = "exit";
	private static final String LNK_CANCELA = "cancela";
	
	private FluxeSurvey fluxeSurvey;
	
	private MenuLinkBar upperMenuBar;
	
	private MenuLinkBar menuAnonimes;
	private MenuLinkBar menuNoAnonimes;
        
        private final ClassCSS cssMenuBar = new ClassCSS();
        
	
	private String email_or_code;
	private UserData usuariData;
	private ViewDB viewDB;
	private void initMenu(){
		upperMenuBar.addMenuLink(LNK_MARXAR,"Marxar del programa","#","vols_marxar","'marxar'","Si vols marxar.. tu mateix");
	}	
	//carrega el combo amb les opcions
	public void  populateComboSelects(String id_select, String newname){
		String caption="Not Found";

		if(fluxeSurvey!=null){
			
			ResultSet myResult = viewDB.getSelectsLabels(id_select);			
			try {
				while(myResult.next()){
					 caption = myResult.getString("caption");
					 fluxeSurvey.addComboOption(newname, caption);
				}
					 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else{
			this.messageBox.setMessage("Has de carregar una enquesta.");
		}
			
		
	}
	
	
	//carrega els objectes de la pregunta
	public void loadObjects(String idPregunta){
		String tipus="Not Found";
		String caption="Not Found";

		String id_tp = "none";
		String id_select ="Not Found";
		

		if(fluxeSurvey!=null){
			
			ResultSet myResult = viewDB.getObjects(idPregunta);
			try {
				while(myResult.next()){
					 tipus = myResult.getString("name");
					 caption = myResult.getString("caption");
					 id_select = myResult.getString("id_select");
					 id_tp = myResult.getString("id_tp");
					 
					 String newname = new UniqueName(8).getName();
					 if(tipus.equals("Label"))fluxeSurvey.addLabel(caption, newname,id_tp);
					 if(tipus.equals("EditBox"))fluxeSurvey.addTextBox(caption, newname,id_tp);
					 if(tipus.equals("Select")){
						 	fluxeSurvey.addCombo(caption, newname,id_tp);
						 	populateComboSelects(id_select,newname);
					 }
					
					 
			}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else{
			this.messageBox.setMessage("Has de carregar una enquesta.");
		}
			
		
	}
		
		

	
	
	
	
	//carrega les preguntes
	public void loadPreguntes(String idEnquestaDB){
		String id="none";
		if(fluxeSurvey!=null){
			
			ResultSet myResult = viewDB.getPreguntes(idEnquestaDB);
			try {
				while(myResult.next()){
					 id = myResult.getString("id_pregunta");
					 fluxeSurvey.addPregunta(id);
					 loadObjects(id);
			}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else{
			this.messageBox.setMessage("Has de carregar una enquesta.");
		}
			
		
	}
	
	public void loadEnquesta(String idEnquestaDB){
		String desc="Not found";
		String nome="Not found";
		
			ResultSet myResult = viewDB.getEnquesta(idEnquestaDB);
			try {
				while(myResult.next()){
					 desc = myResult.getString("dsc_enquesta");
				     nome = myResult.getString("nm_enquesta");
			}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		fluxeSurvey = new FluxeSurvey(nome,desc,idEnquestaDB);
		loadPreguntes(idEnquestaDB);
	}
		
	public Viewer(String eml_or_code, String session) {
		// TODO Auto-generated constructor stub
		super("SurveyServer",session,"Viewer");
		
		viewDB= new ViewDB();
		
		upperMenuBar = new MenuLinkBar("mainMenuBar",cssMenuBar);
		menuAnonimes = new MenuLinkBar("llistaAnonimes",cssMenuBar);
		menuAnonimes.setVertical();
		
		menuNoAnonimes = new MenuLinkBar("llistaNoAnonimes",cssMenuBar);
		menuNoAnonimes.setVertical();
		
		if(eml_or_code==null) this.setEmail_or_code(session);
		else this.setEmail_or_code(eml_or_code);
		
		carregaDadesUsuari();
		continua();
		
		this.initMenu();
		
	}
	
	public String toHtml(){
		
		cssStyles.addFileCSS(CSS_VIEWER_FILE_NAME);
		this.updateFunctions();
		vsFunctions.addFunction("vols_marxar","actions","if(confirm(\"Segur que vols Marxar?\"))fes(actions,'')");
			
		vsFunctions.addFile(JS_VIEWER_FILE_NAME);
		
		this.addBanner(getEmail_or_code());
		this.addDataForm("<input type=\"hidden\" name=\"where\" value=\"view\"/>");
		
		
		
		this.addBodyData(upperMenuBar.toHtml());
		
		String ret=this.getHtml();
		messageBox.setMessage("");//inicialitzem missatges
		return(ret);
	}

	public void carregaDadesUsuari(){
		usuariData=new UserData(getEmail_or_code());
		//this.addBodyData(usuariData.toHtml());
	}
	
	public void LlistaAnonimes(){
		String ide;
		String nome;
		
		ResultSet myResult = viewDB.getLlistaAnonimes();
		try {
			while(myResult.next()){
				 ide = myResult.getString("id_enquesta");
			     nome = myResult.getString("nm_enquesta");
			     menuAnonimes.addMenuLink("a_"+ide, nome , "#","fes","'loadEnquesta','"+ide+"'", "Fes clic per carregar l`enquesta :("+ nome + ")");
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
                             menuAnonimes.addMenuLink("a_", "nome" , "#","fes","'loadEnquesta','ide'", "Fes clic per carregar l`enquesta :("+ "nome" + ")");
			
			e.printStackTrace();
		}
		menuAnonimes.setTitle("S`han trobat les següents enquestes anònimes");
	}
		
	public void  LlistaNoAnonimes(String email){
		String ide;
		String nome;
		
		ResultSet myResult = viewDB.getLlistaNoAnonimes(email);
		try {
			while(myResult.next()){
				 ide = myResult.getString("id_enquesta");
			     nome = myResult.getString("nm_enquesta");
			     menuNoAnonimes.addMenuLink("n_"+ide, nome , "#","fes","'loadEnquesta','"+ide+"'", "Fes clic per carregar l`enquesta :("+ nome + ")");
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		menuNoAnonimes.setTitle("S`han trobat les següents enquestes dirigides a tú.");
	}
		
	public void continua(){
		this.clearBodyData();
		menuAnonimes.clear();
		menuNoAnonimes.clear();
		LlistaAnonimes();
		
		this.addBodyData(menuAnonimes.toHtml());
		
		if(!usuariData.isAnonim()){
				LlistaNoAnonimes(usuariData.getEmail());
				this.addBodyData(menuNoAnonimes.toHtml());
		}
	}
	public void inicia(String idEnquestaDB){//inicia l'enquesta
		this.clearBodyData();
		upperMenuBar.addMenuLink(LNK_CANCELA, "Tornar", "#","fes","'cancela_resp','0'","Cancel·la i torna al menú inicial.");
		loadEnquesta(idEnquestaDB);
		this.fluxeSurvey.primera();
		this.addBodyData(this.fluxeSurvey.toHtml());
	}
	public void cancela(){
		this.fluxeSurvey=null;
		upperMenuBar.eliminaOption(LNK_CANCELA);
		continua();
		
	}
	public void anterior(LinkedList<String> respostes){
		this.clearBodyData();
		this.fluxeSurvey.saveresposta(respostes);
		this.fluxeSurvey.anterior();
		this.addBodyData(this.fluxeSurvey.toHtml());
	}
	public void seguent(LinkedList<String> respostes){
		this.clearBodyData();
		this.fluxeSurvey.saveresposta(respostes);
		if(this.fluxeSurvey.seguent()){
			this.addBodyData(this.fluxeSurvey.toHtml());
		}
		else{
			viewDB.saveAlltoBD(fluxeSurvey,getEmail_or_code());
			upperMenuBar.eliminaOption(LNK_CANCELA);
			continua();
			this.messageBox.setMessage("Gràcies per respondre l`enquesta.");
			}
		
	}
	public void setEmail_or_code(String code) {
		this.email_or_code = code;
	}

	public String getEmail_or_code() {
		return email_or_code;
	}
}
