package Controller;

import View.BasicFrameView;
import View.LoginView;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginController {

    public BasicFrameView basicframe;
    public LoginView loginview;


    /*COSTRUTTORE*/
    public LoginController(BasicFrameView view) {

        basicframe = view;
        loginview = new LoginView();
        //Il pannello di login va messo nella parte destra della basicframe
        basicframe.setdestra(loginview.getIntermedio0());

        loginListener();

    }


    /*
     *loginListener gestisce gli eventi scatenati dall utente interagendo con la LoginView
     */
    private void loginListener(){

        /*Registrazione*/
        JButton loginRegistratiButton = loginview.getRegistratiButton();
        loginRegistratiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    CFVerificaController verificaframe;
                    verificaframe = new CFVerificaController(basicframe , loginview);


            }
        });

        /*Accesso*/
        JButton loginAccediButton = loginview.getAccediButton();
        loginAccediButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
