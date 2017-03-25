package Controller.Compiti;


import View.BasicFrameView;
import View.ArchivistaHomeView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ArchivistaHome {

    private BasicFrameView basicframe;
    private ArchivistaHomeView archivistaHomeView;

/*costruttore*/

    public ArchivistaHome(BasicFrameView frame) {

        basicframe = frame;
        archivistaHomeView = new ArchivistaHomeView();
        basicframe.setdestra(archivistaHomeView.getIntermedio0());

        ArchivistaHomeContriollerListener();

    }

    /**
     * Ascolto azioni dell utente
     */

    public void ArchivistaHomeContriollerListener() {


        /*listacandidatibutton*/
        JButton listaCandidatiButton = archivistaHomeView.getCandidatiButtonButton();
        listaCandidatiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ListaggiController controller;
                controller = new ListaggiController(basicframe, "listacandidati");

            }
        });

        /*TUTTIIVOLONTARI*/
        JButton tuttiIvolonari = archivistaHomeView.gettuttiIVolontariButton();
        tuttiIvolonari.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ListaggiController controller;
                controller = new ListaggiController(basicframe, "listavolontari");

            }
        });



    }




}


