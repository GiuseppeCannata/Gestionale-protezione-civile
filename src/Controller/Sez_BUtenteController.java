package Controller;

import Model.Certificazione;
import View.BasicFrameView;
import View.Sez_BView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

/**
 * Controller per la sezione B l utilizzatore è un utente gia registrato
 */

public class Sez_BUtenteController {

    private BasicFrameView basicframe;
    private String codicefiscale;
    private int Indice;

    private Sez_BView sez_Bview;
    private ArrayList<Certificazione> CERTIFICAZIONI;
    private JComboBox boxlist;
    private int aggiungi;

    /*COSTRUTTORE*/

    /*costruttore vuoto*/
    public Sez_BUtenteController() {

        return;

    }
    //utente gia registrato
    public Sez_BUtenteController(Sez_BView view2, ArrayList<Certificazione> Certificazioni, BasicFrameView frame,
                                 String CodiceFiscale) {


        sez_Bview = view2;
        CERTIFICAZIONI = Certificazioni;
        boxlist = sez_Bview.getBoxlist();
        basicframe = frame;
        codicefiscale = CodiceFiscale;
        sez_Bview.Abilita_Disabilita_Campi(false);
        sez_Bview.VisibilitaAggiungiButton(false);
        sez_Bview.VisibilitaUpdateButton(false);
        sez_Bview.VisibilitaEliminaButton(false);


        Listner();


    }

   //registrazione
    public Sez_BUtenteController(BasicFrameView frame, Sez_BView view2, String CodiceFiscale) {

        basicframe = frame;
        codicefiscale = CodiceFiscale;
        sez_Bview = view2;
        sez_Bview.VisibilitaEliminaButton(false);
        sez_Bview.VisibilitaUpdateButton(false);

        CERTIFICAZIONI = new ArrayList<>(25);

        aggiungi = 0;

        ListenerRegistrazione();

    }


    /**
     * Ascolto azioni dell' utente
     * Botton:Aggiungi,boxcertificazioni
     */
    private void ListenerRegistrazione(){

        /*Aggiungi*/
        JButton Aggiungi = sez_Bview.getAggiungiButton();
        Aggiungi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ControlloInserimento();

            }

        });

        /*Boxcertificazioni*/
        JComboBox boxcertificazioni = sez_Bview.getCertif_Box();
        JComboBox boxlist = sez_Bview.getBoxlist();
        boxcertificazioni.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                if(e.getSource() ==  sez_Bview.getCertif_Box()) {
                    if (boxcertificazioni.getSelectedItem().equals("- CERTIFICAZIONI -")){

                        boxlist.removeAllItems();

                    }
                    else if (boxcertificazioni.getSelectedItem().equals("abilitazioni")) {

                        boxlist.removeAllItems();
                        boxlist.addItem("utilizzo piatt elev");
                        boxlist.addItem("anti incendio");
                        boxlist.addItem("anti inc bos");
                        boxlist.addItem("anti inquinamento");
                        boxlist.addItem("marino");
                        boxlist.addItem("bls");
                        boxlist.addItem("blsd");
                        boxlist.addItem("cinofilo");

                    } else if(boxcertificazioni.getSelectedItem().equals("corsi")){

                        boxlist.removeAllItems();
                        boxlist.addItem("base protezione civil");
                        boxlist.addItem("fuoristrada");
                        boxlist.addItem("lingua inglese");
                        boxlist.addItem("logistica da campo");
                        boxlist.addItem("rischio sanitari");
                        boxlist.addItem("sub");
                        boxlist.addItem("procedure amministrative");
                        boxlist.addItem("istruttore guida");
                        boxlist.addItem("vigli fuoco");

                    }else if (boxcertificazioni.getSelectedItem().equals("patenti")){

                        boxlist.removeAllItems();
                        boxlist.addItem("ecdl");
                        boxlist.addItem("guida a");
                        boxlist.addItem("guida c");
                        boxlist.addItem("guida ce");
                        boxlist.addItem("guida d");
                        boxlist.addItem("guida b");
                        boxlist.addItem("guida kd");
                        boxlist.addItem("guida nautica");
                        boxlist.addItem("guida adr");
                        boxlist.addItem("guida mezzi pubblici");

                    }
                }

            }
        });
    }

    /**
     * Si occupa di controllare se l utente clicca erroneamente sul Button Aggiungi senza aver selezionato una
     * certificazion oppure senza aver inserito un Ente di rilascio o N Documento.
     * In seguito alle dovute verifice può procedere con la memorizzazione degli inserimenti
     *
     */
    private void ControlloInserimento() {

        String ItemSelezionato = (String)sez_Bview.getBoxlist().getSelectedItem();

        try {
            //limite sulle certificazioni che un utente puo inserire
            if (aggiungi == 10)
                throw new Exception("stop inserimento");
            //nessuna certificazione selezionata
            if (ItemSelezionato == null)
                throw  new Exception("Errore! nessuna certificazione scelta");

            if (sez_Bview.getEnte_r_Text().length() == 0 || sez_Bview.getnDoc_Text().length() == 0)
                throw new Exception("Errore! nessun ente e numero documento inserirti");


            aggiungi += 1;

            if (sez_Bview.getCertif_Box().getSelectedItem().equals("abilitazioni")) {

                Certificazione Certificazione = new Certificazione("abilitazioni",codicefiscale, sez_Bview.getNomeCertificazione(),
                        sez_Bview.getDataAcquisizone(), sez_Bview.getDataScadenza(), sez_Bview.getEnte_r_Text(),
                        sez_Bview.getnDoc_Text());

                CERTIFICAZIONI.add(Certificazione);

            } else if (sez_Bview.getCertif_Box().getSelectedItem().equals("corsi")) {

                Certificazione Certificazione = new Certificazione("corsi",codicefiscale, sez_Bview.getNomeCertificazione(),
                        sez_Bview.getDataAcquisizone(), sez_Bview.getDataScadenza(), sez_Bview.getEnte_r_Text(),
                        sez_Bview.getnDoc_Text());


                CERTIFICAZIONI.add(Certificazione);

            } else if (sez_Bview.getCertif_Box().getSelectedItem().equals("patenti")) {

                Certificazione Certificazione = new Certificazione("patenti",codicefiscale, sez_Bview.getNomeCertificazione(),
                        sez_Bview.getDataAcquisizone(), sez_Bview.getDataScadenza(), sez_Bview.getEnte_r_Text(),
                        sez_Bview.getnDoc_Text());


                CERTIFICAZIONI.add(Certificazione);

            }

            //RESET
            sez_Bview.HardReset();
        }
        catch(Exception e){

            basicframe.ErrorMessage(e.getMessage());

        }
    }

    /**
     * Ascolto azioni dell' utente
     * Botton:boxcertificazioni,boxlist, Update,Elimina
     *
     */
    private void Listner() {

        /*Boxcertificazioni*/
        JComboBox boxcertificazioni = sez_Bview.getCertif_Box();
        JComboBox boxlist = sez_Bview.getBoxlist();
        boxcertificazioni.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                if (e.getSource() == sez_Bview.getCertif_Box()) {

                    sez_Bview.Reset();

                    for(Certificazione certificazione : CERTIFICAZIONI) {

                        if (boxcertificazioni.getSelectedItem().equals(certificazione.getTipo())
                                && !certificazione.getFlag().equals("elimina")) {
                            boxlist.addItem(certificazione.getNome());
                        }


                    }
                }

            }
        });

        /*boxlist*/
        boxlist.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                if (e.getSource() == sez_Bview.getBoxlist() && sez_Bview.getBoxlist().getSelectedItem() != null) {


                    for(Certificazione certificazione : CERTIFICAZIONI) {

                        if (boxlist.getSelectedItem().equals(certificazione.getNome())) {

                            sez_Bview.setComboboxDataAcquisizione(certificazione.getDataacquisizione());
                            sez_Bview.setComboboxDataScadenza(certificazione.getDatascadenza());

                            sez_Bview.setnDoc_Text(certificazione.getN_documento());
                            sez_Bview.setEnte_r_Text(certificazione.getEntedirilascio());
                        }
                    }
                }
            }
        });


        /*Update*/
        JButton UpdateButton = sez_Bview.getUpdateButton();
        UpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (Ricerca()) {

                    ModificaCertificazione();
                    sez_Bview.HardReset();

                }


            }

        });

        /*Elimina*/
        JButton EliminaButton = sez_Bview.getEliminaButton();
        EliminaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (sez_Bview.getBoxlist().getSelectedItem() == null)
                    basicframe.ErrorMessage("Nessuna certificazione selezionate");
                else
                   EliminaCertificazione();
                      // sez_Bview.HardReset();


            }

        });
    }

    /**
     * Elimina la certificazione in locale;
     * Con "in locale" si intende che questo metodo tagga la gertificazione come "elimina"
     * ma verra veramente eliminata (nel DB) quando l utente cliccherà su salva
     *
     */
    private void EliminaCertificazione() {

        boolean controllo = false;
        int i = 0;

        String DaEliminare = (String) sez_Bview.getBoxlist().getSelectedItem();


        if (basicframe.OpotionalMessage("Sei sicuro di voler eliminare " + DaEliminare + "?") == 0) {


            while (i < CERTIFICAZIONI.size() && !controllo) {

                if (CERTIFICAZIONI.get(i).getNome().equals(DaEliminare)) {

                    CERTIFICAZIONI.get(i).setFlag("elimina");

                    controllo = true;
                    boxlist.removeItem(DaEliminare);
                    sez_Bview.HardReset();

                }
             i++;
            }
        }
    }

    /**
     * Certca all interno dell ArrayList l indice della certificazione selezionata
     *
     * @return true ,trovata
     * @return false ,non trovata
     */

    private boolean Ricerca(){

        String DaUpdate = (String)sez_Bview.getBoxlist().getSelectedItem();
        boolean controllo = false;

        int i=0;

            while (i < CERTIFICAZIONI.size() && !controllo) {

                if (CERTIFICAZIONI.get(i).getNome().equals(DaUpdate)) {

                    controllo = true;
                    Indice= i;

                }
             i++;
            }

        return controllo;

    }

    /**
     * Fa l update della certificazione in locale.
     * Con "in locale" si intende che questo metodo tagga la gertificazione come "update"
     * ma verra veramente aggiornata ( nel DB) quando l utente cliccherà su salva
     */

    private void ModificaCertificazione(){

        Certificazione certificazione = CERTIFICAZIONI.get(Indice);

        System.out.println(certificazione.getNome());
        if(!sez_Bview.getDataAcquisizone().equals(certificazione.getDataacquisizione())
                || !sez_Bview.getDataScadenza().equals(certificazione.getDatascadenza())
                || !sez_Bview.getEnte_r_Text().equals(certificazione.getEntedirilascio())
                || !sez_Bview.getnDoc_Text().equals(certificazione.getN_documento())){

            certificazione.setFlag("update");
            certificazione.setDataacquisizione(sez_Bview.getDataAcquisizone());
            certificazione.setDatascadenza(sez_Bview.getDataScadenza());
            certificazione.setEntedirilascio(sez_Bview.getEnte_r_Text());
            certificazione.setN_documento(sez_Bview.getnDoc_Text());
        }
    }


    //GETTER
    public ArrayList<Certificazione> getCERTIFICAZIONI() {

        return CERTIFICAZIONI;

    }

    @Override
    public String toString() {

        return "Sez_BUtenteController{}";

    }

    //se hanno lo stesso pannello controllano la stessa GUI
    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sez_BUtenteController that = (Sez_BUtenteController) o;

        return sez_Bview != null ? sez_Bview.equals(that.sez_Bview) : that.sez_Bview == null;
    }

}
