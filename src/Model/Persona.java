package Model;

import java.sql.ResultSet;
import java.sql.SQLException;


public class Persona extends Model{

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
    private String Data_Prima_Iscrizione; /*Data in cui il volontario si è iscritto alla protezione
	                                        civile MA NON DEVE PER FORZA COMBACIARE CON LA DATA IN
	                                        CUI E' STATA INFORMATIZZATA NELL'APPLICAZIONE LA SUA
	                                        ISCRIZIONE*/
    private String Professione;
    private String Eventuale_Specializzazione;


    /*SEZIONE (B) */
    private String Brevetti;
    private String Patenti;
    private String Licenze_Possedute;
    private String Abilitazioni_Possedute;
    private String Corsi_Frequentati;


    /*SEZIONE (C) */
    private String Denominazione_Datore_di_Lavoro;
    private String Telefono_Datore_Lavoro;
    private String Fax_Datore_di_Lavoro;
    private String email_Datore_di_Lavoro;
    private String Numero_Civico_Postale;
    private String IBAN;


    /*
     * Costruttore vuoto
     **/

    public Persona(){

        return;
    }


    public Persona (String CodiceFiscale){

        openConnection();

        String sql ="SELECT cf,nome,cognome FROM a  ";

        ResultSet query = selectQuery(sql);

        try{

            boolean trovato=false;

            while(!trovato && query.next()){
                String cf1= query.getString(1);//1-->cf
                System.out.println(cf1);
               trovato = cf1.equals(CodiceFiscale);
            }
               setNome(query.getString("nome"));
               setCognome(query.getString("cognome"));
               System.out.println(getCognome());
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

    public String getBrevetti() {
        return Brevetti;
    }

    public void setBrevetti(String brevetti) {
        Brevetti = brevetti;
    }

    public String getPatenti() {
        return Patenti;
    }

    public void setPatenti(String patenti) {
        Patenti = patenti;
    }

    public String getLicenze_Possedute() {
        return Licenze_Possedute;
    }

    public void setLicenze_Possedute(String licenze_Possedute) {
        Licenze_Possedute = licenze_Possedute;
    }

    public String getAbilitazioni_Possedute() {
        return Abilitazioni_Possedute;
    }

    public void setAbilitazioni_Possedute(String abilitazioni_Possedute) {
        Abilitazioni_Possedute = abilitazioni_Possedute;
    }

    public String getCorsi_Frequentati() {
        return Corsi_Frequentati;
    }

    public void setCorsi_Frequentati(String corsi_Frequentati) {
        Corsi_Frequentati = corsi_Frequentati;
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

    public void setEmail_Datore_di_Lavoro(String email_Datore_di_Lavoro) {
        this.email_Datore_di_Lavoro = email_Datore_di_Lavoro;
    }

    public String getNumero_Civico_Postale() {
        return Numero_Civico_Postale;
    }

    public void setNumero_Civico_Postale(String numero_Civico_Postale) {
        Numero_Civico_Postale = numero_Civico_Postale;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String iBAN) {
        IBAN = iBAN;
    }
}
