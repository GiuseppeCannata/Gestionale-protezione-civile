package Controller;

import Model.Sez_ManagerModel;
import View.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Sez_ManagerController {

   public BasicFrameView basicframe;
   public Sez_ManagerView sez_managerview;
   public LoginView loginview;
   public CandidatoDestraView Dview;
   public Sez_AView sez_Aview;
   public Sez_BView sez_Bview;
   public Sez_CView sez_Cview;

   private String Utilizzatore;

    /*COSTRUTTORI*/
    public Sez_ManagerController(BasicFrameView frame , LoginView view , String utilizzatore) {

        basicframe = frame;
        loginview = view;
        Utilizzatore = utilizzatore;

        InizializzazioneRegistrazione();

        sezmanagerListener();

    }

    public Sez_ManagerController(BasicFrameView frame , CandidatoDestraView view , String utilizzatore) {

        basicframe = frame;
        Dview = view;
        Utilizzatore = utilizzatore;

        InizializzazioneCandidato();

        sezmanagerListener();

    }


    private void InizializzazioneRegistrazione(){

        sez_managerview = new Sez_ManagerView();

        sez_Aview = new Sez_AView();
        sez_Bview = new Sez_BView();
        sez_Cview = new Sez_CView();

        sez_managerview.setIbridoButton("Pagina Login");
        sez_managerview.setmodificaButton(false);
        sceltapannelli(Utilizzatore);
        //Setto il mio manager di pagine
        Pagine_Manager.setPagina_Corrente();
        basicframe.setdestra(sez_managerview.getIntermedio0());
        sez_managerview.MessaggioBenvenuto(basicframe);

    }

    private void InizializzazioneCandidato(){

        sez_managerview = new Sez_ManagerView();

        sez_Aview = new Sez_AView();
        sez_Bview = new Sez_BView();
        sez_Cview = new Sez_CView();

        sez_managerview.setIbridoButton("Home");
        sez_managerview.setmodificaButton(true);
        sceltapannelli(Utilizzatore);
        //Setto il mio manager di pagine
        Pagine_Manager.setPagina_Corrente();
        basicframe.setdestra(sez_managerview.getIntermedio0());

    }


    /*
     *sceltapannelli gestisce i pannelli da inserire nella Sez_managerView.
     *La scelta è eseguita in base all utilizzatore--> Registrazione, Candidato , Volontario
     */
    private void sceltapannelli(String utilizzatore){

        if(utilizzatore.equals("Registrazione")){

            sez_managerview.setSezA(sez_Aview.getIntermedio0());
            sez_managerview.setSezB(sez_Bview.getIntermedio0());
            sez_managerview.setSezC(sez_Cview.getIntermedio0());

        }

        else if(utilizzatore.equals("Candidato")){

            sez_managerview.setSezA(sez_Aview.getIntermedio0());
            sez_managerview.setSezB(sez_Bview.getIntermedio0());
            sez_managerview.setSezC(sez_Cview.getIntermedio0());

        }

    }



    private void sezmanagerListener(){

        CardLayout CL=(CardLayout) sez_managerview.getIntermedio1().getLayout();

        /*Avanti*/
        JButton sez_managerviewAvanti = sez_managerview.getAvantiButton();
        sez_managerviewAvanti.addActionListener(new ActionListener() {
                @Override
                    public void actionPerformed(ActionEvent e) {

                        if (Pagine_Manager.getPagina_Corrente() < 3) {

                            CL.next(sez_managerview.getIntermedio1());
                            Pagine_Manager.addPagina_Corrente();

                        }

                    }

        });


        /*Indietro*/
        JButton sez_managerviewIndietro= sez_managerview.getIndietroButton();
        sez_managerviewIndietro.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    if (Pagine_Manager.getPagina_Corrente() > 1) {

                        CL.previous(sez_managerview.getIntermedio1());
                        Pagine_Manager.subctractPagina_Corrente();
                    }

                }
        });


        /*ibrido*/
        JButton button = sez_managerview.getIbridoButton();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

               if(Utilizzatore.equals("Registrazione"))
                basicframe.setdestra(loginview.getIntermedio0());
               if(Utilizzatore.equals("Persona"))
                   basicframe.setdestra(Dview.getIntermedio0());



            }
        });


        /*Salva*/
        JButton Salvabutton = sez_managerview.getSalvaButton();
        Salvabutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

               if(VerificaCompletamentoCampiObbligatori()) {
                   Sez_ManagerModel sezManagerModel;
                   System.out.println("Campi obbligatori pieni");
                   sezManagerModel=new Sez_ManagerModel(sez_Aview, sez_Bview, sez_Cview);
               }

            }
        });

    }

    public Boolean VerificaCompletamentoCampiObbligatori(){

        boolean controllo= false;
        try{
            if((sez_Aview.getNometext().length() == 0) || (sez_Aview.getCognometext().length()==0) ||
                    (sez_Aview.getDatadinascitatext().length()==0) || (sez_Aview.getIndirizzodiresidenzatext().length()==0)
                    || (sez_Aview.getTelefonocellularetext().length()==0) || (sez_Aview.getCodicefiscaletext().length()==0)
                    || (sez_Aview.getUsernametext().length()==0) || (sez_Aview.getPasswordtext().length()==0))
              throw new Exception("Completare tutti i campi obbligatori");
            //ANCHE PER LA SEZIONE C E B????

            controllo= true;

        }catch(Exception e){
            basicframe.ErrorMessage(e.getMessage());

        }finally {
            return controllo;
        }


    }
}
