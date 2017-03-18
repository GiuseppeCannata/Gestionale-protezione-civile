package View;

import javax.swing.*;

/**
 * CandidatoSinistraView
 * View mostrata alla sinastra della basicframe al Login del candidato
 */

public class CandidatoSinistraView extends JPanel{

    private JPanel Intermedio0;
    private JButton logoutButton;
    private JButton datiPersonaliButton;
    private JButton evolviButton;
    private JPanel Bottonbar;
    private JPanel Logopannel;


    public CandidatoSinistraView() {

        setVisible(true);

    }

    public JPanel getIntermedio0() {

        return Intermedio0;

    }

    public JButton getLogoutButton() {

        return logoutButton;

    }

    public JButton getDatiPersonaliButton() {

        return datiPersonaliButton;

    }

    public JButton getEvolviButton() {

        return evolviButton;

    }
}
