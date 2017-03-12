package Model;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * LoginModel extends Model
 * Si preoccupa di stabilire la connessione col DB, e di controllare la presenza o meno dell username e password nel DB
 */
public class LoginModel extends Model {

    private String UserInserito;
    private String PassInserita;
    private String volocand;


    /*COSTRUTTORE*/
    public LoginModel(String userInserito, String passInserita){

        super();
        UserInserito = userInserito;
        PassInserita = passInserita;
        volocand = null;  //oggetto non valido o no creato--> non noto

    }

    /**
     * SearchSQL controlla l' esatezza dell username e password inseriti dall'utente.
     * Fa uso di due metodi di servizio. --> TrovaUser, TrovaPass
     *
     * @return true --> ok
     * @return false --> username e password errati
     *
     **/

    @Override
    public boolean SearchSQL() {

        Boolean controllo = false;

        openConnection();

        String sql ="SELECT user,pass,vol_o_cand FROM pass ";
        ResultSet query = selectQuery(sql);

        if(TrovaUser(query) && TrovaPass(query))
            controllo = true;

        closeConnection();

        return controllo;

    }

    @Override
    public boolean UpdateSQL() {

        return false;

    }

    @Override
    public boolean InsertSQL() {

        return false;

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

     /*
     appunto su query.next()
     inizialmente query.next è posto prima della prima riga
     alla prima chiaata si posiziona sulla prima row
     alla seconda chiamata si posiziona sulla seconda row e cosi via
    */
        boolean trovatoUser = false;
        String user;

        try {
            while (!trovatoUser && query.next()) {

                user = query.getString("user");
                    if (user.equals(UserInserito)) {
                      trovatoUser = true;
                      volocand = query.getString("vol_o_cand");
                      //System.out.println(trovato);
                    }

            }
        }catch(SQLException se){
          se.printStackTrace();
        }

        return trovatoUser;

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

        boolean trovatoPass = false;

        try {
                           //se questa colonna non esiste mi genera una eccezione
            String pass = query.getString("pass");
            if(pass.equals(PassInserita))
                    trovatoPass = true;


        }catch(SQLException se) {
            se.printStackTrace();
        }

        return trovatoPass;

    }

    //getter
    public String getVolocand() {

        return volocand;
    }
}





