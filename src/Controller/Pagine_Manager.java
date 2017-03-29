package Controller;

/**
 * Manager che ci aiuta nella gestione delle pagine Avanti-Indietro
 */
public class Pagine_Manager {

    private static int Pagina_Corrente;


    /**
     * Setta inizialmente Pagina Corrente a 1
     */
    public static void setPagina_Corrente() {

        Pagina_Corrente = 1;

    }

    /**
     * Incremento Pagina Corrente
     */

    public static void addPagina_Corrente() {

        Pagina_Corrente += 1;

    }

    /**
     * Decremento Pagina Corrente
     */

    public static void subctractPagina_Corrente() {

        Pagina_Corrente -= 1;

    }

    //GETTER
    public static int getPagina_Corrente() {

        return Pagina_Corrente;

    }
}
