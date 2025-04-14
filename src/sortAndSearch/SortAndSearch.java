package src.sortAndSearch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class SortAndSearch implements Runnable {
    private int threadId; // id da thread
    private File arquivo;
    private String target;
    private long initTime;
    private ArrayList<Linha> linhasList = new ArrayList<Linha>();

    public SortAndSearch(String target, File arquivo, long initTime, int threadId) {
        this.target = target;
        this.arquivo = arquivo;
        this.initTime = initTime;
        this.threadId = threadId;

        // Crie um array para armazenar os nomes
        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
            String nome;
            int numLinha = 1;

            while ((nome = leitor.readLine()) != null) {
                Linha linha = new Linha(nome, numLinha);
                linhasList.add(linha);
                numLinha++;
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + arquivo.getName());
        }
    }

    private void sortNames() {
        // Utiliza TimSort para ordenar os nomes
        // TimSort é o algoritmo de ordenação padrão em Java.
        linhasList.sort((l1, l2) -> l1.getNome().compareToIgnoreCase(l2.getNome()));
    }

    @Override
    public void run() {
        sortNames(); // Ordena os nomes antes de procurar

        int inicio = 0;
        int fim = linhasList.size() - 1;

        while (inicio <= fim) {
            int meio = (inicio + fim) / 2;
            Linha linhaAtual = linhasList.get(meio);

            int comparacao = linhaAtual.getNome().compareToIgnoreCase(target);

            if (comparacao == 0) {
                System.out.println("Nome encontrado: " + linhaAtual.getNome() +
                        "\nno arquivo: " + arquivo.getName() +
                        "\nna linha: " + linhaAtual.getNumeroLinha() +
                        "\npela Thread " + threadId +
                        "\nem: " + (new Date().getTime() - initTime) + " milissegundos");

                System.exit(0);
            } else if (comparacao < 0) {
                inicio = meio + 1;
            } else {
                fim = meio - 1;
            }
        }

        System.out.println("-------------------------------------------------------------" +
                "\nThread " + threadId + " finalizada" +
                "\nem: " + (new Date().getTime() - initTime) + " milissegundos" +
                "\n-------------------------------------------------------------");
    }
}
