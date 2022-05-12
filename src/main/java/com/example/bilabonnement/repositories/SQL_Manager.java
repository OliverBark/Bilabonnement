package com.example.bilabonnement.repositories;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class SQL_Manager {
    private static Connection conn;
    private ResultSet rs;
    private Statement stmt;


    public Statement establishConnection() {

        try (InputStream propertiesFiles = new FileInputStream("src/main/resources/application.properties")) {
            Properties properties = new Properties();
            properties.load(propertiesFiles);
            String url = properties.getProperty("db.url");
            String user = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");
            Connection con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return stmt;
    }

    //Ny get connection metode, I skal ændre jeres adgangskode i application.properties til jeres egen i jeres Workbench
    /*public static Connection getConnection() {
        if (conn != null) {
            return conn;
        }

        try (InputStream propertiesFiles = new FileInputStream("src/main/resources/application.properties")) {
            Properties properties = new Properties();
            properties.load(propertiesFiles);
            String url = properties.getProperty("db.url");
            String username = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return conn;
    }*/


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
