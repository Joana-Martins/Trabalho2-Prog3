import java.util.*;

class Docente{
    Long codigo;
    String nome;
    Date dataNascimento;
    Date dataIngresso;
    Boolean coordenador;
    List<Publicacao> publicacoes = new ArrayList<Publicacao>();
    Float pontuacao = 0f;

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

    public void calcula_pontuacao(){
        for(Publicacao p:this.publicacoes){
            this.pontuacao += p.get_veiculo().get_qualis().get_pontuacao();
        }
        System.out.print(this.pontuacao+"\n");
    }
}