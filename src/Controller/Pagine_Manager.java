package Controller;

/**
 * Pagine_Manager è un manager che ci aiuta nella gestione delle pagine Avanti-Indietro
 * Classe statica
 */
public class Pagine_Manager {

    private static int Pagina_Corrente;
    private static int Fine_Pagina;


    public static void setPagina_Corrente() {

        Pagina_Corrente = 1;
        Fine_Pagina = 1;

    }

    public static int getPagina_Corrente() {

        return Pagina_Corrente;

    }


    public static void addPagina_Corrente() {

        Pagina_Corrente += 1;
        Fine_Pagina += 1;

    }


    public static void subctractPagina_Corrente() {

        Pagina_Corrente -= 1;

    }

    public static int getFine_Pagina(){

        return Fine_Pagina;
    }

}
