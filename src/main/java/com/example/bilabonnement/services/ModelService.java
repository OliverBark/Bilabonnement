package com.example.bilabonnement.services;

import com.example.bilabonnement.models.data.Costumer;
import com.example.bilabonnement.models.data.Subscription;
import com.example.bilabonnement.models.economy.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ModelService {
    //Generate Model
    public Costumer generateCostumer(ResultSet rs){
        try {
            return new Costumer(rs.getString("first_name"), rs.getString("last_name"), rs.getString("address"),
                    rs.getString("moble"), rs.getString("cpr_nr"), rs.getString("reg_nr"), rs.getString("account nr"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Subscription generateSubscription(ResultSet rs){
        try {
            return new Subscription(rs.getString("subscription_id"), rs.getString("model"), rs.getString("color"),
                    Boolean.getBoolean(rs.getString("afleveringsforsikring")), Boolean.getBoolean(rs.getString("selvrisiko")), rs.getString("location"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Payment generatePayment(ResultSet rs){
        try {
            return new Payment(rs.getString("payment_id"), rs.getString("reg_nr"), rs.getString("account_nr"),
                    rs.getDouble("price"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    
    public Costumer here (String costumer){
        String[] data = costumer.split("\\|");
        String firstName = data[0];
        String lastName = data[1];
        String address = data[2];
        String mobile = data[3];
        String cprNr = data[4];
        String regNr = data[5];
        String accountNr = data[6];
        return new Costumer(firstName, lastName, address, mobile, cprNr, regNr, accountNr);
    }
}
