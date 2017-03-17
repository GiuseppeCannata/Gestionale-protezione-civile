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
   private Candidato Utente;
   private CandidatoDestraView Dview;

   private Sez_AView sez_Aview;
   private Sez_BView sez_Bview;
   private Sez_BRegistrazioneController sez_bRegistrazioneController;
   private Sez_BUtenteController sez_bUtenteController;
   private Sez_CView sez_Cview;

   private JButton Salvabutton;


    /*COSTRUTTORI*/

    //REGISTRAZIONE
    public AnagraficaController(BasicFrameView frame, LoginView view, String CodiceFiscale) {

        basicframe = frame;
        sez_managerview = new Sez_ManagerView();
        loginview = view;
        codicefiscale = CodiceFiscale ;

        Utente = null;
        Dview = null;
        Salvabutton = sez_managerview.getSalvaButton();
        Salvabutton.setContentAreaFilled(false);

        sez_Aview = new Sez_AView();
        sez_Bview = new Sez_BView();
        sez_bRegistrazioneController = new Sez_BRegistrazioneController(sez_Bview, basicframe, codicefiscale);
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
        codicefiscale = null ;
        loginview = null;
        Dview = view;
        Utente = utente;

        sez_managerview = new Sez_ManagerView();
        Salvabutton = sez_managerview.getSalvaButton();
        Salvabutton.setContentAreaFilled(false);

        sez_Aview = new Sez_AView();
        sez_Bview = new Sez_BView();
        sez_Cview = new Sez_CView();
        sez_bUtenteController = new Sez_BUtenteController(sez_Bview,Utente.getCERTIFICAZIONI(),basicframe,codicefiscale);



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

        JButton sez_managerviewAvanti = sez_managerview.getAvantiButton();
        JButton sez_managerviewIndietro= sez_managerview.getIndietroButton();

        sez_managerviewIndietro.setContentAreaFilled(false);

        /*Avanti*/
        sez_managerviewAvanti.addActionListener(new ActionListener() {
                @Override
                    public void actionPerformed(ActionEvent e) {

                        if (Pagine_Manager.getPagina_Corrente() < 3) {

                            CL.next(sez_managerview.getIntermedio1());
                            Pagine_Manager.addPagina_Corrente();

                        }
                    if (Pagine_Manager.getPagina_Corrente() == 3) {
                        sez_managerviewAvanti.setContentAreaFilled(false);
                        Salvabutton.setContentAreaFilled(true);
                    }

                    if (Pagine_Manager.getPagina_Corrente() > 1)
                        sez_managerviewIndietro.setContentAreaFilled(true);
                }




        });


        /*Indietro*/

        sez_managerviewIndietro.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    if (Pagine_Manager.getPagina_Corrente() > 1) {

                        CL.previous(sez_managerview.getIntermedio1());
                        Pagine_Manager.subctractPagina_Corrente();
                    }


				/*significa che mi trovo alla prima pagina, e non potendo andare più indietro "opacizzo" indietro*/
                        if (Pagine_Manager.getPagina_Corrente() == 1)
                            sez_managerviewIndietro.setContentAreaFilled(false);

				/*significa che */
                        if (Pagine_Manager.getPagina_Corrente() >= 1)
                            sez_managerviewAvanti.setContentAreaFilled(true);


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
        Salvabutton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    if(Pagine_Manager.getFine_Pagina()==3)
                    SalvataggioRegistrazione();
                    else
                        basicframe.ErrorMessage("Ancora non è possibile salvare!");

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
                sez_Bview.VisibilitàEliminaButton(true);
                sez_Bview.VisibilitàAggiungiButton(true);
                sez_Bview.VisibilitàUpdateButton(true);
                sez_Cview.Abilita_Disabilita_Campi(true);
                sez_managerview.setModificaButton(false);

            }
        });

        /*Home*/
        JButton Homebutton = sez_managerview.getHomeButton();
        Homebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                basicframe.setdestra(Dview.getIntermedio0());

            }
        });



        /*Salva*/
        Salvabutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(Pagine_Manager.getFine_Pagina()==3)
                   if(!UpdateCandidato())
                       basicframe.ErrorMessage("Nessun cambiamento");
                else
                    basicframe.ErrorMessage("Ancora non è possibile salvare!");

            }
        });


    }

    /**
     * Metodo Privato.
     * Salvataggio si preoccupa di verificare la compilazione,da parte dell utente di tutti i campi contrassegnati
     * come obbligatori, (per fare questo ricorre all utilizzo di un metodo privato VerificaCampi().
     * Una volta accertata la compilazione avvia l inserimento nel DB
     */
    private void SalvataggioRegistrazione(){

           if(basicframe.OpotionalMessage("Una volta effettuato il salvataggio verrai\nrindirizzato" +
                   "alla pagina login.Salvare?") == 0 && VerificaCampi()){

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
     * VerificaCampi si preoccupa di controllare se l utente per sbaglio o volutamente abbia mancato
     * l inserimento dei campi necessari alla sua scheda anagrafica
     *
     * @return false --> non tutti i campi obbligatori sono completi
     * @return true  --> tutti i campi obbligatori sono completi
     */
     private Boolean VerificaCampi(){

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

            if(sez_Aview.getUsernametext().length() > 20)
                throw new Exception ("Username troppo lunga!\nMassimo 20 caratteri");

            if(sez_Aview.getPasswordtext().length() > 20)
                throw new Exception ("Password troppo lunga!\nMassimo 20 caratteri");

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

        sez_Cview.setDenominazioneDatoreDiLavorotext(Utente.getDenominazione_Datore_di_Lavoro());
        sez_Cview.setTelDatoreDiLavorotext(Utente.getTelefono_Datore_Lavoro());
        sez_Cview.setFaxDatoreDiLavorotext(Utente.getFax_Datore_di_Lavoro());
        sez_Cview.setEmailDatoreDiLavorotext(Utente.getEmail_Datore_di_Lavoro());
        sez_Cview.setNumeroCodicePostaletext(Utente.getNumerocodicepostale());
        sez_Cview.setIbantext(Utente.getIBAN());

        sez_Cview.Abilita_Disabilita_Campi(false);


    }

    private boolean UpdateCandidato(){

        boolean controllo = false;
        UtenteModel Update = new UtenteModel();
        String[] appoggio =  new String[4];

        appoggio[0] = "a";
        appoggio[1] = Utente.getCodice_Fiscale();

        //A
        if(!sez_Aview.getNometext().equals(Utente.getNome())) {

            controllo= true;
            appoggio[2] = "nome";
            appoggio[3] = sez_Aview.getNometext();
            Utente.setNome(sez_Aview.getNometext());
            Update.UpdateSQL(appoggio);
        }

       if(!sez_Aview.getCognometext().equals(Utente.getCognome())){

           controllo= true;
            appoggio[2] = "cognome";
            appoggio[3] = sez_Aview.getCognometext();
            Utente.setCognome(sez_Aview.getCognometext());
            Update.UpdateSQL(appoggio);
        }


        if(!sez_Aview.getLuogodinascitatext().equals(Utente.getLuogo_di_Nascita())){

            controllo= true;
            appoggio[2] = "luogodinascita";
            appoggio[3] = sez_Aview.getLuogodinascitatext();
            Utente.setLuogo_di_Nascita(sez_Aview.getLuogodinascitatext());
            Update.UpdateSQL(appoggio);

        }

        if(!sez_Aview.getDatadinascitatext().equals(Utente.getData_di_Nascita())){

            controllo= true;
            appoggio[2] ="datadinascita";
            appoggio[3] = sez_Aview.getDatadinascitatext();
            Utente.setData_di_Nascita(sez_Aview.getDatadinascitatext());
            Update.UpdateSQL(appoggio);

        }


        if(!sez_Aview.getIndirizzodiresidenzatext().equals(Utente.getIndirizzo_di_residenza())){

            controllo= true;
            appoggio[2] = "indirizzodiresidenza";
            appoggio[3] = sez_Aview.getIndirizzodiresidenzatext();
            Utente.setIndirizzo_di_residenza(sez_Aview.getIndirizzodiresidenzatext());
            Update.UpdateSQL(appoggio);

        }


        if(!sez_Aview.getTelefonofissotext().equals(Utente.getTelefono_Fisso())){

            controllo= true;
            appoggio[2] = "telefonofisso";
            appoggio[3]=sez_Aview.getTelefonofissotext();
            Utente.setTelefono_Fisso(sez_Aview.getTelefonofissotext());
            Update.UpdateSQL(appoggio);
        }


        if(!sez_Aview.getTelefonocellularetext().equals(Utente.getTelefono_Cellulare())){

            appoggio[2] = "telefonomobile";
            appoggio[3]=sez_Aview.getTelefonocellularetext();
            Utente.setTelefono_Cellulare(sez_Aview.getTelefonocellularetext());
            Update.UpdateSQL(appoggio);

        }


        if(!sez_Aview.getEmailtext().equals(Utente.getEmail())){

            controllo= true;
            appoggio[2] = "email";
            appoggio[3]=sez_Aview.getEmailtext();
            Utente.setEmail(sez_Aview.getEmailtext());
            Update.UpdateSQL(appoggio);

        }


        if(!sez_Aview.getProfessionetext().equals(Utente.getProfessione())){

            controllo= true;
            appoggio[2] = "professione";
            appoggio[3] = sez_Aview.getProfessionetext();
            Utente.setProfessione(sez_Aview.getProfessionetext());
            Update.UpdateSQL(appoggio);

        }

        if(!sez_Aview.getSpecializzazionetext().equals(Utente.getEventuale_Specializzazione())){

            controllo= true;
            appoggio[2] = "eventualespecializzazione";
            appoggio[3] = sez_Aview.getSpecializzazionetext();
            Utente.setEventuale_Specializzazione(sez_Aview.getSpecializzazionetext());
            Update.UpdateSQL(appoggio);

        }


        //C
        appoggio[0]="c";

        if(!sez_Cview.getDenominazioneDatoreDiLavorotext().equals(Utente.getDenominazione_Datore_di_Lavoro())){

            controllo= true;
            appoggio[2] = "nomedatore";
            appoggio[3] = sez_Cview.getDenominazioneDatoreDiLavorotext();
            Utente.setDenominazione_Datore_di_Lavoro(sez_Cview.getDenominazioneDatoreDiLavorotext());
            Update.UpdateSQL(appoggio);

        }

        if(!sez_Cview.getTelDatoreDiLavorotext().equals(Utente.getTelefono_Datore_Lavoro())){

            controllo= true;
            appoggio[2] = "telefono";
            appoggio[3] = sez_Cview.getTelDatoreDiLavorotext();
            Utente.setTelefono_Datore_Lavoro(sez_Cview.getTelDatoreDiLavorotext());
            Update.UpdateSQL(appoggio);

        }

        if(!sez_Cview.getFaxDatoreDiLavorotext().equals(Utente.getFax_Datore_di_Lavoro())){

            controllo= true;
            appoggio[2] = "eventualespecializzazione";
            appoggio[3] = sez_Cview.getFaxDatoreDiLavorotext() ;
            Utente.setFax_Datore_di_Lavoro(sez_Cview.getFaxDatoreDiLavorotext());
            Update.UpdateSQL(appoggio);

        }

        if(!sez_Cview.getEmailDatoreDiLavorotext().equals(Utente.getEmail_Datore_di_Lavoro())){

            controllo= true;
            appoggio[2] = "email";
            appoggio[3] = sez_Cview.getEmailDatoreDiLavorotext();
            Utente.setEmail_Datore_di_Lavoro(sez_Cview.getEmailDatoreDiLavorotext());
            Update.UpdateSQL(appoggio);

        }

        if(!sez_Cview.getNumeroCodicePostaletext().equals(Utente.getNumerocodicepostale())){

            controllo= true;
            appoggio[2] = "numero_codice_postale";
            appoggio[3] = sez_Cview.getNumeroCodicePostaletext();
            Utente.setNumerocodicepostale(sez_Cview.getNumeroCodicePostaletext());
            Update.UpdateSQL(appoggio);

        }

        if(!sez_Cview.getIbantext().equals(Utente.getIBAN())){

            controllo= true;
            appoggio[2] = "iban";
            appoggio[3] = sez_Cview.getIbantext();
            Utente.setIBAN(sez_Cview.getIbantext());
            Update.UpdateSQL(appoggio);

        }




      return controllo;


    }


}
