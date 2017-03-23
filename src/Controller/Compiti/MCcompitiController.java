package Controller.Compiti;

import Model.GestioneModel;
import View.BasicFrameView;
import View.MCcompitiView;

import javax.swing.*;


public class MCcompitiController {

    private BasicFrameView basicframe;
    private GestioneModel model;
    private MCcompitiView view;


    public MCcompitiController(BasicFrameView frame) {

        basicframe = frame;
        view = new MCcompitiView();

        model = new GestioneModel("ciao");

        basicframe.setdestra(view.getIntermedio0());

        Listener();

    }


    private void Listener(){

        JButton Assegnacomiti =  view.getAssegnaCompitoButton();




    }



}
