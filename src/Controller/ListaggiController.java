package Controller;


import Controller.Compiti.ArchivistaController;
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

        view.VisibilitaVisionaSchedaButton(true);
        view.VisibilitaRitornaButton(true);

        if(utilizzatore.equals("candidato")){

           view.VisibilitaAccettaButton(true);
           appoggio = "vol_o_cand=0 and Conf_Archivista=0";


        }else if(utilizzatore.equals("volontario"))
            appoggio = "vol_o_cand=1";

        view.setLabel("Lista "+utilizzatore);

        gestione = new GestioneModel(appoggio);
        UTENTI = gestione.Schede(Utilizzatore);

        basicframe.setdestra(view.getIntermedio0());
        stampalista();
        Listener();

    }


    public void stampalista() {

        Box = view.getBox1();

            for (Persona candidato : UTENTI)
                Box.addItem(candidato.getCognome() + "    -    " + candidato.getNome());

    }

    /**
     * Ascolto azioni dell utente
     */
    private void Listener() {

        /*Ritorna ai compiti*/
        JButton ritornaAiCompitiDaArchivista = view.getRitornaButton();
        ritornaAiCompitiDaArchivista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ArchivistaController controller;
                controller = new ArchivistaController(basicframe);

            }
        });

        JButton visionaSchedaButton = view.getVisionaSchedaButton();
        visionaSchedaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                VisionaSchedaAction();

            }
        });

        JButton AccettaButton = view.getAccettaButton();
        AccettaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                AccettaAction();

            }
        });

    }


    private void VisionaSchedaAction() {

        int Indice;
        Indice = Box.getSelectedIndex();

            AnagraficaController controller;
            controller = new AnagraficaController(basicframe, UTENTI.get(Indice), view, utilizzatore);



    }

    private void AccettaAction() {

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

