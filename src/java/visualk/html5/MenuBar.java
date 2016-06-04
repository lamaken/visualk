/**
 *
 */
package visualk.html5;

import java.util.LinkedList;

/**
 * @author �lex
 * @version 1.a
 *
 */
public class MenuBar {

    private static final int VERTICAL_ALIGN = 1;
    private static final int HORIZONTAL_ALIGN = 2;
    private static final String FILE_JS = "js/MenuBar.js";
    private String did = "MenuBar";
    private String title = "";
    private int orientation = HORIZONTAL_ALIGN;
    private LinkedList<MenuItem> optionsMenu;
    public LinkedList<ClassCSS> cssMenuBar;
    private VsFunctions my_js;

    public void setVertical() {
        this.orientation = VERTICAL_ALIGN;
    }

    public void setHorizontal() {
        this.orientation = HORIZONTAL_ALIGN;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MenuBar(String id, ClassCSS cssMenuBar2) {
        did = id;
        cssMenuBar = new LinkedList();
        this.cssMenuBar.add(cssMenuBar2);
        optionsMenu = new LinkedList();

        my_js = new VsFunctions();
        my_js.addFile(FILE_JS);

    }

    public void addMenuItem(String id, String label, String onclick, String params, String status, String parent) {
        optionsMenu.add(new MenuItem(id, label, onclick, params, status, parent, did));
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
        String MenuSeparatorHtml = "<span style='color:black'>|</span>";

        if (this.orientation == HORIZONTAL_ALIGN) {
            htmlChar = space.toHtml();
        }
        if (this.orientation == VERTICAL_ALIGN) {
            htmlChar = enter.toHtml();
        }


        String returnHtml = my_js.toHtml()
                + "<div id=\"" + this.cssMenuBar.get(0).getId() + "\">";


        if (!this.title.equals("")) {
            returnHtml += this.title + htmlChar + MenuSeparatorHtml + htmlChar;
        }


        String rawMenu = "";

        int numItems = 0;

        //obtenim el numero de cap�aleres
        for (int n = 0; n < optionsMenu.size(); n++) {
            if (optionsMenu.get(n).parent.equals("")) {
                numItems++;
            }
        }

        String returnFillsHtml = "";
        returnFillsHtml += "<table>";


        //mostrem nomes les cap�aleres
        LinkedList<String> listparent = new LinkedList<String>();
        for (int n = 0; n < optionsMenu.size(); n++) {
            if (optionsMenu.get(n).parent.equals(""))//nomes el principal
            {
                listparent.add(optionsMenu.get(n).id_item);
                returnHtml += optionsMenu.get(n).toHtml();
                if (n < numItems + 1) {
                    returnHtml += htmlChar + MenuSeparatorHtml + htmlChar;
                }
            }
            rawMenu += optionsMenu.get(n).raw(",");
        }

        
        for (int p = 0; p < listparent.size(); p++) {

            for (int n = 0; n < optionsMenu.size(); n++) {
                if (optionsMenu.get(n).parent.equals(listparent.get(p)))//nomes el principal
                {
                    
                    ClassCSS css=new ClassCSS("menuitem_"+optionsMenu.get(n).id);
                    css.setVisible("hidden");
                    css.setBackground("red");
                    css.setPadding("10px");
                    
                    this.cssMenuBar.add(css);
                    
                    returnFillsHtml += "<tr><td class="+css.getId()+">";
                    
                    DivHtml div = new DivHtml(css.getId());
                    returnFillsHtml += div.toHtml(optionsMenu.get(n).toHtml());
                    
                    //returnFillsHtml += optionsMenu.get(n).toHtml();
                   /* if (n < numItems + 1) {
                        returnFillsHtml += enter.toHtml();
                    }*/
                    returnFillsHtml += "</td></tr>";
                }
            }
        }






        returnHtml += "</div>";
        returnFillsHtml += "</table>";



        String table =
                "<table style=\"width: 100%;background:rgb(210,210,210);line-height: 0;\" border=\"0\"  cellpadding=\"0\" cellspacing=\"0\">"
                + "<tbody><tr><td><img src='img/c1.png'/></td><td background='img/c2.png'>" + this.title + "</td><td><img src='img/c3.png'/></td></tr>"
                + "<tr><td background='img/c4.png'></td><td>" + returnHtml + "</td><td background='img/c5.png'></td></tr>"
                + "<tr><td><img src='img/c6.png'/></td><td background='img/c7.png'></td><td><img src='img/c8.png'/></td></tr></tbody></table>";



        /*
         creem els popups
         */
        String htmlFills =
                "<div id=\"submenu\">"
                + "<table style=\"width: 100%;background:rgb(210,210,210);line-height: 0;\" border=\"0\"  cellpadding=\"0\" cellspacing=\"0\">"
                + " <tbody><tr><td><img src='img/c9.png'/></td><td ></td><td><img src='img/c10.png'/></td></tr> <tr><td background='img/c4.png'></td><td><div id=\"kk\">";

        htmlFills += returnFillsHtml;
        htmlFills += "</div></td><td background='img/c5.png'></td></tr>"
                + "<tr><td><img src='img/c6.png'/></td><td background='img/c7.png'></td><td><img src='img/c8.png'/></td></tr></tbody></table>"
                + "</div>";

        DivHtml divfills = new DivHtml("fills_" + this.did);


        DivHtml dv = new DivHtml(this.did);
        DivHtml div_raw = new DivHtml("raw_" + this.did);
        div_raw.setDiv_style("visibility: hidden");

        return (div_raw.toHtml(rawMenu) + dv.toHtml(table) + divfills.toHtml(htmlFills));
    }
}
