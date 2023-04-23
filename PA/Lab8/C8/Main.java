package org.example;

import java.sql.SQLException;

public class Main {
    public static void main(String args[]) {
        try {
            var artists = new ArtistDAO();
            try {
                artists.create("Pink Floyd");
                System.out.println("Am adaugat un artist");
                artists.create("Michael Jackson");
                System.out.println("Am adaugat un artist");
            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
            }

            var genres = new GenreDAO();
            try {
                genres.create("Rock"); //TODO: Funk, Soul, Pop
                System.out.println("Am adaugat un gen");
                genres.create("Funk");
                System.out.println("Am adaugat un gen");
                genres.create("Soul");
                System.out.println("Am adaugat un gen");
                genres.create("Pop");
                System.out.println("Am adaugat un gen");

                //findByName
                genres.findByName("Pop");

            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
            }

            try {
                Database.getConnection().commit();
                System.out.println("Am facut commit");
            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
            }

            var albums = new AlbumDAO();
            try {
                albums.create(1979, "The Wall", "Pink Floyd", "Rock");
                //.create(1982, "Thriller", "Michael Jackson","Funk,Soul,Pop");
            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
            }

            Database.getConnection().commit();
            //am facut commit

            Database.getConnection().close();
        } catch (SQLException e) {
            System.err.println(e);
            Database.rollback();
        }
    }
}