package View;

import javax.swing.*;

/**
 * CandidatoDestraView
 * View della parte destra della basicframe mostrata al login del Candidato
 */
public class CandidatoDestraView extends JPanel {

    private JPanel Intermedio0;
    private JLabel benvenutoAttendiLaConfermaLabel;

    public CandidatoDestraView() {

        setVisible(true);

    }

    public JPanel getIntermedio0() {

        return Intermedio0;

    }

    public void setBenvenutoAttendiLaConfermaLabel(String nuovoText) {

        benvenutoAttendiLaConfermaLabel.setText(nuovoText);

    }
}
