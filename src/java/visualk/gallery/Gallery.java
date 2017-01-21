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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import visualk.Main;
import visualk.gallery.modules.Detail;
import visualk.gallery.objects.User;

/**
 * Servlet implementation class
 */
@WebServlet("/Gallery")
public class Gallery extends HttpServlet {

    private static final long serialVersionUID = 1024371973219L;
    public static final String SERVLET_URL = "/visualk/gallery/Gallery";
    public static final String URL_PATH = Main.HOST_NAME + Main.HOST_VISUALK + "/gallery";

   
    Integer idWork=0; //ara es comu a tothom hauria destar 

    //Session sessioN;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Gallery() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.sendRedirect("/visualk/gallery");
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out;
        out = response.getWriter();
        response.setContentType("text/html");
        try {
            String where = request.getParameter("where");
            String what = request.getParameter("what");

            if (!what.equals("marxar")) {

                if (where.equals("detail")) {
                    
                    String option = request.getParameter("option");
                    
                    if(option!=null)
                    idWork = Integer.parseInt(option);
                    
                    
                    
                    
                    if(what.equals("anterior"))idWork--;
                    if(what.equals("seguent"))idWork++;
                            
                    if(idWork<0)idWork=0;
                    if(idWork>4)idWork=4;
                    
                    
                    String parameters;
                    parameters="idWork="+idWork;

                    Detail detail = new Detail();
                    
                    out.println(detail.toHtml());
                } else {
                    response.sendRedirect("/visualk/gallery");
                }
            } else {
                response.sendRedirect("/visualk");
            }

        } finally {
            out.close();  // Always close the output writer
        }
    }

}
