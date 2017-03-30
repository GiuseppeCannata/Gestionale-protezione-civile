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
 * Classe rappresentante il compito del MasterChief.
 */
public class MC {

    private BasicFrameView basicframe;
    private MCHomeView mcHomeview;
    private Volontario utenteloggato;

    private GestioneModel model;
    private String appoggio;
    private JComboBox Box;
    private MCView view;
    private JComboBox Box2;
    private String utilizzatore;
    private ArrayList<Persona> UTENTI;


    /*COSTRUTTORI*/

    /*costruttore vuoto*/
    public MC() {

        return;

    }

    public MC(BasicFrameView frame, Volontario UtenteLoggato) {

        basicframe = frame;
        mcHomeview = new MCHomeView();
        utenteloggato = UtenteLoggato;
        basicframe.setdestra(mcHomeview.getIntermedio0());

        Listener();
    }

    /**
     * Ascolto le azioni dell utente.
     * Botton:CompitiRuoli, Ruoli
     */
    private void Listener() {


        /*CompitiRuoli*/
        JButton Compiti = mcHomeview.getCompitiButton();
        Compiti.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                CompitiAction();

            }
        });


        JButton Ruoli = mcHomeview.getRuoliButton();
        Ruoli.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {


                RuoliAction();

            }
        });
    }

    private void CompitiAction(){

        view = new MCView();
        appoggio = "vol_o_cand = 1";

        utilizzatore = "compiti";
        Box2 = view.getBox2();

        model = new GestioneModel(appoggio);

        settaggioview();

        stampalista();

        basicframe.setdestra(view.getIntermedio0());

        listener();
    }

    private void RuoliAction(){

        view = new MCView();


        appoggio = "vol_o_cand = 1";

        utilizzatore = "ruoli";
        Box2 = view.getBox2();

        model = new GestioneModel(appoggio);

        settaggioview();


        stampalista();

        basicframe.setdestra(view.getIntermedio0());

        listener();


    }

    /**
     * Settaggio della view in base all utilizzatore(compiti,ruoli)
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

                UTENTI = model.CompitiRuoli();

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

                UTENTI = model.CompitiRuoli();

                listRuoliListner();
                break;
            }

        }

    }

    /**
     * Ascolto dell azioni dell utente.
     * Botton:Assegnacompito, Rimuovicompito, Assegnaruolo, Rimuoviruolo, ritorna
     */
    private void listener(){


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
                            CompitiAction();
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
                            CompitiAction();
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
                            RuoliAction();
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
                           RuoliAction();
                        }
                    }

            }
        });

        JButton RitornaButton = view.getRitornaButton();
        RitornaButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                basicframe.setdestra(mcHomeview.getIntermedio0());

            }
        });


    }

    /**
     *  Stampa la lista degli utenti nella box
     */
    public void stampalista() {

        Box = view.getBox1();

        for (Persona utente : UTENTI) {

            if(getUtenteloggato().getRuolo().equals("Admin"))
                if (!utente.getCodice_Fiscale().equals(getUtenteloggato().getCodice_Fiscale()))
                    Box.addItem(utente.getCognome() + "    -    " + utente.getNome());

            if(getUtenteloggato().getRuolo().equals("Vicecordinatore"))
                if (!utente.getCodice_Fiscale().equals(getUtenteloggato().getCodice_Fiscale())
                    && !((Volontario) utente).getRuolo().equals("Admin") && !((Volontario) utente).getRuolo().equals("Cordinatore"))
                    Box.addItem(utente.getCognome() + "    -    " + utente.getNome());

            if(getUtenteloggato().getRuolo().equals("Cordinatore"))
              if (!utente.getCodice_Fiscale().equals(getUtenteloggato().getCodice_Fiscale())
                    && !((Volontario) utente).getRuolo().equals("Admin"))
                Box.addItem(utente.getCognome() + "    -    " + utente.getNome());



        }
    }

    /**
     * Listner Item per la combobox.
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
     * Listener Item per la combobox.
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

    //GETTER
    public MCHomeView getMcHomeview() {

        return mcHomeview;

    }

    public BasicFrameView getBasicframe() {

        return basicframe;

    }

    public Volontario getUtenteloggato() {

        return utenteloggato;

    }

    public void setUtilizzatore(String Utilizzatore) {

       utilizzatore = Utilizzatore;

    }

    public void setAppoggio(String Appoggio) {

        appoggio = Appoggio;

    }

    public void setModel(GestioneModel Model) {

        model = Model;

    }

    public String getAppoggio() {

        return appoggio;

    }

    public String getUtilizzatore() {

        return utilizzatore;

    }

    public void setUTENTI(ArrayList<Persona> uTENTI) {

        UTENTI = uTENTI;

    }

    public GestioneModel getModel() {

        return model;

    }

    public ArrayList<Persona> getUTENTI() {

        return UTENTI;

    }

    @Override
    public String toString() {

        return "MC{}";

    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MC mcHome = (MC) o;

        return mcHomeview != null ? mcHomeview.equals(mcHome.mcHomeview) : mcHome.mcHomeview == null;

    }

}
