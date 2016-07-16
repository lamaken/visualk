/**
 * 
 */
package visualk.ss.objectsmierdo;
/**
 * @author �lex
 *
 */

public class LabelHtml {
	private String label=new String();

	
public LabelHtml(String label) {
	this.label=label;
}
public String toHtml(){
	return(this.label);
}
}