package Model;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * LoginModel extends Model
 * Si preoccupa di stabilire la connessione col DB, e di controllare la presenza o meno dell username e password nel DB
 */
public class LoginModel extends Model {

    private String UserInserito;
    private char[] PassInserita;
    private Boolean trovatoUser;
    private Boolean trovatoPass;
    private String volocand;


    /*COSTRUTTORE*/
    public LoginModel(String userInserito, char[] passInserita){

        UserInserito = userInserito;
        PassInserita = passInserita;
        trovatoUser = false;
        trovatoPass = false;
    }

    /**
     * VerificaEntità controlla l' esatezza dell username e password inseriti dall'utente.
     * Fa uso di due metodi di servizio. --> TrovaUser, TrovaPass
     *
     * @return true --> ok
     * @return false --> errormessage --> username e password errati
     *
     **/
    public Boolean VerificaEntità(){

        Boolean controllo = false;

        openConnection();
        String sql ="SELECT* FROM pass ";
        ResultSet query = selectQuery(sql);

        if(TrovaUser(query) && TrovaPass(query))
            controllo = true;

        closeConnection();

        return controllo;

    }

    /**
     * Metodo di servizio
     * TrovaUser Controlla la presenza o meno dell username inserito in fase di login
     *
     * @param query
     * @return true   --> L'username è corretto     (presente nel DB)
     * @return false  --> L'username non è corretto (non presente nel DB)
     */
    private boolean TrovaUser(ResultSet query){

        try {
            while (!trovatoUser && query.next()) {

                String user = query.getString("user");
                // System.out.println(user);
                if (user.equals(UserInserito)) {
                    trovatoUser = true;
                    volocand = query.getString("vol_o_cand");
                    //System.out.println(trovato);
                }

            }
        }catch(SQLException se){
          se.printStackTrace();
        }finally {

            return trovatoUser;
        }
    }


    /**
     * Metodo di servizio.
     * TrovaPass controlla la presenza o meno della password inserito in fase di login
     *
     * @param query
     * @return true   --> La password è corretta     (presente nel DB)
     * @return false  --> La password non è corretto (non presente nel DB)
     */
    private boolean TrovaPass(ResultSet query){

        try {

            String pass = query.getString("pass");
            //conversione
            char[] passw = pass.toCharArray();


            if (passw.length == PassInserita.length) {

                int i = 0;
                while (i < passw.length && passw[i] == PassInserita[i]) {

                    i++;

                }
                if (i == passw.length)
                    trovatoPass = true;

            }
        }catch(SQLException se){
            se.printStackTrace();
        }finally {

                return trovatoPass;
        }
    }


    public String getVolocand() {

        return volocand;
    }
}





