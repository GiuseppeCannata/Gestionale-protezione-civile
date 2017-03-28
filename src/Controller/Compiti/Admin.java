package Controller.Compiti;


import Model.GestioneModel;
import Model.Messaggio;
import Model.Persona;
import Model.Volontario;
import View.BasicFrameView;

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

    public void ResetMC(BasicFrameView frame) {

        ArrayList<Persona> UTENTI;

        String cf = frame.InputMessage("Inserire Codice fiscale del nuovo Master: ");

        if (cf != null && cf.length() == 16) {

            GestioneModel model = new GestioneModel("vol_o_cand=1");
            UTENTI = model.Ruoli();

            //cerco il vecchio cordinatore per resettarlo a volontario semplice
            for (Persona utente : UTENTI) {

                Volontario volontario = (Volontario) utente;

                if (volontario.getRuolo().equals("Cordinatore")) {

                    volontario.ResetRuoli();

                    //avviso di Broadcast
                    Messaggio messaggio = new Messaggio("Broadcast", "Admin",
                            volontario.getNome() + " " + volontario.getCognome() + " e' diventato un Volontario_Sempice");

                    if (messaggio.InsertSQL()) {

                        //messaggio privato
                        Messaggio sms;
                        sms = new Messaggio(volontario.getCodice_Fiscale(), "Admin",
                                "Sei diventato un Volontario_Sempice");

                        if (sms.InsertSQL())
                            frame.Message("Il vecchio cordinatore è stato cancellato");
                    }
                }

            }

            String[] appoggio = new String[4];
            appoggio[0] = "flagvolontario";
            appoggio[1] = "ruolo";
            appoggio[2] = "Cordinatore";
            appoggio[3] = cf;

            if (model.UpdateSQL(appoggio)) {

                for (Persona utente : UTENTI)
                    if(utente.getCodice_Fiscale().equals(cf)) {

                        //messaggio di Broadcast
                        Messaggio messaggio = new Messaggio ("Broadcast","Admin",
                            utente.getNome()+" "+utente.getCognome()+" è il nuovo cordinatore ");

                        //messaggio privato per il nuovo cordinatore
                        Messaggio sms = new Messaggio(cf, "Admin",
                                "Sei diventato un Cordinatore");

                        if (sms.InsertSQL())
                            frame.Message("Cambiamento avvenuto!\nHo inoltre avvertito il nuovo Codinatore");
                    }

            }

        }
    }

    @Override
    public String toString() {

        return "Admin{}";

    }

    //equals ereditato da MC


}
