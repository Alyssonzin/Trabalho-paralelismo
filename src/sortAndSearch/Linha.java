package src.sortAndSearch;

public class Linha {
    private String nome;
    private int numeroLinha;

    public Linha(String nome, int numeroLinha) {
        this.nome = nome;
        this.numeroLinha = numeroLinha;
    }

    public String getNome() {
        return nome;
    }

    public int getNumeroLinha() {
        return numeroLinha;
    }
}
