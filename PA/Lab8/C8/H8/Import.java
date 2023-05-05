package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Import {
    public static void importData(Connection connection, String fileName) {
        String line;
        String[] fields;
        int year;
        String name;
        String artistName;
        String genreName;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String header = reader.readLine(); // sar de capul de tabel caci nu are date, doar numele tabelelor

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO albums2 (year, name, artist_id, genre_id) VALUES (?, ?, ?, ?)");

            while ((line = reader.readLine()) != null) {
                fields = line.split(",", -1);   //tot asa, ma folosesc de separatorul , si inserez in tabela din .csv file

                year = Integer.parseInt(fields[1]);
                name = fields[2];
                artistName = fields[3];
                genreName = fields[5];

                preparedStatement.setInt(1, year);
                preparedStatement.setString(2, name);
                preparedStatement.setString(3, artistName);
                preparedStatement.setString(4, genreName);
                preparedStatement.executeUpdate();

                System.out.printf("Inserted album: year=%d, name='%s', artist='%s', genre='%s'%n", year, name, artistName, genreName);
            }

            preparedStatement.close();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
