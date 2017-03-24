package Model;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GestioneModel extends Model{

    private ArrayList<Persona> listutenti;
    private String appoggio;


    /*Costruttore*/
    public GestioneModel(String Appoggio){

        super();
        appoggio = Appoggio;
        listutenti = new ArrayList<Persona>(20);

    }


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

                if(utilizzatore.equals("volontario")) {

                    Volontario VOLONTARIO = (Volontario) utente;
                    VOLONTARIO.popolaD();

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

    @Override
    public  boolean InsertSQL(){

        return false;

    };

    @Override
    public  boolean SearchSQL(){

        boolean controllo = false;
        try {

            openConnection();

            String sql = "select * from a,pass where "+appoggio+" and a.cf=pass.cf order by cognome,nome";

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
        return false;

    };

    public ArrayList<Persona> getListutenti() {
        return listutenti;
    }
}
