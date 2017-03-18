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
 * Sez_BUtenteController --> Controller per la sezione B --> l utilizzatore è un utente gia registrato
 */

public class Sez_BUtenteController {

    private BasicFrameView basicframe;
    private String codicefiscale;
    private int Indice;

    private Sez_BView sez_Bview;
    private ArrayList<Certificazione> CERTIFICAZIONI;
    private JComboBox boxlist;

    /*COSTRUTTORE*/

    public Sez_BUtenteController(Sez_BView view2, ArrayList<Certificazione> Certificazioni, BasicFrameView frame,
                                 String CodiceFiscale) {


        sez_Bview = view2;
        CERTIFICAZIONI = Certificazioni;
        boxlist = sez_Bview.getBoxlist();
        basicframe = frame;
        codicefiscale = CodiceFiscale;
        sez_Bview.Abilita_Disabilita_Campi(false);
        sez_Bview.VisibilitàAggiungiButton(false);
        sez_Bview.VisibilitàUpdateButton(false);
        sez_Bview.VisibilitàEliminaButton(false);

        Listner();


    }

    /**
     * Ascolto azioni dell' utente --> boxcertificazioni,boxlist, Update,Elimina
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
                    int i = 0;

                    while (i < CERTIFICAZIONI.size()) {

                        if (boxcertificazioni.getSelectedItem().equals(CERTIFICAZIONI.get(i).getTipo())
                                && !CERTIFICAZIONI.get(i).getFlag().equals("elimina")) {
                            boxlist.addItem(CERTIFICAZIONI.get(i).getNome());
                        }

                        i++;
                    }
                }

            }
        });

        /*boxlist*/
        boxlist.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                if (e.getSource() == sez_Bview.getBoxlist() && sez_Bview.getBoxlist().getSelectedItem() != null) {

                    int i = 0;

                    while (i < CERTIFICAZIONI.size()) {

                        if (boxlist.getSelectedItem().equals(CERTIFICAZIONI.get(i).getNome())) {

                            sez_Bview.setComboboxDataAcquisizione(CERTIFICAZIONI.get(i).getDataacquisizione());
                            sez_Bview.setComboboxDataScadenza(CERTIFICAZIONI.get(i).getDatascadenza());

                            sez_Bview.setnDoc_Text(CERTIFICAZIONI.get(i).getN_documento());
                            sez_Bview.setEnte_r_Text(CERTIFICAZIONI.get(i).getEntedirilascio());
                        }
                        i++;
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

                }
                i++;
            }
        }
    }

    /**
     * Certca all interno dell ArrayList l indice della certificazione selezionata
     *
     * @return true --> trovata
     * @return false --> non trovata
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
     * Fa l update della certificazione in locale
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

}
