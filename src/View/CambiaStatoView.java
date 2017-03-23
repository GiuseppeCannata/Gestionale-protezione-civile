package View;


import javax.swing.*;

public class CambiaStatoView extends JPanel {

    private JPanel Intemedio0;
    private JComboBox Box;
    private JButton cambiaStatoButton;
    private JPanel Intermedio0;
    private JButton AncoraButton;

    /*costruttore */
    public CambiaStatoView() {

        setVisible(true);

    }

        /*GETTER*/


    public JPanel getIntemedio0() {
        return Intermedio0;
    }


    public JComboBox getBox() {
        return Box;
    }


    public JButton getCambiaStatoButton() {
        return cambiaStatoButton;
    }
}


