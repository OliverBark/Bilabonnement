package com.example.bilabonnement.repositories;

import java.sql.*;

public class SQL_Manager {

    private ResultSet rs;
    private Statement stmt;


    public Statement establishConnection(){
        String url = "jdbc:mysql://localhost:3306/Bilabonnement";
        String user = "root";
        String password = "testtest";
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            return stmt;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean login(String username, String password) { //User can log in, and program registers credentials
        try {
            rs = stmt.executeQuery("SELECT * FROM users ORDER BY username");
            while (rs.next()) {
                if (rs.getString("username").equals(username)) {
                    System.out.println("user found");
                    if (rs.getString("password").equals(password)) {
                        System.out.println("password matches");
                        return true;
                    } else {
                        System.out.println("invalid password");
                        return false;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error?");
        }
        return false;
    }

    public void start() {
        establishConnection();
    }
}
