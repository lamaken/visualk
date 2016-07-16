/**
 * 
 */
package ss.objects;

import java.util.LinkedList;




/**
 * @author �lex
 * @version 1.a
 * 
 */
public class MenuBar{
	private static final int VERTICAL_ALIGN = 1;
	private static final int HORIZONTAL_ALIGN = 2;
	
	private String did="MenuBar";
	private String title = "";
	
	private int orientation=HORIZONTAL_ALIGN;
	
	private LinkedList<LinkHtml> optionsMenu;

	public void setVertical(){this.orientation=VERTICAL_ALIGN;}
	public void setHorizontal(){this.orientation=HORIZONTAL_ALIGN;}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public MenuBar(String id) {
		did=id;
		optionsMenu= new LinkedList<LinkHtml>();
	}
	public void addMenuLink(String id,String label, String link, String status){
		optionsMenu.add(new LinkHtml(id,label,link,status));
	}
	public void addMenuLink(String id, String label,String link,String onclick, String params,String status){
		optionsMenu.add(new LinkHtml(id,label,link,onclick,params,status));
	}
	public int eliminaOption(String id){
		for (int n=0;n<optionsMenu.size();n++)
		{	
			if(optionsMenu.get(n).getId()==id){
				optionsMenu.remove(n);
				return(0);
			}
		}
		return(-1);
	}
	public void clear(){
		optionsMenu.clear();
	}
	public String toHtml(){
		String htmlChar="";
		SpaceHtml space=new SpaceHtml();
		CrHtml enter = new CrHtml();
		
		if(this.orientation==HORIZONTAL_ALIGN)htmlChar=space.toHtml();
		if(this.orientation==VERTICAL_ALIGN)htmlChar=enter.toHtml()+enter.toHtml();
		
		
		String returnHtml="";
		
		if(!this.title.equals(""))returnHtml+=this.title+htmlChar+htmlChar;
		
		
		for (int n=0;n<optionsMenu.size();n++)
			returnHtml += optionsMenu.get(n).toHtml()+htmlChar;
		return(new DivHtml(this.did).toHtml(returnHtml));
	}

}
