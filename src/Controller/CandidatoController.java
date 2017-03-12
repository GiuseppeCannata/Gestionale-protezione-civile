package Controller;


import View.BasicFrameView;
import View.CandidatoDestraView;
import View.CandidatoSinistraView;
import Model.Candidato;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * CandidatoController --> Controller per la CandidatoView
 * Classe pubblica
 */
public class CandidatoController {

   private BasicFrameView basicframe;
   private Candidato Utente;
   private CandidatoDestraView Dview;
   private CandidatoSinistraView Sview;

    public CandidatoController(BasicFrameView frame, Candidato utente) {

        basicframe = frame;
        Utente = utente;
        Dview = new CandidatoDestraView();
        Sview = new CandidatoSinistraView();
        //Settaggio della basicframe con inserimento dei due pannelli a destra e sinistra
        basicframe.setdestra(Dview.getIntermedio0());
        basicframe.setsinistra(Sview.getIntermedio0());

        CandidatoControllerListener();

    }

    /**
     * Ascolto azioni dell utente
     */
    private void  CandidatoControllerListener(){

        /*DatiPersonali*/
        JButton DatiPersonaliButton = Sview.getDatiPersonaliButton();
        DatiPersonaliButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

               /* Sez_ManagerController sez_managerController ;
                sez_managerController = new Sez_ManagerController(basicframe, Dview,"Candidato");*/

            }

        });

        /*Evolvi*/
        JButton Evolvi = Sview.getEvolviButton();
        Evolvi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }

        });

        /*Aiuto*/
        JButton Aiuto = Sview.getAiutoButton();
        Aiuto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }

        });
    }

    @Override
    public String toString() {

        return "Sono Candidato Controller e mi occupo della gestione delle azioni scatenate dall utente interagendo con" +
                "la CandidatoDestraView e CandidatoSinistraView";

    }
}
