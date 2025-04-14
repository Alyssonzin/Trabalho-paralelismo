package src.sequencial;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

public class LerArquivoSequencial {
	public static boolean buscarNoArquivo(File arquivo, String nome, long initTime) {

    	try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            int numeroLinha = 1;

            //Percorre cada linha do arquivo
            while ((linha = leitor.readLine()) != null) {
                if (linha.equalsIgnoreCase(nome)) {
                    System.out.println("Nome encontrado no arquivo: " + arquivo.getName() + ", linha: " + numeroLinha+ " em "+(new Date().getTime() - initTime)+" milissegundos");
                    return true;
                }
                numeroLinha++;
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + arquivo.getName());
        }
        return false;
    }
}
