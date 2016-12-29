/**
 *
 */
package visualk.gallery.modules;

import java.util.ArrayList;
import org.json.JSONObject;
import visualk.gallery.db.DbWorks;
import visualk.gallery.objects.Artist;
import visualk.gallery.objects.Resource;
import visualk.gallery.objects.Work;
import visualk.hrz.Hrz;
import visualk.html5.*;

/**
 * @author alex
 *
 */
public class Detail extends Xhtml5 {

    private static final String CSS_DETAIL_FILE_NAME = "/visualk/gallery/css/detail.css";
    private static final String JS_DETAIL_FILE_NAME = "/visualk/gallery/js/detail.js";

    //private final MenuLinkBar upperMenuBar;
    private final ClassCSS cssMenuBar = new ClassCSS();

    private String idWork;

    private void addMyStyles() {
        cssStyles.addFileCSS(CSS_DETAIL_FILE_NAME);
    }

    public Detail(String title, String jsonWork) {
        super("Gallery", title, "detail");
        ClassCSS cssLink = new ClassCSS();

        addMyStyles();
        /*
        upperMenuBar = new MenuLinkBar("marxarBar", cssMenuBar);
        upperMenuBar.setHorizontal();
        upperMenuBar.addMenuLink(Hrz.getString("label.exit.artzar.hrzmkr"), "vols_marxar", Hrz.getString("help.exit.artzar.hrzmkr"), cssLink);//label,function,help
         */

        JSONObject o = new JSONObject(jsonWork);
        idWork = o.get("idWork").toString();
        System.out.println("idWork:".contains(idWork));
    }

    public String toHtml() {

        this.clearBodyData();
        this.clearDataForm();

        this.updateFunctions();

        this.vsFunctions.addFile(JS_DETAIL_FILE_NAME);

        vsFunctions.addFunction("vols_marxar", "", "if(confirm(\"" + Hrz.getString("label.exit.dialog.artzar.hrzmkr") + "\")){document.fmain.what.value='marxar';document.fmain.submit();}");

        this.addDataForm("<input type=\"hidden\" name=\"where\" value=\"detail\"/>");
        this.addDataForm("<input type=\"hidden\" name=\"what\" value=\"\"/>");
        this.addDataForm("<input type=\"hidden\" name=\"option\" value=\"\"/>");

        String workTitle = "";
        String authorName = "";
        String workImage = "";
        String workDescription = "";

        Work work = null;
        try {
            work = new DbWorks("user", "pass", "gallery_db").getWorkById(1);

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
                    for (int i = 0; i < resources.size(); i++) {
                        workImage += "<img id='imgWorkImage' src='" + resources.get(i).getUrl() + "'/><br/>";
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
        this.addBodyData(new DivHtml("cssWorkImage").toHtml(workImage));
        this.addBodyData(new DivHtml("cssWorkDescription").toHtml(workDescription));

        String footer = "<br/><br/>";
        this.addBodyData(new DivHtml("cssFooter").toHtml(footer));

        String ret = this.getHtml();
        System.out.println("return Detail.");
        return (ret);
    }

}
