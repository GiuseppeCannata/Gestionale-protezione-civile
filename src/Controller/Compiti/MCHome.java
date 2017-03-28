package Controller.Compiti;


import Model.Volontario;
import View.BasicFrameView;
import View.MCHomeView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller per la home del MC
 */
public class MCHome {

    private BasicFrameView basicframe;
    private MCHomeView mcHomeview;
    private Volontario utenteloggato;

    public MCHome(BasicFrameView frame, Volontario UtenteLoggato) {

        basicframe = frame;
        mcHomeview = new MCHomeView();
        utenteloggato = UtenteLoggato;
        basicframe.setdestra(mcHomeview.getIntermedio0());

        Listener();
    }

    /**
     * Ascolto le azioni dell utente
     * -->Compiti, Ruoli
     */
    private void Listener() {


        /*Compiti*/
        JButton Compiti = mcHomeview.getCompitiButton();
        Compiti.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                MC controller;
                controller = new MC(basicframe, "compiti", mcHomeview, utenteloggato);

            }
        });


        JButton Ruoli = mcHomeview.getRuoliButton();
        Ruoli.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                MC controller;
                controller = new MC(basicframe, "ruoli", mcHomeview, utenteloggato);

            }
        });
    }

    //GETTER
    public MCHomeView getMcHomeview() {

        return mcHomeview;

    }

    public BasicFrameView getBasicframe() {

        return basicframe;

    }

    public Volontario getUtenteloggato() {

        return utenteloggato;

    }

    @Override
    public String toString() {

        return "MCHome{}";

    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MCHome mcHome = (MCHome) o;

        return mcHomeview != null ? mcHomeview.equals(mcHome.mcHomeview) : mcHome.mcHomeview == null;

    }

}
