/**
 *
 */
package visualk.hrz.modules;

import visualk.Main;
import visualk.hrz.Hrz;
import visualk.html.ClassCSS;
import visualk.html.DivHtml;
import visualk.html.InputDialog;
import visualk.html.MenuBar;

import visualk.html.Xhtml;

/**
 * @author alex
 *
 */
public class Artzar extends Xhtml {

    private static final String CSS_ARTZAR_FILE_NAME = "/hrz/css/artzar.css";
    private static final String JS_ARTZAR_FILE_NAME = "/hrz/js/artzar.js";

    private static final String JS_PINO = "/hrz/js/pino.js";

    private MenuBar upperMenuBar;
    private MenuBar leftMenuBar;
    private MenuBar randomMenuBar;

    private ClassCSS cssLink = new ClassCSS();
    private ClassCSS cssMenuBar = new ClassCSS();
    private ClassCSS cssInputBox = new ClassCSS();

    private InputDialog inputDialog;

    private void addMyStyles() {

        /////////////////////// input dialog
        cssInputBox.setLeft("30%");
        cssInputBox.setTop("30%");
        cssInputBox.setRight("30%");
        //cssInputBox.setHeight("80px");
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
        super(title, Hrz.SERVELT_URL);

        addMyStyles(); 

        inputDialog = new InputDialog(cssInputBox);

        upperMenuBar = new MenuBar("marxarBar", cssMenuBar);
        upperMenuBar.setHorizontal();
        upperMenuBar.addMenuLink(Main.getString("label.exit.artzar.hrzmkr"), "vols_marxar", Main.getString("help.exit.artzar.hrzmkr"), cssLink);//label,function,help

        leftMenuBar = new MenuBar("optionsBar", cssMenuBar);
        leftMenuBar.setVertical();
        leftMenuBar.addMenuLink(Main.getString("label.generate.artzar.hrzmkr"), "refresca", Main.getString("help.generate.artzar.hrzmkr"), cssLink);//label,function,help
        leftMenuBar.addMenuLink(Main.getString("label.save.artzar.hrzmkr"), "guarda", "'" + cssInputBox.getId() + "'", Main.getString("help.save.artzar.hrzmkr"), cssLink);//label,function,help
        leftMenuBar.addMenuLink(Main.getString("label.load.artzar.hrzmkr"), "carrega", Main.getString("help.load.artzar.hrzmkr"), cssLink);//label,function,help

        randomMenuBar = new MenuBar("randomBar", cssMenuBar);
        randomMenuBar.addMenuLink(Main.getString("label.changeColors.artzar.hrzmkr"), "colorsRnd", Main.getString("help.changeColors.artzar.hrzmkr"), cssLink);//label,function,help
        randomMenuBar.addMenuLink(Main.getString("label.changePosition.artzar.hrzmkr"), "posicioRnd", Main.getString("help.changePosition.artzar.hrzmkr"), cssLink);//label,function,help
        randomMenuBar.addMenuLink(Main.getString("label.changeShadow.artzar.hrzmkr"), "hombraRnd", Main.getString("help.changeShadow.artzar.hrzmkr"), cssLink);//label,function,help
        randomMenuBar.addMenuLink(Main.getString("label.changeSun.artzar.hrzmkr"), "superRnd", Main.getString("help.changeSun.artzar.hrzmkr"), cssLink);//label,function,help

        randomMenuBar.setVertical();

    }

    public String toHtml() {
        String html_image;

        System.out.println("enter artzar.");

        //this.updateFunctions();
        this.clearBodyData();
        this.clearDataForm();

        this.updateFunctions();

        vsFunctions.addFile(JS_ARTZAR_FILE_NAME);
        vsFunctions.addFile(JS_PINO);

        vsFunctions.addFunction("vols_marxar","","if(confirm(\""+Main.getString("label.exit.dialog.artzar.hrzmkr")+"\")){document.fmain.what.value='marxar';document.fmain.submit();}");
        
        
	
 
 

 
        
        this.addDataForm("<input type=\"hidden\" name=\"where\" value=\"artzar\"/>");
        this.addDataForm("<input type=\"hidden\" name=\"what\" value=\"\"/>");
        this.addDataForm("<input type=\"hidden\" name=\"option\" value=\"\"/>");

        this.addDataForm("<input type=\"hidden\" name=\"canvasW\" value=\"\"/>");
        this.addDataForm("<input type=\"hidden\" name=\"canvasH\" value=\"\"/>");

        //input dialog
        this.addBodyData(inputDialog.toHtml());
        /////////////////////////////////////////////////////// input dialog

        this.addBodyData(upperMenuBar.toHtml());
        this.addBodyData(leftMenuBar.toHtml());
        this.addBodyData(randomMenuBar.toHtml());

        String styleMarc = "style=\"padding:2px; color:white; border:solid 3px; \"";

        html_image = "<img " + styleMarc + " name=\"HrzCanvasImg\" title=\"Horizon Maker v0.2\" alt=\"carregant...\" src=\"/hrz/Hrz?option=paint\"/>";

        this.addBodyData(new DivHtml("HrzCanvasDiv").toHtml(html_image));
        this.addBodyData("<div id='pino'></id>");

        String ret = this.getHtml();
        System.out.println("return ArtZar.");
        return (ret);
    }

}
