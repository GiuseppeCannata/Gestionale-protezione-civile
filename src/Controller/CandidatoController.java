package Controller;

import Model.Candidato;
import View.BasicFrameView;
import View.CandidatoDestraView;
import View.CandidatoSinistraView;

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
   private CandidatoSinistraView Sview;
   private int DatiPersonali;


    /*COSTRUTTORI*/
    public CandidatoController(BasicFrameView frame, Candidato utente) {

        basicframe = frame;
        Utente = utente;
        Dview = new CandidatoDestraView();
        Sview = new CandidatoSinistraView();
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

    private void LogoutAction(){

        if(basicframe.OpotionalMessage("Vuoi davvero uscire?") == 0) {
            LoginController loginController = new LoginController(basicframe);
        }

    }


    public void setDatiPersonali(int datiPersonali) {

        DatiPersonali = datiPersonali;

    }


    @Override
    public String toString() {

        return "CandidatoController";

    }
}
