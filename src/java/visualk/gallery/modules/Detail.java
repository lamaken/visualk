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

    private String idWork;

    private void addMyStyles() {
        cssStyles.addFileCSS(CSS_DETAIL_FILE_NAME);
    }

    public Detail() {

        super("Gallery", "idle.", "detail");

    }

    public Detail(String title) {
        super("Gallery", title, "detail");

        addMyStyles();
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
        String allImagesPath = "";
        try {
            work = new DbWorks().getWorkById(idWork);

            if (work != null) {
                workTitle = work.title;
                workDescription = work.description;

                ArrayList<Artist> artists = work.artists;
                if (artists != null) {
                    for (int i = 0; i < artists.size(); i++) {
                        authorName += artists.get(i).getSurname() + ", " + artists.get(i).getName();
                    }
                }
                ArrayList<Resource> resources = work.resources;
                if (resources != null) {
                    workImage = "";
                    for (int i = 0; i < resources.size(); i++) {
                        allImagesPath = allImagesPath.concat("<img alt='"+work.title+"' id='"+new UniqueName(3).getName()+"' class='cssImage' src='"+Main.HOST_NAME + resources.get(i).getUrl()+"'/>");
                    }
                }
            }

        } catch (Exception e) {
            workTitle = "TITULO DE LA OBRA";
            authorName = "Antoni Tapies";
            workImage = "<img class='cssImage' src='img/tapies.jpeg'/></iframe>";
            workDescription = "¿DATABASE ERROR MAY BE DOWN? "+e.getMessage()+"<p> Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer purus ipsum, condimentum a hendrerit at, egestas id leo. Nam pellentesque bibendum dolor, ac molestie urna viverra eget. \n</p>";

        }
        

        //workDescription = workDescription + workDescription;
        this.addBodyData(new DivHtml("cssWorkTitle").toHtml(workTitle));
        this.addBodyData(new DivHtml("cssAuthorName").toHtml(authorName));
        this.addBodyData(new DivHtml("cssWorkDescription").toHtml(workDescription));       
        this.addBodyData(new DivHtml("cssImages").toHtml(allImagesPath));

        String footer = "<br/><br/>";
        this.addBodyData(new DivHtml("cssFooter").toHtml(footer));

        LinkHtml linkNext = new LinkHtml("seguent", "seg --->", "#", "seguent", "", "Passa a la seguent obra.");
        LinkHtml linkPrev = new LinkHtml("anterior", "<--- prev", "#", "anterior", "", "Passa a l`obra anterior.");
        
        this.addBodyData(new DivHtml("nextButton").toHtml(linkNext.toHtml()));
        this.addBodyData(new DivHtml("prevButton").toHtml(linkPrev.toHtml()));
        
        String ret = this.getHtml();

        System.out.println("return Detail.");
        return (ret);
    }

}
