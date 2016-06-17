package visualk.hrz.modules;


import java.sql.ResultSet;
import java.sql.SQLException;


import visualk.hrz.Hrz;
import visualk.hrz.db.DbHorizons;
import visualk.hrz.objects.Horizon;
import visualk.html.ClassCSS;
import visualk.html.DivHtml;
import visualk.html.MenuBar;
import visualk.html.UniqueName;
import visualk.html.Xhtml;

public class ListHorizons extends Xhtml{

	private static final String CSS_LIST_FILE_NAME="/visualk/hrz/css/listhorizons.css";
	private static final String JS_LIST_FILE_NAME="/visualk/hrz/js/listhorizons.js";
	
	private MenuBar upperMenuBar;
	
	
	
	
   private final String googlePlusTagBefore="<g:plusone href=\"";
   private final String googlePlusAfter="\" size=\"small\" count=\"false\"></g:plusone>";
   
   private final String facebookLikeTagBefore="<fb:like href=\"";
   private final String facebookLikeTagAfter="\" layout=\"button\" show-faces=\"true\" action=\"like\" colorscheme=\"light\" />";
   
	
	public ListHorizons(String title) {
		super(title,Hrz.SERVELT_URL);
		ClassCSS cssLink = new ClassCSS();
		cssLink.setColor("yellow");
		
		ClassCSS cssMenuBar = new ClassCSS();
		cssMenuBar.setColor("green");
		
		upperMenuBar = new MenuBar("tornarBar",cssMenuBar);
		upperMenuBar.setHorizontal();
		upperMenuBar.addMenuLink(Hrz.getString("back.gallery.hrzmkr"),"torna",Hrz.getString("help.back.gallery.hrzmkr"),cssLink);//label,function,help
		
		cssStyles.addStyle(cssMenuBar);
		
		cssStyles.addStyle(cssLink);
		cssStyles.addFileCSS(CSS_LIST_FILE_NAME);
		
	}
	public String toHtml(){
		String html_image="";
		
		//this.updateFunctions();
		this.clearBodyData();
		this.clearDataForm();
		
		
		this.updateFunctions();
		
		
		
		vsFunctions.addFile(JS_LIST_FILE_NAME);
		
		this.addDataForm("<input type=\"hidden\" name=\"where\" value=\"listhorizons\"/>");
		this.addDataForm("<input type=\"hidden\" name=\"what\" value=\"\"/>");
		this.addDataForm("<input type=\"hidden\" name=\"nom\" value=\"\"/>");
		
		
		
		this.addBodyData(upperMenuBar.toHtml());
		
		
	    DbHorizons db = new DbHorizons(); //connexio a la BD
		ResultSet rs = db.listHrzns();
		String namehrz="";
		String table="";
		String tds="";
		
		try {
			table="<table border=0 cellspacing=10px padding=10px><tr>";
			while(rs.next()){
				namehrz=rs.getString("nameHrz");
				
				Horizon hrz= new Horizon(namehrz);
				hrz.carrega(namehrz);
				String id_div = new UniqueName(8).getName();
		    	
				html_image=  	"<img "+
		    					"title=\""+hrz.getAuthorHrz()+"\" " +
		    					"alt=\"carregant..\" " +
		    					"onclick=\"selecciona('"+id_div+"')\" " +
		    					"ondblclick=\"edita('"+namehrz+"')\" " +
		    					"src=\"/visualk/hrz/Hrz?option=paint&amp;namehrz="+namehrz+"\"/>";
				
				String html_google = googlePlusTagBefore+"http://hrzmkr.com/servlet/Hrz?option=paint&amp;namehrz="+namehrz+googlePlusAfter;
				
				String html_facebook = facebookLikeTagBefore+"http://hrzmkr.com/servlet/Hrz?option=paint&amp;namehrz="+namehrz+facebookLikeTagAfter;
				
				
				tds += "<td>"+ new DivHtml(id_div).toHtml(html_image+html_google+html_facebook)+"</td>";
				
			}
			table += tds+"</tr></table>";
		 		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.addBodyData(new DivHtml("HrzListDiv").toHtml(table));
		String ret=this.getHtml();
                db.disconnect();
		return(ret);
	}

}
