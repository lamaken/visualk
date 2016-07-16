/**
 * 
 */
package visualk.ss.modules.generator;

import java.util.LinkedList;

import visualk.html.DivHtml;


/**
 * @author alex
 *
 */
public class MapBar {

	private LinkedList<Frame> frames;

	private String id;
	
	public MapBar(String id){
		frames= new LinkedList<Frame>();
		
		this.id=id;
	}
	public void clear(){
		frames.clear();
	}
	
	public void addFrame(int index,Frame newFrame){
		this.frames.add(index,newFrame);
		
	}
	public void addFrame(Frame newFrame){
		this.frames.add(newFrame);
		
	}
	public void delFrame(int index){
		this.frames.remove(index);
	}
	public int getNumFrames(){
		return(this.frames.size());
	}	
	
	public String toHtml(){
		String ret="";
		int n;
		DivHtml div = new DivHtml(this.id);
			
		for(n=0;n<frames.size();n++){
				ret+=frames.get(n).toHtml();
		}
		return(div.toHtml(ret));
			
	}
	
}
