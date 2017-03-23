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
    private ListaggiView listaggiView;
    private GestioneModel archivistamodel;
    private JComboBox Box;
    private String utilizzatore;

    private ArrayList<Persona> UTENTI;


    /*COSTRUTTORE*/
    public ListaggiController(BasicFrameView frame, String Utilizzatore) {

        basicframe = frame;

        listaggiView = new ListaggiView();
        listaggiView.VisibilitaResettaPasswordButton(false);

        Box = listaggiView.getBox1();
        utilizzatore = Utilizzatore;

        archivistamodel = new GestioneModel(utilizzatore);
        listaggiView.setLabel("Lista "+utilizzatore);

        UTENTI = archivistamodel.getListutenti();

        if(utilizzatore.equals("volontario"))
        listaggiView.VisibilitaAccettaButton(false);


        basicframe.setdestra(listaggiView.getIntermedio0());
        stampalista();
        Listener();

    }


    public void stampalista() {

            for (Persona candidato : UTENTI)
                Box.addItem(candidato.getCognome() + "    -    " + candidato.getNome());

    }

    /**
     * Ascolto azioni dell utente
     */
    private void Listener() {

        /*Ritorna ai compiti*/
        JButton ritornaAiCompitiDaArchivista = listaggiView.getRitornaButton();
        ritornaAiCompitiDaArchivista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ArchivistaController controller;
                controller = new ArchivistaController(basicframe);

            }
        });

        JButton visionaSchedaButton = listaggiView.getVisionaSchedaButton();
        visionaSchedaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                VisionaSchedaAction();

            }
        });

        JButton AccettaButton = listaggiView.getAccettaButton();
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
            controller = new AnagraficaController(basicframe, UTENTI.get(Indice), listaggiView, utilizzatore);



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

