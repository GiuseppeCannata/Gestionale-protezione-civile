package Controller;

import Model.Messaggio;
import View.BasicFrameView;
import View.MessaggioView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MessaggioController {

    private BasicFrameView basicframe;
    private MessaggioView view;
    private String Destinatario;
    private String Mittente;
    private  Messaggio messaggio;

    public MessaggioController(BasicFrameView frame, String destinatario, String mittente) {

        basicframe = frame;
        Destinatario = destinatario;
        Mittente = mittente;

        view = new MessaggioView(basicframe);
        Listener();

        view.setTextAa(Destinatario);
        view.setTextDa(Mittente);

        view.setVisible(true);

    }

    private void Listener(){

        JButton Invia = view.getInviaButton();
        Invia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                InviaAction();

            }
        });
    }

    private void InviaAction(){

        //controllo sulla lunghezza del messaggio
        if(view.getTextArea().length()>200)
            basicframe.ErrorMessage("Testo troppo lungo");
        else {

            messaggio = new Messaggio(Destinatario, Mittente, view.getTextArea());
            messaggio.setMessaggio(view.getTextArea());

            if (messaggio.InsertSQL()) {
                view.dispose();
                basicframe.Message("Inviato!");
            }
        }

    }


}
