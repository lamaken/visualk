package visualk.hrz;

/*
 * 
 * generar dinamicament els css a partir de les propietats java
 * fer la finestra de dialeg
 * publica
 * fer Debug xivato 
 */
import java.io.IOException;

import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.Locale;

import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import visualk.Main;

import visualk.hrz.modules.Artzar;
import visualk.hrz.modules.ListHorizons;

import visualk.hrz.objects.Horizon;
import visualk.html.UniqueName;

/**
 * Servlet implementation class Hrz
 */
@WebServlet("/Hrz")
public class Hrz extends HttpServlet {

    private static final long serialVersionUID = 102431973219L;
    public static final String SERVELT_URL = "/visualk/hrz/Hrz";
    public static final String URL_PATH = Main.SERVER_URL + "/visualk/hrz";

  

    private Hashtable hrzns = new Hashtable();
    private static ResourceBundle bundle;
    
    private Horizon hrz;

    //Session sessioN;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Hrz() {
        super();
    }

    private static final String INICIAL_HORIZON_NAME_SESSION = "Inicial";

    public static String getString(String key) {
        
        return bundle.getString(key);
    }

    //signature for emails
    public void firma(String name, HttpServletResponse response) throws IOException {

        response.setContentType("image/JPEG");
        Horizon hrz2 = new Horizon(new UniqueName(8).getName());
        hrz2.setAuthorHrz(name);
        hrz2.setHorizontal();
        hrz2.setAureaProp(false);
        hrz2.makeRandomCanvas(150, 100, 100, 70);
        hrz2.makeRandomAlçadaHoritzo();
        hrz2.makeRandomPal();
        hrz2.makeRandomHombra();
        hrz2.makeRandomColors();
        hrz2.makeRandomSuperNova();

        ImageIO.write(hrz2.getHrzImage(), "gif", response.getOutputStream());
    }

    //For the list to load. Small image
    public void peque(String name, HttpServletResponse response) throws IOException {
        response.setContentType("image/JPEG");
        hrz.carrega(name);
        ImageIO.write(hrz.getHrzSmallImage(200, 200), "jpeg", response.getOutputStream());
    }

    //carrega un dibuix existent
    public void loadAtzar(String name, HttpServletResponse response) throws IOException {
        response.setContentType("image/JPEG");
        Horizon hrz2 = new Horizon(new UniqueName(8).getName());
        hrz2.carrega(name);
        ImageIO.write(hrz2.getHrzSmallImage(300,300), "jpeg", response.getOutputStream());
    }

    //retorna dibuix
    public void getAtzar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("image/JPEG");
        HttpSession session = request.getSession(true);
        String sessionId = "no_session";
        try {
            sessionId = session.getId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (hrzns.containsKey(sessionId)) {
            hrz = (Horizon) hrzns.get(sessionId);
        } else {
            hrz = (Horizon) hrzns.get(INICIAL_HORIZON_NAME_SESSION);
        }
        ImageIO.write(hrz.getHrzImage(), "jpeg", response.getOutputStream());
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

        String option = request.getParameter("option");
        String namehrz = request.getParameter("namehrz");

        if (option == null) {
            option = "";
        }

        if (option.equals("paint")) {// a mida real
            if (namehrz == null) {
                getAtzar(request, response);

            } else {
                loadAtzar(namehrz, response);
            }

        }
        if (option.equals("peque")) {// mida small
            peque(namehrz, response);
        }
        if (option.equals("firma")) {// firma petita
            firma("by alk@soft.org", response);
        }

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

        Artzar artzar=null; 		// artzar horitzons a l'atzar
        ListHorizons listH=null;  // galeria d'horitzons
        // Wizard wizard; 	 	// asistent per la ceracio
    
    
        Locale lan;
        String where = request.getParameter("where");
        String what = request.getParameter("what");
        String pino = request.getParameter("pino");

        String option = request.getParameter("option");
        String nom = request.getParameter("nom");

        String mx = "";
        String my = "";

        mx = request.getParameter("mx");
        my = request.getParameter("my");

        lan = request.getLocale();

        if (mx == null) {
            mx = "250";
        }
        if (my == null) {
            my = "250";
        }
        if (lan == null) {
            lan = Locale.getDefault();//DEFAULT_LANGUAGE
        }

        

        HttpSession session = request.getSession(true);
        String sessionId = "no_session";
        try {
            sessionId = session.getId();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ResourceBundle.clearCache();
        bundle = ResourceBundle.getBundle("outputTextConstants", lan);
        
        if (hrzns.containsKey(sessionId)) {
            hrz = (Horizon) hrzns.get(sessionId);
        } else {
            hrz = new Horizon(new UniqueName(8).getName());
            hrz.setNameHrz(new UniqueName(8).getName());
            hrz.makeRandom(Integer.parseInt(mx), Integer.parseInt(my));//random de tot
            hrzns.put(sessionId, hrz);// = (Horizon) hrzns.get(INICIAL_HORIZON_NAME_SESSION);
        }

       

        if (pino == null) {
            pino = "0";
        }

        if (where == null) {
            where = "";
        }
        if (what == null) {
            what = "";
        }

        if (option == null) {
            option = "";
        }
        if (nom == null) {
            nom = "";
        }

        PrintWriter out;
        out = response.getWriter();
        response.setContentType("text/html");

        //option start	
        System.out.println("where:" + where);
        System.out.println("what:" + what);
        System.out.println("opt:" + option);
        System.out.println("nom:" + nom);

        if (!what.equals("marxar")) {

            ////////// control atzar
            if (where.equals("artzar")) {
                //if (artzar == null) {
                artzar = new Artzar(getString("title.artzar.hrzmkr"));
               // }
                if (what.equals("carrega")) { //entra a artzar
                } else if (what.equals("gen_atzar")) {
                    hrz = new Horizon(new UniqueName(8).getName());
                    hrz.setNameHrz(new UniqueName(8).getName());
                    hrz.makeRandom(Integer.parseInt(mx), Integer.parseInt(my));//random de tot

                    hrzns.put(sessionId, hrz);

                } else if (what.equals("colorsRnd")) {
                    hrz.makeRandomColors(); //random de colors
                } else if (what.equals("posicioRnd")) {
                    hrz.makeRandomPal(); // random del pal
                } else if (what.equals("hombraRnd")) {
                    hrz.makeRandomHombra(); // random del pal
                } else if (what.equals("superRnd")) {
                    hrz.makeRandomSuperNova(); // random del pal
                } else if (what.equals("guarda")) {
                    hrz.saveToFile(option); // random del pal
                }

                if (pino.equals("0")) {
                    out.println(artzar.toHtml());// + "<script>alert(\"" + lan.getDisplayLanguage()+ "\")</script>");
                } else {
                    out.println("");
                }

                out.close();
            }
            //////// control llista per carregar

            if (where.equals("listhorizons")) {
                listH = new ListHorizons(getString("title.gallery.hrzmkr"));
                if (what.equals("carrega")) {
                    out.println(listH.toHtml());
                    out.close();
                } else if (what.equals("selecciona")) {
                    hrz.carrega(nom);
                    artzar = new Artzar(getString("title.artzar.hrzmkr"));
                    out.println(artzar.toHtml());
                    out.close();
                }

            } ///////////// si res de res	
        } else {
            response.sendRedirect("/visualk/");
        }
       
    }

}
