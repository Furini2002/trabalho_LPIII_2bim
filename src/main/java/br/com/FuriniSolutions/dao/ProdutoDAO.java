package br.com.FuriniSolutions.dao;

import br.com.FuriniSolutions.bean.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO extends GenericDAO<Produto> {

    public ProdutoDAO() {
        super(Produto.class);
    }

    public List<Produto> buscarPorDescricao(String descricao) {
        EntityManager em = emf.createEntityManager();
        try {
            String jpql = "SELECT p FROM Produto p WHERE p.descricao LIKE :descricao";
            TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);
            query.setParameter("descricao", "%" + descricao + "%"); // Busca parcial
            return query.getResultList();

        } finally {
            em.close();
        }

    }

    //Listar todos os produtos com valor acima de um determinado valor.
    public List<Produto> buscarProdutoValorMinimo(double min) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Produto> query = em.createQuery(""
                    + "SELECT p "
                    + "FROM Produto p "
                    + "WHERE p.valor > :minimo",
                    Produto.class);

            query.setParameter("minimo", min);

            return query.getResultList();

        } finally {
            em.close();
        }

    }

    //Filtrar produtos por descrição e valor mínimo.
    public List<Produto> filtrarProdutosPorDescricaoEValorMinimo(String descricao, Double valorMin) {
        EntityManager em = emf.createEntityManager();
        try {
            // cria o CriteriaBuilder
            CriteriaBuilder cb = em.getCriteriaBuilder();

            // cria a query para retornar uma lista de produtos
            CriteriaQuery<Produto> cq = cb.createQuery(Produto.class);

            // define a entidade raiz 
            Root<Produto> produto = cq.from(Produto.class);

            // cria lista de predicados (filtros)
            List<Predicate> predicates = new ArrayList<>();

            // filtra a descrição (LIKE %***%)
            //se for nulo ele não acrescenta esse predicado
            if (descricao != null && !descricao.isEmpty()) {
                predicates.add(cb.like(cb.lower(produto.get("descricao")), "%" + descricao.toLowerCase() + "%"));
            }

            // filtra de valor mínimo (greaterThanOrEqualTo para pegar valores maiores ou iguais)
            //se for nulo ele não acrescenta esse predicado
            if (valorMin != null) {
                predicates.add(cb.greaterThanOrEqualTo(produto.get("valor"), valorMin));
            }

            // adsciona os filtros na consulta
            cq.select(produto).where(predicates.toArray(new Predicate[0]));

            return em.createQuery(cq).getResultList();
        } finally {
            em.close();
        }
    }

}
