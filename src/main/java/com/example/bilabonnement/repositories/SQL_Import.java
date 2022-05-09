package com.example.bilabonnement.repositories;

import com.example.bilabonnement.models.data.Costumer;
import com.example.bilabonnement.models.data.Subscription;
import com.example.bilabonnement.models.economy.Payment;
import com.example.bilabonnement.services.ModelService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SQL_Import {
    private ModelService modelService;
    private Statement stmt;
    private SQL_Data data;

    public SQL_Import(Statement stmt, SQL_Data data) {
        this.stmt = stmt;
        this.data = data;
    }

    //Object
    public Costumer getCostumer(String value){
        return modelService.generateCostumer(getData("costumer", value));
    }
    public Subscription getSubscription(String value){
        return modelService.generateSubscription(getData("subscription", value));
    }
    public Payment getPayment(String value){
        return modelService.generatePayment(getData("payment",value));
    }
    //Object List
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

    //Entity
    private ResultSet getData(String type, String value){
        try {
            switch(type){
                case "costumer":
                    return stmt.executeQuery("SELECT * FROM " + data.getCostumer_database() + " WHERE " + data.getCostumer_primary_key() + " = '" + value + "'");
                case "subscription":
                    return stmt.executeQuery("SELECT * FROM " + data.getSubscription_database() + " WHERE " + data.getSubscription_primary_key() + " = '" + value + "'");
                case "payment":
                    return stmt.executeQuery("SELECT * FROM " + data.getPayment_database() + " WHERE " + data.getPayment_primary_key() + " = '" + value + "'");
                default:
                    return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //List of Entities
    private ResultSet getDataList(String type){
        try {
            switch (type){
                case "costumer":
                    return stmt.executeQuery("SELECT * FROM " + data.getCostumer_database() + " ORDER BY " + data.getCostumer_primary_key());
                case "subscription":
                    return stmt.executeQuery("SELECT * FROM " + data.getSubscription_database() + " ORDER BY " + data.getSubscription_primary_key());
                case "payment":
                    return stmt.executeQuery("SELECT * FROM " + data.getPayment_database() + " ORDER BY " + data.getPayment_primary_key());
                default:
                    return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
