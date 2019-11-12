class Periodico extends Veiculo{
    Float impacto;
    String ISSN;

    // Construtor
    public Periodico(String sigla, String nome, String tipo, Float impacto, String ISSN){
        super(sigla, nome, tipo);
        this.set_impacto(impacto);
        this.set_ISSN(ISSN);
    }

    // Sets
    public void set_impacto(Float impacto){
        this.impacto = impacto;
    }
    public void set_ISSN(String ISSN){
        this.ISSN = ISSN;
    }

    // Gets
    public Float get_impacto(){
        return this.impacto;
    }
    public String get_ISSN(){
        return this.ISSN;
    }
}