package View;

import javax.swing.*;

/**
 * Created by Giuseppe on 23/03/2017.
 */
public class MCcompitiView extends JPanel {

    private JPanel Intermedio0;
    private JComboBox Box1;
    private JButton assegnaCompitoButton;
    private JList list1;
    private JComboBox Box2;

    public MCcompitiView() {

        this.setVisible(true);

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

    public JList getList1() {
        return list1;
    }

    public JComboBox getBox2() {
        return Box2;
    }
}
