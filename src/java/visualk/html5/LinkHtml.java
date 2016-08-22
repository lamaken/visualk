/**
 *
 */
package visualk.html5;




/**
 * @author àlex
 *
 */
public class LinkHtml {

    public LabelHtml label;
    public String id;
    private String onclick = "";
    public String onclickAction = "";
    public String link = "";
    private String params = "";
    public String status = "";

    public String getLabel() {
        return (label.getLabel());
    }

    public String getId() {
        return (id);
    }
    public LinkHtml() {
        this.label = new LabelHtml("label_none");
        this.onclick = "onclick_none";
        this.link = "link_none";
        this.status = EscapeString.escapeHTML("status_none");
        this.params = "params_none";
        this.id = "id_none";
    }

    public LinkHtml(String id, String label, String link, String onclick, String params, String status) {
        this.label = new LabelHtml(label);
        this.onclick = onclick;
        this.link = link;
        this.status = EscapeString.escapeHTML(status);
        this.params = params;
        this.id = id;
    }

    public LinkHtml(String id, String label, String link, String onclickAction, String status) {
        this.label = new LabelHtml(label);
        this.onclickAction = onclickAction;
        this.link = link;
        this.status = EscapeString.escapeHTML(status);
        this.id = id;
    }

    public LinkHtml(String id, String label, String link, String status) {
        this.label = new LabelHtml(label);
        this.link = link;
        this.id = id;
        this.status = EscapeString.escapeHTML(status);
    }

    public String toHtml() {
        String tagStatus = "";
        String tagOnClick = "";
        
        tagStatus = "onmouseout=\"statusBar('visualk experiments v0.1')\" onmouseover=\"statusBar(\'" + this.status + "\')\"";
        if (!this.onclick.equals("")) {
            tagOnClick = "onclick=\"" + this.onclick + "(" + this.params + ")\"";
        }
        if (!this.onclickAction.equals("")) {

            tagOnClick = "onclick=\"" + this.onclickAction + "\"";

        }
        return ("<a   class=\"" + this.id + "\" " + tagStatus + " " + tagOnClick + " href=\"" + this.link + "\" >" + this.label.toHtml() + "</a>");
    }

    /**
     * @param onclickAction the onclickAction to set
     */
    public void setOnclickAction(String onclickAction) {
        this.onclickAction = onclickAction;
    }

    /**
     * @param params the params to set
     */
    public void setParams(String params) {
        this.params = params;
    }
}
