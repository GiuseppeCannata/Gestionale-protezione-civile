import Controller.LoginController;
import View.BasicFrameView;


/**
 *Gestione dell'avvio dell applicazione
 */

public class MainApplicazione {

    public static void main(String[] args) {

        LoginController ApplicazioneStart;

        //Genero la frame principale(BasicFrameView) passandola al LoginController che porrà all interno il
        //pannello di login
        ApplicazioneStart=new LoginController(new BasicFrameView());

    }
}
