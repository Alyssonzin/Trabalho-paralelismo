package src.blocos;

import java.io.File;
import java.util.Date;

//O(N)
public class MainBlocos {
    public static void main(String[] args) {
        File diretorio = new File("./arquivos/g");
        String nome = "Sharon Sullivan";

        System.out.println("Buscando pelo nome: " + nome + " com método de busca por blocos");
        long init = new Date().getTime();

        if (diretorio.exists() && diretorio.isDirectory()) {
            File[] arquivos = diretorio.listFiles((dir, name) -> name.endsWith(".txt"));

            int threadId = 0; // identificador da thread
            if (arquivos != null) {
                for (File arquivo : arquivos) {
                    threadId++;
                    SearchBlocks searchBlocks = new SearchBlocks(nome, arquivo, init, threadId);
                    Thread t = new Thread(searchBlocks);
                    t.start();
                }
            }
        }
    }
}
