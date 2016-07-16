package visualk.ss.objectsmierdo;


public class Xhtml {

	
	private static final String xmldoc		 = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" ?>";
	private static final String doctype 	 = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">";
	private static final String open_html	 = "<html xmlns=\"http://www.w3.org/1999/xhtml\">";
	
	private static final String open_head 	 = 	"<head>";
	private String head 		 = 	"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\"/>";
	
	private static final String open_title 	 = 	"<title>";
	private String title="default title";
	private static final String close_title  =  "</title>";
	
	public VsFunctions vsFunctions;
	
	public CssStyles cssStyles;
	
	private static final String close_head	 =	"</head>";
	
	
	private static final String open_body	 = "<body onload=\"load()\">";
	private String body="nothing in de body";
	
	private static final String open_form	 = "<form method=\"post\" name=\"fmain\" action=\"/surveyserver/SurveyServer\">";
	private String data_form="";
	
	private static final String close_form	 = "</form>";
	
	private static final String close_body	 = "</body>";
	
	private static final String close_html	 = "</html>";
	
	public MessageBox messageBox;
	
  	
	public Xhtml(String title) {
		this.vsFunctions= new VsFunctions();
		this.cssStyles = new CssStyles();
		
		
		clearBodyData();
		clearDataForm();
		this.title = title;
		
		//creo el messageBox
		messageBox=new MessageBox("");

		updateFunctions();
		
	}
	
	public void updateFunctions(){
		vsFunctions.clear();
		vsFunctions.addFunction("statusBar","content","document.getElementById('statusBar').innerHTML=content;");
		vsFunctions.addFunction("fes", "actions,params", "document.fmain.actions.value=actions;document.fmain.params.value=params;document.fmain.submit();");
		vsFunctions.addFunction("fes2", "actions,params,more", "document.fmain.actions.value=actions;document.fmain.params.value=params;document.fmain.more.value=more;document.fmain.submit();");
		
		vsFunctions.addFunction("messageBox", "data", "alert(data);");
		vsFunctions.addFunction("load", "", this.messageBox.getScript());
	}
	public void clearBodyData(){
		//creo status bar on hi ha el help
		
		this.body="";
		DivHtml statusBar = new DivHtml("statusBar");
		addBodyData(statusBar.toHtml("surveyserver v0.1"));
		
		
	}
	public void addBanner(String email){
		DivHtml emailBar = new DivHtml("emailBar");
		addBodyData(emailBar.toHtml(email));
	}
	public void addBodyData(String body){
		
		this.body+=body;
	}
	public void clearDataForm(){
		this.data_form = "<input type=\"hidden\" name=\"more\"/> <input name=\"actions\" type=\"hidden\"/> <input name=\"params\" type=\"hidden\"/>";
	}
	public void addDataForm(String data){
		this.data_form += data;
	}
	public String getHtml(){
		
		
		String output = "";
		
		
		output += xmldoc;
		//output += pagedoc;
		output += doctype;
		output += open_html;
		output += open_head;
		output += head;
		output += open_title;
		output += this.title;
		output += close_title;
		output += this.cssStyles.toHtml();
		output += this.vsFunctions.toHtml();
		
		output += close_head;
		output += open_body;
		output += open_form;
		output += data_form;
		output += close_form;
		output += this.body;
		output += close_body;
		output += close_html;
		return (output);
	}
}
