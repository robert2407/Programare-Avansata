package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ExamplePU");
//        EntityManager em = emf.createEntityManager();

//        Map<String, String> properties = new HashMap<>();
//        properties.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");
//        properties.put("javax.persistence.jdbc.url", "jdbc:postgresql://localhost:5432/MusicDB");
//        properties.put("javax.persistence.jdbc.user", "postgres");
//        properties.put("javax.persistence.jdbc.password", "user");
//        properties.put("javax.persistence.schema-generation.database.action", "create");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MusicDB");



//        em.getTransaction().begin();
//        Artist artist = new Artist("Beatles");
//        em.persist(artist);
//
//        Artist a = (Artist)em.createQuery(
//                        "select e from Artist e where e.name='Beatles'")
//                .getSingleResult();
//        a.setName("The Beatles");
//        em.getTransaction().commit();
//        em.close();
//        emf.close();
    }
}
