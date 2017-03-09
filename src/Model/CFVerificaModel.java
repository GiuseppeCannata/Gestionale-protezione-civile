package Model;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * CFVerificaModel extends Model.
 * Si preoccupa di stabilire la connessione col DB(per la CFVerificaController),
 * e di controllare la presenza o meno ,del codicefiscale passato, nel DB
 */
public class CFVerificaModel extends Model {

    private String CodiceFiscaleInserito;
    private Boolean trovatocf;
    private String user;


    public CFVerificaModel(String codicefiscaleinserito){

        CodiceFiscaleInserito = codicefiscaleinserito;
        trovatocf = false;
    }

    /**
     * VerificaEntità controlla se il codicefiscale inserito dall utente in fase di verifica(ante registrazione)
     * sia contenuto o meno all interno del DB
     *
     * @return true --> ok
     * @return false --> errormessage --> username e password errati
     *
     */
    public Boolean VerificaEntità(){

        Boolean controllo = false;

        openConnection();

        String sql ="SELECT* FROM pass ";

        ResultSet query = selectQuery(sql);


        if(TrovaCF(query))
            controllo = true;

        closeConnection();

        return controllo;

    }

    /**
     * Metodo di servizio.
     * TrovaCF Controlla la presenza o meno dell codice fiscale inserito in fase di verifica.
     * Qualora il codice fiscale fosse contenuto nel DB TrovaCF è in grado di prelevare l user e salvarlo
     * in modo tale da poterlo riutilizzare.--> lo scopo è quello di indicare all utente una sua precedente iscrizione
     * e di mostrargli l username con la quale si era registrato
     *
     * @param query
     * @return true  --> il codice fiscale è presente nel DB(l utente era gia registrato)
     * @return false --> il codice fiscale non è nel DB(l utente ha bisogno di registrarsi)
     *
     */
    private boolean TrovaCF(ResultSet query){

        try {
            while (!trovatocf && query.next()) {

                String cf = query.getString("cf");
                // System.out.println(user);
                if (cf.equals(CodiceFiscaleInserito)) {
                    trovatocf = true;
                    setUser(query.getString("user"));
                }
                //System.out.println(trovato);

            }

        }catch(SQLException se){
            se.printStackTrace();
        }finally {

            return trovatocf;
        }
    }

    //GETTER
    public String getUser() {

        return user;
    }

    public void setUser(String userDB) {

       user = userDB;
    }
}

