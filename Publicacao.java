import java.util.*;

class Publicacao{
    int ano;
    Veiculo veiculo;
    String titulo;
    List<Docente> autores;
    int numero;
    int paginaInicial;
    int paginaFinal;

    // Construtor
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
    public void set_ano(int ano){
        this.ano = ano;
    }
    public void set_veiculo(Veiculo veiculo){
        this.veiculo = veiculo;
    }
    public void set_titulo(String titulo){
        this.titulo = titulo;
    }
    public void set_autores(List<Docente> docentes){
        this.autores = docentes;
    }
    public void set_numero(int numero){
        this.numero = numero;
    }
    public void set_paginaInicial(int paginaInicial){
        this.paginaInicial = paginaInicial;
    }
    public void set_paginaFinal(int paginaFinal){
        this.paginaFinal = paginaFinal;
    }

    // Gets
    public int get_ano(){
        return this.ano;
    }
    public Veiculo get_veiculo(){
        return this.veiculo;
    }
    public String get_titulo(){
        return this.titulo;
    }
    public List<Docente> get_autores(){
        return this.autores;
    }
    public int get_numero(){
        return this.numero;
    }
    public int get_paginaInicial(){
        return this.paginaInicial;
    }
    public int get_paginaFinal(){
        return this.paginaFinal;
    }
}