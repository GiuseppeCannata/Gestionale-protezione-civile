package Controller;


import View.BasicFrameView;
import View.CandidatoDestraView;
import View.CandidatoSinistraView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CandidatoController {

    BasicFrameView basicframe;
    CandidatoDestraView Dview;
    CandidatoSinistraView Sview;

    public CandidatoController(BasicFrameView frame) {

        basicframe = frame;
        Dview = new CandidatoDestraView();
        Sview = new CandidatoSinistraView();
        basicframe.setdestra(Dview.getIntermedio0());
        basicframe.setsinistra(Sview.getIntermedio0());

        CandidatoControllerListener();

    }

    private void  CandidatoControllerListener(){

        /*DatiPersonali*/
        JButton DatiPersonaliButton = Sview.getDatiPersonaliButton();
        DatiPersonaliButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sez_ManagerController sez_managerController ;
                sez_managerController = new Sez_ManagerController(basicframe,Dview,"Persona");
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
}
