package Model;

import Controller.Sez_BController;
import View.Sez_AView;
import View.Sez_BView;
import View.Sez_CView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RegistrazioneModel extends Model {

    private String codicefiscale;
    private Sez_AView sez_Aview;
    private Sez_BView sez_Bview;
    private Sez_CView sez_Cview;
    private String ElementoCercato;
    private Sez_BController sez_bController;


    /*COSTRUTTORE*/
    public RegistrazioneModel(String CodiceFiscale, Sez_AView view1, Sez_BView view2, Sez_CView view3, Sez_BController controller) {

          super();
          codicefiscale = CodiceFiscale;
          sez_Aview = view1;
          sez_Bview = view2;
          sez_bController= controller;
          sez_Cview = view3;

          ElementoCercato = sez_Aview.getUsernametext();

    }

    /**
     * Inserimeto,in fase di registrazione delle sez A,B,C
     * Fa uso di 3 metodi privati:--> insertAIntoSQL, insertBIntoSQL , InsertCIntoSQL
     *
     */

    public boolean InsertSQL(){

        Boolean controllo = true;
        openConnection();

        if(!insertASQL() || !insertBSQL() || !insertCSQL() || !InsertPASS() )
        controllo = false;  //c'è stato qualche problema in fase di inserimento

        closeConnection();

        return controllo;

    }


    public boolean SearchSQL() {

        openConnection();

        Boolean controllo = false;

        String sql ="SELECT* FROM pass ";
        ResultSet query = selectQuery(sql);
        String user;

        try {

            while (!controllo && query.next()) {

                user = query.getString("user");
                // System.out.println(user);
                if (user.equals(ElementoCercato)) {
                    controllo = true;
                }

            }
        }catch(SQLException se){
            se.printStackTrace();
        }

        return controllo;


    }

    @Override
    public boolean UpdateSQL(){

        return false;

    }


    /**
     * Metodo di servizio.
     * insertAIntoSQL inserisce i dati inerenti alla sezione A nel DB
     *
     *
     * @return true --> inserimento andato a buon fine
     * @return false --> problema in fase di inserimento
     *
     */
    private boolean insertASQL(){

        Boolean controllo = false;


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
                System.out.print("tutto bene");
            }


        return controllo;


    }

   private boolean insertBSQL() {

       System.out.println("\nB");
       boolean controllo = false;
       ArrayList<String> listaABILITAZIONE = sez_bController.getListaABILITAZIONE();
       ArrayList<String> listaPATENTE = sez_bController.getListaPATENTE();
       ArrayList<String> listaCORSO= sez_bController.getListaCORSO();


       int i = 0;

       while (i < listaABILITAZIONE.size()) {
           String sql = "Insert into abilitazioni(cf,nome,datascadenza,dataacquisizione,entedirilascio,n_documento) values('" +
                   codicefiscale     + "','" +
                   listaABILITAZIONE.get(i)      + "','" +
                   listaABILITAZIONE.get(i += 1) + "','" +
                   listaABILITAZIONE.get(i += 1) + "','" +
                   listaABILITAZIONE.get(i += 1) + "','" +
                   listaABILITAZIONE.get(i += 1) + "')";
                   i+=1;


           if (updateQuery(sql)) {
               controllo = true;
               System.out.print("tutto bene");
           }
       }

       i=0;
       while (i < listaCORSO.size()) {
           String sql = "Insert into corsi(cf,nome,datascadenza,dataacquisizione,entedirilascio,n_documento) values('" +
                   codicefiscale     + "','" +
                   listaCORSO.get(i)      + "','" +
                   listaCORSO.get(i += 1) + "','" +
                   listaCORSO.get(i += 1) + "','" +
                   listaCORSO.get(i += 1) + "','" +
                   listaCORSO.get(i += 1) + "')";
           i+=1;


           if (updateQuery(sql))
               controllo = true;
       }

       i=0;
       while (i < listaPATENTE.size()) {
           String sql = "Insert into patenti(cf,nome,datascadenza,dataacquisizione,entedirilascio,n_documento) values('" +
                   codicefiscale     + "','" +
                   listaPATENTE.get(i)      + "','" +
                   listaPATENTE.get(i += 1) + "','" +
                   listaPATENTE.get(i += 1) + "','" +
                   listaPATENTE.get(i += 1) + "','" +
                   listaPATENTE.get(i += 1) + "')";
           i+=1;


           if (updateQuery(sql))
               controllo = true;
       }



       return controllo;

    }

    /**
     * Metodo di servizio.
     * insertAIntoSQL inserisce i dati inerenti alla sezione A nel DB
     *
     *
     * @return true --> inserimento andato a buon fine
     * @return false --> problema in fase di inserimento
     *
     */
   private boolean insertCSQL(){

       System.out.println("C");
       Boolean controllo = false;

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
            System.out.print("tutto bene");
        }

        return controllo;
   }

   private boolean InsertPASS(){

       Boolean controllo = false;

       String sql = "Insert into pass(cf,pass,user,vol_o_cand) values('" +
               codicefiscale                                    + "','" +
               sez_Aview.getUsernametext()                      + "','" +
               sez_Aview.getPasswordtext()                      + "','" +
               "0"                                              + "')";


       if(updateQuery(sql)) {
           controllo = true;   //operazione eseguita con successo
           System.out.print("tutto bene");
       }

       return controllo;


   }

}
