class Regras{
    Date inicioVigencia;
    Date fimVigencia;
    List<String> qualis = new ArrayList<String>();
    List<Integer> pontos = new ArrayList<Integer>();
    float multiplicador;
    int anos;
    float minimoPontos;

    // Construtor
    public Regras(Date inicioVigencia, Date fimVigencia, String qualis, int pontuacao, float multiplicador, int anos, float minimoPontos){
        this.set_inicioVigencia(inicioVigencia);
        this.set_fimVigencia(fimVigencia);
        this.set_qualis(qualis);
        this.set_pontos(pontuacao);
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
    public void set_qualis(String qualis){
        this.qualis.add(qualis);
    }
    public void set_pontos(int pontuacao){
        this.pontos.add(pontuacao);
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
    public List<String> get_qualis(){
        return this.qualis;
    }
    public List<Integer> get_pontos(){
        return this.pontos;
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
}