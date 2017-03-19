package Controller;


<<<<<<< HEAD
import Model.Volontario;
=======
>>>>>>> origin/master
import View.BasicFrameView;
import View.UtenteSinistraView;
import View.VolontarioDView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


<<<<<<< HEAD
public class VolontarioController{

    private BasicFrameView basicframe;
    private VolontarioDView Dview;
    private UtenteSinistraView Sview;
    private Volontario Utente;

    private int DatiPersonali;

    public VolontarioController(BasicFrameView frame, Volontario utente){

        basicframe = frame;
        DatiPersonali = 0;
        Utente = utente;
=======
public class VolontarioController extends CandidatoController{

    private BasicFrameView basicframe;
    private VolontarioDView Dview;
    private Volontario Utente;

    private UtenteSinistraView Sview;
    private int DatiPersonali;

    public VolontarioController(BasicFrameView frame){

        super();
        basicframe = frame;
        DatiPersonali = 0;
>>>>>>> origin/master
        Dview = new VolontarioDView();
        Sview = new UtenteSinistraView();
        Sview.setEvolviButton(false);
        basicframe.setdestra(Dview.getIntermedio0());
        basicframe.setsinistra(Sview.getIntermedio0());

        VolontarioControllerListener();

    }

    private void VolontarioControllerListener(){

        /*DatiPersonali*/
        JButton DatiPersonaliButton = Sview.getDatiPersonaliButton();
        DatiPersonaliButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (DatiPersonali == 0){

                    DatiPersonali = 1;
<<<<<<< HEAD

                    AnagraficaController datipersonali;
                    //datipersonali = new AnagraficaController(basicframe, Dview, Utente);
=======
                    AnagraficaController datipersonali;
                   /** datipersonali = new AnagraficaController(basicframe, Dview, Utente, Utente.getCodice_Fiscale(),
                            );**/
>>>>>>> origin/master
                }

            }

        });



        /*Logout*/
        JButton Logout = Sview.getLogoutButton();
        Logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                LogoutAction();
            }

        });
    }


    private void LogoutAction(){

        if(basicframe.OpotionalMessage("Vuoi davvero uscire?") == 0) {
            LoginController loginController = new LoginController(basicframe);
        }

    }
}
