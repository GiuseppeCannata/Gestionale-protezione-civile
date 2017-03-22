package Model;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GestioneModel extends Model{

    private ArrayList<Candidato> listcandidati;
    private ArrayList<Volontario> listvolontari;
    private String utilizzatore;
    private String appoggio;


    /* Costruttore*/

    public GestioneModel(String Utilizzatore){

        super();
        utilizzatore = Utilizzatore;


        if(utilizzatore.equals("candidato")) {

            listcandidati = new ArrayList<Candidato>(20);
            listvolontari = null;
            appoggio = "and Conf_Archivista=0";
            popolacomboC();

        }else if(utilizzatore.equals("volontario")){

            listcandidati = null;
            listvolontari = new ArrayList<Volontario>(20);
            popolacomboV();

        }else if(utilizzatore.equals("referenteinformatico")){

            listcandidati = new ArrayList<Candidato>(20);
            listvolontari = new ArrayList<Volontario>(20);
            appoggio= " ";
            popolacomboC();
            popolacomboV();

        }


    }


    private void popolacomboC() {


        try {

            openConnection();

            String sql = "select a.cf,nome,cognome,luogodinascita,indirizzodiresidenza,telefonofisso,telefonomobile,email," +
                    "dataprimaiscrizione,professione,eventualespecializzazione,datadinascita from a,pass where vol_o_cand=0 " +
                    appoggio+" and a.cf=pass.cf order by cognome,nome";

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
                    "dataprimaiscrizione,professione,eventualespecializzazione,datadinascita from a,pass where vol_o_cand=1" +
                    " and a.cf=pass.cf order by cognome,nome";

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


    /*GETTER*/
    public ArrayList<Candidato> getListcandidati(){

        return listcandidati;

    }

    public ArrayList<Volontario> getListvolontari() {

        return listvolontari;

    }
}
