package View;

import javax.swing.*;


public class LoginView {

    private JPanel Intermedio0;
    private JPanel Bottonbar;
    private JButton registratiButton;
    private JPanel Intermedio1;
    private JTextField Usernametext;
    private JPasswordField passwordField1;
    private JButton accediButton;
    private JLabel usernameLabel;
    private JLabel passwordLabel;

    public LoginView() {

        return;

    }




    public void ErrorMessage(BasicFrameView frame,String nomeErrore){
        JOptionPane.showMessageDialog(frame, nomeErrore, "Warning!", JOptionPane.ERROR_MESSAGE);
    }



    /*GETTER*/
    public JPanel getIntermedio0() {

        return Intermedio0;

    }

    public JButton getRegistratiButton() {

        return registratiButton;

    }

    public JButton getAccediButton() {

        return accediButton;

    }

    public String getUsernametext() {
        return Usernametext.getText();
    }

    public char[] getPasswordField1() {
        return passwordField1.getPassword();
    }
}
