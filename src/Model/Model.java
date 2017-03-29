package Model;

import DBC.DBConnessione;
import java.sql.*;


/**
 * Classe abstract padre di tutte le classi del model
 * contiene i metodi base per usare una tabella del DB
 */
public abstract class Model {

    protected Connection conn;
    private Statement stmt;
    private ResultSet rs;
    private DBConnessione db;


    /**
     * Costruttore della classe che inizializza un oggetto DBConnection per poi potersi connettere al DB
     */
    public Model() {

        conn = null;
        stmt = null;
        rs = null;
        db = new DBConnessione();
    }

    /**
     * OpenConnection apre la connessione col DB
     */
    protected void openConnection() {

        conn = db.connectDB();

    }

    /**
     * CloseConnection chiude la connessione col DB
     */
    protected void closeConnection() {

        conn = db.closeConnection();

    }

    /**
     * esegue una query sql per la selezione di dati
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
     * esegue una query sql per l'update dei dati (inserimento, modifica e eliminazione)
     *
     * @param sql stringa sql
     * @return true , avvenuta con successo
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

    /**
     * Metodo il cui corpo verra definito diversamente nelle varie classi figlie.
     * Serve per l inserimento dei dati nel DB
     * @return
     */
    public abstract boolean InsertSQL();
    /**
     * Metodo il cui corpo verra definito diversamente nelle varie classi figlie.
     * Serve per la ricerca dei dati nel DB
     * @return
     */
    public abstract boolean SearchSQL();
    /**
     * Metodo il cui corpo verra definito diversamente nelle varie classi figlie.
     * Serve per l update dei dati nel DB
     * @return
     */
    public abstract boolean UpdateSQL(String[] Appoggio);

}
