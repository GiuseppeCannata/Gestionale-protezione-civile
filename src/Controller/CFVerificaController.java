package Controller;

import Model.CFVerificaModel;
import View.BasicFrameView;
import View.CFVerificaView;
import View.LoginView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class CFVerificaController {

   public BasicFrameView basicframe;
   public LoginView loginview;
   public CFVerificaView verificaview;


   /*COSTRUTTORE*/
   public CFVerificaController(BasicFrameView frame , LoginView view) {

        basicframe = frame;
        loginview = view;
        verificaview = new CFVerificaView();
        basicframe.setdestra(verificaview.getIntermedio0());

        CFVerificaListener();

   }

   /**
    *Metodo di servizio.
    *VerificaCodiceFiscale gestisce eventuali errori dell utente nella digitazione del codice fiscale, o nella mancata
    *immisione di quest'ultimo. Nel caso di errori l utente verrà avvertito con un messaggio di errore.
    */
    private void VerificaCodiceFiscale(){

        String codicefiscale = verificaview.getText();

        try{

            if(codicefiscale.length() == 0)
                throw new Exception("Inserire Codice fiscale!");
            else
              if((codicefiscale.length() > 16) || (codicefiscale.length() < 16))
                throw new Exception("Lunghezza Codice fiscale errata!");
              else{

                  CFVerificaModel cfVerificaModel = new CFVerificaModel(codicefiscale);
                  if(cfVerificaModel.VerificaEntità())
                      throw new Exception("Codice fiscale già presente col seguente username:\n"+cfVerificaModel.getUser());
              }


            /*Apro la finestra di registrazione*/
            Sez_ManagerController sez_managerController ;
            sez_managerController = new Sez_ManagerController(basicframe,loginview,"Registrazione");


        }catch(Exception e){
            verificaview.ErrorMessage(basicframe,e.getMessage());
        }



        //ALTRA IPOTESI VADO NEL DB CON IL CFVerificaModel
    }

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
}
