package Controller;


import Model.Messaggio;
import Model.Volontario;
import View.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Controller per la View del cambia stato
 */
public class CambiaStatoController {

    private BasicFrameView basicframe;
    private CambiaStatoView CambiaStatoview;
    private VolontarioDView Dview;
    private String stato;
    private Volontario Utente;

    /*costruttore*/
    public CambiaStatoController(BasicFrameView frame, Volontario UtenteLoggato, VolontarioDView view){

        basicframe = frame;
        Dview = view;
        Utente = UtenteLoggato;
        CambiaStatoview = new CambiaStatoView();

        basicframe.setdestra(CambiaStatoview.getIntemedio0());

        Listener();

    }

    /**
     * Ascolto delle azioni svolte dall utente
     * Botton:cambiastato
     **/
    public void Listener(){

        /*cambia stato*/
        JButton cambiastatoButton = CambiaStatoview.getCambiaStatoButton();
        cambiastatoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                CambiaStatoAction();

            }
        });
    }

    /**
     * Metodo che richiama l update dell utente loggato affinche il suo stato possa essere modificato.
     * Se il cambiamento avviene correttamente informa l utente di cio.
     *
     * Richiama MessaggioDiBroadcast
     */
    private void CambiaStatoAction(){

        stato = (String) CambiaStatoview.getBox().getSelectedItem();
        String[] appoggio = new String[3];

        appoggio[0] = "flagvolontario";
        appoggio[1] = "stato";
        appoggio[2] =  stato;

        Utente.setStato(stato);
        Dview.setSTATOLabel(stato);

        if(Utente.UpdateSQL(appoggio) && MessaggioDiBroadcast())
            basicframe.Message("Il suo stato è mutato in: "+stato+" ");


    }

    /**
     * Se l utente cambia lo stato, questa informazione viene resa pubblica ai volontari attraverso un messaggio di broadcast.
     * Per fare questo utilizziamo questo metodo
     *
     * @return true ,messaggio salvato
     * @return false  ,messaggio non salvatio
     */
    private boolean MessaggioDiBroadcast(){

        boolean controllo = false;

        Messaggio messaggio = new Messaggio("Broadcast", Utente.getNome()+" "+Utente.getCognome(),
                "Ha cambiato il suo stato in : "+stato);


        if (messaggio.InsertSQL()){

            //aggiorno la lista dei mesaggi locale
            messaggio.AggiornaBroadcast(Utente, Dview);
           /* ArrayList<String> BROADCAST = Utente.getBROADCAST();
            BROADCAST.add("< "+Utente.getNome()+" "+Utente.getCognome()+" > : "+
                    "Ha cambiato il suo stato in : "+stato);
            Dview.setBroadcast(BROADCAST);*/
            controllo = true;

        }


        return controllo;
    }

    @Override
    public String toString() {
        return "CambiaStatoController{" +
                "basicframe=" + basicframe +
                ", CambiaStatoview=" + CambiaStatoview +
                ", Dview=" + Dview +
                ", stato='" + stato + '\'' +
                ", Utente=" + Utente +
                '}';
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CambiaStatoController that = (CambiaStatoController) o;

        return Dview != null ? Dview.equals(that.Dview) : that.Dview == null;
    }

}

