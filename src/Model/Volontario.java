package Model;

import Model.Persona;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class Volontario extends Persona {


    /*SEZIONE D*/
    private String grupposanguigno;
    private String tagliatesta;
    private String tagliabusto;
    private String tagliagiacca;
    private String tagliamano;
    private String tagliapantaloni;
    private String tagliascarpe;

   /* private String ruolo;
    private int stato;*/

    private ArrayList<Compito> COMPITI;



    public Volontario() {

        return;

    }

    public Volontario(String userInserito){

        super(userInserito);

        popolaD();
        popolacompiti();

    }


    private void popolaD(){

        openConnection();

        try {


            String sql = "select grupposang,tagliatesta,tagliabusto,tagliagiacca,tagliapantaloni,tagliascarpe" +
                    " from d where cf ='"+getCodice_Fiscale()+"'";


            ResultSet query = selectQuery(sql);
            if(query.next()){

                grupposanguigno = query.getString("grupposang");
                tagliatesta = query.getString("tagliatesta");
                tagliabusto = query.getString("tagliabusto");
                tagliagiacca = query.getString("tagliagiacca");
                tagliamano = query.getString("tagliamano");
                tagliapantaloni = query.getString("tagliapantaloni");
                tagliascarpe = query.getString("tagliascarpe");


            }
        }catch(SQLException se){
            se.printStackTrace();
        }finally{
            closeConnection();
        }

    }

    private void popolacompiti(){

        openConnection();

        try{

            String sql = "select* from compiti where cf ='"+getCodice_Fiscale()+"'";

            ResultSet query = selectQuery(sql);

            if(query.next()) {

                if (query.getInt("archivista") == 1){

                    Compito compito = new Compito("archivista");
                    COMPITI.add(COMPITI.size(), compito);
                }

               /* if (query.getInt("magazzino") == 1){

                    Compito compito = new Compito("magazzino");
                    COMPITI.add(COMPITI.size(), compito);
                }*/


                if (query.getInt("comunicazionigiunta") == 1){

                    Compito compito = new Compito("comunicazionigiunta");
                    COMPITI.add(COMPITI.size(), compito);

                }



            }

        }catch(SQLException se){
            se.printStackTrace();
        }finally{
            closeConnection();
        }

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
