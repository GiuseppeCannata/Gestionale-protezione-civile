package Controller.Compiti;


import Controller.AnagraficaController;
import Controller.MessaggioController;
import Model.GestioneModel;
import Model.Persona;
import Model.Volontario;
import View.BasicFrameView;
import View.ArchivistaHomeView;
import View.ListaggiView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

/**
 * Classe che rappresenta i compiti dell Archivista
 */

public class Archivista {

    private BasicFrameView basicframe;
    private ArchivistaHomeView archivistaHomeView;
    private Volontario UtenteLoggato;
    private ListaggiView view;
    private JComboBox Box;
    private String appoggio;
    private GestioneModel gestione;
    private ArrayList<Persona> UTENTI;
    private String utilizzatore;

   /*costruttore*/

   /*costruttore vuoto*/
    public Archivista() {

        return;

    }

    public Archivista(BasicFrameView frame, Volontario Utenteloggato) {

        basicframe = frame;
        UtenteLoggato=Utenteloggato;

        archivistaHomeView = new ArchivistaHomeView();
        basicframe.setdestra(archivistaHomeView.getIntermedio0());

        ArchivistaHomeContriollerListener();

    }

    /**
     * Ascolto azioni dell utente
     * Listacandidati,TuttiIVolontari
     */
    private void ArchivistaHomeContriollerListener() {


        /*listacandidatibutton*/
        JButton listaCandidatiButton = archivistaHomeView.getCandidatiButtonButton();
        listaCandidatiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                view = new ListaggiView();
                Box = view.getBox1();

                view.VisibilitaAccettaButton(true);
                view.VisibilitaVisionaSchedaButton(true);
                view.VisibilitaRitornaButton(true);
                view.VisibilitaInviomessaggio(true);
                view.setLabel("Lista candidati");
                appoggio = "vol_o_cand=0 and Conf_Archivista=0 and conf_giunta=1";
                gestione = new GestioneModel(appoggio);
                utilizzatore =  "listacandidati";
                UTENTI = gestione.Schede(utilizzatore);

                basicframe.setdestra(view.getIntermedio0());
                RitornaAiCompitiDaArchivistaListener();
                VisionaSchedaListener();
                AccettaListener();
                InvioMessaggioListener();
                stampalista();

            }
        });

        /*TUTTIIVOLONTARI*/
        JButton tuttiIvolonari = archivistaHomeView.gettuttiIVolontariButton();
        tuttiIvolonari.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                view = new ListaggiView();
                Box = view.getBox1();

                view.VisibilitaRitornaButton(true);
                view.VisibilitaVisionaSchedaButton(true);
                // view.VisibilitaInviomessaggio(true);
                view.setLabel("Lista volontari");
                view.VisibilitaStatoLabel(true);
                view.VisibilitaText(true);
                appoggio = "vol_o_cand=1";
                gestione = new GestioneModel(appoggio);
                utilizzatore = "listavolontari";
                UTENTI = gestione.Schede(utilizzatore);
                basicframe.setdestra(view.getIntermedio0());
                VisionaSchedaListener();
                RitornaAiCompitiDaArchivistaListener();
                Boxlistener();
                stampalista();

            }
        });



    }

    /**
     * Metodo che permette di stampare la lista degli utenti nella combobox
     */

    public void stampalista() {

        for (Persona utente : UTENTI) {

            if (!utente.getCodice_Fiscale().equals(UtenteLoggato.getCodice_Fiscale()))
                Box.addItem(utente.getCognome() + "    -    " + utente.getNome());
        }



    }

    /**
     * Ascolto azioni dell utente
     * Botton:Ritornaaicompiti
     */
    private void RitornaAiCompitiDaArchivistaListener() {

        /*Ritorna ai compiti*/
        JButton ritornaAiCompitiDaArchivista = view.getRitornaButton();
        ritornaAiCompitiDaArchivista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Archivista controller;
                controller = new Archivista(basicframe, UtenteLoggato);

            }
        });
    }

    /**
     * Ascolto azioni dell utente
     * Botton:VisionaScheda
     */

    private void VisionaSchedaListener() {

        JButton visionaSchedaButton = view.getVisionaSchedaButton();
        visionaSchedaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.print(Box.getSelectedItem());
                if(Box.getSelectedItem() != null)
                    VisionaSchedaAction();

            }
        });

    }

    /**
     * Ascolto azioni dell utente
     * Botton:Accetta
     */
    private void AccettaListener(){


        JButton AccettaButton = view.getAccettaButton();
        AccettaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(Box.getSelectedItem() != null)
                    AccettaArchivistaAction();

            }
        });

    }

    /**
     * Ascolto azioni dell utente
     * Botton:Inviomessagio
     */

    private void InvioMessaggioListener(){


        JButton InvioMessaggio = view.getInviagliUnMessaggioButton();
        InvioMessaggio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(Box.getSelectedItem() != null){

                    int Indice;
                    Indice = Box.getSelectedIndex();

                    MessaggioController controller;
                    controller = new MessaggioController(basicframe, UTENTI.get(Indice).getCodice_Fiscale(), UtenteLoggato.getNome() + " " + UtenteLoggato.getCognome());

                }
            }
        });

    }


    /**
     * Metodo che permette di Visionare la scheda dell utente.
     * Richiama AnagraficaController
     */
    private void VisionaSchedaAction() {

        int Indice;
        Indice = Box.getSelectedIndex();

        AnagraficaController controller;
        controller = new AnagraficaController(basicframe, UTENTI.get(Indice), view, utilizzatore);

    }

    /**
     * Listener per la combobox.
     * In base all utente selezionato, verra visualizzato il suo stato settanto la sezione text della view
     */
    private void Boxlistener(){


        Box.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                if (e.getSource() == view.getBox1()) {

                    int Indice =  Box.getSelectedIndex();

                    Volontario volontario = (Volontario) UTENTI.get(Indice);

                    view.setText(volontario.getStato());


                }

            }
        });
    }

    /**
     * Metodo per la conferma della scheda dell utente
     */
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

    @Override
    public String toString() {
        return "Archivista{" +
                "basicframe=" + basicframe +
                ", archivistaHomeView=" + archivistaHomeView +
                ", UtenteLoggato=" + UtenteLoggato +
                ", view=" + view +
                ", Box=" + Box +
                ", appoggio='" + appoggio + '\'' +
                ", gestione=" + gestione +
                ", UTENTI=" + UTENTI +
                ", utilizzatore='" + utilizzatore + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Archivista that = (Archivista) o;

        return archivistaHomeView != null ? archivistaHomeView.equals(that.archivistaHomeView) : that.archivistaHomeView == null;

    }

}


