/**
 * 
 */
package visualk.ss.modules.generator;

import java.util.LinkedList;

import ss.objects.DivHtml;
import ss.objects.LinkHtml;


/**
 * @author alex
 *
 */
public class Enquesta {

	private static final String LNK_ANTERIOR_PREG = "anterior_preg";
	private static final String LNK_SEGUENT_PREG = "seguent_preg";
	private static final String LNK_EDIT_PREG= "edit_enquesta";
	private static final String LNK_GUARDA_DADES_ENQ= "guarda_dades_enq";
	private static final String LNK_ELIMINAR_DADES_ENQ= "guarda_dades_enq";
	private static final String LNK_PUBLICAR_DADES_ENQ= "publica_enq";
	
	private String id_enquestaDB;
	private String caption; // titol de l'enquesta
	private String descripcio; // titol de l'enquesta
	
		
	
	
//	private String name;
	private int pregunta_actual;
	private boolean dataeditpage=true;
	
	private LinkedList<Pregunta> preguntes;
	
	
	public LinkedList<Pregunta> getPreguntes(){
		return(preguntes);
	}
	public void guardaDadesEnq(String nom, String desc){
		this.caption=nom;
		this.descripcio=desc;
	}
	
	public void setIdBD(String id){
		this.id_enquestaDB=id;
	}
	public String getCaption(){
		return(caption);
	}
	public boolean editaEnquesta(){
		dataeditpage=!dataeditpage;
		return(dataeditpage);
	}
	public void addCombo(String caption,String name){
		preguntes.get(pregunta_actual-1).addCombo(caption, name);
	}
	
	public void addComboOption(String name,String caption){
		preguntes.get(pregunta_actual-1).addComboOption(name, caption);
	}
	
	public void editObject(String name, String value){
		preguntes.get(pregunta_actual-1).setObjectCaption(name,value);
	}
	public void upObject(String name){
		preguntes.get(pregunta_actual-1).upObject(name);
	}
	public void downObject(String name){
		preguntes.get(pregunta_actual-1).downObject(name);
	}
	public void deleteObject(String name){
		preguntes.get(pregunta_actual-1).deleteObject(name);
	}
	public void addLabel(String caption,String name){
		preguntes.get(pregunta_actual-1).addLabel(caption,name);
	}
	public void addTextBox(String caption,String name){
		preguntes.get(pregunta_actual-1).addTextBox(caption,name);
	}
	/*public int loadEnquesta(String id){
		return (0);
	}*/
	public int count(){
		return(preguntes.size());
	}
	public int guardaEnquesta(){
		return(0);
	}
	public void anterior(){
		pregunta_actual-=1;
		if(pregunta_actual<1)pregunta_actual=1;
	}
	public void primera(){
		if(preguntes.size()>0)pregunta_actual=1;
	}
	public void seguent(){
		pregunta_actual+=1;
		if(pregunta_actual>preguntes.size())pregunta_actual=preguntes.size();
	
	}
	public void addPregunta(String id){
		dataeditpage=false; //passem a editar les preguntes i no l'enquesta
		preguntes.add(new Pregunta(id));
		pregunta_actual = preguntes.size();
	}
	public void eliminaPregunta(){
		preguntes.remove(pregunta_actual-1);
		pregunta_actual=preguntes.size();
	}
	public Enquesta(String caption,String desc,String id_enquesta) {
		preguntes= new LinkedList<Pregunta>();
		pregunta_actual=0;
		this.caption=caption;
		this.descripcio=desc;
		this.id_enquestaDB=id_enquesta;

	}
	public String getIdEnquestaDB(){
		return(this.id_enquestaDB);
	}
	
	public String divEnquesta(String xform){
		String div;
		String seguent="";
		String anterior="";
		String posicio="";
		String editEnq="";
		
		
		
		
		
		editEnq=new LinkHtml(LNK_EDIT_PREG,this.caption,"#","fes","'editaEnquesta',''","Edita dades de l`enquesta").toHtml();
		
		
		if(pregunta_actual>1)anterior=new LinkHtml(LNK_ANTERIOR_PREG,"&lt;&lt;anterior","#","fes","'panterior','1'","Pregunta anterior").toHtml();
		if(pregunta_actual<preguntes.size())seguent= new LinkHtml(LNK_SEGUENT_PREG,"seguent&gt;&gt;","#","fes","'pseguent','1'","Pregunta següent").toHtml();
		posicio=pregunta_actual+"/"+preguntes.size();
		
		DivHtml divNomEnquesta= new DivHtml("divNomEnquesta");
		DivHtml divPosEnquesta= new DivHtml("divPosEnquesta");
		DivHtml divAntEnquesta= new DivHtml("divAntEnquesta");
		DivHtml divSegEnquesta= new DivHtml("divSegEnquesta");
		DivHtml divCanvas= new DivHtml("divCanvas");
		
		
		div=divNomEnquesta.toHtml(editEnq);
		div+=divPosEnquesta.toHtml(posicio);
		div+=divAntEnquesta.toHtml(anterior);
		div+=divSegEnquesta.toHtml(seguent);
		div+=divCanvas.toHtml(xform);
		return(div);
	}
	public String getDescripcio(){
		return(this.descripcio);
	}
	
	public String formDades(){
		String html ="";
		String editEnq="";
		if(this.caption==null)this.caption="";
		if(this.descripcio==null)this.descripcio="";

		html="<form name=\"fdadesEnq\">"+
		"<table border=0 style=\"background-color:gray\" width=\"100%\" height=\"100%\"  cellpadding=\"5\" cellspacing=\"5\">"+
		"<tr align=\"center\">"+
	    "<td colspan=\"2\" rowspan=\"1\">Dades de l`enquesta</td>"+
	    "</tr>"+
		    "<tr>"+
			      "<td style=\"text-align: right;vertical-align: top;\" >Nom :</td>"+
			      "<td style=\"vertical-align: top;\" ><input size=\"30\" name=\"nom\" value=\""+this.caption+"\"></td>"+
			    "</tr>"+
			    "<tr>"+
			      "<td style=\"text-align: right;vertical-align: top;\" >Descripció:</td>"+
			      "<td style=\"vertical-align: top;\" ><textarea cols=\"30\" rows=\"4\" name=\"desc\">"+ 
			      		this.descripcio+
			      		"</textarea>"+
			    "  </td>"+
			   " </tr>"+
			 " </tbody>"+
			"</table></form>"+
			"<center>" + 
			"<table style=\"background-color:silver\" borde=0 width=\"100%\" cellpadding=\"5\" cellspacing=\"5\"><tr><td style=\"text-align: left;\">"+
			new LinkHtml(LNK_GUARDA_DADES_ENQ,"Guardar","#","fes2","'guardaDadesEnq',document.fdadesEnq.desc.value,document.fdadesEnq.nom.value","Guarda les dades.").toHtml()+
			"</td>" +
			"<td style=\"text-align: center;\">"+
			new LinkHtml(LNK_PUBLICAR_DADES_ENQ, "Publicar" , "#","question_publica","'publicaEnquesta','"+this.caption+"'", "Fes clic per publicatr l`enquesta.").toHtml()+
			"</td>"+
			"<td style=\"text-align: right;\">"+
			new LinkHtml(LNK_ELIMINAR_DADES_ENQ,"Eliminar","#","fes","'eliminaEnquesta',''","Elimina tota l`enquesta.").toHtml()+
			"</td></tr></table>"+
			"</center>";
		
		if(pregunta_actual>0)editEnq=new LinkHtml(LNK_EDIT_PREG,"Ves a les preguntes","#","fes","'editaEnquesta',''","Ves a les preguntes de l`enquesta.").toHtml();

		DivHtml divVesPreg = new DivHtml("divVesPreg");
		DivHtml divDadesEnquesta = new DivHtml("divDadesEnquesta");	
		
		return(divVesPreg.toHtml(editEnq) +	divDadesEnquesta.toHtml(html));
	}
	public String getHtml(){
		if(pregunta_actual==0)dataeditpage=true;
		
		if(!dataeditpage){
			String xmlform_preg = preguntes.get(pregunta_actual-1).toHtml();
			String div = divEnquesta(xmlform_preg);
			
			return(new DivHtml("divEnquesta").toHtml(div));
		}
		else return (new DivHtml("divEnquesta").toHtml(this.formDades()));
	}
}
