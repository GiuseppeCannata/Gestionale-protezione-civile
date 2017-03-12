package Controller;

import Model.CFVerificaModel;
import View.BasicFrameView;
import View.CFVerificaView;
import View.LoginView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * CFVerificaController --> controller per la CFVerificaView
 * Classe pubblica
 */

public class CFVerificaController {

   public BasicFrameView basicframe;
   public LoginView loginview;
   public CFVerificaView verificaview;
   public String codicefiscale;


   /*COSTRUTTORE*/
   public CFVerificaController(BasicFrameView frame, LoginView view) {

        basicframe = frame;
        loginview = view;
        verificaview = new CFVerificaView();
        codicefiscale = null;
        //imposto la view nello scroll a destra
        basicframe.setdestra(verificaview.getIntermedio0());

        CFVerificaListener();

   }

    /**
     * Ascolto operazioni dell'utente
     */

    private void CFVerificaListener(){

        /*OK*/
        JButton OKButton = verificaview.getOkButton();
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                VerificaCodiceFiscale();

            }
        });

        /*paginaLogin*/
        JButton paginaLoginButton = verificaview.getpaginaLoginButton();
        paginaLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

               basicframe.setdestra(loginview.getIntermedio0());

            }
        });
    }

    /**
     * Metodo di servizio.
     * VerificaCodiceFiscale gestisce eventuali errori dell utente nella digitazione del codice fiscale, o nella mancata
     * immisione di quest'ultimo.
     * Nel caso di errori l utente verrà avvertito con un messaggio di errore.
     */
    private void VerificaCodiceFiscale(){

       codicefiscale = verificaview.getText();

        try{

            if(codicefiscale.length() == 0)
                throw new Exception("Inserire Codice fiscale!");
            else
            if((codicefiscale.length() > 16) || (codicefiscale.length() < 16)) //la lunghezza del codice fiscale puo essere massimo 16
                throw new Exception("Lunghezza Codice fiscale errata!");


            //Verifica se il codice fiscale è contenuto o meno nel DB
            CFVerificaModel cfVerificaModel = new CFVerificaModel(codicefiscale);
            if(cfVerificaModel.SearchSQL())
                throw new Exception("Codice fiscale già presente.");


            /*Apro la finestra di registrazione*/
            RegistrazioneController registrazioneController;
            registrazioneController = new RegistrazioneController(basicframe, loginview, codicefiscale);



        }catch(Exception e){

            basicframe.ErrorMessage(e.getMessage());

        }
    }

    @Override
    public String toString() {

        return " Sono CFVerificaController e mi occupo e mi occupo della gestione delle azioni scatenate dall utente " +
                "interagendo con la CFVerificaView";

    }
}
