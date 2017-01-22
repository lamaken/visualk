package visualk.hrz.db;

import java.awt.Color;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import visualk.db.MysqlLayer;
import visualk.hrz.objects.Horizon;

public class DbHorizons extends MysqlLayer {
    
    private static final String dbUser = "hrzmkr_user";
    private static final String dbPassword = "hrzmkr_password";
    private static final String dbDb = "hrzmkr_db";
    
    
    public DbHorizons() {
        super(dbUser, dbPassword, dbDb);
    }
    

    private DbHorizons(String user, String pass, String db) {
        super(user, pass, db);
    }

    public void addHorizon(Horizon hrz, String authorName) {
        if (this != null) {
            try {
                this.executeDB("insert into hrzns (" + "nameHrz," + "dt," + "topHrz,"
                        + "topHrzColor," + "bottomHrzColor," + "canvasWidth,"
                        + "canvasHeigth," + "authorHrz," + "xPal," + "yPal," + "hPalx,"
                        + "hPaly," + "alcada," + "colPal," + "horizontal,"
                        + "aureaProp," + "superX," + "superY," + "textura," + "version) values ('"
                        + hrz.getNameHrz()
                        + "', "
                        + "NOW()"
                        + ", '"
                        + hrz.getTopHrz()
                        + "', '"
                        + hrz.getTopHrzColor().getRGB()
                        + "', '"
                        + hrz.getBottomHrzColor().getRGB()
                        + "', '"
                        + hrz.getCanvasWidth()
                        + "', '"
                        + hrz.getCanvasHeigth()
                        + "', '"
                        + authorName
                        + "', '"
                        + hrz.getxPal()
                        + "', '"
                        + hrz.getyPal()
                        + "', '"
                        + hrz.gethPalx()
                        + "', '"
                        + hrz.gethPaly()
                        + "', '"
                        + hrz.getAlçada()
                        + "', '"
                        + hrz.getColPal().getRGB()
                        + "', '"
                        + hrz.isHorizontal()
                        + "', '"
                        + hrz.isAureaProp()
                        + "', '"
                        + hrz.getSuperX()
                        + "', '"
                        + hrz.getSuperY()
                        + "', '"
                        + hrz.isTextura()
                        + "', '"
                        + hrz.getVersion() + "')");
            } catch (Exception e) {
            } finally {
                this.disconnect();
            }
        }
    }

    public ResultSet listHrzns() {
        ResultSet myResult=null;
        try {
            myResult = this.queryDB("SELECT * FROM hrzns WHERE namehrz<>'wellcome' order by dt desc;");
        } catch (SQLException ex) {
            Logger.getLogger(DbHorizons.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DbHorizons.class.getName()).log(Level.SEVERE, null, ex);
        }

        return (myResult);

    }

    public ResultSet listHrzns(Integer offset, Integer limit, Integer width, Integer height) {
        ResultSet myResult=null;
        String onlySize = "(canvasWidth<=" + width + " and  canvasHeigth<=" + height + ")";
        try {
            //myResult = this.queryDB("SELECT * FROM hrzns WHERE namehrz<>'wellcome' and " + onlySize + " order by dt desc limit " + offset + "," + limit + " ;");
            myResult = this.queryDB("SELECT * FROM hrzns order by dt desc;");
        } catch (SQLException ex) {
            Logger.getLogger(DbHorizons.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DbHorizons.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.print(onlySize);
        return (myResult);

    }

    public Horizon getHrznBD(String name) throws ClassNotFoundException, SQLException {

        Horizon temp = new Horizon(name);
        ResultSet myResult=null;
       
        myResult = this.queryDB("SELECT * FROM hrzns where nameHrz='" + name + "'");
     
        
        if (myResult != null) {

            try {
                while (myResult.next()) {
                    String nameHrz = "";
                    String topHrz = "";
                    String topHrzColor = "";
                    String bottomHrzColor = "";
                    String canvasWidth = "";
                    String canvasHeigth = "";
                    String authorHrz = "";
                    String xPal = "";
                    String yPal = "";
                    String hPalx = "";
                    String hPaly = "";
                    String alcada = "";
                    String horizontal = "";
                    String aureaProp = "";
                    String colPal = "";
                    String superX = "";
                    String superY = "";
                    String textura = "";

                    String version = "";
                    try {
                        nameHrz = myResult.getString("nameHrz");
                        topHrz = myResult.getString("topHrz");
                        topHrzColor = myResult.getString("topHrzColor");
                        bottomHrzColor = myResult.getString("bottomHrzColor");
                        canvasWidth = myResult.getString("canvasWidth");
                        canvasHeigth = myResult.getString("canvasHeigth");
                        authorHrz = myResult.getString("authorHrz");
                        xPal = myResult.getString("xPal");
                        yPal = myResult.getString("yPal");
                        hPalx = myResult.getString("hPalx");
                        hPaly = myResult.getString("hPaly");
                        alcada = myResult.getString("alcada");
                        colPal = myResult.getString("colPal");
                        horizontal = myResult.getString("horizontal");
                        aureaProp = myResult.getString("aureaProp");

                        superX = myResult.getString("superX");
                        superY = myResult.getString("superY");
                        textura = myResult.getString("textura");

                        version = myResult.getString("version");
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    temp.setNameHrz(nameHrz);
                    temp.setTopHrz(Integer.parseInt(topHrz));
                    temp.setTopHrzColor(new Color(Integer.parseInt(topHrzColor)));
                    temp.setBottomHrzColor(new Color(Integer.parseInt(bottomHrzColor)));
                    temp.setCanvasWidth(Integer.parseInt(canvasWidth));
                    temp.setCanvasHeigth(Integer.parseInt(canvasHeigth));
                    temp.setAuthorHrz(authorHrz);
                    temp.setxPal(Integer.parseInt(xPal));
                    temp.setyPal(Integer.parseInt(yPal));
                    temp.sethPalx(Integer.parseInt(hPalx));
                    temp.sethPaly(Integer.parseInt(hPaly));
                    temp.setAlcada(Integer.parseInt(alcada));
                    temp.setColPal(new Color(Integer.parseInt(colPal)));
                    temp.setHorizontal(horizontal.equals("true"));
                    temp.setAureaProp(aureaProp.equals("true"));

                    temp.setSuperX(Integer.parseInt(superX));
                    temp.setSuperY(Integer.parseInt(superY));
                    temp.setTextura(textura.equals("true"));

                    temp.setVersion(version);
                }
                myResult.close();

            } catch (NumberFormatException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        return (temp);
    }

}
