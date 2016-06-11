/**
 * 
 */
package visualk.html;

/**
 * @author alex
 *
 */
public class VsFunctions {

	private String script="";

	public void addVar(String name,String value){
		this.script+="<script type=\"text/javascript\">";
		this.script+=name+"=\""+value+"\";";
		this.script+="</script>";
	}
	
	public void addFile(String outFile) {
		this.script+="<script type=\"text/javascript\" src=\""+outFile+"\"></script>";
		
	}
	public VsFunctions() {
		this.script="";
	}
	public void clear(){
		script="";
	}
	public void addFunction(String methodName,String methodParams,String methodCode) {
		this.script+="<script type=\"text/javascript\">";
		this.script+="function "+methodName+"("+methodParams+"){";
		this.script+=methodCode;
		this.script+="}";
		this.script+="</script>";
	}
	public String toHtml(){
		return(this.script);
	}
}
	


