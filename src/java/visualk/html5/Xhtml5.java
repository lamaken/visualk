package visualk.html5;

import visualk.Main;

public class Xhtml5 {

    private static final String JSXHTML = Main.HOST_NAME + Main.HOST_VISUALK + "/js/xhtml5.js";
    private static final String doctype = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" ?><!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><html xmlns=\"http://www.w3.org/1999/xhtml\" >";
    private static final String open_html = "<html>";
    private static final String open_head = "<head>";
    private static final String meta = "<meta charset=\"ISO-8859-1\"/><meta name=\"viewport\" content=\"width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0\">";
    private static final String open_title = "<title>";
    private static final String close_title = "</title>";
    private static final String close_head = "</head>";
    private static final String open_body = "<body style=\"background-image:url('" + Main.HOST_NAME + Main.HOST_VISUALK + "/img/fondo.png')\" onload=\"load()\">";
    private static final String close_body = "</body>";
    private static final String close_html = "</html>";
    private static final String close_form = "</form>";
    
    private static String SERVLET_URL;
    
    
    private String title = "default title";
    public VsFunctions vsFunctions;
    public CssStyles cssStyles;
    private String body = "nothing in de body";
    private String open_form = "<form method=\"post\" name=\"fmain\" action=";
    private String data_form = "";
    public MessageBox messageBox;
    private String form_where = "";//valor del where del formulari

    private final String google_scripts = "<script src=\"https://apis.google.com/js/platform.js\" async defer></script>";
    private final String facebook_scripts = "<div id=\"fb-root\"></div>\n"
            + "<script>(function(d, s, id) {"
            + "  var js, fjs = d.getElementsByTagName(s)[0];"
            + "  if (d.getElementById(id)) return;"
            + "  js = d.createElement(s); js.id = id;"
            + "  js.src = \"//connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.8\";"
            + "  fjs.parentNode.insertBefore(js, fjs);"
            + "}(document, 'script', 'facebook-jssdk'));</script>";

    public Xhtml5(String appServlet,String title, String where) {

        SERVLET_URL = Main.HOST_NAME + Main.HOST_VISUALK +"/"+ appServlet;
        this.vsFunctions = new VsFunctions();
        this.cssStyles = new CssStyles();

        this.form_where = where;

        this.cssStyles.addFileCSS(Main.HOST_NAME + Main.HOST_VISUALK + "/css/xhtml5.css");
        open_form += "\"" + SERVLET_URL + "\">";

        clearBodyData();
        clearDataForm();
        this.title = title;

        //creo el messageBox
        messageBox = new MessageBox("");

        updateFunctions();
    }

    public final void updateFunctions() {
        vsFunctions.clear();

        vsFunctions.addVar("SERVLET_URL", SERVLET_URL);
        vsFunctions.addFile(JSXHTML);

        addingBasicFunctions();

    }

    private void addingBasicFunctions() {
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
        addBodyData(statusBar.toHtml(this.title));

        addBodyData(new DivHtml("logo").toHtml("<img src=\"" + Main.HOST_NAME + Main.HOST_VISUALK + "/img/logo.png\"/>"));

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

        this.data_form
                = "<input type=\"hidden\" name=\"more\"/>"
                + "<input name=\"actions\" type=\"hidden\"/>"
                + "<input name=\"params\" type=\"hidden\"/>";
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
        output += this.google_scripts;
        output += close_head;

        output += open_body;
        output += this.facebook_scripts;
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
