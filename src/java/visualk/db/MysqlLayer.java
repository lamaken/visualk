package visualk.db;

/**
 * Capa pel mySQL
 *
 * @autor alex
 */
import java.sql.*;

public class MysqlLayer {

    private String dbServer = null;
    private String dbUser = null;
    private String dbPassword = null;
    private String dbDataBase = null;

    protected Connection dbConn = null;

    protected ResultSet result = null;
    protected PreparedStatement sql = null;

    /**
     * Crea la instÃ ncia de sqlLayer
     */
    public MysqlLayer() {
        result = null;
    }

    public void disconnect() {
        try {
            result.close();
            sql.close();
            dbConn.close();
        } catch (Exception e) {
            System.out.print("no puc tancar la bd.");
        }
    }

    public void setDBValues(String dbServer, String dbUser, String dbPassword, String dbDataBase) {
        this.dbPassword = dbPassword;
        this.dbServer = dbServer;
        this.dbPassword = dbPassword;
        this.dbDataBase = dbDataBase;
        this.dbUser = dbUser;
    }

    public void executeDB(String sqlQuery) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String cadenaConn = "jdbc:mysql://" + dbServer + ":3306/" + dbDataBase;
        try {
            dbConn = DriverManager.getConnection(cadenaConn, dbUser, dbPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            sql = dbConn.prepareStatement(sqlQuery);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            sql.execute();
           
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public ResultSet queryDB(String sqlQuery) {
        PreparedStatement sql = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            String cadenaConn = "jdbc:mysql://" + dbServer + ":3306/" + dbDataBase;
            dbConn = DriverManager.getConnection(cadenaConn, dbUser, dbPassword);
            sql = dbConn.prepareStatement(sqlQuery);
            result = sql.executeQuery();
           
            // Close the result set, statement and the connection

        } catch (SQLException s) {
            s.printStackTrace();
            System.out.println("SQL Error on Open jdbc:mysql://" + dbServer + ":3306/" + dbDataBase);
            System.out.println("SQL Error on Open:" + dbUser + "," + dbPassword);
            //TODO:control d'excepcio

        }

        return (result);
    }
}
