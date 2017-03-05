package Controller;

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
   public CFVerificaController(BasicFrameView frame, LoginView view) {

        basicframe = frame;
        loginview = view;
        verificaview = new CFVerificaView();
        basicframe.setdestra(verificaview.getIntermedio0());

        CFVerificaListener();

   }

    private void VerificaEsistenzaUtente(){

       String codicefiscale = verificaview.getText();

        if(codicefiscale.length() > 16)
            JOptionPane.showMessageDialog(basicframe, "Errore!\nCodice fiscale troppo lungo.", "Warning!",
                                          JOptionPane.ERROR_MESSAGE);
        else
         if(codicefiscale.length() == 0)
            JOptionPane.showMessageDialog(basicframe, "Errore!\nNessun codice fiscale inserito.", "Warning!",
                                          JOptionPane.ERROR_MESSAGE);
         else
           if(codicefiscale.equals("si")) {
                JOptionPane.showMessageDialog(basicframe, "Sei già registrato!", "Warning!",
                                              JOptionPane.ERROR_MESSAGE);

           }
           else{
                /*Apro la finestra di registrazione*/
                Sez_ManagerController sez_managerController ;
                sez_managerController = new Sez_ManagerController(basicframe,loginview,"Registrazione");
                JOptionPane.showMessageDialog(basicframe, "Benvenuto nelle sezione registrazione!\nCompleta " +
                        "tutti i campi obbligatori\ne successivamente clicca su salva.", "Benvenuto",
                        JOptionPane.INFORMATION_MESSAGE);
           }

        //ALTRA IPOTESI VADO NEL DB CON IL CFVerificaModel
    }

    private void CFVerificaListener(){

        JButton OKButton = verificaview.getOkButton();
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                VerificaEsistenzaUtente();

            }
        });

        JButton getpaginaLoginButton = verificaview.getpaginaLoginButton();
        getpaginaLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

               basicframe.setdestra(loginview.getIntermedio0());

            }
        });
    }
}
