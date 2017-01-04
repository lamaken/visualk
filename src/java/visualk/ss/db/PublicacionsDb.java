package visualk.ss.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import visualk.db.MysqlLayer;

import visualk.ss.modules.generator.Publicacions;

public class PublicacionsDb extends MysqlLayer {

    public PublicacionsDb(String user, String pass, String db) {
        super(user, pass, db);
    }

    private String getUserId(String eml) {
        String ret = "mm";
        ResultSet myResult = null;
        try {
            myResult = this.queryDB("select id_usuari from usuaris where email=\"" + eml + "\"");
        } catch (SQLException ex) {
            Logger.getLogger(PublicacionsDb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PublicacionsDb.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while (myResult.next()) {
                ret = myResult.getString("id_usuari");//eliminem totes les opcions
                if (ret == null) {
                    ret = "kk";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (ret);
    }

    private String getNextAutonum(String table) {
        String id = "";
        String ret = "-1";//error

        if (table.equals("usuaris")) {    //seleccio múltiple
            id = "id_usuari";
        }
        if (table.equals("grups")) {    //seleccio múltiple
            id = "id_grup";
        }

        String sql = "select (max(" + id + ")+1) as ret from " + table;
        ResultSet myResult = null;
        try {
            myResult = this.queryDB(sql);
        } catch (SQLException ex) {
            Logger.getLogger(PublicacionsDb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PublicacionsDb.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while (myResult.next()) {
                ret = myResult.getString("ret");//eliminem totes les opcions
                if (ret == null) {
                    ret = "666";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (ret);
    }

    public ResultSet loadUsuaris(String id_grup) {
        ResultSet myResult = null;
        String sentence = "select a.email from usuaris a, grups_usuari b where a.id_usuari=b.id_usuari and b.id_grup=\"" + id_grup + "\"";
        try {
            myResult = this.queryDB(sentence);
        } catch (SQLException ex) {
            Logger.getLogger(PublicacionsDb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PublicacionsDb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (myResult);
    }

    public ResultSet loadPub(String id_pub) {
        ResultSet myResult = null;
        try {
            myResult = this.queryDB("SELECT a.id_enquesta as id_enquesta,a.id_grup as grup,a.dt_start as dt_start,a.dt_end as dt_end,a.codi_access as codi,a.activa as activa,a.anonima as anonima,b.nm_enquesta as nom,b.dsc_enquesta as descr FROM publicacions a, enquesta b where a.id_enquesta = b.id_enquesta and id_pub=" + id_pub);
        } catch (SQLException ex) {
            Logger.getLogger(PublicacionsDb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PublicacionsDb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (myResult);
    }

    public void activa(String id_pub) {
        this.executeDB("update publicacions set activa=\"1\" where id_pub=" + id_pub);
    }

    public void desactiva(String id_pub) {
        this.executeDB("update publicacions set activa=\"0\" where id_pub=" + id_pub);
    }

    public void addEmails(String id_grup, String list) {
        String emails[];
        emails = list.split(",");
        String antid = "";
        String idu = "";
        int i = 0;

        for (i = 0; i < emails.length; i++) {
            if (isEmailValid(emails[i])) {
                idu = getNextAutonum("usuaris");
                this.executeDB("insert ignore into usuaris(id_usuari,email) values (\"" + idu + "\",\"" + emails[i] + "\")");//ignore per si ja existeix
                antid = getNextAutonum("usuaris");

                if (!antid.equals(idu)) {//si l'usuari no existeix
                    this.executeDB("insert into grups_usuari(id_grup,id_usuari) values (\"" + id_grup + "\",\"" + idu + "\")");
                } else {	//si ja existeix l'afegim amb el seu codi
                    antid = getUserId(emails[i]);
                    this.executeDB("insert ignore into grups_usuari(id_grup,id_usuari) values (\"" + id_grup + "\",\"" + antid + "\")"); //ignore per si ja existeix

                }

            }
        }
    }

    public void guardaDB(Publicacions pubData) {

        String bAnonim;
        String sentence;
        String idg;
        String ins;

        if (pubData.isAnonymous()) {
            bAnonim = "1";

        } else {
            bAnonim = "0";
            idg = pubData.getId_grup();
            if (idg == null) {
                idg = this.getNextAutonum("grups");
                this.executeDB("insert into grups(id_grup) values (\"" + idg + "\")");
            }
            addEmails(idg, pubData.getListEmails());
            pubData.setId_grup(idg);
        }

        if (pubData.getId_grup() == null) {
            ins = "NULL";
        } else {
            ins = "\"" + pubData.getId_grup() + "\"";
        }

        sentence = "update publicacions a, enquesta b set a.dt_start=\"" + pubData.getDt_start()
                + "\",a.anonima=\"" + bAnonim
                + "\",a.dt_end=\"" + pubData.getDt_end()
                + "\",a.id_grup="
                + ins
                + " ,b.nm_enquesta=\"" + pubData.getNom()
                + "\",b.dsc_enquesta=\"" + pubData.getDesc()
                + "\" where a.id_pub=" + pubData.getId_pub()
                + " and a.id_enquesta=b.id_enquesta";

        this.executeDB(sentence);

        /*if(bAnonim.equals("0"))///if not anonimus add
    	(if(id_grup==null)){
    		get next id_grup update pub with id_grup
    		
    		pubData.getListEmails();
    	}
		else (id_grup=null)
         */
    }

    private boolean isEmailValid(String email) {
        boolean isValid = false;
        //Initialize reg ex for email.
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }
}
