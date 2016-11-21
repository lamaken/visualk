package visualk.hrz.modules;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import visualk.Main;

import visualk.hrz.Hrz;
import visualk.hrz.db.DbHorizons;
import visualk.hrz.objects.Horizon;
import visualk.html5.ClassCSS;
import visualk.html5.DivHtml;
import visualk.html5.MenuLinkBar;
import visualk.html5.UniqueName;
import visualk.html5.Xhtml5;

public class ListHorizons extends Xhtml5 {

    private static final String CSS_LIST_FILE_NAME = "/visualk/hrz/css/listhorizons.css";
    private static final String JS_LIST_FILE_NAME = "/visualk/hrz/js/listhorizons.js";

    private MenuLinkBar upperMenuBar;

    private final String googlePlusTagBefore = "<g:plusone href=\"";
    private final String googlePlusAfter = "\" size=\"small\" count=\"false\"></g:plusone>";

    private final String facebookLikeTagBefore = "<fb:like href=\"";
    private final String facebookLikeTagAfter = "\" layout=\"button\" show-faces=\"true\" action=\"like\" colorscheme=\"light\" />";

    private int maxWidth;
    private int maxHeight;

    public ListHorizons(String title) {
        super("hrz/Hrz", title, "listhorizons");
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

    public void setSize(int mx, int my) {
        maxWidth = mx;
        maxHeight = my;
    }

    public String toHtml() {
        String html_image = "";

        this.clearBodyData();
        this.clearDataForm();

        this.updateFunctions();

        vsFunctions.addFile(JS_LIST_FILE_NAME);

        this.addDataForm("<input type=\"hidden\" name=\"where\" value=\"listhorizons\"/>");
        this.addDataForm("<input type=\"hidden\" name=\"what\" value=\"\"/>");
        this.addDataForm("<input type=\"hidden\" name=\"nom\" value=\"\"/>");

        this.addDataForm("<input type=\"hidden\" name=\"mx\" value=\"60\"/>");
        this.addDataForm("<input type=\"hidden\" name=\"my\" value=\"60\"/>");

        this.addBodyData(upperMenuBar.toHtml());

        DbHorizons db = new DbHorizons("hrzmkr_user", "hrzmkr_password", "hrzmkr_db"); //connexio a la BD
        int tre = new Random().nextInt(50);

        ResultSet rs = db.listHrzns(tre, 10, maxWidth, maxHeight - 100);//TODO:search good number till 100
        String namehrz = "";
        String table = "";
        String notable = "";

        String tds = "";

        try {
            table = "<table border=0 cellspacing=10px padding=10px><tr>";

            if (rs == null) {
                namehrz = "UNABLE MYSQL";
                Horizon hrz = new Horizon(namehrz);

                hrz.carrega(namehrz);
                String id_div = new UniqueName(8).getName();

                html_image = "DATABASE ERROR";

                tds += "<td>" + new DivHtml(id_div).toHtml(html_image) + "</td>";

            } else {
                while (rs.next()) {
                    namehrz = rs.getString("nameHrz");
                    System.out.println("nameHrz:" + namehrz);
                    Horizon hrz = new Horizon(namehrz);
                    //hrz.carrega(namehrz);
                    String id_div = new UniqueName(8).getName();

                    html_image = "<img "
                            + "title=\"" +namehrz+ "\" "
                            + "alt=\"" + Hrz.getString("label.loading.gallery.hrzmkr") + "\" "
                            //+ "onclick=\"selecciona('" + id_div + "')\" "
                            //+ "ondblclick=\"edita('" + namehrz + "')\" "
                            + "src=\"" + Main.HOST_NAME + Main.HOST_VISUALK + "/hrz/Hrz?option=paint&amp;namehrz=" + namehrz + "\" "
                            + "data-src=\"" + Main.HOST_NAME + Main.HOST_VISUALK + "/hrz/Hrz?option=paint&amp;namehrz=" + namehrz + "\"/>";

                    String html_google = googlePlusTagBefore + Main.HOST_NAME + Main.HOST_VISUALK + "/hrz/Hrz?option=paint&amp;namehrz=" + namehrz + googlePlusAfter;

                    String html_facebook = facebookLikeTagBefore + Main.HOST_NAME + Main.HOST_VISUALK + "/hrz/Hrz?option=paint&amp;namehrz=" + namehrz + facebookLikeTagAfter;

                    tds += "<td><table border=0 padding=5px><tr><td colspan=2>" + new DivHtml(id_div).toHtml(html_image) + "</td></tr><tr><td width=50% align=right>" + html_facebook + "</td><td align=left width=50% >" + html_google + "</td></tr></table></td>";

                    notable += html_image;
                }
                rs.close();
            }
            table += tds + "</tr></table>";

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        this.addBodyData(new DivHtml("HrzListDiv").toHtml(table));
        //this.addBodyData(new DivHtml("HrzListDiv").toHtml(notable));

        String ret = this.getHtml();
        db.disconnect();
        return (ret);
    }

}
