package View;

import javax.swing.*;
import java.util.ArrayList;

/**
 * View della parte destra della basicframe mostrata al login del Candidato
 */
public class CandidatoDestraView extends JPanel {

    private JPanel Intermedio0;
    private JLabel MessaggioaSchermo;
    private JLabel labelConf_ArchivistaLabel;
    private JLabel labelConf_giuntaLabel;
    private JCheckBox checkBox1;
    private JCheckBox checkBox2;
    private JScrollPane ScrollPaneBroadcast;
    private JScrollPane ScrollPaneMessaggi;


    /*COSTRUTORE*/
    public CandidatoDestraView() {

        checkBox1.setEnabled(false);
        checkBox2.setEnabled(false);
        setVisible(true);
    }

    //GETTER e SETTER
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

    public void setBroadcast(ArrayList<String> MESSAGGI){

        JList listBroadcast = new JList(MESSAGGI.toArray());
        ScrollPaneBroadcast.setViewportView(listBroadcast);

    }

    public void seteMessaggi(ArrayList<String> MESSAGGI){

        JList listMessaggi = new JList(MESSAGGI.toArray());
        ScrollPaneMessaggi.setViewportView(listMessaggi);

    }

    @Override
    public String toString() {

        return "CandidatoDestraView";

    }
}
