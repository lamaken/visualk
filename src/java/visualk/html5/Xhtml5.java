package visualk.html5;


public class Xhtml5 {

    private String SERVLET_HRZ_URL = "error_URL";
    private static final String JSXHTML = "js/xhtml5.js";
    private static final String doctype = "<!DOCTYPE html>";
    private static final String open_html = "<html>";
    private static final String open_head = "<head>";
    private static final String meta = "<meta charset=\"ISO-8859-1\"/>";
    private static final String open_title = "<title>";
    private String title = "default title";
    private static final String close_title = "</title>";
    public VsFunctions vsFunctions;
    public CssStyles cssStyles;
    private static final String close_head = "</head>";
    private static final String open_body = "<body style=\"background-image:url('img/fondo.png')\" onload=\"load()\">";
    private String body = "nothing in de body";
    private static final String close_body = "</body>";
    private static final String close_html = "</html>";
    private String open_form = "<form method=\"post\" name=\"fmain\" action=";
    private String data_form = "";
    private static final String close_form = "</form>";
    public MessageBox messageBox;
    private String form_where = "";//valor del where del formulari

    public Xhtml5(String title, String action_form, String where) {

        SERVLET_HRZ_URL = action_form;
        this.vsFunctions = new VsFunctions();
        this.cssStyles = new CssStyles();

        this.form_where = where;

        this.cssStyles.addFileCSS("css/xhtml5.css");
        open_form += "\"" + SERVLET_HRZ_URL + "\">";

        clearBodyData();
        clearDataForm();
        this.title = title;

        //creo el messageBox
        messageBox = new MessageBox("");

        updateFunctions();
    }

    public final void updateFunctions() {
        vsFunctions.clear();

        vsFunctions.addVar("SERVLET_HRZ_URL", SERVLET_HRZ_URL);
        vsFunctions.addFile(JSXHTML);

        vsFunctions.addFunction("statusBar", "content", "document.getElementById('statusBar').innerHTML=content;");
        vsFunctions.addFunction("fes", "actions,params", "document.fmain.actions.value=actions;document.fmain.params.value=params;document.fmain.submit();");
        vsFunctions.addFunction("fes2", "actions,params,more", "document.fmain.actions.value=actions;document.fmain.params.value=params;document.fmain.more.value=more;document.fmain.submit();");

        vsFunctions.addFunction("messageBox", "data", "alert(data);");
        vsFunctions.addFunction("load", "", "init();");


    }

    public final void clearBodyData() {
        //creo status bar on hi ha el help
        this.body = "";
        DivHtml titleBar = new DivHtml("titleBar");
        addBodyData(titleBar.toHtml(this.title));

        DivHtml statusBar = new DivHtml("statusBar");
        addBodyData(statusBar.toHtml("PAGENAME"));

        addBodyData(new DivHtml("logo").toHtml("<img src=\"img/logo.png\"/>"));

        DivHtml desconectarDiv = new DivHtml("desconectar");

        addBodyData(desconectarDiv.toHtml(
                new LinkHtml("desconectar", "desconectar", "#", "vols_marxar", "", "Tancar i marxar del programa.").toHtml()));



    }

    public void addBanner(String email) {
        DivHtml emailBar = new DivHtml("emailBar");
        addBodyData(emailBar.toHtml(email));
    }

    public void addBodyData(String body) {

        this.body += body;
    }

    public final void clearDataForm() {
        this.data_form =
                "<input type=\"hidden\" name=\"more\"/>"
                + "<input name=\"actions\" type=\"hidden\"/>"
                + "<input name=\"params\" type=\"hidden\"/>";

        this.data_form += "<input type=\"hidden\" name=\"where\" value=\"" + this.form_where + "\"/>";
        this.data_form += "<input type=\"hidden\" name=\"what\" value=\" \"/>";
        this.data_form += "<input type=\"hidden\" name=\"option\" value=\" \"/>";
    }

    public void addDataForm(String data) {
        this.data_form += data;
    }

    public String getHtml() {


        String output = "";

        System.out.println("enter xhtml.");

        output += doctype;
        output += open_html;
        output += open_head;
        output += meta;
        output += open_title;
        output += this.title;
        output += close_title;
        output += this.cssStyles.toHtml();
        output += this.vsFunctions.toHtml();
        output += close_head;

        output += open_body;
        output += open_form;
        output += data_form;
        output += close_form;
        output += this.body;
        output += close_body;
        output += close_html;
        System.out.println("return xhtml.");
        return (output);
    }
}
