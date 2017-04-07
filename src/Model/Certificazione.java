package Model;


/**
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

    private String flag; //-->serve per taggare la certificazione come da eliminare o aggiornare, in fase di modifica


    /*COSTRUTTORI*/

    /*costruttore vuoto*/
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


    /**
     * Inserimento della certificazione nel DB
     * @return
     */
    @Override
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
     *
     * @return true ,andato a buon fine
     **/
    public boolean updatesql(){

        boolean controllo = false;
        openConnection();

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
     * @return true , eliminazione andata a buon fine
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

    @Override
    public String toString() {
        return "Certificazione{" +
                "codicefiscale='" + codicefiscale + '\'' +
                ", tipo='" + tipo + '\'' +
                ", nome='" + nome + '\'' +
                ", datascadenza='" + datascadenza + '\'' +
                ", dataacquisizione='" + dataacquisizione + '\'' +
                ", entedirilascio='" + entedirilascio + '\'' +
                ", n_documento='" + n_documento + '\'' +
                ", flag='" + flag + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Certificazione that = (Certificazione) o;

       return nome != null && nome.equals(that.getNome());

    }

}
