package Model;

import Controller.Sez_BUtenteController;
import View.Sez_AView;
import View.Sez_BView;
import View.Sez_CView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Classe che si occupa dell inserimento e verifica validita username in fase di registrazione
 *
 */

public class RegistrazioneModel extends Model {

    private String codicefiscale;
    private Sez_AView sez_Aview;
    private Sez_BView sez_Bview;
    private Sez_CView sez_Cview;
    private String UsernameInserito;
    private Sez_BUtenteController sez_bRegistrazioneController;


    /*COSTRUTTORI*/

    /*costruttore vuoto*/
    public RegistrazioneModel() {

        return;

    }

    public RegistrazioneModel(String CodiceFiscale, Sez_AView view1, Sez_BView view2, Sez_CView view3,
                              Sez_BUtenteController controller) {

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
     * Fa uso di 4 metodi privati: insertAIntoSQL, insertBIntoSQL , InsertCIntoSQL, InsertPASS
     *
     */

    public boolean InsertSQL(){

        boolean controllo = true;


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

        boolean controllo = false;

        String sql ="select user from pass where user='"+UsernameInserito+"'";
        ResultSet query = selectQuery(sql);

        try {

            if(query.next())
                controllo = true;


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
     * @return true ,inserimento andato a buon fine
     * @return false ,problema in fase di inserimento
     *
     */
    private boolean insertASQL(){

        boolean controllo = false;

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
     * @return true  ,inserimento andato a buon fine
     * @return false  ,problema in fase di inserimento
     *
     */
    private boolean insertBSQL() {

       boolean controllo = false;

       ArrayList<Certificazione> CERTIFICAZIONI = sez_bRegistrazioneController.getCERTIFICAZIONI();

       if(CERTIFICAZIONI.size() == 0 )
           controllo = true;
       else{

           for(Certificazione certificazione : CERTIFICAZIONI) {

               if (certificazione.InsertSQL()) ;
               controllo = true;
           }

       }

       return controllo;

    }

    /**
     * Metodo di servizio.
     * insertCIntoSQL inserisce i dati inerenti alla sezione C nel DB
     *
     *
     * @return true  ,inserimento andato a buon fine
     * @return false ,problema in fase di inserimento
     *
     */
   private boolean insertCSQL(){

       boolean controllo = false;

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
     * @return true ,inserimento andato a buon fine
     * @return false ,problema in fase di inserimento
     *
     */
    private boolean InsertPASS(){

       boolean controllo = false;
       openConnection();
       String sql = "Insert into pass(cf,pass,user,vol_o_cand,conf_giunta,primoaccesso,Conf_Archivista) values('" +
               codicefiscale                                    + "','" +
               sez_Aview.getPasswordtext()                      + "','" +
               sez_Aview.getUsernametext()                      + "','" +
               "0"                                              + "','" +
               "0"                                              + "','" +
               "si"                                             + "','" +
               "0"                                              + "')";


       if(updateQuery(sql)) {
           controllo = true;   //operazione eseguita con successo
           //System.out.print("tutto bene");
       }

       closeConnection();
       return controllo;
   }


    @Override
    public String toString() {
        return "RegistrazioneModel{" +
                "codicefiscale='" + codicefiscale + '\'' +
                ", sez_Aview=" + sez_Aview +
                ", sez_Bview=" + sez_Bview +
                ", sez_Cview=" + sez_Cview +
                ", UsernameInserito='" + UsernameInserito + '\'' +
                ", sez_bRegistrazioneController=" + sez_bRegistrazioneController +
                '}';
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RegistrazioneModel that = (RegistrazioneModel) o;

        return codicefiscale != null ? !codicefiscale.equals(that.codicefiscale) : that.codicefiscale != null;

    }

}
