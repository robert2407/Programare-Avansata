package org.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String args[]) {
        Connection connection = null;
        try {
            connection = Database.getConnection();
            connection.setAutoCommit(false);
            var artists = new ArtistDAO();
            try {
                artists.create("Pink Floyd");
                System.out.println("Am adaugat un artist");
                artists.create("Michael Jackson");
                System.out.println("Am adaugat un artist");
            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
            }



            Import importer = new Import();
            importer.importData(connection, "C:\\Users\\Robert\\Desktop\\albumlist.csv");



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

                genres.findByName("Pop");

            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
            }

            try {
                connection.setAutoCommit(false);
                Database.getConnection().commit();
                System.out.println("Am facut commit");
            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
            }

            var albums = new AlbumDAO();
            try {
                System.out.println("Am adaugat un album");
                albums.create(1979, "The Wall", "Pink Floyd", "Rock");
                System.out.println("Am adaugat un album");
                albums.create2(1982, "Thriller", "Michael Jackson","Funk,Soul,Pop");
                System.out.println("Am adaugat un album");
                albums.create2(1982, "Thriller", "Michael Jackson","Funk,Soul,Pop");
            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
            }
            connection.setAutoCommit(false);
            Database.getConnection().commit();
            //am facut commit

            Database.getConnection().close();
        } catch (SQLException e) {
            System.err.println(e);
            Database.rollback(connection);
        }
    }
}
