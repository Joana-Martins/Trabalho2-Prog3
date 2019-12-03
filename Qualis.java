import java.io.Serializable;

class Qualis implements Serializable{
    int ano;
    String nota;
    Float pontuacao;
    static final long serialVersionUID = 1L;

    // Construtor

    /**
     * Construtor da classe Qualis.
     * @param ano ano do Qualis.
     * @param nota nota do Qualis.
     */
    public Qualis(int ano, String nota){
        this.set_ano(ano);
        this.set_nota(nota);
    }

    // Sets

    /**
     * Função que seta o ano do Qualis.
     * @param ano ano do Qualis.
     */
    public void set_ano(int ano){
        this.ano = ano;
    }

    /**
     * Função que seta a nota do Qualis.
     * @param nota nota do Qualis.
     */
    public void set_nota(String nota){
        this.nota = nota;
    }

    /**
     * Função que seta a pontuação do Qualis.
     * @param pontuacao pontuação do Qualis.
     */
    public void set_pontuacao(Float pontuacao){
        this.pontuacao = pontuacao;
    }

    // Gets

    /**
     * Função que retorna o ano do Qualis.
     * @return ano do Qualis.
     */
    public int get_ano(){
        return this.ano;
    }

    /**
     * Função que retorna a nota do Qualis.
     * @return nota do Qualis.
     */
    public String get_nota(){
        return this.nota;
    }

    /**
     * Função que retorna a pontuação do Qualis.
     * @return pontuação do Qualis.
     */
    public Float get_pontuacao(){
        return this.pontuacao;
    }

    /**
     * Função que calcula a pontuação do Qualis.
     * @param regra regra a ser considerada para o cálculo da pontuação do Qualis.
     * @param ano ano a ser considerado para o cálculo da pontuação do Qualis.
     */
    public void calcula_pontuacao(Regra regra, int ano){     
        //if(this.get_ano() >= ano - regra.get_anos()){
            int contador = 0;
            while(contador<regra.notas.length-1){
                if(this.get_nota().compareTo(regra.notas[contador])>=0 && this.get_nota().compareTo(regra.notas[contador+1])<0){
                    this.set_pontuacao(Float.parseFloat(regra.pontos[contador]));
                    break;
                }
                contador++;
            }
            if(contador==regra.notas.length-1) this.set_pontuacao(Float.parseFloat(regra.pontos[contador]));
        //}
        //else this.set_pontuacao(Float.parseFloat("0"));
    }
}
