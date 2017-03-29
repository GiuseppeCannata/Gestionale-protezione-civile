package Controller.Compiti;

import Model.GestioneModel;
import Model.Messaggio;
import Model.Persona;
import Model.Volontario;
import View.BasicFrameView;
import View.VolontarioDView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Classe rappresentate il compito dell Admin
 */

public class Admin extends MC {

    private VolontarioDView Dview;
    /*Costruttore*/

    public Admin(BasicFrameView frame, Volontario UtenteLoggato, VolontarioDView view) {

        super(frame, UtenteLoggato);
        Dview = view;
        getMcHomeview().VisibilitaResetButton(true);
        getMcHomeview().VisibilitaNominaNuovoAdmin(true);

        getMcHomeview().VisibilitaResetCompitiButton(true);
        getMcHomeview().VisibilitaResetRuoliButton(true);

        AdminHomeListener();
    }

    /**
     * Ascolto delle azioni dell utente
     * ResetMC, ResetCompiti,ResetRuoli
     */
    private void AdminHomeListener(){

        JButton ResetMC = getMcHomeview().getResetMCButton();
        ResetMC.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {


                ResetMC();

            }
        });

        JButton NominaNuovoAdmin = getMcHomeview().getNominaNuovoAdminButton();
        NominaNuovoAdmin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {



                    NominaNuovoAdminAction();


            }
        });

        JButton ResetCompiti = getMcHomeview().getResetCompitiButton();
        ResetCompiti.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if(getBasicframe().OpotionalMessage("Resettare tutti i compiti ?")==0) {
                   // MC controller;
                   // controller = new MC("compiti");
                   // controller.

                    setAppoggio("vol_o_cand = 1");
                    setUtilizzatore("compiti");

                    setModel(new GestioneModel(getAppoggio()));

                    setUTENTI(getModel().Compiti());
                    ResetAction();
                }

            }
        });

        JButton ResetRuoli = getMcHomeview().getResetRuoliButton();
        ResetRuoli.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if(getBasicframe().OpotionalMessage("Resettare tutti i ruoli ?")==0) {

                    setAppoggio("vol_o_cand = 1");
                    setUtilizzatore("ruoli");

                    setModel(new GestioneModel(getAppoggio()));

                    setUTENTI(getModel().Ruoli());
                    ResetAction();
                }

            }
        });



    }

    /**
     * Metodo che permette di cancellare il vecchio MC, e di nominarne uno nuovo attraverso l immissione del codice fiscale
     * dell utente a cui si vuole affidare questo ruolo
     *
     */

    private void ResetMC() {

        String cf = getBasicframe().InputMessage("Inserire Codice fiscale del nuovo Master: ");

        try {


            if(cf != null) {

                if (cf.length() == 0)
                    throw new Exception("Inserire codice fiscale");

                if (cf.length() < 16)
                    throw new Exception("Lungezza codice fiscale errata");

                if (cf.equals(getUtenteloggato().getCodice_Fiscale()))
                    throw new Exception("Errore è il suo codice fiscale");



                setModel(new GestioneModel("vol_o_cand=1"));
                setUTENTI(getModel().Ruoli());

                //cerco il vecchio cordinatore per resettarlo a volontario semplice
                for (Persona utente : getUTENTI()) {

                    Volontario volontario = (Volontario) utente;

                    if (volontario.getRuolo().equals("Cordinatore")) {

                        volontario.ResetRuoli();

                        //messaggio privato --> lo informo del passaggio a volontario semplice
                        Messaggio sms;
                        sms = new Messaggio(volontario.getCodice_Fiscale(), "Admin",
                                "Sei diventato un Volontario_semplice");

                        if (sms.InsertSQL())
                            getBasicframe().Message("Il vecchio cordinatore è stato cancellato");

                    }

                }

                //ELEGGO IL NUOVO CORDINATORE
                String[] appoggio = new String[4];
                appoggio[0] = "flagvolontario";
                appoggio[1] = "ruolo";
                appoggio[2] = "Cordinatore";
                appoggio[3] = cf;

                if (getModel().UpdateSQL(appoggio)) {

                    for (Persona utente : getUTENTI())
                        if (utente.getCodice_Fiscale().equals(cf)) {

                            //messaggio di Broadcast
                            Messaggio messaggio = new Messaggio("Broadcast", "INFO",
                                    utente.getNome() + " " + utente.getCognome() + " è il nuovo cordinatore ");

                            if (messaggio.InsertSQL()) {

                                messaggio.AggiornaBroadcast(getUtenteloggato(), Dview);

                                //messaggio privato per il nuovo cordinatore
                                Messaggio sms = new Messaggio(cf, "Admin", "Sei diventato un Cordinatore");

                                if (sms.InsertSQL())
                                    getBasicframe().Message("Cambiamento avvenuto!\nHo inoltre avvertito il nuovo Codinatore");
                            }
                        }

                }
            }
        }catch (Exception e){

            getBasicframe().ErrorMessage(e.getMessage());

        }


    }

    /**
     * Metodo che permette di resettare sia i compiti che i ruoli
     * Il reset non incida sull utente che è loggato
     */
    public void ResetAction() {

        ArrayList<Persona> UTENTI = getUTENTI();

        for(Persona utente :UTENTI){

            if(!utente.getCodice_Fiscale().equals(getUtenteloggato().getCodice_Fiscale())) {

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
     * Metodo che permette di nominare il nuovo Admin
     *
     */
    private void NominaNuovoAdminAction(){

        String cf = getBasicframe().InputMessage("Inserire Codice fiscale del nuovo Admin: ");

        try {

            if(cf != null) {

                if (cf.length() == 0)
                    throw new Exception("Inserire codice fiscale");

                if (cf.length() < 16)
                    throw new Exception("Lungezza codice fiscale errata");

                if (cf.equals(getUtenteloggato().getCodice_Fiscale()))
                    throw new Exception("Errore è il suo codice fiscale");

                setModel(new GestioneModel("vol_o_cand=1"));
                setUTENTI(getModel().Ruoli());

                //Cancello il vecchio admin
                String[] appoggio = new String[4];
                appoggio[0] = "flagvolontario";
                appoggio[1] = "ruolo";
                appoggio[2] = "Direttivo";
                appoggio[3] = getUtenteloggato().getCodice_Fiscale();

                if(getModel().UpdateSQL(appoggio)) {


                    //ELEGGO IL NUOVO Admin
                    appoggio[0] = "flagvolontario";
                    appoggio[1] = "ruolo";
                    appoggio[2] = "Admin";
                    appoggio[3] = cf;

                    if (getModel().UpdateSQL(appoggio)) {

                        //messaggio di Broadcast
                        Messaggio messaggio = new Messaggio("Broadcast", "INFO",
                                getUtenteloggato().getNome() + " " + getUtenteloggato().getCognome() +
                                        " non è più l Admin ");

                        if (messaggio.InsertSQL()) {

                            messaggio.AggiornaBroadcast(getUtenteloggato(), Dview);

                            //messaggio privato per il nuovo cordinatore
                            Messaggio sms = new Messaggio(cf, "Admin", "Sei diventato il nuovo Admin");

                            if (sms.InsertSQL())
                                getBasicframe().Message("Cambiamento avvenuto!\nHo inoltre avvertito il nuovo Admin"
                                        + "\nPer vedere le modifiche apportate effettuare un logout!");
                        }
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
