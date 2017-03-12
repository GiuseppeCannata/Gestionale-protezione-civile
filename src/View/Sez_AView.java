package View;

import javax.swing.*;


public class Sez_AView{

    private JPanel Intermedio0;
    private JTextField nometext;
    private JTextField cognometext;
    private JTextField luogodinascitatext;
    private JTextField indirizzodiresidenzatext;
    private JTextField telefonofissotext;
    private JTextField telefonocellularetext;
    private JTextField emailtext;
    private JTextField codicefiscaletext;
    private JTextField professionetext;
    private JTextField specializzazionetext;
    private JTextField passwordtext;
    private JTextField usernametext;


    private JLabel nomeLabel;
    private JLabel cognomeLabel;
    private JLabel luogoDiNascitaLabel;
    private JLabel dataDiNascitaLabel;
    private JLabel indirizzoDiResidenzaLabel;
    private JLabel telefonoFissoLabel;
    private JLabel telefonoCellullareLabel;
    private JLabel professioneLabel;
    private JLabel specializzazioneLabel;
    private JLabel emailLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;

    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;



    //Costruttore vuoto
    public Sez_AView() {

        return;

    }


    /*GETTER*/
    public JPanel getIntermedio0() {


        return Intermedio0;
    }

    public String getNometext() {


        return nometext.getText();
    }

    public String getCognometext() {

        return cognometext.getText();

    }

    public String getLuogodinascitatext() {

        return luogodinascitatext.getText();
    }


    public String getDatadinascitatext() {

        return (String)comboBox1.getSelectedItem()+"-"+ (String)comboBox2.getSelectedItem()+"-"+(String)comboBox3.getSelectedItem();

    }

    public String getIndirizzodiresidenzatext() {

        return indirizzodiresidenzatext.getText();
    }

    public String getTelefonofissotext() {

        return telefonofissotext.getText();
    }

    public String getTelefonocellularetext() {

        return telefonocellularetext.getText();
    }

    public String getEmailtext() {

        return emailtext.getText();
    }

    public String getProfessionetext() {

        return professionetext.getText();
    }

    public String getSpecializzazionetext() {

        return specializzazionetext.getText();
    }



    public String getPasswordtext() {

        return passwordtext.getText();

    }

    public String getUsernametext() {

        return usernametext.getText();
    }


}
