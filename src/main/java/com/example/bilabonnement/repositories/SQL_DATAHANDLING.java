package com.example.bilabonnement.repositories;

import com.example.bilabonnement.models.data.Costumer;
import com.example.bilabonnement.models.data.Subscription;
import com.example.bilabonnement.models.economy.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SQL_DATAHANDLING {
    private Statement stmt;
    //Costumer
    private String costumer_database = "costumers";
    private String costumer_primary_key = "cpr_nr";
    //Subscription
    private String subscription_database = "subscriptions";
    private String subscription_primary_key = "subscription_id";
    //Payment
    private String payment_database = "payments";
    private String payment_primary_key = "payment_id";

    public SQL_DATAHANDLING(Statement stmt){
        this.stmt = stmt;
    }


    //Costumers
    public Costumer getCostumer(String cpr){
        Costumer costumer;
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + costumer_database + " WHERE " + costumer_primary_key +
                    " = '" + cpr + "'");
            costumer = generateCostumer(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return costumer;
    }
    public ArrayList<Costumer> getCostumerData(){
        ArrayList<Costumer> costumers = new ArrayList<>();
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + costumer_database + " ORDER BY " + costumer_primary_key);
            while(rs.next()){
                costumers.add(generateCostumer(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return costumers;
    }
    private Costumer generateCostumer(ResultSet rs){
        try {
        return new Costumer(rs.getString("first_name"), rs.getString("last_name"), rs.getString("address"),
                rs.getString("moble"), rs.getString("cpr_nr"), rs.getString("reg_nr"), rs.getString("account nr"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Subscriptions
    public Subscription getSubscription(String cpr){
        Subscription subscription;
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + subscription_database + " WHERE " +
                    subscription_primary_key + " = '" + cpr + "'");
            subscription = generateSubscription(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return subscription;
    }
    public ArrayList<Costumer> getSubscriptionList(){
        ArrayList<Costumer> costumers = new ArrayList<>();
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + costumer_database + " ORDER BY " + costumer_primary_key);
            while(rs.next()){
                costumers.add(generateCostumer(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return costumers;
    }
    private Subscription generateSubscription(ResultSet rs){
        try {
            return new Subscription(rs.getString("subscription_id"), rs.getString("model"), rs.getString("color"),
                    Boolean.getBoolean(rs.getString("afleveringsforsikring")), Boolean.getBoolean(rs.getString("selvrisiko")), rs.getString("location"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Payments
    public Payment getPayment(String paymentID){
        Payment payment;
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + payment_database + " WHERE " + payment_primary_key +
                    " = '" + paymentID + "'");
            payment = generatePayment(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return payment;
    }
    public ArrayList<Payment> getPaymentData(){
        ArrayList<Payment> payments = new ArrayList<>();
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + costumer_database + " ORDER BY " + costumer_primary_key);
            while(rs.next()){
                payments.add(generatePayment(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return payments;
    }
    private Payment generatePayment(ResultSet rs){
        try {
            return new Payment(rs.getString("payment_id"), rs.getString("reg_nr"), rs.getString("account_nr"),
                    rs.getDouble("price"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
