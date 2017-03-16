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


public class Sez_BCandidatoController {

    private BasicFrameView basicframe;
    private String codicefiscale;
    private int Indice;

    private Sez_BView sez_Bview;
    private ArrayList<Certificazione> CERTIFICAZIONI;


    public Sez_BCandidatoController(Sez_BView view2, ArrayList<Certificazione>  Certificazioni ,BasicFrameView frame,
                                    String CodiceFiscale) {


        sez_Bview = view2;
        CERTIFICAZIONI = Certificazioni;

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

                if(e.getSource() ==  sez_Bview.getCertif_Box() ) {

                    sez_Bview.Reset();
                    int i=0;

                    while(i < CERTIFICAZIONI.size()) {

                        if(boxcertificazioni.getSelectedItem().equals(CERTIFICAZIONI.get(i).getTipo()))
                        boxlist.addItem(CERTIFICAZIONI.get(i).getNome());

                        i++;
                    }
                }

            }
        });

       boxlist.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                if (e.getSource() == sez_Bview.getBoxlist() && sez_Bview.getBoxlist().getSelectedItem() != null) {

                    int i = 0;

                    while (i < CERTIFICAZIONI.size()) {

                        if (boxlist.getSelectedItem().equals(CERTIFICAZIONI.get(i).getNome())) {

                            sez_Bview.setComboboxDataAcquisizione(CERTIFICAZIONI.get(i).getDataacquisizione());
                            sez_Bview.setComboboxDataScadenza(CERTIFICAZIONI.get(i).getDatascadenza());

                            sez_Bview.setnDoc_Text(CERTIFICAZIONI.get(i).getEntedirilascio());
                            sez_Bview.setEnte_r_Text(CERTIFICAZIONI.get(i).getN_documento());
                        }
                        i++;
                    }
                }
            }
        });




       JButton UpdateButton = sez_Bview.getUpdateButton();
       UpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

               /** if(Ricerca());**/


            }

       });

        JButton EliminaButton = sez_Bview.getEliminaButton();
        EliminaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(sez_Bview.getBoxlist().getSelectedItem() == null)
                    basicframe.ErrorMessage("Nessuna certificazione selezionate");
                else
                    DeleteSQL();



            }

        });



    }


    private void DeleteSQL() {

        boolean controllo = false;
        int i = 0;
        String DaEliminare = (String) sez_Bview.getBoxlist().getSelectedItem();


        if(basicframe.OpotionalMessage("Sei sicuro di voler eliminare "+DaEliminare+"?") == 0){


            while (i < CERTIFICAZIONI.size() && !controllo) {

                if (CERTIFICAZIONI.get(i).getNome().equals(DaEliminare)) {

                    CERTIFICAZIONI.get(i).DeleteSQL();
                    CERTIFICAZIONI.remove(i);
                    controllo = true;
                   // System.out.println("vero");
                }
                i++;
            }
        }
    }

   /** private boolean Ricerca(){

        String DaUpdate = (String)sez_Bview.getBoxlist().getSelectedItem();
        String[] appoggio = new String[4];
        boolean controllo = false;
        int i=0;

        if (sez_Bview.getBoxlist().getSelectedItem().equals("ABILITAZIONE")) {

            while (i < CERTIFICAZIONI.size() && !controllo) {

                if (CERTIFICAZIONI.get(i).getNome().equals(DaUpdate)) {
                    controllo = true;
                    Indice= i;
                    ModificaAbilitazione();

                }
                i++;
            }
        } else if (sez_Bview.getCertif_Box().getSelectedItem().equals("CORSO")) {


            while (i < CORSI.size() && !controllo) {

                if (CORSI.get(i).getNome().equals(DaUpdate)){
                    controllo = true;
                    Indice= i;
                    ModificaCorso();
                    //System.out.println("vero");
                }

                i++;
            }
        } else if (sez_Bview.getCertif_Box().getSelectedItem().equals("PATENTE")) {

            while (i < PATENTI.size() && !controllo) {

                if (PATENTI.get(i).getNome().equals(DaUpdate)) {
                    controllo = true;
                    Indice = i;
                    ModificaPatente();
                }



                i++;
            }

        }

        return controllo;

    }

    private void ModificaAbilitazione(){

        Certificazione abilitazione = CERTIFICAZIONI.get(Indice);
        String[] appoggio = new String[2];


        if(!sez_Bview.getDataAcquisizone().equals(abilitazione.getDataacquisizione())){
            appoggio[0] = "dataaquisizione";
            appoggio[1] =  sez_Bview.getDataScadenza();
            abilitazione.UpdateSQL(appoggio);

        }


        if(!sez_Bview.getDataScadenza().equals(abilitazione.getDatascadenza())){
            appoggio[0] = "datascadenza";
            appoggio[1] =  sez_Bview.getDataScadenza();
            abilitazione.UpdateSQL(appoggio);

        }


        if(!sez_Bview.getEnte_r_Text().equals(abilitazione.getEntedirilascio())){
            appoggio[0] = "entedirilascio";
            appoggio[1] =  sez_Bview.getDataScadenza();
            abilitazione.UpdateSQL(appoggio);

        }

        if(!sez_Bview.getnDoc_Text().equals(abilitazione.getN_documento())){
            appoggio[0] = "n_documento";
            appoggio[1] =  sez_Bview.getDataScadenza();
            abilitazione.UpdateSQL(appoggio);

        }
    }

    private void ModificaPatente(){

        Patente patente = PATENTI.get(Indice);
        String[] appoggio = new String[2];


        if(!sez_Bview.getDataAcquisizone().equals(patente.getDataacquisizione())){
            appoggio[0] = "dataaquisizione";
            appoggio[1] =  sez_Bview.getDataScadenza();
            patente.UpdateSQL(appoggio);

        }


        if(!sez_Bview.getDataScadenza().equals(patente.getDatascadenza())){
            appoggio[0] = "datascadenza";
            appoggio[1] =  sez_Bview.getDataScadenza();
            patente.UpdateSQL(appoggio);

        }


        if(!sez_Bview.getEnte_r_Text().equals(patente.getEntedirilascio())){
            appoggio[0] = "entedirilascio";
            appoggio[1] =  sez_Bview.getDataScadenza();
            patente.UpdateSQL(appoggio);

        }

        if(!sez_Bview.getnDoc_Text().equals(patente.getN_documento())){
            appoggio[0] = "n_documento";
            appoggio[1] =  sez_Bview.getDataScadenza();
            patente.UpdateSQL(appoggio);

        }
    }

    private void ModificaCorso(){

        Certificazione abilitazione = CERTIFICAZIONI.get(Indice);
        String[] appoggio = new String[2];


        if(!sez_Bview.getDataAcquisizone().equals(abilitazione.getDataacquisizione())){
            appoggio[0] = "dataaquisizione";
            appoggio[1] =  sez_Bview.getDataScadenza();
            abilitazione.UpdateSQL(appoggio);

        }


        if(!sez_Bview.getDataScadenza().equals(abilitazione.getDatascadenza())){
            appoggio[0] = "datascadenza";
            appoggio[1] =  sez_Bview.getDataScadenza();
            abilitazione.UpdateSQL(appoggio);

        }


        if(!sez_Bview.getEnte_r_Text().equals(abilitazione.getEntedirilascio())){
            appoggio[0] = "entedirilascio";
            appoggio[1] =  sez_Bview.getDataScadenza();
            abilitazione.UpdateSQL(appoggio);

        }

        if(!sez_Bview.getnDoc_Text().equals(abilitazione.getN_documento())){
            appoggio[0] = "n_documento";
            appoggio[1] =  sez_Bview.getDataScadenza();
            abilitazione.UpdateSQL(appoggio);

        }
    }**/
}
