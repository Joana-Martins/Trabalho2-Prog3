import java.util.*;

class Regra{
    Date inicioVigencia;
    Date fimVigencia;
    List<Qualis> qualis = new ArrayList<Qualis>();
    float multiplicador;
    int anos;
    float minimoPontos;

    // Construtor
    public Regra(Date inicioVigencia, Date fimVigencia, List<Qualis> qualis, float multiplicador, int anos, float minimoPontos){
        this.set_inicioVigencia(inicioVigencia);
        this.set_fimVigencia(fimVigencia);
        this.set_qualis(qualis);
        this.set_multiplicador(multiplicador);
        this.set_anos(anos);
        this.set_minimoPontos(minimoPontos);
    }

    // Sets
    public void set_inicioVigencia(Date inicioVigencia){
        this.inicioVigencia = inicioVigencia;
    }
    public void set_fimVigencia(Date fimVigencia){
        this.fimVigencia = fimVigencia;
    }
    public void set_qualis(List<Qualis> qualis){
        this.qualis = qualis;
    }
    public void set_multiplicador(float multiplicador){
        this.multiplicador = multiplicador;
    }
    public void set_anos(int anos){
        this.anos = anos;
    }
    public void set_minimoPontos(float minimoPontos){
        this.minimoPontos = minimoPontos;
    }

    // Gets
    public Date get_inicioVigencia(){
        return this.inicioVigencia;
    }
    public Date get_fimVigencia(){
        return this.fimVigencia;
    }
    public List<Qualis> get_qualis(){
        return this.qualis;
    }
    public float get_multiplicador(){
        return this.multiplicador;
    }
    public int get_anos(){
        return this.anos;
    }
    public float get_minimoPontos(){
        return this.minimoPontos;
    }

    public void calcula_pontuacao(String[] notas, String[] pontos, Qualis q){     
        int contador = 0;
        if(q.get_nota()>=notas[contador] && q.get_nota()<notas[contador+1]){
            q.set_pontuacao(Integer.parseInt(pontos[contador])*this.multiplicador);
        }
        else contador++;
    }
}