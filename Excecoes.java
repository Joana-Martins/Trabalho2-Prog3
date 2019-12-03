import java.lang.Exception;
import java.lang.Integer;

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
}