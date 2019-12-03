import java.io.Serializable;
import java.util.Date;

class Regra implements Serializable{
    Date inicioVigencia;
    Date fimVigencia;
    String[] notas;
    String[] pontos;
    Float multiplicador;
    int anos;
    float minimoPontos;
    static final long serialVersionUID = 1L;

    // Construtor

    /**
     * Construtor da classe Regra.
     * @param inicioVigencia data do início de vigência da regra. 
     * @param fimVigencia data do fim de vigência da regra.
     * @param notas notas da regra.
     * @param pontos pontuação das notas da regra.
     * @param multiplicador fator multiplicador da regra.
     * @param anos anos a serem considerados pela regra.
     * @param minimoPontos mínimo de pontos aceitável pela regra.
     */
    public Regra(Date inicioVigencia, Date fimVigencia, String[] notas, String[] pontos, Float multiplicador, int anos, float minimoPontos){
        this.set_inicioVigencia(inicioVigencia);
        this.set_fimVigencia(fimVigencia);
        this.set_notas(notas);
        this.set_pontos(pontos);
        this.set_multiplicador(multiplicador);
        this.set_anos(anos);
        this.set_minimoPontos(minimoPontos);
    }

    // Sets

    /**
     * Função que seta a data do início de vigência da regra.
     * @param inicioVigencia data do início de vigência da regra.
     */
    public void set_inicioVigencia(Date inicioVigencia){
        this.inicioVigencia = inicioVigencia;
    }

    /**
     * Função que seta a data do fim de vigêcia da regra.
     * @param fimVigencia data do fim de vigência da regra.
     */
    public void set_fimVigencia(Date fimVigencia){
        this.fimVigencia = fimVigencia;
    }

    /**
     * Função que seta as notas da regra.
     * @param notas notas da regra.
     */
    public void set_notas(String[] notas){
        this.notas = notas;
    }

    /**
     * Função que seta a pontuação das notas da regra.
     * @param pontos pontuação das notas da regra.
     */
    public void set_pontos(String[] pontos){
        this.pontos = pontos;
    }

    /**
     * Função de seta o fator multiplicador da regra.
     * @param multiplicador fator multiplicador da regra.
     */
    public void set_multiplicador(Float multiplicador){
        this.multiplicador = multiplicador;
    }

    /**
     * Função que seta a quantidade de anos a ser considerada pela regra.
     * @param anos anos a serem considerados pela regra.
     */
    public void set_anos(int anos){
        this.anos = anos;
    }

    /**
     * Função que seta o mínimo de pontos aceitável pela regra.
     * @param minimoPontos mínimo de pontos aceitável pela regra.
     */
    public void set_minimoPontos(float minimoPontos){
        this.minimoPontos = minimoPontos;
    }

    // Gets

    /**
     * Função que retorna a data do início de vigência da regra.
     * @return data do início de vigência da regra.
     */
    public Date get_inicioVigencia(){
        return this.inicioVigencia;
    }

    /**
     * Função que retorna a data do fim de vigência da regra.
     * @return data do fim de vigência da regra.
     */
    public Date get_fimVigencia(){
        return this.fimVigencia;
    }

    /**
     * Função que retorna as notas da regra.
     * @return notas da regra.
     */
    public String[] get_notas(){
        return this.notas;
    }

    /**
     * Função que retorna a pontuação das notas da regra.
     * @return pontuação das notas da regra.
     */
    public String[] get_pontos(){
        return this.pontos;
    }

    /**
     * Função que retorna o fator multiplicador da regra.
     * @return fator multiplicador da regra.
     */
    public Float get_multiplicador(){
        return this.multiplicador;
    }

    /**
     * Função que retorna a quantidade de anos a ser considerada pela regra.
     * @return anos a serem considerados pela regra.
     */
    public int get_anos(){
        return this.anos;
    }

    /**
     * Função que retorna o fator multiplicador da regra.
     * @return fator multiplicador da regra.
     */
    public float get_minimoPontos(){
        return this.minimoPontos;
    }
}
