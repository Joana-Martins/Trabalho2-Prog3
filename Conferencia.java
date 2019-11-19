import java.util.*;

class Conferencia extends Veiculo{
    List<Integer> numeros = new ArrayList<Integer>();
    List<String> locais = new ArrayList<String>();

    // Construtor
    public Conferencia(String sigla, String nome, String tipo, float impacto){
        super(sigla, nome, tipo, impacto);
    }

    // Sets
    public void set_numeros(int numero){
        this.numeros.add(numero);
    }
    public void set_locais(String local){
        this.locais.add(local);
    }

    // Gets
    public List<Integer> get_numeros(){
        return this.numeros;
    }
    public List<String> get_locais(){
        return this.locais;
    }
}