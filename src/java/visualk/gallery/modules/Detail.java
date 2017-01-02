/**
 *
 */
package visualk.gallery.modules;

import java.util.ArrayList;
import org.json.JSONObject;
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

    private final MenuLinkBar upperSeguentMenuBar,upperAnteriorMenuBar;
    private final ClassCSS cssSeguentMenuBar = new ClassCSS();
    private final ClassCSS cssAnteriorMenuBar = new ClassCSS();
    

    private String idWork;

    private void addMyStyles() {
        cssStyles.addFileCSS(CSS_DETAIL_FILE_NAME);
        
        cssSeguentMenuBar.setColor("green");
        cssAnteriorMenuBar.setColor("green");
        cssStyles.addStyle(cssSeguentMenuBar);
        cssStyles.addStyle(cssAnteriorMenuBar);
        
        
    }

    public Detail(String title, String jsonWork) {
        super("Gallery", title, "detail");
        
        addMyStyles();
        
        upperSeguentMenuBar = new MenuLinkBar("seguentBar", cssSeguentMenuBar);
        upperSeguentMenuBar.setHorizontal();
        upperSeguentMenuBar.addMenuLink("seguent >>", "seguent", "passes a la següent obra.", cssSeguentMenuBar);//label,function,help
        
        upperAnteriorMenuBar = new MenuLinkBar("anteriorBar", cssAnteriorMenuBar);
        upperAnteriorMenuBar.setHorizontal();
        upperAnteriorMenuBar.addMenuLink("<< anterior", "anterior", "passes a l`anterior obra.", cssAnteriorMenuBar);//label,function,help
     
        JSONObject o = new JSONObject(jsonWork);
        idWork = o.get("idWork").toString();
        System.out.println("idWork:".contains(idWork));
    }

    public String toHtml() {
        
        this.useBackgroundRemoteMediaImage("http://alkasoft.org/visualk/art/Mixed?mx=5&my=5&cellw=2");

        this.clearBodyData();
        this.clearDataForm();

        this.updateFunctions();

        this.vsFunctions.addFile(JS_DETAIL_FILE_NAME);

        vsFunctions.addFunction("seguent", "", "document.fmain.what.value='seguent';document.fmain.submit();}");
        vsFunctions.addFunction("anterior", "", "document.fmain.what.value='anterior';document.fmain.submit();}");
        

        this.addDataForm("<input type=\"hidden\" name=\"where\" value=\"detail\"/>");       //detail    
        this.addDataForm("<input type=\"hidden\" name=\"what\" value=\"\"/>");              //next,prev,zoom,buy
        this.addDataForm("<input type=\"hidden\" name=\"option\" value=\""+idWork+"\"/>");  //idWork

        String workTitle = "";
        String authorName = "";
        String workImage = "";
        String workDescription = "";

        Work work = null;
        try {
            work = new DbWorks("user", "pass", "gallery_db").getWorkById(Integer.parseInt(idWork));

            if (work != null) {
                workTitle = work.getTitle();
                workDescription = work.getDescription();

                ArrayList<Artist> artists = work.getArtists();
                if (artists != null) {
                    for (int i = 0; i < artists.size(); i++) {
                        authorName += " & " + artists.get(i).getName();
                    }
                }
                ArrayList<Resource> resources = work.getResources();
                if (resources != null) {
                    workImage = "";
                    for (int i = 0; i < resources.size(); i++) {
                        workImage += "<img class=\"cssImage\" src=\"" + resources.get(i).getUrl() + "\" />";

                    }
                }
            }

        } catch (Exception e) {
            workTitle = "TITULO DE LA OBRA";
            authorName = "Antoni Tapies";
            workImage = "<img id='imgWorkImage' src='img/tapies.jpeg'/>";
            workDescription = "<p> Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer purus ipsum, condimentum a hendrerit at, egestas id leo. Nam pellentesque bibendum dolor, ac molestie urna viverra eget. Suspendisse potenti. Praesent arcu justo, porta a tellus et, vulputate dapibus nunc. Sed dictum sagittis felis eget eleifend. In scelerisque tristique tortor, ut auctor quam. Mauris a dictum lorem, ut ultrices quam. Sed ullamcorper lorem et luctus iaculis. Vivamus risus lacus, porta ut finibus eu, lacinia et tellus.\n</p>";

        }

        workDescription = workDescription + workDescription;
        this.addBodyData(new DivHtml("cssWorkTitle").toHtml(workTitle));
        this.addBodyData(new DivHtml("cssAuthorName").toHtml(authorName));
        this.addBodyData(new DivHtml("cssWorkDescription").toHtml(workDescription));
        this.addBodyData(new DivHtml("cssImages").toHtml(workImage));
 
        String footer = "<br/><br/>";
        this.addBodyData(new DivHtml("cssFooter").toHtml(footer));
        
        
        
        this.addBodyData(upperAnteriorMenuBar.toHtml());
        this.addBodyData(upperSeguentMenuBar.toHtml());
        
        

        String ret = this.getHtml();
        System.out.println("return Detail.");
        return (ret);
    }

}
