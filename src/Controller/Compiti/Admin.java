package Controller.Compiti;


import Model.Persona;
import Model.Volontario;
import View.BasicFrameView;
import View.MCHomeView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Admin extends MC {

    public Admin(String Utilizzatore) {

        super(Utilizzatore);

    }

    public Admin(BasicFrameView frame, String utilizzatore, MCHomeView view) {

        super(frame, utilizzatore, view);

    }


    public void ResetAction(Volontario UtenteLoggato) {

        ArrayList<Persona> UTENTI = getUTENTI();

        for(Persona utente :UTENTI){

            if(!utente.getCodice_Fiscale().equals(UtenteLoggato.getCodice_Fiscale())) {

                Volontario volontario = (Volontario) utente;

                if (getUtilizzatore().equals("compiti")) {
                    volontario.ResetCompiti();
                }
                if (getUtilizzatore().equals("ruoli"))
                    volontario.ResetRuoli();
            }
        }
     }
}
