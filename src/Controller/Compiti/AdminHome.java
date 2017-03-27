package Controller.Compiti;

import Model.GestioneModel;
import Model.Volontario;
import View.BasicFrameView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AdminHome extends MCHome{

    private Volontario utenteloggato;

    public AdminHome(BasicFrameView frame, Volontario UtenteLoggato) {

        super(frame);
        utenteloggato = UtenteLoggato;
        getMcHomeview().VisibilitaResetButton(true);

        getMcHomeview().VisibilitaResetCompitiButton(true);
        getMcHomeview().VisibilitaResetRuoliButton(true);

        AdminHomeListener();
    }

    private void AdminHomeListener(){

        JButton ResetMC = getMcHomeview().getResetMCButton();
        ResetMC.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {


              String cf = getBasicframe().InputMessage("Inserire Codice fiscale del nuovo Master: ");
              if(cf != null && cf.length() == 16 ) {

                  GestioneModel model = new GestioneModel();
                  String[] appoggio = new String[4];
                  appoggio[0] = "flagvolontario";
                  appoggio[1] = "ruolo";
                  appoggio[2] = "Cordinatore";
                  appoggio[3] = cf;
                  model.UpdateSQL(appoggio);

              }
            }
        });

        JButton ResetCompiti = getMcHomeview().getResetCompitiButton();
        ResetCompiti.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if(getBasicframe().OpotionalMessage("Resettare tutti i compiti ?")==0) {
                    Admin controller;
                    controller = new Admin("compiti");
                    controller.ResetAction(utenteloggato);
                }

            }
        });

        JButton ResetRuoli = getMcHomeview().getResetRuoliButton();
        ResetRuoli.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if(getBasicframe().OpotionalMessage("Resettare tutti i ruoli ?")==0) {
                    Admin controller;
                    controller = new Admin("ruoli");
                    controller.ResetAction(utenteloggato);
                }

            }
        });
    }
}
