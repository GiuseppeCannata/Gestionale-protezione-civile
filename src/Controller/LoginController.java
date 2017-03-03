package Controller;

import View.BasicFrameView;
import View.LoginView;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class LoginController {

    BasicFrameView basicframe;
    LoginView loginview;
    /*Mi serve per evitare che l utente istanzi più di una Sez_ManagerView, quando ne è già istanziata una*/
    int REGISTRATI;

    /*COSTRUTTORE*/
    public LoginController(BasicFrameView view) {

        basicframe = view;
        loginview = new LoginView();
        /*Vado a mettere nella sezione di destra della basicframe il pannello di login */
        basicframe.setdestra(loginview.getIntermedio0());
        /*Ovviamente all inizio ne ho zero istanziate*/
        REGISTRATI = 0;

        loginListener();

    }

    /*SETTER*/
    public void setREGISTRATI(int registrati) {

        REGISTRATI = registrati;

    }

    /*loginListener() gestisce gli eventi scatenati dall utente interagendo con la LoginView*/
    private void loginListener(){

        /*Registrazione*/
        JButton loginRegistratiButton=loginview.getRegistratiButton();
        loginRegistratiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(REGISTRATI==0) {
                    /*Settando Registrati con 1 dico che eiste gia un istanza di Sez_ManagerController*/
                    REGISTRATI = 1;
                    Sez_ManagerController sez_managerController = new Sez_ManagerController(basicframe,
                            "Registrazione", LoginController.this);
                }

            }
        });

        /*Accesso*/
        JButton loginAccediButton=loginview.getAccediButton();
        loginRegistratiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
