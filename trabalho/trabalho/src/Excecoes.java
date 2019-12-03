import java.lang.*;
import java.io.*;
import java.util.*;

public class Excecoes{
    public static class ErroDeFormatacao extends Exception{
        private static final long serialVersionUID = 1L;
        public ErroDeFormatacao(){
            super("Erro de formatação");
        }
        public static void checa_se_numero(String palavra) throws ErroDeFormatacao{
            for(int i=0;i<palavra.length();i++){
                if(!Character.isDigit(palavra.charAt(i)) && palavra.charAt(i)!='.') throw new ErroDeFormatacao();
            }
        }
        public static void checa_se_data(String palavra) throws ErroDeFormatacao{
            for(int i=0;i<palavra.length();i++){
                if(!Character.isDigit(palavra.charAt(i)) && palavra.charAt(i)!='/') throw new ErroDeFormatacao();
            }
        }
        public static void checa_se_pontos(String palavra) throws ErroDeFormatacao{
            for(int i=0;i<palavra.length();i++){
                if(!Character.isDigit(palavra.charAt(i)) && palavra.charAt(i)!=',') throw new ErroDeFormatacao();
            }
        }
    }

    public static class ErroDeCodigo extends Exception{
        private static final long serialVersionUID = 1L;

        public ErroDeCodigo(String mensagem){
            super(mensagem);
        }
        public static void checa_se_existe(Docente docente, List<Docente> docentes) throws ErroDeCodigo{
            for(Docente d:docentes){
                if(d.get_codigo().equals(docente.get_codigo())){
                    throw new ErroDeCodigo("Código repetido para docente: "+d.get_codigo()+".");
                } 
            }
        }
    }

    public static class ErroDeVeiculo extends Exception{
        private static final long serialVersionUID = 1L;

        public ErroDeVeiculo(){
            super("Sigla de veículo não definida usada na publicação");
        }
        public static void checa_se_existe(Veiculo veiculo, List<Veiculo> veiculos) throws ErroDeVeiculo{
            int contador = 0;
            for(Veiculo v:veiculos){
                if(!veiculo.get_sigla().equals(v.get_sigla())) contador++;
            }
            if(contador==veiculos.size()) throw new ErroDeVeiculo();
        }
    }

    public static class ErroDeDocente extends Exception{
        private static final long serialVersionUID = 1L;

        public ErroDeDocente(String mensagem){
            super(mensagem);
        }
        public static void checa_se_existe(String docente, List<Docente> docentes, String publicacao) throws ErroDeDocente{
            int contador = 0;
            for(Docente d:docentes){
                if(!d.get_codigo().equals(docente)) contador++;
            }
            if(contador==docentes.size()){
                throw new ErroDeDocente("Código de docente não definido usado na publicação \""+publicacao+"\": "+docente+".");
            } 
        }
    }
}