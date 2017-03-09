package View;

import javax.swing.*;


public class Sez_ManagerView{

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


    public Sez_ManagerView() {

        return;

    }

    public void MessaggioBenvenuto (BasicFrameView frame){

        JOptionPane.showMessageDialog(frame, "Benvenuto nelle sezione registrazione!\nCompleta " +
                        "tutti i campi obbligatori\ne successivamente clicca su salva.", "Benvenuto",
                         JOptionPane.INFORMATION_MESSAGE);
    }

    //GETTER

    public JButton getHomeButton() {
        return homeButton;
    }

    public JPanel getIntermedio0() {

        return Intermedio0;
    }

    public JPanel getIntermedio1() {

        //PERCHE E QUI CHE HO IL CARD LAYOUT
        return Intermedio1;
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

    //SETTERS

    public void setSalvaButton(boolean visibilita) {
        salvaButton.setContentAreaFilled(visibilita);

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

    public void setPaginaLoginButton(boolean visibilita) {

        paginaLoginButton.setVisible(visibilita);

    }

    public void setModificaButton(boolean visibilità) {
        modificaButton.setVisible(visibilità);
    }

    public void setHomeButton(boolean visibilita) {
        homeButton.setVisible(visibilita);
    }
}
