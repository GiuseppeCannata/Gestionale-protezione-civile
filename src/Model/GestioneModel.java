package Model;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Classe che consente ai vari compiti di interagire al meglio con il DB, in base a cio che ognuno di questi
 * richiede
 */

public class GestioneModel extends Model{

    private ArrayList<Persona> listutenti;
    private String appoggio;


    /*Costruttore*/

    /*costruttore vuoto*/
    public GestioneModel() {

        super();

    }

    public GestioneModel(String Appoggio){

        super();
        appoggio = Appoggio;
        listutenti = new ArrayList<Persona>(20);

    }

    /**
     * Consente di popolare le varie schede degli utenti
     *
     * @param utilizzatore
     * @return Un ArrayList contentente i vari utenti
     */
    public ArrayList<Persona> Schede(String utilizzatore) {

        try {

            openConnection();

            String sql = "select * from a,pass where "+appoggio+" and a.cf=pass.cf order by cognome,nome";

            ResultSet query = selectQuery(sql);

            while (query.next()) {


               Persona utente = new Volontario();

                utente.setCodice_Fiscale(query.getString("cf"));
                utente.popolaA();
                utente.popolaB();
                utente.popolaC();


                if(utilizzatore.equals("listavolontari")) {

                    //conversione esplicita
                    Volontario VOLONTARIO = (Volontario) utente;
                    VOLONTARIO.popolaD();
                    VOLONTARIO.popolastato();

                }

                listutenti.add(listutenti.size(), utente);

            }

        } catch (SQLException se) {
            se.printStackTrace();
        }finally{

            closeConnection();
            return listutenti;

        }

    }

    /**
     * Consete di popolare la sezione compiti
     *
     * @return Un ArrayList contentente i vari utenti
     */
    public ArrayList<Persona> Compiti(){

        try {

            openConnection();

            String sql = "select * from a,pass where "+appoggio+" and a.cf=pass.cf order by cognome,nome ";

            ResultSet query = selectQuery(sql);

            while (query.next()) {

                Persona utente = new Volontario();

                utente.setCodice_Fiscale(query.getString("cf"));
                utente.setNome(query.getString("nome"));
                utente.setCognome(query.getString("cognome"));

                //conversione esplicita
                Volontario VOLONTARIO = (Volontario) utente;
                VOLONTARIO.popolacompiti();

                listutenti.add(listutenti.size(), utente);

            }

        } catch (SQLException se) {
            se.printStackTrace();
        }finally{
            closeConnection();
            return listutenti;
        }

    }

    /**
     * Consete di popolare la sezione ruoli
     *
     * @return Un ArrayList contentente i vari utenti
     */
    public ArrayList<Persona> Ruoli() {

        try {

            openConnection();

            String sql = "select * from a,pass where "+appoggio+" and a.cf=pass.cf order by cognome,nome ";

            ResultSet query = selectQuery(sql);

            while (query.next()) {

                Persona utente = new Volontario();

                utente.setCodice_Fiscale(query.getString("cf"));
                utente.setNome(query.getString("nome"));
                utente.setCognome(query.getString("cognome"));

                //conversione eplicita
                Volontario VOLONTARIO = (Volontario) utente;
                VOLONTARIO.popolaruolo();

                listutenti.add(listutenti.size(), utente);

            }

        } catch (SQLException se) {
            se.printStackTrace();
        }finally{
            closeConnection();
            return listutenti;
        }

    }

    @Override
    public  boolean InsertSQL(){

        return false;

    };

    @Override
    public  boolean SearchSQL(){

        boolean controllo = false;
        try {

            openConnection();

            String sql = "select * from a,pass where a.cf=pass.cf "+appoggio+" order by cognome,nome";

            ResultSet query = selectQuery(sql);

            while (query.next()) {

                Persona utente = new Volontario();

                controllo = false;

                utente.setCodice_Fiscale(query.getString("cf"));
                utente.setNome(query.getString("nome"));
                utente.setCognome(query.getString("cognome"));

                listutenti.add(listutenti.size(), utente);
                controllo = true;

            }


        } catch (SQLException se) {
            se.printStackTrace();
        }finally{
            closeConnection();
        }

       return controllo;
    };

    @Override
    public boolean UpdateSQL(String[] Appoggio){

        boolean controllo = false;

        openConnection();

        String sql = "update "+Appoggio[0]+" set "+Appoggio[1]+"='"+Appoggio[2]+"' where cf='"+Appoggio[3]+"'";


        if(updateQuery(sql)){
            controllo = true;
        }
        closeConnection();
        return controllo;

    };


    //GETTER
    public ArrayList<Persona> getListutenti() {

        return listutenti;

    }

    @Override
    public String toString() {

        return "GestioneModel{}";

    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GestioneModel that = (GestioneModel) o;

        return listutenti != null ? !listutenti.equals(that.listutenti) : that.listutenti != null;

    }

}
