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
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import visualk.hrz.modules.Artzar;
import visualk.hrz.modules.ListHorizons;
import visualk.hrz.objects.Horizon;
import visualk.html5.UniqueName;

/**
 * Servlet implementation class Hrz
 */
@WebServlet("/Hrz")
public class Hrz extends HttpServlet {

    private static final long serialVersionUID = 1024371973219L;

    private Hashtable<String, Horizon> hrzns = new Hashtable<String, Horizon>();

    private static ResourceBundle bundle;

    private final Horizon hrzFirma = new Horizon("idle.");
    
    private final Artzar artzar = new Artzar(getString("title.artzar.hrzmkr"));
    
    ListHorizons listH = new ListHorizons(getString("title.gallery.hrzmkr"));
    // Wizard wizard; 	 	// asistent per la ceracio
    
    
    private static final String FIRST_HRZ_NAME ="I'm the first!";

    //Session sessioN;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Hrz() {
        super();
        hrzns.put(INICIAL_HORIZON_NAME_SESSION, new Horizon(FIRST_HRZ_NAME));
    }

    private static final String INICIAL_HORIZON_NAME_SESSION = "Inicial";

    public static String getString(String key) {
        String result = "";
        if (bundle == null) {
            try {
                bundle = ResourceBundle.getBundle("outputTextConstants", Locale.US);
            } catch (Exception e) {
                e.printStackTrace();
                return e.getMessage();
            }
        }

        try {
            result = bundle.getString(key);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }

        return result;

    }

    //signature for emails
    public void firma(String name, HttpServletResponse response) throws IOException {

        response.setContentType("image/PNG");

        response.setHeader("Transfer-Encoding", "PNG");

        hrzFirma.setNameHrz(name);
        
        hrzFirma.makeRandom(150, 93);
        hrzFirma.setAuthorHrz("http://alkasoft.org");

        ImageIO.write(hrzFirma.getHrzImage(), "png", response.getOutputStream());
    }

    //For the list to load. Small image
    public void peque(String name, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("image/PNG");
        response.setHeader("Transfer-Encoding", "PNG");
        
        HttpSession session = request.getSession(true);
        String sessionId = INICIAL_HORIZON_NAME_SESSION;
        try {
            sessionId = session.getId();

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        hrzns.get(sessionId).carrega(name);
        ImageIO.write(hrzns.get(sessionId).getHrzSmallImage(200, 200), "png", response.getOutputStream());
    }

    //carrega un dibuix existent
    public void loadAtzar(String name, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("image/PNG");
        response.setHeader("Transfer-Encoding", "PNG");
        
        HttpSession session = request.getSession(true);
        String sessionId = INICIAL_HORIZON_NAME_SESSION;
        try {
            sessionId = session.getId();

        } catch (Exception e) {
            e.printStackTrace();
        }
        final Horizon hrzLoaded= new Horizon(name);
        hrzLoaded.carrega(name);
        
        hrzns.put(sessionId,hrzLoaded);
        ImageIO.write(hrzns.get(sessionId).getHrzImage(), "png", response.getOutputStream());
    }

    //retorna dibuix
    public void getAtzar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("image/PNG");
        response.setHeader("Transfer-Encoding", "PNG");
        HttpSession session = request.getSession(true);
        String sessionId = INICIAL_HORIZON_NAME_SESSION;
        try {
            sessionId = session.getId();
            System.out.println("session:"+sessionId);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
         if (!hrzns.containsKey(sessionId)) {
             System.out.println("new session:"+sessionId);
             hrzns.put(sessionId, new Horizon(FIRST_HRZ_NAME));
        }
        
        ImageIO.write(hrzns.get(sessionId).getHrzImage(), "png", response.getOutputStream());
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
            response.sendRedirect("/visualk/hrz");
        } else {
            if (option.equals("paint")) {// a mida real
                if (namehrz == null) {
                    getAtzar(request, response);

                } else {
                    loadAtzar(namehrz, request,response);
                }

            } else {
                if (option.equals("peque")) {// mida small
                    peque(namehrz, request,response);
                } else {
                    if (option.equals("firma")) {// firma petita
                        firma(UUID.randomUUID().toString(), response);
                    } else {
                        response.sendRedirect("/visualk/hrz");
                    }
                }
            }
        }

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

       

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
            mx = "150";
        }
        if (my == null) {
            my = "150";
        }
        if (lan == null) {
            lan = Locale.getDefault();//DEFAULT_LANGUAGE
        }

        String sessionId = INICIAL_HORIZON_NAME_SESSION;
        HttpSession session = request.getSession(true);
        try {
            sessionId = session.getId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!hrzns.containsKey(sessionId)) {
            hrzns.put(sessionId, new Horizon(FIRST_HRZ_NAME));
        }

        ResourceBundle.clearCache();
        try {
            System.out.println("language:" + lan);
            bundle = ResourceBundle.getBundle("outputTextConstants", lan);
        } catch (Exception e) {
            System.out.println("language:" + e.getMessage());
            e.printStackTrace();
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
                
                // }
                switch (what) {
                    case "carrega":
                      
                        hrzns.get(sessionId).makeRandom(Integer.parseInt(mx), Integer.parseInt(my));
                        
                    case "gen_atzar":
                        hrzns.get(sessionId).setNameHrz(new UniqueName(8).getName());
                        hrzns.get(sessionId).makeRandom(Integer.parseInt(mx), Integer.parseInt(my));//random de tot
                        break;
                    case "colorsRnd":
                        hrzns.get(sessionId).makeRandomColors(); //random de colors
                        break;
                    case "posicioRnd":
                        hrzns.get(sessionId).makeRandomPal(); // random del pal
                        break;
                    case "hombraRnd":                    
                        hrzns.get(sessionId).makeRandomHombra(); // random del pal
                        break;
                    case "superRnd":
                        hrzns.get(sessionId).makeRandomSuperNova(); // random del pal
                        break;
                    case "guarda":
                        hrzns.get(sessionId).saveToFile(option); // random del pal
                        
                        break;
                    default:
                        break;
                }

                if (pino.equals("0")) {
                    out.println(artzar.toHtml());// + "<script>alert(\"" + lan.getDisplayLanguage()+ "\")</script>");
                } else {
                    out.println("Hola");
                }

                out.close();
            }
            //////// control llista per carregar

            if (where.equals("listhorizons")) {
                
                if (what.equals("carrega")) {
                    listH.setSize(Integer.parseInt(mx), Integer.parseInt(my));
                    out.println(listH.toHtml());
                    out.close();
                } else if (what.equals("selecciona")) {
                    hrzns.get(sessionId).carrega(nom);
                    out.println(artzar.toHtml());
                    out.close();
                }

            } ///////////// si res de res	
        } else {
            response.sendRedirect("/visualk/");
        }

    }

}
