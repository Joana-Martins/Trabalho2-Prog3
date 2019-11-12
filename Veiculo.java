class Veiculo{
    String sigla;
    String nome;
    String tipo;

    // Construtor
    public Veiculo(String sigla, String nome, String tipo){
        this.set_sigla(sigla);
        this.set_nome(nome);
        this.set_tipo(tipo);
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
}