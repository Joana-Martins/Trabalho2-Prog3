import java.util.*;
import java.io.*;
import java.lang.*;
import java.text.SimpleDateFormat;

class Trabalho{
    List<Docente> docentes = new ArrayList<Docente>();
    List<Veiculo> veiculos = new ArrayList<Veiculo>();
    List<Publicacao> publicacoes = new ArrayList<Publicacao>();
    List<Regra> regras = new ArrayList<Regra>();
    List<Qualis> qualis = new ArrayList<Qualis>();
    int ano;

    public Trabalho(){}

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
            Float impacto = scanner.nextFloat();

            if(tipo.equals("P")){
                String ISSN = scanner.next();
                Periodico periodico = new Periodico(sigla, nome, tipo, impacto, ISSN);
                this.veiculos.add(periodico);
            }
            if(tipo.equals("C")){
                Conferencia conferencia = new Conferencia(sigla, nome, tipo, impacto);
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
            String veiculo_ = scanner.next();
            String titulo = scanner.next();
            String[] docentes_ = scanner.next().split(",");                 
            int numero = scanner.nextInt();
            String volume_ = scanner.next();
            String local = scanner.next();
            int paginaInicial = scanner.nextInt();
            int paginaFinal = scanner.nextInt();
            
            List<Docente> docentes = new ArrayList<Docente>();
            for(String autor:docentes_){
                for(Docente docente:this.docentes){
                    if(String.valueOf(docente.get_codigo()).equals(autor)) docentes.add(docente);
                }
            }

            Publicacao publicacao = null;
            for(Veiculo v:this.veiculos){
                if(v.get_sigla().equals(veiculo_)){
                    if(v.tipo.equals("P")){
                        int volume = Integer.parseInt(volume_);
                        Periodico periodico = (Periodico)v;
                        periodico.set_volume(volume);
                        publicacao = new Publicacao(ano, periodico, titulo, docentes, numero, paginaInicial, paginaFinal);
                    }
                    if(v.get_tipo().equals("C")){
                        Conferencia conferencia = (Conferencia)v;
                        conferencia.set_locais(local);
                        conferencia.set_numeros(numero);
                        publicacao = new Publicacao(ano, conferencia, titulo, docentes, numero, paginaInicial, paginaFinal);
                    }
                }
            }

            for(Docente d:docentes) d.set_publicacoes(publicacao);
            if(this.publicacoes.isEmpty()) this.publicacoes.add(publicacao);
            else{
                int contagem = 0;
                for(Publicacao p:this.publicacoes){
                    if(p.get_titulo()!=titulo) contagem++;
                }
                if(contagem==this.publicacoes.size()) this.publicacoes.add(publicacao);
            }
            if(scanner.hasNextLine()) scanner.nextLine();
            else break;
        }
        scanner.close();
    }

    public void carregaArquivosQualis(File qualis) throws Exception{
        Scanner scanner = new Scanner(qualis, "UTF-8");
        scanner.nextLine();
        while(scanner.hasNext()){
            scanner.useDelimiter(";|\n");
            int ano = scanner.nextInt();
            String veiculo = scanner.next();
            String nota = scanner.next();
            Qualis q = new Qualis(ano, nota);
            
            Calendar calendar = new GregorianCalendar();
            for(Regra regra:this.regras){
                calendar.setTime(regra.get_inicioVigencia());
                int year = calendar.get(Calendar.YEAR);
                if(year == this.ano){
                    q.calcula_pontuacao(regra, this.ano);
                }
            }
            this.qualis.add(q);
            for(Veiculo v:this.veiculos){
                if(v.get_sigla().equals(veiculo)) v.set_qualis(q);
            }
            if(scanner.hasNextLine()) scanner.nextLine();
            else break;
        }
    }

    public void carregaArquivoRegras(File regras) throws Exception{
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Scanner scanner = new Scanner(regras, "UTF-8");
        scanner.nextLine();
        while(scanner.hasNext()){
            scanner.useDelimiter(";|\n");
            Date inicioVigencia = formato.parse(scanner.next());
            Date fimVigencia = formato.parse(scanner.next());
            String[] notas = scanner.next().split(",");
            String[] pontos = scanner.next().split(",");      
            Float multiplicador = scanner.nextFloat();
            int anos = scanner.nextInt();
            Float minimoPontos = scanner.nextFloat();
            this.regras.add(new Regra(inicioVigencia, fimVigencia, notas, pontos, multiplicador, anos, minimoPontos));
            if(scanner.hasNextLine()) scanner.nextLine();
            else break;
        }
    }

    public void imprimeArquivoPublicacoes(FileWriter publicacoes) throws Exception{
        FileWriter fr = null;
        BufferedWriter bufferWriter = new BufferedWriter(publicacoes);
        bufferWriter.append("Ano;Sigla Veículo;Veículo;Qualis;Fator de Impacto;Título;Docentes\n");
    
        String[] qualis = {"A1", "A2", "B1", "B2", "B3", "B4", "B5", "C"};
        for(String q:qualis){
            List<Publicacao> lista = new ArrayList<Publicacao>();
            for(Publicacao p:this.publicacoes){
                if(p.get_veiculo().get_qualis().get_nota().equals(q)) lista.add(p);
            }
            lista.sort(publicacao);
            for(Publicacao publicacao:lista){
                bufferWriter.append(publicacao.get_ano()+";");
                bufferWriter.append(publicacao.get_veiculo().get_sigla()+";");
                bufferWriter.append(publicacao.get_veiculo().get_nome()+";");
                bufferWriter.append(publicacao.get_veiculo().get_qualis().get_nota()+";");
                bufferWriter.append(String.format("%.3f", publicacao.get_veiculo().get_impacto())+";");
                bufferWriter.append(publicacao.get_titulo()+";");
                int contagem = 1;
                for(Docente docente:publicacao.autores){
                    bufferWriter.append(docente.get_nome());
                    if(publicacao.autores.size() - contagem != 0){
                        bufferWriter.append(",");
                        contagem++;
                    }
                }
                bufferWriter.append("\n");
            }
        }
        bufferWriter.close();
    }

    public void imprimeArquivoRecredenciamento(FileWriter recredenciamento) throws Exception{
        FileWriter fr = null;
        BufferedWriter bufferWriter = new BufferedWriter(recredenciamento);
        bufferWriter.append("Docente;Pontuação;Recredenciado?\n");
        for(Docente d:this.docentes){
            System.out.println(d.get_nome()+": ");
            d.calcula_pontuacao();
        }
    }

    Comparator<Publicacao> publicacao = new Comparator<Publicacao>(){
        public int compare(Publicacao p1, Publicacao p2){
            if(Integer.compare(p1.get_ano(), p2.get_ano())==0){
                if(p1.get_veiculo().get_sigla().compareTo(p2.get_veiculo().get_sigla())==0){
                    return p1.get_titulo().compareTo(p2.get_titulo());
                }
                return p1.get_veiculo().get_sigla().compareTo(p2.get_veiculo().get_sigla());
            }
            return Integer.compare(p2.get_ano(), p1.get_ano());
        }
    };

    public static void main(String argv[]) throws Exception {
        Trabalho trabalho = new Trabalho();

        File docentes = null, veiculos = null, publicacoes = null, qualis = null, regras = null;
        List<Character> parametros = new ArrayList<Character>();
        for(int i=0;i<6;i++){
            parametros.add(argv[2*i].charAt(1));
            switch(parametros.get(i)){
                case 'd': docentes = new File(argv[2*i+1]); break; 
                case 'v': veiculos = new File(argv[2*i+1]); break;   
                case 'p': publicacoes = new File(argv[2*i+1]); break; 
                case 'q': qualis = new File(argv[2*i+1]); break; 
                case 'r': regras = new File(argv[2*i+1]); break; 
                case 'a': trabalho.ano = Integer.parseInt(argv[2*i+1]);
                default: break;
            }
        }
        trabalho.carregaArquivoDocentes(docentes);
        trabalho.carregaArquivoVeiculos(veiculos);
        trabalho.carregaArquivoPublicacoes(publicacoes);
        trabalho.carregaArquivoRegras(regras);
        trabalho.carregaArquivosQualis(qualis);

        FileWriter output_publicacoes = new FileWriter("2-publicacoes.txt");
        trabalho.imprimeArquivoPublicacoes(output_publicacoes);
        FileWriter output_recredenciamento = new FileWriter("1-recredenciamento.txt");
        trabalho.imprimeArquivoRecredenciamento(output_recredenciamento);
    }
}
