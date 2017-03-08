package Controller;

import Model.LoginModel;
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

    /**
     * Metodo di servizio.
     *loginListener gestisce gli eventi scatenati dall utente interagendo con la LoginView
     */
    private void loginListener(){

        /*Registrazione*/
        JButton RegistratiButton = loginview.getRegistratiButton();
        RegistratiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                CFVerificaController verificaframe;
                verificaframe = new CFVerificaController(basicframe , loginview);

            }
        });

        /*Accesso*/
        JButton AccediButton = loginview.getAccediButton();
        AccediButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ControlloImmissione(loginview.getUsernametext(),loginview.getPasswordField1());

            }
        });
    }

    /**
     *Metodo di servizio.
     *ControlloImmisione controlla se:
     *      1)L'utente non ha completato uno dei due campi(Userame, Password) nella pagina Login
     *      2)Segnala all'utente,grazie ad una finestra di errore, se l username e la password inseriti sono non corretti
     */
    private void ControlloImmissione(String userInserito, char[] passInserita){

        try{

            if(userInserito.length() == 0 || passInserita.length == 0)
                throw new Exception("Completare tutti i campi");

            else{

                LoginModel RichiestaDiAccesso= new LoginModel(userInserito, passInserita);
                if(!RichiestaDiAccesso.VerificaEntità())
                    throw new Exception("Username o Password errati");
                else{
                }


            }

        }
        catch (Exception e ){
            loginview.ErrorMessage(basicframe, e.getMessage());
        }
    }
}