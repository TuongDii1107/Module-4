package com.sqc.academy.kiemtra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseRepository {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/tmarketplace";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "12345";

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }
}
