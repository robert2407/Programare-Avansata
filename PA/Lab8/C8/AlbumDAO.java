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

}
