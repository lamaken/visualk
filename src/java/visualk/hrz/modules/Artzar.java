/**
 *
 */
package visualk.hrz.modules;


import visualk.hrz.Hrz;
import visualk.html5.*;





/**
 * @author alex
 *
 */
public class Artzar extends Xhtml5 {

    private static final String CSS_ARTZAR_FILE_NAME = "/visualk/hrz/css/artzar.css";
    private static final String JS_ARTZAR_FILE_NAME = "/visualk/hrz/js/artzar.js";

    private static final String JS_PINO = "/visualk/hrz/js/pino.js";

    private final MenuLinkBar upperMenuBar;
    private final MenuLinkBar leftMenuBar;
    private final MenuLinkBar randomMenuBar;

    private final ClassCSS cssLink;
    private final ClassCSS cssMenuBar = new ClassCSS();
    private final ClassCSS cssInputBox = new ClassCSS();

    private final InputDialog inputDialog;

    private void addMyStyles() {

        /////////////////////// input dialog
        cssInputBox.setLeft("30px");
        cssInputBox.setTop("30%");
        cssInputBox.setRight("30px");
        cssInputBox.setHeight("80px");
        cssInputBox.setPosition("absolute");
        cssInputBox.setTextalign("center");
        cssInputBox.setColor("black");
        cssInputBox.setPadding("5px");
        cssInputBox.setBackground("black");
        cssInputBox.setZindex("200");
        cssInputBox.setVisible("hidden");

        cssStyles.addStyle(cssInputBox);

        cssLink.setColor("yellow");
        cssStyles.addStyle(cssLink);

        cssMenuBar.setColor("green");
        cssStyles.addStyle(cssMenuBar);

        cssStyles.addFileCSS(CSS_ARTZAR_FILE_NAME);

    }

    public Artzar(String title) {
        super(title, Hrz.SERVLET_URL,"artzar");
        this.cssLink = new ClassCSS();

        addMyStyles(); 

        inputDialog = new InputDialog(cssInputBox);

        upperMenuBar = new MenuLinkBar("marxarBar", cssMenuBar);
        upperMenuBar.setHorizontal();
        upperMenuBar.addMenuLink(Hrz.getString("label.exit.artzar.hrzmkr"), "vols_marxar", Hrz.getString("help.exit.artzar.hrzmkr"), cssLink);//label,function,help

        leftMenuBar = new MenuLinkBar("optionsBar", cssMenuBar);
        leftMenuBar.setVertical();
        leftMenuBar.addMenuLink(Hrz.getString("label.generate.artzar.hrzmkr"), "refresca", Hrz.getString("help.generate.artzar.hrzmkr"), cssLink);//label,function,help
        leftMenuBar.addMenuLink(Hrz.getString("label.save.artzar.hrzmkr"), "guarda", "'" + cssInputBox.getId()+"'", Hrz.getString("help.save.artzar.hrzmkr"), cssLink);//label,function,help
        leftMenuBar.addMenuLink(Hrz.getString("label.load.artzar.hrzmkr"), "carrega", Hrz.getString("help.load.artzar.hrzmkr"), cssLink);//label,function,help

        randomMenuBar = new MenuLinkBar("randomBar", cssMenuBar);
        randomMenuBar.addMenuLink(Hrz.getString("label.changeColors.artzar.hrzmkr"), "colorsRnd", Hrz.getString("help.changeColors.artzar.hrzmkr"), cssLink);//label,function,help
        randomMenuBar.addMenuLink(Hrz.getString("label.changePosition.artzar.hrzmkr"), "posicioRnd", Hrz.getString("help.changePosition.artzar.hrzmkr"), cssLink);//label,function,help
        randomMenuBar.addMenuLink(Hrz.getString("label.changeShadow.artzar.hrzmkr"), "hombraRnd", Hrz.getString("help.changeShadow.artzar.hrzmkr"), cssLink);//label,function,help
        randomMenuBar.addMenuLink(Hrz.getString("label.changeSun.artzar.hrzmkr"), "superRnd", Hrz.getString("help.changeSun.artzar.hrzmkr"), cssLink);//label,function,help

        randomMenuBar.setVertical();

    }

    public String toHtml() {
        String html_image;

        System.out.println("enter artzar.");

        //this.updateFunctions();
        this.clearBodyData();
        this.clearDataForm();

        this.updateFunctions();

        this.vsFunctions.addFile(JS_ARTZAR_FILE_NAME);
        vsFunctions.addFile(JS_PINO);

        vsFunctions.addFunction("vols_marxar","","if(confirm(\""+Hrz.getString("label.exit.dialog.artzar.hrzmkr")+"\")){document.fmain.what.value='marxar';document.fmain.submit();}");
        
        
	
 
 

 
        
        this.addDataForm("<input type=\"hidden\" name=\"where\" value=\"artzar\"/>");
        this.addDataForm("<input type=\"hidden\" name=\"what\" value=\"\"/>");
        this.addDataForm("<input type=\"hidden\" name=\"option\" value=\"\"/>");

        this.addDataForm("<input type=\"hidden\" name=\"canvasW\" value=\"\"/>");
        this.addDataForm("<input type=\"hidden\" name=\"canvasH\" value=\"\"/>");
        
        this.addDataForm("<input type=\"hidden\" name=\"mx\" value=\"60\"/>");
        this.addDataForm("<input type=\"hidden\" name=\"my\" value=\"60\"/>");
        
       
        
        

        //input dialog
        this.addBodyData(inputDialog.toHtml());
        /////////////////////////////////////////////////////// input dialog

        this.addBodyData(upperMenuBar.toHtml());
        this.addBodyData(leftMenuBar.toHtml());
        this.addBodyData(randomMenuBar.toHtml());

        String styleMarc = "style=\"padding:2px; color:white; border:solid 3px; \"";

        html_image = "<img " + styleMarc + " name=\"HrzCanvasImg\" title=\"hrzmkr img\" alt=\""+Hrz.getString("label.loading.artzar.hrzmkr")+"\" src=\"/visualk/hrz/Hrz?option=paint\"/>";

        this.addBodyData(new DivHtml("HrzCanvasDiv").toHtml(html_image));
        this.addBodyData("<div id='pino'></id>");

        String ret = this.getHtml();
        System.out.println("return ArtZar.");
        return (ret);
    }

}
