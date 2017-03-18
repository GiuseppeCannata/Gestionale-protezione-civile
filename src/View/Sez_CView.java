package View;

import javax.swing.*;

/**
 * Sez_CView
 * Form per la sezione C
 */

public class Sez_CView extends JPanel{

    private JPanel Intermedio0;
    private JTextField denominazioneDatoreDiLavorotext;
    private JTextField TelDatoreDiLavorotext;
    private JTextField faxDatoreDiLavorotext;
    private JTextField emailDatoreDiLavorotext;
    private JTextField numeroCodicePostaletext;
    private JTextField ibantext;
    private JLabel telDatoreDiLavoroLabel;
    private JLabel denominazioneomeDatoreDiLavoroLabel;
    private JLabel faxDatoreDiLavoroLabel;
    private JLabel emaiDatoreDiLavoroLabel;
    private JLabel numCodPostaleLabel;
    private JLabel IBANLabel;

    //Costruttore vuoto
    public Sez_CView() {

        setVisible(true);
    }


    //GETTER e SETTER
    public JPanel getIntermedio0() {

        return Intermedio0;
    }

    public String getDenominazioneDatoreDiLavorotext() {

        return denominazioneDatoreDiLavorotext.getText();
    }

    public String getTelDatoreDiLavorotext() {

        return TelDatoreDiLavorotext.getText();
    }

    public String getFaxDatoreDiLavorotext() {

        return faxDatoreDiLavorotext.getText();
    }

    public String getEmailDatoreDiLavorotext() {

        return emailDatoreDiLavorotext.getText();
    }

    public String getNumeroCodicePostaletext() {

        return numeroCodicePostaletext.getText();
    }

    public String getIbantext() {

        return ibantext.getText();
    }

    public void setDenominazioneDatoreDiLavorotext(String DenominazioneDatoreDiLavorotext) {

        denominazioneDatoreDiLavorotext.setText(DenominazioneDatoreDiLavorotext);

    }

    public void setTelDatoreDiLavorotext(String telDatoreDiLavorotext) {

        TelDatoreDiLavorotext.setText(telDatoreDiLavorotext);

    }

    public void setFaxDatoreDiLavorotext(String FaxDatoreDiLavorotext) {

        faxDatoreDiLavorotext.setText(FaxDatoreDiLavorotext);

    }

    public void setEmailDatoreDiLavorotext(String EmailDatoreDiLavorotext) {

       emailDatoreDiLavorotext.setText(EmailDatoreDiLavorotext);

    }

    public void setNumeroCodicePostaletext(String NumeroCodicePostaletext) {

        numeroCodicePostaletext.setText(NumeroCodicePostaletext);

    }

    public void setIbantext(String Ibantext) {

        ibantext.setText(Ibantext);

    }



    public void Abilita_Disabilita_Campi(boolean editabile){

        denominazioneDatoreDiLavorotext.setEditable(editabile);
        TelDatoreDiLavorotext.setEditable(editabile);
        faxDatoreDiLavorotext.setEditable(editabile);
        emailDatoreDiLavorotext.setEditable(editabile);
        numeroCodicePostaletext.setEditable(editabile);
        ibantext.setEditable(editabile);

    }

    @Override
    public String toString() {

        return "Sez_CView";

    }
}
