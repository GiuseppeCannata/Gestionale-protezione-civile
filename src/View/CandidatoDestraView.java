package View;

import javax.swing.*;
import java.util.ArrayList;

/**
 * CandidatoDestraView
 * View della parte destra della basicframe mostrata al login del Candidato
 */
public class CandidatoDestraView extends JPanel {

    private JPanel Intermedio0;
    private JLabel MessaggioaSchermo;
    private JLabel labelConf_ArchivistaLabel;
    private JLabel labelConf_giuntaLabel;
    private JCheckBox checkBox1;
    private JCheckBox checkBox2;
    private JTabbedPane tabbedPane1;
    private JScrollPane ScrollPane;


    public CandidatoDestraView() {

        checkBox1.setEnabled(false);
        checkBox2.setEnabled(false);
        setVisible(true);


    }

    public JPanel getIntermedio0() {

        return Intermedio0;

    }


    public void MessaggioSchermo(String testo){

        MessaggioaSchermo.setText(testo);

    }

    public void setConf_Archivista(boolean visibilita){

        checkBox1.setSelected(visibilita);

    }

    public void setConf_giunta(boolean visibilita){

        checkBox2.setSelected(visibilita);

    }

    public void setTextList(ArrayList<String> MESSAGGI){

        JList list = new JList(MESSAGGI.toArray());
        ScrollPane.setViewportView(list);

    }

    @Override
    public String toString() {

        return "CandidatoDestraView";

    }
}
