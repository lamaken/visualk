package visualk.html;

public class MessageBox {
	private String msg;
	
	public MessageBox(String msg) {
		this.msg=msg;
	}
	public void setMessage(String msg){
		this.msg=msg;
	}
	public String getScript(){
		if(this.msg.equals(""))return("");
		else{
		return("messageBox(\""+this.msg+"\")");
		}
	}
	public String getHtml(){
		if(this.msg.equals(""))return("");
		else{
			return(new DivHtml("messageBox").toHtml(this.msg))+";";
		}
	}
}
