package DBC;

import java.sql.*;

/**
 * Classe contenente i dati e i metodi per consentire la connessione col DB
 */
public class DBConnessione{

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/dbogg?autoReconnect=false&useSSL=false";
    private static final String DB_NAME = "dbogg";

    private String user;
    private String password;
    private Connection conn;

    /**
     * Costruttore della classe che assegna il valore null alla connessione
     */
    public DBConnessione() {

        conn = null;
        user = "root";
        password = "0000";

    }

    /**
     * Metodo che consente di creare una connessione col DB
     *
     * @return  Restituisce la connessione
     */
    public Connection connectDB() {

        try {

            Class.forName(JDBC_DRIVER);
            System.out.println("Connessione in corso..");
            conn = DriverManager.getConnection(DB_URL, user, password);

        } catch (SQLException se) {
            se.getErrorCode();
        } catch (Exception e) {
            e.getMessage();
        }

        return conn;
    }

    /**
     * Metodo che consente di chiudere la connessione col DB
     *
     * @return la connessione chiusa @return
     */
    public Connection closeConnection() {

        try {
            conn.close();
            System.out.println("Chiudo connessione");
        } catch (SQLException se) {
            se.getErrorCode();
        }
        return conn;
    }


    //GETTER
    public String getDbName(){

        return DB_NAME;

    }

    public String getUser(){

        return user;

    }

    public String getPassword(){

        return password;

    }

    @Override
    public String toString() {

        return "DBConnessione{}";

    }
}
