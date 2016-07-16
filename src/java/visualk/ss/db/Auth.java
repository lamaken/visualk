package visualk.ss.db;

import java.sql.*;

public class Auth {
    
    private MysqlLayer mySQL=new MysqlLayer();
    private ResultSet myResult;
    
    public void prepareDB(String dbServer,String dbUser, String dbPassword,String dbDataBase){   
    	this.mySQL.setDBValues(dbServer, dbUser, dbPassword, dbDataBase);
        
    } 
                     
    public Auth(){
    	prepareDB("localhost","ss_user","pass","surveyserver_db");
    	//prepareDB("mysql-s","s257847rw","segona","s257847_surveyserver");
    }
  
    public boolean canEnter(String alias, String clau){ 
        this.myResult=mySQL.queryDB("select * from login where USER_NAME=\""+alias+"\" and USER_PASSW=\""+clau+"\"");
        if(myResult!=null){
            try{
                if(this.myResult.first()){
                	this.myResult.close();
                	mySQL.dbConn.close();
                	return true;
                }
                else return false;
            }
            catch (SQLException s)
            {
            	s.printStackTrace();
            	System.out.println("Error en myResult");
            }
        }
        return false;
    }
    public boolean isEmailActive(String email,String password){
    	String sentence;
    	sentence = "select * from usuaris where (passw=NULL or passw=\""+password+"\") and email=\""+email+"\"";
    	System.out.print(sentence);
    	this.myResult=mySQL.queryDB(sentence);
        if(myResult!=null){
            try{
                if(this.myResult.first()){
                	this.myResult.close();
                	mySQL.dbConn.close();
                	return true;
                }
                else return false;
            }
            catch (SQLException s)
            {
            	s.printStackTrace();
            	System.out.println("Error en myResult");
            }
        }
        return false;
    }

    public boolean isCodeActive(String code){
    	String sentence;
    	sentence = "select id_pub from publicacions where anonima=1 and codi_access=\""+code+"\" and dt_start<NOW() and NOW()<dt_end";
    	
    	this.myResult=mySQL.queryDB(sentence);
        if(myResult!=null){
            try{
                if(this.myResult.first()){
                	this.myResult.close();
                	mySQL.dbConn.close();
                	return true;
                }
                else return false;
            }
            catch (SQLException s)
            {
            	s.printStackTrace();
            	System.out.println("Error en myResult");
            }
        }
        return false;
    }
    
}