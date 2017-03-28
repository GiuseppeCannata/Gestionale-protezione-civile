package View;

import javax.swing.*;

/**
 * View per il compito Maste Chief
 */
public class MCView extends JPanel {

    private JPanel Intermedio0;
    private JComboBox Box1;
    private JButton assegnaCompitoButton;
    private JList lista;
    private JComboBox Box2;
    private JButton rimuoviCompitoButton;
    private JButton assegnaRuoloButton;
    private JButton rimuoviRuoloButton;
    private JButton RitornaButton;

    public MCView() {

        assegnaRuoloButton.setVisible(false);
        assegnaCompitoButton.setVisible(false);
        rimuoviCompitoButton.setVisible(false);
        rimuoviRuoloButton.setVisible(false);
        setVisible(true);

    }

    public JPanel getIntermedio0() {

        return Intermedio0;

    }

    public JComboBox getBox1() {

        return Box1;

    }

    public JButton getAssegnaRuoloButton() {

        return assegnaRuoloButton;

    }

    public JButton getRimuoviRuoloButton() {

        return rimuoviRuoloButton;

    }

    public JButton getAssegnaCompitoButton() {

        return assegnaCompitoButton;

    }

    public JList getLista() {

        return lista;

    }

    public JButton getRitornaButton() {

        return RitornaButton;

    }

    public JComboBox getBox2() {

        return Box2;

    }

    public JButton getRimuoviCompitoButton() {

        return rimuoviCompitoButton;

    }

    /*metodi per la visibilita dei vari elementi della view*/
    public void VisibilitaRimuoviCompito(boolean visibilita) {

        rimuoviCompitoButton.setVisible(visibilita);

    }
    public void VisibilitaAssegnaCompito(boolean visibilita) {

        assegnaCompitoButton.setVisible(visibilita);

    }

    public void VisibilitaRimuoviRuolo(boolean visibilita) {

        rimuoviRuoloButton.setVisible(visibilita);

    }
    public void VisibilitaAssegnaRuolo(boolean visibilita) {

        assegnaRuoloButton.setVisible(visibilita);

    }

    @Override
    public String toString() {

        return "MCView{}";

    }
}
