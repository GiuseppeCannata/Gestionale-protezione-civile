package Model;


import Controller.Sez_BCandidatoController;
import View.Sez_AView;
import View.Sez_BView;
import View.Sez_CView;

import java.sql.ResultSet;

public class UtenteModel extends Model {

    private Sez_AView sez_Aview;
    private Sez_BView sez_Bview;
    private Sez_CView sez_Cview;
    private Sez_BCandidatoController sez_bCandidatoController;

    public UtenteModel(){

       return;

    }

    @Override
    public boolean InsertSQL() {
        return false;
    }

    @Override
    public boolean UpdateSQL(String[] Appoggio) {

        boolean controllo = false;

        openConnection();

        String sql = "update "+Appoggio[0]+" set "+Appoggio[2]+"='"+Appoggio[3]+"' where cf='"+Appoggio[1]+"'";


        if(updateQuery(sql)){
            controllo = true;
        }
        closeConnection();
        return controllo;

    }

    @Override
    public boolean SearchSQL() {
        return false;
    }
}
