import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

class Serializacao{
    // Construtor

    /**
     * Constutor da classe Serialização
     */
    public Serializacao(){}
    /**
     * Função que serializa um objeto.
     * @param trabalho objeto principal do sistema.
     */
    public void serializar(Trabalho trabalho){
        try{ 
            FileOutputStream file = new FileOutputStream("recredenciamento.dat"); 
            ObjectOutputStream out = new ObjectOutputStream(file); 
  
            out.writeObject(trabalho);
            out.flush(); 
            out.close();
            file.flush(); 
            file.close(); 
        } 
        catch(IOException ex){ 
            System.out.println("Erro de I/O"); 
        } 
    }

    /**
     * Função que desserializa um objeto.
     * @return objeto principal do sistema.
     */
    public Trabalho desserializar(){
        Trabalho t = null;
        try{
            FileInputStream file = new FileInputStream("recredenciamento.dat");
            ObjectInputStream in = new ObjectInputStream(file);

            t = (Trabalho)in.readObject();
            in.close();
            file.close();
        }
        catch(IOException ex){
            System.out.println("Erro de I/O");
        }
        catch(ClassNotFoundException ex){
            System.out.println("Erro de I/O");
        }
        return t;
    }
}