package org.example;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final HikariDataSource dataSource;
//    private static final String URL = "jdbc:postgresql://localhost:5432/MusicDB";
//    private static final String USER = "postgres";
//    private static final String PASSWORD = "user";
//    private static Connection connection = null;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/MusicDB");
        config.setUsername("postgres");
        config.setPassword("user");

        dataSource = new HikariDataSource(config);
    }
    private Database() {}
    public static Connection getConnection() throws SQLException {
//        if (connection == null) {
//            createConnection();
//        }
//        return connection;
        return dataSource.getConnection();
    }
//    private static void createConnection() {
//        try {
//            connection = DriverManager.getConnection(URL, USER, PASSWORD);
//            connection.setAutoCommit(false);
//        } catch (SQLException e) {
//            System.err.println(e);
//        }
//    }
    public static void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public static void commit(Connection connection) {
        try {
            if (connection != null) {
                connection.commit();
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public static void rollback(Connection connection) {
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
}