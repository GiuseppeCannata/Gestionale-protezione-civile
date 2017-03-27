package Controller;

import Model.Candidato;
import View.BasicFrameView;
import View.CandidatoDestraView;
import View.UtenteSinistraView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * CandidatoController --> Controller per la CandidatoView
 */
public class CandidatoController {

   private BasicFrameView basicframe;
   private Candidato Utente;
   private CandidatoDestraView Dview;
   private UtenteSinistraView Sview;
   private int DatiPersonali;

   private ArrayList<String> BROADCAST;
   private ArrayList<String> MESSAGGI;


    /*COSTRUTTORI*/

    public CandidatoController() {

        return;

    }

    public CandidatoController(BasicFrameView frame, Candidato utente) {

        basicframe = frame;
        Utente = utente;
        DatiPersonali = 0;

        InizializzaGUI();

    }


    /**
     * Inzializza la home del candidato
     */
    private void InizializzaGUI(){

        Dview = new CandidatoDestraView();
        Sview = new UtenteSinistraView();

        if(Utente.getConf_Giunta() == 1 && Utente.getConf_Archivista() == 1)
            Dview.MessaggioSchermo("Puoi finalmente evolvere in Volontario.\nFai click su evolvi per dare la tua conferma!");

        if (Utente.getConf_Archivista() == 1)
            Dview.setConf_Archivista(true);

        if (Utente.getConf_Giunta() == 1)
            Dview.setConf_giunta(true);

        //selezione broadcast
        BROADCAST = Utente.getBROADCAST();

        if(BROADCAST.size() !=0){

            for(String messaggio: BROADCAST)
                Dview.setBroadcast(BROADCAST);
        }

        //Settaggio della basicframe con inserimento dei due pannelli a destra e sinistra
        basicframe.setdestra(Dview.getIntermedio0());
        basicframe.setsinistra(Sview.getIntermedio0());

        //selezione messaggi
        MESSAGGI = Utente.getMESSAGGI();

        if(MESSAGGI.size() !=0){

            for(String messaggio: MESSAGGI) {
                Dview.seteMessaggi(MESSAGGI);

                //pone la lettura del messaggio a si
                String[] appoggio = new String[3];

                appoggio[0] = "messaggi";
                appoggio[1] = "letto";
                appoggio[2] = "si";

                if(Utente.UpdateSQL(appoggio))
                    System.out.print("ok");
            }

            basicframe.Message("Hai "+MESSAGGI.size()+" messaggi! Vedili nella sezione messaggi");
        }

        //Settaggio della basicframe con inserimento dei due pannelli a destra e sinistra
        basicframe.setdestra(Dview.getIntermedio0());
        basicframe.setsinistra(Sview.getIntermedio0());

        CandidatoControllerListener();
    }

    /**
     * Ascolto azioni dell utente
     * --> DatiPersonali, Evolvi, logout,home
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

          JButton Home = Sview.getHomeButton();
          Home.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {

                  DatiPersonali = 0;
                  basicframe.setdestra(Dview.getIntermedio0());

              }

          });

      }

    /**
     * Metodo che
     * 1)verifica se Il candidato puo diventare un volontario
     * 2)se puo, chiede la conferma all utente
     * 3)effettua il logout in caso di risposta affermativa del candidato
     */
    private void EvolviAction(){

        if(Utente.getConf_Giunta() != 1 || Utente.getConf_Archivista() != 1){

            basicframe.ErrorMessage("Attendi la conferma dei nostri collaboratori!");
        }else if(basicframe.OpotionalMessage("Confermi a diventare Volontario?") == 0 ) {

            String[] appoggio = new String[3];

            appoggio[0] = "pass";
            appoggio[1] = "vol_o_cand";
            appoggio[2] = "1";


            if(Utente.UpdateSQL(appoggio)) {

                appoggio[1] = "primoaccesso";
                appoggio[2] = "si";

                if(Utente.UpdateSQL(appoggio) && Utente.InsertSQL()) {

                    LoginController login;
                    login = new LoginController(basicframe);

                }
            }

        }


    }

    /**
     * Effettua il logout
     */
    private void LogoutAction(){

        if(basicframe.OpotionalMessage("Vuoi davvero uscire?") == 0) {
            LoginController loginController = new LoginController(basicframe);
        }

    }

    @Override
    public String toString() {

        return "CandidatoController";

    }
}
