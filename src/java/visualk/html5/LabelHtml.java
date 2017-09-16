/**
 *
 */
package visualk.html5;

/**
 * @author �lex
 *
 */
public class LabelHtml {

    private String label = new String();

    public String getLabel() {
        return (label);
    }

   

    public LabelHtml(String label) {
        this.label = label;
    }

    public String toHtml() {
        return EscapeString.escapeHTML(this.label);
    }
}