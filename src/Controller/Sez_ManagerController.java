
package Controller;

import View.BasicFrameView;
import View.Sez_ManagerView;
import View.Sez_AView;
import View.Sez_BView;
import View.Sez_CView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class Sez_ManagerController {

    BasicFrameView basicframe;
    Sez_ManagerView sez_managerview;
    LoginController logincontroller;

    public Sez_ManagerController(BasicFrameView view,String utilizzatore,LoginController Logincontroller) {

        basicframe = view;
        logincontroller = Logincontroller;
        sez_managerview = new Sez_ManagerView();

        sceltapannelli(utilizzatore);
        /*Setto il mio manager di pagine*/
        Pagine_Manager.setPagina_Corrente();
        sezmanagerListener();

    }

    /*sceltapannelli(String utilizzatore) gestisce i pannelli da inserire nella Sez_managerView*/
    private void sceltapannelli(String utilizzatore){

        if(utilizzatore.equals("Registrazione")){

            sez_managerview.setTitle(utilizzatore);
            sez_managerview.setSezA(new Sez_AView().getIntermedio0());
            sez_managerview.setSezB(new Sez_BView().getIntermedio0());
            sez_managerview.setSezC(new Sez_CView().getIntermedio0());

        }
    }

    /*sezmanagerListener() gestisce gli eventi scatenati dall utente interagendo con la Sez_managerView*/
    private void sezmanagerListener(){

        CardLayout CL=(CardLayout) sez_managerview.getIntermedio1().getLayout();

        /*Avanti*/
        JButton sez_managerviewAvanti= sez_managerview.getAvantiButton();
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


        /*Esci*/
        JButton sez_managerviewEsci=sez_managerview.getEsciButton();
        sez_managerviewEsci.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*
                 * Il metodo showConfirmDialog() ritorna un valore intero; in questo
                 * caso: 0==Si 1==No Sapendo questo sono in grado di capire la
                 * scelta dell utente e di poterla gestire di conseguenza
                 */
                int risposta;
                risposta = JOptionPane.showConfirmDialog(sez_managerview, "Sei proprio sicuro di voler uscire \n" +
                                "dalla registrazione?", "Warning", JOptionPane.YES_NO_OPTION,
                                 JOptionPane.WARNING_MESSAGE);

                /* L utente ha premuto Si per questo chiudo la sez_managerView */
                if (risposta == 0) {

                    sez_managerview.dispose();
                    /*chiudendo la sez_managerview, ho zero istanze*/
                    logincontroller.setREGISTRATI(0);

                }
            }
        });


    }

}
