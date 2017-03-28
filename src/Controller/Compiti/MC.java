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

/**
 * classe che rappresenta il compito del Master Chief
 */
public class MC {

    private BasicFrameView basicframe;
    private GestioneModel model;
    private String appoggio;
    private JComboBox Box;
    private MCView view;
    private JComboBox Box2;
    private MCHomeView homeView;
    private String utilizzatore;
    private Volontario utenteloggato;
    private ArrayList<Persona> UTENTI;

    /*COSTRUTTORI*/

    /*costruttore vuoto*/
    public MC(){

        return;

    }

    public MC(String Utilizzatore) {

        appoggio = "vol_o_cand = 1";
        utilizzatore = Utilizzatore;
        model = new GestioneModel(appoggio);

        if(Utilizzatore.equals("compiti"))
        UTENTI = model.Compiti();
        if(utilizzatore.equals("ruoli"))
        UTENTI = model.Ruoli();
    }

    public MC(BasicFrameView frame, String Utilizzatore, MCHomeView HomeView, Volontario UtenteLoggato) {

        basicframe = frame;
        view = new MCView();
        homeView = HomeView;
        utenteloggato = UtenteLoggato;
        appoggio = "vol_o_cand = 1";

        utilizzatore = Utilizzatore;
        Box2 = view.getBox2();

        model = new GestioneModel(appoggio);

        settaggioview();

        stampalista();

        basicframe.setdestra(view.getIntermedio0());

        Listener();

    }

    /**
     * Settaggio della view
     */
    private void settaggioview(){

        switch (utilizzatore){

            case "compiti" :{

                view.VisibilitaAssegnaCompito(true);
                view.VisibilitaRimuoviCompito(true);

                Box2.removeAllItems();
                Box2.addItem("Archivista");
                Box2.addItem("Referente_Informatico");
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
                if(utenteloggato.getRuolo().equals("Cordinatore"))
                Box2.addItem("Vicecordinatore");

                UTENTI = model.Ruoli();

                listRuoliListner();
                break;
            }

        }

    }

    /**
     * Ascolto dell azioni dell utente
     * -->Assegnacompito, Rimuovicompito, Assegnaruolo, Rimuoviruolo, ritorna
     */
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
                             new MC(basicframe, "compiti", homeView,utenteloggato);
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
                            new MC(basicframe, "compiti", homeView, utenteloggato);
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
                            new MC(basicframe, "ruoli", homeView, utenteloggato);
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
                            new MC(basicframe, "ruoli", homeView, utenteloggato);
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

    /**
     * Listner Item per la combobox
     * In base all utente selezionato permette di stampare in Una Jlist i compiti del medesimo
     */
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

    /**
     * Listener Item per la combobox
     * In base all utente selezionato permette di stampare in una Jlist il ruolo del medesimo
     */
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
                            String[] ruolo = new String[]{Utente.getRuolo()};

                            list.setListData(ruolo);
                        }
                    }

                }


            }
        });
    }

    /**
     *  Stampa la lista degli utenti nella box
     */
    public void stampalista() {

        Box = view.getBox1();

        for (Persona utente : UTENTI)
            Box.addItem(utente.getCognome() + "    -    " + utente.getNome());

    }

    //GETTER
    public MCView getView() {
        return view;
    }

    public ArrayList<Persona> getUTENTI() {

        return UTENTI;

    }

    public String getUtilizzatore() {
        return utilizzatore;
    }

    @Override
    public String toString() {

        return "MC{}";

    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MC mc = (MC) o;

        return view != null ? view.equals(mc.view) : mc.view == null;

    }

}
