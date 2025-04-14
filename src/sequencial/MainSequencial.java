package src.sequencial;
import java.io.*;
import java.util.Date;

public class MainSequencial {

	public static void main(String[] args) {
        File diretorio = new File("./arquivos/p");
        String nome = "Kathryn Jordan";
        
        System.out.println("Buscando pelo nome: " + nome + " sequencialmente");
        Date init = new Date();
        
        if (diretorio.exists() && diretorio.isDirectory()) {
            File[] arquivos = diretorio.listFiles((dir, name) -> name.endsWith(".txt"));
            
            if (arquivos != null) {
            	
                for (File arquivo : arquivos) {
                	System.out.println("Procurando no arquivo " + arquivo.getName());
                    if(LerArquivoSequencial.buscarNoArquivo(arquivo, nome, init.getTime())) {
                    	break;
                    }
                }
            }
        }
        System.out.println("Fim das buscas em: "+ (new Date().getTime() - init.getTime())+" milissegundos");
    }
}
