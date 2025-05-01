package br.com.FuriniSolutions.dao;

import br.com.FuriniSolutions.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceException;
import java.util.List;

public class GenericDAO<Entity> {

    protected final EntityManagerFactory emf = JPAUtil.getEntityManager().getEntityManagerFactory();
    protected Class<Entity> entityClass;

    public GenericDAO(Class<Entity> entityClass) {
        this.entityClass = entityClass;
    }

    public void create(Entity entity) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            em.getTransaction().rollback(); // Garante rollback em caso de erro
            throw new RuntimeException("Erro ao criar entidade: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    public Entity retrieve(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(entityClass, id);
        } catch (PersistenceException e) {
            throw new RuntimeException("Erro ao buscar entidade: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    public void update(Entity entity) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao atualizar entidade: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    public boolean delete(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Entity entity = em.find(entityClass, id);
            if (entity != null) {
                em.remove(entity);
                em.getTransaction().commit();
                return true;
            }
            em.getTransaction().rollback();
            return false;
        } catch (PersistenceException e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao excluir entidade: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    public List<Entity> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            String query = "FROM " + entityClass.getSimpleName();
            return em.createQuery(query, entityClass).getResultList();
        } catch (PersistenceException e) {
            throw new RuntimeException("Erro ao listar entidades: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }
}
