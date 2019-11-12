import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;

class trabalho{
    List<Docente> docentes = new ArrayList<Docente>();
    List<Veiculo> veiculos = new ArrayList<Veiculo>();

    public trabalho(){}

    public void carregaArquivoDocentes(File docentes) throws Exception{
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Scanner scanner = new Scanner(docentes,"UTF-8");
        scanner.nextLine();
        while(scanner.hasNext()){
            scanner.useDelimiter(";|\n");
            Long codigo = scanner.nextLong();
            String nome = scanner.next();
            Date dataNascimento = formato.parse(scanner.next());
            Date dataIngresso = formato.parse(scanner.next());
            String coordenador = scanner.next();

            Docente docente = new Docente(codigo, nome, dataNascimento, dataIngresso, coordenador);
            this.docentes.add(docente);
        }
        scanner.close();
    }

    public void carregaArquivoVeiculos(File veiculos) throws Exception{
        Scanner scanner = new Scanner(veiculos, "UTF-8");
        scanner.nextLine();
        while(scanner.hasNext()){
            scanner.useDelimiter(";|\n");
            String sigla = scanner.next();
            String nome = scanner.next();
            String tipo = scanner.next();
            if(tipo.equals("P")){
                Float impacto = scanner.nextFloat();
                String ISSN = scanner.next();
                Periodico periodico = new Periodico(sigla, nome, tipo, impacto, ISSN);
                this.veiculos.add(periodico);
            }
            if(tipo.equals("C")){
                int numero = scanner.nextInt();
                Conferencia conferencia = new Conferencia(sigla, nome, tipo, numero);
                this.veiculos.add(conferencia);
            }
            if(scanner.hasNextLine()) scanner.nextLine();
            else break;
        }
        scanner.close();
    }

    public void carregaArquivoPublicacoes(File publicacoes) throws Exception{
        Scanner scanner = new Scanner(publicacoes, "UTF-8");
        scanner.nextLine();
        while(scanner.hasNext()){
            scanner.useDelimiter(";|\n");
            int ano = scanner.nextInt();
            String veiculo = scanner.next();
            String titulo = scanner.next();
            String autores = scanner.next();
            int numero = scanner.nextInt();
            String volume = scanner.next();
            String local = scanner.next();
            int paginaInicial = scanner.nextInt();
            int paginaFinal = scanner.nextInt();          
        }
        scanner.close();
    }

    public static void main(String argv[]) throws Exception {
        trabalho t = new trabalho();

        for(int i=0;i<argv.length;i++){
            if(argv[i].equals("-d")==true){
                File docentes = new File("docentes.csv");
                t.carregaArquivoDocentes(docentes);
            }
            if(argv[i].equals("-v")==true){
                File veiculos = new File("veiculos.csv");
                t.carregaArquivoVeiculos(veiculos);
            }
            if(argv[i].equals("-p")==true){
                File publicacoes = new File("publicacoes.csv");
                t.carregaArquivoPublicacoes(publicacoes);
            }
            //if(argv[i].equals("-q")==true){
            //    File qualis = new File("qualis.csv");
            //}
            //if(argv[i].equals("-r")==true){
            //    File regras = new File("regras.csv");
        }
    }
}