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

    public void ErrorMessage(String nomeErrore){
        JOptionPane.showMessageDialog(BasicFrameView.this, nomeErrore, "Warning!", JOptionPane.ERROR_MESSAGE);
    }

    public int OpotionalMessage(String nomeMessage){
            /*
			 * Il metodo showConfirmDialog() ritorna un valore intero; in questo
			 * caso: 0==Si 1==No Sapendo questo sono in grado di capire la
			 * scelta dell utente e di poterla gestire di conseguenza
			 */

        return JOptionPane.showConfirmDialog(this, nomeMessage, "Warning",
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

    }

    public void Message(String nomeMessage){
        JOptionPane.showMessageDialog(BasicFrameView.this, nomeMessage, "Warning!",JOptionPane.INFORMATION_MESSAGE);
    }




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
