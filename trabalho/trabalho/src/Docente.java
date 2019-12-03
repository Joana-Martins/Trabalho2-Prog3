import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

class Docente implements Serializable{
    Long codigo;
    String nome;
    Date dataNascimento;
    Date dataIngresso;
    Boolean coordenador;
    List<Publicacao> publicacoes = new ArrayList<Publicacao>();
    Float pontuacao = 0f;
    String situacao;
    static final long serialVersionUID = 1L;

    // Construtor

    /**
     * Construtor da classe Docente.
     * @param codigo codigo do docente.
     * @param nome nome do docente.
     * @param dataNascimento data de nascimento do docente.
     * @param dataIngresso data de ingresso do docente.
     * @param coordenador indica se o docente é coordenador.
     */
    public Docente(Long codigo, String nome, Date dataNascimento, Date dataIngresso, String coordenador){
        this.set_codigo(codigo);
        this.set_nome(nome);
        this.set_dataNascimento(dataNascimento);
        this.set_dataIngresso(dataIngresso);
        this.set_coordenador(coordenador);
    }

    // Sets

    /**
     * Função que seta o código do docente.
     * @param codigo código do docente.
     */
    public void set_codigo(Long codigo){
        this.codigo = codigo;
    }

    /**
     * Função que seta o nome do docente.
     * @param nome nome do docente.
     */
    public void set_nome(String nome){
        this.nome = nome;
    }
    
    /**
     * Função que seta a data de nascimento do docente.
     * @param dataNascimento data de nascimento do docente.
     */
    public void set_dataNascimento(Date dataNascimento){
        this.dataNascimento = dataNascimento;
    }

    /**
     * Função que seta a data de ingresso do docente.
     * @param dataIngresso data de ingresso do docente.
     */
    public void set_dataIngresso(Date dataIngresso){
        this.dataIngresso = dataIngresso;
    }

    /**
     * Função que seta o parâmetro que indica se o docente é coordenador.
     * @param coordenador se 'X', o docente é coordenador.
     */
    public void set_coordenador(String coordenador){
        if(coordenador.equals("X")) this.coordenador = true;
        else this.coordenador = false;
    }

    /**
     * Função que seta a lista de publicações do docente.
     * @param publicacao publicação do docente.
     */
    public void set_publicacoes(Publicacao publicacao){
        this.publicacoes.add(publicacao);
    }

    /**
     * Função que seta o parâmetro que informa a situação do docente.
     * @param situacao situação do docente.
     */
    public void set_situacao(String situacao){
        this.situacao = situacao;
    }

    // Gets

    /**
     * Função que retorna o código do docente.
     * @return código do docente.
     */
    public Long get_codigo(){
        return this.codigo;
    }

    /**
     * Função que retorna o nome do docente.
     * @return nome do docente.
     */
    public String get_nome(){
        return this.nome;
    }

    /**
     * Função que retorna a data de nascimento do docente.
     * @return data de nascimento do docente.
     */
    public Date get_dataNascimento(){
        return this.dataNascimento;
    }

    /**
     * Função que retorna a data de ingresso do docente.
     * @return data de ingresso do docente.
     */
    public Date get_dataIngresso(){
        return this.dataIngresso;
    }

    /**
     * Função que retorna se o docente é coordenador.
     * @return se 'true', o docente é coordenador.
     */
    public Boolean get_coordenador(){
        return this.coordenador;
    }

    /**
     * Função que retorna a lista de publicações do docente.
     * @return lista de publicações do docente.
     */
    public List<Publicacao> get_publicacao(){
        return this.publicacoes;
    }

    /**
     * Função que retorna a pontuação do docente.
     * @return pontuação do docente.
     */
    public float get_pontuacao(){
        return this.pontuacao;
    }

    /**
     * Função que retorna situação do docente.
     * @return situação do docente.
     */
    public String get_situacao(){
        return this.situacao;
    }

    /**
     * Função que calcula a pontuação do docente.
     * @param regra regra a ser considerada para cálculo da pontuação do docente.
     */
    public void calcula_pontuacao(Regra regra){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(regra.get_inicioVigencia());
        int ano = calendar.get(Calendar.YEAR);
        for(Publicacao p:this.publicacoes){
            if(ano - regra.get_anos() <= p.get_ano()){
                if(p.get_veiculo().get_tipo().equals("P")) this.pontuacao += (p.get_veiculo().get_qualis().get_pontuacao()*regra.get_multiplicador());
                else this.pontuacao += p.get_veiculo().get_qualis().get_pontuacao();
            }
        }
    }

    /**
     * Função que calcula a situação do docente.
     * @param ano ano a ser considerado para cálculo da situação do docente.
     * @param regra regra a ser considerada para cálculo da situação do docente.
     */
    public void calcula_situacao(int ano, Regra regra){
        Calendar c1 = new GregorianCalendar(); c1.setTime(this.get_dataNascimento());
        Calendar c2 = new GregorianCalendar(); c2.setTime(this.get_dataIngresso());

        if(this.get_coordenador()) this.set_situacao("Coordenador");
        else if(ano - c2.get(Calendar.YEAR) <= 3) this.set_situacao("PPJ");
        else if(ano - c1.get(Calendar.YEAR) > 60) this.set_situacao("PPS");
        else if(this.get_pontuacao() >= regra.get_minimoPontos()) this.set_situacao("Sim");
        else this.set_situacao("Não");
    }
}
