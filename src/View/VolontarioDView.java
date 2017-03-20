package View;

import javax.swing.*;



public class VolontarioDView {


    private JPanel Intermedio0;
    private JButton addGiuntaComunaleButton;
    private JButton button2;
    private JLabel RUOLOVOLabel;
    private JLabel COGNOMEVOLabel;
    private JLabel NOMEVOLabel;
    private JTabbedPane tabbedPane1;
    private JList list1;


    public VolontarioDView() {


        return;

    }


    public JPanel getIntermedio0() {

        return Intermedio0;
       // list1.setListData(porca);

    }

    public JButton getAddGiuntaComunaleButton() {

        return addGiuntaComunaleButton;

    }

    public JButton getButton2() {

        return button2;

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
