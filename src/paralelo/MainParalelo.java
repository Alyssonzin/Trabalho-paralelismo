package src.paralelo;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;

public class MainParalelo {

	public static void main(String[] args) {
        File diretorio = new File("./src/arquivos/p");
        String nome = "Danielle Hall";
        
        System.out.println("Buscando pelo nome: "+nome+" paralelamente");
        long initTime = new Date().getTime();
        
        ArrayList<Thread> threads = new ArrayList<Thread>();
        
        if (diretorio.exists() && diretorio.isDirectory()) {
            File[] arquivos = diretorio.listFiles((dir, name) -> name.endsWith(".txt"));
            
            if (arquivos != null) {
                for (File arquivo : arquivos) {
                	LerArquivoParalelo lerArquivo = new LerArquivoParalelo(nome, arquivo, initTime);
                	Thread t = new Thread(lerArquivo);
                	threads.add(t);
                    t.start();
                }
            }
        }
        
        System.out.println("Fim das buscas em: "+ (new Date().getTime() - initTime)+" milissegundos");
    }

}
