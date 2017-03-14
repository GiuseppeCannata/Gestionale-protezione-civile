package Controller;

import Model.Abilitazione;
import Model.Corso;
import Model.Patente;
import View.Sez_BView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;


public class Sez_BCandidatoController {

    private Sez_BView sez_Bview;
    private ArrayList<Abilitazione> ABILITAZIONI;
    private ArrayList<Corso> CORSI;
    private ArrayList<Patente> PATENTI;

    public Sez_BCandidatoController(Sez_BView view2, ArrayList<Abilitazione> Abilitazioni, ArrayList<Corso> Corsi, ArrayList<Patente> Patenti) {


        sez_Bview = view2;
        ABILITAZIONI = Abilitazioni;
        CORSI = Corsi;
        PATENTI = Patenti;
        Listner();


    }

    private void  Listner(){

        /*Aggiorna*/
        JButton Aggiorna = sez_Bview.getAggiornaButton();


        JComboBox boxcertificazioni = sez_Bview.getCertif_Box();
        JComboBox boxlist = sez_Bview.getBoxlist();
        boxcertificazioni.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                if(e.getSource() ==  sez_Bview.getCertif_Box()) {
                    if (boxcertificazioni.getSelectedItem().equals("ABILITAZIONE")) {

                        boxlist.removeAllItems();
                        int i=0;
                         while(i<ABILITAZIONI.size()) {
                             boxlist.addItem(ABILITAZIONI.get(i).getNome());
                             System.out.println(ABILITAZIONI.get(i).getDataacquisizione());
                             sez_Bview.setComboboxDataAcquisizione(ABILITAZIONI.get(i).getDataacquisizione());
                             sez_Bview.setComboboxDataScadenza(ABILITAZIONI.get(i).getDatascadenza());

                             sez_Bview.setnDoc_Text(ABILITAZIONI.get(i).getEntedirilascio());
                             sez_Bview.setEnte_r_Text(ABILITAZIONI.get(i).getN_documento());
                             i++;
                         }


                    }
                    else if(boxcertificazioni.getSelectedItem().equals("CORSO")){

                        boxlist.removeAllItems();
                        int i=0;
                        while(i<CORSI.size()){

                            boxlist.addItem(CORSI.get(i).getNome());
                            sez_Bview.setComboboxDataAcquisizione(CORSI.get(i).getDataacquisizione());
                            sez_Bview.setComboboxDataScadenza(CORSI.get(i).getDatascadenza());
                            sez_Bview.setnDoc_Text(CORSI.get(i).getEntedirilascio());
                            sez_Bview.setEnte_r_Text(CORSI.get(i).getN_documento());
                            i++;
                        }




                    }
                    else if (boxcertificazioni.getSelectedItem().equals("PATENTE")){

                        boxlist.removeAllItems();
                        int i=0;
                        while(i<PATENTI.size()){

                            boxlist.addItem(PATENTI.get(i).getNome());
                            sez_Bview.setComboboxDataAcquisizione(PATENTI.get(i).getDataacquisizione());
                            sez_Bview.setComboboxDataScadenza(PATENTI.get(i).getDatascadenza());
                            sez_Bview.setnDoc_Text(PATENTI.get(i).getEntedirilascio());
                            sez_Bview.setEnte_r_Text(PATENTI.get(i).getN_documento());
                            i++;
                        }


                    }
                }

            }
        });


    }
}
