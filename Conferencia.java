class Conferencia extends Veiculo{
    int numero;

    // Construtor
    public Conferencia(String sigla, String nome, String tipo, int numero){
        super(sigla, nome, tipo);
        this.set_numero(numero);
    }

    // Sets
    public void set_numero(int numero){
        this.numero = numero;
    }

    // Gets
    public int get_numero(){
        return this.numero;
    }
}