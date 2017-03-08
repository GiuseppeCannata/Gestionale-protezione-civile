package View;

import javax.swing.*;
import java.awt.*;

public class BasicFrameView extends JFrame{

    private JPanel Intermedio0;
    private JScrollPane sinistra;
    private JScrollPane destra;

    /*COSTRUTTORE*/
    public BasicFrameView(){

        super("Protezione Civile Gestionale");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(950, 500);
        setLocation(250, 100);
        setContentPane(Intermedio0);
        Image icon = Toolkit.getDefaultToolkit().getImage("immagini/Logo_PC_Nazionale.png");
        setIconImage(icon);
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

    public void setsinistra(Component SINISTRA) {

        sinistra.setViewportView(SINISTRA);

    }
}
