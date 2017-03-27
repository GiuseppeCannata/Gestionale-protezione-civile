package Controller;

import Model.Certificazione;
import View.BasicFrameView;
import View.Sez_BView;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Sez_BRegistrazioneController --> controller per la sezione B in fase di registrazione
 */

public class Sez_BRegistrazioneController {

    private BasicFrameView basicframe;
    private String codicefiscale;
    private Sez_BView sez_Bview;
    private ArrayList<Certificazione> CERTIFICAZIONI;
    private int aggiungi;

    public Sez_BRegistrazioneController() {

        return;

    }

    public Sez_BRegistrazioneController(BasicFrameView frame, Sez_BView view2, String CodiceFiscale) {

        basicframe = frame;
        codicefiscale = CodiceFiscale;
        sez_Bview = view2;
        sez_Bview.VisibilitàEliminaButton(false);
        sez_Bview.VisibilitàUpdateButton(false);

        CERTIFICAZIONI = new ArrayList<>(25);

        aggiungi = 0;

        Listener();

    }

    /**
     * Ascolto azioni dell' utente
     * --> Aggiungi,boxcertificazioni
     */
    private void Listener(){

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
     * Metodo di servizio.
     * Si occupa di controllare se l utente clicca erroneamente sul Button Aggiungi senza aver selezionato una
     * certificazion oppure senza aver inserito un Ente di rilascio o N Documento.
     * In seguito alle dovute verifice può procedere con la memorizzazione degli inserimenti
     *
     */
    private void ControlloInserimento() {

        String ItemSelezionato = (String)sez_Bview.getBoxlist().getSelectedItem();

        try {

            if (aggiungi == 10)
                throw new Exception("stop inserimento");
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


    public ArrayList<Certificazione> getCERTIFICAZIONI() {

        return CERTIFICAZIONI;

    }

    @Override
    public String toString() {

        return "Sez_BRegistrazioneController";

    }
}