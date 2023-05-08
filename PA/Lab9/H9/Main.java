package org.example;
import java.util.List;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.persistence.*;
import jakarta.persistence.EntityManagerFactory;

public class Main {
    private static final int NUM_ARTISTS = 100;
    private static final int ALBUM_FOR_ARTIST = 10;
    private static final Random RANDOM = new Random();
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MusicDB");
        EntityManager em = emf.createEntityManager();

        long start = System.currentTimeMillis();

        for (int i = 0; i < NUM_ARTISTS; i++) {
            Artist artist = new Artist("Artist " + i);
            em.getTransaction().begin();
            em.persist(artist);
            for (int j = 0; j < ALBUM_FOR_ARTIST; j++) {
//                Album album = new Album(artist, "Album " + j);
                Album album = new Album(artist, "Album " + RANDOM.nextInt());
                em.persist(album);
            }
            em.getTransaction().commit();
        }

        long end = System.currentTimeMillis();

        LOGGER.info((end - start) + " ms");

        Query query = em.createQuery("SELECT COUNT(a) FROM Album a");
        long numAlbums = (long) query.getSingleResult();
        LOGGER.info("Number of albums in database: " + numAlbums);

        em.getTransaction().begin();
        Artist artist = new Artist("Beatles");
        em.persist(artist);

        Artist a = (Artist)em.createQuery(
                        "select e from Artist e where e.name='Beatles'")
                .getSingleResult();
        a.setName("The Beatles");

        GenreRepository genreRepo = new GenreRepository();
        genreRepo.create(new Genre("Rock"));
        List<Genre> genres = genreRepo.findByName("Rock");

        System.out.println("Genres found by name:");
        for (Genre genre : genres) {
            System.out.println(genre);
        }
        genreRepo.closeEntityManagerFactory();

        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}