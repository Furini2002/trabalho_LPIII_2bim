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
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Basic
    @Column(nullable = false)
    private String descricao;
    
    @Basic
    @Column(nullable = false)
    private Double valor;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produto")
    private List<ItemNota> itensNota;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public List<ItemNota> getItensNota() {
        return itensNota;
    }

    public void setItensNota(List<ItemNota> itensNota) {
        this.itensNota = itensNota;
    }

    @Override
    public String toString() {
        return descricao;
    }

}
