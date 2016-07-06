package visualk.html;

import visualk.hrz.Hrz;




public class InputDialog {

	private ClassCSS css;
	private String question		= Hrz.getString("label.savedialog.artzar.hrzmkr");
	private String defaultValue = "anonymous";
	
	private String inputName	= "inputAnswer";
	private String formName		= "formName";
		
	public InputDialog(ClassCSS inputCSS){
		this.css = inputCSS;
	}
	public String toHtml(){
		String html = "<form onsubmit=\"return(false);\" name=\""
				+ this.formName
				+ "\">"
				+ "<table cellspacing=1 cellpadding=4 style=\"background:rgb(210,210,210);width:100%;height:100%;\" border=1><tr><td>"
				+ "<table cellspacing=1 cellpadding=4 style=\"background:rgb(230,230,230);width:100%;height:100%;\" border=0><tr><td>"+new LabelHtml(this.question).toHtml()
				+ "</tr></td><tr><td><input name=\""
				+ this.inputName
				+ "\" "
				+ " value=\""
				+ new LabelHtml(this.defaultValue).toHtml()
				+ "\""
				+ " type=\"text\"/><br>"
				+ "</tr></td><tr><td><input type=\"button\" value=\""+Hrz.getString("label.buttonAccept.savedialog.artzar.hrzmkr")+"\" onclick=\"doneDialog(document."
				+ this.formName
				+ "."
				+ this.inputName
				+ ".value)\"/>"
				+ "<input type=\"button\" value=\""+Hrz.getString("label.buttonCancel.savedialog.artzar.hrzmkr")+"\" onclick=\"closeDialog('"
				+ this.css.getId() + "')\"/>" +

				"</tr></td></table>" +"</tr></td></table>" + "</form>";

		return(new DivHtml(css.getId()).toHtml(html));
	}
}
