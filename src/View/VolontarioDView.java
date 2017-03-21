package View;

import javax.swing.*;



public class VolontarioDView extends JPanel{


    private JPanel Intermedio0;
    private JButton addGiuntaComunaleButton;
    private JButton archivistaButton;
    private JLabel RUOLOVOLabel;
    private JLabel COGNOMEVOLabel;
    private JLabel NOMEVOLabel;


    public VolontarioDView() {

        setVisible(true);

    }


    public JPanel getIntermedio0() {

        return Intermedio0;

    }

    public JButton getAddGiuntaComunaleButton() {

        return addGiuntaComunaleButton;

    }


    public JButton getArchivistaButton() {

        return archivistaButton;

    }

    public void setRUOLOVOLabel(String testo) {

        RUOLOVOLabel.setText(testo);

    }

    public void setCOGNOMEVOLabel(String testo) {

        COGNOMEVOLabel.setText(testo);

    }

    public void setNOMEVOLabel(String testo){

       NOMEVOLabel.setText(testo);

    }
}
