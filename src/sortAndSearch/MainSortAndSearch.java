package src.sortAndSearch;

import java.io.File;
import java.util.Date;

public class MainSortAndSearch {
    public static void main(String[] args) {
        File diretorio = new File("./arquivos/g");
        String nome = "Sharon Sullivan";

        System.out.println("Buscando pelo nome: " + nome + " com método de ordenação e busca binária");
        long init = new Date().getTime();

        if(diretorio.exists() && diretorio.isDirectory()) {
            File[] arquivos = diretorio.listFiles((dir, name) -> name.endsWith(".txt"));

            if(arquivos != null) {
                for(File arquivo : arquivos) {
                    SortAndSearch sortAndSearch = new SortAndSearch(nome, arquivo, init);
                    Thread t = new Thread(sortAndSearch);
                    t.start();
                }
            }
        }
        System.out.println("Fim das buscas em: " + (new Date().getTime() - init) + " milissegundos");
    }
}
