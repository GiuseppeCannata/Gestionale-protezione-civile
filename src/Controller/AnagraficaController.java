package Controller;

import Model.Candidato;
import Model.RegistrazioneModel;
import Model.UtenteModel;
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

   private BasicFrameView basicframe;
   private Sez_ManagerView sez_managerview;
   private LoginView loginview;
   private String codicefiscale;
   private CandidatoDestraView candidatoview;
   private Candidato Utente;

   private Sez_AView sez_Aview;
   private Sez_BView sez_Bview;
   private Sez_BRegistrazioneController sez_bRegistrazioneController;
   private Sez_BCandidatoController sez_bUtenteController;
   private Sez_CView sez_Cview;


    /*COSTRUTTORI*/

    //REGISTRAZIONE
    public AnagraficaController(BasicFrameView frame, LoginView view, String CodiceFiscale) {

        basicframe = frame;
        sez_managerview = new Sez_ManagerView();
        loginview = view;
        codicefiscale = CodiceFiscale ;
        candidatoview = null;
        Utente = null;

        sez_Aview = new Sez_AView();
        sez_Bview = new Sez_BView();
        sez_bRegistrazioneController = new Sez_BRegistrazioneController(sez_Bview, basicframe);
        sez_Cview = new Sez_CView();


        sez_managerview.setPaginaLoginButton(true);
        sez_managerview.setModificaButton(false);
        sez_managerview.setHomeButton(false);
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
        sez_bUtenteController = new Sez_BCandidatoController(sez_Bview,Utente.getABILITAZIONI(),Utente.getCORSI(),Utente.getPATENTI());



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

        JButton Salvabutton = sez_managerview.getSalvaButton();
        Salvabutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                SalvataggioRegistrazione();

            }
        });


    }

    private void CandidatoListner(){


        /*Modifica*/
        JButton Modificabutton = sez_managerview.getModificaButton();
        Modificabutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                sez_Aview.Abilita_Disabilita_Campi(true);
                sez_Bview.Abilita_Disabilita_Campi(true);
                sez_Cview.Abilita_Disabilita_Campi(true);

            }
        });



        /*Salva*/
        JButton Salvabutton = sez_managerview.getSalvaButton();
        Salvabutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

               UpdateCandidato();

            }
        });


    }

    /**
     * Metodo Privato.
     * Salvataggio si preoccupa di verificare la compilazione,da parte dell utente di tutti i campi contrassegnati
     * come obbligatori, (per fare questo ricorre all utilizzo di un metodo privato VerificaCompletamentoCampiObbligatori().
     * Una volta accertata la compilazione avvia l inserimento nel DB
     */
    private void SalvataggioRegistrazione(){

           if(basicframe.OpotionalMessage("Una volta effettuato il salvataggio verrai\nrindirizzato" +
                   "alla pagina login.Salvare?") == 0 && VerificaCompletamentoCampiObbligatori()){

                       RegistrazioneModel Gestione;
                       Gestione = new RegistrazioneModel(codicefiscale, sez_Aview, sez_Bview, sez_Cview, sez_bRegistrazioneController);
                       if (Gestione.SearchSQL())
                           basicframe.ErrorMessage("Username già utilizzato!Cambialo!");
                       else if (!Gestione.InsertSQL())
                               basicframe.ErrorMessage("Errore nell'inserimento!\nRicontrollare!");
                       else
                           basicframe.setdestra(loginview.getIntermedio0());



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
        sez_Aview.setCognometext(Utente.getCognome());
        sez_Aview.setLuogodinascitatext(Utente.getLuogo_di_Nascita());
        sez_Aview.setTelefonofissotext(Utente.getTelefono_Fisso());
        sez_Aview.setTelefonocellularetext(Utente.getTelefono_Cellulare());
        sez_Aview.setCombobox(Utente.getData_di_Nascita().substring(0,4),Utente.getData_di_Nascita().substring(5,7),Utente.getData_di_Nascita().substring(8,10));
        sez_Aview.setIndirizzodiresidenzatext(Utente.getIndirizzo_di_residenza());
        sez_Aview.setEmailtext(Utente.getEmail());
        sez_Aview.setProfessionetext(Utente.getProfessione());
        sez_Aview.setSpecializzazionetext(Utente.getEventuale_Specializzazione());
        sez_Aview.setUsernametext(Utente.getUsername());
        sez_Aview.setPasswordtext(Utente.getPassword());

        sez_Aview.Abilita_Disabilita_Campi(false);

        //ovviamente per la sezione B c è anche il controller invocato nel costruttore
        sez_Bview.Abilita_Disabilita_Campi(false);

        sez_Cview.setDenominazioneDatoreDiLavorotext(Utente.getDenominazione_Datore_di_Lavoro());
        sez_Cview.setTelDatoreDiLavorotext(Utente.getTelefono_Datore_Lavoro());
        sez_Cview.setFaxDatoreDiLavorotext(Utente.getFax_Datore_di_Lavoro());
        sez_Cview.setEmailDatoreDiLavorotext(Utente.getEmail_Datore_di_Lavoro());
        sez_Cview.setNumeroCodicePostaletext(Utente.getNumerocodicepostale());
        sez_Cview.setIbantext(Utente.getIBAN());

        sez_Cview.Abilita_Disabilita_Campi(false);


    }

    private void UpdateCandidato(){

        UtenteModel Update = new UtenteModel();
        String[] appoggio =  new String[4];

        appoggio[0] = "a";

        //A
        if(!sez_Aview.getNometext().equals(Utente.getNome())) {

            appoggio[2] = "nome";
            appoggio[3] = sez_Aview.getNometext();
            appoggio[1] = Utente.getCodice_Fiscale();
            Update.UpdateSQL(appoggio);
        }

       if(!sez_Aview.getCognometext().equals(Utente.getCognome())){

            appoggio[2] = "cognome";
            appoggio[3] = sez_Aview.getCognometext();
            Update.UpdateSQL(appoggio);
        }


        if(!sez_Aview.getLuogodinascitatext().equals(Utente.getLuogo_di_Nascita())){

            appoggio[2] = "luogodinascita";
            appoggio[3] = sez_Aview.getLuogodinascitatext();
            Update.UpdateSQL(appoggio);

        }

        if(!sez_Aview.getDatadinascitatext().equals(Utente.getData_di_Nascita())){

            appoggio[2] ="datadinascita";
            appoggio[3] = sez_Aview.getDatadinascitatext();
            Update.UpdateSQL(appoggio);

        }


        if(!sez_Aview.getIndirizzodiresidenzatext().equals(Utente.getIndirizzo_di_residenza())){

            appoggio[2] = "indirizzodiresidenza";
            appoggio[3]=sez_Aview.getIndirizzodiresidenzatext();
            Update.UpdateSQL(appoggio);

        }


        if(!sez_Aview.getTelefonofissotext().equals(Utente.getTelefono_Fisso())){

            appoggio[2] = "telefonofisso";
            appoggio[3]=sez_Aview.getTelefonofissotext();
            Update.UpdateSQL(appoggio);
        }


        if(!sez_Aview.getTelefonocellularetext().equals(Utente.getTelefono_Cellulare())){

            appoggio[2] = "telefonomobile";
            appoggio[3]=sez_Aview.getTelefonocellularetext();
            Update.UpdateSQL(appoggio);

        }


        if(!sez_Aview.getEmailtext().equals(Utente.getEmail())){

            appoggio[2] = "email";
            appoggio[3]=sez_Aview.getEmailtext();
            Update.UpdateSQL(appoggio);

        }


        if(!sez_Aview.getProfessionetext().equals(Utente.getProfessione())){

            appoggio[2] = "professione";
            appoggio[3]=sez_Aview.getProfessionetext();
            Update.UpdateSQL(appoggio);

        }

        if(!sez_Aview.getSpecializzazionetext().equals(Utente.getEventuale_Specializzazione())){

            appoggio[2] = "eventualespecializzazione";
            appoggio[3] = sez_Aview.getSpecializzazionetext();
            Update.UpdateSQL(appoggio);

        }


        //C

        if(!sez_Cview.getDenominazioneDatoreDiLavorotext().equals(Utente.getDenominazione_Datore_di_Lavoro())){

            appoggio[2] = "nomedatore";
            appoggio[3] = sez_Cview.getDenominazioneDatoreDiLavorotext();
            Update.UpdateSQL(appoggio);

        }

        if(!sez_Cview.getTelDatoreDiLavorotext().equals(Utente.getTelefono_Datore_Lavoro())){

            appoggio[2] = "telefono";
            appoggio[3] = sez_Cview.getTelDatoreDiLavorotext();
            Update.UpdateSQL(appoggio);

        }

        if(!sez_Cview.getFaxDatoreDiLavorotext().equals(Utente.getFax_Datore_di_Lavoro())){

            appoggio[2] = "eventualespecializzazione";
            appoggio[3] = sez_Cview.getFaxDatoreDiLavorotext() ;
            Update.UpdateSQL(appoggio);

        }

        if(!sez_Cview.getEmailDatoreDiLavorotext().equals(Utente.getEmail_Datore_di_Lavoro())){

            appoggio[2] = "email";
            appoggio[3] = sez_Cview.getEmailDatoreDiLavorotext();
            Update.UpdateSQL(appoggio);

        }

        if(!sez_Cview.getNumeroCodicePostaletext().equals(Utente.getNumerocodicepostale())){

            appoggio[2] = "numero_codice_postale";
            appoggio[3] = sez_Cview.getNumeroCodicePostaletext();
            Update.UpdateSQL(appoggio);

        }

        if(!sez_Cview.getIbantext().equals(Utente.getIBAN())){

            appoggio[2] = "iban";
            appoggio[3] = sez_Cview.getIbantext();
            Update.UpdateSQL(appoggio);

        }







    }


}
