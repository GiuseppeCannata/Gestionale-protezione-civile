package View;

import javax.swing.*;

/**
 * AnagraficaView
 * "Contenitore" per le varie sezioni
 */

public class AnagraficaView extends JPanel{

    private JPanel Intermedio0;
    private JPanel Bottonbar;
    private JPanel Intermedio1;
    private JPanel SezA;
    private JPanel SezB;
    private JPanel SezC;
    private JButton paginaLoginButton;
    private JButton salvaButton;
    private JButton indietroButton;
    private JButton avantiButton;
    private JButton modificaButton;
    private JButton homeButton;
    private JPanel SezD;
    private JButton listaCanddiatiButton;


    public AnagraficaView() {

       setVisible(true);

    }

    public void MessaggioBenvenuto (BasicFrameView frame){

        JOptionPane.showMessageDialog(frame, "Benvenuto nella sezione registrazione!\nCompleta " +
                        "tutti i campi obbligatori\ne successivamente clicca su salva.", "Benvenuto",
                         JOptionPane.INFORMATION_MESSAGE);
    }


    //GETTER E SETTER
    public JPanel getIntermedio0() {

        return Intermedio0;
    }

    public JPanel getIntermedio1() {

        //QUI HO IL CARD LAYOUT
        return Intermedio1;

    }

    public JButton getHomeButton() {

        return homeButton;

    }

    public JButton getPaginaLoginButton() {

        return paginaLoginButton;
    }

    public JButton getSalvaButton() {

        return salvaButton;
    }

    public JButton getIndietroButton() {

        return indietroButton;
    }

    public JButton getAvantiButton() {

        return avantiButton;
    }

    public JButton getModificaButton() {

        return modificaButton;
    }

    public JButton getListaCanddiatiButton() {

        return listaCanddiatiButton;

    }

    public void setSezA(JPanel sezA) {

        SezA.add(sezA);

    }

    public void setSezB(JPanel sezB) {

        SezB.add(sezB);

    }

    public void setSezC(JPanel sezC) {

        SezC.add(sezC);

    }

    public void setSezD(JPanel sezD) {

        SezD.add(sezD);

    }

    public void VisibilitaSalvaButton(boolean visibilita){

        salvaButton.setVisible(visibilita);

    }

    public void VisibilitaPaginaLoginButton(boolean visibilita) {

        paginaLoginButton.setVisible(visibilita);

    }

    public void VisibilitaModificaButton(boolean visibilita) {

        modificaButton.setVisible(visibilita);

    }

    public void VisibilitaHomeButton(boolean visibilita) {

        homeButton.setVisible(visibilita);

    }

    public void VisibilitaArchivistaButton(boolean visibilita){

        listaCanddiatiButton.setVisible(visibilita);
    }

    @Override
    public String toString() {

        return "AnagraficaView";

    }
}
