package br.com.FuriniSolutions.dao;

import br.com.FuriniSolutions.bean.Cliente;
import br.com.FuriniSolutions.bean.NotaFiscal;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NotaFiscalDAO extends GenericDAO<NotaFiscal> {

    public NotaFiscalDAO() {
        super(NotaFiscal.class);
    }

    //Buscar todas as notas fiscais emitidas para um cliente específico.    
    public List<NotaFiscal> buscarNotasClienteEspecifico(Cliente cliente) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<NotaFiscal> query = em.createQuery(""
                    + "SELECT n "
                    + "FROM NotaFiscal n "
                    + "WHERE n.cliente = :cliente",
                    NotaFiscal.class);

            query.setParameter("cliente", cliente);

            return query.getResultList();

        } finally {
            em.close();
        }

    }

    //Calcular o valor total de uma nota fiscal com base nos itens.
    public Double calcularValorTotalNota(NotaFiscal nota) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Double> query = em.createQuery(""
                    + "SELECT SUM(i.quantidade * i.valorItem)  "
                    + "FROM ItemNota i "
                    + "WHERE i.notaFiscal = :nota",
                    Double.class);

            query.setParameter("nota", nota);

            return query.getSingleResult();

        } finally {
            em.close();
        }

    }

    //Listar todas as notas fiscais emitidas em um intervalo de datas
    public List<NotaFiscal> buscarNotaFiscalPorIntervaloData(LocalDate dataInicio, LocalDate dataFim) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<NotaFiscal> query = em.createQuery(
                    "SELECT n FROM NotaFiscal n WHERE n.dataEmissao BETWEEN :dataInicio AND :dataFim",
                    NotaFiscal.class);

            query.setParameter("dataInicio", dataInicio);
            query.setParameter("dataFim", dataFim);

            return query.getResultList();
        } finally {
            em.close();
        }
    }

    //Filtrar notas fiscais por cliente e intervalo de datas.
    public List<NotaFiscal> filtrarNotasPorClienteEIntervaloDeDatas(String nomeCliente, LocalDate dataInicio, LocalDate dataFim) {
        EntityManager em = emf.createEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<NotaFiscal> cq = cb.createQuery(NotaFiscal.class);
            Root<NotaFiscal> notas = cq.from(NotaFiscal.class);

            // list de predicados)
            List<Predicate> predicates = new ArrayList<>();

            //se for nulo ele não acrescenta esse predicado
            if (nomeCliente != null && !nomeCliente.trim().isEmpty()) {
                predicates.add(cb.like(cb.lower(notas.get("cliente").get("nome")), "%" + nomeCliente.toLowerCase() + "%"));
            }

            //se for nulo ele não acrescenta esse predicado
            if (dataInicio != null && dataFim != null) {
                predicates.add(cb.between(notas.get("dataEmissao"), dataInicio, dataFim));
            } else if (dataInicio != null) { // Filtrar apenas por data inicial
                predicates.add(cb.greaterThanOrEqualTo(notas.get("dataEmissao"), dataInicio));
            } else if (dataFim != null) { // Filtrar apenas por data final
                predicates.add(cb.lessThanOrEqualTo(notas.get("dataEmissao"), dataFim));
            }

            cq.select(notas).where(predicates.toArray(new Predicate[0]));

            return em.createQuery(cq).getResultList();
        } finally {
            em.close();
        }
    }

}
