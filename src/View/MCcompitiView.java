package View;

import javax.swing.*;


public class MCcompitiView extends JPanel {

    private JPanel Intermedio0;
    private JComboBox Box1;
    private JButton assegnaCompitoButton;
    private JList lista;
    private JComboBox Box2;

    public MCcompitiView() {

        lista.setEnabled(false);
        setVisible(true);

    }

    public JPanel getIntermedio0() {
        return Intermedio0;
    }

    public JComboBox getBox1() {
        return Box1;
    }

    public JButton getAssegnaCompitoButton() {
        return assegnaCompitoButton;
    }

    public JList getLista() {
        return lista;
    }

    public JComboBox getBox2() {
        return Box2;
    }
}
