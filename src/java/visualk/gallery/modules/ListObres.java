package visualk.gallery.modules;

import visualk.hrz.Hrz;
import visualk.html5.ClassCSS;
import visualk.html5.MenuLinkBar;
import visualk.html5.Xhtml5;

public class ListObres extends Xhtml5 {

    private static final String CSS_LIST_FILE_NAME = "/visualk/hrz/css/listhorizons.css";
    private static final String JS_LIST_FILE_NAME = "/visualk/hrz/js/listhorizons.js";
    private static final String JS_BLAZYLOAD_FILE_NAME = "/visualk/hrz/js/blazy.min.js";
    
    
   
    private MenuLinkBar upperMenuBar;

    private final String googlePlusTagBefore = "<g:plusone href=\"";
    private final String googlePlusAfter = "\" size=\"small\" count=\"false\"></g:plusone>";

    private final String facebookLikeTagBefore = "<fb:like href=\"";
    private final String facebookLikeTagAfter = "\" layout=\"button\" show-faces=\"true\" action=\"like\" colorscheme=\"light\" />";

    public ListObres(String title) {
        super(title, Hrz.SERVLET_URL,"listhorizons");
        ClassCSS cssLink = new ClassCSS();
        cssLink.setColor("yellow");

        ClassCSS cssMenuBar = new ClassCSS();
        cssMenuBar.setColor("green");

        upperMenuBar = new MenuLinkBar("tornarBar", cssMenuBar);
        upperMenuBar.setHorizontal();
        upperMenuBar.addMenuLink(Hrz.getString("back.gallery.hrzmkr"), "torna", Hrz.getString("help.back.gallery.hrzmkr"), cssLink);//label,function,help

        cssStyles.addStyle(cssMenuBar);

        cssStyles.addStyle(cssLink);
        cssStyles.addFileCSS(CSS_LIST_FILE_NAME);

    }

    public String toHtml() {
        String html_image = "";

        
        this.clearBodyData();
        this.clearDataForm();

        this.updateFunctions();

        vsFunctions.addFile(JS_LIST_FILE_NAME);
        vsFunctions.addFile(JS_BLAZYLOAD_FILE_NAME);
        
        

        this.addDataForm("<input type=\"hidden\" name=\"where\" value=\"listhorizons\"/>");
        this.addDataForm("<input type=\"hidden\" name=\"what\" value=\"\"/>");
        this.addDataForm("<input type=\"hidden\" name=\"nom\" value=\"\"/>");
      

         
        this.addDataForm("<input type=\"hidden\" name=\"mx\" value=\"60\"/>");
        this.addDataForm("<input type=\"hidden\" name=\"my\" value=\"60\"/>");
        
        
        this.addBodyData(upperMenuBar.toHtml());

        
        
        
        
        
        
        String ret="listObres";
        return (ret);
    }

}
