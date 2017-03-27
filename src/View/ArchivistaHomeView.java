package View;

import javax.swing.*;

/**
 * View della home del copito archivista
 */
public class ArchivistaHomeView extends JPanel {

    private JPanel Intermedio0;

    private JButton listaCandidatiButton;
    private JButton tuttiIVolontariButton;


    public ArchivistaHomeView(){

        setVisible(true);

    }

    public JButton getCandidatiButtonButton() {
        return listaCandidatiButton;
    }

    public JButton gettuttiIVolontariButton() {
        return tuttiIVolontariButton;
    }

    public JPanel getIntermedio0() {
        return Intermedio0;
    }

    @Override
    public String toString() {
        return "ArchivistaHomeView{}";
    }
}
