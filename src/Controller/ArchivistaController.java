package Controller;


import View.BasicFrameView;
import View.ArchivistaView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ArchivistaController {

    private BasicFrameView basicframe;
    private ArchivistaView archivistaView;

/*costruttore*/

    public ArchivistaController(BasicFrameView frame) {

        basicframe = frame;
        archivistaView = new ArchivistaView();
        basicframe.setdestra(archivistaView.getIntermedio0());

        ArchivistaContriollerListener();

    }

    /**
     * Ascolto azioni dell utente
     */

    public void ArchivistaContriollerListener() {


        /*listacandidatibutton*/
        JButton listaCandidatiButton = archivistaView.getCandidatiButtonButton();
        listaCandidatiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ListaggiArchivistaController controller;
                controller = new ListaggiArchivistaController(basicframe, "candidato");

            }
        });

        JButton tuttiIvolonari = archivistaView.gettuttiIVolontariButton();
        tuttiIvolonari.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ListaggiArchivistaController controller;
                controller = new ListaggiArchivistaController(basicframe, "volontario");

            }
        });



    }




}


