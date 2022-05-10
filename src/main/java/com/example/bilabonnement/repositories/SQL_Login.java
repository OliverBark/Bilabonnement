package com.example.bilabonnement.repositories;

import com.example.bilabonnement.models.admin.User;
import com.example.bilabonnement.services.ModelService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQL_Login {
    private ModelService modelService;
    private Statement stmt;
    private SQL_Data data;

    public SQL_Login(Statement stmt, SQL_Data data) {
        this.stmt = stmt;
        this.data = data;
    }

    public User getUser(String username, String password){
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + data.getUser_database() + " WHERE " +
                    data.getUser_primary_key() + " = '" + username + "'");
            if(rs.getString("password").equals(password)){
                return modelService.generateUser(rs);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
