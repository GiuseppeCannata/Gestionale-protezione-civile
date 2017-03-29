package Controller.Compiti;

import Controller.AnagraficaController;
import Controller.MessaggioController;
import Model.GestioneModel;
import Model.Persona;
import Model.Volontario;
import View.BasicFrameView;
import View.ListaggiView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Classe rappresentate il compito dell' addetto alla comunicazione Giunta
 */
public class Add_Giunta {

    private BasicFrameView basicframe;
    private ListaggiView view;
    private GestioneModel model;
    private Volontario UtenteLoggato;
    private int Indice;
    private JComboBox Box;
    private String appoggio;
    private ArrayList<Persona> UTENTI;


    /*COSTRUTTORI*/

    /*costruttore vouto*/
    public Add_Giunta() {

        return;

    }

    public Add_Giunta(BasicFrameView frame, Volontario Utenteloggato) {

        basicframe = frame;
        view = new ListaggiView();
        appoggio = "conf_giunta=0";
        model = new GestioneModel(appoggio);
        UtenteLoggato = Utenteloggato;
        Indice = 0;

        view.setLabel("Lista utenti");
        view.VisibilitaVisionaSchedaButton(true);
        view.VisibilitaAccettaButton(true);
        view.VisibilitaInviomessaggio(true);


        UTENTI = model.Schede("listacandidati");

        stampalista();

        basicframe.setdestra(view.getIntermedio0());

        Listener();

    }

    /**
     * Stampa nel box della view i candidati
     */
    public void stampalista () {

        Box = view.getBox1();

        for(Persona candidato : UTENTI)
            Box.addItem(candidato.getCognome() + "    -    " +candidato.getNome());

    }

    /**
     * Ascolto delle azioni dell utente interagendo con la view.
     * Botton:Accetta,Visionascheda,InvioMessaggio
     */
    private void Listener() {

           //SE NON C E NESSUN ITEM RESTITUISCE NULL
        JButton Accetta = view.getAccettaButton();
            Accetta.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {


                    if(Box.getSelectedItem() != null)
                    AccettaAction();

                }
            });

            JButton VisionaScheda = view.getVisionaSchedaButton();
            VisionaScheda.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    if(Box.getSelectedItem() != null) {
                        Indice = Box.getSelectedIndex();

                        AnagraficaController controller;
                        controller = new AnagraficaController(basicframe, UTENTI.get(Indice), view, "listacandidati");
                    }

                }
            });

            JButton InvioMessaggio = view.getInviagliUnMessaggioButton();
            InvioMessaggio.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    if(Box.getSelectedItem() != null){

                       Indice = Box.getSelectedIndex();

                       MessaggioController controller;
                       controller = new MessaggioController(basicframe, UTENTI.get(Indice).getCodice_Fiscale(), UtenteLoggato.getNome() + " " + UtenteLoggato.getCognome());
                    }
                }
            });

    }

    /**
     * Metodo che richiama l update(per settare la conferma) qualora l Add_Giunta accettasse il candidato
     */
    private void AccettaAction(){

        Indice = Box.getSelectedIndex();
        String[] appoggio = new String[3];

        if(basicframe.OpotionalMessage("Accettare "+UTENTI.get(Indice).getCognome()+" "+UTENTI.get(Indice).getNome()+" ?") == 0) {

            appoggio[0] = "pass";
            appoggio[1] = "conf_giunta";
            appoggio[2] =  "1" ;

            if (UTENTI.get(Indice).UpdateSQL(appoggio)) {

                basicframe.Message("Conferma avvenuta con successo!");
                UTENTI.remove(Indice);
                Box.removeItemAt(Indice);
            }
        }
    }

    @Override
    public String toString() {

        return "Add_Giunta{}";

    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Add_Giunta that = (Add_Giunta) o;

        return view != null ? view.equals(that.view) : that.view == null;

    }

}
