package model;

public class Elemento {

    private String nome;
    private String simbolo;
    private int numeroAtomico;
    private String familia;

    public Elemento(int numeroAtomico, String nome, String simbolo, String familia) {
        this.nome = nome;
        this.simbolo = simbolo;
        this.numeroAtomico = numeroAtomico;
        this.familia = familia;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public int getNumeroAtomico() {
        return numeroAtomico;
    }

    public void setNumeroAtomico(int numeroAtomico) {
        this.numeroAtomico = numeroAtomico;
    }

    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }
    
    public String toString() {
    	
    	String result = "\nNº Atômico: "+ this.numeroAtomico + 
    					"\nNome: "+ this.nome+ 
    					"\nSímbolo: "+ this.simbolo +
    					"\nFamília: "+ this.familia;
    	
    	return result;
    }
}






















