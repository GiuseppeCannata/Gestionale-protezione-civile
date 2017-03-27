package Model;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe che rappresenta il candidato
 */
public class Candidato extends Persona {

	/*VARIABILI D'ISTANZA DI PERSONA*/

    //--> Oltre alle sue quelle ereditate da Persona


    private int Conf_Giunta;
    private int Conf_Archivista;

    /*COSTRUTTORI*/

    public Candidato() {

        return;

    }

    public Candidato(String userInserito){

      super(userInserito);

      openConnection();

      String sql = "select * from pass where cf ='"+ getCodice_Fiscale() +"'";;

      ResultSet query = selectQuery(sql);

        try {

            if(query.next()) {
                Conf_Giunta = query.getInt("conf_giunta");
                Conf_Archivista = query.getInt("Conf_Archivista");
            }


        }catch(SQLException se){
            se.printStackTrace();
        }finally {
            closeConnection();
        }


    }


    @Override
    public boolean UpdateSQL(String[] Appoggio) {

            boolean controllo = false;

            openConnection();

            String sql = "update "+Appoggio[0]+" set "+Appoggio[1]+"='"+Appoggio[2]+"' where cf='"+getCodice_Fiscale()+"'";


            if(updateQuery(sql)){
                controllo = true;
            }
            closeConnection();
            return controllo;

    }


    @Override
    public boolean SearchSQL() {

        return false;

    }

    @Override
    public boolean InsertSQL() {

        boolean controllo = false;

        if(InsertCompiti() && InsertFlagVolontario())
            controllo = true;

        return controllo;

    }

    /**
     * Query inserimento nella tabella compiti
     * Inserimento di default--> questo metodo setta tutti i campi a no
     * @return true --> inserimento andato bene
     */
    private boolean InsertCompiti(){

        boolean controllo = false;
        openConnection();

        String sql = "Insert into Compiti(cf,Archivista,Add_Giunta,Referente_Informatico) values('" +
                getCodice_Fiscale()            + "','" +
                "no"                           + "','" +
                "no"                           + "','" +
                "no"                           + "')";

        if(updateQuery(sql))
            controllo=true;

        closeConnection();
        return controllo;

    }

    /**
     * Query inserimento nella tabella flagvolontario
     * Inserimento di default --> stato: attivo , ruolo: semplice
     * @return true --> inserimento andato a buon fine
     */
    private boolean InsertFlagVolontario(){

        boolean controllo = false;
        openConnection();

        String sql = "Insert into flagvolontario(cf,stato,ruolo) values('" +
                getCodice_Fiscale()            + "','" +
                "Attivo"                       + "','" +
                "Semplice"                     + "')";


        if(updateQuery(sql))
            controllo=true;

        closeConnection();
        return controllo;
    }

    public boolean DeleteSQL(String Appoggio){

        boolean controllo=false;

        openConnection();


        String sql = "delete from "+Appoggio+" where cf='"+getCodice_Fiscale()+"' ";

        if (updateQuery(sql))
            controllo = true;

        closeConnection();

        return controllo;

    }


    /*GETTER*/
    public int getConf_Giunta() {

        return Conf_Giunta;

    }

    public int getConf_Archivista(){

        return Conf_Archivista;

    }

    @Override
    public String toString() {

        return "Candidato";

    }
}
