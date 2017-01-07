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

    public Connection dbConn = null;

    protected ResultSet result = null;
    protected PreparedStatement sql = null;

    public MysqlLayer(String user, String pass, String db) {
        setDBValues("127.0.0.1", user, pass, db);

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
        }
        String cadenaConn = "jdbc:mysql://" + dbServer + ":3306/" + dbDataBase;
        try {
            dbConn = DriverManager.getConnection(cadenaConn, dbUser, dbPassword);
        } catch (Exception e) {
        }

        result = null;
    }

    public void disconnect() {
        try {
            if (result != null) {
                result.close();
            }
            if (sql != null) {
                sql.close();
            }
            if (dbConn != null) {
                dbConn.close();
            }
        } catch (Exception e) {
            System.out.println("no puc tancar la bd.>>>" + e.getMessage());
        }
    }

    private void setDBValues(String dbServer, String dbUser, String dbPassword, String dbDataBase) {
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
        disconnect();
    }

    public ResultSet queryDB(String sqlQuery) throws SQLException, ClassNotFoundException {
        PreparedStatement sql = null;

        String cadenaConn = "jdbc:mysql://" + dbServer + ":3306/" + dbDataBase;
        try {
            dbConn = DriverManager.getConnection(cadenaConn, dbUser, dbPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        sql = dbConn.prepareStatement(sqlQuery);
        result = sql.executeQuery();

        if (result == null) {
            System.out.println("SQL Error mysql, result is null");
            disconnect();
        }

        return (result);
    }
}
