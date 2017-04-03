package Model;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Si preoccupa di stabilire la connessione col DB, e di controllare la presenza o meno dell username e password nel DB
 */
public class LoginModel extends Model {

    private String userInserito;
    private String passInserita;
    private String volocand;


    /*COSTRUTTORE*/

    //costruttore vuoto
    public LoginModel(){

        return;

    }
    public LoginModel(String UserInserito, String PassInserita){

        super();
        userInserito = UserInserito;
        passInserita = PassInserita;
        volocand = null;  //oggetto non valido o no creato--> non noto

    }

    /**
     * SearchSQL controlla l' esatezza dell username e password inseriti dall'utente, cercandoli all interno del DB.
     * Fa uso di due metodi di servizio TrovaUser, TrovaPass
     *
     * @return true ,ok, user e pass corretti
     * @return false ,username e password errati
     *
     **/

    @Override
    public boolean SearchSQL() {

        /*
     appunto su query.next()
     inizialmente query.next è posto prima della prima riga
     alla prima chiaata si posiziona sulla prima row
     alla seconda chiamata si posiziona sulla seconda row e cosi via
    */

        boolean controllo = false;

        openConnection();

        String sql ="select user,pass,vol_o_cand from pass where user='"+userInserito+"'";
        ResultSet query = selectQuery(sql);

        try {

            if(query.next()){

                String pass = query.getString("pass");

                if(pass.equals(passInserita)) {
                    controllo = true;
                    volocand = query.getString("vol_o_cand");
                }

            }


        }catch(SQLException se){
            se.printStackTrace();
        }finally{
            closeConnection();
        }


        return controllo;

    }

    @Override
    public boolean UpdateSQL(String[] Appoggio) {

        return false;

    }

    @Override
    public boolean InsertSQL() {

        return false;

    }


    //getter
    public String getVolocand() {

        return volocand;
    }


    @Override
    public String toString() {
        return "LoginModel{" +
                "userInserito='" + userInserito + '\'' +
                ", passInserita='" + passInserita + '\'' +
                ", volocand='" + volocand + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoginModel that = (LoginModel) o;

        if (userInserito != null ? !userInserito.equals(that.userInserito) : that.userInserito != null) return false;
        return passInserita != null ? !passInserita.equals(that.passInserita) : that.passInserita != null;

    }

}





