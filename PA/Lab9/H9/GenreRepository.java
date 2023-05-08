package org.example;

import jakarta.persistence.*;

import java.util.List;

public class GenreRepository extends AbstractRepository<Genre, Long>{

    private static final EntityManagerFactory emf;

    static {
        emf = Persistence.createEntityManagerFactory("MusicDB");
    }

    @Override
    public Class<Genre> getEntityClass() {
        return Genre.class;
    }

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Genre genre) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(genre);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Genre> findByName(String name) {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("Genre.findByName", Genre.class)
                    .setParameter("name", "%" + name + "%")
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }

    public void closeEntityManagerFactory() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }

}
