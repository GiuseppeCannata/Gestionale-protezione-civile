package Controller;


import Model.Volontario;
import View.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



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

                stato = (String) CambiaStatoview.getBox().getSelectedItem();
                String[] appoggio = new String[3];

                appoggio[0] = "flagvolontario";
                appoggio[1] = "stato";
                appoggio[2] =  stato;

                Utente.setStato(stato);
                Dview.setSTATOLabel(stato);

               if(Utente.UpdateSQL(appoggio))
                   basicframe.Message("Il suo stato è mutato in: "+stato+" ");

            }
        });


    }
}

