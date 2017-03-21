package Model;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ArchivistaModel extends Model{

    private ArrayList<Candidato> listcandidati;
    private ArrayList<Volontario> listvolontari;
    private String utilizzatore;
    private int appoggio;


    /* Costruttore*/
    public ArchivistaModel(String Utilizzatore){

        super();
        utilizzatore = Utilizzatore;

        if(utilizzatore.equals("candidato")) {

            listcandidati = new ArrayList<Candidato>(20);
            appoggio = 0;
            popolacomboC();

        }else if(utilizzatore.equals("volontario")){

            listvolontari = new ArrayList<Volontario>(20);
            appoggio = 1;
            popolacomboV();

        }


    }


    @Override
    public  boolean InsertSQL(){

        return false;

    };

    @Override
    public  boolean SearchSQL(){

        return false;

    };

    @Override
    public boolean UpdateSQL(String[] Appoggio){
        return false;

    };

    private void popolacomboC() {


        try {

            openConnection();

            String sql = "select a.cf,nome,cognome,luogodinascita,indirizzodiresidenza,telefonofisso,telefonomobile,email," +
                    "dataprimaiscrizione,professione,eventualespecializzazione,datadinascita from a,pass where vol_o_cand="
                    +appoggio+" and Conf_Archivista=0 and a.cf=pass.cf order by cognome,nome";

            ResultSet query = selectQuery(sql);

            while (query.next()) {

               Candidato utente = new Candidato();

                utente.setCodice_Fiscale(query.getString("cf"));
                utente.setNome(query.getString("nome"));
                utente.setCognome(query.getString("cognome"));
                utente.setLuogo_di_Nascita(query.getString("luogodinascita"));
                utente.setIndirizzo_di_residenza(query.getString("indirizzodiresidenza"));
                utente.setTelefono_Fisso(query.getString("telefonofisso"));
                utente.setTelefono_Cellulare(query.getString("telefonomobile"));
                utente.setEmail(query.getString("email"));
                //utente.setData_Prima_Iscrizione(query.getString("dataprimaiscrizione"));
                utente.setProfessione(query.getString("professione"));
                utente.setEventuale_Specializzazione(query.getString("eventualespecializzazione"));
                utente.setData_di_Nascita(query.getString("datadinascita"));

                utente.popolaB();
                utente.popolaC();

                    listcandidati.add(listcandidati.size(), utente);

            }

        } catch (SQLException se) {
            se.printStackTrace();
        }finally{
            closeConnection();
        }

    }

    private void popolacomboV() {


        try {

            openConnection();

            String sql = "select a.cf,nome,cognome,luogodinascita,indirizzodiresidenza,telefonofisso,telefonomobile,email," +
                    "dataprimaiscrizione,professione,eventualespecializzazione,datadinascita from a,pass where vol_o_cand="
                    +appoggio+" and a.cf=pass.cf order by cognome,nome";

            ResultSet query = selectQuery(sql);

            while (query.next()) {

                Volontario utente = new Volontario();

                utente.setCodice_Fiscale(query.getString("cf"));
                utente.setNome(query.getString("nome"));
                utente.setCognome(query.getString("cognome"));
                utente.setLuogo_di_Nascita(query.getString("luogodinascita"));
                utente.setIndirizzo_di_residenza(query.getString("indirizzodiresidenza"));
                utente.setTelefono_Fisso(query.getString("telefonofisso"));
                utente.setTelefono_Cellulare(query.getString("telefonomobile"));
                utente.setEmail(query.getString("email"));
                //utente.setData_Prima_Iscrizione(query.getString("dataprimaiscrizione"));
                utente.setProfessione(query.getString("professione"));
                utente.setEventuale_Specializzazione(query.getString("eventualespecializzazione"));
                utente.setData_di_Nascita(query.getString("datadinascita"));

                utente.popolaB();
                utente.popolaC();
                utente.popolaD();

                listvolontari.add(listvolontari.size(), utente);

            }

        } catch (SQLException se) {
            se.printStackTrace();
        }finally{
            closeConnection();
        }

    }


    /*GETTER*/
    public ArrayList getListcandidati(){

        return listcandidati;

    }

    public ArrayList<Volontario> getListvolontari() {

        return listvolontari;

    }
}
