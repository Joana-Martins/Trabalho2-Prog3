import java.io.Serializable;
import java.util.*;

class Docente implements Serializable{
    Long codigo;
    String nome;
    Date dataNascimento;
    Date dataIngresso;
    Boolean coordenador;
    List<Publicacao> publicacoes = new ArrayList<Publicacao>();
    Float pontuacao = 0f;
    String situacao = "";
    static final long serialVersionUID = 1L;

    // Construtor
    public Docente(Long codigo, String nome, Date dataNascimento, Date dataIngresso, String coordenador){
        this.set_codigo(codigo);
        this.set_nome(nome);
        this.set_dataNascimento(dataNascimento);
        this.set_dataIngresso(dataIngresso);
        this.set_coordenador(coordenador);
    }

    // Sets
    public void set_codigo(Long codigo){
        this.codigo = codigo;
    }
    public void set_nome(String nome){
        this.nome = nome;
    }
    public void set_dataNascimento(Date dataNascimento){
        this.dataNascimento = dataNascimento;
    }
    public void set_dataIngresso(Date dataIngresso){
        this.dataIngresso = dataIngresso;
    }
    public void set_coordenador(String coordenador){
        if(coordenador.equals("X")) this.coordenador = true;
        else this.coordenador = false;
    }
    public void set_publicacoes(Publicacao publicacao){
        this.publicacoes.add(publicacao);
    }
    public void set_situacao(String situacao){
        this.situacao = situacao;
    }

    // Gets
    public Long get_codigo(){
        return this.codigo;
    }
    public String get_nome(){
        return this.nome;
    }
    public Date get_dataNascimento(){
        return this.dataNascimento;
    }
    public Date get_dataIngresso(){
        return this.dataIngresso;
    }
    public Boolean get_coordenador(){
        return this.coordenador;
    }
    public List<Publicacao> get_publicacao(){
        return this.publicacoes;
    }
    public float get_pontuacao(){
        return this.pontuacao;
    }
    public String get_situacao(){
        return this.situacao;
    }

    public void calcula_pontuacao(Regra regra){
        for(Publicacao p:this.publicacoes){
            if(p.get_veiculo().get_tipo().equals("P")) this.pontuacao += (p.get_veiculo().get_qualis().get_pontuacao()*regra.get_multiplicador());
            else this.pontuacao += p.get_veiculo().get_qualis().get_pontuacao();
        }
    }

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
