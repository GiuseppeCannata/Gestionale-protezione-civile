package View;

import javax.swing.*;

/**
 * Form per la sezione A
 */

public class Sez_AView extends JPanel{

    private JPanel Intermedio0;
    private JTextField nometext;
    private JTextField cognometext;
    private JTextField luogodinascitatext;
    private JTextField indirizzodiresidenzatext;
    private JTextField telefonofissotext;
    private JTextField telefonocellularetext;
    private JTextField emailtext;
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

        setVisible(true);

    }


    //GETTER e SETTER
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

       return (String)comboBox1.getSelectedItem()+"-"+(String)comboBox2.getSelectedItem()+"-"+(String)comboBox3.getSelectedItem();

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

    public void setNometext(String Nometext) {

        nometext.setText(Nometext);

    }

    public void setCognometext(String Cognometext) {

        cognometext.setText(Cognometext);

    }

    public void setLuogodinascitatext(String Luogodinascitatext) {

        luogodinascitatext.setText(Luogodinascitatext);

    }


    public void setIndirizzodiresidenzatext(String Indirizzodiresidenzatext) {

        indirizzodiresidenzatext.setText(Indirizzodiresidenzatext);

    }

    public void setTelefonofissotext(String Telefonofissotext) {

        telefonofissotext.setText(Telefonofissotext);

    }

    public void setTelefonocellularetext(String Telefonocellularetext) {

        telefonocellularetext.setText(Telefonocellularetext);

    }

    public void setEmailtext(String Emailtext) {

        emailtext.setText(Emailtext);

    }


    public void setProfessionetext(String Professionetext) {

       professionetext.setText(Professionetext);

    }

    public void setSpecializzazionetext(String Specializzazionetext) {

        specializzazionetext.setText(Specializzazionetext);

    }

    public void setPasswordtext(String Passwordtext) {

       passwordtext.setText(Passwordtext);


    }

    public void setUsernametext(String Usernametext) {

        usernametext.setText(Usernametext);

    }

    public void setDataDiNascitaComboBox(String anno, String mese, String giorno){

        comboBox1.setSelectedItem(anno);
        comboBox2.setSelectedItem(mese);
        comboBox3.setSelectedItem(giorno);

    }

    /*metodi per la visibilita dei vari elementi della view*/
    public void VisibilitaPasswordLT(boolean visibile){

        passwordLabel.setVisible(visibile);
        passwordtext.setVisible(visibile);

    }

    //L-->label
    //T-->text
    public void VisibilitaUserLT(boolean visibile){

        usernameLabel.setVisible(visibile);
        usernametext.setVisible(visibile);

    }

    //Abilita o disabilita l editabilita dei campi di questa sezione
    public void Abilita_Disabilita_Campi(boolean editabile){

         nometext.setEditable(editabile);
         cognometext.setEditable(editabile);
         luogodinascitatext.setEditable(editabile);
         indirizzodiresidenzatext.setEditable(editabile);
         telefonofissotext.setEditable(editabile);
         telefonocellularetext.setEditable(editabile);
         emailtext.setEditable(editabile);
         professionetext.setEditable(editabile);
         specializzazionetext.setEditable(editabile);
         passwordtext.setEditable(editabile);
         usernametext.setEditable(editabile);

         comboBox1.setEnabled(editabile);
         comboBox2.setEnabled(editabile);
         comboBox3.setEnabled(editabile);

    }

    @Override
    public String toString() {

        return "Sez_AView";

    }
}
