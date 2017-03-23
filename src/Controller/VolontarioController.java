package Controller;


import Controller.Compiti.ArchivistaController;
import Controller.Compiti.MCController;
import Controller.Compiti.ReferenteinformaticoController;
import Model.Volontario;
import View.BasicFrameView;
import View.UtenteSinistraView;
import View.VolontarioDView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class VolontarioController{

    private BasicFrameView basicframe;
    private VolontarioDView Dview;
    private UtenteSinistraView Sview;
    private Volontario Utente;

    private int DatiPersonali;

    public VolontarioController(BasicFrameView frame, Volontario utente){

        basicframe = frame;
        DatiPersonali = 0;
        Utente = utente;
        Dview = new VolontarioDView();
        Sview = new UtenteSinistraView();
        Sview.setEvolviButton(false);
        basicframe.setdestra(Dview.getIntermedio0());


        if(Utente.getPrimoaccesso().equals("si")) {
            new AnagraficaController(basicframe, Dview, utente);
            DatiPersonali = 1; //senno si puo aprire questa sezione
            basicframe.Message("Ciao sei appena diventato un Volontario!\nPer completa la tua iscrizione " +
                    "completa la sezione D di seguito riportata");

            DatiPersonali= 0;
        }

        basicframe.setsinistra(Sview.getIntermedio0());
        Dview.setNOMEVOLabel(Utente.getNome());
        Dview.setCOGNOMEVOLabel(Utente.getCognome());
        Dview.setSTATOLabel(Utente.getStato());

        VolontarioControllerListener();

        ArchivistaListner();
        ReferenteInformaticoListner();
        MasterChiefListner();

    }

    private void VolontarioControllerListener(){

        /*DatiPersonali*/
        JButton DatiPersonaliButton = Sview.getDatiPersonaliButton();
        DatiPersonaliButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (DatiPersonali == 0){

                    DatiPersonali = 1;

                    AnagraficaController datipersonali;
                    datipersonali = new AnagraficaController(basicframe, Dview, Utente, VolontarioController.this);
                }

            }

        });



        /*Logout*/
        JButton Logout = Sview.getLogoutButton();
        Logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                LogoutAction();
            }

        });

        JButton cambiastato = Dview.getCambiaButton();
        cambiastato.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                CambiaStatoController controller;
                controller = new CambiaStatoController(basicframe , Utente, Dview);

            }

        });

    }

    private void ArchivistaListner(){

        JButton Archivista = Dview.getArchivistaButton();
        Archivista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ArchivistaController controller;
                controller = new ArchivistaController(basicframe);

            }

        });
    }

    private void ReferenteInformaticoListner() {

        JButton ReferenteInformatico = Dview.getReferenteInformaticoButton();
        ReferenteInformatico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ReferenteinformaticoController controller;
                controller = new ReferenteinformaticoController(basicframe);

            }

        });
    }

    private void MasterChiefListner(){

        JButton MasterChief = Dview.getMaster_ChiefButton();
        MasterChief.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                MCController controller;
                controller = new MCController(basicframe);

            }

        });
    }

    private void LogoutAction(){

        if(basicframe.OpotionalMessage("Vuoi davvero uscire?") == 0) {
            LoginController loginController = new LoginController(basicframe);
        }

    }

    public void setDatiPersonali(int datiPersonali) {

        DatiPersonali = datiPersonali;

    }
}
