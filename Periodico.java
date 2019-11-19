class Periodico extends Veiculo{
    String ISSN;
    int volume;

    // Construtor
    public Periodico(String sigla, String nome, String tipo, Float impacto, String ISSN){
        super(sigla, nome, tipo, impacto);
        this.set_ISSN(ISSN);
    }

    // Sets
    public void set_ISSN(String ISSN){
        this.ISSN = ISSN;
    }
    public void set_volume(int volume){
        this.volume = volume;
    }

    // Gets
    public String get_ISSN(){
        return this.ISSN;
    }
    public int get_volume(){
        return this.volume;
    }
}