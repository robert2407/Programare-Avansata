package org.example;

import jakarta.persistence.*;

public class ArtistRepository {

    private static final EntityManagerFactory emf;

    static {
        emf = Persistence.createEntityManagerFactory("MusicDB");
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
}