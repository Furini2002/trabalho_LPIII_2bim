package br.com.FuriniSolutions.dao;

import br.com.FuriniSolutions.bean.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO extends GenericDAO<Cliente> {

    public ClienteDAO() {
        super(Cliente.class);
    }

    public List<Cliente> buscarPorDescricao(String nome) {
        EntityManager em = emf.createEntityManager();
        
        List<Cliente> clientes = new ArrayList<>();
        try {
            String jpql = "FROM Cliente c WHERE c.nome LIKE :nome";
            clientes = em.createQuery(jpql, Cliente.class)
                    .setParameter("nome", "%" + nome + "%")
                    .getResultList();
        } catch (PersistenceException e) {
            throw new RuntimeException("Erro ao buscar clientes por nome: " + e.getMessage(), e);
        } finally{
            em.close();
        }
        return clientes;
    }
}
