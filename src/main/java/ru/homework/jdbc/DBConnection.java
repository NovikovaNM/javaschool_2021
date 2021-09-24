
package ru.homework.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBConnection {

    private static final Logger log = LoggerFactory.getLogger(DBConnection.class);
    private Connection conn = null;
    private final String URL = "jdbc:hsqldb:mem:sampleDB";
    private final String USER = "SA";
    private final String PASSWORD = "";

    public void close() {
        try {
            if (conn != null) {
                conn.close();
                log.info("Close connection");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        try {
            if (conn == null) {
                conn = DriverManager.getConnection(URL,USER,PASSWORD);
                log.info("Open connection");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }
}
