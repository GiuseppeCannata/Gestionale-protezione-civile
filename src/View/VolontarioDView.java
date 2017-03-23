package View;

import javax.swing.*;



public class VolontarioDView extends JPanel{


    private JPanel Intermedio0;
    private JButton addGiuntaComunaleButton;
    private JButton archivistaButton;
    private JLabel RUOLOVOLabel;
    private JLabel COGNOMEVOLabel;
    private JLabel NOMEVOLabel;
    private JButton referenteInformaticoButton;
    private JButton cambiaButton;
    private JLabel STATOLabel;
    private JButton master_ChiefButton;


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

    public JButton getReferenteInformaticoButton() {

        return referenteInformaticoButton;

    }

    public JButton getCambiaButton() {

        return cambiaButton;

    }

    public JButton getMaster_ChiefButton() {

        return master_ChiefButton;

    }

    public void setRUOLOVOLabel(String testo) {

        RUOLOVOLabel.setText(testo);

    }

    public void setSTATOLabel(String testo) {

        STATOLabel.setText(testo);

    }

    public void setCOGNOMEVOLabel(String testo) {

        COGNOMEVOLabel.setText(testo);

    }

    public void setNOMEVOLabel(String testo){

       NOMEVOLabel.setText(testo);

    }
}
