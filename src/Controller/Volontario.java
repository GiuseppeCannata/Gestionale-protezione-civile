package Controller;

import Model.Persona;


public class Volontario extends Persona {


    private int ruolo;



    public Volontario() {
        return;
    }

    @Override
    public boolean UpdateSQL(String[] Appoggio) {
        return false;
    }

    @Override
    public boolean SearchSQL() {
        return false;
    }

    @Override
    public boolean InsertSQL() {
        return false;
    }
}
