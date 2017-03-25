package Controller;


import Controller.AnagraficaController;
import Controller.Compiti.ArchivistaHome;
import Model.GestioneModel;
import Model.Persona;
import View.ListaggiView;
import View.BasicFrameView;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class ListaggiController {


    private BasicFrameView basicframe;
    private ListaggiView view;
    private GestioneModel gestione;
    private JComboBox Box;
    private String utilizzatore;
    private String appoggio;

    private ArrayList<Persona> UTENTI;


    /*COSTRUTTORE*/
    public ListaggiController(BasicFrameView frame, String Utilizzatore) {

        basicframe = frame;

        view = new ListaggiView();

        utilizzatore = Utilizzatore;

        SettaggioView();

       // view.setLabel("Lista "+utilizzatore);

        gestione = new GestioneModel(appoggio);
        UTENTI = gestione.Schede(Utilizzatore);

        basicframe.setdestra(view.getIntermedio0());
        stampalista();

    }

   private void SettaggioView(){

        switch(utilizzatore){

            case "listacandidati":{

                view.VisibilitaAccettaButton(true);
                view.VisibilitaVisionaSchedaButton(true);
                view.VisibilitaRitornaButton(true);
                appoggio = "vol_o_cand=0 and Conf_Archivista=0";
                RitornaAiCompitiDaArchivistaListener();
                VisionaSchedaListener();
                AccettaListener();
                break;


            }

            case "listavolontari":{

                view.VisibilitaVisionaSchedaButton(true);
                view.VisibilitaRitornaButton(true);
                appoggio = "vol_o_cand=1";
                VisionaSchedaListener();
                RitornaAiCompitiDaArchivistaListener();
                break;

            }

        }

   }


    public void stampalista() {

        Box = view.getBox1();

            for (Persona candidato : UTENTI)
                Box.addItem(candidato.getCognome() + "    -    " + candidato.getNome());

    }

    /**
     * Ascolto azioni dell utente
     */
    private void RitornaAiCompitiDaArchivistaListener() {

        /*Ritorna ai compiti*/
        JButton ritornaAiCompitiDaArchivista = view.getRitornaButton();
        ritornaAiCompitiDaArchivista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ArchivistaHome controller;
                controller = new ArchivistaHome(basicframe);

            }
        });
    }

    private void VisionaSchedaListener() {

        JButton visionaSchedaButton = view.getVisionaSchedaButton();
        visionaSchedaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                VisionaSchedaAction();

            }
        });
    }

    private void AccettaListener(){

        JButton AccettaButton = view.getAccettaButton();
        AccettaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                AccettaArchivistaAction();

            }
        });

    }


    private void VisionaSchedaAction() {

        int Indice;
        Indice = Box.getSelectedIndex();

            AnagraficaController controller;
            controller = new AnagraficaController(basicframe, UTENTI.get(Indice), view, utilizzatore);



    }

    private void AccettaArchivistaAction() {

        int Indice;
        Indice = Box.getSelectedIndex();
        String[] appoggio = new String[3];


        if (basicframe.OpotionalMessage("Confermare scheda per " + UTENTI.get(Indice).getNome() + " ?") == 0) {

            appoggio[0] = "pass";
            appoggio[1] = "Conf_Archivista";
            appoggio[2] = "1";

            if (UTENTI.get(Indice).UpdateSQL(appoggio)) {

                basicframe.Message("Conferma effettuata con successo!");
                UTENTI.remove(Indice);
                Box.removeItemAt(Indice);

            }
        }


    }
}

