/**
 * 
 */
package visualk.ss.modules.viewer;

import java.util.LinkedList;

import visualk.ss.modules.viewer.Pregunta;

import visualk.html5.DivHtml;
import visualk.html5.LinkHtml;


/**
 * @author alex
 *
 */
public class FluxeSurvey { 

	private static final String LNK_ANTERIOR_PREG = "anterior_preg";
	private static final String LNK_SEGUENT_PREG = "seguent_preg";
	private static final String LNK_FIN_PREG = "fin_preg";
	
	private String id_enquestaDB;
	private String caption; // titol de l'enquesta
	private String descripcio; // titol de l'enquesta
	
	private int pregunta_actual;
	
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
	
	public void addCombo(String caption,String name,String id_tp){
		preguntes.get(pregunta_actual-1).addCombo(caption, name,id_tp);
	}
	
	public void addComboOption(String name,String caption){
		preguntes.get(pregunta_actual-1).addComboOption(name, caption);
	}
	
	public void addLabel(String caption,String name,String id_tp){
		preguntes.get(pregunta_actual-1).addLabel(caption,name,id_tp);
	}
	public void addTextBox(String caption,String name,String id_tp){
		preguntes.get(pregunta_actual-1).addTextBox(caption,name,id_tp);
	}
	
	public int count(){
		return(preguntes.size());
	}
	public int guardaEnquesta(){
		return(0);
	}
	public void saveresposta(LinkedList<String> respostes){
		preguntes.get(pregunta_actual-1).setRespostes(respostes);
	}
	public void anterior(){
		pregunta_actual-=1;
		if(pregunta_actual<1)pregunta_actual=1;
	}
	public void primera(){
		if(preguntes.size()>0)pregunta_actual=1;
	}
	public boolean seguent(){
		pregunta_actual+=1;
		if(pregunta_actual>preguntes.size())return(false);
		return(true);
	
	}
	public void addPregunta(String id){
		preguntes.add(new Pregunta(id));
		pregunta_actual = preguntes.size();
	}

	public FluxeSurvey(String caption,String desc,String id_enquesta) {
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
		String finalitzar = "";
		
		if(pregunta_actual>1)anterior=new LinkHtml(LNK_ANTERIOR_PREG,"&lt;&lt;anterior","javascript:document.fPagina.actions.value='panterior';document.fPagina.submit();","Pregunta anterior").toHtml();
		if(pregunta_actual<preguntes.size())seguent= new LinkHtml(LNK_SEGUENT_PREG,"seguent&gt;&gt;","javascript:document.fPagina.actions.value='pseguent';document.fPagina.submit();","Pregunta següent").toHtml();
		
		if(pregunta_actual==preguntes.size())finalitzar = new LinkHtml(LNK_FIN_PREG,"finalitzar","javascript:document.fPagina.actions.value='pseguent';document.fPagina.submit();","Finalitzar l`enquesta").toHtml();
		
		
		posicio=pregunta_actual+"/"+preguntes.size();
		
		DivHtml divNomEnquesta= new DivHtml("divNomEnquesta");
		DivHtml divPosEnquesta= new DivHtml("divPosEnquesta");
		DivHtml divAntEnquesta= new DivHtml("divAntEnquesta");
		DivHtml divSegEnquesta= new DivHtml("divSegEnquesta");
		DivHtml divFinEnquesta= new DivHtml("divFinEnquesta");
		DivHtml divCanvas= new DivHtml("divCanvas");
		
		div = divNomEnquesta.toHtml(this.caption);
		div+=divPosEnquesta.toHtml(posicio);
		div+=divAntEnquesta.toHtml(anterior);
		div+=divSegEnquesta.toHtml(seguent);
		div+=divFinEnquesta.toHtml(finalitzar);
		div+=divCanvas.toHtml(xform);
		return(div);
	}
	public String getDescripcio(){
		return(this.descripcio);
	}
	
	public String toHtml(){
		String xmlform_preg = preguntes.get(pregunta_actual-1).toHtml();
		String div = divEnquesta(xmlform_preg);
		return(new DivHtml("divEnquesta").toHtml(div));
	}
}










