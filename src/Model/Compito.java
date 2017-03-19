package Model;


public class Compito extends Model{

    private String Nome;

    public Compito(String nome) {

        Nome = nome;

    }

    @Override
    public boolean InsertSQL() {
        return false;
    }

    @Override
    public boolean SearchSQL() {
        return false;
    }

    @Override
    public boolean UpdateSQL(String[] Appoggio) {
        return false;
    }
}
