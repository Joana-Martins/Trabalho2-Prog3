import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;


class Conferencia extends Veiculo implements Serializable{
    List<Integer> numeros = new ArrayList<Integer>();
    List<String> locais = new ArrayList<String>();
    static final long serialVersionUID = 1L;

    // Construtor

    /**
     * Construtor da classe Conferência.
     * @param sigla sigla da conferência.
     * @param nome nome da conferência.
     * @param tipo tipo de veículo. Para conferência, tal parâmetro será igual à 'C'.
     * @param impacto valor de impacto da conferência.
    */
    public Conferencia(String sigla, String nome, String tipo, float impacto){
        super(sigla, nome, tipo, impacto);
    }

    // Sets

    /**
     * Função para setar lista de números das edições da conferência.
     * @param numero número da edição da conferência.
     */
    public void set_numeros(int numero){
        this.numeros.add(numero);
    }

    /**
     * Função para setar lista de locais de realização da conferência.
     * @param local local de realização da conferência.
     */
    public void set_locais(String local){
        this.locais.add(local);
    }

    // Gets

    /**
     * Função que retorna a lista de números das edições da conferência.
     * @return lista de números das edições da conferência.
     */
    public List<Integer> get_numeros(){
        return this.numeros;
    }

    /**
     * Função que retorna a lista de locais de realização da conferência.
     * @return lista de locais de realização da conferência.
     */
    public List<String> get_locais(){
        return this.locais;
    }
}
