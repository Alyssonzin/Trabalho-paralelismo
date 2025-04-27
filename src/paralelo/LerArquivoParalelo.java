package src.paralelo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

public class LerArquivoParalelo implements Runnable {

	private String nome;
	private File arquivo;
	private long initTime;
	
	public LerArquivoParalelo(String nome, File arquivo, long initTime) {
		this.nome = nome;
		this.arquivo = arquivo;
		this.initTime = initTime;
	}

	public String getNome() {
		return nome;
	}

	public File getArquivo() {
		return arquivo;
	}

	@Override
	public void run() {
		System.out.println("Procurando no arquivo "+arquivo.getName());
    	try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            int numeroLinha = 1;
            while ((linha = leitor.readLine()) != null) {
                if (linha.equalsIgnoreCase(nome)) {
                    System.out.println("Nome encontrado no arquivo: " + arquivo.getName() + ", linha: " + numeroLinha+ " em "+(new Date().getTime() - initTime)+" milissegundos");
                    System.exit(0);
                }
                numeroLinha++;
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + arquivo.getName());
        }
	}
	
}
