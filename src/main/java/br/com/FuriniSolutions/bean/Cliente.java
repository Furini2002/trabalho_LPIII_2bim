package br.com.FuriniSolutions.bean;

public class Cliente {    
    private int id;
    private String nome;
    private String endereco;

    public Cliente() {        
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", nome=" + nome + ", endereco=" + endereco + '}';
    }

    public Cliente(String nome, String endereco) {        
        this.nome = nome;
        this.endereco = endereco;
    }
    
    
    
}
