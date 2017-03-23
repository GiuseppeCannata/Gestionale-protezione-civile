package Model;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GestioneModel extends Model{

    private ArrayList<Persona> listutenti;
    private String utilizzatore;
    private String appoggio;


    /*Costruttore*/
    public GestioneModel(String Utilizzatore){

        super();
        utilizzatore = Utilizzatore;


        if(utilizzatore.equals("candidato")) {

            listutenti = new ArrayList<Persona>(20);
            appoggio = "vol_o_cand=0 and Conf_Archivista=0";
            popolacombo();

        }else if(utilizzatore.equals("volontario")) {

            appoggio = "vol_o_cand=1";
            listutenti = new ArrayList<Persona>(20);
            popolacombo();

        }else if(utilizzatore.equals("referente informatico")) {

            System.out.println("ciao");

            appoggio = "vol_o_cand=1 or vol_o_cand=0";
            listutenti = new ArrayList<Persona>(20);
            popolacombo();
        }

    }


    private void popolacombo() {


        try {

            openConnection();

            String sql = "select * from a,pass where "+appoggio+" and a.cf=pass.cf order by cognome,nome";

            ResultSet query = selectQuery(sql);

            while (query.next()) {

               Persona utente = new Volontario();

                utente.setCodice_Fiscale(query.getString("cf"));
                utente.setNome(query.getString("nome"));
                utente.setCognome(query.getString("cognome"));
                utente.setLuogo_di_Nascita(query.getString("luogodinascita"));
                utente.setIndirizzo_di_residenza(query.getString("indirizzodiresidenza"));
                utente.setTelefono_Fisso(query.getString("telefonofisso"));
                utente.setTelefono_Cellulare(query.getString("telefonomobile"));
                utente.setEmail(query.getString("email"));
                utente.setProfessione(query.getString("professione"));
                utente.setEventuale_Specializzazione(query.getString("eventualespecializzazione"));
                utente.setData_di_Nascita(query.getString("datadinascita"));

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
    public ArrayList<Persona> getListutenti(){

        return listutenti;

    }

}
