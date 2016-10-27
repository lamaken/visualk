package visualk.gallery;

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

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import visualk.Main;
import visualk.gallery.modules.Detail;
import visualk.gallery.modules.ListAuthors;

import visualk.gallery.objects.User;

/**
 * Servlet implementation class Hrz
 */
@WebServlet("/Gallery")
public class Gallery extends HttpServlet {

    private static final long serialVersionUID = 1024371973219L;
    public static final String SERVLET_URL = "/visualk/gallery/Gallery";
    public static final String URL_PATH = Main.HOST_NAME + Main.HOST_VISUALK + "/gallery";

    private static final String PARAM_MY = "my";
    private static final String PARAM_MX = "mx";
    private static final String PARAM_NAME = "nom";
    private static final String PARAM_OPTION = "option";
    private static final String PARAM_PINO = "pino";
    private static final String PARAM_WHAT = "what";
    private static final String PARAM_WHERE = "where";

    private Hashtable<String, User> users = new Hashtable<String, User>();

    private static ResourceBundle bundle;

    private User user;

    //Session sessioN;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Gallery() {
        super();
    }

    private static final String INICIAL_HORIZON_NAME_SESSION = "Inicial";

    public static String getString(String key) {
        if (bundle == null) {
            bundle = ResourceBundle.getBundle("outputTextConstants", Locale.ENGLISH);
        }
        return bundle.getString(key);
    }

    //carrega un dibuix existent
    public void loadAtzar(String name, HttpServletResponse response) throws IOException {
        response.setContentType("image/PNG");
        //Author hrz2 = new Author(new UniqueName(8).getName());
        //hrz2.carrega(name);
        //ImageIO.write(hrz2.getHrzImage()/*.getHrzSmallImage(300,300)*/, "png", response.getOutputStream());
    }

    //retorna dibuix
    public void getAtzar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("image/PNG");
        HttpSession session = request.getSession(true);
        String sessionId = "no_session";
        try {
            sessionId = session.getId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (users.containsKey(sessionId)) {
            user = (User) users.get(sessionId);
        } else {
            user = (User) users.get(INICIAL_HORIZON_NAME_SESSION);
        }
        //ImageIO.write(hrz.getHrzImage(), "png", response.getOutputStream());
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

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

        Detail artzar = null; 		// artzar horitzons a l'atzar
        ListAuthors listH = null;  // galeria d'horitzons
        // Wizard wizard; 	 	// asistent per la ceracio

        Locale lan;
        String where = request.getParameter(PARAM_WHERE);
        String what = request.getParameter(PARAM_WHAT);
        String pino = request.getParameter(PARAM_PINO);

        String option = request.getParameter(PARAM_OPTION);
        String nom = request.getParameter(PARAM_NAME);

        String mx = "";
        String my = "";

        mx = request.getParameter(PARAM_MX);
        my = request.getParameter(PARAM_MY);

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

        if (users.containsKey(sessionId)) {
            user = (User) users.get(sessionId);
        } else {
            //hrz = new Author(new UniqueName(8).getName());
            //hrz.setNameHrz(new UniqueName(8).getName());
            //hrz.makeRandom(Integer.parseInt(mx), Integer.parseInt(my));//random de tot
            //hrzns.put(sessionId, (Author) hrz);
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

        if (!what.equals(WHAT_MARXAR)) {

            ////////// control atzar
            if (where.equals(WHERE_HOME)) {
                //if (artzar == null) {
                artzar = new Detail(getString("title.artzar.hrzmkr"));
                // }
                if (what.equals(WHAT_CARREGAR)) { //entra a artzar
                } else if (what.equals("gen_atzar")) {
                    //hrz = new Author(new UniqueName(8).getName());
                    //hrz.setNameHrz(new UniqueName(8).getName());
                    //hrz.makeRandom(Integer.parseInt(mx), Integer.parseInt(my));//random de tot

                    users.put(sessionId, user);

                } else if (what.equals("colorsRnd")) {
                    //hrz.makeRandomColors(); //random de colors
                } else if (what.equals("posicioRnd")) {
                    //hrz.makeRandomPal(); // random del pal
                } else if (what.equals("hombraRnd")) {
                    //hrz.makeRandomHombra(); // random del pal
                } else if (what.equals("superRnd")) {
                    //hrz.makeRandomSuperNova(); // random del pal
                } else if (what.equals("guarda")) {
                    //hrz.saveToFile(option); // random del pal
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
                listH = new ListAuthors(getString("title.gallery.hrzmkr"));
                if (what.equals("carrega")) {
                    out.println(listH.toHtml());
                    out.close();
                } else if (what.equals("selecciona")) {
                    //hrz.carrega(nom);
                    artzar = new Detail(getString("title.artzar.hrzmkr"));
                    out.println(artzar.toHtml());
                    out.close();
                }

            } ///////////// si res de res	
        } else {
            response.sendRedirect("/visualk/");
        }

    }
    private static final String WHAT_CARREGAR = "carrega";
    private static final String WHAT_MARXAR = "marxar";

    private static final String WHERE_HOME = "home";
    private static final String WHERE_LIST_ARTISTS = "artists";
    private static final String WHERE_DETAIL_OBRA = "home";

}
