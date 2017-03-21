package View;

import javax.swing.*;


public class ListaggiArchivistaView extends JPanel{

    private JButton visionaSchedaButton;
    private JButton ritornaAiCompitiDaButton;
    private JComboBox comboBox1;
    private JPanel Intermedio0;
    private JButton accettaButton;
    private JLabel Label;


    public ListaggiArchivistaView(){

        setVisible(true);

    }



    public JButton getRitornaAiCompitiDaButton() {

        return ritornaAiCompitiDaButton;

    }


    public JComboBox getComboBox1() {

        return comboBox1;

    }

    public JButton getVisionaSchedaButton() {

        return visionaSchedaButton;

    }


    public JButton getAccettaButton() {

        return accettaButton;

    }
    public JPanel getIntermedio0() {

        return Intermedio0;

    }

    public void setLabel(String Nome){

        Label.setText(Nome);

    }

    public void VisibilitaAccettaButton(boolean visibilita){

        accettaButton.setVisible(visibilita);

    }

}
