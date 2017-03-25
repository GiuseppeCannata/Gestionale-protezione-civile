package Controller.Compiti;


import View.BasicFrameView;
import View.MCHomeView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MCHome {

    private BasicFrameView basicframe;
    private MCHomeView mcHomeview;

    public MCHome(BasicFrameView frame) {

        basicframe = frame;
        mcHomeview = new MCHomeView();
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
                controller = new MC(basicframe, "compiti", mcHomeview);

            }
        });


        JButton Ruoli = mcHomeview.getRuoliButton();
        Ruoli.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                MC controller;
                controller = new MC(basicframe, "ruoli", mcHomeview);

            }
        });


    }

}
