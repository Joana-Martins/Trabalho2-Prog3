import java.io.Serializable;

class Veiculo implements Serializable{
    String sigla;
    String nome;
    String tipo;
    float impacto;
    Qualis qualis;
    static final long serialVersionUID = 1L;

    // Construtor
    public Veiculo(String sigla, String nome, String tipo, float impacto){
        this.set_sigla(sigla);
        this.set_nome(nome);
        this.set_tipo(tipo);
        this.set_impacto(impacto);
    }

    // Sets
    public void set_sigla(String sigla){
        this.sigla = sigla;
    }
    public void set_nome(String nome){
        this.nome = nome;
    }
    public void set_tipo(String tipo){
        this.tipo = tipo;
    }
    public void set_impacto(float impacto){
        this.impacto = impacto;
    }
    public void set_qualis(Qualis qualis){
        this.qualis = qualis;
    }

    // Gets
    public String get_sigla(){
        return this.sigla;
    }
    public String get_nome(){
        return this.nome;
    }
    public String get_tipo(){
        return this.tipo;
    }
    public float get_impacto(){
        return this.impacto;
    }
    public Qualis get_qualis(){
        return this.qualis;
    }
}
