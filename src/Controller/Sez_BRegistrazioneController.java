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


public class Sez_BRegistrazioneController {

    private String codicefiscale;

    private Sez_BView sez_Bview;
    private BasicFrameView basicframe;
    private int aggiorna;

    private ArrayList<Certificazione> listaCERTIFICAZIONI;


    public Sez_BRegistrazioneController(Sez_BView view2, BasicFrameView frame, String CodiceFiscale) {

        codicefiscale = CodiceFiscale;

        sez_Bview = view2;
        basicframe = frame;

        listaCERTIFICAZIONI = new ArrayList<>(25);


        sez_Bview.VisibilitàEliminaButton(false);

        aggiorna = 1;

        Listener();

    }

    private void Listener(){


        /*Aggiungi*/
        JButton Aggiungi = sez_Bview.getAggiungiButton();
        Aggiungi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Controllo();

            }

        });

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

                       // System.out.println("aaa");
                        boxlist.removeAllItems();
                        boxlist.addItem("utilizzo piatt elev");
                        boxlist.addItem("anti incendio");
                        boxlist.addItem("anti inc bos");
                        boxlist.addItem("anti inquinamento");
                        boxlist.addItem("marino");
                        boxlist.addItem("bls");
                        boxlist.addItem("blsd");
                        boxlist.addItem("cinofilo");

                    }
                    else if(boxcertificazioni.getSelectedItem().equals("corsi")){

                        boxlist.removeAllItems();
                        boxlist.addItem("corso base di radiocomunicazione");
                        boxlist.addItem("base protezione civil");
                        boxlist.addItem("fuoristrada");
                        boxlist.addItem("lingua ingles");
                        boxlist.addItem("logistica da campo");
                        boxlist.addItem("obbligatrion sic.vol. 81.08");
                        boxlist.addItem("rischio sanitari");
                        boxlist.addItem("sub");
                        boxlist.addItem("procedure amministrative");
                        boxlist.addItem("istruttore guida");
                        boxlist.addItem("vigli fuoco");

                    }
                    else if (boxcertificazioni.getSelectedItem().equals("patenti")){

                        boxlist.removeAllItems();
                        boxlist.addItem("ecdl");
                        boxlist.addItem("guida a");
                        boxlist.addItem("guida c");
                        boxlist.addItem("guida ce");
                        boxlist.addItem("guida d");
                        boxlist.addItem("guida k");
                        boxlist.addItem("guida kd");
                        boxlist.addItem("guida nautica");
                        boxlist.addItem("guida adr");
                        boxlist.addItem("guida mezzi pubblici");

                    }
                }

            }
        });


    }

    private void Controllo() {

        String Item = (String)sez_Bview.getBoxlist().getSelectedItem();

        try {
            if (aggiorna == 5)
                throw new Exception("stop inserimento");
            if (Item == null)
                throw  new Exception("Errore! nessuna certificazione scelta");
            if (sez_Bview.getEnte_r_Text().length() == 0 || sez_Bview.getnDoc_Text().length() == 0)
                throw new Exception("Errore! nessun ente e numero documento inserirti");


            aggiorna += 1;


            if (sez_Bview.getCertif_Box().getSelectedItem().equals("abilitazioni")) {

                Certificazione Certificazione = new Certificazione("abilitazioni",codicefiscale, sez_Bview.getNomeCertificazione(),
                        sez_Bview.getDataAcquisizone(), sez_Bview.getDataScadenza(), sez_Bview.getEnte_r_Text(),
                        sez_Bview.getnDoc_Text());


                listaCERTIFICAZIONI.add(Certificazione);
            }
            else if (sez_Bview.getCertif_Box().getSelectedItem().equals("corsi")) {

                Certificazione Certificazione = new Certificazione("corsi",codicefiscale, sez_Bview.getNomeCertificazione(),
                        sez_Bview.getDataAcquisizone(), sez_Bview.getDataScadenza(), sez_Bview.getEnte_r_Text(),
                        sez_Bview.getnDoc_Text());


                listaCERTIFICAZIONI.add(Certificazione);

            }

            else if (sez_Bview.getCertif_Box().getSelectedItem().equals("patenti")) {

                Certificazione Certificazione = new Certificazione("patenti",codicefiscale, sez_Bview.getNomeCertificazione(),
                        sez_Bview.getDataAcquisizone(), sez_Bview.getDataScadenza(), sez_Bview.getEnte_r_Text(),
                        sez_Bview.getnDoc_Text());


                listaCERTIFICAZIONI.add(Certificazione);

            }

            //RESET
            sez_Bview.HardReset();
        }
        catch(Exception e){

            basicframe.ErrorMessage(e.getMessage());

            }
    }



    public ArrayList<Certificazione> getListaCERTIFICAZIONI() {

        return listaCERTIFICAZIONI;

    }

}