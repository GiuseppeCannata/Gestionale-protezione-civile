package Model;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * CFVerificaModel
 * Si preoccupa di stabilire la connessione col DB(per la CFVerificaController),
 * e di controllare la presenza o meno ,del codicefiscale passato, nel DB
 */
public class CFVerificaModel extends Model {

    private String CodiceFiscaleInserito;


    /*COSTRUTTORI*/

    public CFVerificaModel() {

        return;

    }

    public CFVerificaModel(String codicefiscaleinserito){

        super();
        CodiceFiscaleInserito = codicefiscaleinserito;

    }

    /**
     * SearchSQL Controlla la presenza o meno dell codice fiscale inserito in fase di verifica.
     *
     * @return true  --> il codice fiscale è presente nel DB(l utente era gia registrato)
     * @return false --> il codice fiscale non è nel DB(l utente ha bisogno di registrarsi)
     *
     */

    @Override
    public boolean SearchSQL() {

        Boolean controllo = false;

        openConnection();

        String sql ="select cf from pass where cf='"+CodiceFiscaleInserito+"'";
        ResultSet query = selectQuery(sql);

        try {

            if(query.next()) {

                String cf = query.getString("cf");
                if (cf.equals(CodiceFiscaleInserito))
                   controllo = true;

            }

        }catch(SQLException se){
            se.printStackTrace();
        }finally {
            closeConnection();
        }

        return controllo;

    }

    @Override
    public boolean InsertSQL() {

        return false;

    }

    @Override
    public boolean UpdateSQL(String[] Appoggio) {

        return false;

    }

    @Override
    public String toString() {

        return "CFVerificaModel";
    }
}

