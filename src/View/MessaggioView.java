package View;

import javax.swing.*;

/**
 * E una JDialog
 * Frame per la scrittura e l invio di un messaggio
 */
public class MessaggioView extends JDialog{

    private JPanel Intermedio0;
    private JTextField text1;
    private JTextField text2;
    private JTextArea TextArea;
    private JButton inviaButton;

    /*costruttore*/
    public MessaggioView(BasicFrameView basicframe) {

        //richiamo il costruttore della padre
        //voglio una finestra modale di titolo messaggio e che blocchi basicframe
        super(basicframe,"Messaggio",true);

        setSize(400, 300);
        setLocation(400, 160);
        setContentPane(Intermedio0);
        return;

    }

    //GETTER e SETTER
    public JPanel getIntermedio0() {

        return Intermedio0;

    }

    public String getTextArea() {

        return TextArea.getText();

    }

    public JButton getInviaButton() {

        return inviaButton;

    }

    public void setTextAa(String testo) {

        text1.setText(testo);

    }

    public void setTextDa (String testo) {

        text2.setText(testo);
    }

    @Override
    public String toString() {

        return "MessaggioView{}";

    }
}
