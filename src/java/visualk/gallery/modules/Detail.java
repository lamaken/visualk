/**
 *
 */
package visualk.gallery.modules;


import visualk.gallery.Gallery;
import visualk.hrz.Hrz;
import visualk.html5.*;





/**
 * @author alex
 *
 */
public class Detail extends Xhtml5 {

    private static final String CSS_ARTZAR_FILE_NAME = "/visualk/gallery/css/detail.css";
    private static final String JS_ARTZAR_FILE_NAME = "/visualk/gallery/js/detail.js";

    private final MenuLinkBar upperMenuBar;
   
    private final ClassCSS cssMenuBar = new ClassCSS();

    

    private void addMyStyles() {
        cssStyles.addFileCSS(CSS_ARTZAR_FILE_NAME);
    }

    public Detail(String title) {
        super("Gallery",title, "artzar");
        ClassCSS cssLink = new ClassCSS();
    
        addMyStyles(); 

       

        upperMenuBar = new MenuLinkBar("marxarBar", cssMenuBar);
        upperMenuBar.setHorizontal();
        upperMenuBar.addMenuLink(Hrz.getString("label.exit.artzar.hrzmkr"), "vols_marxar", Hrz.getString("help.exit.artzar.hrzmkr"), cssLink);//label,function,help
    }

    public String toHtml() {
        String html_image;

        System.out.println("enter artzar.");

        //this.updateFunctions();
        this.clearBodyData();
        this.clearDataForm();

        this.updateFunctions();

        this.vsFunctions.addFile(JS_ARTZAR_FILE_NAME);
    
        vsFunctions.addFunction("vols_marxar","","if(confirm(\""+Hrz.getString("label.exit.dialog.artzar.hrzmkr")+"\")){document.fmain.what.value='marxar';document.fmain.submit();}");
    
        
        this.addDataForm("<input type=\"hidden\" name=\"where\" value=\"artzar\"/>");
        this.addDataForm("<input type=\"hidden\" name=\"what\" value=\"\"/>");
        this.addDataForm("<input type=\"hidden\" name=\"option\" value=\"\"/>");

        this.addDataForm("<input type=\"hidden\" name=\"mx\" value=\"60\"/>");
        this.addDataForm("<input type=\"hidden\" name=\"my\" value=\"60\"/>");
        
   

        this.addBodyData(upperMenuBar.toHtml());
    
        String styleMarc = "style=\"padding:2px; color:white; border:solid 3px; \"";

        html_image = "<img " + styleMarc + " name=\"HrzCanvasImg\" title=\"hrzmkr img\" alt=\""+Hrz.getString("label.loading.artzar.hrzmkr")+"\" src=\"/visualk/\"/>";

        this.addBodyData(new DivHtml("HrzCanvasDiv").toHtml(html_image));
        this.addBodyData("<div id='pino'></id>");

        String ret = this.getHtml();
        System.out.println("return Detail.");
        return (ret);
    }

}
