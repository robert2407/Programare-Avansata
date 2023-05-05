package org.example;

import java.sql.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AlbumDAO {

    public void create(int year, String name, String artistName, String genreName) throws SQLException {
        String sql = "INSERT INTO albums (year, name, artist_id, genre_id) " +
                "VALUES (?, ?, (SELECT id FROM artists WHERE name = ? LIMIT 1), " +
                "(SELECT id FROM genres WHERE name = ? LIMIT 1))";
        Connection con = Database.getConnection();
        try (
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, year);
            pst.setString(2, name);
            pst.setString(3, artistName);
            pst.setString(4, genreName);
            pst.executeUpdate();
        }
    }

    public void create2(int year, String name, String artistName, String genreName) throws SQLException {
        //pentru inserarile cu virgula am separat cuvintele cu virgula si m-am ocupat separat de ele.
        Connection con = Database.getConnection();
        try {
            String artistSql = "SELECT id FROM artists WHERE name = ?";
            PreparedStatement artistPst = con.prepareStatement(artistSql);
            artistPst.setString(1, artistName);
            ResultSet artistRs = artistPst.executeQuery();
            if (!artistRs.next()) {
                throw new SQLException("Artist not found: " + artistName);
            }
            int artistId = artistRs.getInt("id");

            String[] genres = genreName.split(",");

            String albumSql = "INSERT INTO albums (year, name, artist_id, genre_id) " +
                    "VALUES (?, ?, ?, ?)";
            PreparedStatement albumPst = con.prepareStatement(albumSql);
            albumPst.setInt(1, year);
            albumPst.setString(2, name);
            albumPst.setInt(3, artistId);

            for (String genre : genres) {
                String genreSql = "SELECT id FROM genres WHERE name = ?";
                PreparedStatement genrePst = con.prepareStatement(genreSql);
                genrePst.setString(1, genre.trim());
                ResultSet genreRs = genrePst.executeQuery();
                if (!genreRs.next()) {  // daca nu exista deja o inserez
                    String insertGenreSql = "INSERT INTO genres (name) VALUES (?)";
                    PreparedStatement insertGenrePst = con.prepareStatement(insertGenreSql, Statement.RETURN_GENERATED_KEYS);
                    insertGenrePst.setString(1, genre.trim());
                    insertGenrePst.executeUpdate();

                    ResultSet genreIdRs = insertGenrePst.getGeneratedKeys();    //id autogenereat pt fiecare
                    genreIdRs.next();
                    int genreId = genreIdRs.getInt(1);

                    albumPst.setInt(4, genreId);
                } else {
                    int genreId = genreRs.getInt("id");
                    albumPst.setInt(4, genreId);
                }
                albumPst.executeUpdate();
            }

            con.commit();
        } catch (SQLException e) {
            con.rollback();
            throw e;
        } finally {
            con.close();
        }
    }
}
