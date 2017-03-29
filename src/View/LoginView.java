package View;

import javax.swing.*;

/**
 * E' il pannello di login
 */
public class LoginView extends JPanel{

    private JPanel Intermedio0;
    private JPanel Bottonbar;
    private JPanel Intermedio1;
    private JTextField Usernametext;
    private JPasswordField passwordField1;
    private JButton registratiButton;
    private JButton accediButton;
    private JLabel usernameLabel;
    private JLabel passwordLabel;

    /*costruttore*/
    public LoginView() {

        setVisible(true);

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

    @Override
    public String toString() {

        return "LoginView";

    }
}
