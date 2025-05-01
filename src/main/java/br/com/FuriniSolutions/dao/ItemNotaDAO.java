package br.com.FuriniSolutions.dao;

import br.com.FuriniSolutions.bean.ItemNota;
import br.com.FuriniSolutions.bean.NotaFiscal;
import br.com.FuriniSolutions.bean.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ItemNotaDAO extends GenericDAO<ItemNota> {

    public ItemNotaDAO() {
        super(ItemNota.class);
    }

    //Buscar todos os itens de uma nota fiscal específica.
    // esse metodo já exitia
    public List<ItemNota> findAllWithIDNota(Integer idNota) {
        EntityManager em = emf.createEntityManager();

        try {
            String jpql = "SELECT i FROM ItemNota i WHERE i.notaFiscal.id = :idNota";
            TypedQuery<ItemNota> query = em.createQuery(jpql, ItemNota.class);
            query.setParameter("idNota", idNota);

            return query.getResultList();

        } finally {
            em.close();
        }

    }

    //Filtrar itens de nota fiscal por produto e quantidade
    public List<ItemNota> filtrarItensPorProdutoEQuantidade(String produto, Integer quantidadeMinima) {
        EntityManager em = emf.createEntityManager();
        try {
            // cria o CriteriaBuilder
            CriteriaBuilder cb = em.getCriteriaBuilder();

            // cria a query para retornar uma lista de ItemNotaFiscal
            CriteriaQuery<ItemNota> cq = cb.createQuery(ItemNota.class);

            // define a entidade raiz from ***
            Root<ItemNota> item = cq.from(ItemNota.class);

            // cria a lista de predicados (filtros)
            List<Predicate> predicates = new ArrayList<>();

            // filtra por Produto
            if (produto != null) {
                predicates.add(cb.like(cb.lower(item.get("produto").get("descricao")), "%" + produto.toLowerCase() + "%"));
            }

            // filtra por Quantidade Mínima
            if (quantidadeMinima != null) {
                predicates.add(cb.greaterThanOrEqualTo(item.get("quantidade"), quantidadeMinima));
            }

            // Adicionando os filtros na consulta
            cq.select(item).where(predicates.toArray(new Predicate[0]));

            // Criando e executando a query
            return em.createQuery(cq).getResultList();

        } finally {
            em.close();
        }
    }

    //relatorio de itesn vendidos
    public List<Object[]> gerarRelatorioItensVendidosPorIntervalo(LocalDate dataInicio, LocalDate dataFim) {
        EntityManager em = emf.createEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Object[]> query = cb.createQuery(Object[].class);

            Root<ItemNota> item = query.from(ItemNota.class);
            Join<ItemNota, Produto> produto = item.join("produto");
            Join<ItemNota, NotaFiscal> nota = item.join("notaFiscal");

            // expressoes matemáticas 
            Expression<Long> totalQuantidade = cb.coalesce(cb.sum(item.get("quantidade")), 0L);
            Expression<Double> totalVendido = cb.coalesce(cb.sum(cb.prod(item.get("quantidade"), item.get("valorItem"))), 0.0);

            // campos que eu quero selecionar
            query.multiselect(
                    produto.get("descricao"), // Nome do Produto
                    totalQuantidade, // Quantidade Total Vendida
                    totalVendido // Valor Total Vendido
            );

            // whres
            List<Predicate> predicates = new ArrayList<>();

            // se os valores não forem nulos adiciona condição
            if (dataInicio != null && dataFim != null) {
                predicates.add(cb.between(nota.get("dataEmissao"), dataInicio, dataFim));
            } else if (dataInicio != null) {
                predicates.add(cb.greaterThanOrEqualTo(nota.get("dataEmissao"), dataInicio));
            } else if (dataFim != null) {
                predicates.add(cb.lessThanOrEqualTo(nota.get("dataEmissao"), dataFim));
            }

            // coloca filtros na consulta
            if (!predicates.isEmpty()) {
                query.where(predicates.toArray(new Predicate[0]));
            }

            // group by 
            query.groupBy(produto.get("id"), produto.get("descricao"));

            // order by 
            query.orderBy(cb.desc(totalQuantidade));

            TypedQuery<Object[]> typedQuery = em.createQuery(query);
            return typedQuery.getResultList();

        } finally {
            em.close(); 
        }
    }

}
