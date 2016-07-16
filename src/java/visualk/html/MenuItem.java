/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package visualk.html;



/**
 *
 * @author alex
 */
public class MenuItem extends LinkHtml{
    /**
     *
     * @param id
     * @param label
     * @param link
     * @param onclickAction
     * @param status
     */
    String parent;
    String id_item;
    String onclick_item;
    String show;
    
     MenuItem(String id_item, String label,  String onclick, String params, String status, String parent,String menuid) {
        super(new UniqueName(8).getName(), label, "#", onclick, params, status);
        
        this.id_item = id_item;
        this.setOnclickAction("over_MenuBar('"+this.id+"','"+menuid+"')");
        this.onclick_item=onclick;
        this.parent=parent;
        this.show="true";
    }
     
     
    @Override
     public String toHtml() {
        String tagStatus = "";
        String tagOnClick = "";
        tagStatus = "onmouseout=\"statusBar('" + "CMCB.MAIN_TITLE" + "')\" onmouseover=\"statusBar(\'" + this.status + "\')\"";       
        tagOnClick = "onclick=\"" + this.onclickAction + "\"";
        return ("<a   id=\""+this.id_item+"\" " + tagStatus + " " + tagOnClick + " href=\"" + this.link + "\" >" + this.label.toHtml() + "</a>");
    }
    
    public String raw(String ch){
        return(this.label.toHtml()+
                ","+this.id_item+
                ","+this.onclick_item+
                ","+this.id+
                ","+this.parent+
                ","+this.show+",");
    }
   
}
