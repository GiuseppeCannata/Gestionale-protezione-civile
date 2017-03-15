package View;

import javax.swing.*;


public class Sez_BView {

    private JComboBox certif_Box;
    private JComboBox anno_scad_Box;
    private JComboBox mese_scad_Box;
    private JComboBox giorno_scad_Box;
    private JComboBox anno_acq_Box;
    private JComboBox mese_acq_Box;
    private JComboBox giorno_acq_Box;
    private JComboBox boxlist;
    private JTextField ente_r_Text;
    private JTextField nDoc_Text;
    private JPanel Intermedio0;
    private JButton aggiungiButton;
    private JButton aggiornaButton;
    private JButton eliminaButton;


    public Sez_BView() {

        return;

    }

    //GETTER
    public JComboBox getBoxlist() {

        return boxlist;

    }
    public String getNomeCertificazione(){

        return (String) boxlist.getSelectedItem();

    }

    public JComboBox getCertif_Box() {

        return certif_Box;
    }



    public String getDataScadenza(){

        return (String)anno_scad_Box.getSelectedItem()+"-"+(String)mese_scad_Box.getSelectedItem()+"-"+
                (String)giorno_scad_Box.getSelectedItem();
    }

    public String getDataAcquisizone() {
        return (String)anno_acq_Box.getSelectedItem()+"-"+(String)mese_acq_Box.getSelectedItem()+"-"+(String)giorno_acq_Box.getSelectedItem();
    }

    public String getEnte_r_Text() {

        return ente_r_Text.getText();
    }

    public String getnDoc_Text() {

        return nDoc_Text.getText();
    }

    public JPanel getIntermedio0() {

        return Intermedio0;
    }
    public JButton getAggiungiButton() {

        return aggiungiButton;
    }

    public JButton getAggiornaButton() {
        return aggiornaButton;
    }

    public JButton getEliminaButton() {
        return eliminaButton;
    }

    //SETTER
    public void setEnte_r_Text(String Ente_r_Text) {

        ente_r_Text.setText(Ente_r_Text);

    }

    public void setnDoc_Text(String NDoc_Text) {

        nDoc_Text.setText(NDoc_Text);

    }

    public void VisibilitàAggiungiButton(boolean visibilita){

        aggiungiButton.setVisible(visibilita);

    }
    public void VisibilitàEliminaButton(boolean visibilita){

        eliminaButton.setVisible(visibilita);

    }
    public void VisibilitàAggiornaButton(boolean visibilita){

        aggiornaButton.setVisible(visibilita);

    }



    public void setComboboxDataAcquisizione(String Data){

        String anno, mese, giorno;
        anno = Data.substring(0,4);
        mese = Data.substring(5,7);
        giorno = Data.substring(8,10);
        System.out.println(giorno);
        anno_acq_Box.setSelectedItem(anno);
        mese_acq_Box.setSelectedItem(mese);
        giorno_acq_Box.setSelectedItem(giorno);


    }

    public void setComboboxDataScadenza(String Data){

        String anno, mese, giorno;
        anno = Data.substring(0,4);
        mese = Data.substring(5,7);
        giorno = Data.substring(8,10);
        anno_scad_Box.setSelectedItem(anno);
        mese_scad_Box.setSelectedItem(mese);
        giorno_scad_Box.setSelectedItem(giorno);

    }

    public void  Abilita_Disabilita_Campi(boolean editabile){

        ente_r_Text.setEditable(editabile);
        nDoc_Text.setEditable(editabile);
        anno_acq_Box.setEnabled(editabile);
        mese_acq_Box.setEnabled(editabile);
        giorno_acq_Box.setEnabled(editabile);
        anno_scad_Box.setEnabled(editabile);
        mese_scad_Box.setEnabled(editabile);
        giorno_scad_Box.setEnabled(editabile);

    }

    /**
     * Reset resetta,per l appunto, la pagina, cancellando i vari campi ogni volta che è stato eseguito un aggiorna
     */
    public void Reset(){

       //certif_Box.setSelectedIndex(0);
       // boxlist.removeAllItems();
        anno_scad_Box.setSelectedIndex(0);
        mese_scad_Box.setSelectedIndex(0);
        giorno_scad_Box.setSelectedIndex(0);
        anno_acq_Box.setSelectedIndex(0);
        mese_scad_Box.setSelectedIndex(0);
        giorno_scad_Box.setSelectedIndex(0);
        nDoc_Text.setText("");
        ente_r_Text.setText("");

    }

    public void HardReset(){

        certif_Box.setSelectedIndex(0);
        boxlist.removeAllItems();
        anno_scad_Box.setSelectedIndex(0);
        mese_scad_Box.setSelectedIndex(0);
        giorno_scad_Box.setSelectedIndex(0);
        anno_acq_Box.setSelectedIndex(0);
        mese_scad_Box.setSelectedIndex(0);
        giorno_scad_Box.setSelectedIndex(0);
        nDoc_Text.setText("");
        ente_r_Text.setText("");

    }
}





