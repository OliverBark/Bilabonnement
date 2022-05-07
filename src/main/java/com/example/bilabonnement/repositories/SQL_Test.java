package com.example.bilabonnement.repositories;

import com.example.bilabonnement.models.data.Costumer;
import com.example.bilabonnement.models.data.Subscription;
import com.example.bilabonnement.models.economy.Payment;
import com.example.bilabonnement.services.ModelService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SQL_Test {
    Statement stmt;
    ModelService modelService;
    //Costumer
    private String costumer_database = "costumers";
    private String costumer_primary_key = "cpr_nr";
    //Subscription
    private String subscription_database = "subscriptions";
    private String subscription_primary_key = "subscription_id";
    //Payment
    private String payment_database = "payments";
    private String payment_primary_key = "payment_id";


    public SQL_Test(Statement stmt){
        this.stmt = stmt;
    }


    //get data
    public Costumer getCostumer(String value){
        return modelService.generateCostumer(getData("costumer", value));
    }
    public Subscription getSubscription(String value){
        return modelService.generateSubscription(getData("subscription", value));
    }
    public Payment getPayment(String value){
        return modelService.generatePayment(getData("payment",value));
    }
    //get data list
    public ArrayList<Costumer> getCostumerList(){
        ArrayList<Costumer> costumers = new ArrayList<>();
        try {
            ResultSet rs = getDataList("costumer");
            while(rs.next()){
                costumers.add(modelService.generateCostumer(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return costumers;
    }
    public ArrayList<Subscription> getSubscriptionList(){
        ArrayList<Subscription> subscriptions = new ArrayList<>();
        try {
            ResultSet rs = getDataList("subscription");
            while(rs.next()){
                subscriptions.add(modelService.generateSubscription(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return subscriptions;
    }
    public ArrayList<Payment> getPaymentList(){
        ArrayList<Payment> payments = new ArrayList<>();
        try {
            ResultSet rs = getDataList("payment");
            while(rs.next()){
                payments.add(modelService.generatePayment(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return payments;
    }

    //update data
    public void updateCostumer(Costumer costumer){

    }
    public void updateSubscription(Subscription subscription){

    }
    public void updatePayment(Payment payment){

    }
    //Get Data - Processor
    private ResultSet getData(String type, String value){
        try {
            switch(type){
                case "costumer":
                    return stmt.executeQuery("SELECT * FROM " + costumer_database + " WHERE " + costumer_primary_key + " = '" + value + "'");
                case "subscription":
                    return stmt.executeQuery("SELECT * FROM " + subscription_database + " WHERE " + subscription_primary_key + " = '" + value + "'");
                case "payment":
                    return stmt.executeQuery("SELECT * FROM " + payment_database + " WHERE " + payment_primary_key + " = '" + value + "'");
                default:
                    return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private ResultSet getDataList(String type){
        try {
            switch (type){
                case "costumer":
                    return stmt.executeQuery("SELECT * FROM " + costumer_database + " ORDER BY " + costumer_primary_key);
                case "subscription":
                    return stmt.executeQuery("SELECT * FROM " + payment_database + " ORDER BY " + subscription_primary_key);
                case "payment":
                    return stmt.executeQuery("SELECT * FROM " + payment_database + " ORDER BY " + payment_primary_key);
                default:
                    return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    //Add Data
    public void addData(Object object){
        if(object instanceof Costumer){
            insertCostumer((Costumer) object);
        }
        if(object instanceof Subscription){
            insertSubscription((Subscription) object);
        }
        if(object instanceof Payment){
            insertPayment((Payment) object);
        }
    }
    //Add Data - Processor
    private void insertCostumer(Costumer costumer){
        try {
            stmt.executeUpdate("INSERT INTO * " + costumer_database +
                    "(first_name, last_name, address, mobile, cpr_nr, reg_nr, account_nr)" +
                    " VALUES('" +
                    costumer.getFirstName() + "', '" +
                    costumer.getLastName() + "', '" +
                    costumer.getAddress() + "', '" +
                    costumer.getMobile() + "', '" +
                    costumer.getCprNr() + "', '" +
                    costumer.getRegNr() + "', '" +
                    costumer.getAccountNr() + "')");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void insertSubscription(Subscription subscription){
        try {
            stmt.executeUpdate("INSERT INTO * " + subscription_database +
                    "(subscription_id, model, color, afleveringsforsikring, selvrisiko, location)" +
                    " VALUES('" +
                    subscription.getSubscriptionID() + "', '" +
                    subscription.getModel() + "', '" +
                    subscription.getColor() + "', '" +
                    subscription.isAfleveringsforsikring() + "', '" +
                    subscription.isSelvrisiko() + "', '" +
                    subscription.getLocation() + "')");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    private void insertPayment(Payment payment){
        try {
            stmt.executeUpdate("INSERT INTO * " + payment_database +
                    "(payment_id, reg_nr, account_nr, price)" +
                    " VALUES('" +
                    payment.getPaymentID() + "', '" +
                    payment.getRegNr() + "', '" +
                    payment.getAccountNr() + "', '" +
                    payment.getPrice() + "')");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
