package visualk.ss.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import visualk.db.MysqlLayer;

import visualk.ss.modules.viewer.ObjectForm;
import visualk.ss.modules.viewer.FluxeSurvey;

public class ViewDB extends MysqlLayer {

    public ViewDB(String user, String pass, String db) {
        super(user, pass, db);
    }

    private String getNextAutonum() {
        String ret = "-1";//error

        String table = "respuestas";
        String id = "id_respuesta";

        String sql = "select (max(" + id + ")+1) as ret from " + table;
        ResultSet myResult = null;
        try {
            myResult = this.queryDB(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ViewDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViewDB.class.getName()).log(Level.SEVERE, null, ex);
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

    public ResultSet getPreguntes(String id) {
        ResultSet myResult = null;
        try {
            myResult = this.queryDB("SELECT id_pregunta FROM preguntes where id_enquesta=\"" + id + "\" order by ordre;");
        } catch (SQLException ex) {
            Logger.getLogger(ViewDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViewDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (myResult);
    }

    public ResultSet getSelectsLabels(String id) {
        ResultSet myResult = null;
        try {
            myResult = this.queryDB("SELECT caption FROM selectt where id_object=\"" + id + "\" order by ordre");
        } catch (SQLException ex) {
            Logger.getLogger(ViewDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViewDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return (myResult);
    }

    public ResultSet getObjects(String id) {
        ResultSet myResult = null;
        try {
            myResult = this.queryDB("SELECT t.id_select as id_select,t.id_tp,t.id_pregunta,tp.name as name,t.caption as caption,t.ordre FROM tipusPreg t, tipus tp"
                    + " where t.id_tipus = tp.id and t.id_pregunta=\"" + id + "\" order by t.ordre;");
        } catch (SQLException ex) {
            Logger.getLogger(ViewDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViewDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (myResult);
    }

    public void saveAlltoBD(FluxeSurvey fs, String who) {
        // TODO Auto-generated method stub
        int p, o;
        LinkedList<ObjectForm> objectesLLista = null;
        for (p = 0; p < fs.count(); p++) {
            objectesLLista = fs.getPreguntes().get(p).getObjectesFormulari();

            for (o = 0; o < objectesLLista.size(); o++) {
                String idp = getNextAutonum();
                if (!objectesLLista.get(o).getTipus().equals("3")) {
                    this.executeDB("insert into respuestas(id_respuesta,id_tp,respuesta,usuari) values (\"" + idp + "\",\"" + objectesLLista.get(o).getId_tp() + "\", \"" + objectesLLista.get(o).getResposta() + "\",\"" + who + "\");");
                }
            }
        }

    }

    public ResultSet getEnquesta(String id) {
        ResultSet myResult = null;
        try {
            myResult = this.queryDB("select id_enquesta,nm_enquesta,dsc_enquesta,dt_creacio,propietari from enquesta where id_enquesta=\"" + id + "\"");
        } catch (SQLException ex) {
            Logger.getLogger(ViewDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViewDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (myResult);
    }

    public ResultSet getLlistaAnonimes() {
        ResultSet myResult = null;
        String sentence = "select a.id_pub as id_pub, b.id_enquesta as id_enquesta,b.nm_enquesta as nm_enquesta from publicacions a, enquesta b where a.id_enquesta=b.id_enquesta and a.activa=1 and a.anonima=1 and a.dt_start<NOW() and NOW()<a.dt_end order by b.dt_creacio";
        try {
            myResult = this.queryDB(sentence);
        } catch (SQLException ex) {
            Logger.getLogger(ViewDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViewDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (myResult);
    }
    //TODO-LLISTA ENQUESTES D'USUARIS NO ANONIMS

    public ResultSet getLlistaNoAnonimes(String email) {
        ResultSet myResult = null;
        String sentence = "select a.id_pub as id_pub, b.id_enquesta as id_enquesta,b.nm_enquesta as nm_enquesta from publicacions a, enquesta b, grups_usuari c,grups d,usuaris e where a.id_grup=d.id_grup and c.id_usuari=e.id_usuari and a.id_enquesta=b.id_enquesta and d.id_grup = c.id_grup and a.anonima=0 and a.activa=1 and a.dt_start<NOW() and NOW()<a.dt_end and e.email=\"" + email + "\"";
        try {
            myResult = this.queryDB(sentence);
        } catch (SQLException ex) {
            Logger.getLogger(ViewDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViewDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (myResult);
    }
}
