package visualk.ss.db;

import java.sql.*;
import visualk.db.AuthAdapter;
import visualk.db.MysqlLayer;

public class Auth implements AuthAdapter {

    private MysqlLayer mySQL=new MysqlLayer("ss_user","ss_password","surveyserver_db");
    private ResultSet myResult;

    public boolean canEnter(String alias, String clau) {
        this.myResult = mySQL.queryDB("select * from login where USER_NAME=\"" + alias + "\" and USER_PASSW=\"" + clau + "\"");
        if (myResult != null) {
            try {
                if (this.myResult.first()) {
                    this.myResult.close();
                    mySQL.dbConn.close();
                    return true;
                } else {
                    return false;
                }
            } catch (SQLException s) {
                s.printStackTrace();
                System.out.println("Error en myResult");
            }
        }
        return false;
    }

    public boolean isEmailActive(String email, String password) {
        String sentence;
        sentence = "select * from usuaris where (passw is NULL or passw=\"" + password + "\") and email=\"" + email + "\"";
        System.out.print(sentence);
        this.myResult = mySQL.queryDB(sentence);
        if (myResult != null) {
            try {
                if (this.myResult.first()) {
                    this.myResult.close();
                    mySQL.dbConn.close();
                    return true;
                } else {
                    return false;
                }
            } catch (SQLException s) {
                s.printStackTrace();
                System.out.println("Error en myResult");
            }
        }
        return false;
    }

    public boolean isCodeActive(String code) {
        String sentence;
        sentence = "select id_pub from publicacions where anonima=1 and codi_access=\"" + code + "\" and dt_start<NOW() and NOW()<dt_end";

        this.myResult = mySQL.queryDB(sentence);
        if (myResult != null) {
            try {
                if (this.myResult.first()) {
                    this.myResult.close();
                    mySQL.dbConn.close();
                    return true;
                } else {
                    return false;
                }
            } catch (SQLException s) {
                s.printStackTrace();
                System.out.println("Error en myResult");
            }
        }
        return false;
    }

    @Override
    public void prepareDB(String dbServer, String dbUser, String dbPassword, String dbDataBase) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
