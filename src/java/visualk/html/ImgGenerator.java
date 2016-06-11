package visualk.html;

/*
 * 
 * generar dinamicament els css a partir de les propietats java
 * fer la finestra de dialeg
 * publica
 * fer Debug xivato 
 */
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;

import java.net.URL;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class Hrz
 */
public class ImgGenerator extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	private int mx	= 0;
	private int my	= 0;
	private String title = "no title";
	
	private Image bmpWindow;
	
	
	
	
	public ImgGenerator() {
        super();
        
    }

	private void loadBMPWindow() {
	        System.out.print("Loading window bmp");
	        URL url;
	        url = this.getClass().getResource("/img/Win.png");
	        
	        bmpWindow = Toolkit.getDefaultToolkit().getImage(url);
	        if (bmpWindow == null) {
	            System.out.println("... error");
	        } else {
	            System.out.println("... ok");
	        }
	    }

	//gen Win
	public BufferedImage genWin(){
		
		this.mx = this.mx * 8;
    	this.my = this.my * 23;
    	
    	loadBMPWindow();
    	
    	BufferedImage buf = new BufferedImage(this.mx,this.my,1); 
		Graphics2D gk = buf.createGraphics();
                
        
		gk.setColor(Color.red);
		gk.drawRect(0,0,mx,my);
		
        
        
        
		System.out.println("title:"+this.title+"mx:"+this.mx+" mY:"+this.my);
		
		gk.setColor(Color.white);
		gk.drawString(this.title+" w:"+ this.mx+" h:"+ this.my,1,15);
        return(buf);         
	}
    
    //retorna dibuix
    public void retImgWin(HttpServletResponse response) throws IOException{
    	response.setContentType("image/JPEG"); 
        ImageIO.write(genWin(),"gif",response.getOutputStream()); 
    }
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String option = request.getParameter("option");
		String tit = request.getParameter("title");
		
		String mx = request.getParameter("mx");
		String my = request.getParameter("my");
		
		if (option == null)	option = "";
		if ((tit == null)||(tit.equals(""))) tit = "no title";
		
		if ((mx == null)||(mx.equals("")))mx="0";
		if ((my == null)||(my.equals("")))my="0";
		
		this.mx = Integer.parseInt(mx);
		this.my = Integer.parseInt(my);
		this.title = tit;
	
		if (option.equals("newWin")) {
			retImgWin(response);
		}
	}
}
