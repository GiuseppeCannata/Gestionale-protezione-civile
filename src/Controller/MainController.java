package Controller;

import View.BasicFrameView;


public class MainController{

    public static void main(String[] args) {

        LoginController ApplicazioneStart;

        /*Genero la frame principale(BasicFrameView) passandola al LoginController che porrà all interno il
         *pannello di accesso*/
        ApplicazioneStart=new LoginController(new BasicFrameView());

    }
}
