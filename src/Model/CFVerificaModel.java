package Model;

import java.sql.ResultSet;
import java.sql.SQLException;


public class CFVerificaModel extends Model {

    private String CodiceFiscaleInserito;
    private Boolean trovatocf;
    private String user;


    public CFVerificaModel(String codicefiscaleinserito){

        openConnection();

        CodiceFiscaleInserito = codicefiscaleinserito;

        trovatocf = false;



    }

    /**
     *
     */
    public Boolean VerificaEntità(){

        Boolean result = false;

        String sql ="SELECT* FROM pass ";

        ResultSet query = selectQuery(sql);


        if(TrovaCF(query))
            result = true;

        closeConnection();

        return result;

    }

    /**
     *
     *
     * @param query
     * @return true
     * @return false
     */
    private boolean TrovaCF(ResultSet query){

        try {
            while (!trovatocf && query.next()) {

                String cf = query.getString("cf");
                // System.out.println(user);
                if (cf.equals(CodiceFiscaleInserito))
                    trovatocf = true;
                //System.out.println(trovato);

            }

            setUser(query.getString("user"));

        }catch(SQLException se){
            se.printStackTrace();
        }finally {

            return trovatocf;
        }
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
