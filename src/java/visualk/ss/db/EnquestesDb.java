package visualk.ss.db;

import java.sql.*;
import java.util.LinkedList;

import visualk.ss.modules.generator.ComboForm;
import visualk.ss.modules.generator.Enquesta;
import visualk.ss.modules.generator.ObjectForm;
import visualk.html.UniqueName;


public class EnquestesDb {

	
	private static final String TIPUS_OBJ_EDIT 		= "1";
	private static final String TIPUS_OBJ_SELECT 	= "2";
	private static final String TIPUS_OBJ_LABEL 	= "3";
	
	private MysqlLayer mySQL=new MysqlLayer();
    private String propietari="";
	
	
	private String getNextAutonum(String table){
	String id="";
	String ret="-1";//error
		
	  if(table.equals("publicacions")){	//enquestes
		id		= "id_pub";
	   }
		if(table.equals("enquesta")){	//enquestes
			id		= "id_enquesta";
		}
		if(table.equals("preguntes")){	//preguntes
			id		= "id_pregunta";
		}
		if(table.equals("tipusPreg")){	//tipus de preguntes
			id		= "id_tp";
		}
		if(table.equals("selectt")){    //seleccio múltiple
			id		= "id_object";
		}
		
		String sql="select (max("+id+")+1) as ret from "+table;
        ResultSet myResult=mySQL.queryDB(sql);
        try {
			while(myResult.next()){
				 ret = myResult.getString("ret");//eliminem totes les opcions
				 if(ret==null)ret="666";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return(ret);
	}
	
	
	
	
    
    public void prepareDB(String dbServer,String dbUser, String dbPassword,String dbDataBase){   
        this.mySQL.setDBValues(dbServer, dbUser, dbPassword, dbDataBase);
    } 
    public String addEnquesta(String id, String nom, String desc){
    	if(id.equals("")){
    		//TODO: Guardar be la data
    		id = getNextAutonum("enquesta");
    		mySQL.executeDB("insert into enquesta (id_enquesta,nm_enquesta,dt_creacio,dsc_enquesta,propietari) values (\""+id+"\",\""+nom+"\",NOW(),\""+desc+"\",\""+this.propietari+"\");");
    	}else {
    		mySQL.executeDB("update enquesta set nm_enquesta='"+nom+"',dsc_enquesta='"+desc+"' where id_enquesta = '"+id+"';");
    		this.eliminaTotAbans(id);
    		
    	}
    	return(id);
    }
   
    
    public String duplicaEnquestayPublica(String id_org, String nom){
    	
    	//TODO: Guardar be la data
    	String id_dst = getNextAutonum("enquesta");
    	mySQL.executeDB("insert into enquesta (id_enquesta,nm_enquesta,dt_creacio,dsc_enquesta,propietari) " +
    					"select '"+id_dst+"','"+nom+"',NOW(),dsc_enquesta,propietari from enquesta where id_enquesta ='"+id_org+"'");
    	
    	String id_pub = getNextAutonum("publicacions");
    	String code = new UniqueName(8).getName();//generem un codi d'accès a l'atzar per la publicacio
    	mySQL.executeDB("insert into publicacions (id_pub,id_enquesta,codi_access) values (\""+id_pub+"\",\""+id_dst+"\",upper(\""+code+"\"))");
    	return(id_dst);
    }
    
    
    /** Crea la instància de clients */
    public EnquestesDb(String propietari) {
    	this.propietari = propietari;
    	prepareDB("localhost","ss_user","pass","surveysdb");
    	//prepareDB("mysql-s","s257847rw","segona","s257847_surveyserver");
    }
    public void eliminaTotaEnquesta(String id_enquesta){
    	eliminaTotAbans(id_enquesta);
    	mySQL.executeDB("delete from enquesta where id_enquesta=\""+id_enquesta+"\";");
    }
    
    private void eliminaPregunta(String id_pregunta){
    	//seleccionem totes les opcions de selecció per una pregunta
    	String sql="select tp.id_select as op from tipusPreg tp where  tp.id_select and tp.id_tipus=\"00000000000000000003\" and tp.id_pregunta = \""+id_pregunta+"\";";
        ResultSet myResult=mySQL.queryDB(sql);
        try {
			while(myResult.next()){
				 String option = myResult.getString("op");//eliminem totes les opcions
				 mySQL.executeDB("delete from selectt where id_object=\""+option+"\";");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mySQL.executeDB("delete from tipusPreg where id_pregunta=\""+id_pregunta+"\";");
    }
    
    private void eliminaTotAbans(String id_enquesta){
    	ResultSet myResult;
    	String sql = "select id_pregunta from preguntes where id_enquesta = \""+id_enquesta+"\"";
    	myResult=mySQL.queryDB(sql);
    	try {
			while(myResult.next()){
				 String id_pregunta = myResult.getString("id_pregunta");//eliminem totes les opcions
				 eliminaPregunta(id_pregunta);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	mySQL.executeDB("delete from preguntes where id_enquesta=\""+id_enquesta+"\";");
	}
    
    
    public ResultSet getPreguntes(String id){
    	ResultSet myResult;
        myResult=mySQL.queryDB("SELECT id_pregunta FROM preguntes where id_enquesta=\""+id+"\" order by ordre;");
        return (myResult);
    }
    public ResultSet getSelectsLabels(String id){
    	ResultSet myResult;
    	myResult=mySQL.queryDB("SELECT caption FROM selectt where id_object=\""+id+"\" order by ordre");
    	        
    	return(myResult);
    }
    public ResultSet getObjects(String id){
     	ResultSet myResult;
        myResult=mySQL.queryDB("SELECT t.id_select as id_select,t.id_tp,t.id_pregunta,tp.name as name,t.caption as caption,t.ordre FROM tipusPreg t, tipus tp"+
    	" where t.id_tipus = tp.id and t.id_pregunta=\""+id+"\" order by t.ordre;");
        return (myResult);
    }
    public ResultSet getEnquesta(String id)
    {
      ResultSet myResult;
      myResult=mySQL.queryDB("select id_enquesta,nm_enquesta,dsc_enquesta,dt_creacio,propietari from enquesta where id_enquesta=\""+id+"\" and propietari=\""+this.propietari+"\";");
      return (myResult);
    }
    public ResultSet getLlista()
    {
      ResultSet myResult;
      myResult=mySQL.queryDB("select id_enquesta,nm_enquesta,dt_creacio,propietari from enquesta where propietari=\""+this.propietari+"\" and id_enquesta not in (select id_enquesta from publicacions) order by dt_creacio;");
      return (myResult);
    }
    public ResultSet getLlistaPub()
    {
      ResultSet myResult;
      myResult=mySQL.queryDB("select a.id_pub as id_pub, b.id_enquesta as id_enquesta,b.nm_enquesta as nm_enquesta from publicacions a, enquesta b where a.id_enquesta=b.id_enquesta and b.propietari=\""+this.propietari+"\" order by b.dt_creacio;");
      return (myResult);
    }
	public void addPreguntes(Enquesta enquesta) {
		// TODO Auto-generated method stub
		int p,o,k;
		String t="";
		LinkedList<ObjectForm> objectesLLista=null;
		LinkedList<String> options=null;
		String caption="";
		
		
		for (p=0;p<enquesta.count();p++){
			objectesLLista = enquesta.getPreguntes().get(p).getObjectesFormulari();
			
			String idp = getNextAutonum("preguntes");
			
			mySQL.executeDB("insert into preguntes(id_pregunta,id_enquesta,ordre) values (\""+idp+"\",\""+enquesta.getIdEnquestaDB()+"\", \""+p+"\");");
				
			
			for(o=0;o<objectesLLista.size();o++){
				
				t = objectesLLista.get(o).getTipus();
				caption= objectesLLista.get(o).getCaption();
				
				String idtp = getNextAutonum("tipusPreg");
				
				
				if( t.equals(TIPUS_OBJ_EDIT) ){
					mySQL.executeDB("insert into tipusPreg(id_tp,id_pregunta,id_tipus,caption,ordre) values (\""+idtp+"\",\""+idp+"\",1,\""+caption+"\",\""+o+"\");");
				}
				
				if( t.equals(TIPUS_OBJ_LABEL) ){
					mySQL.executeDB("insert into tipusPreg(id_tp,id_pregunta,id_tipus,caption,ordre) values (\""+idtp+"\",\""+idp+"\",2,\""+caption+"\",\""+o+"\");");
				}
				
				if( t.equals(TIPUS_OBJ_SELECT) ){
					
					String ido = getNextAutonum("selectt");
					mySQL.executeDB("insert into tipusPreg(id_tp,id_pregunta,id_tipus,caption,ordre,id_select) values (\""+idtp+"\",\""+idp+"\",3,\""+caption+"\",\""+o+"\",\""+ido+"\");");
					
					options = ((ComboForm)objectesLLista.get(o)).getOptions();
					
					
					for(k=0;k<options.size();k++){
					mySQL.executeDB("insert into selectt(id_object,caption,ordre) values (\""+ido+"\",\""+options.get(k)+"\",\""+k+"\");");
					}
					
					
				}
				
			}
				
		}
	}
    
}
