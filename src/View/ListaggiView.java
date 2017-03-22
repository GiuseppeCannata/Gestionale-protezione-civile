package View;

import javax.swing.*;


public class ListaggiView extends JPanel{

    private JButton visionaSchedaButton;
    private JButton ritornaButton;
    private JComboBox comboBox1;
    private JPanel Intermedio0;
    private JButton accettaButton;
    private JLabel Label;
    private JButton resettaPasswordButton;


    public ListaggiView(){

        setVisible(true);

    }



    public JButton getRitornaButton() {

        return ritornaButton;

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

    public JButton getResettaPasswordButton() {

        return resettaPasswordButton;

    }

    public void setLabel(String Nome){

        Label.setText(Nome);

    }

    public void VisibilitaAccettaButton(boolean visibilita){

        accettaButton.setVisible(visibilita);

    }

    public void VisibilitaResettaPasswordButton(boolean visibilita){

        resettaPasswordButton.setVisible(visibilita);

    }

    public void VisibilitaVisionaSchedaButton(boolean visibilita){

            visionaSchedaButton.setVisible(visibilita);

    }

    public void VisibilitaRitornaButton (boolean visibilita){

        ritornaButton.setVisible(visibilita);


    }

}
