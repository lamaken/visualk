package visualk.ss.db;


/** Capa pel mySQL
*
* @autor alex
*/
import java.sql.*;

public class MysqlLayer {
	
   protected Connection dbConn=null;
   private String dbServer=null;
   private String dbUser=null;
   private String dbPassword=null;
   private String dbDataBase=null;
   
   protected ResultSet result=null;
   
   /** Crea la instància de sqlLayer */
   protected MysqlLayer() {
	   result = null;
	   this.setDBValues("127.0.0.1","ss_user","ss_password","surveyserver_db");//servidor,usuari,password,base de dades
   }

   private void setDBValues(String dbServer, String dbUser, String dbPassword, String dbDataBase) {
       this.dbPassword=dbPassword;
       this.dbServer=dbServer;
       this.dbPassword=dbPassword;
       this.dbDataBase=dbDataBase;
       this.dbUser=dbUser;
   }
   protected void executeDB(String sqlQuery){
	   PreparedStatement sql=null;
	
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
		} catch (ClassNotFoundException e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		}
	    String cadenaConn="jdbc:mysql://"+dbServer+":3306/"+dbDataBase;
		try {
			dbConn = DriverManager.getConnection(cadenaConn,dbUser,dbPassword);
		} catch (SQLException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		try {
			sql = dbConn.prepareStatement(sqlQuery);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			sql.execute();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
   }
   protected ResultSet queryDB(String sqlQuery){
       PreparedStatement sql=null;
       
	try
	{
       Class.forName("org.gjt.mm.mysql.Driver");
           try
           {
               String cadenaConn="jdbc:mysql://"+dbServer+":3306/"+dbDataBase;
               dbConn = DriverManager.getConnection(cadenaConn,dbUser,dbPassword);
               sql = dbConn.prepareStatement(sqlQuery);
               result=sql.executeQuery();
               // Close the result set, statement and the connection
               
               
           }
           catch (SQLException s)
           {
        	s.printStackTrace();
            System.out.println("SQL Error on Open");
           }
       }
       catch (ClassNotFoundException err)
	{
	 System.out.println("Class loading error");
       }

      return(result);
   }
}