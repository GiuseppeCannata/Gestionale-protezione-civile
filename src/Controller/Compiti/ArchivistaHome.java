package Controller.Compiti;


import Model.Volontario;
import View.BasicFrameView;
import View.ArchivistaHomeView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe che rappresenta i compiti dell Archivista
 */

public class ArchivistaHome {

    private BasicFrameView basicframe;
    private ArchivistaHomeView archivistaHomeView;
    private Volontario UtenteLoggato;

   /*costruttore*/
    public ArchivistaHome() {

        return;

    }

    public ArchivistaHome(BasicFrameView frame, Volontario Utenteloggato) {

        basicframe = frame;
        UtenteLoggato=Utenteloggato;

        archivistaHomeView = new ArchivistaHomeView();
        basicframe.setdestra(archivistaHomeView.getIntermedio0());

        ArchivistaHomeContriollerListener();

    }

    /**
     * Ascolto azioni dell utente
     * -->Listacandidati,TuttiIVolontari
     */
    private void ArchivistaHomeContriollerListener() {


        /*listacandidatibutton*/
        JButton listaCandidatiButton = archivistaHomeView.getCandidatiButtonButton();
        listaCandidatiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ListaggiController controller;
                controller = new ListaggiController(basicframe, "listacandidati", UtenteLoggato);

            }
        });

        /*TUTTIIVOLONTARI*/
        JButton tuttiIvolonari = archivistaHomeView.gettuttiIVolontariButton();
        tuttiIvolonari.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ListaggiController controller;
                controller = new ListaggiController(basicframe, "listavolontari", UtenteLoggato);

            }
        });



    }

    @Override
    public String toString() {
        return "ArchivistaHome{}";
    }
}


