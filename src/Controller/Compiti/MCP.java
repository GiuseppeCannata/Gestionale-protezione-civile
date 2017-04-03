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
 * Classe rappresentate il compito dell Master_Chief_Plus.
 */

public class MCP extends MC {

    private VolontarioDView Dview;

    /*Costruttori*/

    public MCP() {

        super();

    }

    public MCP(BasicFrameView frame, Volontario UtenteLoggato, VolontarioDView view) {

        super(frame, UtenteLoggato);
        Dview = view;
        getMcHomeview().VisibilitaResetButton(true);
        getMcHomeview().VisibilitaNominaNuovoAdmin(true);

        getMcHomeview().VisibilitaResetCompitiButton(true);
        getMcHomeview().VisibilitaResetRuoliButton(true);

        AdminHomeListener();
    }

    /**
     * Ascolto delle azioni dell utente.
     * Botton:ResetMC, ResetCompiti,ResetRuoli
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

                    setUTENTI(getModel().CompitiRuoli());
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

                    setUTENTI(getModel().CompitiRuoli());
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

                setModel(new GestioneModel("vol_o_cand=1"));

                if (cf.length() == 0)
                    throw new Exception("Inserire codice fiscale");

                if (cf.length() < 16)
                    throw new Exception("Lungezza codice fiscale errata");

                if (cf.equals(getUtenteloggato().getCodice_Fiscale()))
                    throw new Exception("Errore è il suo codice fiscale");

                if(!getModel().SearchUtente(cf))
                    throw new Exception("Utente non trovato");


                setUTENTI(getModel().CompitiRuoli());

                //cerco il vecchio cordinatore per resettarlo a volontario semplice
                for (Persona utente : getUTENTI()) {

                    Volontario volontario = (Volontario) utente;

                    if (volontario.getRuolo().equals("Cordinatore")) {

                        volontario.ResetRuoli();

                        //messaggio privato --> lo informo del passaggio a volontario semplice
                        Messaggio sms;
                        sms = new Messaggio(volontario.getCodice_Fiscale(), "MCP",
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
                                    utente.getNome() + " " + utente.getCognome() + " è il nuovo Cordinatore ");

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
     * Metodo che permette di nominare il nuovo MCP
     *
     */
    private void NominaNuovoAdminAction(){

        String cf = getBasicframe().InputMessage("Inserire Codice fiscale del nuovo Admin: ");

        try {

            if(cf != null) {

                setModel(new GestioneModel());

                if (cf.length() == 0)
                    throw new Exception("Inserire codice fiscale");

                if (cf.length() < 16)
                    throw new Exception("Lungezza codice fiscale errata");

                if (cf.equals(getUtenteloggato().getCodice_Fiscale()))
                    throw new Exception("Errore è il suo codice fiscale");

                if(!getModel().SearchUtente(cf))
                    throw new Exception("Utente non trovato");


                //Cancello il vecchio admin
                String[] appoggio = new String[4];
                appoggio[0] = "flagvolontario";
                appoggio[1] = "ruolo";
                appoggio[2] = "Direttivo";
                appoggio[3] = getUtenteloggato().getCodice_Fiscale();

                                                    //il vecchio admin perde tutti i compiti prima posseduti
                if(getModel().UpdateSQL(appoggio) && getUtenteloggato().ResetCompiti()) {


                    //ELEGGO IL NUOVO MCP
                    appoggio[0] = "flagvolontario";
                    appoggio[1] = "ruolo";
                    appoggio[2] = "Admin";
                    appoggio[3] = cf;

                    if (getModel().UpdateSQL(appoggio) && getModel().SetteggioNuovoAdmin(cf)) {

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
                                        + "\nPer vedere le modifiche apportate, effettuare un logout!");
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
        return "MCP{" +
                "Dview=" + Dview +
                '}';
    }

    //equals ereditato da MC

}
