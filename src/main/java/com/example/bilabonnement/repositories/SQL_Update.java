package com.example.bilabonnement.repositories;

import com.example.bilabonnement.models.data.Costumer;
import com.example.bilabonnement.models.data.Subscription;
import com.example.bilabonnement.models.economy.Payment;
import com.example.bilabonnement.services.ModelService;

import java.sql.SQLException;
import java.sql.Statement;

public class SQL_Update {
    private ModelService modelService;
    private Statement stmt;
    private SQL_Data data;

    public SQL_Update(Statement stmt, SQL_Data data) {
        this.stmt = stmt;
        this.data = data;
    }

    public void deleteData(Object object){
        if(object instanceof Costumer){
            deleteCostumer((Costumer) object);
        }
        if(object instanceof Subscription){
            deleteSubscription((Subscription) object);
        }
        if(object instanceof Payment){
            deletePayment((Payment) object);
        }
    }

    private void deleteCostumer(Costumer costumer){
        try {
            stmt.executeUpdate("DELETE FROM " + data.getCostumer_database() + " WHERE " + data.getCostumer_primary_key() + " = '" + costumer.getCprNr() + "'");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void deleteSubscription(Subscription subscription){
        try {
            stmt.executeUpdate("DELETE FROM " + data.getSubscription_database() + " WHERE " + data.getSubscription_primary_key() + " = '" + subscription.getSubscriptionID() + "'");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void deletePayment(Payment payment){
        try {
            stmt.executeUpdate("DELETE FROM " + data.getPayment_database() + " WHERE " + data.getPayment_primary_key() + " = '" + payment.getPaymentID() + "'");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
