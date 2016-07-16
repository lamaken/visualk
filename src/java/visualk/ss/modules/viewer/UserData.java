/**
 * 
 */
package ss.modules.viewer;

import ss.objects.DivHtml;
import ss.objects.LinkHtml;

/**
 * @author alex
 *
 */
public class UserData {
	
	
	private boolean anonim;
	
	
	private String email	="";
	private String password="";
	private String nom="";
	private String cognom="";
	
	private String html="";
	
	
	public void setAnonim(boolean anonim) {
		this.anonim = anonim;
	}
	public boolean isAnonim() {
		return anonim;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	public String getHtml() {
		return html;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getCognom() {
		return cognom;
	}
	public void setCognom(String cognom) {
		this.cognom = cognom;
	}
	
	
	
	
	public void genHtmlUser(){
		
		
		String a_segueix = new DivHtml("a_segueix_div").toHtml(new LinkHtml("a_segueix","Ves a l`enquesta","#","fes","'continua',''","No guardis les dades i salta  la pàgina.").toHtml());
		String a_guarda = new DivHtml("a_guarda_div").toHtml(new LinkHtml("a_guarda","Guarda i segueix","javascript:document.fUserData.submit()","Guarda les dades i continua.").toHtml());
	
		
		
		String html_email="";
		String html_passw="";
		
		if(isAnonim()){
				html_email = "<input size=\"30\" name=\"email\" value=\""+getEmail()+"\">";
				html_passw="<tr>"+	
			      "<td style=\"text-align: right;vertical-align: top;\" >Clau d'accès</td>"+
			      "<td style=\"vertical-align: center;\" ><input type=\"\" size=\"30\" name=\"password\" value=\""+getPassword()+"\"></td>"+
			    "</tr>";
		}
		else html_email = getEmail();
		
		
		
		
		
		
		this.setHtml("<form method=\"post\" action=\"/surveyserver/SurveyServer\" name=\"fUserData\">" +
				"<table border=\"0\" style=\"background-color:gray\" width=\"100%\" cellpadding=\"5\" cellspacing=\"5\">"+
				"<tr align=\"center\">"+
				"<td colspan=\"2\" rowspan=\"1\">Introdueix les teves dades si ho consideres oportú.</td>"+
				"</tr>"+
				"<tr align=\"center\">"+
				"<td colspan=\"2\" rowspan=\"1\"></td>"+
				"</tr>"+
				"<tr>"+
			      "<td style=\"text-align: right;vertical-align: top;\" >email</td>"+
			      "<td style=\"vertical-align: center;\" >" +
			      html_email+
			      "</td>"+
			    "</tr>"+
			    "<tr>"+
			   "<tr>"+
			      "<td style=\"text-align: right;vertical-align: top;\" >Nom</td>"+
			      "<td style=\"vertical-align: center;\" ><input size=\"30\" name=\"nom\" value=\""+getNom()+"\"></td>"+
			    "</tr>"+
			   "<tr>"+	
			      "<td style=\"text-align: right;vertical-align: top;\" >Cognom</td>"+
			      "<td style=\"vertical-align: center;\" ><input type=\"\" size=\"30\" name=\"cognom\" value=\""+getCognom()+"\"></td>"+
			    "</tr>"+
			    html_passw+
			   "<tr>"+
			    " </tr>"+
				"<tr >"+
				"<td  align=\"center\" >"+a_segueix+"</td>"+
				"<td  align=\"center\" >"+a_guarda+"</td>"+
						
				"</tr>"+
			   "</table>" +
			   "<input name=\"actions\" value=\"guardaUsuari\" type=\"hidden\"/>"+
			   "<input name=\"where\" value=\"view\" type=\"hidden\"/>"+
			   "<input name=\"sec\" value=\"jeo\" type=\"hidden\"/>"+
			   "</form>");
	}
	
	
	public UserData(String email){
		//carrega les dades de la publicacio id_pub
		if(email.contains("@")){
			setAnonim(false);
			this.setEmail(email);
		}
		else setAnonim(true);
		
		//TODO-Load user data if(!isAnonim())LoadUser(email);
		
		genHtmlUser();
		
	}
	
	
	public String toHtml(){
		DivHtml divDataUser = new DivHtml("divDataUser");
		return(divDataUser.toHtml(html));
	}
	
	
	
	
}
