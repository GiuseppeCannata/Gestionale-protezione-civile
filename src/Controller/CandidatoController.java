package Controller;

import Model.Candidato;
import Model.Certificazione;
import View.BasicFrameView;
import View.CandidatoDestraView;
import View.UtenteSinistraView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Controller per la CandidatoView
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

    /*costruttore vuoto*/
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
        Sview.VisibilitaDisiscrivimi(true);

        //controllo sul messaggio a schermo
        if(Utente.getConf_Giunta() == 1 && Utente.getConf_Archivista() == 1)
            Dview.MessaggioSchermo("Puoi finalmente evolvere in Volontario.\nFai click su evolvi per dare la tua conferma!");

        //controllo sul set della checbox
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

        //selezione messaggi(privati)
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
            //mnotifica all utente la presenza di messaggi privati
            basicframe.Message("Hai "+MESSAGGI.size()+" messaggi! Vedili nella sezione messaggi");
        }

        //Settaggio della basicframe con inserimento dei due pannelli a destra e sinistra
        basicframe.setdestra(Dview.getIntermedio0());
        basicframe.setsinistra(Sview.getIntermedio0());

        CandidatoControllerListener();
    }

    /**
     * Ascolto azioni dell utente
     * Botton:DatiPersonali, Evolvi, logout,home
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

          JButton Disiscrivimi = Sview.getDisiscrivimiButton();
          Disiscrivimi.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {

                  if(basicframe.OpotionalMessage("Sei sicuro di volerti disiscrivere? ") == 0)
                      Disiscizione();

              }

          });

      }

    /**
     * Metodo che
     * 1)verifica se Il candidato puo diventare un volontario
     * 2)se puo, chiede la conferma all utente
     * 3)effettua il logout in caso di risposta affermativa del candidato
     * 4)se l utente non puo ancora evolvere notifica di questo
     */
    private void EvolviAction(){

        if(Utente.getConf_Giunta() != 1 || Utente.getConf_Archivista() != 1){

            basicframe.ErrorMessage("Attendi la conferma dei nostri collaboratori!");
        }else if(basicframe.OpotionalMessage("Confermi a diventare Volontario?\nDigitando no si procederà alla " +
                "disiscrizione") == 0 ) {

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

        }else{

            Disiscizione();


        }


    }


    private void Disiscizione(){

        String Appoggio;

        Appoggio = "a";
        Utente.DeleteSQL(Appoggio);
        for(Certificazione certificazione : Utente.getCERTIFICAZIONI())
            certificazione.DeleteSQL();
        Appoggio = "c";
        Utente.DeleteSQL(Appoggio);
        Appoggio = "pass";
        Utente.DeleteSQL(Appoggio);

        LoginController controller;
        controller = new LoginController(basicframe);
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
        return "CandidatoController{" +
                "basicframe=" + basicframe +
                ", Utente=" + Utente +
                ", Dview=" + Dview +
                ", Sview=" + Sview +
                ", DatiPersonali=" + DatiPersonali +
                ", BROADCAST=" + BROADCAST +
                ", MESSAGGI=" + MESSAGGI +
                '}';
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CandidatoController that = (CandidatoController) o;

        if (Dview != null ? !Dview.equals(that.Dview) : that.Dview != null) return false;
        return Sview != null ? Sview.equals(that.Sview) : that.Sview == null;
    }

}
