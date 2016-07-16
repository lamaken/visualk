package visualk.ss.db;

import java.sql.ResultSet;

import visualk.ss.modules.generator.Publicacions;

public class PublicacionsDb {
	
	
	private MysqlLayer mySQL=new MysqlLayer();
    
	
	 public void prepareDB(String dbServer,String dbUser, String dbPassword,String dbDataBase){   
	        this.mySQL.setDBValues(dbServer, dbUser, dbPassword, dbDataBase);
	    } 
	 
	public PublicacionsDb() {
		   	prepareDB("localhost","ss_user","pass","surveysdb");
	    	//prepareDB("mysql-s","s257847rw","segona","s257847_surveyserver");
	   
	}
	
	public ResultSet loadPub(String id_pub){
		ResultSet myResult;
		myResult=mySQL.queryDB("SELECT a.id_enquesta as id_enquesta,a.id_grup as grup,a.dt_start as dt_start,a.dt_end as dt_end,a.codi_access as codi,a.activa as activa,a.anonima as anonima,b.nm_enquesta as nom,b.dsc_enquesta as descr FROM publicacions a, enquesta b where a.id_enquesta = b.id_enquesta and id_pub="+id_pub);
		return(myResult);
	}
	public void activa(String id_pub){
		mySQL.executeDB("update publicacions set activa=\"1\" where id_pub="+id_pub);
	}
	public void desactiva(String id_pub){
		mySQL.executeDB("update publicacions set activa=\"0\" where id_pub="+id_pub);
	}
	public void guardaDB(Publicacions pubData){
		    	
		String bAnonim;
		String sentence;
		
		if(pubData.isAnonymous())bAnonim="1";
		else bAnonim="0";
		
    	
		sentence = "update publicacions a, enquesta b set a.dt_start=\""+pubData.getDt_start()+
		"\",a.anonima=\""+bAnonim+
		"\",a.dt_end=\""+pubData.getDt_end()+		
		"\",b.nm_enquesta=\""+pubData.getNom()+
		"\",b.dsc_enquesta=\""+pubData.getDesc()+
		"\" where a.id_pub="+pubData.getId_pub()+
		" and a.id_enquesta=b.id_enquesta";
		System.out.println(sentence);
		mySQL.executeDB(sentence);
    	
    	
    	
	}

}
