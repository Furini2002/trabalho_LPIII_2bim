package br.com.FuriniSolutions.bean;

import java.util.Date;

public class NotaFiscal {
    
    private int id;
    private Date dataEmissao;
    private Cliente cliente;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "NotaFiscal{" + "id=" + id + ", dataEmissao=" + dataEmissao + ", cliente=" + cliente + '}';
    }
    
    
}
