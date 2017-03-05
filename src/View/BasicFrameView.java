package View;

import javax.swing.*;
import java.awt.*;

public class BasicFrameView extends JFrame{

    private JPanel Intermedio0;
    private JPanel sinistra;
    private JScrollPane destra;

    /*COSTRUTTORE*/
    public BasicFrameView(){

        super("Protezione Civile");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(950, 500);
        setLocation(250, 100);
        setContentPane(Intermedio0);
        setResizable(false);

        setVisible(true);

    }

    //GETTERS
    public JPanel getIntermedio0() {

        return Intermedio0;

    }

    //SETTER
    public void setdestra(Component DESTRA) {

       destra.setViewportView(DESTRA);

    }

    public void setSinistra(JPanel SINISTRA) {

        sinistra = SINISTRA;

    }
}
