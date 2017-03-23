package Controller.Compiti;


import Controller.ListaggiController;
import View.BasicFrameView;
import View.ArchivistaHomeView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ArchivistaController {

    private BasicFrameView basicframe;
    private ArchivistaHomeView archivistaHomeView;

/*costruttore*/

    public ArchivistaController(BasicFrameView frame) {

        basicframe = frame;
        archivistaHomeView = new ArchivistaHomeView();
        basicframe.setdestra(archivistaHomeView.getIntermedio0());

        ArchivistaContriollerListener();

    }

    /**
     * Ascolto azioni dell utente
     */

    public void ArchivistaContriollerListener() {


        /*listacandidatibutton*/
        JButton listaCandidatiButton = archivistaHomeView.getCandidatiButtonButton();
        listaCandidatiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ListaggiController controller;
                controller = new ListaggiController(basicframe, "candidato");

            }
        });

        /*TUTTI I VOLONTARI*/
        JButton tuttiIvolonari = archivistaHomeView.gettuttiIVolontariButton();
        tuttiIvolonari.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ListaggiController controller;
                controller = new ListaggiController(basicframe, "volontario");

            }
        });



    }




}


