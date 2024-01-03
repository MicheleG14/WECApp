package com.unisa;

import java.sql.*;

public class Database {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/f1";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "1234";

    private final Connection con;
    private final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";

    public Database() throws ClassNotFoundException, SQLException {

        Class.forName(DRIVER_CLASS); //load driver
        this.con = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
    }

    public ResultSet execQuery(String query) throws SQLException {

        ResultSet set = null;

        Statement stmt = this.con.createStatement();
        set = stmt.executeQuery(query);
        return set;
    }

    public void insertQuery(String query) throws SQLException {
        try {
            Statement stmt = this.con.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

    }
}
