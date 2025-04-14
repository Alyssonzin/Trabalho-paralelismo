package src.sortAndSearch;

import java.io.File;
import java.util.Date;

//Demora entre 90 e 100 milissegundos
public class MainSortAndSearch {
    public static void main(String[] args) {
        File diretorio = new File("./arquivos/g");
        String nome = "Holly Lynch";

        System.out.println("Buscando pelo nome: " + nome + " com método de ordenação e busca binária");
        long init = new Date().getTime();

        if(diretorio.exists() && diretorio.isDirectory()) {
            File[] arquivos = diretorio.listFiles((dir, name) -> name.endsWith(".txt"));

            int threadId = 0; // identificador da thread
            if(arquivos != null) {
                for(File arquivo : arquivos) {
                    threadId++;
                    SortAndSearch sortAndSearch = new SortAndSearch(nome, arquivo, init, threadId);
                    Thread t = new Thread(sortAndSearch);
                    t.start();
                }
            }
        }
    }
}
