package View;

import javax.swing.*;


public class Sez_CView {

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

        return;
    }

    //GETTER
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
}
