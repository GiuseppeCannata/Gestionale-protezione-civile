package View;

import javax.swing.*;


public class Sez_ManagerView extends JFrame{

    private JPanel Intermedio0;
    private JPanel Bottonbar;
    private JPanel Intermedio1;
    private JPanel SezA;
    private JPanel SezB;
    private JPanel SezC;
    private JButton esciButton;
    private JButton salvaButton;
    private JButton indietroButton;
    private JButton avantiButton;



    public Sez_ManagerView() {

        super();
        setSize(500, 500);
        setLocation(450,150);
        setResizable(false);
        setContentPane(Intermedio0);
        setVisible(true);


    }

    //GETTER
    public JPanel getIntermedio0() {

        /*PERCHE E QUI CHE HO IL CARD LAYOUT*/
        return Intermedio0;
    }

    public JPanel getIntermedio1() {

        return Intermedio1;
    }


    public JButton getEsciButton() {

        return esciButton;
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


    public void setSezB(JPanel sezB) {

        SezB.add(sezB);

    }

    public void setSezC(JPanel sezC) {

        SezC.add(sezC);

    }
}
