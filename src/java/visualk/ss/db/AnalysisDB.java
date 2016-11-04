package visualk.ss.db;

import java.sql.ResultSet;
import visualk.db.MysqlLayer;

public class AnalysisDB extends MysqlLayer {

    public ResultSet getRespostes(int tipus, String tp) {
        ResultSet myResult;
        String sentence = "";

        if (tipus == 3) {
            sentence = "select a.usuari as usuari,c.caption as resposta from respuestas a,tipusPreg b,selectt c where a.id_tp=\"" + tp + "\" and a.id_tp = b.id_tp and b.id_select = c.id_object and (c.ordre+1) = a.respuesta";
        }
        if (tipus == 1) {
            sentence = "select a.respuesta as resposta,a.usuari as usuari from respuestas a where a.id_tp = \"" + tp + "\"";
        }

        myResult = this.queryDB(sentence);

        return (myResult);
    }

    public ResultSet getPreguntes(String id_enquesta) {
        ResultSet myResult;
        String sentence = "select"
                + " a.caption as caption,"
                + " a.id_tipus as id_tipus,"
                + " a.id_select as id_select, "
                + " a.id_tp as id_tp "
                + " from "
                + " tipusPreg a,"
                + " preguntes b "
                + " where "
                + " a.id_pregunta = b.id_pregunta and "
                + " b.id_enquesta = \"" + id_enquesta + "\" "
                + " order by b.ordre,a.ordre ";
        myResult = this.queryDB(sentence);
        return (myResult);
    }

    public ResultSet getLlista(String email) {
        ResultSet myResult;
        String sentence = "select a.id_pub as id_pub, b.id_enquesta as id_enquesta,b.nm_enquesta as nm_enquesta from publicacions a, enquesta b where a.id_enquesta=b.id_enquesta and a.activa=1 and b.propietari=\"" + email + "\"";
        myResult = this.queryDB(sentence);
        return (myResult);
    }
}
