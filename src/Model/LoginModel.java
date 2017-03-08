package Model;

import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginModel extends Model {

    private String UserInserito;
    private char[] PassInserita;
    private Boolean trovatoUser;
    private Boolean trovatoPass;


    /*COSTRUTTORE*/
    public LoginModel(String userInserito, char[] passInserita){

        openConnection();

        UserInserito = userInserito;
        PassInserita = passInserita;
        trovatoUser = false;
        trovatoPass = false;
    }

    /**
     * VerificaEntità controlla l' esatezza dell username e password inseriti dall'utente.
     * Fa uso di due metodi di servizio.
     *
     * @return true --> ok
     * @return false --> errormessage --> username e password errati
     **/
    public Boolean VerificaEntità(){

        Boolean result = false;

        String sql ="SELECT* FROM pass ";

        ResultSet query = selectQuery(sql);


        if(TrovaUser(query) && TrovaPass(query))
            result = true;

        closeConnection();

        return result;

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
                if (user.equals(UserInserito))
                    trovatoUser = true;
                //System.out.println(trovato);

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
}





