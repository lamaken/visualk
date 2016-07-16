/**
 * 
 */
package ss.modules.analysis;

import java.sql.ResultSet;
import java.sql.SQLException;

import ss.db.AnalysisDB;
import ss.objects.DivHtml;

/**
 * @author alex
 *
 */
public class DataGroupAnalysis {

	private String html;
	private String id_enq;
	private AnalysisDB analysisDB;
	
	
	public DataGroupAnalysis(String id_enq){
		this.id_enq=id_enq;
		analysisDB=new AnalysisDB();
	}
	
	private void genTable(){
		ResultSet myResult=analysisDB.getPreguntes(this.id_enq);
		int c=0;
		String caption="";
		String id_tipus="";
		String id_tp="";
		int tipus = 0;
		
		html	= "<table style=\"width: 100%;\">";
		try {
			while(myResult.next()){
				 caption = myResult.getString("caption");
				 id_tipus = myResult.getString("id_tipus");
				 id_tp = myResult.getString("id_tp");
				 
				 tipus =Integer.parseInt(id_tipus); 
				 if(tipus!=2){//si no es un label
					 c++;
				     
					 html+="<tr>"+
					 "<td style=\"color:navy;text-align:right;\">"+c+"</td>"+
					 "<td style=\"color:blue;text-align:left;\">"+caption+"</td>"+
					 "<td></td>"+
					 "</tr>";
					 
					 ResultSet rs = analysisDB.getRespostes(tipus, id_tp);
					 while(rs.next()){
						 String usuari = rs.getString("usuari");
						 String resposta = rs.getString("resposta");
						 
						 html+="<tr>"+
						 "<td></td>"+
						 "<td  style=\"text-align:left;\">"+resposta+"</td>"+
						 "<td  style=\"text-align:left;\">"+usuari+"</td>"+
						 "</tr>";
						}
					 
				 }
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		 
	  html+="</table>";
	 
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  /*
	   * 
	   * select 
a.respuesta as respuesta,
a.usuari as usuari
from
respuestas a
where
a.id_tp = "id_tp"
	   */
	}
	
	
	public String toHtml(){
		genTable();
		return(new DivHtml("divData").toHtml(html));
	}
	
}
