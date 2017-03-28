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
    private JTextField text;
    private JLabel statoLabel;
    private JList list;
    private JButton assegnaCompitoButton;
    private JComboBox BoxAcomparsa;

    /*costruttore*/
    public ListaggiView(){

        inviagliUnMessaggioButton.setVisible(false);
        accettaButton.setVisible(false);
        resettaPasswordButton.setVisible(false);
        visionaSchedaButton.setVisible(false);
        ritornaButton.setVisible(false);
        text.setVisible(false);
        statoLabel.setVisible(false);
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

    public JComboBox getBox1() {

        return Box1;

    }

    public void setLabel(String Nome){

        Label.setText(Nome);

    }

    public void setText(String testo){

        text.setText(testo);

    }


    /*metodi per il set delle visibilita dei vai elementi della view*/
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

    public void  VisibilitaText(boolean visibilita){

        text.setVisible(visibilita);

    }

    public void VisibilitaStatoLabel(boolean visibilita){

        statoLabel.setVisible(visibilita);
    }

    @Override
    public String toString() {
        return "ListaggiView{}";
    }
}
