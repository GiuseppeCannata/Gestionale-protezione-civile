package Model;


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

        boolean controllo = false;

        openConnection();

        String sql = "update "+tipo+" set "+Appoggio[0]+"='"+Appoggio[1]+"' where cf='"+codicefiscale+"'";


        if(updateQuery(sql)){
            controllo = true;
        }
        closeConnection();
        return controllo;
    }

    public boolean DeleteSQL(){

        boolean controllo=false;

        openConnection();

        System.out.println(nome);
        System.out.println(codicefiscale);


        String sql = "delete from "+tipo+" where cf='"+codicefiscale+"' and nome='"+nome+"'";

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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
