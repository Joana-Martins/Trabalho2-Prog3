class Qualis{
    int ano;
    String nota;
    int pontuacao;

    // Construtor
    public Qualis(int ano, String nota){
        this.set_ano(ano);
        this.set_nota(nota);
    }

    // Sets
    public void set_ano(int ano){
        this.ano = ano;
    }
    public void set_nota(String nota){
        this.nota = nota;
    }
    public void set_pontuacao(int pontuacao){
        this.pontuacao = pontuacao;
    }

    // Gets
    public int get_ano(){
        return this.ano;
    }
    public String get_nota(){
        return this.nota;
    }
    public int get_pontuacao(){
        return this.pontuacao;
    }
}