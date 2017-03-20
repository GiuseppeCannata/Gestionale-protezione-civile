package Model;


/**
 * Certificazione
 * Raffigura la struttura(presente nel DB) dei corsi, patenti, abilitazioni dell utente
 */
public class Certificazione extends Model{

    private String codicefiscale;

    private String tipo;
    private String nome;
    private String datascadenza;
    private String dataacquisizione;
    private String entedirilascio;
    private String n_documento;

    private String flag;


    public Certificazione(){

        return;
    }

    public Certificazione(String Tipo,String CodiceFiscale, String NomeCertificazione, String DataAcquisizione, String DataScadenza,
                          String EnteRilascio, String NDocumento) {

        tipo = Tipo;
        codicefiscale = CodiceFiscale;
        nome = NomeCertificazione;
        dataacquisizione = DataAcquisizione;
        datascadenza = DataScadenza;
        entedirilascio = EnteRilascio;
        n_documento = NDocumento;

        flag = "nessuna";




    }

    public boolean InsertSQL(){

        boolean controllo=false;

        openConnection();
        String sql = "Insert into "+tipo+"(cf,nome,datascadenza,dataacquisizione,entedirilascio,n_documento) values('" +
                codicefiscale       + "','" +
                nome                + "','" +
                datascadenza        + "','" +
                dataacquisizione    + "','" +
                entedirilascio      + "','" +
                n_documento         + "')";

        if (updateQuery(sql))
            controllo = true;


        closeConnection();
        return controllo;

    }

    @Override
    public boolean SearchSQL() {
        return false;
    }

    @Override
    public boolean UpdateSQL(String[] Appoggio) {
        return false;
    }

    /**
     * Esegue l update della certificazione nel DB
     **/
    public boolean updatesql(){

        boolean controllo = false;
        System.out.println("ci sono");
        openConnection();

        System.out.print(codicefiscale);
        String sql = "update "+tipo+" set datascadenza='"+datascadenza+"'" +
                ",dataacquisizione='"+dataacquisizione+"' ,entedirilascio='"+entedirilascio+
                "' ,n_documento='"+n_documento+"' where cf='"+codicefiscale+"' and nome='"+nome+"'";


        if(updateQuery(sql)){
            controllo = true;
        }
        closeConnection();
        return controllo;


    }

    /**
     * Elimina la certificazinoe dal DB
     *
     * @return
     */
    public boolean DeleteSQL(){

        boolean controllo=false;

        openConnection();


        String sql = "delete from "+tipo+" where cf='"+codicefiscale+"' and nome='"+nome+"'";

        if (updateQuery(sql))
            controllo = true;

        closeConnection();

        return controllo;

    }

    @Override
    public String toString() {

        return "Certificazione";

    }

    //GETTER e SETTER
    public String getNome() {

        return nome;

    }

    public String getDatascadenza() {

        return datascadenza;

    }

    public String getDataacquisizione() {

        return dataacquisizione;

    }

    public String getEntedirilascio() {

        return entedirilascio;

    }

    public String getN_documento() {

        return n_documento;

    }

    public void setNome(String Nome) {

        nome = Nome;

    }

    public void setDatascadenza(String Datascadenza) {

       datascadenza = Datascadenza;

    }

    public void setDataacquisizione(String Dataacquisizione) {

        dataacquisizione = Dataacquisizione;

    }

    public void setEntedirilascio(String Entedirilascio) {

       entedirilascio = Entedirilascio;

    }

    public void setN_documento(String N_documento) {

        n_documento = N_documento;

    }


    public String getTipo() {

        return tipo;

    }

    public void setTipo(String Tipo) {

        tipo = Tipo;

    }


    public String getFlag() {

        return flag;

    }

    public void setFlag(String Flag) {

        flag = Flag;

    }
}
