package Controller.Compiti;

import Model.GestioneModel;
import Model.Persona;
import Model.Volontario;
import View.BasicFrameView;
import View.MCcompitiView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;


public class MCcompitiController {

    private BasicFrameView basicframe;
    private GestioneModel model;
    private String appoggio;
    private JComboBox Box;
    private MCcompitiView view;
    private int Indice;
    private ArrayList<Persona> UTENTI;


    public MCcompitiController(BasicFrameView frame) {

        basicframe = frame;
        view = new MCcompitiView();
        appoggio = "vol_o_cand = 1";

        Indice =0;

        model = new GestioneModel(appoggio);
        UTENTI = model.Compiti();

        stampalista();

        basicframe.setdestra(view.getIntermedio0());

        Listener();

    }

    public void stampalista() {

        Box = view.getBox1();

        for (Persona utente : UTENTI)
            Box.addItem(utente.getCognome() + "    -    " + utente.getNome());

    }

    private void Listener(){

        /*Assegnacompito*/
        JComboBox Box2 = view.getBox2();
        JComboBox Box1 = view.getBox1();

        JButton Assegnacompiti = view.getAssegnaCompitoButton();
        Assegnacompiti.addActionListener(new ActionListener() {

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
                             new MCcompitiController(basicframe);
                        }
                    }


            }
        });


        JList list = view.getLista();
        Box1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                if(e.getSource() ==  Box1) {


                    for (Persona utente : UTENTI) {

                        if (Box1.getSelectedItem().equals(utente.getCognome() + "    -    " + utente.getNome())) {

                            Volontario Utente = (Volontario) utente;
                            String[] compiti = new String[]{"Referente_Informatico : " + Utente.getReferenteinformatico(),
                                    "Magazzino_Divise : " + Utente.getMagazzinodivise(),
                                    "Add_Giunta : " + Utente.getAdd_giunta(),
                                    "Archivista : " + Utente.getArchivista()};

                            list.setListData(compiti);
                        }
                    }

                }


            }
        });






    }



}
