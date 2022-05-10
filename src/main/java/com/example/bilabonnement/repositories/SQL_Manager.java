package com.example.bilabonnement.repositories;

import java.sql.*;

public class SQL_Manager {
    public Statement establishConnection(){
        String url = "";
        String user = "";
        String password = "";
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            return stmt;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
