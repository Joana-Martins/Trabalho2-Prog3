package trabalho;

import java.io.Serializable;

class Qualis implements Serializable{
    int ano;
    String nota;
    Float pontuacao;
    static final long serialVersionUID = 1L;

    // Construtor
    public Qualis(int ano, String nota){
        this.set_ano(ano);
        this.set_nota(nota);
    }

    // Sets
    public void set_ano(int ano){
        this.ano = ano;
    }
    public void set_nota(String nota){
        this.nota = nota;
    }
    public void set_pontuacao(Float pontuacao){
        this.pontuacao = pontuacao;
    }

    // Gets
    public int get_ano(){
        return this.ano;
    }
    public String get_nota(){
        return this.nota;
    }
    public Float get_pontuacao(){
        return this.pontuacao;
    }

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
