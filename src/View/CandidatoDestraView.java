package View;

import javax.swing.*;

/**
 * CandidatoDestraView
 * View della parte destra della basicframe mostrata al login del Candidato
 */
public class CandidatoDestraView extends JPanel {

    private JPanel Intermedio0;
    private JLabel MessaggioaSchermo;

    public CandidatoDestraView() {

        setVisible(true);

    }

    public JPanel getIntermedio0() {

        return Intermedio0;

    }

    public void setMessaggioaSchermo(String nuovoText) {

        MessaggioaSchermo.setText(nuovoText);

    }

    public void MessaggioSchermo(String testo){

        MessaggioaSchermo.setText(testo);

    }

    @Override
    public String toString() {

        return "CandidatoDestraView";

    }
}
