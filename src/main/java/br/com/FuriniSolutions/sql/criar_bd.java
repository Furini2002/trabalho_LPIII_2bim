package br.com.FuriniSolutions.sql;

import br.com.FuriniSolutions.bean.Cliente;
import br.com.FuriniSolutions.bean.ItemNota;
import br.com.FuriniSolutions.bean.NotaFiscal;
import br.com.FuriniSolutions.bean.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.Date;
import java.util.List;

public class criar_bd {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("furini_db");
        EntityManager em = emf.createEntityManager();

        try {
            // Inicia uma transação
            em.getTransaction().begin();

            // Criação de entidades Cliente e Produto
            Cliente cliente = new Cliente("João Silva", "Rua A, 123");
            Produto produto = new Produto();
            produto.setDescricao("Palito de dente");
            produto.setValor(4.0);

            // Persistir Cliente e Produto
            em.persist(cliente);
            em.persist(produto);

            // Criação de NotaFiscal e ItemNota
            NotaFiscal notaFiscal = new NotaFiscal();
            notaFiscal.setDataEmissao(new Date());
            notaFiscal.setCliente(cliente);

            ItemNota itemNota = new ItemNota();
            itemNota.setQuantidade(2);
            itemNota.setValorItem(produto.getValor());
            itemNota.setProduto(produto);
            itemNota.setNotaFiscal(notaFiscal);

            // Associando o ItemNota à NotaFiscal
            notaFiscal.setListaItens(List.of(itemNota));

            // Persistir NotaFiscal e ItemNota
            em.persist(notaFiscal);
            em.persist(itemNota);

            // Commit da transação
            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
            emf.close();
        }
    }

}
