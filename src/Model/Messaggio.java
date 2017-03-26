package Model;


public class Messaggio extends Model {

    private String Destinatario; //cf del destinatario

    private String Mittente;
    private String Messaggio;

    public Messaggio(String destinatario, String mittente, String messaggio) {

        Destinatario = destinatario;

        Mittente = mittente;
        Messaggio = messaggio;

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

        Boolean controllo = false;

        openConnection();

        String sql = "Insert messaggi (Destinatario,Mittente,messaggio) values('" +
                Destinatario                  + "','" +
                Mittente                      + "','" +
                Messaggio                     + "')";


        if(updateQuery(sql)) {
            controllo = true;   //operazione eseguita con successo
        }

        closeConnection();

        return controllo;

    }

    public String getMittente() {

        return Mittente;

    }


    public String getMessaggio() {
        return Messaggio;
    }

    public void setMessaggio(String messaggio) {

        Messaggio = messaggio;

    }
}
