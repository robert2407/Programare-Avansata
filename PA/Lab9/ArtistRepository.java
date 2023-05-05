package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class ArtistRepository {

    private static final EntityManagerFactory emf;

    static {
        emf = Persistence.createEntityManagerFactory("ExamplePU");
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
