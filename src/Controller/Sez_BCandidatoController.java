package Controller;

import Model.Abilitazione;
import Model.Corso;
import Model.Patente;
import View.BasicFrameView;
import View.Sez_BView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;


public class Sez_BCandidatoController {

    private BasicFrameView basicframe;
    private String codicefiscale;

    private Sez_BView sez_Bview;
    private ArrayList<Abilitazione> ABILITAZIONI;
    private ArrayList<Corso> CORSI;
    private ArrayList<Patente> PATENTI;

    public Sez_BCandidatoController(Sez_BView view2, ArrayList<Abilitazione> Abilitazioni, ArrayList<Corso> Corsi,
                                    ArrayList<Patente> Patenti,BasicFrameView frame,String CodiceFiscale) {


        sez_Bview = view2;
        ABILITAZIONI = Abilitazioni;
        CORSI = Corsi;
        PATENTI = Patenti;
        basicframe = frame;
        codicefiscale = CodiceFiscale;
        sez_Bview.VisibilitàAggiungiButton(false);
        sez_Bview.VisibilitàEliminaButton(false);

        Listner();


    }

    private void  Listner(){

        JComboBox boxcertificazioni = sez_Bview.getCertif_Box();
        JComboBox boxlist = sez_Bview.getBoxlist();
        boxcertificazioni.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                if(e.getSource() ==  sez_Bview.getCertif_Box()) {
                    if (boxcertificazioni.getSelectedItem().equals("ABILITAZIONE")) {

                        sez_Bview.Reset();

                        int i=0;
                         while(i < ABILITAZIONI.size()) {


                             boxlist.addItem(ABILITAZIONI.get(i).getNome());
                             i++;
                         }


                    }
                    else if(boxcertificazioni.getSelectedItem().equals("CORSO")){

                        sez_Bview.Reset();


                        int i=0;
                        while(i < CORSI.size()){

                            boxlist.addItem(CORSI.get(i).getNome());
                            i++;
                        }

                    }
                    else if (boxcertificazioni.getSelectedItem().equals("PATENTE")){

                        sez_Bview.Reset();
                        int i=0;
                        while(i<PATENTI.size()){

                            boxlist.addItem(PATENTI.get(i).getNome());

                            i++;
                        }
                    }
                }

            }
        });

       boxlist.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                if(e.getSource() ==  sez_Bview.getBoxlist() && sez_Bview.getBoxlist().getSelectedItem() !=null ) {

                    int i= 0;

                    while(i < ABILITAZIONI.size()){

                       if (boxlist.getSelectedItem().equals(ABILITAZIONI.get(i).getNome())) {

                            sez_Bview.setComboboxDataAcquisizione(ABILITAZIONI.get(i).getDataacquisizione());
                            sez_Bview.setComboboxDataScadenza(ABILITAZIONI.get(i).getDatascadenza());

                            sez_Bview.setnDoc_Text(ABILITAZIONI.get(i).getEntedirilascio());
                            sez_Bview.setEnte_r_Text(ABILITAZIONI.get(i).getN_documento());
                        }
                        i++;
                    }


                    i= 0;

                    while(i < CORSI.size()){

                        if (boxlist.getSelectedItem().equals(CORSI.get(i).getNome())){

                            sez_Bview.setComboboxDataAcquisizione(CORSI.get(i).getDataacquisizione());
                            sez_Bview.setComboboxDataScadenza(CORSI.get(i).getDatascadenza());
                            sez_Bview.setnDoc_Text(CORSI.get(i).getEntedirilascio());
                            sez_Bview.setEnte_r_Text(CORSI.get(i).getN_documento());


                        }
                        i++;
                    }

                    i= 0;

                    while(i < PATENTI.size()){

                        if (boxlist.getSelectedItem().equals(PATENTI.get(i).getNome())){

                            sez_Bview.setComboboxDataAcquisizione(PATENTI.get(i).getDataacquisizione());
                            sez_Bview.setComboboxDataScadenza(PATENTI.get(i).getDatascadenza());
                            sez_Bview.setnDoc_Text(PATENTI.get(i).getEntedirilascio());
                            sez_Bview.setEnte_r_Text(PATENTI.get(i).getN_documento());


                        }
                        i++;
                    }


                }




            }
        });




       JButton AggiungiButton = sez_Bview.getAggiungiButton();
       AggiungiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


            }

       });

        JButton EliminaButton = sez_Bview.getEliminaButton();
        EliminaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(sez_Bview.getBoxlist().getSelectedItem()!= null) {

                    DeleteSQL();

                }

            }

        });



    }


    private void DeleteSQL() {


        boolean controllo = false;
        int i = 0;
        String DaEliminare = (String) sez_Bview.getBoxlist().getSelectedItem();


        if(basicframe.OpotionalMessage("Si sicuro di voler eliminare "+DaEliminare+"?") == 0)
        if (sez_Bview.getCertif_Box().getSelectedItem().equals("ABILITAZIONE")) {

            while (i < ABILITAZIONI.size() && !controllo) {

                if (ABILITAZIONI.get(i).getNome().equals(DaEliminare)) {

                    ABILITAZIONI.get(i).DeleteSQL();
                    ABILITAZIONI.remove(i);
                    controllo = true;
                   // System.out.println("vero");
                }
                i++;
            }
        } else if (sez_Bview.getCertif_Box().getSelectedItem().equals("CORSO")) {


            while (i < CORSI.size() && !controllo) {

                if (CORSI.get(i).getNome().equals(DaEliminare)) {

                    CORSI.get(i).DeleteSQL();
                    CORSI.remove(i);
                    controllo = true;
                    //System.out.println("vero");

                }
                i++;
            }
        } else if (sez_Bview.getCertif_Box().getSelectedItem().equals("PATENTE")) {

            while (i < PATENTI.size() && !controllo) {

                if (PATENTI.get(i).getNome().equals(DaEliminare)) {

                    System.out.println(PATENTI.get(i).getNome());

                    PATENTI.get(i).DeleteSQL();
                    PATENTI.remove(i);
                    controllo = true;
                    System.out.println("vero");

                }
                i++;
            }

        }
    }
}
