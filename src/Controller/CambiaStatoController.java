package Controller;


import Model.Messaggio;
import Model.Volontario;
import View.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


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

        CambiaStatoControllerListener();

    }

    /**
     * ascoltatore
     **/
    public void CambiaStatoControllerListener(){

        /*cambia stato*/
        JButton cambiastatoButton = CambiaStatoview.getCambiaStatoButton();
        cambiastatoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                CambiaStatoAction();

            }
        });
    }

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

    private boolean MessaggioDiBroadcast(){

        boolean controllo = false;

        Messaggio messaggio = new Messaggio("Broadcast", Utente.getNome()+" "+Utente.getCognome(),
                "Ha cambiato il suo stato in : "+stato);


        if (messaggio.InsertSQL()){

            //aggiorno la lista dei mesaggi locale

            ArrayList<String> BROADCAST = Utente.getBROADCAST();
            BROADCAST.add("< "+Utente.getNome()+" "+Utente.getCognome()+" > : "+
                    "Ha cambiato il suo stato in : "+stato);
            Dview.setBroadcast(BROADCAST);
            controllo = true;

        }


        return controllo;
    }
}

