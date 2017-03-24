package Controller.Compiti;



import Model.GestioneModel;
import Model.Persona;
import View.BasicFrameView;
import View.ListaggiView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ReferenteinformaticoController {

    private BasicFrameView basicframe;
    private ListaggiView view;
    private GestioneModel model;
    private JComboBox Box;
    private String appoggio;

    private ArrayList<Persona> UTENTI;


    /*costruttore*/
    public ReferenteinformaticoController(BasicFrameView frame) {

        basicframe = frame;

        view = new ListaggiView();
        view.VisibilitaResettaPasswordButton(true);
        view.setLabel("Lista utenti");


        Box = view.getBox1();
        appoggio = "vol_o_cand=1 or vol_o_cand=0";
        model = new GestioneModel(appoggio);

        if(model.SearchSQL())
            UTENTI = model.getListutenti();

        stampalista();

        basicframe.setdestra(view.getIntermedio0());

        Listener();

    }


    public void stampalista () {


            for(Persona candidato : UTENTI)
                Box.addItem(candidato.getCognome() + "    -    " +candidato.getNome());


    }



    private void Listener() {

        JButton resettaPasswordButton =  view.getResettaPasswordButton();
        resettaPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ResettaAction();

            }
        });

    }


    private void ResettaAction(){

        int Indice;
        Indice = Box.getSelectedIndex();
        String[] appoggio = new String[3];



        if(basicframe.OpotionalMessage("Reset password per "+UTENTI.get(Indice).getNome()+" ?") == 0) {

            String Nuovapass = basicframe.InputMessage("Inserire una nuova password: ");
            if(Nuovapass != null && Nuovapass.length() != 0) {

                appoggio[0] = "pass";
                appoggio[1] = "pass";
                appoggio[2] = Nuovapass;

                if (UTENTI.get(Indice).UpdateSQL(appoggio)) {

                    basicframe.Message("Reset avvenuto con successo!");
                    UTENTI.remove(Indice);
                    Box.removeItemAt(Indice);

                }
            }
        }
    }
}
