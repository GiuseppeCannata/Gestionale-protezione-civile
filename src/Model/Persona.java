package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Persona
 * Classe abstact poiche non implementa i metodi della classe padre
 * Rappresenta la struttura fondamentale(sez A,B,C,PASS,MESSAGGI) di ogni candidato o volontario
 * Estende Model ma non implementa i suoi metodi astratti
 */
public abstract class Persona extends Model{

    /*VARIABILI D'ISTANZA DI PERSONA*/

    /*SEZIONE (A) */
    private String Nome;
    private String Cognome;
    private String Luogo_di_Nascita;
    private String Data_di_Nascita;
    private String Indirizzo_di_residenza;
    private String Telefono_Fisso;
    private String Telefono_Cellulare;
    private String Email;
    private String Codice_Fiscale;
    private String Data_Prima_Iscrizione;
    private String Professione;
    private String Eventuale_Specializzazione;

    /*SEZIONE (B) */
    private ArrayList<Certificazione> CERTIFICAZIONI;


    /*SEZIONE (C) */
    private String Denominazione_Datore_di_Lavoro;
    private String Telefono_Datore_Lavoro;
    private String Fax_Datore_di_Lavoro;
    private String email_Datore_di_Lavoro;
    private String numerocodicepostale;
    private String IBAN;

    /*Sez_Pass*/
    private String Username;
    private String Password;


    private ArrayList<String> BROADCAST;
    private ArrayList<String> MESSAGGI;


    /*COSTRUTTORI*/

    /* Costruttore vuoto*/
    public Persona(){

        return;
    }

    public Persona(String username){

        Username = username;

        User();
        popolaA();
        popolaB();
        popolaC();
        popolaBroadcast();
        popolaMessaggi();

    }

    /**
     * Ricerca il codice fiscale servendosi del UserInserito
     */
    private void User(){

        try {

            openConnection();
            String sql = "select cf,pass from pass where user='"+Username+"'";
            ResultSet query = selectQuery(sql);

            if(query.next()) {

                Codice_Fiscale = query.getString("cf");
                Password = query.getString("pass");

            }

        }catch(SQLException se){
            se.printStackTrace();
        }finally{
            closeConnection();

        }
    }

    /**
     * Metodo di servizio
     * Popola con l ausilio del DB le variabili di istanza, della sez A
     */
    protected void popolaA(){

        openConnection();

        try {

            String sql = "select* from a where cf ='"+Codice_Fiscale+"'";

            ResultSet query = selectQuery(sql);
            if(query.next()){

                Codice_Fiscale = query.getString("cf");
               Nome = query.getString("nome");
               Cognome = query.getString("cognome");
               Luogo_di_Nascita = query.getString("luogodinascita");
               Indirizzo_di_residenza = query.getString("indirizzodiresidenza");
               Telefono_Fisso = query.getString("telefonofisso");
               Telefono_Cellulare = query.getString("telefonomobile");
               Email = query.getString("email");
               Data_Prima_Iscrizione = query.getString("dataprimaiscrizione");
               Professione = query.getString("professione");
               Eventuale_Specializzazione = query.getString("eventualespecializzazione");
               Data_di_Nascita = query.getString("datadinascita");

            }
        }catch(SQLException se){
            se.printStackTrace();
        }finally{
            closeConnection();
            //System.out.println("A tutto bene");
        }
    }

    /**
     * Metodo di servizio
     * Popola le variabili di istanza, della sez B
     */
    protected void popolaB(){

        CERTIFICAZIONI = new ArrayList<>(25);


        openConnection();

        String sql = "select* from abilitazioni where cf ='"+ Codice_Fiscale +"'";
        ResultSet query = selectQuery(sql);

        try{

            while(query.next()){

                Certificazione temporaneo = new Certificazione("abilitazioni",Codice_Fiscale,
                        query.getString("nome"),query.getString("dataacquisizione"),
                        query.getString("datascadenza"),query.getString("entedirilascio"),
                        query.getString("n_documento"));

                CERTIFICAZIONI.add(CERTIFICAZIONI.size(), temporaneo);
            }


        sql = "select* from patenti where cf ='"+ Codice_Fiscale +"'";
        query = selectQuery(sql);

            while(query.next()){

                Certificazione temporaneo = new Certificazione("patenti",Codice_Fiscale,
                        query.getString("nome"),query.getString("dataacquisizione"),
                        query.getString("datascadenza"),query.getString("entedirilascio"),
                        query.getString("n_documento"));

                CERTIFICAZIONI.add(CERTIFICAZIONI.size(), temporaneo);

            }


        sql = "select* from corsi where cf ='"+ Codice_Fiscale +"'";
        query = selectQuery(sql);

            while(query.next()){

                Certificazione temporaneo = new Certificazione("corsi",Codice_Fiscale,
                        query.getString("nome"),query.getString("dataacquisizione"),
                        query.getString("datascadenza"),query.getString("entedirilascio"),
                        query.getString("n_documento"));

                CERTIFICAZIONI.add(CERTIFICAZIONI.size(), temporaneo);

            }
        }catch(SQLException se){
            se.printStackTrace();
        }finally{
            closeConnection();
           // System.out.println("B tutto bene");
        }

    }

    /**
     * Metodo di servizio
     * Popola  le variabili di istanza, della sez C
     */
    protected void popolaC(){

        openConnection();

        String sql = "select * from c where cf ='"+ Codice_Fiscale +"'";;

        ResultSet query = selectQuery(sql);

        try {

            if(query.next()){

                  Denominazione_Datore_di_Lavoro = query.getString("nomedatore");
                  Telefono_Datore_Lavoro = query.getString("telefono");
                  Fax_Datore_di_Lavoro = query.getString("faxdatore");
                  email_Datore_di_Lavoro = query.getString("email");
                  numerocodicepostale = query.getString("numero_codice_postale");
                  IBAN = query.getString("iban");

            }
        }catch(SQLException se){
            se.printStackTrace();
        }finally{
            closeConnection();
        }
    }

    /**
     * Popola i messaggi di broadcast dell utente
     */
    private void popolaBroadcast(){

        openConnection();
        BROADCAST = new ArrayList<>(5);

        String sql = "select * from messaggi where cf='Broadcast'";

        ResultSet query = selectQuery(sql);

        try {

            while(query.next()){

                String Mittente = query.getString("Mittente");

                String Messaggio = query.getString("messaggio");

                BROADCAST.add(BROADCAST.size(), "< "+Mittente+" > : "+Messaggio+".\n");

            }
        }catch(SQLException se){
            se.printStackTrace();
        }finally{
            closeConnection();
        }

    }

    /**
     * Popola i messaggi privati del utente
     */
    private void popolaMessaggi(){

        openConnection();
        MESSAGGI = new ArrayList<>(5);

        String sql = "select * from messaggi where cf='"+Codice_Fiscale+"' and letto='no'";

        ResultSet query = selectQuery(sql);

        try {

            while(query.next()){

                String Mittente = query.getString("Mittente");

                String Messaggio = query.getString("messaggio");
                MESSAGGI.add(MESSAGGI.size(), "< "+Mittente+" > : "+Messaggio+".\n");

                String[] appoggio = new String[3];

            }
        }catch(SQLException se){
            se.printStackTrace();
        }finally{
            closeConnection();
        }

    }


    /*GETTERS&SETTERS*/

    public String getNome() {

        return Nome;

    }

    public void setNome(String nome) {

        Nome = nome;

    }

    public String getCognome() {

        return Cognome;

    }

    public void setCognome(String cognome) {

        Cognome = cognome;

    }

    public String getLuogo_di_Nascita() {

        return Luogo_di_Nascita;

    }

    public void setLuogo_di_Nascita(String luogo_di_Nascita) {

        Luogo_di_Nascita = luogo_di_Nascita;

    }

    public String getData_di_Nascita() {

        return Data_di_Nascita;

    }

    public void setData_di_Nascita(String data_di_Nascita) {

        Data_di_Nascita = data_di_Nascita;

    }

    public String getIndirizzo_di_residenza() {

        return Indirizzo_di_residenza;

    }

    public void setIndirizzo_di_residenza(String indirizzo_di_residenza) {

        Indirizzo_di_residenza = indirizzo_di_residenza;

    }

    public String getTelefono_Fisso() {

        return Telefono_Fisso;

    }

    public void setTelefono_Fisso(String telefono_Fisso) {

        Telefono_Fisso = telefono_Fisso;

    }

    public String getTelefono_Cellulare() {

        return Telefono_Cellulare;

    }

    public void setTelefono_Cellulare(String telefono_Cellulare) {

        Telefono_Cellulare = telefono_Cellulare;

    }

    public String getEmail() {

        return Email;

    }

    public void setEmail(String email) {

        Email = email;

    }

    public String getCodice_Fiscale() {

        return Codice_Fiscale;

    }

    public String getProfessione() {

        return Professione;

    }

    public void setProfessione(String professione) {

        Professione = professione;

    }

    public String getEventuale_Specializzazione() {

        return Eventuale_Specializzazione;

    }

    public void setEventuale_Specializzazione(String eventuale_Specializzazione) {

        Eventuale_Specializzazione = eventuale_Specializzazione;

    }


    public String getDenominazione_Datore_di_Lavoro() {

        return Denominazione_Datore_di_Lavoro;

    }

    public void setDenominazione_Datore_di_Lavoro(String denominazione_Datore_di_Lavoro) {

        Denominazione_Datore_di_Lavoro = denominazione_Datore_di_Lavoro;

    }

    public String getTelefono_Datore_Lavoro() {

        return Telefono_Datore_Lavoro;

    }

    public void setTelefono_Datore_Lavoro(String telefono_Datore_Lavoro) {

        Telefono_Datore_Lavoro = telefono_Datore_Lavoro;

    }

    public String getFax_Datore_di_Lavoro() {

        return Fax_Datore_di_Lavoro;

    }

    public void setFax_Datore_di_Lavoro(String fax_Datore_di_Lavoro) {

        Fax_Datore_di_Lavoro = fax_Datore_di_Lavoro;

    }

    public String getEmail_Datore_di_Lavoro() {

        return email_Datore_di_Lavoro;

    }

    public void setEmail_Datore_di_Lavoro(String email_datore_di_Lavoro) {

        email_Datore_di_Lavoro = email_datore_di_Lavoro;

    }

    public String getNumerocodicepostale() {

        return numerocodicepostale;

    }

    public void setNumerocodicepostale(String numerocodicepostale) {

        this.numerocodicepostale = numerocodicepostale;

    }

    public String getIBAN() {

        return IBAN;

    }

    public ArrayList<String> getBROADCAST() {
        return BROADCAST;
    }

    public void setIBAN(String iBAN) {

        IBAN = iBAN;

    }

    public String getUsername() {

        return Username;

    }

    public String getPassword() {

        return Password;

    }

    public void setUsername(String username) {

        Username = username;

    }

    public void setPassword(String password) {

        Password = password;

    }

    public void setCodice_Fiscale(String codice_Fiscale) {

        Codice_Fiscale = codice_Fiscale;

    }

    public ArrayList<Certificazione> getCERTIFICAZIONI() {

        return CERTIFICAZIONI;

    }

    public ArrayList<String> getMESSAGGI() {

        return MESSAGGI;

    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Persona persona = (Persona) o;

        return Codice_Fiscale != null ? Codice_Fiscale.equals(persona.Codice_Fiscale) : persona.Codice_Fiscale == null;
    }

}
