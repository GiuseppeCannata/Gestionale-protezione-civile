package DBC;

import java.sql.*;


public class DBConnessione{

    // Costanti per la connessione al DB
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/dbogg";
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
     * Metodo per creare una connessione col DB
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
     * Metodo per creare una connessione con Mysql
     *
     * @return  Restituisce la connessione
     */
    public Connection connectMysql(){

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, user, password);
        } catch (SQLException se) {
            se.getErrorCode();
        } catch (Exception e) {
            e.getMessage();
        }

        return conn;
    }


    /**
     * Metodo per chiudere la connessione col DB
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

    public String getDbName(){

        return DB_NAME;

    }

    public String getUser(){

        return user;

    }

    public String getPassword(){

        return password;

    }

}
