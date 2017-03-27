package Controller.Compiti;


import Model.Volontario;
import View.BasicFrameView;
import View.MCHomeView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public MCHomeView getMcHomeview() {
        return mcHomeview;
    }

    public BasicFrameView getBasicframe() {
        return basicframe;
    }

    public Volontario getUtenteloggato() {
        return utenteloggato;
    }
}
