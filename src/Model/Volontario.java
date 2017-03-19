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

    private String primoaccesso;

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
        popolaprimoaccesso();

    }


    private void popolaD(){

        openConnection();

        try {


            String sql = "select grupposang,tagliatesta,tagliabusto,tagliapantaloni,tagliascarpe" +
                    " from d where cf ='"+getCodice_Fiscale()+"'";


            ResultSet query = selectQuery(sql);
            if(query.next()){

                grupposanguigno = query.getString("grupposang");
                tagliatesta = query.getString("tagliatesta");
                tagliabusto = query.getString("tagliabusto");
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

    private void popolaprimoaccesso(){

        openConnection();

        try {


            String sql = "select primoaccesso from pass where cf ='"+getCodice_Fiscale()+"'";


            ResultSet query = selectQuery(sql);
            if(query.next())

               primoaccesso = query.getString("primoaccesso");

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


    //GETTER
    public String getGrupposanguigno() {
        return grupposanguigno;
    }

    public String getTagliatesta() {
        return tagliatesta;
    }

    public String getTagliabusto() {
        return tagliabusto;
    }


    public String getTagliamano() {
        return tagliamano;
    }

    public String getTagliapantaloni() {
        return tagliapantaloni;
    }

    public String getTagliascarpe() {
        return tagliascarpe;
    }

    public ArrayList<Compito> getCOMPITI() {
        return COMPITI;
    }

    public String getPrimoaccesso() {
        return primoaccesso;
    }
}
