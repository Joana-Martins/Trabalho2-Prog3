import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

class Publicacao implements Serializable{
    int ano;
    Veiculo veiculo;
    String titulo;
    List<Docente> autores;
    int numero;
    int paginaInicial;
    int paginaFinal;
    static final long serialVersionUID = 1L;

    // Construtor

    /**
     * Construtor da classe Publicação.
     * @param ano ano da publicação.
     * @param veiculo veículo da publicação.
     * @param titulo título da publicação.
     * @param docentes docentes autores da publicação.
     * @param numero número da publicação.
     * @param paginaInicial página inicial da publicação.
     * @param paginaFinal página final da publicação.
     */
    public Publicacao(int ano, Veiculo veiculo, String titulo, List<Docente> docentes, int numero, int paginaInicial, int paginaFinal){
        this.set_ano(ano);
        this.set_veiculo(veiculo);
        this.set_titulo(titulo);
        this.set_autores(docentes);
        this.set_numero(numero);
        this.set_paginaInicial(paginaInicial);
        this.set_paginaFinal(paginaFinal);
    }

    // Sets

    /**
     * Função que seta o ano da publicação.
     * @param ano ano da publicação.
     */
    public void set_ano(int ano){
        this.ano = ano;
    }

    /**
     * Função que seta o veículo da publicação.
     * @param veiculo veículo da publicação.
     */
    public void set_veiculo(Veiculo veiculo){
        this.veiculo = veiculo;
    }

    /**
     * Função que seta o título da publicação.
     * @param titulo título da publicação.
     */
    public void set_titulo(String titulo){
        this.titulo = titulo;
    }

    /**
     * Função que seta a lista de docentes autores da publicação.
     * @param docentes lista de docentes autores da publicação.
     */
    public void set_autores(List<Docente> docentes){
        this.autores = docentes;
    }

    /**
     * Função que seta o número da publicação.
     * @param numero número da publicação.
     */
    public void set_numero(int numero){
        this.numero = numero;
    }

    /**
     * Função que seta a página inicial da publicação.
     * @param paginaInicial página inicial da publicação.
     */
    public void set_paginaInicial(int paginaInicial){
        this.paginaInicial = paginaInicial;
    }

    /**
     * Função que seta a página final da publicação.
     * @param paginaFinal página final da publicação.
     */
    public void set_paginaFinal(int paginaFinal){
        this.paginaFinal = paginaFinal;
    }

    // Gets

    /**
     * Função que retorna o ano da publicação.
     * @return ano da publicação.
     */
    public int get_ano(){
        return this.ano;
    }

    /**
     * Função que retorna o veículo da publicação.
     * @return veículo da publicação.
     */
    public Veiculo get_veiculo(){
        return this.veiculo;
    }

    /**
     * Função que retorna o título da publicação.
     * @return título da publicação.
     */
    public String get_titulo(){
        return this.titulo;
    }

    /**
     * Função que retorna a lista de docentes autores da publicação.
     * @return lista de docentes autores da publicação.
     */
    public List<Docente> get_autores(){
        return this.autores;
    }

    /**
     * Função que retorna o número da publicação.
     * @return número da publicação.
     */
    public int get_numero(){
        return this.numero;
    }

    /**
     * Função que retorna a página inicial da publicação.
     * @return página inicial da publicação.
     */
    public int get_paginaInicial(){
        return this.paginaInicial;
    }

    /**
     * Função que retorna a página final da publicação.
     * @return página final da publicação.
     */
    public int get_paginaFinal(){
        return this.paginaFinal;
    }
}
