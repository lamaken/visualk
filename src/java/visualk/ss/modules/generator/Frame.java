package visualk.ss.modules.generator;

import ss.objects.DivHtml;
import ss.objects.LinkHtml;

public class Frame {

	private String id;
	private int input;
	private int output;
	private LinkHtml link;

	public Frame(String id,LinkHtml link, int input,int output){
		this.link = link;
		this.input = input;
		this.output = output;
		this.id=id;
	}
	
	public String toHtml(){
		DivHtml div = new DivHtml(this.id);
		
		div.setDiv_class("frame");
		
		String ret="<table border=\"1\"><tr><td><table>";
		 ret+="<tbody>";
		 ret+="<tr>";
		 ret+="<td colspan=\"3\" rowspan=\"1\">"+link.toHtml()+"</td>";
		 ret+="</tr>";
		 ret+="<tr>";
		 ret+="<td>"+input+"</td>";
		 ret+="<td></td>";
		 ret+="<td>"+output+"</td>";
		 ret+="</tr>";
		 ret+="<tr>";
		 ret+="<td colspan=\"3\">x</td>";
		 ret+="</tr>";
		 ret+="</tbody>";
		 ret+="</table></td></tr></table>";
		
	 return(div.toHtml(ret));
	}
}
 