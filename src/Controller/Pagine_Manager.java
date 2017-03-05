package Controller;

/*
 *Pagine_Manager è un manager che ci aiuta nella gestione delle pagine Avanti-Indietro
 */
public class Pagine_Manager {

    private static int Pagina_Corrente;


    public static void setPagina_Corrente() {

        Pagina_Corrente = 1;

    }

    public static int getPagina_Corrente() {

        return Pagina_Corrente;

    }


    public static void addPagina_Corrente() {

        Pagina_Corrente += 1;

    }


    public static void subctractPagina_Corrente() {

        Pagina_Corrente -= 1;

    }

}
