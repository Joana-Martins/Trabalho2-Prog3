import java.util.*;
import java.io.*;
import java.lang.*;
import java.nio.*;
import java.text.*;

public class Trabalho implements Serializable{
    List<Docente> docentes = new ArrayList<Docente>();
    List<Veiculo> veiculos = new ArrayList<Veiculo>();
    List<Publicacao> publicacoes = new ArrayList<Publicacao>();
    List<Regra> regras = new ArrayList<Regra>();
    List<Qualis> qualis = new ArrayList<Qualis>();
    int ano;
    int exit = 0;

    private static final long serialVersionUID = 1L;

    // Construtor

    /**
     * Construtor da classe Trabalho
     */
    public Trabalho(){}

    // Funções de formatação de texto


    /**
     * Função que formata uma string, retirando possíveis espaços desnecessários.
     * @param palavra palavra a ser formatada.
     * @return palavra formatada.
     */
    public String formata_string(String palavra){
        String aux = palavra;
        if(palavra.endsWith(" ")) aux = palavra.substring(0, palavra.length() - 1);
        if(palavra.startsWith(" ")) aux = palavra.substring(1, palavra.length());
        return aux;
    }

    /**
     * Função que formata um float, trocando a vírgula de uma string por um ponto.
     * @param numero número a ser formatado.
     * @return número formatado.
     */
    public String formata_float(String numero){
        if(numero.contains(",")) return numero.replace(",", ".");
        else return numero;
    }

    /**
     * Função que carrega o arquivo de docentes.
     * @param docentes aquivo de docentes a ser lido.
     * @throws Exception
     */
    public void carregaArquivoDocentes(FileInputStream docentes) throws Exception{
        String palavra;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Scanner scanner = new Scanner(docentes,"UTF-8");
        scanner.nextLine();
        while(scanner.hasNext()){
            try{
                scanner.useDelimiter(";|\n");

                palavra = formata_float(scanner.next().trim());
                Excecoes.ErroDeFormatacao.checa_se_numero(palavra);
                Long codigo = Long.parseLong(palavra);

                String nome = scanner.next().trim();

                palavra = scanner.next().trim();
                Excecoes.ErroDeFormatacao.checa_se_data(palavra);
                Date dataNascimento = formato.parse(palavra);

                palavra = scanner.next().trim();
                Excecoes.ErroDeFormatacao.checa_se_data(palavra);
                Date dataIngresso = formato.parse(palavra);

                String coordenador = scanner.next().trim();

                Docente docente = new Docente(codigo, nome, dataNascimento, dataIngresso, coordenador);
                this.docentes.add(docente);
            }
            catch(Excecoes.ErroDeFormatacao exception){
                System.out.println(exception.getMessage());
                this.exit = 1;
            }
            if(scanner.hasNextLine()) scanner.nextLine();
            else break;
        }
        scanner.close();
    }

    /**
     * Função que carrega o arquivo de veículos.
     * @param veiculos arquivo de veículos a ser lido.
     * @throws Exception
     */
    public void carregaArquivoVeiculos(FileInputStream veiculos) throws Exception{
        String palavra;
        Scanner scanner = new Scanner(veiculos, "UTF-8");
        scanner.nextLine();
        while(scanner.hasNext()){
            try{
                scanner.useDelimiter(";|\n");
                String sigla = scanner.next().trim();
                String nome = scanner.next().trim();
                String tipo = scanner.next().trim();

                palavra = formata_float(scanner.next().trim());
                Excecoes.ErroDeFormatacao.checa_se_numero(palavra);
                Float impacto = Float.parseFloat(palavra);

                if(tipo.equals("P")){
                    String ISSN = scanner.next().trim();
                    Periodico periodico = new Periodico(sigla, nome, tipo, impacto, ISSN);
                    this.veiculos.add(periodico);
                }
                else if(tipo.equals("C")){
                    Conferencia conferencia = new Conferencia(sigla, nome, tipo, impacto);
                    this.veiculos.add(conferencia);
                }
            }
            catch(Excecoes.ErroDeFormatacao exception){
                System.out.println(exception.getMessage());
                this.exit = 1;
            }
            if(scanner.hasNextLine()) scanner.nextLine();
            else break;
        }
        scanner.close();
    }

    /**
     * Função que carrega o arquivo de publicações.
     * @param publicacoes arquivo de publicações a ser lido.
     * @throws Exception
     */
    public void carregaArquivoPublicacoes(FileInputStream publicacoes) throws Exception{
        String palavra;
        Scanner scanner = new Scanner(publicacoes, "UTF-8");
        scanner.nextLine();
        while(scanner.hasNext()){
            try{
                scanner.useDelimiter(";|\n");

                palavra = scanner.next().trim();
                Excecoes.ErroDeFormatacao.checa_se_numero(palavra);
                int ano = Integer.parseInt(palavra);

                String veiculo = scanner.next().trim();
                String titulo = scanner.next().trim();
                String[] docentes_ = scanner.next().split(",");
                for(String d:docentes_) d.trim();
                
                palavra = scanner.next().trim();
                Excecoes.ErroDeFormatacao.checa_se_numero(palavra);
                int numero = Integer.parseInt(palavra);
                
                String volume_ = scanner.next().trim();
                String local = scanner.next().trim();

                palavra = scanner.next().trim();
                Excecoes.ErroDeFormatacao.checa_se_numero(palavra);
                int paginaInicial = Integer.parseInt(palavra);

                palavra = scanner.next().trim();
                Excecoes.ErroDeFormatacao.checa_se_numero(palavra);
                int paginaFinal = Integer.parseInt(palavra);
            
                List<Docente> docentes = new ArrayList<Docente>();
                for(String autor:docentes_){
                    for(Docente docente:this.docentes){
                        if(String.valueOf(docente.get_codigo()).equals(autor.trim())) docentes.add(docente);
                    }
                }

                Publicacao publicacao = null;
                for(Veiculo v:this.veiculos){
                    if(v.get_sigla().equals(veiculo)){
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
                if(ano < this.ano){
                    for(Docente d:docentes) d.set_publicacoes(publicacao);
                }
                if(this.publicacoes.isEmpty()) this.publicacoes.add(publicacao);
                else{
                    int contagem = 0;
                    for(Publicacao p:this.publicacoes){
                        if(p.get_titulo()!=titulo) contagem++;
                    }
                    if(contagem==this.publicacoes.size()) this.publicacoes.add(publicacao);
                }
            }
            catch(Excecoes.ErroDeFormatacao exception){
                System.out.println(exception.getMessage());
                this.exit = 1;
            }
            catch(NullPointerException exception){
                this.exit = 1;
            }
            if(scanner.hasNextLine()) scanner.nextLine();
            else break;
        }
        scanner.close();
    }

    /**
     * Função que carrega o arquivo de qualis.
     * @param qualis arquivo de qualis a ser lido.
     * @throws Exception
     */
    public void carregaArquivosQualis(FileInputStream qualis) throws Exception{
        String palavra;
        Scanner scanner = new Scanner(qualis, "UTF-8");
        scanner.nextLine();
        while(scanner.hasNext()){
            try{
                scanner.useDelimiter(";|\n");

                palavra = scanner.next().trim();
                Excecoes.ErroDeFormatacao.checa_se_numero(palavra);
                int ano = Integer.parseInt(palavra);

                String veiculo = formata_string(scanner.next());
                String nota = formata_string(scanner.next());

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
            }
            catch(Excecoes.ErroDeFormatacao exception){
                System.out.println(exception.getMessage());
                this.exit = 1;
            }
            catch(NullPointerException exception){
                this.exit = 1;
            }
            if(scanner.hasNextLine()) scanner.nextLine();
            else break;
        }
        scanner.close();
    }

    /**
     * Função que carrega o arquivo de regras.
     * @param regras arquivo de regras a ser lido.
     * @throws Exception
     */
    public void carregaArquivoRegras(FileInputStream regras) throws Exception{
        String palavra;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Scanner scanner = new Scanner(regras, "UTF-8");
        scanner.nextLine();
        while(scanner.hasNext()){
            try{
                scanner.useDelimiter(";|\n");

                palavra = scanner.next().trim();
                Excecoes.ErroDeFormatacao.checa_se_data(palavra);
                Date inicioVigencia = formato.parse(palavra);

                palavra = scanner.next().trim();
                Excecoes.ErroDeFormatacao.checa_se_data(palavra);
                Date fimVigencia = formato.parse(palavra);

                String[] notas = scanner.next().split(",");

                palavra = scanner.next().trim();
                Excecoes.ErroDeFormatacao.checa_se_pontos(palavra);
                String[] pontos = palavra.split(",");

                palavra = formata_float(scanner.next().trim());
                Excecoes.ErroDeFormatacao.checa_se_numero(palavra);
                Float multiplicador = Float.parseFloat(palavra);

                palavra = scanner.next().trim();
                Excecoes.ErroDeFormatacao.checa_se_numero(palavra);
                int anos = Integer.parseInt(palavra);

                palavra = formata_float(scanner.next().trim());
                Excecoes.ErroDeFormatacao.checa_se_numero(palavra);
                Float minimoPontos = Float.parseFloat(palavra);

                this.regras.add(new Regra(inicioVigencia, fimVigencia, notas, pontos, multiplicador, anos, minimoPontos));
            }
            catch(Excecoes.ErroDeFormatacao exception){
                System.out.println(exception.getMessage());
                this.exit = 1;
            }
            if(scanner.hasNextLine()) scanner.nextLine();
            else break;
        }
        scanner.close();
    }

    /**
     * Função que escreve o arquivo de saída de publicações.
     * @param publicacoes arquivo a ser gerado.
     * @throws Exception
     */
    public void imprimeArquivoPublicacoes(FileWriter publicacoes) throws Exception{
        try{
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
        catch(NullPointerException exception){
            this.exit = 1;
        }
    }

    /**
     * Função que escreve o arquivo de saída de recredenciamento.
     * @param recredenciamento arquivo a ser gerado.
     * @throws Exception
     */
    public void imprimeArquivoRecredenciamento(FileWriter recredenciamento) throws Exception{
        try{
            BufferedWriter bufferWriter = new BufferedWriter(recredenciamento);
            bufferWriter.append("Docente;Pontuação;Recredenciado?\n");
            this.docentes.sort(docente);

            Regra regra = null;
            Calendar calendar = new GregorianCalendar();
            for(Regra r:this.regras){
                calendar.setTime(r.get_inicioVigencia());
                int year = calendar.get(Calendar.YEAR);
                if(year == this.ano) regra = r; break;
            }
            for(Docente d:this.docentes){
                d.calcula_pontuacao(regra);
                d.calcula_situacao(this.ano, regra);
                bufferWriter.append(d.get_nome()+";");
                bufferWriter.append(String.format("%.1f", d.get_pontuacao())+";");
                bufferWriter.append(d.get_situacao()+"\n");
            }
            bufferWriter.close();
        }
        catch(NullPointerException exception){
            this.exit = 1;
        }
    }
    
    /**
     * Função que escreve o arquivo de saída de estatísticas.
     * @param estatisticas arquivo a ser gerado.
     * @throws Exception
     */
    public void imprimeArquivoEstatisticas(FileWriter estatisticas) throws Exception{
        try{
            BufferedWriter bufferWriter = new BufferedWriter(estatisticas);
            bufferWriter.append("Qualis;Qtd. Artigos;Média Artigos / Docente\n");
            
            String[] qualis = {"A1", "A2", "B1", "B2", "B3", "B4", "B5", "C"};
            for(String q:qualis){
                int publicacao_qualis = 0;
                Float arquivos_docente = 0f;
                for(Publicacao p:this.publicacoes){
                    if(p.get_veiculo().get_qualis().get_nota().equals(q)){
                        publicacao_qualis++;
                        arquivos_docente = arquivos_docente + (float)((1.0)/p.get_autores().size());
                    }
                }
                bufferWriter.append(q + ";");
                bufferWriter.append(publicacao_qualis + ";");
                bufferWriter.append(String.format("%.2f", arquivos_docente) + "\n");
            }
            bufferWriter.close();
        }
        catch(NullPointerException exception){
            this.exit = 1;
        }
    }

    static Comparator<Publicacao> publicacao = new Comparator<Publicacao>(){
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

    static Comparator<Docente> docente = new Comparator<Docente>(){
        public int compare(Docente d1, Docente d2){
            return d1.get_nome().compareTo(d2.get_nome());
        }
    };

    public static void main(String argv[]) throws Exception {
        Trabalho trabalho = new Trabalho();
        FileInputStream docentes = null, veiculos = null, publicacoes = null, qualis = null, regras = null;

        for(int i=0;i<13;i++){
            if(i == 12 && argv.length == 12) break;
            else if(i == 1 && argv.length == 1) break;
            try{
                if(argv[i].equals("-d")) docentes = new FileInputStream(argv[i+1]);
                else if(argv[i].equals("-v")) veiculos = new FileInputStream(argv[i+1]);
                else if(argv[i].equals("-p")) publicacoes = new FileInputStream(argv[i+1]);
                else if(argv[i].equals("-q")) qualis = new FileInputStream(argv[i+1]);
                else if(argv[i].equals("-r")) regras = new FileInputStream(argv[i+1]);
                else if(argv[i].equals("-a")) trabalho.ano = Integer.parseInt(argv[i+1]);
                else if(argv[i].equals("--read-only")) trabalho.exit = 2;
                else if(argv[i].equals("--write-only")) trabalho.exit = 3;
                else trabalho.exit = 0;
            }
            catch(FileNotFoundException exception){
                System.out.println("Erro de I/O");
                trabalho.exit = 1;
            }
            catch(ArrayIndexOutOfBoundsException exception){
                System.out.println("Erro de I/O");
                trabalho.exit = 1;
            }
            catch(SecurityException exception){
                System.out.println("Erro de I/O");
                trabalho.exit = 1;
            }
        }

        if(trabalho.exit == 0){
            trabalho.carregaArquivoDocentes(docentes);
            trabalho.carregaArquivoVeiculos(veiculos);
            trabalho.carregaArquivoPublicacoes(publicacoes);
            trabalho.carregaArquivoRegras(regras);
            trabalho.carregaArquivosQualis(qualis);

            FileWriter output_recredenciamento = new FileWriter("1-recredenciamento.csv");
            FileWriter output_publicacoes = new FileWriter("2-publicacoes.csv");
            FileWriter output_estatisticas = new FileWriter("3-estatisticas.csv");

            trabalho.imprimeArquivoRecredenciamento(output_recredenciamento);
            trabalho.imprimeArquivoPublicacoes(output_publicacoes);
            trabalho.imprimeArquivoEstatisticas(output_estatisticas);
        }

        else if(trabalho.exit == 2){
            trabalho.carregaArquivoDocentes(docentes);
            trabalho.carregaArquivoVeiculos(veiculos);
            trabalho.carregaArquivoPublicacoes(publicacoes);
            trabalho.carregaArquivoRegras(regras);
            trabalho.carregaArquivosQualis(qualis);

            Serializacao serializacao = new Serializacao();
            serializacao.serializar(trabalho);
        }

        else if(trabalho.exit == 3){
            Serializacao serializacao = new Serializacao();
            trabalho = serializacao.desserializar();

            FileWriter output_recredenciamento = new FileWriter("1-recredenciamento.csv");
            FileWriter output_publicacoes = new FileWriter("2-publicacoes.csv");
            FileWriter output_estatisticas = new FileWriter("3-estatisticas.csv");

            trabalho.imprimeArquivoRecredenciamento(output_recredenciamento);
            trabalho.imprimeArquivoPublicacoes(output_publicacoes);
            trabalho.imprimeArquivoEstatisticas(output_estatisticas);
        }
    }
}