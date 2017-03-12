package Model;


public class Patente {

    private String nome;
    private String datascadenza;
    private String dataacquisizione;
    private String entedirilascio;
    private String n_documento;


    public Patente() {

        return;

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
}
