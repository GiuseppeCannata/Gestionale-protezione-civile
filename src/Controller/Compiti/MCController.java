package Controller.Compiti;


import View.BasicFrameView;
import View.MCHomeView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MCController {

    private BasicFrameView basicframe;
    private MCHomeView mcHomeview;

    public MCController(BasicFrameView frame) {

        basicframe = frame;
        mcHomeview = new MCHomeView();
        basicframe.setdestra(mcHomeview.getIntermedio0());

        Listener();
    }

    private void Listener() {


        /*Daicompiti*/
        JButton Daicompiti = mcHomeview.getDaiCompitiButton();
        Daicompiti.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                MCcompitiController controller;
                controller = new MCcompitiController(basicframe);

            }
        });




}

}
