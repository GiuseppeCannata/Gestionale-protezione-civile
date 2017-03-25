package Model;



import java.sql.ResultSet;
import java.sql.SQLException;


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

    private String ruolo;
    private String stato;

    private String archivista;
    private String magazzinodivise;
    private String add_giunta;
    private String referenteinformatico;



    public Volontario() {

        return;

    }

    public Volontario(String userInserito){

        super(userInserito);

        popolaD();
        popolaprimoaccesso();
        popolaruolo();
        popolastato();
        popolacompiti();


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

    protected void popolacompiti(){

        openConnection();

        try{

            String sql = "select* from compiti where cf ='"+getCodice_Fiscale()+"'";

            ResultSet query = selectQuery(sql);

            if(query.next()) {

                archivista = query.getString("Archivista");
                magazzinodivise = query.getString("Magazzino_Divise");
                add_giunta = query.getString("Add_Giunta");
                referenteinformatico = query.getString("Referente_Informatico");

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

    private void popolastato(){

        openConnection();

        try {


            String sql = "select stato from flagvolontario where cf ='"+getCodice_Fiscale()+"'";


            ResultSet query = selectQuery(sql);
            if(query.next())

               stato = query.getString("stato");

        }catch(SQLException se){
            se.printStackTrace();
        }finally{
            closeConnection();
        }


    }

    protected void popolaruolo(){

        openConnection();

        try {


            String sql = "select ruolo from flagvolontario where cf ='"+getCodice_Fiscale()+"'";


            ResultSet query = selectQuery(sql);
            if(query.next())

                ruolo = query.getString("ruolo");
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

        return InsertD();

    }

    private boolean InsertD(){

        boolean controllo = false;
        openConnection();

        String sql = "Insert into d(cf,grupposang,tagliatesta,tagliabusto,tagliamano,tagliapantaloni,tagliascarpe,abilita) values('" +
                getCodice_Fiscale()                         + "','" +
                grupposanguigno                             + "','" +
                tagliatesta                                 + "','" +
                tagliabusto                                 + "','" +
                tagliamano                                  + "','" +
                tagliapantaloni                             + "','" +
                tagliascarpe                                + "','" +
                abilita                                     + "')";




        if(updateQuery(sql)) {
            controllo=true;
           // System.out.print("tutto bene");
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

    public String getStato() {
        return stato;
    }

    public String getArchivista() {
        return archivista;
    }

    public String getMagazzinodivise() {
        return magazzinodivise;
    }

    public String getAdd_giunta() {
        return add_giunta;
    }

    public String getReferenteinformatico() {
        return referenteinformatico;
    }

    public String getPrimoaccesso() {
        return primoaccesso;
    }

    public void setGrupposanguigno(String Grupposanguigno) {
        grupposanguigno = Grupposanguigno;
    }

    public void setTagliatesta(String Tagliatesta) {
        tagliatesta = Tagliatesta;
    }

    public void setTagliabusto(String Tagliabusto) {
        tagliabusto = Tagliabusto;
    }

    public void setTagliamano(String Tagliamano) {
        tagliamano = Tagliamano;
    }

    public void setTagliapantaloni(String Tagliapantaloni) {
        tagliapantaloni = Tagliapantaloni;
    }

    public void setTagliascarpe(String Tagliascarpe) {
        tagliascarpe = Tagliascarpe;
    }

    public void setAbilita(String Abilita) {
        abilita = Abilita;
    }

    public void setStato(String Stato) {
        stato = Stato;
    }

    public String getRuolo() {
        return ruolo;
    }
}