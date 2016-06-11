package visualk.hrz;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import visualk.db.MysqlLayer;
import visualk.hrz.objects.Horizon;
import visualk.html.UniqueName;

public class Api  extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Horizon hrz= new Horizon("wellcome");
	
	private MysqlLayer mySQL = new MysqlLayer();

	private void prepareDB(String dbServer, String dbUser, String dbPassword,
			String dbDataBase) {
		this.mySQL.setDBValues(dbServer, dbUser, dbPassword, dbDataBase);
	}

	private ResultSet listHrzns() {
		ResultSet myResult;
		myResult = mySQL
				.queryDB("SELECT * FROM hrzns WHERE namehrz<>'wellcome' order by dt desc;");
		return (myResult);

	}

	public void initdb() {
		//prepareDB("localhost", "user", "pass", "db");
	}

	private String getRndName()  {

		ResultSet myResult = listHrzns();
		Random r = new Random();
		LinkedList<String> list=new LinkedList<String>();
		
		
		
		
		
		
		try {
			while(myResult.next()){		
				list.add(myResult.getString("nameHrz"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String namehrz=list.get(r.nextInt(list.size()));
		
		hrz.carrega(namehrz);
		return namehrz;

		

	}
	 //signature for emails
    private void firma(String name,HttpServletResponse response) throws IOException{    	
    	
    	response.setContentType("image/JPEG");
    	Horizon hrz2 = new Horizon(new UniqueName(8).getName());
    	hrz2.setAuthorHrz(name);
    	hrz2.setHorizontal();
    	hrz2.setAureaProp(false);
    	hrz2.makeRandomCanvas(150,150,150,150);
    	hrz2.makeRandomSuperNova();
    	hrz2.makeRandomAlçadaHoritzo();
    	hrz2.makeRandomPal();
    	hrz2.makeRandomHombra();
    	hrz2.makeRandomColors();
    	
    	ImageIO.write(hrz2.getHrzImage(),"gif",response.getOutputStream());
}
 
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String option = request.getParameter("option");
		String name=getRndName();
		
		if(option.equals("getrnd")){
			response.setContentType("image/JPEG"); 
	        ImageIO.write(hrz.getHrzImage(),"gif",response.getOutputStream()); 
		}else{
			firma(name,response);
		}
	
	}
}
