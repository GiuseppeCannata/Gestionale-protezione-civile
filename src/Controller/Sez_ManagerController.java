package Controller;

import View.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Sez_ManagerController {

   public BasicFrameView basicframe;
   public Sez_ManagerView sez_managerview;
   public LoginView loginview;

    /*COSTRUTTORE*/
    public Sez_ManagerController(BasicFrameView frame, LoginView view,String utilizzatore) {

        basicframe = frame;
        loginview = view;
        sez_managerview = new Sez_ManagerView();
        sceltapannelli(utilizzatore);
        //Setto il mio manager di pagine
        Pagine_Manager.setPagina_Corrente();
        basicframe.setdestra(sez_managerview.getIntermedio0());

        sezmanagerListener();

    }

    /*
     *sceltapannelli gestisce i pannelli da inserire nella Sez_managerView
     */
    private void sceltapannelli(String utilizzatore){

        if(utilizzatore.equals("Registrazione")){

            sez_managerview.setSezA(new Sez_AView().getIntermedio0());
            sez_managerview.setSezB(new Sez_BView().getIntermedio0());
            sez_managerview.setSezC(new Sez_CView().getIntermedio0());

        }

    }

    /*
     *sezmanagerListener() gestisce gli eventi scatenati dall utente interagendo con la Sez_managerView
     */
    private void sezmanagerListener(){

        CardLayout CL=(CardLayout) sez_managerview.getIntermedio1().getLayout();

        /*Avanti*/
        JButton sez_managerviewAvanti = sez_managerview.getAvantiButton();
        sez_managerviewAvanti.addActionListener(new ActionListener() {
                @Override
                    public void actionPerformed(ActionEvent e) {

                        if (Pagine_Manager.getPagina_Corrente() < 3) {

                            CL.next(sez_managerview.getIntermedio1());
                            Pagine_Manager.addPagina_Corrente();

                        }

                    }

        });


        /*Indietro*/
        JButton sez_managerviewIndietro= sez_managerview.getIndietroButton();
        sez_managerviewIndietro.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    if (Pagine_Manager.getPagina_Corrente() > 1) {

                        CL.previous(sez_managerview.getIntermedio1());
                        Pagine_Manager.subctractPagina_Corrente();
                    }

                }
        });


        /*PaginaLogin*/
        JButton PaginaLoginbutton = sez_managerview.getPaginaLoginButton();
        PaginaLoginbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                basicframe.setdestra(loginview.getIntermedio0());

            }
        });
    }
}
