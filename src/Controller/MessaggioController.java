package Controller;

import Model.Messaggio;
import View.BasicFrameView;
import View.MessaggioView;
import com.sun.org.apache.bcel.internal.generic.RETURN;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller per la MessaggioView
 */
public class MessaggioController {

    private BasicFrameView basicframe;
    private MessaggioView view;
    private String Destinatario;
    private String Mittente;
    private  Messaggio messaggio;

    public MessaggioController() {

        return;

    }

    public MessaggioController(BasicFrameView frame, String destinatario, String mittente) {


        basicframe = frame;
        Destinatario = destinatario;
        Mittente = mittente;

        view = new MessaggioView(basicframe);

        view.setTextAa(Destinatario);
        view.setTextDa(Mittente);

        Listener();

        view.setVisible(true);

    }

    /**
     * Ascolto delle azioni dell utente
     * -->Invia
     */
    private void Listener(){

        JButton Invia = view.getInviaButton();
        Invia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                InviaAction();

            }
        });
    }

    /**
     * Metodo che controlla se il testo inserito rispeta il numero dei caratteri consentiti
     * In caso negativo avvisa l utente con una finestra di errore
     * In caso affermativo Salva il messaggio, e avvisa l utente
     */
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

    @Override
    public String toString() {
        return "MessaggioController{}";
    }
}
