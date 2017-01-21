/**
 *
 */
package visualk.gallery.modules;

import java.util.ArrayList;

import visualk.Main;
import visualk.gallery.db.DbWorks;
import visualk.gallery.objects.Artist;
import visualk.gallery.objects.Resource;
import visualk.gallery.objects.Work;

import visualk.html5.*;

/**
 * @author alex
 *
 */
public class Detail extends Xhtml5 {

    private static final String CSS_DETAIL_FILE_NAME = Main.HOST_NAME + Main.HOST_VISUALK + "/gallery/css/detail.css";
    private static final String JS_DETAIL_FILE_NAME = Main.HOST_NAME + Main.HOST_VISUALK + "/gallery/js/detail.js";

    private MenuLinkBar navigateMenuLinkBar=null;
   // private final MenuBar menuBar;
    private final ClassCSS cssSeguentMenuBar = new ClassCSS();
    private final ClassCSS cssAnteriorMenuBar = new ClassCSS();
 //   private final ClassCSS cssMenuBar = new ClassCSS();
    
    

    private String idWork;

    private void addMyStyles() {
        cssStyles.addFileCSS(CSS_DETAIL_FILE_NAME);

        cssSeguentMenuBar.setColor("green");
        cssAnteriorMenuBar.setColor("blue");
  //      cssMenuBar.setColor("red");
        
        cssStyles.addStyle(cssSeguentMenuBar);
        cssStyles.addStyle(cssAnteriorMenuBar);
 //       cssStyles.addStyle(cssMenuBar);
        

    }
    public Detail() {

     super("Gallery", "idle.", "detail");
     
    }

    public Detail(String title) {
        super("Gallery", title, "detail");

    

        addMyStyles();

        if(navigateMenuLinkBar==null)navigateMenuLinkBar = new MenuLinkBar("navigateBar", cssSeguentMenuBar);
        navigateMenuLinkBar.setVertical();
        navigateMenuLinkBar.addMenuLink("seguent >>", "seguent", "passes a la següent obra.", cssSeguentMenuBar);//label,function,help
        navigateMenuLinkBar.addMenuLink("<< anterior", "anterior", "passes a l'obra anterior.", cssAnteriorMenuBar);//label,function,help
        

    }

    public String toHtml(String jsonWork) {
        
        idWork = jsonWork.split("=")[1];
        System.out.println("idWork:".contains(idWork));

        this.useBackgroundRemoteMediaImage("");

        this.clearBodyData();
        this.clearDataForm();

        this.updateFunctions();

        this.vsFunctions.addFile(JS_DETAIL_FILE_NAME);

        vsFunctions.addFunction("seguent", "", "document.fmain.what.value='seguent';document.fmain.submit();");
        vsFunctions.addFunction("anterior", "", "document.fmain.what.value='anterior';document.fmain.submit();");

        this.addDataForm("<input type=\"hidden\" name=\"where\" value=\"detail\"/>");       //detail    
        this.addDataForm("<input type=\"hidden\" name=\"what\" value=\"\"/>");              //next,prev,zoom,buy
        this.addDataForm("<input type=\"hidden\" name=\"option\" value=\"" + idWork + "\"/>");  //idWork

        String workTitle = "";
        String authorName = "";
        String workImage = "";
        String workDescription = "";

        Work work = null;
        try {
            work = new DbWorks().getWorkById(Integer.parseInt(idWork));

            if (work != null) {
                workTitle = work.getTitle();  
                workDescription = work.getDescription();

                ArrayList<Artist> artists = work.getArtists();
                if (artists != null) {
                    for (int i = 0; i < artists.size(); i++) {
                        authorName += artists.get(i).getSurname()+", "+artists.get(i).getName();
                    }
                }
                ArrayList<Resource> resources = work.getResources();
                if (resources != null) {
                    workImage = "";
                    for (int i = 0; i < resources.size(); i++) {
                       workImage += "<img src=\"";
                       workImage += Main.HOST_NAME+resources.get(i).getUrl();
                       workImage += "\" class=\"cssImage\"/>";


                    }
                }
            }
                  

        } catch (Exception e) {
            workTitle = "TITULO DE LA OBRA";
            authorName = "Antoni Tapies";
            workImage = "<img class='cssImage' src='img/tapies.jpeg'/>";
            workDescription = "<p> Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer purus ipsum, condimentum a hendrerit at, egestas id leo. Nam pellentesque bibendum dolor, ac molestie urna viverra eget. \n</p>";

        }

        workDescription = workDescription + workDescription;
        this.addBodyData(new DivHtml("cssWorkTitle").toHtml(workTitle));
        
        this.addBodyData(new DivHtml("cssWorkDescription").toHtml(workDescription));

        this.addBodyData(new DivHtml("cssImages").toHtml(workImage));
        this.addBodyData(new DivHtml("cssAuthorName").toHtml(authorName));

        
        String footer = "<br/><br/>";
        this.addBodyData(new DivHtml("cssFooter").toHtml(footer));
        
       // this.addBodyData(menuBar.toHtml());
        this.addBodyData(navigateMenuLinkBar.toHtml());

        String ret = this.getHtml();
        System.out.println("return Detail.");
        return (ret);
    }

}
