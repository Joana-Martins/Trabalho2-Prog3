import java.util.*;

class Regra{
    Date inicioVigencia;
    Date fimVigencia;
    String[] notas;
    String[] pontos;
    Float multiplicador;
    int anos;
    float minimoPontos;

    // Construtor
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
    public void set_inicioVigencia(Date inicioVigencia){
        this.inicioVigencia = inicioVigencia;
    }
    public void set_fimVigencia(Date fimVigencia){
        this.fimVigencia = fimVigencia;
    }
    public void set_notas(String[] notas){
        this.notas = notas;
    }
    public void set_pontos(String[] pontos){
        this.pontos = pontos;
    }
    public void set_multiplicador(Float multiplicador){
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
    public String[] get_notas(){
        return this.notas;
    }
    public String[] get_pontos(){
        return this.pontos;
    }
    public Float get_multiplicador(){
        return this.multiplicador;
    }
    public int get_anos(){
        return this.anos;
    }
    public float get_minimoPontos(){
        return this.minimoPontos;
    }
}