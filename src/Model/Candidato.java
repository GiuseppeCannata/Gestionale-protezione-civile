package Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Candidato extends Persona {

	/*VARIABILI D'ISTANZA DI PERSONA*/

    //--> Oltre alle sue quelle ereditate da Persona


    private int Conf_Giunta;

    /*COSTRUTTORI*/

    public Candidato(String userInserito){

      super(userInserito);

      openConnection();

      String sql = "select * from pass where cf ='"+ getCodice_Fiscale() +"'";;

      ResultSet query = selectQuery(sql);


      try {

            if(query.next())
                Conf_Giunta = query.getInt("vol_o_cand");


        }catch(SQLException se){
            se.printStackTrace();
        }finally {
            closeConnection();
        }


    }


    /*OPERAZIONI*/

    //--> Oltre alle sue quelle ereditate da Persona


    public int getConf_Giunta() {

        return Conf_Giunta;

    }

    @Override
    public boolean UpdateSQL(String[] Appoggio) {
        return false;
    }

    @Override
    public boolean SearchSQL() {
        return false;
    }

    @Override
    public boolean InsertSQL() {
        return false;
    }
}
