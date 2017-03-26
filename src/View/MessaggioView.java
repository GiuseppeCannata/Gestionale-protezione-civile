package View;

import javax.swing.*;

/**
 * Created by Giuseppe on 26/03/2017.
 */
public class MessaggioView extends JDialog{

    private JPanel Intermedio0;
    private JTextField text1;
    private JTextField text2;
    private JTextArea TextArea;
    private JButton inviaButton;

    public MessaggioView(BasicFrameView basicframe) {

        super(basicframe,"Messaggio",true);
        setSize(400, 300);
        setLocation(400, 160);
        setContentPane(Intermedio0);
        return;

    }

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

}
