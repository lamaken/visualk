/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package visualk.lmk.res.obj;

import java.awt.Color;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.net.URL;

/**
 *
 * @author alex
 */  
public class Button extends Obj{ 
    
    
    private  static final int PUSHED = 29;
    
    private Color btTextColor=Color.BLACK;
    
    public Button(String caption){
        this.caption=caption;
        LoadBMPButton(); 
    }

    public void setLabel(String c){
    	this.caption = c;
    }
    private void LoadBMPButton() {
        System.out.print("Loading window button");
        URL url;
        url = this.getClass().getResource("../img/button2.png");
        this.bmpButton = Toolkit.getDefaultToolkit().getImage(url);
        if (this.bmpButton == null) {
            System.out.println("... error");
        } else {
            System.out.println("... ok");
        }
    }
    public int clic(MouseEvent e){
        if(this.siDins(e.getX(),e.getY())){
        	this.offsetImage=PUSHED;
        return(1);
        }else{ this.offsetImage=0;return(0);}
    }
    public int mouseOver(MouseEvent e){
    	this.offsetImage=0;
        
        if(this.siDins(e.getX(),e.getY())){
            
            btTextColor=Color.YELLOW;
            return(Cursor.HAND_CURSOR);}
        else{
            
            btTextColor=Color.BLACK;
            return(-1);
        }
    
    }
    
    
    public void putinscreen(Graphics g,int x,int y){
    
        int n=0;
        this.bx=x;
        this.by=y; 
        this.bmx=10*(this.caption.length()+2);
        this.bmy=21;
        
        
        g.clipRect(x, y, this.bmx,this.bmy);
        
        
        
        BufferedImage mybuffer = new BufferedImage(58,21, BufferedImage.TYPE_INT_ARGB);
         Graphics g2 = mybuffer.createGraphics();
         g2.drawImage(this.bmpButton, 0, 0 ,null);
         g2.dispose();
           
          
         BufferedImage c1 = mybuffer.getSubimage(offsetImage, 0, 10,21);
         BufferedImage c2 = mybuffer.getSubimage(offsetImage+11, 0, 10,21);
         BufferedImage c3 = mybuffer.getSubimage(offsetImage+18, 0,10,21);
         
         
        
        g.drawImage(c1,x,y,null);
        for (n=1;n<this.caption.length()+1;n++){ 
        g.drawImage(c2,x+(n*10),y,null);}
        
        g.drawImage(c3,x+(n)*10,y,null);
        
        g.setColor(btTextColor);
        if(offsetImage==0)g.drawString(caption, x+14, y+14);
        else g.drawString(caption, x+15, y+15);
    
        g.setClip(null);
       
    }
    public void putinscreenMX(Graphics g,int x,int y,int mx,int push){
    
        int n=0;
        this.bx=x; 
        this.by=y; 
        this.bmx=mx;
        this.bmy=20;
        
        
        
        
        BufferedImage mybuffer = new BufferedImage(58,21, BufferedImage.TYPE_INT_ARGB);
         Graphics g2 = mybuffer.createGraphics();  
         g2.drawImage(this.bmpButton, 0, 0,null);
         g2.dispose();
         
         if(push==0)offsetImage=30;
         else offsetImage=0;
         
         BufferedImage c1 = mybuffer.getSubimage(offsetImage, 0, 10,21);
         BufferedImage c2 = mybuffer.getSubimage(offsetImage+11, 0, 10,21);
         BufferedImage c3 = mybuffer.getSubimage(offsetImage+18, 0,10,21);
         
         
        
        g.drawImage(c1,x,y,null);
        for (n=1;n<mx/10;n++){
        g.drawImage(c2,x+(n*10),y,null);}
        
        g.drawImage(c3,x+(n)*10,y,null);
        
        g.clipRect(x, y, (n+1)*10,21);
        
        g.setColor(btTextColor);
        if(offsetImage==0)g.drawString(caption, x+15, y+15);
        else g.drawString(caption, x+16, y+16);
    

        g.setClip(null);
        
    }
}
