package Controller.Compiti;


import View.BasicFrameView;
import View.MCHomeView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MCHomeController {

    private BasicFrameView basicframe;
    private MCHomeView mcHomeview;

    public MCHomeController(BasicFrameView frame) {

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

                MCController controller;
                controller = new MCController(basicframe, "compiti");

            }
        });


        JButton Ruoli = mcHomeview.getRuoliButton();
        Ruoli.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                MCController controller;
                controller = new MCController(basicframe, "ruoli");

            }
        });


    }

}
