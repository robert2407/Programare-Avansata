package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public abstract class AbstractRepository<T, ID> {

    private static final EntityManagerFactory emf;

    static {
        emf = Persistence.createEntityManagerFactory("MusicDB");
    }

    protected abstract Class<T> getEntityClass();

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(T entity) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public T findById(ID id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(getEntityClass(), id);
        } finally {
            em.close();
        }
    }

    public void deleteById(ID id) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            T entity = em.find(getEntityClass(), id);
            em.remove(entity);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

}
