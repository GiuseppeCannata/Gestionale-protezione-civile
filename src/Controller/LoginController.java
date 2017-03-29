package Controller;

import Model.LoginModel;
import Model.Candidato;
import Model.Volontario;
import View.BasicFrameView;
import View.LoginView;

import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller per la LoginView
 */

public class LoginController {

    public BasicFrameView basicframe;
    public LoginView loginview;


    /*COSTRUTTORI*/

    //Costruttore vuoto
    public LoginController(){

        return;

    }

    public LoginController(BasicFrameView frame) {

        basicframe = frame;
        loginview = new LoginView();
        //Il pannello di login va messo nella parte destra della basicframe
        basicframe.setdestra(loginview.getIntermedio0());
        basicframe.setsinistra(null);

        loginListener();

    }

    /**
     * Metodo di servizio.
     * Gestisce gli eventi scatenati dall utente interagendo con la LoginView
     *
     *  Ascolto azioni dell' utente
     *  Registrazione,Accesso
     */
    private void loginListener(){

        /*Registrazione*/
        JButton RegistratiButton = loginview.getRegistratiButton();
        RegistratiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                CFVerificaController verifica;
                verifica = new CFVerificaController(basicframe, loginview);

            }
        });

        /*Accesso*/
        JButton AccediButton = loginview.getAccediButton();
        AccediButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

               AccediAction();

            }
        });
    }

    /**
     * Metodo di servizio.
     * Controlla se:
     *      1)L'utente non ha completato uno dei due campi(Userame, Password) nella pagina Login
     *      2)Segnala all'utente,grazie ad una finestra di errore, se l username e la password inseriti sono non corretti
     *
     */
    private void AccediAction(){

        String userInserito = loginview.getUsernametext();
        String passInserita = new String (loginview.getPasswordField1());  //conversione a stringa del char[] restituito da JPasswordText


        try{

            //non è inserita nessun user e pass
            if(userInserito.length() == 0 || passInserita.length() == 0)
                throw new Exception("Completare tutti i campi");


            LoginModel RichiestaDiAccesso = new LoginModel(userInserito, passInserita);
            if(!RichiestaDiAccesso.SearchSQL())
                throw new Exception("Username o Password errati");


            if(RichiestaDiAccesso.getVolocand().equals("0")) {

                //chi sta acedendo è uncanddiato
                CandidatoController UController;
                Candidato utente = new Candidato(userInserito);
                UController = new CandidatoController(basicframe, utente);

            }else{

                //chi sta accedendo è un volontario
                VolontarioController UController;
                Volontario utente = new Volontario(userInserito);
                UController = new VolontarioController(basicframe, utente);

            }

        }
        catch (Exception e ){
            basicframe.ErrorMessage(e.getMessage());
        }
    }

    @Override
    public String toString() {

        return "Sono LoginController";
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoginController that = (LoginController) o;

        return loginview != null ? loginview.equals(that.loginview) : that.loginview == null;
    }

}