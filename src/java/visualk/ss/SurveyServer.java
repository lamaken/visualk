package visualk.ss;

import java.io.*;
import java.util.LinkedList;

import javax.servlet.*;
import javax.servlet.http.*;


import ss.db.Auth;
import ss.modules.Generator;
import ss.modules.Viewer;


import ss.objects.UniqueName;

/**
 * Servlet implementation class SurveyServer
 */
public class SurveyServer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	      
	private Generator ssGenerator;
	private Viewer ssViewer;
	private boolean acepted_login;	//acces
	private boolean acepted_code;	//codi enquestes anonimes
	
    public SurveyServer() {
        super();
      //  SendEmail smail = new SendEmail();
       // smail.sendmail("alex.camps@yahoo.es","code");
        acepted_login = false;
        acepted_code = false;
    }
    
    
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out;
		boolean error = false;
		boolean login_error = false;
		
		String html="";
		
		LinkedList<String> resp= new LinkedList<String>(); 
		resp.clear();
		
		String where =request.getParameter("where"); 
		
		//recupera nom i passw si prove de la pagina inicial
		String login_name =request.getParameter("login_name");
		String login_pass =request.getParameter("login_pass");
		
		if ((login_name == null)||(login_name.equals(""))) login_name="anonimous";
		if ((login_pass == null)||(login_pass.equals(""))) login_pass="anonimous";
	
		String actions = request.getParameter("actions"); 	//action.. add, remove, change...
		String params = request.getParameter("params");		//name
		String more= request.getParameter("more");
		String newname = new UniqueName(8).getName();
		
		if 	(actions	== null) actions = "";
		if 	(params 	== null) params = "";
		if 	(more		== null) more = "";
	    if	(where		== null) where = "";
	    
	  
	    ///////generator
	    if((!where.equals("view"))&&(acepted_login==false)) { 
	    	 acepted_login = new Auth().canEnter(login_name,login_pass);
	    }
	    
	    ///////viewer
	    if((!where.equals("generator"))&&(acepted_code==false)){
	    	acepted_code = new Auth().isEmailActive(login_name, login_pass);
	    	if(login_name.equals("anonimous"))acepted_code=true;
	    	System.out.print(login_name);
	    }
	
	    if((acepted_code)&&(where.equals("view"))){ //
	    	if(actions.equals("start")){
	    		ssViewer = new Viewer(login_name,newname);
	    	}
	    	else if(actions.equals("continua")){
	    		ssViewer.continua();
	    	}
	    	//carrega enquesta
			else if (actions.equals("loadEnquesta")){
				ssViewer.inicia(params);
	    	}
	    	//move throw questions
			else if (actions.equals("panterior")){
				int c = Integer.parseInt(request.getParameter("count"));
				for(int n=1;n<=c;n++)resp.add(request.getParameter("object_"+n));
				ssViewer.anterior(resp);
			}
			else if (actions.equals("pseguent")){
				int c = Integer.parseInt(request.getParameter("count"));
				for(int n=1;n<=c;n++)resp.add(request.getParameter("object_"+n));
				ssViewer.seguent(resp);
			}
			else if(actions.equals("marxar")){
	    		acepted_code=false;
				response.sendRedirect("/surveyserver/index.html");
	    	}
	    	else error = true;
	    	
	    	
	    	if(!error){
				html=ssViewer.toHtml();	
			}
	    	
	    	
	    	
	    }
	    
	  
	    
	    //////////////////////////////////////////////////////////
	    //////// control generador d'enquestes i publicacions ////
	    //////////////////////////////////////////////////////////
	    else if((acepted_login)&&(where.equals("generator"))){ //
			
			
			//start
			if (actions.equals("start")) {
				ssGenerator = new Generator(login_name,params);
			}
			//new data
			else if (actions.equals("novaEnquesta")) {
				ssGenerator.novaEnquesta(params); //caption
			}
			
			//carrega enquesta
			else if (actions.equals("loadEnquesta")){
				ssGenerator.loadEnquesta(params);
			}
			//elimina enquesta
			else if (actions.equals("eliminaEnquesta")){
				ssGenerator.eliminaEnquesta();
			}
			
			//carrega l'enquesta publicada
			else if(actions.equals("loadEnquestaPub")){
				ssGenerator.loadEnquestaPub(params);
			}
			//guarda l'enquesta publicada
			else if (actions.equals("guardaPub")) {
				String nom= request.getParameter("nom");
				String desc= request.getParameter("desc");
				String dt_start= request.getParameter("dt_start");
				String dt_end= request.getParameter("dt_end");
				String anonima= request.getParameter("anonima");
				String llista= request.getParameter("llista");
				String emails= request.getParameter("emails");
				ssGenerator.guardaPub(nom,desc,dt_start,dt_end,anonima,llista,emails); 
			}
			else if(actions.equals("activapub")){
				ssGenerator.activaPub(); 
			}
			else if(actions.equals("desactivapub")){
				ssGenerator.desactivaPub(); 
			}
			//tanca l'enquesta publicada
			else if (actions.equals("closePub")) {
				ssGenerator.closePub(); 
			}
			
			//edita enquesta
			else if (actions.equals("editaEnquesta")) {
				ssGenerator.editaEnquesta(); //caption, uniquename
			}
			//publica l'enquesta
			else if (actions.equals("publicaEnquesta")) {
				ssGenerator.publicala(params); //caption, uniquename
			}
			
			else if (actions.equals("novaPreg")) {
				ssGenerator.novaPregunta(newname);
			} 
			
			//close survey
			else if (actions.equals("tancaEnquesta")) {
				if(params.equals("1"))ssGenerator.guardaEnquesta();
				ssGenerator.tancaEnquesta(params);
			}
			//delete object
			else if (actions.equals("deleteObject")) {
				ssGenerator.deleteObject(params);
			}
			
			//add object
			else if (actions.equals("addLabel")) {
				ssGenerator.addLabel(params,newname);
			}
			else if(actions.equals("addTextBox")){
				ssGenerator.addTextBox(params,newname);
			}
			else if(actions.equals("addCombo")){
				ssGenerator.addCombo(params,newname);
			}
			else if(actions.equals("addComboOption")){
				ssGenerator.addComboOption(more,params);
			}
			//edit object
			else if(actions.equals("editObject")){
				ssGenerator.editObjectForm(more,params);//(name,newval)
			}
			else if(actions.equals("upObject")){
				ssGenerator.upObject(params);//(name,newval)
			}
			else if(actions.equals("downObject")){
				ssGenerator.downObject(params);//(name,newval)
			}
			//elimina pregunta
			else if (actions.equals("pelimina")){
				ssGenerator.eliminaPreg();
			}
			//guarda dades enquesta
			else if (actions.equals("guardaDadesEnq")){
				ssGenerator.guardaDadesEnq(more,params);
			}
			
			//carrega enquesta
			else if (actions.equals("guardaDadesEnq")){
				ssGenerator.guardaDadesEnq(more,params);
			}
			
			//move throw questions
			else if (actions.equals("panterior")){
				ssGenerator.anteriorPreg();
			}
			else if (actions.equals("pseguent")){
				ssGenerator.seguentPreg();
			}
			else if(actions.equals("marxar")){
				acepted_login=false;
				response.sendRedirect("/surveyserver/index.html");
			}
			else error = true;
			
		
			if(!error){
				html=ssGenerator.toHtml();	
			}
		
			
				
		}
		else login_error =true;
		
		
	     
	    if(error) System.out.println("!error!, where:"+where+" actions:"+actions+" params:"+params);
		else System.out.println("debug: where:"+where+" actions:"+actions+" params:"+params+ "more:"+more);
	    
	    if(error) html="!error!, where:"+where+" actions:"+actions+" params:"+params;
	
	    if(login_error){
	    	//html  = errorPage.toHtml()
	    	html = "<center>Accès no permès.<br/><br/><a href=\"index.html\">tornar<a></center>";
	    }
	 
	    
		response.setContentType("text/html");
		out = response.getWriter();
		out.println(html);	
		out.close();

		
		
		
		
		//RZTGJBWR
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
