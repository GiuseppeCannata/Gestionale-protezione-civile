package Model;


public class Abilitazione extends Model{

    private String codicefiscale;

    private String nome;
    private String datascadenza;
    private String dataacquisizione;
    private String entedirilascio;
    private String n_documento;


    public Abilitazione(){

        return;
    }

    public Abilitazione(String CodiceFiscale, String NomeCertificazione, String DataAcquisizione,String DataScadenza,
                        String EnteRilascio,String NDocumento) {

        codicefiscale = CodiceFiscale;
        nome = NomeCertificazione;
        dataacquisizione = DataAcquisizione;
        datascadenza = DataScadenza;
        entedirilascio = EnteRilascio;
        n_documento = NDocumento;

    }

    public boolean InsertSQL(){

        boolean controllo=false;

        openConnection();
        String sql = "Insert into abilitazioni(cf,nome,datascadenza,dataacquisizione,entedirilascio,n_documento) values('" +
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

    public boolean DeleteSQL(){

        boolean controllo=false;

        openConnection();

        System.out.println(nome);
        System.out.println(codicefiscale);


        String sql = "delete from abilitazioni where cf='"+codicefiscale+"' and nome='"+nome+"'";

        if (updateQuery(sql))
            controllo = true;

        closeConnection();

        return controllo;

    }

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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDatascadenza(String datascadenza) {
        this.datascadenza = datascadenza;
    }

    public void setDataacquisizione(String dataacquisizione) {
        this.dataacquisizione = dataacquisizione;
    }

    public void setEntedirilascio(String entedirilascio) {
        this.entedirilascio = entedirilascio;
    }

    public void setN_documento(String n_documento) {
        this.n_documento = n_documento;
    }

    public void setCodicefiscale(String codicefiscale) {
        this.codicefiscale = codicefiscale;
    }
}
