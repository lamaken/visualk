package ss.modules.generator;

import java.sql.ResultSet;
import java.sql.SQLException;

import ss.db.PublicacionsDb;
import ss.objects.DivHtml;
import ss.objects.LinkHtml;

public class Publicacions {
	
	private String id_pub;
	
	private String html;
	private String nom;
	
	private String desc;
	private String dt_start;
	private String dt_end;
	private String code;
	private String id_grup="";
	
	
	private boolean activada;
	private boolean anonymous=true;
	
	private String listEmails="";
	private PublicacionsDb pubDB;
	
	
	public void genHtml(){
	
		
		String isanon="";
		String isnotanon="";
		
		if(isAnonymous())isanon="checked";
		else isnotanon="checked";
		
		String a_nom = new LinkHtml("nom_pub_anch","Nom:","#","Nom de l`enquesta publicada").toHtml();
		String a_desc = new LinkHtml("desc_pub_anch","Descripció:","#","Descripció breu de l`enquesta publicada").toHtml();
		String a_dt_start = new LinkHtml("dts_pub_anch","Data inici:","#","Data a partir de la qual l`enquesta estarà activa, FORMAT (AAAA-MM-DD)").toHtml();
		String a_dt_end = new LinkHtml("dte_pub_anch","Data fí:","#","Data finalització de l`enquesta publicada,  FORMAT (AAAA-MM-DD)").toHtml();
		String a_anonymous = new LinkHtml("anony_pub_anch","Anònima:","#","Si permet l`accès anònim").toHtml();
		String a_listEmails = new LinkHtml("lst_pub_anch","Llista d'emails:","#","Llista d`emails (separats per comes) que realitzaràn l`enquesta publicada").toHtml();
		String a_estat = new LinkHtml("estat_pub","Estat de l`enquesta:","#","Indica si l`enquesta està activa o innactiva.").toHtml();
		
		
		String a_activa = new DivHtml("a_activa_div").toHtml(new LinkHtml("activa_pub","Activar","#","fes","'activapub',''","Activa l`enquesta per a poder ser contestada.").toHtml());
		String a_desactiva = new DivHtml("a_activa_div").toHtml(new LinkHtml("desactiva_pub","Desactivar","#","fes","'desactivapub',''","Desactiva l`enquesta.").toHtml());
		
		   
		String desactivada_o_activada ="";
		if(isActivada())desactivada_o_activada ="<td style=\"text-align: right;vertical-align: top;\" >"+a_estat+"</td>"+
												"<td style=\"vertical-align: top;\" ><b>ACTIVA! </b>   "+a_desactiva+
												"</td>";
		else desactivada_o_activada ="<td style=\"text-align: right;vertical-align: top;\" >"+a_estat+"</td>"+
		      						"<td style=\"vertical-align: top;\" ><b>DESACTIVADA  </b>  "+a_activa+
		      						"</td>";
		   

		
		String a_guarda = new DivHtml("a_guarda_div").toHtml(new LinkHtml("a_guarda","Guardar","javascript:document.fPub.submit()","Guarda els canvis.").toHtml());
		String a_tanca = new DivHtml("a_tancar_div").toHtml(new LinkHtml("a_tanca","Tornar","#","fes","'closePub',''","Torna pantalla anterior sense guardar.").toHtml());
		
		this.setHtml("<form method=\"post\" action=\"/surveyserver/SurveyServer\" name=\"fPub\">" +
				"<table border=\"0\" style=\"background-color:gray\" width=\"100%\" cellpadding=\"5\" cellspacing=\"5\">"+
				"<tr align=\"center\">"+
				"<td colspan=\"2\" rowspan=\"1\">Dades de l`enquesta <b>publicada</b></td>"+
				"</tr>"+
				"<tr>"+
			      "<td style=\"text-align: right;vertical-align: top;\" >"+a_nom+"</td>"+
			      "<td style=\"vertical-align: top;\" ><input size=\"30\" name=\"nom\" value=\""+this.nom+"\"></td>"+
			    "</tr>"+
			    "<tr>"+
			      "<td style=\"text-align: right;vertical-align: top;\" >"+a_desc+"</td>"+
			      "<td style=\"vertical-align: top;\" ><textarea cols=\"30\" rows=\"4\" name=\"desc\">"+ 
			      		this.desc+"</textarea>"+
			    "  </td>"+
			   " </tr>"+
			   "<tr>"+
			      "<td style=\"text-align: right;vertical-align: top;\" >"+a_dt_start+"</td>"+
			      "<td style=\"vertical-align: top;\" ><input size=\"10\" name=\"dt_start\" value=\""+this.dt_start+"\"></td>"+
			    "</tr>"+
			   "<tr>"+	
			      "<td style=\"text-align: right;vertical-align: top;\" >"+a_dt_end+"</td>"+
			      "<td style=\"vertical-align: top;\" ><input type=\"\" size=\"10\" name=\"dt_end\" value=\""+this.dt_end+"\"></td>"+
			    "</tr>"+
			    "<tr>"+
			      "<td style=\"text-align: right;vertical-align: top;\" >"+a_anonymous+"</td>"+
			      "<td style=\"vertical-align: top;\" ><input "+isanon+" value=\"1\" name=\"anonima\" type=\"radio\"/></td>"+
			    "</tr>"+
			    "<tr>"+
			      "<td style=\"text-align: right;vertical-align: top;\" >"+a_listEmails+"</td>"+
			      "<td  >" +
			      "<div style=\"float:left;vertical-align: top;\"><input "+isnotanon+" value=\"0\" name=\"anonima\" type=\"radio\"/></div>" +
			      "<div style=\"float:left;vertical-align: top;\"><textarea cols=\"30\" rows=\"4\" name=\"emails\">"+this.listEmails+"</textarea></div>"+
			    "  </td>"+
			   " </tr>"+
			   "<tr>"+
			   desactivada_o_activada+
			   " </tr>"+
				"<tr align=\"center\">"+
				"<td style=\"color:red;text-align: left;vertical-align: top;\">"+a_guarda+"</td>"+
				"<td style=\"text-align: right;vertical-align: top;\">"+a_tanca+"</td>"+
				"</tr>"+
			   "</table>" +
			   "<input name=\"actions\" value=\"guardaPub\" type=\"hidden\"/>"+
			   "<input name=\"where\" value=\"generator\" type=\"hidden\"/>"+
			   "<input name=\"sec\" value=\"jeo\" type=\"hidden\"/>"+
			   "</form>");
	}
	public void loadDb(){
		pubDB= new PublicacionsDb();
		ResultSet myResult = pubDB.loadPub(this.id_pub);
		setNom("Error loading name ...");
		try {
			while(myResult.next()){
				 setNom(myResult.getString("nom"));
				 setDesc(myResult.getString("descr"));
				 setCode(myResult.getString("codi"));
				 setAnonymous((myResult.getString("anonima").equals("1")));
				 setDt_start(myResult.getString("dt_start"));
				 setDt_end(myResult.getString("dt_end"));
				 setActivada((myResult.getString("activa").equals("1")));
				 setId_grup(myResult.getString("grup"));
				 
				 if(getDt_end()==null)setDt_end("4000/01/01");
				 if(getDt_start()==null)setDt_start("4000/01/01");
				  
			}
				 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loadUsuaris();
	}
	private void loadUsuaris(){
		String idg = getId_grup();
		if(idg!=null){
			ResultSet myResult = pubDB.loadUsuaris(idg);
			setListEmails("");
			try {
				while(myResult.next()){
					 this.listEmails+=myResult.getString("email")+",";
				}
					 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	public void activa(){
		
		pubDB.activa(this.getId_pub());
		loadDb();
		genHtml();
	}
	public void desactiva(){
		pubDB.desactiva(this.getId_pub());
		loadDb();
		genHtml();
	}
	public void guardaPub(String nom,String desc,String dt_start,String dt_end,String anonima,String emails){
		
		setNom(nom);
		setDesc(desc);
		setDt_start(dt_start);
		setDt_end(dt_end);
		setListEmails(emails);
		
		setAnonymous((anonima.equals("1")));
		
		
		this.pubDB.guardaDB(this);
		this.pubDB=null;
		this.loadDb();
		genHtml();
		
	}
	public Publicacions(String id_pub){
		//carrega les dades de la publicacio id_pub
		setId_pub(id_pub);
		this.loadDb();
		genHtml();
	}
	
	public void elimina(){ //marcarà un camp a deleted
		
	}
	
	public String toHtml(){
		DivHtml divPublicacions = new DivHtml("divPublicacions");
		return(divPublicacions.toHtml(html));
	}
	public String getId_pub() {
		return id_pub;
	}
	public void setId_pub(String id_pub) {
		this.id_pub = id_pub;
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getDt_start() {
		return dt_start;
	}
	public void setDt_start(String dt_start) {
		this.dt_start = dt_start;
	}
	public String getDt_end() {
		return dt_end;
	}
	public void setDt_end(String dt_end) {
		this.dt_end = dt_end;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public boolean isAnonymous() {
		return anonymous;
	}
	public void setId_grup(String id_grup) {
		this.id_grup = id_grup;
	}
	public String getId_grup() {
		return id_grup;
	}
	public void setAnonymous(boolean anonymous) {
		this.anonymous = anonymous;
	}
	public String getListEmails() {
		return listEmails;
	}
	public void setListEmails(String listEmails) {
		this.listEmails = listEmails;
	}

	
	public boolean isActivada() {
		return activada;
	}
	public void setActivada(boolean activada) {
		this.activada = activada;
	}
}
