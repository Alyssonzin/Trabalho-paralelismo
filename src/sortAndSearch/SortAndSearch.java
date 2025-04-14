package src.sortAndSearch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

public class SortAndSearch implements Runnable {
    private File arquivo;
    private String target;
    private String[] nomes = new String[10000]; // Supondo que o arquivo tenha no máximo 1000 nomes
    private long initTime;

    public SortAndSearch(String target, File arquivo, long initTime) {
        this.target = target;
        this.arquivo = arquivo;
        this.initTime = initTime;

        // Crie um array para armazenar os nomes
        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            int i = 0;
            while ((linha = leitor.readLine()) != null) {
                nomes[i] = linha;
                i++;
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + arquivo.getName());
        }
    }

    private void sortNames() {
        java.util.Arrays.sort(nomes);
    }

    @Override
    public void run() {
        System.out.println("Procurando no arquivo " + arquivo.getName());
        sortNames(); // Ordena os nomes antes de procurar

        int linha = 1;
        for(String nome : nomes) {
            if(nome.equalsIgnoreCase(nome)) {
                System.out.println("Nome encontrado no arquivo: " + arquivo.getName() + " na linha " + linha + ", em " + (new Date().getTime() - initTime) + " milissegundos");
                break;
            }
            linha++;
        }
    }
}
