package Controller.Compiti;


import Model.GestioneModel;
import Model.Messaggio;
import Model.Persona;
import Model.Volontario;
import View.BasicFrameView;
import View.VolontarioDView;

import java.util.ArrayList;

/**
 * Classe rappresentate il compito dell' Admin
 */
public class Admin extends MC {


    /*COSTRUTTORI*/

    /*costruttore vuoto*/
    public Admin() {

        super();

    }

    public Admin(String Utilizzatore) {

        super(Utilizzatore);

    }

    /**
     * Metodo che permette di resettare sia i compiti che i ruoli
     *
     * @param UtenteLoggato --> affinche il reset non incida sull utente che è loggato
     */
    public void ResetAction(Volontario UtenteLoggato) {

        ArrayList<Persona> UTENTI = getUTENTI();

        for(Persona utente :UTENTI){

            if(!utente.getCodice_Fiscale().equals(UtenteLoggato.getCodice_Fiscale())) {

                //Conversione esplicita
                Volontario volontario = (Volontario) utente;

                if (getUtilizzatore().equals("compiti")) {
                    volontario.ResetCompiti();
                }
                if (getUtilizzatore().equals("ruoli"))
                    volontario.ResetRuoli();
            }
        }
    }

    /**
     * Metodo che permette di cancellare il vecchio MC, e di nominarne uno nuovo attraverso l immissione del codice fiscale
     * dell utente a cui si vuole affidare questo ruolo
     *
     * @param frame
     */

    public void ResetMC(BasicFrameView frame, Volontario Utente, VolontarioDView Dview) {

        ArrayList<Persona> UTENTI;

        String cf = frame.InputMessage("Inserire Codice fiscale del nuovo Master: ");


        try {

            if (cf.length() == 0)
                throw new Exception("Inserire codice fiscale");

            if (cf.length() < 16)
                throw new Exception("Lungezza codice fiscale errata");

            if (cf.equals(Utente.getCodice_Fiscale()))
                throw new Exception("Errore è il suo codice fiscale");


            GestioneModel model = new GestioneModel("vol_o_cand=1");
            UTENTI = model.Ruoli();

            //cerco il vecchio cordinatore per resettarlo a volontario semplice
            for (Persona utente : UTENTI) {

                Volontario volontario = (Volontario) utente;

                if (volontario.getRuolo().equals("Cordinatore")) {

                    volontario.ResetRuoli();

                    //messaggio privato --> lo informo del passaggio a volontario semplice
                    Messaggio sms;
                    sms = new Messaggio(volontario.getCodice_Fiscale(), "Admin",
                            "Sei diventato un Volontario_semplice");

                    if (sms.InsertSQL())
                        frame.Message("Il vecchio cordinatore è stato cancellato");

                }

            }

            //ELEGGO IL NUOVO CORDINATORE
            String[] appoggio = new String[4];
            appoggio[0] = "flagvolontario";
            appoggio[1] = "ruolo";
            appoggio[2] = "Cordinatore";
            appoggio[3] = cf;

            if (model.UpdateSQL(appoggio)) {

                for (Persona utente : UTENTI)
                    if (utente.getCodice_Fiscale().equals(cf)) {

                        //messaggio di Broadcast
                        Messaggio messaggio = new Messaggio("Broadcast", "Admin",
                                utente.getNome() + " " + utente.getCognome() + " è il nuovo cordinatore ");

                        if (messaggio.InsertSQL()) {

                            messaggio.AggiornaBroadcast(Utente, Dview);

                            //messaggio privato per il nuovo cordinatore
                            Messaggio sms = new Messaggio(cf, "Admin", "Sei diventato un Cordinatore");

                            if (sms.InsertSQL())
                                frame.Message("Cambiamento avvenuto!\nHo inoltre avvertito il nuovo Codinatore");
                        }
                    }

            }
        }catch (Exception e){

            getBasicframe().ErrorMessage(e.getMessage());

        }


    }

    @Override
    public String toString() {

        return "Admin{}";

    }

    //equals ereditato da MC
}
