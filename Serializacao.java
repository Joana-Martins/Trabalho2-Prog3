import java.io.*;
import java.util.*;

class Serializacao{
    public Serializacao(){}
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