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
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Locale;

import java.util.ResourceBundle;
import java.util.UUID;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import javax.imageio.ImageIO;
import javax.imageio.stream.MemoryCacheImageOutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import visualk.Main;

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
    public static final String SERVLET_URL = "/visualk/hrz/Hrz";

    private final Hashtable<String, Horizon> hrzns = new Hashtable<String, Horizon>();

    private static ResourceBundle bundle;

    private static String HORIZON_SESSION_PRIVATE_KEY = new UniqueName(3).getName();
    public static String HORIZON_SESSION_PUBLIC_KEY = new UniqueName(3).getName();
    public static String sessionId;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Hrz() {
        super();
    }

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
        response.setContentType("image/gif");

        final Horizon hrzFirma = new Horizon("hrz-signature-" + new UniqueName(5).getName(), 150, 93);
        /*
         */
        hrzFirma.setNameHrz(name);
        hrzFirma.setVersion("signature " + Main.VISUALK_VERSION);
        hrzFirma.makeRandom(150, 93);

        ImageIO.write(hrzFirma.getHrzImage(), "gif", response.getOutputStream());
    }

    //For the list to load. Small image
    public void peque(String name, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg");
        hrzns.get(sessionId).carrega(name);
        ImageIO.write(hrzns.get(sessionId).getHrzSmallImage(200, 200), "jpeg", new MemoryCacheImageOutputStream(response.getOutputStream()));
    }

    //carrega un dibuix existent
    public void loadAtzar(String name, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("image/gif");
        final Horizon hrzLoad = new Horizon("Horizon to load. " + new UniqueName(5).getName());

        hrzLoad.carrega(name);

        ImageIO.write(hrzLoad.getHrzImage(), "gif", response.getOutputStream());
    }

    private String getCookie(HttpServletRequest request, String key) {
        String cookie = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int n = 0; n < cookies.length; n++) {
                if (cookies[n].getName().equals(key)) {
                    cookie = cookies[n].getValue();
                }
            }
        }
        return cookie;
    }

    private void saveCookie(HttpServletResponse response, String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(24 * 60 * 60);
        cookie.setPath("/visualk/hrz/Hrz");
        response.addCookie(cookie);

    }

    //retorna dibuix
    public void getAtzar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("image/gif");

        HttpSession session = request.getSession(true);
        try {
            sessionId = (String) session.getAttribute("sessionId");;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (sessionId == null) {
            sessionId = new UniqueName(5).getName();
        }
        if (!hrzns.containsKey(sessionId)) {

            final Horizon hrzPaint = new Horizon("Horizon-to-paint_" + new UniqueName(5).getName());
            hrzPaint.setNameHrz(sessionId);
            hrzns.put(sessionId, hrzPaint);
            hrzns.get(sessionId).makeRandom(300, 300);
            hrzns.get(sessionId).setAuthorHrz("Hrz/getAtzar(" + sessionId + "). NotFound!");
        }
        ImageIO.write(hrzns.get(sessionId).getHrzImage(), "gif", response.getOutputStream());

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
            response.setContentType("text/html");
            response.sendRedirect("/visualk/hrz");
        } else if (option.equals("paint")) {// a mida real
            if (namehrz == null) {
                getAtzar(request, response);

            } else {
                loadAtzar(namehrz, request, response);
            }

        } else if (option.equals("peque")) {// mida small
            peque(namehrz, request, response);
        } else if (option.equals("firma")) {// firma petita
            firma(UUID.randomUUID().toString(), response);
        } else {
            response.setContentType("text/html");
            response.sendRedirect("/visualk/hrz");
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

        String mx = "150";
        String my = "150";

        mx = request.getParameter("mx");
        my = request.getParameter("my");

        lan = request.getLocale();

        if ((mx == null) || (mx.equals(""))) {
            mx = "150";
        }
        if ((my == null) || (my.equals(""))) {
            my = "150";
        }
        if (lan == null) {
            lan = Locale.getDefault();//DEFAULT_LANGUAGE
        }

        ResourceBundle.clearCache();
        try {
            System.out.println("language:" + lan);
            bundle = ResourceBundle.getBundle("outputTextConstants", lan);
        } catch (Exception e) {
            System.out.println("language:" + e.getMessage());
            e.printStackTrace();
        }

        final Artzar artzar = new Artzar(getString("title.artzar.hrzmkr"));

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

        HttpSession session = request.getSession(true);
        sessionId = (String) session.getAttribute("sessionId");;

        if (sessionId == null) {
            sessionId = "refactorizame";
        }

        if (!hrzns.containsKey(sessionId)) {

            sessionId = new UniqueName(5).getName();
            session.setAttribute("sessionId", sessionId);

            Horizon hrz = new Horizon(sessionId);
            hrzns.put(sessionId, hrz);
            hrzns.get(sessionId).makeRandom(Integer.parseInt(mx), Integer.parseInt(my));
            hrzns.get(sessionId).setAuthorHrz("WelCome to hrzmkr");
        }

        if (!what.equals("marxar")) {

            ////////// control atzar
            if (where.equals("artzar")) {

                switch (what) {
                    case "carrega":
                        //no hace nada carga la de por defecto
                        break;

                    case "gen_atzar":
                        sessionId = new UniqueName(5).getName();
                        session.setAttribute("sessionId", sessionId);

                        hrzns.put(sessionId, new Horizon(sessionId));
                        hrzns.get(sessionId).makeRandom(Integer.parseInt(mx), Integer.parseInt(my));//random de tot

                        break;

                    case "colorsRnd":
                        hrzns.get(sessionId).makeRandomColors();
                        break;
                    case "posicioRnd":
                        hrzns.get(sessionId).makeRandomPal();
                        break;
                    case "hombraRnd":
                        hrzns.get(sessionId).makeRandomHombra();
                        break;
                    case "superRnd":
                        hrzns.get(sessionId).makeRandomSuperNova();
                        break;
                    case "guarda":
                        hrzns.get(sessionId).saveToFile(option);
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
                    final ListHorizons listH = new ListHorizons(getString("title.gallery.hrzmkr"));
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

    void openNewSession() {
        sessionId = "test-" + new UniqueName(8).getName();
    }

}
