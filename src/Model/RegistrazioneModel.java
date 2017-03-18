package Model;

import Controller.Sez_BRegistrazioneController;
import View.Sez_AView;
import View.Sez_BView;
import View.Sez_CView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * RegistrazioneModel
 * Classe che si occupa dell inserimento e verifica validita username in fase di registrazione
 *
 */

public class RegistrazioneModel extends Model {

    private String codicefiscale;
    private Sez_AView sez_Aview;
    private Sez_BView sez_Bview;
    private Sez_CView sez_Cview;
    private String UsernameInserito;
    private Sez_BRegistrazioneController sez_bRegistrazioneController;


    /*COSTRUTTORI*/

    public RegistrazioneModel() {

        return;

    }

    public RegistrazioneModel(String CodiceFiscale, Sez_AView view1, Sez_BView view2, Sez_CView view3,
                              Sez_BRegistrazioneController controller) {

          super();
          codicefiscale = CodiceFiscale;
          sez_Aview = view1;
          sez_Bview = view2;
          sez_bRegistrazioneController = controller;
          sez_Cview = view3;

          UsernameInserito = sez_Aview.getUsernametext();

    }

    /**
     * Inserimeto,in fase di registrazione delle sez A,B,C
     * Fa uso di 4 metodi privati:--> insertAIntoSQL, insertBIntoSQL , InsertCIntoSQL, InsertPASS
     *
     */

    public boolean InsertSQL(){

        Boolean controllo = true;


        if(!insertASQL() || !insertBSQL() || !insertCSQL() || !InsertPASS() )
        controllo = false;  //c'è stato qualche problema in fase di inserimento

        return controllo;

    }


    /**
     * Ricerca del username.
     * Verifica se l username scelto dall utente è gia stato utilizzato
     *
     * @return
     */
    public boolean SearchSQL() {

        openConnection();

        Boolean controllo = false;

        String sql ="select user from pass ";
        ResultSet query = selectQuery(sql);
        String user;

        try {

            while (!controllo && query.next()) {

                user = query.getString("user");
                 //System.out.println(user);

                if (user.equals(UsernameInserito)) {
                    controllo = true;
                }

            }
        }catch(SQLException se){
            se.printStackTrace();
        }finally{
            closeConnection();
        }

        return controllo;

    }

    @Override
    public boolean UpdateSQL(String[] Appoggio){

        return false;

    }


    /**
     * Metodo di servizio.
     * insertAIntoSQL inserisce i dati inerenti alla sezione A nel DB
     *
     * @return true --> inserimento andato a buon fine
     * @return false --> problema in fase di inserimento
     *
     */
    private boolean insertASQL(){

        Boolean controllo = false;

         openConnection();

         String sql = "Insert into a(cf,nome,cognome,luogodinascita,telefonofisso,indirizzodiresidenza,telefonomobile,email," +
                    "dataprimaiscrizione,professione,eventualespecializzazione,datadinascita) values('" +
                    codicefiscale                           + "','" +
                    sez_Aview.getNometext()                 + "','" +
                    sez_Aview.getCognometext()              + "','" +
                    sez_Aview.getLuogodinascitatext()       + "','" +
                    sez_Aview.getTelefonofissotext()        + "','" +
                    sez_Aview.getIndirizzodiresidenzatext() + "','" +
                    sez_Aview.getTelefonocellularetext()    + "','" +
                    sez_Aview.getEmailtext()                + "'," +
                    "curdate()"                             + ",'" +
                    sez_Aview.getProfessionetext()          + "','" +
                    sez_Aview.getSpecializzazionetext()     + "','" +
                    sez_Aview.getDatadinascitatext()        + "')";

        //gestisco le eventuali eccezioni
            if (updateQuery(sql)) {
                controllo = true;                //operazinoe eseguita con successo
               // System.out.print("tutto bene");
            }

        closeConnection();
        return controllo;


    }

    /**
     * Metodo di servizio.
     * insertBIntoSQL inserisce i dati inerenti alla sezione B nel DB
     *
     *
     * @return true --> inserimento andato a buon fine
     * @return false --> problema in fase di inserimento
     *
     */
    private boolean insertBSQL() {

       //System.out.println("\nB");
       boolean controllo = false;

       ArrayList<Certificazione> listaCERTIFICAZIONI = sez_bRegistrazioneController.getCERTIFICAZIONI();

       int i = 0;

       if(listaCERTIFICAZIONI.size() == 0 )
           controllo = true;
       else{

           while (i < listaCERTIFICAZIONI.size()) {

               if (listaCERTIFICAZIONI.get(i).InsertSQL()) ;
               controllo = true;
              // System.out.println("tutto bene,per inserimento della b");
               i++;
           }

       }

       return controllo;

    }

    /**
     * Metodo di servizio.
     * insertCIntoSQL inserisce i dati inerenti alla sezione C nel DB
     *
     *
     * @return true --> inserimento andato a buon fine
     * @return false --> problema in fase di inserimento
     *
     */
   private boolean insertCSQL(){

       System.out.println("C");
       Boolean controllo = false;

       openConnection();

       String sql = "Insert into c(cf,nomedatore,telefono,faxdatore,email,numero_codice_postale,iban) values('" +
                    codicefiscale                                    + "','" +
                    sez_Cview.getDenominazioneDatoreDiLavorotext()   + "','" +
                    sez_Cview.getTelDatoreDiLavorotext()             + "','" +
                    sez_Cview.getFaxDatoreDiLavorotext()             + "','" +
                    sez_Cview.getEmailDatoreDiLavorotext()           + "','" +
                    sez_Cview.getNumeroCodicePostaletext()           + "','" +
                    sez_Cview.getIbantext()                          + "')";



        if(updateQuery(sql)) {
            controllo = true;   //operazione eseguita con successo
            //System.out.print("tutto bene");
        }
       closeConnection();
        return controllo;
   }

    /**
     * Metodo di servizio.
     * insertPASS inserisce i dati inerenti alla sezione C nel DB
     *
     *
     * @return true --> inserimento andato a buon fine
     * @return false --> problema in fase di inserimento
     *
     */
    private boolean InsertPASS(){

       Boolean controllo = false;
       openConnection();
       String sql = "Insert into pass(cf,pass,user,vol_o_cand,conf_giunta) values('" +
               codicefiscale                                    + "','" +
               sez_Aview.getPasswordtext()                      + "','" +
               sez_Aview.getUsernametext()                      + "','" +
               "0"                                              + "','" +
               "0"                                              + "')";


       if(updateQuery(sql)) {
           controllo = true;   //operazione eseguita con successo
           //System.out.print("tutto bene");
       }

       closeConnection();
       return controllo;
   }

}
