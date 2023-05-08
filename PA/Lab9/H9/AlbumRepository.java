package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class AlbumRepository extends AbstractRepository<Album, Long> {

    private static final EntityManagerFactory emf;

    static {
        emf = Persistence.createEntityManagerFactory("MusicDB");
    }
    @Override
    public Class<Album> getEntityClass() {
        return Album.class;
    }
    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Album album) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(album);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Album> findAll() {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("Album.findAll", Album.class).getResultList();
        } finally {
            em.close();
        }
    }

    public List<Album> findByArtist(Artist artist) {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("Album.findByArtist", Album.class)
                    .setParameter("artist", artist)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
