package Controller.Compiti;


import Model.GestioneModel;
import Model.Persona;
import Model.Volontario;
import View.BasicFrameView;

import java.util.ArrayList;

public class Admin extends MC {


    public Admin() {

        super();

    }

    public Admin(String Utilizzatore) {

        super(Utilizzatore);

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

    public void ResetMC(BasicFrameView frame){

        String cf = frame.InputMessage("Inserire Codice fiscale del nuovo Master: ");
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
}
