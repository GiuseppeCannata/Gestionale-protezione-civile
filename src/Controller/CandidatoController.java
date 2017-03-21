package Controller;

import Model.Candidato;
import View.BasicFrameView;
import View.CandidatoDestraView;
import View.UtenteSinistraView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * CandidatoController --> Controller per la CandidatoView
 */
public class CandidatoController {

   private BasicFrameView basicframe;
   private Candidato Utente;
   private CandidatoDestraView Dview;
   private UtenteSinistraView Sview;
   private int DatiPersonali;


    /*COSTRUTTORI*/

    public CandidatoController() {

        return;

    }

    public CandidatoController(BasicFrameView frame, Candidato utente) {

        basicframe = frame;
        Utente = utente;
        Dview = new CandidatoDestraView();
        Sview = new UtenteSinistraView();
        Sview.VisibilitaHomeButton(false);

        if(Utente.getConf_Giunta() == 1 && Utente.getConf_Archivista() == 1)
            Dview.MessaggioSchermo("Puoi finalmente evolvere in Volontario.\nFai click su evolvi per dare la tua conferma!");
        else {
            if (Utente.getConf_Archivista() == 1)
              Dview.setConf_Archivista(true);

            if (Utente.getConf_Giunta() == 1)
                Dview.setConf_giunta(true);
        }
        DatiPersonali = 0;
        //Settaggio della basicframe con inserimento dei due pannelli a destra e sinistra
        basicframe.setdestra(Dview.getIntermedio0());
        basicframe.setsinistra(Sview.getIntermedio0());

        CandidatoControllerListener();

    }

    /**
     * Ascolto azioni dell utente --> DatiPersonali, Evolvi
     */
      private void  CandidatoControllerListener(){

        /*DatiPersonali*/
        JButton DatiPersonaliButton = Sview.getDatiPersonaliButton();
        DatiPersonaliButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (DatiPersonali == 0){

                    DatiPersonali = 1;
                    AnagraficaController datipersonali;
                    datipersonali = new AnagraficaController(basicframe, Dview, Utente, Utente.getCodice_Fiscale(),
                            CandidatoController.this);
                 }

            }

        });

        /*Evolvi*/
        JButton Evolvi = Sview.getEvolviButton();
        Evolvi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                EvolviAction();

            }

        });

        /*Logout*/
        JButton Logout = Sview.getLogoutButton();
        Logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                LogoutAction();
            }

        });


    }


    private void EvolviAction(){

        if(Utente.getConf_Giunta() != 1 || Utente.getConf_Archivista() != 1){

            basicframe.ErrorMessage("Attenti la conferma dei nostri collaboratori!");
        }else if(basicframe.OpotionalMessage("Confermi a diventare Volontario?") == 0 ) {

            String[] appoggio = new String[4];

            appoggio[0] = "pass";
            appoggio[1] = Utente.getCodice_Fiscale();
            appoggio[2] = "vol_o_cand";
            appoggio[3] = "1";

            if(Utente.UpdateSQL(appoggio)) {

                appoggio[2] ="primoaccesso";
                appoggio[3] = "si";

                if(Utente.UpdateSQL(appoggio)) {

                    LoginController login;
                    login = new LoginController(basicframe);

                }
            }
        }


    }

    private void LogoutAction(){

        if(basicframe.OpotionalMessage("Vuoi davvero uscire?") == 0) {
            LoginController loginController = new LoginController(basicframe);
        }

    }


    public void setDatiPersonali(int datiPersonali) {

        DatiPersonali = datiPersonali;

    }

    public void setBasicframe(BasicFrameView basicframe) {
        this.basicframe = basicframe;
    }

    public void setSview(UtenteSinistraView sview) {
        Sview = sview;
    }

    @Override
    public String toString() {

        return "CandidatoController";

    }
}
