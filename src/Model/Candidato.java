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

            boolean controllo = false;

            openConnection();

            String sql = "update "+Appoggio[0]+" set "+Appoggio[2]+"='"+Appoggio[3]+"' where cf='"+Appoggio[1]+"'";


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
        return false;
    }
}
