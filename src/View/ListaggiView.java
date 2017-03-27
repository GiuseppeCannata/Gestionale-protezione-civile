package View;

import javax.swing.*;

/**
 * View per referente informatico e Archivista
 */
public class ListaggiView extends JPanel{

    private JButton visionaSchedaButton;
    private JButton ritornaButton;
    private JPanel Intermedio0;
    private JButton accettaButton;
    private JLabel Label;
    private JButton resettaPasswordButton;
    private JComboBox Box1;
    private JButton inviagliUnMessaggioButton;
    private JList list;
    private JButton assegnaCompitoButton;
    private JComboBox BoxAcomparsa;


    public ListaggiView(){

        inviagliUnMessaggioButton.setVisible(false);
        accettaButton.setVisible(false);
        resettaPasswordButton.setVisible(false);
        visionaSchedaButton.setVisible(false);
        ritornaButton.setVisible(false);
        setVisible(true);

    }

    //GETTERE e SETTER
    public JList getList() {

        return list;

    }

    public JButton getRitornaButton() {

        return ritornaButton;

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

    public JButton getInviagliUnMessaggioButton() {

        return inviagliUnMessaggioButton;

    }

    public void setLabel(String Nome){

        Label.setText(Nome);

    }

    public JComboBox getBox1() {

        return Box1;

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

    public void  VisibilitaInviomessaggio(boolean visibilita){

        inviagliUnMessaggioButton.setVisible(visibilita);
    }

    @Override
    public String toString() {
        return "ListaggiView{}";
    }
}
