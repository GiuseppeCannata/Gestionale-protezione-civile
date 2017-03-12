package View;

import javax.swing.*;


public class Sez_BView {

    private JComboBox certif_Box;
    private JComboBox anno_scad_Box;
    private JComboBox mese_scad_Box;
    private JTextField ente_r_Text;
    private JTextField nDoc_Text;
    private JComboBox giorno_scad_Box;
    private JComboBox anno_acq_Box;
    private JComboBox mese_acq_Box;
    private JComboBox giorno_acq_Box;
    private JPanel Intermedio0;
    private JButton aggiornaButton;
    private JComboBox boxlist;

    public Sez_BView() {

        return;

    }

    public JComboBox getBoxlist() {

        return boxlist;

    }
    public String NomeCertificazione(){

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

    public JButton getAggiornaButton() {

        return aggiornaButton;
    }



    public void Reset(){

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





