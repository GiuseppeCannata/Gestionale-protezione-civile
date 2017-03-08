package View;

import javax.swing.*;
import java.sql.Date;


public class Sez_AView{

    private JPanel Intermedio0;
    private JTextField nometext;
    private JTextField cognometext;
    private JTextField luogodinascitatext;
    private JTextField datadinascitatext;
    private JTextField indirizzodiresidenzatext;
    private JTextField telefonofissotext;
    private JTextField telefonocellularetext;
    private JTextField emailtext;
    private JTextField codicefiscaletext;
    private JTextField professionetext;
    private JTextField specializzazionetext;
    private JTextField datatext;
    private JTextField passwordtext;
    private JTextField usernametext;
    private JTextField annoText;
    private JTextField meseText;
    private JTextField giornoText;
    private JTextField annonascitaText;
    private JTextField mesenascitaText;
    private JTextField giornonascitaText;

    private String[] VectorA;
    private JLabel nomeLabel;
    private JLabel cognomeLabel;
    private JLabel luogoDiNascitaLabel;
    private JLabel dataDiNascitaLabel;
    private JLabel indirizzoDiResidenzaLabel;
    private JLabel telefonoFissoLabel;
    private JLabel telefonoCellullareLabel;
    private JLabel codiceFiscaleLabel;
    private JLabel professioneLabel;
    private JLabel specializzazioneLabel;
    private JLabel dataLabel;
    private JLabel emailLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;



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

        return annonascitaText+"-"+mesenascitaText+"-"+giornonascitaText;
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

    public String getCodicefiscaletext() {

        return codicefiscaletext.getText();
    }

    public String getProfessionetext() {

        return professionetext.getText();
    }

    public String getSpecializzazionetext() {

        return specializzazionetext.getText();
    }

    public String getDatatext() {

        return annoText+"-"+meseText+"-"+giornoText;
    }

    public String getPasswordtext() {
        return passwordtext.getText();
    }

    public String getUsernametext() {

        return usernametext.getText();
    }


}
