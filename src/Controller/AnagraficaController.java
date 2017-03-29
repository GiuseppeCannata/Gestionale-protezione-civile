package Controller;

import Model.*;
import View.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Controller per la AnagraficaView
 */
public class AnagraficaController {

    private BasicFrameView basicframe;
    private AnagraficaView Anagraficaview;
    private LoginView loginview;
    private String codicefiscale;
    private Persona Utente;
    private CandidatoDestraView cDview;
    private CandidatoController cController;
    private VolontarioDView vDview;
    private VolontarioController vController;
    private Sez_AView sez_Aview;
    private Sez_BView sez_Bview;
    private Sez_BUtenteController sez_bRegistrazioneController;
    private Sez_BUtenteController sez_bUtenteController;
    private Sez_CView sez_Cview;
    private Sez_DView sez_Dview;
    private JButton Salvabutton;
    private int Modifica;
    private String utilizzatore;
    private ListaggiView listacandidatiview;


    /*COSTRUTTORI*/

    //ARCHIVISTA,ADD_GIUNTA
    public AnagraficaController(BasicFrameView frame, Persona utente, ListaggiView view, String Utilizzatore ){

        basicframe = frame;
        Anagraficaview = new AnagraficaView();
        utilizzatore = Utilizzatore;
        Utente = utente;
        listacandidatiview = view;

        codicefiscale = Utente.getCodice_Fiscale();

        sez_Aview = new Sez_AView();
        sez_Bview = new Sez_BView();
        sez_Cview = new Sez_CView();
        sez_Aview.VisibilitaPasswordLT(false);
        sez_Aview.VisibilitaUserLT(false);

        sez_bUtenteController = new Sez_BUtenteController(sez_Bview, Utente.getCERTIFICAZIONI(), basicframe, codicefiscale);

        Anagraficaview.VisibilitaModificaButton(false);
        Anagraficaview.VisibilitaPaginaLoginButton(false);
        Anagraficaview.VisibilitaSalvaButton(false);
        Anagraficaview.VisibilitaListaButton(true);

        sceltapannelli();
        Pagine_Manager.setPagina_Corrente();
        SettaggioCampi_A();
        SettaggioCampi_C();

        if(utilizzatore.equals("listavolontari")) {

            SettaggioCampi_D();
            VolontarioListner();

        }else
            Listener();


        basicframe.setdestra(Anagraficaview.getIntermedio0());
        AAListner();  //A--> archivista   A--> add_giunta

    }


    //PRIMOACCESSO
    public AnagraficaController(BasicFrameView frame, VolontarioController controller, Volontario utente) {

        basicframe = frame;
        utilizzatore = "primoaccesso";
        Utente = utente;
        sez_Dview = new Sez_DView();
        vController = controller;
        basicframe.setdestra(sez_Dview.getIntermedio0());

        primoaccessoListner();

    }


    //REGISTRAZIONE
    public AnagraficaController(BasicFrameView frame, LoginView view, String CodiceFiscale) {

        basicframe = frame;
        Anagraficaview = new AnagraficaView();
        loginview = view;
        codicefiscale = CodiceFiscale;

        Modifica = 0;
        utilizzatore = "registrazione";

        Salvabutton = Anagraficaview.getSalvaButton();

        sez_Aview = new Sez_AView();
        sez_Bview = new Sez_BView();
        sez_bRegistrazioneController = new Sez_BUtenteController(basicframe, sez_Bview, codicefiscale);
        sez_Cview = new Sez_CView();

        Anagraficaview.VisibilitaPaginaLoginButton(true);
        Anagraficaview.VisibilitaModificaButton(false);

        Anagraficaview.VisibilitaListaButton(false);
        Anagraficaview.VisibilitaSalvaButton(false);

        sceltapannelli();
        //Setto il mio manager di pagine
        Pagine_Manager.setPagina_Corrente();
        basicframe.setdestra(Anagraficaview.getIntermedio0());
        Anagraficaview.MessaggioBenvenuto(basicframe);

        Listener();
        RegistrazioneListner();

    }

    //CANDIDATO
    public AnagraficaController(BasicFrameView frame, CandidatoDestraView view, Candidato utente, String CodiceFiscale,
                                CandidatoController CController) {

        basicframe = frame;
        Anagraficaview = new AnagraficaView();
        loginview = null;
        codicefiscale = CodiceFiscale;
        Utente = utente;
        cDview = view;
        Modifica = 0;
        cController = CController;
        Salvabutton = Anagraficaview.getSalvaButton();
        Salvabutton.setVisible(false);
        utilizzatore = "candidato";

        sez_Aview = new Sez_AView();
        sez_Bview = new Sez_BView();
        sez_Cview = new Sez_CView();
        sez_bUtenteController = new Sez_BUtenteController(sez_Bview, Utente.getCERTIFICAZIONI(), basicframe, codicefiscale);


        Anagraficaview.VisibilitaPaginaLoginButton(false);
        Anagraficaview.VisibilitaModificaButton(true);

        Anagraficaview.VisibilitaListaButton(false);
        sceltapannelli();

        //Setto il mio manager di pagine
        Pagine_Manager.setPagina_Corrente();
        SettaggioCampi_A();
        SettaggioCampi_C();
        basicframe.setdestra(Anagraficaview.getIntermedio0());

        Listener();
        UtenteListner();

    }

    //VOLONTARIO
    public AnagraficaController(BasicFrameView frame, VolontarioDView view, Volontario VUtente, VolontarioController VController) {

        basicframe = frame;
        Anagraficaview = new AnagraficaView();
        Utente = VUtente;

        codicefiscale = Utente.getCodice_Fiscale() ;

        vController = VController;
        vDview = view;
        Modifica = 0;
        Salvabutton = Anagraficaview.getSalvaButton();
        Salvabutton.setVisible(false);
        utilizzatore = "volontario";


        sez_Aview = new Sez_AView();
        sez_Bview = new Sez_BView();
        sez_bUtenteController = new Sez_BUtenteController(sez_Bview, Utente.getCERTIFICAZIONI(), basicframe, codicefiscale);
        sez_Cview = new Sez_CView();


        Anagraficaview.VisibilitaPaginaLoginButton(false);
        Anagraficaview.VisibilitaModificaButton(true);

        Anagraficaview.VisibilitaListaButton(false);
        sceltapannelli();


        //Setto il mio manager di pagine
        Pagine_Manager.setPagina_Corrente();
        SettaggioCampi_A();
        SettaggioCampi_C();
        SettaggioCampi_D();
        basicframe.setdestra(Anagraficaview.getIntermedio0());

        VolontarioListner();
        UtenteListner();

    }


    /*METODI*/
    /**
     * sceltapannelli gestisce i pannelli da inserire nella Sez_managerView.
     */
    private void sceltapannelli() {

        Anagraficaview.setSezA(sez_Aview.getIntermedio0());
        Anagraficaview.setSezB(sez_Bview.getIntermedio0());
        Anagraficaview.setSezC(sez_Cview.getIntermedio0());

        if (utilizzatore.equals("volontario") || utilizzatore.equals("listavolontari")) {

            sez_Dview = new Sez_DView();
            Anagraficaview.setSezD(sez_Dview.getIntermedio0());
            sez_Dview.VisibilitaSalvaButton(false);

        }

    }

    /**
     * Listener per il primo accesso.
     * Botton:Salva
     */

    private void primoaccessoListner() {

        JButton Salva = sez_Dview.getSalvaButton();
        Salva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (sez_Dview.getGStext().length() != 0 && SalvataggioPrimoAccesso()) {

                    vController.InizializzaGUI();
                    basicframe.Message("Hai completato la tua iscrizione.\n Benvenuto nella tua home");


                } else
                    basicframe.ErrorMessage("Completare i campi obbligatori!");
            }
        });
    }

    /**
     * Ascolto operazioni dell'utente.
     * Botton:avanti, indietro
     *
     */
    private void Listener() {

        CardLayout CL = (CardLayout) Anagraficaview.getIntermedio1().getLayout();

        JButton sez_managerviewAvanti = Anagraficaview.getAvantiButton();
        JButton sez_managerviewIndietro = Anagraficaview.getIndietroButton();

        sez_managerviewIndietro.setContentAreaFilled(false);

        /*Avanti*/
        sez_managerviewAvanti.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (Pagine_Manager.getPagina_Corrente() < 3) {

                    CL.next(Anagraficaview.getIntermedio1());
                    Pagine_Manager.addPagina_Corrente();

                }
                if (Pagine_Manager.getPagina_Corrente() == 3) {
                    sez_managerviewAvanti.setContentAreaFilled(false);
                    if(utilizzatore.equals("registrazione"))
                    Salvabutton.setVisible(true);
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

                    CL.previous(Anagraficaview.getIntermedio1());
                    Pagine_Manager.subctractPagina_Corrente();
                }


				    /*significa che mi trovo alla prima pagina, e non potendo andare più indietro "opacizzo" indietro*/
                if (Pagine_Manager.getPagina_Corrente() == 1)
                    sez_managerviewIndietro.setContentAreaFilled(false);


                if (Pagine_Manager.getPagina_Corrente() >= 1)
                    sez_managerviewAvanti.setContentAreaFilled(true);


            }
        });
    }

    /**
     * Listener utilizzato escusivamente nella fase di resgistrazione.
     * Botton:PaginaLogin, Salva
     */
    private void RegistrazioneListner() {

        /*PaginaLogin*/
        JButton paginaLoginbutton = Anagraficaview.getPaginaLoginButton();
        paginaLoginbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (basicframe.OpotionalMessage("I dati,se non salvati, verranno persi.\nSei sicuro di tornare al login?") == 0) {
                    LoginController LController;
                    LController = new LoginController(basicframe);
                }

            }
        });


        /*Salva*/
        Salvabutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                SalvataggioRegistrazione();

            }
        });


    }

    /**
     * Listener utilizzato esclusivamete dalL utente(sia canddiato sia volontario).
     * Botton:Modifica, Home, salva
     */
    private void UtenteListner() {


        /*Modifica*/
        JButton Modificabutton = Anagraficaview.getModificaButton();
        Modificabutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ModificaAction();

            }
        });

        /*Salva*/
        Salvabutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                SalvaAction();

            }
        });


    }

    /**
     * Setta tutti i campi della sezione A,B,C editabili.
     * Fa comparire i bottoni update e elimina della sezione B.
     * Fa scomparire il bottone di modifica.
     *
     */
    private void ModificaAction(){

        Modifica = 1;

        Salvabutton.setVisible(true);

        sez_Aview.Abilita_Disabilita_Campi(true);

        sez_Bview.Abilita_Disabilita_Campi(true);
        sez_Bview.VisibilitaEliminaButton(true);
        sez_Bview.VisibilitaAggiungiButton(false);
        sez_Bview.VisibilitaUpdateButton(true);

        sez_Cview.Abilita_Disabilita_Campi(true);

        if (utilizzatore.equals("volontario"))
            sez_Dview.Abilita_Disabilita_Campi(true);

        Anagraficaview.VisibilitaModificaButton(false);

    }

    /**
     * Richiama i metodi di Update per aggiornare eventuali modifiche delle sezioni A,B,C(per il candidato)
     * A,B,C,D(per il volontario)
     */
    private void SalvaAction(){

        try {

            if(utilizzatore.equals("candidato")) {

                if (!VerificaCampiObbligatoriA())
                    throw new Exception("Completa campi obbligatori");


                boolean a,b,c;
                a = UpdateA();
                b = UpdateB();
                c = UpdateC();

                if(!a && !b && !c)
                    throw new Exception("Nessun cambiamento");

                basicframe.Message("Salvataggio avvenuto con successo");


            }else if(utilizzatore.equals("volontario")){

                if (!VerificaCampiObbligatoriA() && !VerificaCampiObbligatoriD())
                    throw new Exception("Completa campi obbligatori");

                boolean a,b,c,d;
                a = UpdateA();
                b = UpdateB();   //deve eseguirle le funzioni
                c = UpdateC();
                d = UpdateD();

                UpdateA();
                UpdateB();
                UpdateC();
                UpdateD();

                if(!a && !b && !c && !d)
                    throw new Exception("Nessun cambiamento");

                basicframe.Message("Salvataggio avvenuto con successo");

            }
        }catch (Exception es) {
            basicframe.ErrorMessage(es.getMessage());
        }

    }

    /**
     * Listener utilizzato esclusivamete dal Candidato.
     * Botton:Avanti, Indietro
     */
    private void VolontarioListner() {

        CardLayout CL = (CardLayout) Anagraficaview.getIntermedio1().getLayout();

        JButton sez_managerviewAvanti = Anagraficaview.getAvantiButton();
        JButton sez_managerviewIndietro = Anagraficaview.getIndietroButton();

        sez_managerviewIndietro.setContentAreaFilled(false);

        /*Avanti*/
        sez_managerviewAvanti.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (Pagine_Manager.getPagina_Corrente() < 4) {

                    CL.next(Anagraficaview.getIntermedio1());
                    Pagine_Manager.addPagina_Corrente();

                }
                if (Pagine_Manager.getPagina_Corrente() == 4)
                    sez_managerviewAvanti.setContentAreaFilled(false);


                if (Pagine_Manager.getPagina_Corrente() > 1)
                    sez_managerviewIndietro.setContentAreaFilled(true);
            }
        });


        /*Indietro*/
        sez_managerviewIndietro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (Pagine_Manager.getPagina_Corrente() > 1) {

                    CL.previous(Anagraficaview.getIntermedio1());
                    Pagine_Manager.subctractPagina_Corrente();
                }


				    /*significa che mi trovo alla prima pagina, e non potendo andare più indietro "opacizzo" indietro*/
                if (Pagine_Manager.getPagina_Corrente() == 1)
                    sez_managerviewIndietro.setContentAreaFilled(false);


                if (Pagine_Manager.getPagina_Corrente() >= 1)
                    sez_managerviewAvanti.setContentAreaFilled(true);


            }
        });
    }

    /**
     * Listener utilizzato escusivamente dall addetto giunta e archivista per uscire dalla sezione anagrafica
     */

    private  void AAListner(){

        JButton listaButton = Anagraficaview.getListaButton();
        listaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                basicframe.setdestra(listacandidatiview.getIntermedio0());
            }
        });


    }

    /**
     *
     * Salvataggio si preoccupa di verificare la compilazione,da parte dell utente di tutti i campi contrassegnati
     * come obbligatori, (per fare questo ricorre all utilizzo di un metodo privato VerificaCampiObbligatoriA().
     * Una volta accertata la compilazione avvia l inserimento nel DB
     */
    private void SalvataggioRegistrazione() {

        if (basicframe.OpotionalMessage("Una volta effettuato il salvataggio verrai\nrindirizzato" +
                "alla pagina login.Salvare?") == 0 && VerificaCampiObbligatoriA()) {

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
     * VerificaCampiObbligatoriA si preoccupa di controllare se l utente per sbaglio o volutamente abbia mancato
     * l inserimento dei campi necessari alla sua scheda anagrafica
     *
     * @return true, tutti i campi obbligatori sono completi
     */
    private boolean VerificaCampiObbligatoriA() {

        boolean controllo = false;

        try {
            if ((sez_Aview.getNometext().length() == 0)
                    || (sez_Aview.getCognometext().length() == 0)
                    || (sez_Aview.getDatadinascitatext().length() == 0)
                    || (sez_Aview.getIndirizzodiresidenzatext().length() == 0)
                    || (sez_Aview.getTelefonocellularetext().length() == 0)
                    || (sez_Aview.getTelefonofissotext().length() == 0)
                    || (sez_Aview.getUsernametext().length() == 0)
                    || (sez_Aview.getPasswordtext().length() == 0)
                    )
                throw new Exception("Completare tutti i campi obbligatori");

            if (sez_Aview.getUsernametext().length() > 20)
                throw new Exception("Username troppo lunga!\nMassimo 20 caratteri");

            if (sez_Aview.getPasswordtext().length() > 20)
                throw new Exception("Password troppo lunga!\nMassimo 20 caratteri");

            controllo = true;

        } catch (Exception e) {
            basicframe.ErrorMessage(e.getMessage());
        }

        return controllo;

    }

    /**
     * Verifica se i campi della sezione D sono stati completati da parte del utente
     *
     * @return true ,completati
     */
    private boolean VerificaCampiObbligatoriD(){

        boolean controllo = false;

        if(sez_Dview.getGStext().length() == 0)
            basicframe.ErrorMessage("Manca il gruppo sanguigno");
        else
            controllo = true;

        return controllo;

    }

    /**
     * Inizializza i vari campi testo delle sezioni A
     * Pone i vari campi delle sezioni A  non editabili
     */
    private void SettaggioCampi_A() {

        sez_Aview.setNometext(Utente.getNome());
        sez_Aview.setCognometext(Utente.getCognome());
        sez_Aview.setLuogodinascitatext(Utente.getLuogo_di_Nascita());
        sez_Aview.setTelefonofissotext(Utente.getTelefono_Fisso());
        sez_Aview.setTelefonocellularetext(Utente.getTelefono_Cellulare());
        sez_Aview.setDataDiNascitaComboBox(Utente.getData_di_Nascita().substring(0, 4), Utente.getData_di_Nascita().substring(5, 7), Utente.getData_di_Nascita().substring(8, 10));
        sez_Aview.setIndirizzodiresidenzatext(Utente.getIndirizzo_di_residenza());
        sez_Aview.setEmailtext(Utente.getEmail());
        sez_Aview.setProfessionetext(Utente.getProfessione());
        sez_Aview.setSpecializzazionetext(Utente.getEventuale_Specializzazione());
        sez_Aview.setUsernametext(Utente.getUsername());
        sez_Aview.setPasswordtext(Utente.getPassword());

        sez_Aview.Abilita_Disabilita_Campi(false);
    }

    /**
     * Inizializza i vari campi testo delle sezioni C
     * Pone i vari campi delle sezioni C non editabili
     */
    private void SettaggioCampi_C() {


        sez_Cview.setDenominazioneDatoreDiLavorotext(Utente.getDenominazione_Datore_di_Lavoro());
        sez_Cview.setTelDatoreDiLavorotext(Utente.getTelefono_Datore_Lavoro());
        sez_Cview.setFaxDatoreDiLavorotext(Utente.getFax_Datore_di_Lavoro());
        sez_Cview.setEmailDatoreDiLavorotext(Utente.getEmail_Datore_di_Lavoro());
        sez_Cview.setNumeroCodicePostaletext(Utente.getNumerocodicepostale());
        sez_Cview.setIbantext(Utente.getIBAN());

        sez_Cview.Abilita_Disabilita_Campi(false);

    }

    /**
     * Inizializza i vari campi testo delle sezioni D
     * Pone i vari campi delle sezioni D  non editabili
     */

    private void SettaggioCampi_D() {

        //conversione esplicita
        Volontario UTENTE = (Volontario) Utente;

        sez_Dview.setGStext(UTENTE.getGrupposanguigno());
        sez_Dview.setTagliaTestatext(UTENTE.getTagliatesta());
        sez_Dview.setTagliaBustotext(UTENTE.getTagliabusto());
        sez_Dview.setTagliaManotext(UTENTE.getTagliamano());
        sez_Dview.setTagliaPantalonitext(UTENTE.getTagliapantaloni());
        sez_Dview.setTagliaScarpetext(UTENTE.getTagliascarpe());

        sez_Dview.Abilita_Disabilita_Campi(false);

    }

    /**
     * Controllo eventuali aggiornamenti fatti dall utente  ai suoi dati riguardo la sezione A
     *
     * @return true  ,l utente ha effettuato delle modifiche che sono state saalvate con successo
     * @return false ,non c è stato nessun cambiamento
     */
    private boolean UpdateA() {

        boolean controllo = false;
        String[] appoggio = new String[3];

        appoggio[0] = "a";

        //A
        if (!sez_Aview.getNometext().equals(Utente.getNome())) {

            controllo = true;
            appoggio[1] = "nome";
            appoggio[2] = sez_Aview.getNometext();
            Utente.setNome(sez_Aview.getNometext());
            Utente.UpdateSQL(appoggio);
        }

        if (!sez_Aview.getCognometext().equals(Utente.getCognome())) {

            controllo = true;
            appoggio[1] = "cognome";
            appoggio[2] = sez_Aview.getCognometext();
            Utente.setCognome(sez_Aview.getCognometext());
            Utente.UpdateSQL(appoggio);
        }


        if (!sez_Aview.getLuogodinascitatext().equals(Utente.getLuogo_di_Nascita())) {

            controllo = true;
            appoggio[1] = "luogodinascita";
            appoggio[2] = sez_Aview.getLuogodinascitatext();
            Utente.setLuogo_di_Nascita(sez_Aview.getLuogodinascitatext());
            Utente.UpdateSQL(appoggio);

        }

        if (!sez_Aview.getDatadinascitatext().equals(Utente.getData_di_Nascita())) {

            controllo = true;
            appoggio[1] = "datadinascita";
            appoggio[2] = sez_Aview.getDatadinascitatext();
            Utente.setData_di_Nascita(sez_Aview.getDatadinascitatext());
            Utente.UpdateSQL(appoggio);

        }


        if (!sez_Aview.getIndirizzodiresidenzatext().equals(Utente.getIndirizzo_di_residenza())) {

            controllo = true;
            appoggio[1] = "indirizzodiresidenza";
            appoggio[2] = sez_Aview.getIndirizzodiresidenzatext();
            Utente.setIndirizzo_di_residenza(sez_Aview.getIndirizzodiresidenzatext());
            Utente.UpdateSQL(appoggio);

        }


        if (!sez_Aview.getTelefonofissotext().equals(Utente.getTelefono_Fisso())) {

            controllo = true;
            appoggio[1] = "telefonofisso";
            appoggio[2] = sez_Aview.getTelefonofissotext();
            Utente.setTelefono_Fisso(sez_Aview.getTelefonofissotext());
            Utente.UpdateSQL(appoggio);
        }


        if (!sez_Aview.getTelefonocellularetext().equals(Utente.getTelefono_Cellulare())) {

            appoggio[1] = "telefonomobile";
            appoggio[2] = sez_Aview.getTelefonocellularetext();
            Utente.setTelefono_Cellulare(sez_Aview.getTelefonocellularetext());
            Utente.UpdateSQL(appoggio);

        }


        if (!sez_Aview.getEmailtext().equals(Utente.getEmail())) {

            controllo = true;
            appoggio[1] = "email";
            appoggio[2] = sez_Aview.getEmailtext();
            Utente.setEmail(sez_Aview.getEmailtext());
            Utente.UpdateSQL(appoggio);

        }


        if (!sez_Aview.getProfessionetext().equals(Utente.getProfessione())) {

            controllo = true;
            appoggio[1] = "professione";
            appoggio[2] = sez_Aview.getProfessionetext();
            Utente.setProfessione(sez_Aview.getProfessionetext());
            Utente.UpdateSQL(appoggio);

        }

        if (!sez_Aview.getSpecializzazionetext().equals(Utente.getEventuale_Specializzazione())) {

            controllo = true;
            appoggio[1] = "eventualespecializzazione";
            appoggio[2] = sez_Aview.getSpecializzazionetext();
            Utente.setEventuale_Specializzazione(sez_Aview.getSpecializzazionetext());
            Utente.UpdateSQL(appoggio);

        }
        return controllo;
    }
    /**
     * Controllo eventuali aggiornamenti fatti dall utente  ai suoi dati riguardo la sezione B
     *
     * @return true , l utente ha effettuato delle modifiche che sono state saalvate con successo
     * @return false ,non c è stato nessun cambiamento
     */

    private boolean UpdateB() {

        boolean controllo = false;
        int i = 0;

        //B
        ArrayList<Certificazione> CERTIFICAZIONI = Utente.getCERTIFICAZIONI();

        while (i < CERTIFICAZIONI.size()) {

            if (CERTIFICAZIONI.get(i).getFlag().equals("elimina")) {
                controllo = true;
                CERTIFICAZIONI.get(i).DeleteSQL();
                CERTIFICAZIONI.remove(i);

            } else if (CERTIFICAZIONI.get(i).getFlag().equals("update")) {

                controllo = true;
                Certificazione certificazione = CERTIFICAZIONI.get(i);
                certificazione.updatesql();

            }
            i++;
        }

        return controllo;
    }

    /**
     * Controllo eventuali aggiornamenti fatti dall utente  ai suoi dati riguardo la sezione C
     *
     * @return true , l utente ha effettuato delle modifiche che sono state saalvate con successo
     * @return false ,non c è stato nessun cambiamento
     */

    private boolean UpdateC() {

        boolean controllo = false;
        String[] appoggio =  new String[3];

        appoggio[0] = "c";
        //C

        if (!sez_Cview.getDenominazioneDatoreDiLavorotext().equals(Utente.getDenominazione_Datore_di_Lavoro())) {

            controllo = true;
            appoggio[1] = "nomedatore";
            appoggio[2] = sez_Cview.getDenominazioneDatoreDiLavorotext();
            Utente.setDenominazione_Datore_di_Lavoro(sez_Cview.getDenominazioneDatoreDiLavorotext());
            Utente.UpdateSQL(appoggio);

        }

        if (!sez_Cview.getTelDatoreDiLavorotext().equals(Utente.getTelefono_Datore_Lavoro())) {

            controllo = true;
            appoggio[1] = "telefono";
            appoggio[2] = sez_Cview.getTelDatoreDiLavorotext();
            Utente.setTelefono_Datore_Lavoro(sez_Cview.getTelDatoreDiLavorotext());
            Utente.UpdateSQL(appoggio);

        }

        if (!sez_Cview.getFaxDatoreDiLavorotext().equals(Utente.getFax_Datore_di_Lavoro())) {

            controllo = true;
            appoggio[1] = "eventualespecializzazione";
            appoggio[2] = sez_Cview.getFaxDatoreDiLavorotext();
            Utente.setFax_Datore_di_Lavoro(sez_Cview.getFaxDatoreDiLavorotext());
            Utente.UpdateSQL(appoggio);

        }

        if (!sez_Cview.getEmailDatoreDiLavorotext().equals(Utente.getEmail_Datore_di_Lavoro())) {

            controllo = true;
            appoggio[1] = "email";
            appoggio[2] = sez_Cview.getEmailDatoreDiLavorotext();
            Utente.setEmail_Datore_di_Lavoro(sez_Cview.getEmailDatoreDiLavorotext());
            Utente.UpdateSQL(appoggio);

        }

        if (!sez_Cview.getNumeroCodicePostaletext().equals(Utente.getNumerocodicepostale())) {

            controllo = true;
            appoggio[1] = "numero_codice_postale";
            appoggio[2] = sez_Cview.getNumeroCodicePostaletext();
            Utente.setNumerocodicepostale(sez_Cview.getNumeroCodicePostaletext());
            Utente.UpdateSQL(appoggio);

        }

        if (!sez_Cview.getIbantext().equals(Utente.getIBAN())) {

            controllo = true;
            appoggio[1] = "iban";
            appoggio[2] = sez_Cview.getIbantext();
            Utente.setIBAN(sez_Cview.getIbantext());
            Utente.UpdateSQL(appoggio);

        }

        return controllo;
    }
    /**
     * Controllo eventuali aggiornamenti fatti dall utente  ai suoi dati riguardo la sezione d
     *
     * @return true, l utente ha effettuato delle modifiche che sono state saalvate con successo
     * @return false, non c è stato nessun cambiamento
     */

    private boolean UpdateD(){

        Volontario VOLONTARIO = (Volontario) Utente;
        boolean controllo = false;
        String[] appoggio =  new String[3];

        appoggio[0] = "d";
        //D

        if (!sez_Dview.getGStext().equals(VOLONTARIO.getGrupposanguigno())) {

            controllo = true;
            appoggio[1] = "grupposanguigno";
            appoggio[2] = sez_Dview.getGStext();
            VOLONTARIO.setGrupposanguigno(sez_Dview.getGStext());
            Utente.UpdateSQL(appoggio);

        }

        if (!sez_Dview.getTagliaTestatext().equals(VOLONTARIO.getTagliatesta())) {

            controllo = true;
            appoggio[1] = "tagliatesta";
            appoggio[2] = sez_Dview.getTagliaTestatext();
            VOLONTARIO.setTagliatesta(sez_Dview.getTagliaTestatext());
            Utente.UpdateSQL(appoggio);

        }

        if (!sez_Dview.getTagliaBustotext().equals(VOLONTARIO.getTagliabusto())) {

            controllo = true;
            appoggio[1] = "tagliabusto";
            appoggio[2] = sez_Dview.getTagliaBustotext();
            VOLONTARIO.setTagliabusto(sez_Dview.getTagliaBustotext());
            Utente.UpdateSQL(appoggio);

        }

        if (!sez_Dview.getTagliaManotext().equals(VOLONTARIO.getTagliamano())) {

            controllo = true;
            appoggio[1] = "tagliamano";
            appoggio[2] = sez_Dview.getTagliaManotext();
            VOLONTARIO.setTagliamano(sez_Dview.getTagliaManotext());
            Utente.UpdateSQL(appoggio);

        }

        if (!sez_Dview.getTagliaPantalonitext().equals(VOLONTARIO.getTagliapantaloni())) {

            controllo = true;
            appoggio[1] = "tagliapantaloni";
            appoggio[2] = sez_Dview.getTagliaPantalonitext();
            VOLONTARIO.setTagliapantaloni(sez_Dview.getTagliaPantalonitext());
            Utente.UpdateSQL(appoggio);

        }

        if (!sez_Dview.getTagliaScarpetext().equals(VOLONTARIO.getTagliascarpe())) {

            controllo = true;
            appoggio[1] = "tagliascarpe";
            appoggio[2] = sez_Dview.getTagliaScarpetext();
            VOLONTARIO.setTagliascarpe(sez_Dview.getTagliaScarpetext());
            Utente.UpdateSQL(appoggio);

        }

        if (!sez_Dview.getAbilitatext().equals(VOLONTARIO.getAbilita())) {

            controllo = true;
            appoggio[1] = "abilita";
            appoggio[2] = sez_Dview.getAbilitatext();
            VOLONTARIO.setAbilita(sez_Dview.getAbilitatext());
            Utente.UpdateSQL(appoggio);

        }

        return controllo;
    }

    /**
     * Effettua il salvatazggio in locale dei dati dell utente(relativi alla sezione D).
     * Effettua il salvataggio degli stesi dati nel DB.
     * Setta il primo accesso a no.
     *
     * @return
     */

    private boolean SalvataggioPrimoAccesso(){

        boolean controllo = false;

        String[] appoggio = new String[4];
        //conversione esplicita
        Volontario VOLONTARIO = (Volontario) Utente;

        VOLONTARIO.setGrupposanguigno(sez_Dview.getGStext());
        VOLONTARIO.setTagliatesta(sez_Dview.getTagliaTestatext());
        VOLONTARIO.setTagliabusto(sez_Dview.getTagliaBustotext());
        VOLONTARIO.setTagliamano(sez_Dview.getTagliaManotext());
        VOLONTARIO.setTagliapantaloni(sez_Dview.getTagliaPantalonitext());
        VOLONTARIO.setTagliascarpe(sez_Dview.getTagliaScarpetext());
        VOLONTARIO.setAbilita(sez_Dview.getAbilitatext());

        appoggio[0]= "pass";
        appoggio[1]= "primoaccesso";
        appoggio[2]= "no";

        if(VOLONTARIO.InsertSQL() && VOLONTARIO.UpdateSQL(appoggio))
            controllo = true;

        return controllo;

    }

    @Override
    public String toString() {

        return "Sez_ManagerController{}";

    }
}
