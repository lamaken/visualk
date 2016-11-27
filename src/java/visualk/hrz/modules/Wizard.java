package visualk.hrz.modules;



import visualk.html5.*;


public class Wizard extends Xhtml5{

	private static final String CSS_WIZARD_FILE_NAME="/hrz/css/wizard.css";
	private static final String JS_WIZARD_FILE_NAME="/hrz/js/wizard.js";
	
		
	private MenuLinkBar upperMenuBar;
	private MenuLinkBar leftMenuBar;
	
	
	public Wizard(String title) {
		super("hrz/Hrz",title,"Wizard");
		
		ClassCSS cssLink = new ClassCSS();
		
		cssLink.setColor("yellow");
		
	/**	upperMenuBar = new MenuLinkBar("marxarBar");
		upperMenuBar.addMenuLink("Marxar","vols_marxar","Marxar del wizard",cssLink);//label,function,help
		
		leftMenuBar = new MenuLinkBar("optionsBar");
		leftMenuBar.setVertical();
		leftMenuBar.addMenuLink("Genera!!","refresca","En genera un a l`arzar!",cssLink);//label,function,help
		*/
	}
	
	private String testSVG(){
	

		return("<iframe align=\"center\" width=\"50%\" frameborder=\"0\" height=\"100%\" src=\"/visualk/hrz/Hrz?option=svg\"/>"); 

	}
	
	public String toHtml(){
		this.updateFunctions();
		this.clearBodyData();
		this.clearDataForm();
		cssStyles.addFileCSS(CSS_WIZARD_FILE_NAME);
		this.updateFunctions();
		vsFunctions.addFile(JS_WIZARD_FILE_NAME);
		this.addDataForm("<input type=\"hidden\" name=\"where\" value=\"wizard\"/>");
		this.addDataForm("<input type=\"hidden\" name=\"what\" value=\"\"/>");
		
		this.addBodyData(upperMenuBar.toHtml());
		this.addBodyData(leftMenuBar.toHtml());
		
		
		this.addBodyData(new DivHtml("HrzCanvasDiv").toHtml(testSVG()));
		
		
		
		
	
		
		
		String ret=this.getHtml();
		return(ret);
	}
	
}