package Controller.Compiti;

import Model.GestioneModel;
import Model.Persona;
import Model.Volontario;
import View.BasicFrameView;
import View.MCHomeView;
import View.MCView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;


public class MC {

    private BasicFrameView basicframe;
    private GestioneModel model;
    private String appoggio;
    private JComboBox Box;
    private MCView view;
    private JComboBox Box2;
    private MCHomeView homeView;
    private String utilizzatore;
    private ArrayList<Persona> UTENTI;


    public MC(BasicFrameView frame, String Utilizzatore, MCHomeView HomeView) {

        basicframe = frame;
        view = new MCView();
        homeView = HomeView;
        appoggio = "vol_o_cand = 1";

        utilizzatore = Utilizzatore;
        Box2 = view.getBox2();

        model = new GestioneModel(appoggio);

        settaggioview();

        stampalista();

        basicframe.setdestra(view.getIntermedio0());

        Listener();

    }

    private void settaggioview(){

        switch (utilizzatore){

            case "compiti" :{

                view.VisibilitaAssegnaCompito(true);
                view.VisibilitaRimuoviCompito(true);

                Box2.removeAllItems();
                Box2.addItem("Archivista");
                Box2.addItem("Referente_Informatico");
                Box2.addItem("Magazzino_Divise");
                Box2.addItem("Add_Giunta");

                UTENTI = model.Compiti();

                listCompitiListner();
                break;
            }

            case "ruoli" :{

                view.VisibilitaRimuoviRuolo(true);
                view.VisibilitaAssegnaRuolo(true);

                Box2.removeAllItems();
                Box2.addItem("Volontario_semplice");
                Box2.addItem("Direttivo");
                Box2.addItem("Vicecordinatore");
                Box2.addItem("Cordinatore");

                UTENTI = model.Ruoli();

                listRuoliListner();
                break;
            }

        }

    }

    private void Listener(){


        JComboBox Box1 = view.getBox1();

         /*Assegnacompito*/
        JButton Assegnacompito = view.getAssegnaCompitoButton();
        Assegnacompito.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String compito = (String) Box2.getSelectedItem();
                for (Persona utente : UTENTI)
                    if (Box1.getSelectedItem().equals(utente.getCognome() + "    -    " + utente.getNome())) {
                        String[] app = new String[3];

                        app[0] = "compiti";
                        app[1] = compito;
                        app[2] = "si";

                        if (utente.UpdateSQL(app)) {
                            basicframe.Message("Assegnato!");
                             new MC(basicframe, "compiti", homeView);
                        }
                    }


            }
        });

        JButton Rimuovicompito = view.getRimuoviCompitoButton();
        Rimuovicompito.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String compito = (String) Box2.getSelectedItem();
                for (Persona utente : UTENTI)
                    if (Box1.getSelectedItem().equals(utente.getCognome() + "    -    " + utente.getNome())) {
                        String[] app = new String[3];

                        app[0] = "compiti";
                        app[1] = compito;
                        app[2] = "no";

                        if (utente.UpdateSQL(app)) {
                            basicframe.Message("Eliminato!");
                            new MC(basicframe, "compiti", homeView);
                        }
                    }


            }
        });

        JButton Assegnaruolo = view.getAssegnaRuoloButton();
        Assegnaruolo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String ruolo = (String) Box2.getSelectedItem();
                for (Persona utente : UTENTI)
                    if (Box1.getSelectedItem().equals(utente.getCognome() + "    -    " + utente.getNome())) {
                        String[] app = new String[3];

                        app[0] = "flagvolontario";
                        app[1] = "ruolo";
                        app[2] =  ruolo;

                        if (utente.UpdateSQL(app)) {
                            basicframe.Message("Assegnato!");
                            new MC(basicframe, "ruoli", homeView);
                        }
                    }


            }
        });

        JButton Rimuoviruolo = view.getRimuoviRuoloButton();
        Rimuoviruolo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String compito = (String) Box2.getSelectedItem();
                for (Persona utente : UTENTI)
                    if (Box1.getSelectedItem().equals(utente.getCognome() + "    -    " + utente.getNome())) {
                        String[] app = new String[3];

                        app[0] = "flagvolontario";
                        app[1] = "ruolo";
                        app[2] =  " ";

                        if (utente.UpdateSQL(app)) {
                            basicframe.Message("Eliminato!");
                            new MC(basicframe, "ruoli", homeView);
                        }
                    }

            }
        });

        JButton RitornaButton = view.getRitornaButton();
        RitornaButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                basicframe.setdestra(homeView.getIntermedio0());

            }
        });


    }

    private void listCompitiListner(){

        JComboBox Box1 = view.getBox1();

        JList list = view.getLista();
        Box1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                if(e.getSource() ==  Box1) {


                    for (Persona utente : UTENTI) {

                        if (Box1.getSelectedItem().equals(utente.getCognome() + "    -    " + utente.getNome())) {

                            Volontario Utente = (Volontario) utente;
                            String[] compiti = new String[]{"Referente_Informatico : " + Utente.getReferenteinformatico(),
                                    "Add_Giunta : " + Utente.getAdd_giunta(),
                                    "Archivista : " + Utente.getArchivista()};

                            list.setListData(compiti);
                        }
                    }

                }


            }
        });
    }

    private void listRuoliListner(){

        JComboBox Box1 = view.getBox1();

        JList list = view.getLista();
        Box1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                if(e.getSource() ==  Box1) {


                    for (Persona utente : UTENTI) {

                        if (Box1.getSelectedItem().equals(utente.getCognome() + "    -    " + utente.getNome())) {

                            Volontario Utente = (Volontario) utente;
                            System.out.println(Utente.getRuolo());
                            String[] ruolo = new String[]{Utente.getRuolo()};

                            list.setListData(ruolo);
                        }
                    }

                }


            }
        });
    }

    public void stampalista() {

        Box = view.getBox1();

        for (Persona utente : UTENTI)
            Box.addItem(utente.getCognome() + "    -    " + utente.getNome());

    }
}
