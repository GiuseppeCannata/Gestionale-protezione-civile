import Controller.LoginController;
import View.BasicFrameView;

import javax.swing.*;


/**
 * Applicazione per la gestione dei dati personali dei volontari della Protezione Civile di Falconara Marittima
 */

public class MainApplicazione {

    public static void main(String[] args) {

        LoginController ApplicazioneStart;

        //look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        //Genero la frame principale(BasicFrameView) passandola al LoginController che porrà all interno il
        //pannello di login
        ApplicazioneStart=new LoginController(new BasicFrameView());



    }
}
