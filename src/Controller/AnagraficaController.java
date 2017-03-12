package Controller;

import Model.Candidato;
import Model.RegistrazioneModel;
import View.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Sez_ManagerController --> Controller per la Sez_ManagerView
 * Classe pubblica
 *
 */
public class AnagraficaController {

   public BasicFrameView basicframe;
   public Sez_ManagerView sez_managerview;
   public LoginView loginview;
   public CandidatoDestraView Dview;
   public String codicefiscale;
   public CandidatoDestraView candidatoview;
   private Candidato Utente;

   public Sez_AView sez_Aview;
   public Sez_BView sez_Bview;
   public Sez_BController sez_bController;
   public Sez_CView sez_Cview;


    /*COSTRUTTORI*/

    //REGISTRAZIONE
    public AnagraficaController(BasicFrameView frame, LoginView view, String CodiceFiscale) {

        basicframe = frame;
        loginview = view;
        codicefiscale = CodiceFiscale ;

        sez_managerview = new Sez_ManagerView();

        sez_Aview = new Sez_AView();
        sez_Bview = new Sez_BView();
        sez_Cview = new Sez_CView();
        sez_bController = new Sez_BController(sez_Bview, basicframe);

        sez_managerview.setPaginaLoginButton(true);
        sez_managerview.setModificaButton(false);
        sez_managerview.setHomeButton(false);
        // sez_managerview.setSalvaButton(false);
        sceltapannelli();
        //Setto il mio manager di pagine
        Pagine_Manager.setPagina_Corrente();
        basicframe.setdestra(sez_managerview.getIntermedio0());
        sez_managerview.MessaggioBenvenuto(basicframe);

       Listener();
       RegistrazioneListner();

    }

    //CANDIDATO
    public AnagraficaController(BasicFrameView frame, CandidatoDestraView view,Candidato utente ,String utilizzatore) {

        basicframe = frame;
        candidatoview = view;
        codicefiscale = null ;
        Utente = utente;


        sez_managerview = new Sez_ManagerView();

        sez_Aview = new Sez_AView();
        sez_Bview = new Sez_BView();
        sez_Cview = new Sez_CView();

        sez_managerview.setPaginaLoginButton(false);
        sez_managerview.setModificaButton(true);
        sez_managerview.setHomeButton(true);
        //sez_managerview.setSalvaButton(true);
        sceltapannelli();

        //Setto il mio manager di pagine
        Pagine_Manager.setPagina_Corrente();
        AcquisizioneCampi();
        basicframe.setdestra(sez_managerview.getIntermedio0());

        Listener();
        CandidatoListner();

    }
    /**
     * sceltapannelli gestisce i pannelli da inserire nella Sez_managerView.
     *
     */
    private void sceltapannelli(){

            sez_managerview.setSezA(sez_Aview.getIntermedio0());
            sez_managerview.setSezB(sez_Bview.getIntermedio0());
            sez_managerview.setSezC(sez_Cview.getIntermedio0());

    }

    /**
     * Ascolto operazioni dell'utente
     */
    private void Listener(){

        CardLayout CL=(CardLayout) sez_managerview.getIntermedio1().getLayout();

        /*Avanti*/
        JButton sez_managerviewAvanti = sez_managerview.getAvantiButton();
        sez_managerviewAvanti.addActionListener(new ActionListener() {
                @Override
                    public void actionPerformed(ActionEvent e) {

                        if (Pagine_Manager.getPagina_Corrente() < 3) {

                            CL.next(sez_managerview.getIntermedio1());
                            Pagine_Manager.addPagina_Corrente();

                        }

                    }

        });


        /*Indietro*/
        JButton sez_managerviewIndietro= sez_managerview.getIndietroButton();
        sez_managerviewIndietro.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    if (Pagine_Manager.getPagina_Corrente() > 1) {

                        CL.previous(sez_managerview.getIntermedio1());
                        Pagine_Manager.subctractPagina_Corrente();
                    }

                }
        });





    }

    private void RegistrazioneListner(){

        /*PaginaLogin*/
        JButton paginaLoginbutton = sez_managerview.getPaginaLoginButton();
        paginaLoginbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(basicframe.OpotionalMessage("I dati,se non salvati, verranno persi.\nSei sicuro di tornare al login?") == 0)
                    basicframe.setdestra(loginview.getIntermedio0());
            }
        });


        /*Salva*/
        //rivedere in fase di candidato

        JButton Salvabutton = sez_managerview.getSalvaButton();
        Salvabutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Salvataggio();

            }
        });


    }

    private void CandidatoListner(){



        /*Salva*/
        JButton Modificabutton = sez_managerview.getModificaButton();
        Modificabutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                sez_Aview.Abilita_Disabilita_Campi(true);

            }
        });


    }

    /**
     * Metodo Privato.
     * Salvataggio si preoccupa di verificare la compilazione,da parte dell utente di tutti i campi contrassegnati
     * come obbligatori, (per fare questo ricorre all utilizzo di un metodo privato VerificaCompletamentoCampiObbligatori().
     * Una volta accertata la compilazione avvia l inserimento nel DB
     */
    private void Salvataggio(){

           if(basicframe.OpotionalMessage("Una volta effettuato il salvataggio verrai\nrindirizzato" +
                   "alla pagina login.Salvare?") == 0 && VerificaCompletamentoCampiObbligatori()){

                       RegistrazioneModel Gestione;
                       Gestione = new RegistrazioneModel(codicefiscale, sez_Aview, sez_Bview, sez_Cview, sez_bController);
                       if (Gestione.SearchSQL())
                           basicframe.ErrorMessage("Username già utilizzato!Cambialo!");
                       else if (!Gestione.InsertSQL())
                               basicframe.ErrorMessage("Errore nell'inserimento!\nRicontrollare!");



           }

    }

    /**
     * Metodo di servizio.
     * VerificaCompletamentoCampiObbligatori si preoccupa di controllare se l utente per sbaglio o volutamente abbia mancato
     * l inserimento dei campi necessari alla sua scheda anagrafica
     *
     * @return false --> non tutti i campi obbligatori sono completi
     * @return true  --> tutti i campi obbligatori sono completi
     */
     private Boolean VerificaCompletamentoCampiObbligatori(){

        boolean controllo = false;

        try{
            if((sez_Aview.getNometext().length() == 0)
                    || (sez_Aview.getCognometext().length()== 0)
                    || (sez_Aview.getDatadinascitatext().length()== 0)
                    || (sez_Aview.getIndirizzodiresidenzatext().length()== 0)
                    || (sez_Aview.getTelefonocellularetext().length()== 0)
                    || (sez_Aview.getTelefonofissotext().length()== 0)
                    || (sez_Aview.getUsernametext().length()== 0)
                    || (sez_Aview.getPasswordtext().length()== 0)
                    )
              throw new Exception("Completare tutti i campi obbligatori");



            controllo= true;

        }catch(Exception e){
            basicframe.ErrorMessage(e.getMessage());
        }

        return controllo;

     }

    @Override
    public String toString() {

         return "Sez_ManagerController{}";

    }



    private void AcquisizioneCampi(){

         sez_Aview.setNometext(Utente.getNome());
       /** sez_Aview.setCognometext(Utente.get);
        sez_Aview.setNometext(Utente.getNome());
        sez_Aview.setNometext(Utente.getNome());
        sez_Aview.setNometext(Utente.getNome());
        sez_Aview.setNometext(Utente.getNome());
        sez_Aview.setNometext(Utente.getNome());
        sez_Aview.setNometext(Utente.getNome());**/

        sez_Aview.Abilita_Disabilita_Campi(false);


    }


}
