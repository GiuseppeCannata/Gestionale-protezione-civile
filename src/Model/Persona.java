package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


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
    private ArrayList<Patente> PATENTI;
    private ArrayList<Abilitazione> ABILITAZIONI;
    private ArrayList<Corso> CORSI;

    /*SEZIONE (C) */
    private String Denominazione_Datore_di_Lavoro;
    private String Telefono_Datore_Lavoro;
    private String Fax_Datore_di_Lavoro;
    private String email_Datore_di_Lavoro;
    private String numerocodicepostale;
    private String IBAN;


    private String Username;
    private String Password;



    /* Costruttore vuoto*/
    public Persona(){

        return;
    }

    public Persona(String username){

        Username = username;
        popolaA();
        popolaB();
        popolaC();

    }


    private void popolaA(){

        openConnection();

        try {

            String sql = "select cf,pass from pass where user='"+Username+"'";
            ResultSet query = selectQuery(sql);
            if(query.next()) {
                Codice_Fiscale = query.getString("cf");
                Password = query.getString("pass");
            }

            System.out.println(Codice_Fiscale);


            sql = "select* from a where cf ='"+Codice_Fiscale+"'";

            query = selectQuery(sql);
            if(query.next()){

                Codice_Fiscale = query.getString("cf");
               Nome = query.getString("nome");
                //System.out.println(Nome);
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
            System.out.println("A tutto bene");
        }
    }

    private void popolaB(){

        CORSI = new ArrayList<>(25);
        ABILITAZIONI = new ArrayList<>(25);
        PATENTI = new ArrayList<>(25);

        openConnection();

        String sql = "select* from abilitazioni where cf ='"+ Codice_Fiscale +"'";
        ResultSet query = selectQuery(sql);

        try{

            while(query.next()){

                Abilitazione temporaneo = new Abilitazione();

                temporaneo.setCodicefiscale(Codice_Fiscale);
                temporaneo.setNome(query.getString("nome"));
                temporaneo.setDatascadenza(query.getString("datascadenza"));
                temporaneo.setDataacquisizione(query.getString("dataacquisizione"));
                temporaneo.setEntedirilascio(query.getString("entedirilascio"));
                temporaneo.setN_documento(query.getString("n_documento"));

                ABILITAZIONI.add(temporaneo);
            }


        sql = "select* from patenti where cf ='"+ Codice_Fiscale +"'";
        query = selectQuery(sql);



            while(query.next()){
                Patente temporaneo = new Patente();

                temporaneo.setCodicefiscale(Codice_Fiscale);
                temporaneo.setNome(query.getString("nome"));
                temporaneo.setDatascadenza(query.getString("datascadenza"));
                temporaneo.setDataacquisizione(query.getString("dataacquisizione"));
                temporaneo.setEntedirilascio(query.getString("entedirilascio"));
                temporaneo.setN_documento(query.getString("n_documento"));

                PATENTI.add(temporaneo);

            }


        sql = "select* from corsi where cf ='"+ Codice_Fiscale +"'";
        query = selectQuery(sql);



            while(query.next()){

                Corso temporaneo = new Corso();

                temporaneo.setCodicefiscale(Codice_Fiscale);
                temporaneo.setNome(query.getString("nome"));
                temporaneo.setDatascadenza(query.getString("datascadenza"));
                temporaneo.setDataacquisizione(query.getString("dataacquisizione"));
                temporaneo.setEntedirilascio(query.getString("entedirilascio"));
                temporaneo.setN_documento(query.getString("n_documento"));

                CORSI.add(temporaneo);



            }
        }catch(SQLException se){
            se.printStackTrace();
        }finally{
            closeConnection();
            System.out.println("B tutto bene");
        }



    }

    private void popolaC(){

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
            System.out.println("C tutto bene");
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

        System.out.println(Data_di_Nascita);

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

    public void setCodice_Fiscale(String codice_Fiscale) {

        Codice_Fiscale = codice_Fiscale;

    }

    public String getData_Prima_Iscrizione() {

        return Data_Prima_Iscrizione;

    }

    public void setData_Prima_Iscrizione(String data_Prima_Iscrizione) {

        Data_Prima_Iscrizione = data_Prima_Iscrizione;

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

    public ArrayList<Patente> getPATENTI() {

        return PATENTI;

    }

    public ArrayList<Abilitazione> getABILITAZIONI() {

        return ABILITAZIONI;

    }

    public ArrayList<Corso> getCORSI() {

        return CORSI;

    }
}
