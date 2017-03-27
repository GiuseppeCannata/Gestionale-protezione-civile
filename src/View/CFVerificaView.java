package View;

import javax.swing.*;

/**
 * CFVerificaView estende JPanel.
 * E' il pannello di verifica dell codice fiscale
 */

public class CFVerificaView extends JPanel {

    private JPanel Intermedio0;
    private JButton paginaLoginButton;
    private JButton okButton;
    private JTextField textField1;

    public CFVerificaView() {

        setVisible(true);

    }

    //GETTER
    public JPanel getIntermedio0() {

        return Intermedio0;

    }

    public String getText(){

        return textField1.getText();

    }

    public JButton getOkButton() {

        return okButton;

    }

    public JButton getpaginaLoginButton() {

        return paginaLoginButton;

    }

    @Override
    public String toString() {
        return "CFVerificaView{}";
    }
}
