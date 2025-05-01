package br.com.FuriniSolutions.bean;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class Cliente {   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Basic
    @Column(length = 100, nullable = false)
    private String nome;
    
    @Basic
    @Column(length = 120)
    private String endereco;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private List<NotaFiscal> notasfiscais;

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

    public List<NotaFiscal> getNotasfiscais() {
        return notasfiscais;
    }

    public void setNotasfiscais(List<NotaFiscal> notasfiscais) {
        this.notasfiscais = notasfiscais;
    }

    @Override
    public String toString() {
        return nome;
    }

    public Cliente(String nome, String endereco) {        
        this.nome = nome;
        this.endereco = endereco;
    }
    
    
    
}
