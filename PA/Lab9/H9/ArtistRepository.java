package org.example;

import jakarta.persistence.*;

import java.util.List;

public class ArtistRepository extends AbstractRepository<Artist, Long>{

    private static final EntityManagerFactory emf;

    static {
        emf = Persistence.createEntityManagerFactory("MusicDB");
    }
    @Override
    public Class<Artist> getEntityClass() {
        return Artist.class;
    }
    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Artist artist) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(artist);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Artist> findByName(String name) {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("Artist.findByName", Artist.class)
                    .setParameter("name", "%" + name + "%")
                    .getResultList();
        } finally {
            em.close();
        }
    }

    //singleton, ca mai sus
    public EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }

    public void closeEntityManagerFactory() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }

}