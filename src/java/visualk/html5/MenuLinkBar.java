/**
 *
 */
package visualk.html5;

import java.util.LinkedList;
import visualk.Main;

/**
 * @author �lex
 * @version 1.a
 *
 */
public class MenuLinkBar {

    private static final int VERTICAL_ALIGN = 1;
    private static final int HORIZONTAL_ALIGN = 2;

    private String did = "MenuLinkBar";
    private String title = "";

    private int orientation = HORIZONTAL_ALIGN;

    private LinkedList<LinkHtml> optionsMenu;

    private ClassCSS cssMenuBar;

    public void setVertical() {
        this.orientation = VERTICAL_ALIGN;
    }

    public void setHorizontal() {
        this.orientation = HORIZONTAL_ALIGN;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MenuLinkBar(String id, ClassCSS cssMenuBar) {
        did = id;
        this.cssMenuBar = cssMenuBar;
        optionsMenu = new LinkedList<LinkHtml>();
    }

    public void addMenuLink(String label, String onclick, String status, ClassCSS css) {
       this.cssMenuBar=css;
        optionsMenu.add(new LinkHtml(css.getId(), label, "#", onclick, "", status));
    }

    public void addMenuLink(String label, String onclick, String params, String status, ClassCSS css) {
        this.cssMenuBar = css;
        optionsMenu.add(new LinkHtml(css.getId(), label, "#", onclick, params, status));
    }

    public int eliminaOption(String id) {
        for (int n = 0; n < optionsMenu.size(); n++) {
            if (optionsMenu.get(n).getId() == id) {
                optionsMenu.remove(n);
                return (0);
            }
        }
        return (-1);
    }

    public void clear() {
        optionsMenu.clear();
    }

    public String toHtml() {
        String htmlChar = "";
        SpaceHtml space = new SpaceHtml();
        CrHtml enter = new CrHtml();

        if (this.orientation == HORIZONTAL_ALIGN) {
            htmlChar = space.toHtml();
        }
        if (this.orientation == VERTICAL_ALIGN) {
            htmlChar = enter.toHtml();
        }

        String returnHtml = "<div style=\"padding:10px\">";

        if (!this.title.equals("")) {
            returnHtml += this.title + htmlChar + htmlChar;
        }

        for (int n = 0; n < optionsMenu.size(); n++) {
             returnHtml += "<div style=\"padding:10px\">"+optionsMenu.get(n).toHtml()+"</div>";
            if (n < optionsMenu.size() - 1) {
                returnHtml += htmlChar;
            }
        }
        returnHtml += "</div>";

     
        
        String table = "<table style=\"width: 100%;line-height: 0;\" border=\"0\"  cellpadding=\"0\" cellspacing=\"0\">"
                + "<tbody><tr><td><img src='" + Main.HOST_NAME + Main.HOST_VISUALK + "/img/c1.png'/></td><td background='" + Main.HOST_NAME + Main.HOST_VISUALK + "/img/c2.png'>" + this.title + "</td><td><img src='" + Main.HOST_NAME + Main.HOST_VISUALK + "/img/c3.png'/></td></tr>"
                + "<tr><td background='" + Main.HOST_NAME + Main.HOST_VISUALK + "/img/c4.png'></td><td>" + returnHtml + "</td><td background='" + Main.HOST_NAME + Main.HOST_VISUALK + "/img/c5.png'></td></tr>"
                + "<tr><td><img src='" + Main.HOST_NAME + Main.HOST_VISUALK + "/img/c6.png'/></td><td background='" + Main.HOST_NAME + Main.HOST_VISUALK + "/img/c7.png'></td><td><img src='" + Main.HOST_NAME + Main.HOST_VISUALK + "/img/c8.png'/></td></tr></tbody></table>";


        return (new DivHtml(this.did).toHtml(table));
    }

    public void addMenuLink(String label, String onclick, String params, String status, String id) {
        optionsMenu.add(new LinkHtml("link_" + id + "_" + label, label, "#", onclick, "", status));
    }

    public void addMenuLink(String id, String label, String ref, String onclick, String params, String status) {
        optionsMenu.add(new LinkHtml("link_" + id + "_" + label, label, "#", onclick, params, status));
    }

}
