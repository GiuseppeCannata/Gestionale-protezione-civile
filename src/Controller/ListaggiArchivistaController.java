package Controller;


import Model.ArchivistaModel;
import Model.Volontario;
import View.ListaggiArchivistaView;
import Model.Candidato;
import View.BasicFrameView;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class ListaggiArchivistaController {


    private BasicFrameView basicframe;
    private ListaggiArchivistaView listaggiArchivistaView;
    private ArchivistaModel Archivistamodel;
    private JComboBox Box;
    private String utilizzatore;

    private ArrayList<Candidato> CANDIDATI;
    private ArrayList<Volontario> VOLONTARIO;



    /*COSTRUTTORE*/
    public ListaggiArchivistaController(BasicFrameView frame, String Utilizzatore) {

        basicframe = frame;
        listaggiArchivistaView = new ListaggiArchivistaView();
        Box = listaggiArchivistaView.getComboBox1();
        utilizzatore = Utilizzatore;

        if(utilizzatore.equals("candidato")) {

            Archivistamodel = new ArchivistaModel("candidato");
            listaggiArchivistaView.setLabel("Lista candidati");
            CANDIDATI = Archivistamodel.getListcandidati();

        }else if(utilizzatore.equals("volontario")){

            Archivistamodel = new ArchivistaModel("volontario");
            VOLONTARIO = Archivistamodel.getListvolontari();
            listaggiArchivistaView.setLabel("Lista volontarii");
            listaggiArchivistaView.VisibilitaAccettaButton(false);


        }

        basicframe.setdestra(listaggiArchivistaView.getIntermedio0());
        stampalista();
        Listener();

    }


    public void stampalista () {

        if(utilizzatore.equals("candidato"))
        for(Candidato candidato : CANDIDATI)
            Box.addItem(candidato.getCognome() + "    -    " +candidato.getNome());

        else if(utilizzatore.equals("volontario"))
            for(Volontario volontario : VOLONTARIO)
                Box.addItem(volontario.getCognome() + "    -    " +volontario.getNome());

    }

    /**
     * Ascolto azioni dell utente
     */
    private void Listener(){

        /*Ritorna ai compiti*/
        JButton ritornaAiCompitiDaArchivista = listaggiArchivistaView.getRitornaAiCompitiDaButton();
        ritornaAiCompitiDaArchivista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ArchivistaController controller;
                controller = new ArchivistaController(basicframe);

            }
        });

        JButton visionaSchedaButton = listaggiArchivistaView.getVisionaSchedaButton();
        visionaSchedaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

              VisionaSchedaAction();

            }
        });

        JButton AccettaButton = listaggiArchivistaView.getAccettaButton();
        AccettaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                AccettaAction();

            }
        });

    }


    private void VisionaSchedaAction(){

        int Indice;
        Indice = Box.getSelectedIndex();

        if(utilizzatore.equals("candidato")) {

            AnagraficaController controller;
            controller = new AnagraficaController(basicframe, CANDIDATI.get(Indice), listaggiArchivistaView);

        }else if(utilizzatore.equals("volontario")){

            AnagraficaController controller;
            controller = new AnagraficaController(basicframe, VOLONTARIO.get(Indice), listaggiArchivistaView);

        }

    }

    private void  AccettaAction(){

        int Indice;
        Indice = Box.getSelectedIndex();
        String[] appoggio = new String[3];


        if(basicframe.OpotionalMessage("Confermare scheda per "+CANDIDATI.get(Indice).getNome()+" ?") == 0) {

            appoggio[0] = "pass";
            appoggio[1] = "Conf_Archivista";
            appoggio[2] = "1";

            if(CANDIDATI.get(Indice).UpdateSQL(appoggio)){

                basicframe.Message("Conferma effettuata con successo!");
                CANDIDATI.remove(Indice);
                Box.removeItemAt(Indice);

            }
        }


    }
}

