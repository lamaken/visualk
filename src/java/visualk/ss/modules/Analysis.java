package visualk.ss.modules;


import java.sql.ResultSet;
import java.sql.SQLException;
import visualk.html5.ClassCSS;

import visualk.ss.db.AnalysisDB;
import visualk.ss.modules.analysis.DataGroupAnalysis;

import visualk.html5.MenuLinkBar;
import visualk.html5.Xhtml5;

public class Analysis  extends Xhtml5{

	private static final String CSS_VIEWER_FILE_NAME="css/analysis.css";
	private static final String JS_VIEWER_FILE_NAME="js/analysis.js";
	
	private static final String LNK_MARXAR = "exit";
	private static final String LNK_TORNAR = "tornar";
	
	
	private MenuLinkBar upperMenuBar;
	
	private MenuLinkBar menuEnquestes;
	private final ClassCSS cssMenuBar = new ClassCSS();
        
	private AnalysisDB analysisDB;
	private DataGroupAnalysis dataGroup;
	
	private String email;
        
       
	
	public Analysis(String email,String title) {
		super("ss/SurveyServer",title,"Analysis");

		analysisDB= new AnalysisDB("ss_user","ss_password","surveyserver_db");
		
		this.email=email;
		
		upperMenuBar = new MenuLinkBar("mainMenuBar",cssMenuBar);
		menuEnquestes = new MenuLinkBar("llistaEnquestes",cssMenuBar);
		menuEnquestes.setVertical();
		
		this.initMenu();
		
		this.LlistaEnquestes();
		this.addBodyData(menuEnquestes.toHtml());
		
		
	}
	
	private void initMenu(){
		upperMenuBar.addMenuLink(LNK_MARXAR,"Marxar del programa","#","vols_marxar","'marxar'","Si vols marxar.. tu mateix");
                
	}
	public void tornar(){
		upperMenuBar.eliminaOption(LNK_TORNAR);
		dataGroup=null;
		this.LlistaEnquestes();
		this.addBodyData(menuEnquestes.toHtml());
		
	}
	public void viewData(String ide){
		menuEnquestes.clear();
		upperMenuBar.addMenuLink(LNK_TORNAR, "Tornar", "#","fes","'tornar','0'","Cancel·la i torna al menú inicial.");
		dataGroup= new DataGroupAnalysis(ide);
		this.addBodyData(dataGroup.toHtml());
	}
	public String toHtml(){
		
		cssStyles.addFileCSS(CSS_VIEWER_FILE_NAME);
		this.updateFunctions();
		vsFunctions.addFunction("vols_marxar","actions","if(confirm(\"Segur que vols Marxar?\"))fes(actions,'')");
			
		vsFunctions.addFile(JS_VIEWER_FILE_NAME);
		
		this.addBanner(this.email);
		this.addDataForm("<input type=\"hidden\" name=\"where\" value=\"analysis\"/>");
		
		
		
		this.addBodyData(upperMenuBar.toHtml());
		
		
		
		String ret=this.getHtml();
		
		return(ret);
	}
	
	public void LlistaEnquestes(){
		String ide;
		String nome;
		
		ResultSet myResult = analysisDB.getLlista(email);
		try {
			while(myResult.next()){
				 ide = myResult.getString("id_enquesta");
			     nome = myResult.getString("nm_enquesta");
			     menuEnquestes.addMenuLink("a_"+ide, nome , "#","fes","'viewData','"+ide+"'", "Fes clic per consultar les respostes de 1l`enquesta");
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		menuEnquestes.setTitle("Enquestes publicades.");
	}
}
