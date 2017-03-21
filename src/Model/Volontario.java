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
    private String tagliamano;
    private String tagliapantaloni;
    private String tagliascarpe;
    private String abilita;

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


    protected void popolaD(){

        openConnection();

        try {


            String sql = "select grupposang,tagliatesta,tagliabusto,tagliamano,tagliapantaloni,tagliascarpe,abilita" +
                    " from d where cf ='"+getCodice_Fiscale()+"'";


            ResultSet query = selectQuery(sql);
            if(query.next()){

                grupposanguigno = query.getString("grupposang");
                tagliatesta = query.getString("tagliatesta");
                tagliabusto = query.getString("tagliabusto");
                tagliamano = query.getString("tagliamano");
                tagliapantaloni = query.getString("tagliapantaloni");
                tagliascarpe = query.getString("tagliascarpe");
                abilita = query.getString("abilita");


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

        boolean controllo = false;

        openConnection();

        String sql = "update "+Appoggio[0]+" set "+Appoggio[1]+"='"+Appoggio[2]+"' where cf='"+getCodice_Fiscale()+"'";


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

    public boolean InsertD(){

        boolean controllo = false;
        openConnection();

        String sql = "Insert into d(cf,grupposang,tagliatesta,tagliabusto,tagliamano,tagliapantaloni,tagliascarpe,abilita) values('" +
                getCodice_Fiscale()                         + "','" +
                grupposanguigno                             + "','" +
                tagliatesta                                 + "','" +
                tagliabusto                                 + "','" +
                tagliamano                                  + "','" +
                tagliapantaloni                             + "','" +
                tagliascarpe                                 + "','" +
                abilita                                      + "')";




        if(updateQuery(sql)) {
            controllo=true;
            System.out.print("tutto bene");
        }


        closeConnection();
        return controllo;

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

    public String getAbilita() {
        return abilita;
    }

    public ArrayList<Compito> getCOMPITI() {
        return COMPITI;
    }

    public String getPrimoaccesso() {
        return primoaccesso;
    }

    public void setGrupposanguigno(String grupposanguigno) {
        this.grupposanguigno = grupposanguigno;
    }

    public void setTagliatesta(String tagliatesta) {
        this.tagliatesta = tagliatesta;
    }

    public void setTagliabusto(String tagliabusto) {
        this.tagliabusto = tagliabusto;
    }


    public void setTagliamano(String tagliamano) {
        this.tagliamano = tagliamano;
    }

    public void setTagliapantaloni(String tagliapantaloni) {
        this.tagliapantaloni = tagliapantaloni;
    }

    public void setTagliascarpe(String tagliascarpe) {
        this.tagliascarpe = tagliascarpe;
    }

    public void setAbilita(String abilita) {
        this.abilita = abilita;
    }
}
