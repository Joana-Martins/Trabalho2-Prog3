import java.io.Serializable;

class Periodico extends Veiculo implements Serializable{
    String ISSN;
    int volume;
    static final long serialVersionUID = 1L;

    // Construtor

    /**
     * Construtor da classe Periódico.
     * @param sigla sigla do periódico.
     * @param nome nome do periódico.
     * @param tipo tipo de veículo. Para periódico, tal parâmetro será igual à 'P'.
     * @param impacto impacto do periódico.
     * @param ISSN ISSN do periódico.
     */
    public Periodico(String sigla, String nome, String tipo, Float impacto, String ISSN){
        super(sigla, nome, tipo, impacto);
        this.set_ISSN(ISSN);
    }

    // Sets

    /**
     * Função que seta o ISSN do periódico.
     * @param ISSN ISSN do periódico.
     */
    public void set_ISSN(String ISSN){
        this.ISSN = ISSN;
    }

    /**
     * Função que seta o volume do periódico.
     * @param volume volume do periódico.
     */
    public void set_volume(int volume){
        this.volume = volume;
    }

    // Gets

    /**
     * Função que retorna o ISSN do periódico.
     * @return ISSN do periódico.
     */
    public String get_ISSN(){
        return this.ISSN;
    }

    /**
     * Função que retorna o volume do periódico.
     * @return volume do periódico.
     */
    public int get_volume(){
        return this.volume;
    }
}
