package trabalho;

import java.io.Serializable;
import java.util.*;

class Conferencia extends Veiculo implements Serializable{
    List<Integer> numeros = new ArrayList<Integer>();
    List<String> locais = new ArrayList<String>();
    static final long serialVersionUID = 1L;

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
