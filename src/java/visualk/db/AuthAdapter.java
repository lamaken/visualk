package visualk.db;


public interface AuthAdapter {
    void prepareDB(String dbServer,String dbUser, String dbPassword,String dbDataBase);
    boolean canEnter(String alias, String clau);
}