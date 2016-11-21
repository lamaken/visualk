/**
 *
 */
package visualk.ss.modules;

import java.sql.ResultSet;
import java.sql.SQLException;
import visualk.html5.ClassCSS;

import visualk.ss.db.EnquestesDb;
import visualk.ss.modules.generator.Enquesta;
import visualk.ss.modules.generator.Publicacions;

import visualk.html5.MenuBar;
import visualk.html5.MenuLinkBar;
import visualk.html5.UniqueName;
import visualk.html5.Xhtml5;

/**
 * @author àlex
 * @version 1.a
 *
 *
 */
public class Generator extends Xhtml5 {

    private static final String CSS_GENERATOR_FILE_NAME = "css/generator.css";
    private static final String JS_GENERATOR_FILE_NAME = "js/generator.js";

    private static final String LNK_MARXAR = "exit";
    private static final String LNK_NOVA_ENQUESTA = "nEnquesta";
    private static final String LNK_TANCA_ENQUESTA = "nEnquesta";
    private static final String LNK_NOVA_PREGUNTA = "nPregunta";
    private static final String LNK_ELIMINAR_PREG = "elimina_preg";

//	private static final String LNK_FRAME = "frame_";
    private static final String LNK_EDIT = "edit";
    private static final String LNK_COMBO = "combo";
    private static final String LNK_LABEL = "label";

//	private static final String LNK_CONDITIONS = "condition_";
    private EnquestesDb enquestaDB;
    private Publicacions publicacio;

    private String email = "";//email del usuari
    private Enquesta enquesta;

    private MenuLinkBar upperMenuBar;
    //private MapBar mapBar;	
    private MenuLinkBar objectsBar;

    private MenuLinkBar menuEnquesta;
    private MenuLinkBar menuPublicades;

    //private MenuBar conditionsBar; 
    public void addCombo(String caption, String name) {
        enquesta.addCombo(caption, name);
    }

    public void addComboOption(String name, String caption) {
        enquesta.addComboOption(name, caption);
    }

    public void addLabel(String caption, String name) {
        enquesta.addLabel(caption, name);
    }

    public void editObjectForm(String name, String value) {
        enquesta.editObject(name, value);
    }

    public void addTextBox(String caption, String name) {
        enquesta.addTextBox(caption, name);
    }

    public void upObject(String name) {
        enquesta.upObject(name);
    }

    public void downObject(String name) {
        enquesta.downObject(name);
    }

    public void deleteObject(String name) {
        enquesta.deleteObject(name);
    }

    public void eliminaPreg() {
        if (enquesta != null) {
            enquesta.eliminaPregunta();
            if (enquesta.count() == 0) {
                //		mapBar.clear();
                objectsBar.clear();
                //		conditionsBar.clear();
                upperMenuBar.eliminaOption(LNK_ELIMINAR_PREG);
            }
        } else {
            this.messageBox.setMessage("eliminaPreg,Error greu, l'enquesta no existeix");
        }
    }

    public void novaPregunta(String id) {
        if (enquesta != null) {
            enquesta.addPregunta(id);
            //	mapBar.addFrame(new Frame("frame_"+id,new LinkHtml(LNK_FRAME,"Frame "+enquesta.count(),"#",id),1,0));

            if (enquesta.count() == 1) {
                initObjectsBar();
                //			conditionsBar.addMenuLink(LNK_CONDITIONS,"Opció 1","#","Nomès hi ha una condició");
                upperMenuBar.addMenuLink(LNK_ELIMINAR_PREG, "Eliminar pregunta", "#", "fes", "'pelimina','actual'", "Elimina la pregunta activa");

            }
        } else {
            this.messageBox.setMessage("Primer has de crear l'enquesta");
        }
    }

    private void createMapBar() {
        //	mapBar=new MapBar("mapBar");
    }

    private void createEnquestesDB() {
        menuEnquesta = new MenuLinkBar("enqBar", new ClassCSS("err"));
        menuEnquesta.setVertical();

        menuPublicades = new MenuLinkBar("enqPubBar", new ClassCSS("err"));
        menuPublicades.setVertical();

    }

    private void createConditionsBar() {
//		conditionsBar = new MenuBar("conditionsBar");
//		conditionsBar.setVertical();
    }

    private void createObjectsBar() {
        objectsBar = new MenuLinkBar("objectsBar", new ClassCSS("err"));
        objectsBar.setVertical();
    }

    private void createMenu() {
        upperMenuBar = new MenuLinkBar("mainMenuBar", new ClassCSS("err"));
        upperMenuBar.setHorizontal();
    }

    private void initObjectsBar() {

        objectsBar.addMenuLink(LNK_EDIT, "TextEdit", "#", "question_newtextbox", "'addTextBox'", "Afegir un camp de text");
        objectsBar.addMenuLink(LNK_LABEL, "Etiqueta", "#", "question_newlabel", "'addLabel'", "Afegir una etiqueta de text");
        objectsBar.addMenuLink(LNK_COMBO, "Selecció", "#", "question_newcombo", "'addCombo'", "Afegir un combo");

    }

    public void closePub() {
        publicacio = null;
        upperMenuBar.clear();
        initAll();

    }

    public void guardaPub(String nom, String desc, String dt_start, String dt_end, String anonima, String emails) {
        publicacio.guardaPub(nom, desc, dt_start, dt_end, anonima, emails);
        this.messageBox.setMessage("S'ha guardat!");

    }

    public void activaPub() {
        publicacio.activa();
        this.messageBox.setMessage("Enquesta activada !");

    }

    public void desactivaPub() {
        publicacio.desactiva();
        this.messageBox.setMessage("Enquesta desactivada !");

    }

    public void loadEnquestaPub(String id_pub) {
        //	mapBar.clear();
        objectsBar.clear();
//		conditionsBar.clear();
        upperMenuBar.eliminaOption(LNK_NOVA_ENQUESTA);
        menuEnquesta.clear();
        menuPublicades.clear();
        publicacio = new Publicacions(id_pub);

    }

    public void editaEnquesta() {
        if (enquesta != null) {

            if (enquesta.editaEnquesta()) {//mode edició
//					mapBar.clear();
                objectsBar.clear();
                //				conditionsBar.clear();
                upperMenuBar.eliminaOption(LNK_ELIMINAR_PREG);
            } else//mode preguntes
             if (enquesta.count() > 0) {
                    initObjectsBar();
//					conditionsBar.addMenuLink(LNK_CONDITIONS,"Opció 1","#","Nomès hi ha una condició");
                    upperMenuBar.addMenuLink(LNK_ELIMINAR_PREG, "Eliminar pregunta", "#", "fes", "'pelimina','actual'", "Elimina la pregunta activa");
                }

        } else {
            this.messageBox.setMessage("antoriorPreg,Error greu, l'enquesta no existeix");
        }

    }

    public void guardaDadesEnq(String nom, String desc) {
        if (enquesta != null) {
            enquesta.guardaDadesEnq(nom, desc);
            this.messageBox.setMessage("S`ha guardat!");
        } else {
            this.messageBox.setMessage("l`enquesta no existeix");
        }
    }

    public void anteriorPreg() {
        if (enquesta != null) {
            enquesta.anterior();
        } else {
            this.messageBox.setMessage("antoriorPreg,Error greu, l'enquesta no existeix");
        }
    }

    public void seguentPreg() {
        if (enquesta != null) {
            enquesta.seguent();
        } else {
            this.messageBox.setMessage("seguentPreg,Error greu, l'enquesta no existeix");
        }
    }

    private void initMenu() {

        upperMenuBar.addMenuLink(LNK_MARXAR, "Marxar del programa", "#", "vols_marxar", "'marxar'", "Si vols marxar.. tu mateix");

        upperMenuBar.addMenuLink(LNK_NOVA_ENQUESTA, "Nova enquesta", "#", "question_novaEnquesta", "'novaEnquesta'", "Crear una nova enquesta");

    }

    public void publicala(String nom) {
        String id = enquestaDB.duplicaEnquestayPublica(enquesta.getIdEnquestaDB(), nom);
        enquesta.setIdBD(id);
        enquestaDB.addPreguntes(enquesta);

        enquesta = null;
        upperMenuBar.clear();
//		mapBar.clear();	
        objectsBar.clear();
//		conditionsBar.clear();
        menuEnquesta.clear();
        menuPublicades.clear();
        initAll();

        this.messageBox.setMessage("S'ha publicat una nova enquesta amb el nom: " + nom);

    }

    //carrega el combo amb les opcions
    public void populateComboSelects(String id_select, String newname) {
        String caption = "Not Found";

        if (enquesta != null) {

            ResultSet myResult = enquestaDB.getSelectsLabels(id_select);
            try {
                while (myResult.next()) {
                    caption = myResult.getString("caption");
                    this.addComboOption(newname, caption);
                }

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else {
            this.messageBox.setMessage("Has de carregar una enquesta.");
        }

    }

    //carrega els objectes de la pregunta
    public void loadObjects(String idPregunta) {
        String tipus = "Not Found";
        String caption = "Not Found";
        String id_select = "Not Found";

        if (enquesta != null) {

            ResultSet myResult = enquestaDB.getObjects(idPregunta);
            try {
                while (myResult.next()) {
                    tipus = myResult.getString("name");
                    caption = myResult.getString("caption");
                    id_select = myResult.getString("id_select");
                    String newname = new UniqueName(8).getName();
                    if (tipus.equals("Label")) {
                        this.addLabel(caption, newname);
                    }
                    if (tipus.equals("EditBox")) {
                        this.addTextBox(caption, newname);
                    }
                    if (tipus.equals("Select")) {
                        this.addCombo(caption, newname);
                        populateComboSelects(id_select, newname);
                    }

                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else {
            this.messageBox.setMessage("Has de carregar una enquesta.");
        }

    }

    //carrega les preguntes
    public void loadPreguntes(String idEnquestaDB) {
        String id = "none";
        if (enquesta != null) {

            ResultSet myResult = enquestaDB.getPreguntes(idEnquestaDB);
            try {
                while (myResult.next()) {
                    id = myResult.getString("id_pregunta");
                    enquesta.addPregunta(id);
                    loadObjects(id);
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else {
            this.messageBox.setMessage("Has de carregar una enquesta.");
        }

    }

    public void eliminaEnquesta() {
        if (enquesta != null) {
            enquestaDB.eliminaTotaEnquesta(enquesta.getIdEnquestaDB());
            enquesta = null;
            upperMenuBar.clear();
            //		mapBar.clear();	
            objectsBar.clear();
            //		conditionsBar.clear();
            menuEnquesta.clear();
            menuPublicades.clear();
            initAll();
        } else {
            this.messageBox.setMessage("No hi ha cap enquesta per eliminar");
        }
    }

    //carega una enquesta de la base de dades
    public void loadEnquesta(String idEnquestaDB) {
        String desc = "Not found";
        String nome = "Not found";

        if (enquesta == null) {

            ResultSet myResult = enquestaDB.getEnquesta(idEnquestaDB);
            try {
                while (myResult.next()) {
                    desc = myResult.getString("dsc_enquesta");
                    nome = myResult.getString("nm_enquesta");
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            enquesta = new Enquesta(nome, desc, idEnquestaDB);
            loadPreguntes(idEnquestaDB);

            ////////////////
            enquesta.editaEnquesta();
            enquesta.primera();

            //	mapBar.clear();
            objectsBar.clear();
            //	conditionsBar.clear();

            upperMenuBar.eliminaOption(LNK_ELIMINAR_PREG);
            upperMenuBar.addMenuLink(LNK_NOVA_PREGUNTA, "Nova pregunta", "#", "fes", "'novaPreg','1'", "Crear una nova pregunta");
            upperMenuBar.addMenuLink(LNK_TANCA_ENQUESTA, "Tancar enquesta", "#", "tancaEnquesta", "'tancaEnquesta','1'", "Tanca i guarda enquesta activa");
            upperMenuBar.eliminaOption(LNK_NOVA_ENQUESTA);
            /////////////////

        } else {
            this.messageBox.setMessage("Has de tancar l'enquesta activa");
        }
    }

    //carrega la llista d'enquestes
    private void loadEnquestesLlistaDB() {
        String ide;
        String nome;

        ResultSet myResult = enquestaDB.getLlista();
        if(myResult!=null)
        try {
            while (myResult.next()) {
                ide = myResult.getString("id_enquesta");
                nome = myResult.getString("nm_enquesta");
                menuEnquesta.addMenuLink("e_" + ide, nome, "#", "fes", "'loadEnquesta','" + ide + "'", "Fes clic per carregar l`enquesta :(" + nome + ")");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        menuEnquesta.setTitle("Llistat de les enquestes disponibles");
    }

    //carrega la llista d'enquestes
    private void loadEnquestesPublicades() {
        String idp;
        String nome;
        ResultSet myResult = enquestaDB.getLlistaPub();
        try {
            while (myResult.next()) {
                idp = myResult.getString("id_pub");
                nome = myResult.getString("nm_enquesta");
                menuPublicades.addMenuLink("ep_" + idp, nome, "#", "fes", "'loadEnquestaPub','" + idp + "'", "Fes clic per carregar les dades de la publicació. (" + nome + ")");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        menuPublicades.setTitle("Llistat de les enquestes publicades");
    }

    public String toHtml() {
        this.updateFunctions();
        this.clearBodyData();
        this.clearDataForm();

        initFunctions();
        this.addBanner(this.email);

        this.addDataForm("<input type=\"hidden\" name=\"where\" value=\"generator\"/>");
        this.addBodyData(upperMenuBar.toHtml());
        this.addBodyData(objectsBar.toHtml());
        //	this.addBodyData(conditionsBar.toHtml());
        //	this.addBodyData(mapBar.toHtml());

        if (enquesta != null) {
            this.addBodyData(enquesta.getHtml());
        }

        if (publicacio != null) {
            this.addBodyData(publicacio.toHtml());
        } else if (enquesta == null) {
            this.addBodyData(menuEnquesta.toHtml());
            this.addBodyData(menuPublicades.toHtml());
        }

        String ret = this.getHtml();
        messageBox.setMessage("");//inicialitzem missatges
        return (ret);
    }

    public void guardaEnquesta() {
        enquesta.setIdBD(enquestaDB.addEnquesta(enquesta.getIdEnquestaDB(), enquesta.getCaption(), enquesta.getDescripcio()));
        enquestaDB.addPreguntes(enquesta);
    }

    public void tancaEnquesta(String id) {

        enquesta = null;
        upperMenuBar.clear();
        //	mapBar.clear();	
        objectsBar.clear();
        //	conditionsBar.clear();
        menuEnquesta.clear();
        menuPublicades.clear();
        initAll();

    }

    public void novaEnquesta(String caption) {
        if (enquesta == null) {
            enquesta = new Enquesta(caption, "", "");
            upperMenuBar.addMenuLink(LNK_NOVA_PREGUNTA, "Nova pregunta", "#", "fes", "'novaPreg','1'", "Crear una nova pregunta");
            upperMenuBar.addMenuLink(LNK_TANCA_ENQUESTA, "Tancar enquesta", "#", "tancaEnquesta", "'tancaEnquesta','1'", "Tanca i guarda enquesta activa");
            upperMenuBar.eliminaOption(LNK_NOVA_ENQUESTA);
        } else {
            this.messageBox.setMessage("Has de tancar l'enquesta activa");
        }
    }

    public Generator(String email, String session) {
        super("ss/SurveyServer", session, "Generator");
        this.email = email;
        publicacio = null;
        enquestaDB = new EnquestesDb("ss_user","ss_password","surveyserver_db",email);
        createMenu();				//menu principal d'adalt
        createObjectsBar();		//objectes formulari
        createConditionsBar();	//condicions diferents
        createMapBar();
        createEnquestesDB();
        initAll();
    }

    private void initFunctions() {
        vsFunctions.addFunction("help", "content", "alert(\"Help:\"+content);");
        vsFunctions.addFunction("vols_marxar", "actions", "if(confirm(\"Segur que vols Marxar?\"))fes(actions,'')");
        vsFunctions.addFunction("tancaEnquesta", "actions", "if(confirm(\"Vols guardar els canvis?\"))fes(actions,'1');else fes(actions,'2');");

        vsFunctions.addFunction("question_novaEnquesta", "actions", "answer = prompt (\"Introdueix el nom de l'enquesta:\",\"\");fes(actions,answer);");
        vsFunctions.addFunction("question_newlabel", "actions", "answer = prompt (\"Introdueix el text de l'etiqueta:\",\"\");fes(actions,answer);");
        vsFunctions.addFunction("question_newcombo", "actions", "answer = prompt (\"Introdueix el text del combo:\",\"\");fes(actions,answer);");
        vsFunctions.addFunction("question_newtextbox", "actions", "answer = prompt (\"Introdueix el text del control:\",\"\");fes(actions,answer);");
        vsFunctions.addFunction("question_publica", "actions,params", "answer = prompt (\"Introdueix un nom per a la publicació:\",params);fes(actions,answer);");

        vsFunctions.addFunction("editObject", "actions,params,more", "answer = prompt (\"Introdueix el text de l'etiqueta:\",params);fes2(actions,answer,more);");
        vsFunctions.addFunction("editObject", "actions,params,more", "answer = prompt (\"Introdueix el text de la caixa:\",params);fes2(actions,answer,more);");
        vsFunctions.addFunction("deleteObject", "actions,params", "fes(actions,params);");
        vsFunctions.addFunction("upObject", "actions,params", "fes(actions,params);");
        vsFunctions.addFunction("downObject", "actions,params", "fes(actions,params);");
        vsFunctions.addFunction("addComboOption", "actions,params,more", "answer = prompt (\"Introdueix la opció\",\"\");fes2(actions,answer,more);");

        vsFunctions.addFile(JS_GENERATOR_FILE_NAME);
    }

    private void initStyles() {
        cssStyles.addFileCSS(CSS_GENERATOR_FILE_NAME);
    }

    private void initAll() {
        initMenu();
        loadEnquestesLlistaDB(); //carreguem les enquestes guardades a la base de dades.
        loadEnquestesPublicades(); //carreguem publicades.

        initStyles();			//fulla d'estils per a tothom

    }
}
