package src.blocos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SearchBlocks implements Runnable {
    private int threadId; // id da thread
    private File arquivo;
    private String target;
    private long initTime;
    private ArrayList<String> linhasList = new ArrayList<String>();

    public SearchBlocks(String target, File arquivo, long initTime, int threadId) {
        this.target = target;
        this.arquivo = arquivo;
        this.initTime = initTime;
        this.threadId = threadId;

        // Crie um array para armazenar os nomes
        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
            String linha;

            while ((linha = leitor.readLine()) != null) {
                linhasList.add(linha);
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + arquivo.getName());
        }
    }

    @Override
    public void run() {
        int divisor = 1000; // número de linhas por bloco
        int blocos = linhasList.size() / divisor;

        for (int i = 0; i < blocos; i++) {
            int inicio = i * divisor;
            int fim = Math.min(inicio + divisor, linhasList.size());
            List<String> bloco = linhasList.subList(inicio, fim);

            new Thread(() -> {
                int index = inicio;
                for (String linha : bloco) {

                    if (linha.equals(target)) {
                        System.out.println("Encontrado: " + linha + " no arquivo " + arquivo.getName() +
                                "\nThread: " + threadId +
                                "\nLinha: " + index +
                                "\nTempo: " + (new Date().getTime() - initTime) + " milissegundos" +
                                "\n-------------------------------------------------------------");
                        System.exit(0);
                    }
                    index++;
                }
            }).start();
        }

        System.out.println("-------------------------------------------------------------" +
                "\nThread " + threadId + " finalizada" +
                "\nem: " + (new Date().getTime() - initTime) + " milissegundos" +
                "\n-------------------------------------------------------------");
    }
}
