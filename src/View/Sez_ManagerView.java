package View;

import javax.swing.*;


public class Sez_ManagerView{

    private JPanel Intermedio0;
    private JPanel Bottonbar;
    private JPanel Intermedio1;
    private JPanel SezA;
    private JPanel SezB;
    private JPanel SezC;
    private JButton ibridoButton;
    private JButton salvaButton;
    private JButton indietroButton;
    private JButton avantiButton;
    private JButton modificaButton;


    public Sez_ManagerView() {

        return;

    }

    public void MessaggioBenvenuto (BasicFrameView frame){

        JOptionPane.showMessageDialog(frame, "Benvenuto nelle sezione registrazione!\nCompleta " +
                        "tutti i campi obbligatori\ne successivamente clicca su salva.", "Benvenuto",
                         JOptionPane.INFORMATION_MESSAGE);
    }

    //GETTER
    public JPanel getIntermedio0() {

        return Intermedio0;
    }

    public JPanel getIntermedio1() {

        //PERCHE E QUI CHE HO IL CARD LAYOUT
        return Intermedio1;
    }


    public JButton getIbridoButton() {

        return ibridoButton;
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
    public void setSezA(JPanel sezA) {

        SezA.add(sezA);

    }

    public void setSezB(JPanel sezB) {

        SezB.add(sezB);

    }

    public void setSezC(JPanel sezC) {

        SezC.add(sezC);

    }

    public void setIbridoButton(String nuovo) {

        ibridoButton.setText(nuovo);

    }

    public void setmodificaButton(Boolean visibilita) {

       modificaButton.setVisible(visibilita);
    }
}
