package Model;

import DBC.DBConnessione;
import java.sql.*;


/**
 * Classe abstract padre di tutte le classi del model
 * <p>
 * contiene i metodi basi per usare una tabella del DB
 */
public abstract class Model {

    protected Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private DBConnessione db;

    private int id_auto_increment = -1;



    /**
     * Costruttore della classe che inizializza un oggetto DBConnection per poi potersi connettere al DB
     */
    public Model() {

        db = new DBConnessione();
    }

    /**
     * Metodo per aprire la connessione col DB
     */
    protected void openConnection() {

        conn = db.connectDB();

    }

    /**
     * Metodo per chiudere la connessione col DB
     */
    protected void closeConnection() {

        conn = db.closeConnection();

    }

    /**
     * Metodo che esegue una query sql per la selezione di dati
     *
     * @param sql stringa contenente la query in sql
     * @return risultato della query
     */
    protected ResultSet selectQuery(String sql) {

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return rs;

    }

    /**
     * Metodo che esegue una query sql per l'update dei dati (nel dattaglio: inserimento, modifica e eliminazione)
     *
     * @param sql stringa sql
     * @return true se andata a buon fine altrimenti false
     */
    protected boolean updateQuery(String sql) {

        boolean result = false;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            result = true;
        } catch (SQLException se) {
            se.printStackTrace();
        }

        return result;
    }

}
