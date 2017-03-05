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



    public Sez_ManagerView() {

        return;

    }

    //GETTER
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

    public void setSezA(JPanel sezA) {

        SezA.add(sezA);

    }

    public JPanel getSezA() {

        return SezA;

    }

    public void setSezB(JPanel sezB) {

        SezB.add(sezB);

    }

    public void setSezC(JPanel sezC) {

        SezC.add(sezC);

    }
}
