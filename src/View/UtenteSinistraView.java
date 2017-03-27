package View;

import javax.swing.*;

/**
 * UtenteSinistraView
 * View mostrata alla sinastra della basicframe al Login del candidato
 */

public class UtenteSinistraView extends JPanel{

    private JPanel Intermedio0;
    private JButton logoutButton;
    private JButton datiPersonaliButton;
    private JButton evolviButton;
    private JPanel Bottonbar;
    private JPanel Logopannel;
    private JButton homeButton;


    public UtenteSinistraView() {

        setVisible(true);

    }

    //GETTER  e SETTER
    public JPanel getIntermedio0() {

        return Intermedio0;

    }

    public JButton getLogoutButton() {

        return logoutButton;

    }

    public JButton getHomeButton() {

        return homeButton;

    }

    public JButton getDatiPersonaliButton() {

        return datiPersonaliButton;

    }

    public JButton getEvolviButton() {

        return evolviButton;

    }

    public void setEvolviButton(boolean visibilita) {

        evolviButton.setVisible(visibilita);
    }

    @Override
    public String toString() {
        return "UtenteSinistraView{}";
    }
}
